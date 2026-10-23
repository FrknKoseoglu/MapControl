#!/usr/bin/env python3
"""Run on Linux with a real APK and fake ADB; never connects to a device."""
import argparse
import json
import os
from pathlib import Path
import subprocess
import tempfile
import unittest

parser = argparse.ArgumentParser()
parser.add_argument("--script", required=True)
parser.add_argument("--apk", required=True)
args, remaining = parser.parse_known_args()
SCRIPT = Path(args.script).resolve()
APK = Path(args.apk).resolve()
FAKE = r'''#!/usr/bin/env python3
import json, os, re, shlex, sys
from pathlib import Path
p = Path(os.environ["MOCK_STATE"])
s = json.loads(p.read_text())
a = sys.argv[1:]
s["calls"].append(a)
def done(value="", code=0):
    p.write_text(json.dumps(s))
    print(value)
    sys.exit(code)
if a == ["devices", "-l"]:
    done("List of devices attached\n" + s.get("devices", "CAR_TEST device product:mock"))
if a[:1] == ["-s"]: a = a[2:]
if a == ["get-state"]: done("device")
if a[:1] == ["install"]: done(s.get("install", "Success"))
if a[:1] != ["shell"]: done("Unexpected ADB call", 1)
a = shlex.split(a[1])
if a == ["am", "get-current-user"]: done("0")
if a[:1] == ["getprop"]: done("30")
if a[:2] == ["pm", "path"]: done("package:/data/app/test/base.apk")
if a[:2] == ["dumpsys", "package"]:
    # Read the actual permission array, including legacy OEM names.
    perms = re.findall(r"(?:android\.permission|ivi\.vds\.permission)\.[A-Z_]+", Path(os.environ["MOCK_SCRIPT"]).read_text())
    rows = ["Package [com.mapcontrol.testfk]", "install permissions:"]
    rows += [f"  {x}: granted={'false' if x == s.get('deny') else 'true'}" for x in set(perms)]
    done("\n".join(rows))
if a[:2] == ["pm", "grant"]: done("SecurityException: permission cannot be granted")
if a[:3] == ["cmd", "appops", "set"]: done()
if a[:3] == ["cmd", "appops", "get"]: done(a[-1] + ": " + ("default" if s.get("bad_appops") else "allow"))
if a[:2] == ["dumpsys", "deviceidle"]: done("user,com.mapcontrol.testfk,10123")
if a[:1] == ["settings"]:
    user, action, key = a[2], a[3], a[5]
    settings = s.setdefault("settings", {}).setdefault(user, {})
    if action == "get":
        if s.get("read_error") and key == "enabled_accessibility_services": done("Error: permission denied")
        done(settings.get(key, "null"))
    settings[key] = a[6]
    done()
if a[:3] == ["cmd", "notification", "allow_listener"]: done()
if a == ["dumpsys", "device_policy"]: done("Device Owner:\n  admin=ComponentInfo{com.mapcontrol/com.mapcontrol.admin.MapControlDeviceAdminReceiver}")
if a[:2] == ["am", "start"]: done("Status: ok")
done("UNEXPECTED: " + repr(a), 1)
'''

class InstallerTests(unittest.TestCase):
    def setUp(self):
        self.temp = tempfile.TemporaryDirectory(prefix="test-fk-")
        self.root = Path(self.temp.name)
        (self.root / APK.name).symlink_to(APK)
        self.fake = self.root / "adb"
        self.fake.write_text(FAKE)
        self.fake.chmod(0o700)
        self.statefile = self.root / "state.json"
        self.state = {"calls": [], "settings": {"0": {
            "enabled_accessibility_services": "other.app/.Service:com.mapcontrol/com.mapcontrol.service.GlobalBackService",
            "enabled_notification_listeners": "other.media/.Listener"}, "10": {
            "enabled_accessibility_services": "work.app/.Service",
            "enabled_notification_listeners": "work.media/.Listener"}}}
    def tearDown(self):
        self.temp.cleanup()
    def run_installer(self, *options):
        self.statefile.write_text(json.dumps(self.state))
        env = dict(os.environ, TESTFK_ADB=str(self.fake), TESTFK_ROOT=str(self.root),
                   TESTFK_LOG_ROOT=str(self.root / "logs"), MOCK_STATE=str(self.statefile), MOCK_SCRIPT=str(SCRIPT))
        result = subprocess.run(["bash", str(SCRIPT), *options], env=env, text=True,
                                stdout=subprocess.PIPE, stderr=subprocess.STDOUT, timeout=45)
        self.state = json.loads(self.statefile.read_text())
        self.assertNotIn("UNEXPECTED:", result.stdout)
        return result
    def test_preserves_lists_and_repeat_is_idempotent(self):
        old = dict(self.state["settings"]["0"])
        r = self.run_installer()
        self.assertEqual(r.returncode, 0, r.stdout)
        for key, value in old.items():
            self.assertTrue(self.state["settings"]["0"][key].startswith(value + ":"))
        installed = dict(self.state["settings"]["0"])
        r = self.run_installer("--device-owner")
        self.assertEqual(r.returncode, 2, r.stdout)
        self.assertEqual(installed, self.state["settings"]["0"])
        self.assertFalse(any("dpm" in " ".join(c) for c in self.state["calls"]))
    def test_install_failure_with_zero_exit_stops_permissions(self):
        self.state["install"] = "Failure [INSTALL_FAILED_UPDATE_INCOMPATIBLE]"
        r = self.run_installer()
        self.assertEqual(r.returncode, 1, r.stdout)
        self.assertFalse(any("shell" in c and "settings" in c[-1] for c in self.state["calls"]))
        self.assertFalse(any("shell" in c and "grant" in c[-1] for c in self.state["calls"]))
    def test_multiple_devices_rejected_before_install(self):
        self.state["devices"] = "CAR_TEST device\nSECOND device"
        self.assertEqual(self.run_installer().returncode, 1)
        self.assertFalse(any("install" in c for c in self.state["calls"]))
        self.assertEqual(self.run_installer("--serial", "CAR_TEST").returncode, 0)
    def test_unauthorized_and_implicit_emulator_rejected(self):
        for devices in ("CAR_TEST unauthorized", "emulator-5584 device"):
            self.state["devices"] = devices
            self.assertEqual(self.run_installer().returncode, 1)
            self.assertFalse(any("install" in c for c in self.state["calls"]))
    def test_permission_and_appops_failure_reported(self):
        self.state["deny"] = "android.permission.CAMERA"
        self.state["bad_appops"] = True
        r = self.run_installer()
        self.assertEqual(r.returncode, 2, r.stdout)
        self.assertIn("SecurityException", r.stdout)
        self.assertIn("SYSTEM_ALERT_WINDOW: default", r.stdout)
    def test_target_user_and_other_user_preserved(self):
        old = dict(self.state["settings"]["0"])
        r = self.run_installer("--user", "10", "--no-launch")
        self.assertEqual(r.returncode, 0, r.stdout)
        self.assertEqual(old, self.state["settings"]["0"])
        self.assertIn("work.app/.Service:", self.state["settings"]["10"]["enabled_accessibility_services"])
        self.assertFalse(any("'am' 'start'" in c[-1] for c in self.state["calls"]))
    def test_read_failure_does_not_overwrite_accessibility(self):
        self.state["read_error"] = True
        old = self.state["settings"]["0"]["enabled_accessibility_services"]
        r = self.run_installer()
        self.assertEqual(r.returncode, 2, r.stdout)
        self.assertEqual(old, self.state["settings"]["0"]["enabled_accessibility_services"])
    def test_invalid_user_and_checksum_stop_before_adb(self):
        self.assertEqual(self.run_installer("--user", "oops").returncode, 1)
        (self.root / APK.name).unlink()
        (self.root / APK.name).write_text("wrong apk")
        self.assertEqual(self.run_installer().returncode, 1)
        self.assertEqual(self.state["calls"], [])

if __name__ == "__main__":
    unittest.main(argv=[__file__, *remaining])
