#!/usr/bin/env python3
"""Build an emulator-only OEM window lookalike. Never installs or connects to any device."""
import argparse
import os
from pathlib import Path
import subprocess
import zipfile

parser=argparse.ArgumentParser()
parser.add_argument("--sdk",default=os.environ.get("ANDROID_HOME") or str(Path(os.environ.get("LOCALAPPDATA",""))/"Android/Sdk"))
parser.add_argument("--java",default=os.environ.get("JAVA_HOME","C:/Program Files/Java/jdk-17"))
args=parser.parse_args()
root=Path(__file__).resolve().parents[2]
src=root/"scripts/tests/fixtures/sport-window"
out=root/"output/sport-window-fixture"
out.mkdir(parents=True,exist_ok=True)
sdk=Path(args.sdk);java=Path(args.java)
build=sdk/"build-tools/34.0.0";android=sdk/"platforms/android-34/android.jar"
for name in ("gen","classes","dex"): (out/name).mkdir(exist_ok=True)
def run(command): subprocess.run([str(x) for x in command],check=True,cwd=out)
run([build/"aapt.exe","package","-f","-m","-J",out/"gen","-M",src/"AndroidManifest.xml","-S",src/"res","-I",android,"-F",out/"unsigned.apk"])
run([java/"bin/javac.exe","-source","8","-target","8","-classpath",android,"-d",out/"classes",*src.glob("*.java"),*(out/"gen").rglob("*.java")])
run([build/"d8.bat","--lib",android,"--output",out/"dex",*(out/"classes").rglob("*.class")])
with zipfile.ZipFile(out/"unsigned.apk","a") as z: z.write(out/"dex/classes.dex","classes.dex")
run([build/"apksigner.bat","sign","--ks",Path.home()/".android/debug.keystore","--ks-pass","pass:android","--out",out/"fixture.apk",out/"unsigned.apk"])
print(out/"fixture.apk")
