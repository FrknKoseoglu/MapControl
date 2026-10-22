# Kaynak envanteri ve denetim izi

[İndeks](README.md) · [Semantik tasarım](design.md) · [Bileşenler](components.md)

22 Ekim 2026 **ortak başlık yer değişiminden önceki** çalışma ağacı taraması. Sonraki ortak başlık değişikliği README ve components belgelerinde açıklanır. Bu dosya ham kaynak kanıtıdır; buradaki eski ölçüler yeni HMI hedefleri değildir. Satır numaraları bu anlık görüntüye aittir. Kod ifadeleri bağlamlarıyla korunur; hesaplanan boyutların sonucunu veya erişim uygunluğunu otomatik olarak kanıtlamaz. OEM event numaraları görsel renk tokenı sayılmaz.

## Kapsam

358 metin kaynak dosyası okundu: native app kodu, OEM istemci tanımları, res XML ve welcome HTML. Build çıktıları, binary res/font/GLB/PNG/MP3 içerikleri metin tokenı diye ayrıştırılmadı. Assets vehicle_models_tr.json bir UI token dosyası değildir. Tüm app/src dosyalarının hash kapsamı audit-manifest.json içindedir.

## Dosya haritası

| Kaynak | Satır | SHA-256 |
|---|---:|---|
| [app/src/main/AndroidManifest.xml](../../app/src/main/AndroidManifest.xml) | 228 | `7808410b58156869ae7ef660ef10757d967ba44362384fa3769393c1116ffcbd` |
| [app/src/main/assets/welcome.html](../../app/src/main/assets/welcome.html) | 1628 | `2be95d05888e23285fc64672ffa7af154b7f93919fe304b68be37eb46ff4557b` |
| [app/src/main/java/com/desaysv/ivi/extra/project/carinfo/AccountID.java](../../app/src/main/java/com/desaysv/ivi/extra/project/carinfo/AccountID.java) | 5 | `94475de04b47a8dd072dc7f3e56857c83d8d59c96de948cecc84fa4a1db3ee23` |
| [app/src/main/java/com/desaysv/ivi/extra/project/carinfo/AmbientLightID.java](../../app/src/main/java/com/desaysv/ivi/extra/project/carinfo/AmbientLightID.java) | 6 | `e2da7ec29ec82a56c417e5fdfa0290be0735e76d4194b1a41e70720d0a3a2627` |
| [app/src/main/java/com/desaysv/ivi/extra/project/carinfo/AutoParkingID.java](../../app/src/main/java/com/desaysv/ivi/extra/project/carinfo/AutoParkingID.java) | 5 | `17806dafbab05831dcef03105727af2da9fc2e742dc7e6951902214709412144` |
| [app/src/main/java/com/desaysv/ivi/extra/project/carinfo/AvmID.java](../../app/src/main/java/com/desaysv/ivi/extra/project/carinfo/AvmID.java) | 13 | `3e4809ebdf1ec2fea527f4326f10697a1f15607a391a33852ca6af7b285e5c3f` |
| [app/src/main/java/com/desaysv/ivi/extra/project/carinfo/CarSettingID.java](../../app/src/main/java/com/desaysv/ivi/extra/project/carinfo/CarSettingID.java) | 201 | `967a30f93b2f8fc3d366c6d7ab7d02e2b1c575f6ca48494c0ae917bdf90769db` |
| [app/src/main/java/com/desaysv/ivi/extra/project/carinfo/DiagnosisID.java](../../app/src/main/java/com/desaysv/ivi/extra/project/carinfo/DiagnosisID.java) | 21 | `bfac8a7c5a93dd05a4dfc42c2e1eea3c63c86bd71d5179c950aadb355e9d2eab` |
| [app/src/main/java/com/desaysv/ivi/extra/project/carinfo/DVRID.java](../../app/src/main/java/com/desaysv/ivi/extra/project/carinfo/DVRID.java) | 18 | `549f1b67d1ac63944d82c3a78304741d862f7e981744898b041218cec8530f10` |
| [app/src/main/java/com/desaysv/ivi/extra/project/carinfo/ExternalAmpID.java](../../app/src/main/java/com/desaysv/ivi/extra/project/carinfo/ExternalAmpID.java) | 5 | `14f15632d08c08c8a334466a195f64826bed247c791c54fc9a6f88571ac07aab` |
| [app/src/main/java/com/desaysv/ivi/extra/project/carinfo/HUDID.java](../../app/src/main/java/com/desaysv/ivi/extra/project/carinfo/HUDID.java) | 24 | `914986453622c45787f5e8abea4bb8e0750d043c76d95887914454a71c24c867` |
| [app/src/main/java/com/desaysv/ivi/extra/project/carinfo/HvacID.java](../../app/src/main/java/com/desaysv/ivi/extra/project/carinfo/HvacID.java) | 86 | `410cc75a90549cc51d6e042606668bb89cb6d69b681757650754d3311b6e0c33` |
| [app/src/main/java/com/desaysv/ivi/extra/project/carinfo/MeterMalfunctionID.java](../../app/src/main/java/com/desaysv/ivi/extra/project/carinfo/MeterMalfunctionID.java) | 26 | `30f722565705fc5341b501b57819c9b93710a22676c04cd611e5a5907cf997c6` |
| [app/src/main/java/com/desaysv/ivi/extra/project/carinfo/NewEnergyID.java](../../app/src/main/java/com/desaysv/ivi/extra/project/carinfo/NewEnergyID.java) | 101 | `612a835cd9d739695fe1419a6dd5ccb338ad661a04002a909250b194a42559a2` |
| [app/src/main/java/com/desaysv/ivi/extra/project/carinfo/OtaID.java](../../app/src/main/java/com/desaysv/ivi/extra/project/carinfo/OtaID.java) | 5 | `aeeb20c826d18b907167ca1fb8344a3e733a03ec64430cddf4962426d9e075bc` |
| [app/src/main/java/com/desaysv/ivi/extra/project/carinfo/proxy/CarInfoHelper.java](../../app/src/main/java/com/desaysv/ivi/extra/project/carinfo/proxy/CarInfoHelper.java) | 233 | `dbc001b3a99f02efcd5d3c2a519a4b4ab552ee813f89a4970df8de372937e88c` |
| [app/src/main/java/com/desaysv/ivi/extra/project/carinfo/proxy/CarInfoProxy.java](../../app/src/main/java/com/desaysv/ivi/extra/project/carinfo/proxy/CarInfoProxy.java) | 141 | `66e9f534f62f156497e5b7ac912a68b9f18f340ca585d510ba7ff1fb0f958bc9` |
| [app/src/main/java/com/desaysv/ivi/extra/project/carinfo/proxy/CarInfoTimer.java](../../app/src/main/java/com/desaysv/ivi/extra/project/carinfo/proxy/CarInfoTimer.java) | 41 | `f7586186dd6d195e0735f9057bf7847df25b5229666e162c168a0854f5aac6d1` |
| [app/src/main/java/com/desaysv/ivi/extra/project/carinfo/proxy/Constants.java](../../app/src/main/java/com/desaysv/ivi/extra/project/carinfo/proxy/Constants.java) | 27 | `d07cc3db1f156142dbbf9de60f46d71b459e709135746cd712e18b3255cea274` |
| [app/src/main/java/com/desaysv/ivi/extra/project/carinfo/proxy/IServiceConnectListener.java](../../app/src/main/java/com/desaysv/ivi/extra/project/carinfo/proxy/IServiceConnectListener.java) | 6 | `f1d25d20acba537c316a32c6a445ef128903ccb38b819d814e3ed89c6f48a2ca` |
| [app/src/main/java/com/desaysv/ivi/extra/project/carinfo/QueryID.java](../../app/src/main/java/com/desaysv/ivi/extra/project/carinfo/QueryID.java) | 13 | `f851b57f3fd1e4287543de6b24a4fe39e9071f3200e3ff21f5bc457b5d6ed7ba` |
| [app/src/main/java/com/desaysv/ivi/extra/project/carinfo/RadarID.java](../../app/src/main/java/com/desaysv/ivi/extra/project/carinfo/RadarID.java) | 20 | `6b84fadd2761525a6dc17f2e790334841b88cb2db3702bd7111c230e7d7682f3` |
| [app/src/main/java/com/desaysv/ivi/extra/project/carinfo/ReadOnlyID.java](../../app/src/main/java/com/desaysv/ivi/extra/project/carinfo/ReadOnlyID.java) | 97 | `7232fffa857023014d498946240cadddce1c6a9359eddff0dc5387cb52e86ba5` |
| [app/src/main/java/com/desaysv/ivi/extra/project/carinfo/RvcID.java](../../app/src/main/java/com/desaysv/ivi/extra/project/carinfo/RvcID.java) | 6 | `94c9746bd92bb53c5326f171ff4c91632332558d74663979279ce1c4ece4c19e` |
| [app/src/main/java/com/desaysv/ivi/extra/project/carinfo/SelfDiagnosisID.java](../../app/src/main/java/com/desaysv/ivi/extra/project/carinfo/SelfDiagnosisID.java) | 5 | `0d9c0451f3306b21ee2e9fd1e9318c74cb3d967e428f1c07d780d030040820ee` |
| [app/src/main/java/com/desaysv/ivi/extra/project/carinfo/VrID.java](../../app/src/main/java/com/desaysv/ivi/extra/project/carinfo/VrID.java) | 15 | `67ded553cd362e891d59341c43520c6d6c392a111722d9909669d06189f7f5c8` |
| [app/src/main/java/com/desaysv/ivi/vdb/client/bind/a.java](../../app/src/main/java/com/desaysv/ivi/vdb/client/bind/a.java) | 5 | `ecc8d9c68fdd1869c8eb2023df264e43247899aad2f57d810bd906e86a49959a` |
| [app/src/main/java/com/desaysv/ivi/vdb/client/bind/b.java](../../app/src/main/java/com/desaysv/ivi/vdb/client/bind/b.java) | 5 | `5f4155d84bcf8ad6c50230f73f26059d1dc67137d404a581768a7bcaa5ff8a3c` |
| [app/src/main/java/com/desaysv/ivi/vdb/client/bind/VDConnector.java](../../app/src/main/java/com/desaysv/ivi/vdb/client/bind/VDConnector.java) | 1014 | `074e7be5d91a5a608a296542e44f8cd9728f83720c1e0a9459d13dc954efead1` |
| [app/src/main/java/com/desaysv/ivi/vdb/client/bind/VDRouter.java](../../app/src/main/java/com/desaysv/ivi/vdb/client/bind/VDRouter.java) | 471 | `e8f93ad6268124f66de938490f6263858c6589ca3c7ef045041eb14b23ba8b7c` |
| [app/src/main/java/com/desaysv/ivi/vdb/client/bind/VDServiceDef.java](../../app/src/main/java/com/desaysv/ivi/vdb/client/bind/VDServiceDef.java) | 364 | `08d894c99d8b459aee322a82d258b78803bc6a26f1d93516aad2cbc599cc137c` |
| [app/src/main/java/com/desaysv/ivi/vdb/client/bind/VDThreadConfig.java](../../app/src/main/java/com/desaysv/ivi/vdb/client/bind/VDThreadConfig.java) | 21 | `8a567adc99ac49a9b632dd5e762d37663422e9366a5cce8a77db4b50e5060f69` |
| [app/src/main/java/com/desaysv/ivi/vdb/client/bind/VDThreadType.java](../../app/src/main/java/com/desaysv/ivi/vdb/client/bind/VDThreadType.java) | 8 | `0df4bd0ca668eaca3c8fde2afde7c3216efa4620fbe7ecd1408bdf1afb7d1c9d` |
| [app/src/main/java/com/desaysv/ivi/vdb/client/listener/VDBindListener.java](../../app/src/main/java/com/desaysv/ivi/vdb/client/listener/VDBindListener.java) | 10 | `dc691775208f060fc4da94864a9c875779ebaaabce2c83fa6f72d1a51462b085` |
| [app/src/main/java/com/desaysv/ivi/vdb/client/listener/VDCallbackListener.java](../../app/src/main/java/com/desaysv/ivi/vdb/client/listener/VDCallbackListener.java) | 10 | `4a5b61afb5e40caac8e4f7164c37e4b8cb714c66cc07a02ae84e87432935001b` |
| [app/src/main/java/com/desaysv/ivi/vdb/client/listener/VDGetListener.java](../../app/src/main/java/com/desaysv/ivi/vdb/client/listener/VDGetListener.java) | 8 | `783131a604d5290d993fa1330cae396f5f93ebe6aa3d4006614aaa9d5ed4b9e9` |
| [app/src/main/java/com/desaysv/ivi/vdb/client/listener/VDGetListListener.java](../../app/src/main/java/com/desaysv/ivi/vdb/client/listener/VDGetListListener.java) | 9 | `0fc65b8e3d335539ea13ffab504e274447f8622b9e3b8f262903c0288cfd373a` |
| [app/src/main/java/com/desaysv/ivi/vdb/client/listener/VDNotifyListener.java](../../app/src/main/java/com/desaysv/ivi/vdb/client/listener/VDNotifyListener.java) | 8 | `1a357429b93a6020f7c02e12b7052105820eb18dffec3353b6a4475a68482306` |
| [app/src/main/java/com/desaysv/ivi/vdb/client/VDBus.java](../../app/src/main/java/com/desaysv/ivi/vdb/client/VDBus.java) | 154 | `18fc356e831a64a923159d581283fc2552cbc2b4668b4b7fcba6194fb930dc2f` |
| [app/src/main/java/com/desaysv/ivi/vdb/event/base/VDBundleParcelable.java](../../app/src/main/java/com/desaysv/ivi/vdb/event/base/VDBundleParcelable.java) | 12 | `ce3236ff44a22311331bbebb6d69c0e74e8e6805c6ac9bbc7fdcdff1a219aea5` |
| [app/src/main/java/com/desaysv/ivi/vdb/event/base/VDKey.java](../../app/src/main/java/com/desaysv/ivi/vdb/event/base/VDKey.java) | 18 | `ed50cfbd972744469218edbe14daa95d3ce90856edd69ba759b18fdef1b04287` |
| [app/src/main/java/com/desaysv/ivi/vdb/event/base/VDValue.java](../../app/src/main/java/com/desaysv/ivi/vdb/event/base/VDValue.java) | 76 | `109c01bfc1da523138895d8edda91eb4b95651a7aa7ea94c48c7036374f4e7cc` |
| [app/src/main/java/com/desaysv/ivi/vdb/event/id/bt/VDEventBT.java](../../app/src/main/java/com/desaysv/ivi/vdb/event/id/bt/VDEventBT.java) | 95 | `ff1978ff49fd28d83af66ce8dfe00ba519e9477764de7e4c3c5ac42e6c0fb6d7` |
| [app/src/main/java/com/desaysv/ivi/vdb/event/id/cabin/bean/VDCLCommonMessage.java](../../app/src/main/java/com/desaysv/ivi/vdb/event/id/cabin/bean/VDCLCommonMessage.java) | 149 | `8b6fb5231b93d621cc0b5255e32f1b27b62b612e3cebd93291e77ee05a9406f7` |
| [app/src/main/java/com/desaysv/ivi/vdb/event/id/cabin/bean/VDCLCommonMsgKeyValue.java](../../app/src/main/java/com/desaysv/ivi/vdb/event/id/cabin/bean/VDCLCommonMsgKeyValue.java) | 13 | `6819ccd435badc179a04b67b283686fc28890a74bcdf82ccc137c42754766a02` |
| [app/src/main/java/com/desaysv/ivi/vdb/event/id/cabin/VDEventCabinLan.java](../../app/src/main/java/com/desaysv/ivi/vdb/event/id/cabin/VDEventCabinLan.java) | 43 | `27599e10e66c3586c0a2d711e296e7c224b3327cb23a58f81b701c667c67c620` |
| [app/src/main/java/com/desaysv/ivi/vdb/event/id/cabin/VDValueCabinLan.java](../../app/src/main/java/com/desaysv/ivi/vdb/event/id/cabin/VDValueCabinLan.java) | 276 | `dfef0092ddb980cef87bd6130f6dc25911d83615611e5887b6be143bdb1d3671` |
| [app/src/main/java/com/desaysv/ivi/vdb/event/id/carinfo/VDEventCarInfo.java](../../app/src/main/java/com/desaysv/ivi/vdb/event/id/carinfo/VDEventCarInfo.java) | 32 | `477c76ed2a16c2a3b1c7360ee4fb588bb3b4927a35e5805709a6b97201caaede` |
| [app/src/main/java/com/desaysv/ivi/vdb/event/id/carlan/bean/VDARDisplayInfoResponse.java](../../app/src/main/java/com/desaysv/ivi/vdb/event/id/carlan/bean/VDARDisplayInfoResponse.java) | 66 | `448cbca0c6ae9915c6e9837610642b6bff8c05b3ceb0c78c6009359a983af2f2` |
| [app/src/main/java/com/desaysv/ivi/vdb/event/id/carlan/bean/VDARSettingsInfo.java](../../app/src/main/java/com/desaysv/ivi/vdb/event/id/carlan/bean/VDARSettingsInfo.java) | 66 | `fcbfdda1b58b6d46a288d4628a070db9e539844d783424045ad4805ac542b801` |
| [app/src/main/java/com/desaysv/ivi/vdb/event/id/carlan/bean/VDMusicID3Info.java](../../app/src/main/java/com/desaysv/ivi/vdb/event/id/carlan/bean/VDMusicID3Info.java) | 155 | `73964f200722ab73d01b7174c9efe571c8150d6557e98a5af93472ff29247382` |
| [app/src/main/java/com/desaysv/ivi/vdb/event/id/carlan/bean/VDMusicPlayMode.java](../../app/src/main/java/com/desaysv/ivi/vdb/event/id/carlan/bean/VDMusicPlayMode.java) | 67 | `7d2634a0311b88b00cd4de41b35f95a9eeace270c8da7c5c67f91cf31ea32e30` |
| [app/src/main/java/com/desaysv/ivi/vdb/event/id/carlan/bean/VDMusicPlayStatus.java](../../app/src/main/java/com/desaysv/ivi/vdb/event/id/carlan/bean/VDMusicPlayStatus.java) | 67 | `c2c005bd3a83c667a6d3ccd3ea6d9dc902a8a0dcfdeae071ac92e8e9f4bf7be4` |
| [app/src/main/java/com/desaysv/ivi/vdb/event/id/carlan/bean/VDMusicPlayTime.java](../../app/src/main/java/com/desaysv/ivi/vdb/event/id/carlan/bean/VDMusicPlayTime.java) | 78 | `c91e4b45df75e25ddb816b135042ba340d657f85f5524c04cc1178e1af643970` |
| [app/src/main/java/com/desaysv/ivi/vdb/event/id/carlan/bean/VDNaviAreaDisplay.java](../../app/src/main/java/com/desaysv/ivi/vdb/event/id/carlan/bean/VDNaviAreaDisplay.java) | 75 | `e6fd3ff721e39f128d4661692e4e97aeee68c17b6bc717157cd36ead492feb2b` |
| [app/src/main/java/com/desaysv/ivi/vdb/event/id/carlan/bean/VDNaviCompass.java](../../app/src/main/java/com/desaysv/ivi/vdb/event/id/carlan/bean/VDNaviCompass.java) | 73 | `955077d1e684aecc4f02e6ba303b2d47d72ce48a805ec5537b98ed9b752b32a2` |
| [app/src/main/java/com/desaysv/ivi/vdb/event/id/carlan/bean/VDNaviDigitalInfo.java](../../app/src/main/java/com/desaysv/ivi/vdb/event/id/carlan/bean/VDNaviDigitalInfo.java) | 106 | `c1fde79e0b7e328980acf8821b4930a80407c5292ed6d77fd8b544c96e057c11` |
| [app/src/main/java/com/desaysv/ivi/vdb/event/id/carlan/bean/VDNaviDisplayArea.java](../../app/src/main/java/com/desaysv/ivi/vdb/event/id/carlan/bean/VDNaviDisplayArea.java) | 84 | `dd8eaae6136ed163904005c0d2f4c911b9557759c47e0849c5984ef19f5bd2fb` |
| [app/src/main/java/com/desaysv/ivi/vdb/event/id/carlan/bean/VDNaviDisplayCluster.java](../../app/src/main/java/com/desaysv/ivi/vdb/event/id/carlan/bean/VDNaviDisplayCluster.java) | 111 | `b8d3d89c1b2593635365d3c5d0c1aafa986b5e9a9052d53aab863eb4417f5ef1` |
| [app/src/main/java/com/desaysv/ivi/vdb/event/id/carlan/bean/VDNaviDisplayImage.kt](../../app/src/main/java/com/desaysv/ivi/vdb/event/id/carlan/bean/VDNaviDisplayImage.kt) | 138 | `258a3bdf3179f08b5d02276c18f74e85625cd914c59d997d242e06d27a250f5b` |
| [app/src/main/java/com/desaysv/ivi/vdb/event/id/carlan/bean/VDNaviLaneInfo.java](../../app/src/main/java/com/desaysv/ivi/vdb/event/id/carlan/bean/VDNaviLaneInfo.java) | 142 | `37632a1e7930dd137880ff06ff1d94fa18c03aed22a46f55a53541f4953659fc` |
| [app/src/main/java/com/desaysv/ivi/vdb/event/id/carlan/bean/VDNaviPerspective.java](../../app/src/main/java/com/desaysv/ivi/vdb/event/id/carlan/bean/VDNaviPerspective.java) | 75 | `1819877fbdec9f47614374d68028f245cbda18d6c5eee69bb7741e1d947438c4` |
| [app/src/main/java/com/desaysv/ivi/vdb/event/id/carlan/bean/VDNaviRoadInfo.java](../../app/src/main/java/com/desaysv/ivi/vdb/event/id/carlan/bean/VDNaviRoadInfo.java) | 150 | `2304b73a309e5146d04ea7f7adc1dac025c9630052e61a2aa567254856f824cd` |
| [app/src/main/java/com/desaysv/ivi/vdb/event/id/carlan/bean/VDNaviRoadInfo2.java](../../app/src/main/java/com/desaysv/ivi/vdb/event/id/carlan/bean/VDNaviRoadInfo2.java) | 161 | `df9549024dc942a1f6f79f5d89df3dbfc5e80b3c0ebd8922c3c1b08868fabb2d` |
| [app/src/main/java/com/desaysv/ivi/vdb/event/id/carlan/bean/VDNaviTotalInfo.java](../../app/src/main/java/com/desaysv/ivi/vdb/event/id/carlan/bean/VDNaviTotalInfo.java) | 130 | `3b8786847ba684a524e81eb2783ad3e76c102f4be589811c80158fdecf38d51b` |
| [app/src/main/java/com/desaysv/ivi/vdb/event/id/carlan/bean/VDNaviTotalInfo2.java](../../app/src/main/java/com/desaysv/ivi/vdb/event/id/carlan/bean/VDNaviTotalInfo2.java) | 128 | `a59d1be457161adc05b5a413a7f1da495570f335b992d881c759c6aebb154149` |
| [app/src/main/java/com/desaysv/ivi/vdb/event/id/carlan/VDEventCarLan.java](../../app/src/main/java/com/desaysv/ivi/vdb/event/id/carlan/VDEventCarLan.java) | 146 | `42fc81cbcef45846f5df13de15d2a0cb8bee7e5ed9c3fc61d9a74713883d6364` |
| [app/src/main/java/com/desaysv/ivi/vdb/event/id/carlan/VDValueCarLan.java](../../app/src/main/java/com/desaysv/ivi/vdb/event/id/carlan/VDValueCarLan.java) | 1 | `a45f955afdc6fc60f161be4b3cfd1da8632527bf9cec367b2ba3da6122313429` |
| [app/src/main/java/com/desaysv/ivi/vdb/event/id/carstate/VDEventCarState.java](../../app/src/main/java/com/desaysv/ivi/vdb/event/id/carstate/VDEventCarState.java) | 24 | `44354f0664a365ae63c1243f1eecd2df830f2343871ddff2b7e43c6357d617d9` |
| [app/src/main/java/com/desaysv/ivi/vdb/event/id/device/VDEventVehicleDevice.java](../../app/src/main/java/com/desaysv/ivi/vdb/event/id/device/VDEventVehicleDevice.java) | 40 | `33b60d152bc75620ac65e2b4cc15cbdd77e9ec3940fc50871fae94a53f59d47e` |
| [app/src/main/java/com/desaysv/ivi/vdb/event/id/dsp/VDEventDsp.java](../../app/src/main/java/com/desaysv/ivi/vdb/event/id/dsp/VDEventDsp.java) | 35 | `375dc10039c427ca0de181a1ca778d6e814d93e304c651022e0a236821eb7b3b` |
| [app/src/main/java/com/desaysv/ivi/vdb/event/id/engineering/VDEventEngineering.java](../../app/src/main/java/com/desaysv/ivi/vdb/event/id/engineering/VDEventEngineering.java) | 8 | `c718ef337be4ac68ce211608079187e681c0c979b0a256dc02ef52f89de652ce` |
| [app/src/main/java/com/desaysv/ivi/vdb/event/id/log/VDEventLog.java](../../app/src/main/java/com/desaysv/ivi/vdb/event/id/log/VDEventLog.java) | 15 | `6495cf4d1e626a71422203eb5af90c6479263299b76c7d14b6f7940b76ab09b0` |
| [app/src/main/java/com/desaysv/ivi/vdb/event/id/media/VDEventMedia.java](../../app/src/main/java/com/desaysv/ivi/vdb/event/id/media/VDEventMedia.java) | 21 | `b7dc0417f9ebcb61959bbb19491a0160fdc794c9a28478b0800fde683af845f5` |
| [app/src/main/java/com/desaysv/ivi/vdb/event/id/media/VDMediaType.java](../../app/src/main/java/com/desaysv/ivi/vdb/event/id/media/VDMediaType.java) | 71 | `896141d090a0cecbe5de9490bf1134c81bbeec79f54a1972e7fdce35a92efb79` |
| [app/src/main/java/com/desaysv/ivi/vdb/event/id/navi/VDEventNavi.java](../../app/src/main/java/com/desaysv/ivi/vdb/event/id/navi/VDEventNavi.java) | 29 | `e3bdf53583e00c2cadc7586e21d74d8e35ba5fed1ccf6249cd6360ca81f36951` |
| [app/src/main/java/com/desaysv/ivi/vdb/event/id/permission/VDEventPermission.java](../../app/src/main/java/com/desaysv/ivi/vdb/event/id/permission/VDEventPermission.java) | 11 | `2e4a31fbccfb76f5aa3613c0be60242e4909809e974af3cc5332374ed43d4081` |
| [app/src/main/java/com/desaysv/ivi/vdb/event/id/phonelink/VDEventPhoneLink.java](../../app/src/main/java/com/desaysv/ivi/vdb/event/id/phonelink/VDEventPhoneLink.java) | 15 | `9919edbfe883b63ce9e048fe185c4e75a19a29edcfbe727b9210564ad8d55172` |
| [app/src/main/java/com/desaysv/ivi/vdb/event/id/projection/VDEventProjection.java](../../app/src/main/java/com/desaysv/ivi/vdb/event/id/projection/VDEventProjection.java) | 16 | `ab673b4d71538508ff1f0ebb51c3340377bb89e6ec522d94b7d7c3e8f1ee895a` |
| [app/src/main/java/com/desaysv/ivi/vdb/event/id/rvc/VDEventRvc.java](../../app/src/main/java/com/desaysv/ivi/vdb/event/id/rvc/VDEventRvc.java) | 23 | `aac4f532996243d60e01144569646006c83740d23163bd820e79f891c5f7a41b` |
| [app/src/main/java/com/desaysv/ivi/vdb/event/id/setting/VDEventSetting.java](../../app/src/main/java/com/desaysv/ivi/vdb/event/id/setting/VDEventSetting.java) | 11 | `1f0aa33b583a9db09bf4bb492a913a36bf5e461e92d338285caf1c8ac5fe5ec2` |
| [app/src/main/java/com/desaysv/ivi/vdb/event/id/sms/VDEventSms.java](../../app/src/main/java/com/desaysv/ivi/vdb/event/id/sms/VDEventSms.java) | 12 | `6917a9c8bccd2d9fb2b4668910fe9d1af99cedb531eff611c933257624c42040` |
| [app/src/main/java/com/desaysv/ivi/vdb/event/id/sms/VDValueAction.java](../../app/src/main/java/com/desaysv/ivi/vdb/event/id/sms/VDValueAction.java) | 11 | `f0cb96ef03e587e0b2709c13764c6ea67cf353cbaa87f8b6a5ca172af7b4343f` |
| [app/src/main/java/com/desaysv/ivi/vdb/event/id/sms/VDValueKey.java](../../app/src/main/java/com/desaysv/ivi/vdb/event/id/sms/VDValueKey.java) | 43 | `f009716b1f138774bc64bd6b7e6b9cfb5a874d33dcb81a123de813e204b10a1a` |
| [app/src/main/java/com/desaysv/ivi/vdb/event/id/tsp/VDEventTsp.java](../../app/src/main/java/com/desaysv/ivi/vdb/event/id/tsp/VDEventTsp.java) | 35 | `03378169519bac87e8d0606e5f45ae9ffb997266939271c69e58b8903fdbc55f` |
| [app/src/main/java/com/desaysv/ivi/vdb/event/id/tuner/VDEventTuner.java](../../app/src/main/java/com/desaysv/ivi/vdb/event/id/tuner/VDEventTuner.java) | 36 | `a264a699ab125c570ed2cff0439b88ae8f5308755f833d29fe25ee48439a4750` |
| [app/src/main/java/com/desaysv/ivi/vdb/event/id/upgrade/VDEventUpgrade.java](../../app/src/main/java/com/desaysv/ivi/vdb/event/id/upgrade/VDEventUpgrade.java) | 15 | `625b9ec690f550670b60b362d54bf210d3b90cea669480c3d9a0d01638ca10c6` |
| [app/src/main/java/com/desaysv/ivi/vdb/event/id/vehicle/VDEventVehicleHal.java](../../app/src/main/java/com/desaysv/ivi/vdb/event/id/vehicle/VDEventVehicleHal.java) | 319 | `e2eaa9dff6ffec7d723ac22d4f8fbba1b6cc3ffa4cedc575a1069101b102d026` |
| [app/src/main/java/com/desaysv/ivi/vdb/event/id/vehicle/VDKeyVehicleHal.java](../../app/src/main/java/com/desaysv/ivi/vdb/event/id/vehicle/VDKeyVehicleHal.java) | 11 | `9ba623196b19d930b1d09203e30f9762abff45051284f0624278e77dc53c6ee8` |
| [app/src/main/java/com/desaysv/ivi/vdb/event/id/vr/VDEventVR.java](../../app/src/main/java/com/desaysv/ivi/vdb/event/id/vr/VDEventVR.java) | 40 | `0faed24358064a4e51b723aa22664f332efac8484d87d80c23da6cdacb546ec1` |
| [app/src/main/java/com/desaysv/ivi/vdb/event/id/watchdog/VDEventWatchdog.java](../../app/src/main/java/com/desaysv/ivi/vdb/event/id/watchdog/VDEventWatchdog.java) | 8 | `df7085105bdc490f8153e75a38afdc58bbb202e3eb3f429e419cd037931192ef` |
| [app/src/main/java/com/desaysv/ivi/vdb/event/VDEvent.java](../../app/src/main/java/com/desaysv/ivi/vdb/event/VDEvent.java) | 97 | `9e518e76f4bacfeaa44eb9da6d02635e746125a64e9db32e03d847072054d5f6` |
| [app/src/main/java/com/desaysv/ivi/vdb/IVDBus.java](../../app/src/main/java/com/desaysv/ivi/vdb/IVDBus.java) | 260 | `7290c807d79ac188afebadd76bf0f0ff1b9bc9c8b6830676b311a8d10e4136db` |
| [app/src/main/java/com/desaysv/ivi/vdb/IVDBusCallback.java](../../app/src/main/java/com/desaysv/ivi/vdb/IVDBusCallback.java) | 128 | `36a566fc00f8512fcc009962bc2574537705876df8c704595f1f2d3f128e641d` |
| [app/src/main/java/com/desaysv/ivi/vdb/IVDBusNotify.java](../../app/src/main/java/com/desaysv/ivi/vdb/IVDBusNotify.java) | 98 | `04ccfb9ea325413530b175e601b428914b3ca40f3f6c7f7188ddbaa8d0c6712a` |
| [app/src/main/java/com/desaysv/ivi/vdb/utils/VDConfigUtil.java](../../app/src/main/java/com/desaysv/ivi/vdb/utils/VDConfigUtil.java) | 6 | `0c08473763e98e8be5a4186085ed37e5b63c8dd25bc485b682046e8a70164bea` |
| [app/src/main/java/com/desaysv/ivi/vdb/utils/VDServiceUtil.java](../../app/src/main/java/com/desaysv/ivi/vdb/utils/VDServiceUtil.java) | 77 | `7386051b80cc937155708d796b65bfc00a4702a0c6453318abce0fab0388da6c` |
| [app/src/main/java/com/desaysv/model/display/utils/VehicleHalUtil.java](../../app/src/main/java/com/desaysv/model/display/utils/VehicleHalUtil.java) | 38 | `adbcf28512ae306000cc7ed6f659f4b8ab73567004edafb63f5eb4546acf0634` |
| [app/src/main/java/com/desaysv/platform/VDBusManager.java](../../app/src/main/java/com/desaysv/platform/VDBusManager.java) | 13 | `e6cd96081cd0ee749638694d17c371557b4f1b78e594c23081b16a508dec4b93` |
| [app/src/main/java/com/mapcontrol/admin/MapControlDeviceAdminReceiver.java](../../app/src/main/java/com/mapcontrol/admin/MapControlDeviceAdminReceiver.java) | 10 | `db770da1c27b0abbc1b9f523e864db52fc900a133b64135e08b7d4d21dd247ca` |
| [app/src/main/java/com/mapcontrol/api/ProfileApiService.java](../../app/src/main/java/com/mapcontrol/api/ProfileApiService.java) | 389 | `9101f6d76fc703857172371a5df757c72229d71496603fabb9c1a3ae1e3dbb69` |
| [app/src/main/java/com/mapcontrol/audio/AudioEqualizerController.java](../../app/src/main/java/com/mapcontrol/audio/AudioEqualizerController.java) | 507 | `b83bd21b1520a1d1c189b932fa6d74c870c868ec0bd04efd076143f0f9fc9beb` |
| [app/src/main/java/com/mapcontrol/bean/CameraBean.java](../../app/src/main/java/com/mapcontrol/bean/CameraBean.java) | 148 | `eb92472d930e6321c2206bf0cd51bbd1d6b2133a0a5ebba3f79f2aed11849e92` |
| [app/src/main/java/com/mapcontrol/GlitchTip.java](../../app/src/main/java/com/mapcontrol/GlitchTip.java) | 195 | `16d76b4bf5c3ff6bbf91501bb218400f497b796741e3be83d7102bc18bc1ee02` |
| [app/src/main/java/com/mapcontrol/manager/CarSystemGestureController.java](../../app/src/main/java/com/mapcontrol/manager/CarSystemGestureController.java) | 315 | `9ad9b5cafc2f7db673505c86afa5c9cd7d0c7de705f727abf85827ead9a8c731` |
| [app/src/main/java/com/mapcontrol/manager/ClusterDisplayManager.java](../../app/src/main/java/com/mapcontrol/manager/ClusterDisplayManager.java) | 315 | `3123e0dfadf3eaf5650aa93c7060af5fe3d3eea616df89fcfbfc5daf62978cf3` |
| [app/src/main/java/com/mapcontrol/manager/ClusterVDBusBenchManager.java](../../app/src/main/java/com/mapcontrol/manager/ClusterVDBusBenchManager.java) | 77 | `3208b0b86bc899095fd62bf74d75baf7aa96f02ce4629cad0223aab16aca75e6` |
| [app/src/main/java/com/mapcontrol/manager/DisplayRunningAppsOverlay.java](../../app/src/main/java/com/mapcontrol/manager/DisplayRunningAppsOverlay.java) | 388 | `837b727bd29e29327a26769d7cbb32f9d0cc53bba174a8410625c1e5abec82bf` |
| [app/src/main/java/com/mapcontrol/manager/FloatingBackButtonManager.java](../../app/src/main/java/com/mapcontrol/manager/FloatingBackButtonManager.java) | 978 | `4b10d5f3151f9bf2d439ac52e940b4d300ea9c10aae2748c128caed101eb2ef5` |
| [app/src/main/java/com/mapcontrol/manager/FloatingOverlayBarSpec.java](../../app/src/main/java/com/mapcontrol/manager/FloatingOverlayBarSpec.java) | 154 | `699cae08736d1bbc2111064cf9b5ae8fc044c19005e4536981d1279403d41d1b` |
| [app/src/main/java/com/mapcontrol/manager/FloatingProjectionControlsManager.java](../../app/src/main/java/com/mapcontrol/manager/FloatingProjectionControlsManager.java) | 313 | `cb498d41adba0484d4127a43850c75f173570295ef4a3e73d2e9c0a3a96a9b70` |
| [app/src/main/java/com/mapcontrol/manager/FloatingQuickActionsManager.java](../../app/src/main/java/com/mapcontrol/manager/FloatingQuickActionsManager.java) | 396 | `55addf4fbeef9959362b77d274820223dd4b8430f54fc78501925af808a5edba` |
| [app/src/main/java/com/mapcontrol/manager/MapControlVDBusKeyBridge.java](../../app/src/main/java/com/mapcontrol/manager/MapControlVDBusKeyBridge.java) | 160 | `57578d152a8094cb498696fd12d93f5b5f2c5c72ecc13e94f9275e33a81ae273` |
| [app/src/main/java/com/mapcontrol/manager/ProjectionVDBusTargetPickerManager.java](../../app/src/main/java/com/mapcontrol/manager/ProjectionVDBusTargetPickerManager.java) | 627 | `9449adff379cbd3c3f069474f83b8ebb87f6592b7e9d6c15124c780bced25e59` |
| [app/src/main/java/com/mapcontrol/manager/SportAnimationController.java](../../app/src/main/java/com/mapcontrol/manager/SportAnimationController.java) | 151 | `6fe2068251da2bb36a4715eeb2a9ad8df993492b0139d72f84cc38a61117977e` |
| [app/src/main/java/com/mapcontrol/manager/TargetAppPickerOverlay.java](../../app/src/main/java/com/mapcontrol/manager/TargetAppPickerOverlay.java) | 358 | `43260e182a62498d93e2642a942aa75230aa563726cd1b1f55fb51352fd65b76` |
| [app/src/main/java/com/mapcontrol/manager/VDBusManager.java](../../app/src/main/java/com/mapcontrol/manager/VDBusManager.java) | 247 | `5d8564ca48e087eebc49687c9ddfbc09120af4fa3ab2861881ab4577c845cd40` |
| [app/src/main/java/com/mapcontrol/manager/WebServerManager.java](../../app/src/main/java/com/mapcontrol/manager/WebServerManager.java) | 1349 | `d8b353811113195f2a3a33fc4281ea6c2ba200ba52e707a5cf10d001278dc5e5` |
| [app/src/main/java/com/mapcontrol/MapControlApplication.java](../../app/src/main/java/com/mapcontrol/MapControlApplication.java) | 46 | `7707b5701549966c8cca1a2a8f1a81a3c330ee62c8d070dcea172fb58e5dda88` |
| [app/src/main/java/com/mapcontrol/media/LauncherMediaController.java](../../app/src/main/java/com/mapcontrol/media/LauncherMediaController.java) | 440 | `fc637271602207186165139b40d0b4918e3a35f5e53d98219c15d284fd04420c` |
| [app/src/main/java/com/mapcontrol/media/LauncherMediaNotificationListener.java](../../app/src/main/java/com/mapcontrol/media/LauncherMediaNotificationListener.java) | 48 | `1f1bff4861ba01c013f6f4ba02bec57560972a3b2d4a49f879c655ececcac36f` |
| [app/src/main/java/com/mapcontrol/nav/ClusterNavOverlayOffsets.java](../../app/src/main/java/com/mapcontrol/nav/ClusterNavOverlayOffsets.java) | 277 | `1335c3a267116bae6365f8d0a3442e4135c8324fa7aa4d2e1c40cb70e0bfc183` |
| [app/src/main/java/com/mapcontrol/nav/GoogleMapsClusterNavOverlay.java](../../app/src/main/java/com/mapcontrol/nav/GoogleMapsClusterNavOverlay.java) | 560 | `02335618fcf77b761081d6a48dfd8899467bdd5e3b607dee2a38ef44bfc5e0cf` |
| [app/src/main/java/com/mapcontrol/nav/GoogleMapsNavNotificationCoordinator.java](../../app/src/main/java/com/mapcontrol/nav/GoogleMapsNavNotificationCoordinator.java) | 364 | `3e5703d1ae46d4228dfda93385c1cb70f481272e272e61e2cf22bdbeb963b257` |
| [app/src/main/java/com/mapcontrol/nav/GoogleMapsNavNotificationParser.java](../../app/src/main/java/com/mapcontrol/nav/GoogleMapsNavNotificationParser.java) | 126 | `595f2857a904286aa8d204b4235f002b7288fa235f4706f5484c0c688ef9c499` |
| [app/src/main/java/com/mapcontrol/nav/GoogleMapsNavSnapshot.java](../../app/src/main/java/com/mapcontrol/nav/GoogleMapsNavSnapshot.java) | 188 | `01d7878c62ae4f69c23b63b3671e94cafc79869fa2e0b33cef3414fdc10af202` |
| [app/src/main/java/com/mapcontrol/nav/NavigationQuantities.java](../../app/src/main/java/com/mapcontrol/nav/NavigationQuantities.java) | 97 | `3ab8ee07af72c8395aaec98f990db596c39ef7041aaa978183ba960317a9d5d2` |
| [app/src/main/java/com/mapcontrol/nav/YandexClusterNavCoordinator.java](../../app/src/main/java/com/mapcontrol/nav/YandexClusterNavCoordinator.java) | 98 | `7ccf09c45dba0237c2a2a26730c06fe80abb984ff13f72fb200b1c7b30a0e70d` |
| [app/src/main/java/com/mapcontrol/nav/YandexClusterNavOverlay.java](../../app/src/main/java/com/mapcontrol/nav/YandexClusterNavOverlay.java) | 530 | `fe1e7bfa68405b296e0924355f360e992f062ecbe0f23b79fd5343037b674c00` |
| [app/src/main/java/com/mapcontrol/nav/YandexMapsPresence.java](../../app/src/main/java/com/mapcontrol/nav/YandexMapsPresence.java) | 89 | `1b85ad3253b1f32473e5cc80cb54d9ab61659b4e04c7f50690e57889a6ada444` |
| [app/src/main/java/com/mapcontrol/nav/YandexNavScraper.java](../../app/src/main/java/com/mapcontrol/nav/YandexNavScraper.java) | 219 | `4079dd58529c7d8bce13a8297b5b1339caa1dd13370c1be83269694df3528a3e` |
| [app/src/main/java/com/mapcontrol/nav/YandexNavSnapshot.java](../../app/src/main/java/com/mapcontrol/nav/YandexNavSnapshot.java) | 103 | `907a8344417997effdcbb026a00e5a8e822fdf04bc7cbde77e4629b01078f189` |
| [app/src/main/java/com/mapcontrol/service/BootReceiver.java](../../app/src/main/java/com/mapcontrol/service/BootReceiver.java) | 57 | `eadfcc890c0ee5c7e9896cf725588502eca426d73142f8488980a2b3676b7232` |
| [app/src/main/java/com/mapcontrol/service/DmsFaceRecognitionManager.java](../../app/src/main/java/com/mapcontrol/service/DmsFaceRecognitionManager.java) | 640 | `697a10d223a159b564e74aab38fad5c8daa748ca7c166902fb8576024c45d2f2` |
| [app/src/main/java/com/mapcontrol/service/GlobalBackService.java](../../app/src/main/java/com/mapcontrol/service/GlobalBackService.java) | 439 | `1198c22b8b34b61a0696252957042be272c8dce2c98a351e9dfddc4cc595a4db` |
| [app/src/main/java/com/mapcontrol/service/MapControlService.java](../../app/src/main/java/com/mapcontrol/service/MapControlService.java) | 1211 | `ff9881ff2e341a36a77d8abb406845916ed087a9b984f0ed57e5d257d71b4a3b` |
| [app/src/main/java/com/mapcontrol/service/ServiceInitializer.java](../../app/src/main/java/com/mapcontrol/service/ServiceInitializer.java) | 75 | `074cf7130150d8b8a6616f70600c009a8441b45632c4ba56646ae75d3ce9dd73` |
| [app/src/main/java/com/mapcontrol/ui/activity/CameraActivity.java](../../app/src/main/java/com/mapcontrol/ui/activity/CameraActivity.java) | 475 | `0ea8b04bb89549caa4b519a160d9111a563891393d86c0a0cfbab7b3b051c410` |
| [app/src/main/java/com/mapcontrol/ui/activity/ClusterVDBusTestActivity.java](../../app/src/main/java/com/mapcontrol/ui/activity/ClusterVDBusTestActivity.java) | 363 | `0ba072629943ba0d281229950ef100af52e67f4bf3e88b879fb48fe550d793b5` |
| [app/src/main/java/com/mapcontrol/ui/activity/DmsClusterPreview.java](../../app/src/main/java/com/mapcontrol/ui/activity/DmsClusterPreview.java) | 182 | `94173fdc9107f333db1e4b9ed41a648c6a16d4816a698c0f3a1ac6bbd6d267ca` |
| [app/src/main/java/com/mapcontrol/ui/activity/DmsFaceProfileActivity.java](../../app/src/main/java/com/mapcontrol/ui/activity/DmsFaceProfileActivity.java) | 1312 | `551c4be17531c93cface3ff91da32f1f7821edeb58a6bb8917acfade5d0d7bf2` |
| [app/src/main/java/com/mapcontrol/ui/activity/MainActivity.java](../../app/src/main/java/com/mapcontrol/ui/activity/MainActivity.java) | 2366 | `be620e57147d952044201c6ad9aa353537b6542427a17ced4870e9541b1c9992` |
| [app/src/main/java/com/mapcontrol/ui/builder/AppsTabBuilder.java](../../app/src/main/java/com/mapcontrol/ui/builder/AppsTabBuilder.java) | 2206 | `9a2f2f49987e1d51ffef3b5a74adf7594c9f8e2bf4fb844660bbd628edbf614f` |
| [app/src/main/java/com/mapcontrol/ui/builder/AssistTabBuilder.java](../../app/src/main/java/com/mapcontrol/ui/builder/AssistTabBuilder.java) | 366 | `43aa8fd133fbd7fa3a6af94fbfb0b69260753024724e8772abbb9961ea1d480e` |
| [app/src/main/java/com/mapcontrol/ui/builder/ClimateAutomationTabBuilder.java](../../app/src/main/java/com/mapcontrol/ui/builder/ClimateAutomationTabBuilder.java) | 840 | `9e3c5c86c222ae1cbb36d67c0d9bc8003f63d8f39fcd46bf6f189eeac24e1d6c` |
| [app/src/main/java/com/mapcontrol/ui/builder/DmsFaceRecognitionTabBuilder.java](../../app/src/main/java/com/mapcontrol/ui/builder/DmsFaceRecognitionTabBuilder.java) | 323 | `a1b1a1020780f919c5abfb9a5483dde8283a0d6884e7024b006d89280a39e4ce` |
| [app/src/main/java/com/mapcontrol/ui/builder/DriveModeTabBuilder.java](../../app/src/main/java/com/mapcontrol/ui/builder/DriveModeTabBuilder.java) | 552 | `2a47907871578dd5c36538419b141ff0e95d4f7191bb1bb48da1f0b58b974b5e` |
| [app/src/main/java/com/mapcontrol/ui/builder/EqualizerTabBuilder.java](../../app/src/main/java/com/mapcontrol/ui/builder/EqualizerTabBuilder.java) | 504 | `9dec6a85bba349ff24342f5401eb65eb2a82d680835f17e54dc3b4261940117e` |
| [app/src/main/java/com/mapcontrol/ui/builder/FileUploadTabBuilder.java](../../app/src/main/java/com/mapcontrol/ui/builder/FileUploadTabBuilder.java) | 213 | `11a45c96fd94735710bf41f4351ff4c712665d0586980a2070c903a1bdd361bf` |
| [app/src/main/java/com/mapcontrol/ui/builder/LauncherDashboardBuilder.java](../../app/src/main/java/com/mapcontrol/ui/builder/LauncherDashboardBuilder.java) | 2050 | `40ac432b16ce74b01f9516cb0f720c564fdc0297c7e3fd13049fc5c0c28c18e7` |
| [app/src/main/java/com/mapcontrol/ui/builder/LauncherTabBuilder.java](../../app/src/main/java/com/mapcontrol/ui/builder/LauncherTabBuilder.java) | 92 | `c1ec5b81d3dcbaaf419fd7070dec95ebf61a0657f9e361e40ed65578e235b5cc` |
| [app/src/main/java/com/mapcontrol/ui/builder/LogTabBuilder.java](../../app/src/main/java/com/mapcontrol/ui/builder/LogTabBuilder.java) | 352 | `a2808a09eb586e14a460c21c3eb2afd97515930af756b738848f7e13f773a126` |
| [app/src/main/java/com/mapcontrol/ui/builder/ProfileTabBuilder.java](../../app/src/main/java/com/mapcontrol/ui/builder/ProfileTabBuilder.java) | 971 | `1f74852506dcb438f8665fbeff7563e9d6b86c32df0fbcb8f817537a698a36fd` |
| [app/src/main/java/com/mapcontrol/ui/builder/ProjectionTabBuilder.java](../../app/src/main/java/com/mapcontrol/ui/builder/ProjectionTabBuilder.java) | 969 | `273d7946cca67fc18329e5f66827cac3d6d72f34a824b52246ae1e5f5246c6ea` |
| [app/src/main/java/com/mapcontrol/ui/builder/SettingsTabBuilder.java](../../app/src/main/java/com/mapcontrol/ui/builder/SettingsTabBuilder.java) | 657 | `931ae8d0e0d3e75412cf86814fdd4bb61b3896c6d96a62a9c8f558ded9963c81` |
| [app/src/main/java/com/mapcontrol/ui/builder/SideRailBuilder.java](../../app/src/main/java/com/mapcontrol/ui/builder/SideRailBuilder.java) | 579 | `fa2aa0829c2b3a1a12eff74b6ec547bff725ee99a75825d2bbf2e8df40e34b91` |
| [app/src/main/java/com/mapcontrol/ui/builder/TopBarBuilder.java](../../app/src/main/java/com/mapcontrol/ui/builder/TopBarBuilder.java) | 376 | `101417a2617e37b7cfd9e7ee31ca515a358783669712da3c663891f778d380e1` |
| [app/src/main/java/com/mapcontrol/ui/builder/VehicleInfoTabBuilder.java](../../app/src/main/java/com/mapcontrol/ui/builder/VehicleInfoTabBuilder.java) | 391 | `ce333c1e7225c6ecfb2a2499726f1beb4a613d70f7ab8e7755eadda26de570ed` |
| [app/src/main/java/com/mapcontrol/ui/builder/WelcomeSoundScreenBuilder.java](../../app/src/main/java/com/mapcontrol/ui/builder/WelcomeSoundScreenBuilder.java) | 319 | `ba8f8a1b40925fdc73920a8e6580f9d09aab7a91b35f1bd16555d1ea6eac2703` |
| [app/src/main/java/com/mapcontrol/ui/builder/WelcomeSoundTabBuilder.java](../../app/src/main/java/com/mapcontrol/ui/builder/WelcomeSoundTabBuilder.java) | 384 | `7065ce9bac2621739e30f3c1be7ea06f3d13e70008d416ec468ccdba1e6ccf27` |
| [app/src/main/java/com/mapcontrol/ui/builder/WifiTabBuilder.java](../../app/src/main/java/com/mapcontrol/ui/builder/WifiTabBuilder.java) | 724 | `0f32c422e62419bc3dd7709189f7fb4cb8e6d5f1d5c1c063f637a59a33e329ad` |
| [app/src/main/java/com/mapcontrol/ui/HeaderStatusMonitor.java](../../app/src/main/java/com/mapcontrol/ui/HeaderStatusMonitor.java) | 151 | `11b2687f6464d4675a14a1c2fc7697fbabc880cf142c590b1758a723c46037df` |
| [app/src/main/java/com/mapcontrol/ui/HeaderStatusSink.java](../../app/src/main/java/com/mapcontrol/ui/HeaderStatusSink.java) | 14 | `4f9afc586ec5bb629d219dd635abe911e7d1f15d095bb893b3e9148cae7a12f2` |
| [app/src/main/java/com/mapcontrol/ui/theme/FeatureGridLayout.java](../../app/src/main/java/com/mapcontrol/ui/theme/FeatureGridLayout.java) | 60 | `0f6c67f2606f4b334ba552eeafc5c5131dfe66f50ef300415256a594643a6d93` |
| [app/src/main/java/com/mapcontrol/ui/theme/UiStyles.java](../../app/src/main/java/com/mapcontrol/ui/theme/UiStyles.java) | 662 | `89322b91431fe971941f583436ed76acbba3b5d168bcc1c5553aea93acb1c796` |
| [app/src/main/java/com/mapcontrol/ui/welcome/MountainHikeScene.java](../../app/src/main/java/com/mapcontrol/ui/welcome/MountainHikeScene.java) | 287 | `3053b7830f445efbeaae162924236db731b49cd8628151839537461c253c7620` |
| [app/src/main/java/com/mapcontrol/ui/welcome/MountainHikeWelcomeDesign.java](../../app/src/main/java/com/mapcontrol/ui/welcome/MountainHikeWelcomeDesign.java) | 250 | `c7cd11944ea92e3d517b5fbfb1ba5d0fd2ec07b246cc3965ed6b89c9671174b9` |
| [app/src/main/java/com/mapcontrol/ui/welcome/SignatureWelcomeDesign.java](../../app/src/main/java/com/mapcontrol/ui/welcome/SignatureWelcomeDesign.java) | 311 | `7a5598699a2ffad6b2721b8e06f092682132cf59aac00be92ae44774c18f7ce2` |
| [app/src/main/java/com/mapcontrol/ui/welcome/WelcomeAudioFiles.java](../../app/src/main/java/com/mapcontrol/ui/welcome/WelcomeAudioFiles.java) | 331 | `95644aa5aff2104d69ca0fb2d3f4a8f48fcc87cfb9628183f3fd4fe7fb7bd060` |
| [app/src/main/java/com/mapcontrol/ui/welcome/WelcomeDesign.java](../../app/src/main/java/com/mapcontrol/ui/welcome/WelcomeDesign.java) | 28 | `c76e261324e126013a0e6646041ab588b764076a18c3277a2b98925ac06ae1a2` |
| [app/src/main/java/com/mapcontrol/ui/welcome/WelcomeDesignCatalog.java](../../app/src/main/java/com/mapcontrol/ui/welcome/WelcomeDesignCatalog.java) | 94 | `0cf860969c1425e2a93975f0965d9f7010522c1bac9631654aafef79cf002160` |
| [app/src/main/java/com/mapcontrol/ui/welcome/WelcomeScreenPresenter.java](../../app/src/main/java/com/mapcontrol/ui/welcome/WelcomeScreenPresenter.java) | 175 | `7779fe80f551e564fe62c06849548b94d509bc86d4aa5c386071bda9a857099d` |
| [app/src/main/java/com/mapcontrol/ui/welcome/WinterTouchScene.java](../../app/src/main/java/com/mapcontrol/ui/welcome/WinterTouchScene.java) | 331 | `cb3ff9a3029bfc2a3bc46990579305c36f51e90b8554a8b2fb84e9620fb1cb47` |
| [app/src/main/java/com/mapcontrol/ui/welcome/WinterTouchWelcomeDesign.java](../../app/src/main/java/com/mapcontrol/ui/welcome/WinterTouchWelcomeDesign.java) | 255 | `703b9f026683a13efa41a0436a398ee3d036b31241471c89d4663735b4deb631` |
| [app/src/main/java/com/mapcontrol/ui/widget/LauncherClockSwipeLayout.java](../../app/src/main/java/com/mapcontrol/ui/widget/LauncherClockSwipeLayout.java) | 398 | `0462e5aa92e8c07dd7f81d642eed790510ee75b19bd5a19b5ecbfec89f9595e4` |
| [app/src/main/java/com/mapcontrol/ui/widget/LauncherLitePanelView.java](../../app/src/main/java/com/mapcontrol/ui/widget/LauncherLitePanelView.java) | 412 | `cdfd31bf5d96516bd8a1adcf6936a2dd87ba8336258a3f070d8bd7c71bb7fc9b` |
| [app/src/main/java/com/mapcontrol/ui/widget/OemFrameAnimationView.java](../../app/src/main/java/com/mapcontrol/ui/widget/OemFrameAnimationView.java) | 129 | `6d614f550efebd267fb80ed7a8354aa3b4009a75dbabc42d8b02cef141048c43` |
| [app/src/main/java/com/mapcontrol/ui/widget/VehicleModelHostView.java](../../app/src/main/java/com/mapcontrol/ui/widget/VehicleModelHostView.java) | 238 | `6cae0aeb4c4ea312ed8ce9478e0c6a37e41fa4a9f4673bf1bb221377e830848e` |
| [app/src/main/java/com/mapcontrol/util/ActivityDisplayTasksHelper.java](../../app/src/main/java/com/mapcontrol/util/ActivityDisplayTasksHelper.java) | 152 | `0d13697b1080935c97250ef5d78dad123766c58168ee2e321e622ea71dc6eb2f` |
| [app/src/main/java/com/mapcontrol/util/AlertSoundHelper.java](../../app/src/main/java/com/mapcontrol/util/AlertSoundHelper.java) | 106 | `9b3ba31cad82941946209f58e8eca929b3529596f755c4ad7c7096f2f5859251` |
| [app/src/main/java/com/mapcontrol/util/ApkSessionInstaller.java](../../app/src/main/java/com/mapcontrol/util/ApkSessionInstaller.java) | 215 | `7a11baa4dcdd113b197350fab172e62e899427ec96f24466c4e95b417b13fdc6` |
| [app/src/main/java/com/mapcontrol/util/AppForceStopHelper.java](../../app/src/main/java/com/mapcontrol/util/AppForceStopHelper.java) | 94 | `0e5064ba2d60c862c5e3e0a31377662b9c3779722baa0338205ce34106e9c1d9` |
| [app/src/main/java/com/mapcontrol/util/AppIconHelper.java](../../app/src/main/java/com/mapcontrol/util/AppIconHelper.java) | 365 | `d8e8e0aeb1cb4e6794d4d5e23e040e992354b1ca77d497e28b06a9d5cb5b608d` |
| [app/src/main/java/com/mapcontrol/util/AppLaunchHelper.java](../../app/src/main/java/com/mapcontrol/util/AppLaunchHelper.java) | 132 | `2b0821da8b8a7449091b7ee9a15ff1360bc4c123453c21dd89374156eb41423c` |
| [app/src/main/java/com/mapcontrol/util/AppLocaleManager.java](../../app/src/main/java/com/mapcontrol/util/AppLocaleManager.java) | 55 | `061b595447f252f0dc9925b81066c16f8eef623ca5a13024abe18666b4c30248` |
| [app/src/main/java/com/mapcontrol/util/BootSession.java](../../app/src/main/java/com/mapcontrol/util/BootSession.java) | 96 | `4930bfe94752a8920d1fff4165a7963f6464c1f77e4ee9356db2f851556d9656` |
| [app/src/main/java/com/mapcontrol/util/BoundedLogBuffer.java](../../app/src/main/java/com/mapcontrol/util/BoundedLogBuffer.java) | 21 | `8d659df08250d63fbef01aac7cddfe520278703f7c6c052eb8c337a2a5542671` |
| [app/src/main/java/com/mapcontrol/util/ClusterNavigationState.java](../../app/src/main/java/com/mapcontrol/util/ClusterNavigationState.java) | 39 | `9abeebc54c513f980a57e71082bfec2de0c1b7ca9e4e0e5aa1b6ea6fc45e222f` |
| [app/src/main/java/com/mapcontrol/util/DialogHelper.java](../../app/src/main/java/com/mapcontrol/util/DialogHelper.java) | 244 | `0cfe61483ac02d1f29748e2d8cd03c4df54461e79f879c4b847cdfa592fd9b0f` |
| [app/src/main/java/com/mapcontrol/util/DisplayHelper.java](../../app/src/main/java/com/mapcontrol/util/DisplayHelper.java) | 992 | `4f668567dbbeee2efb7fc49363fd703d36951587ef76b84485c721cafef72bfe` |
| [app/src/main/java/com/mapcontrol/util/DisplayTaskParser.java](../../app/src/main/java/com/mapcontrol/util/DisplayTaskParser.java) | 46 | `ed513add4485a622dba6ecb2e2dab2ed0b4684d90980bf476613d487c911e28e` |
| [app/src/main/java/com/mapcontrol/util/GrantedPermissionStatus.java](../../app/src/main/java/com/mapcontrol/util/GrantedPermissionStatus.java) | 159 | `d35750e8d56105a86fa4d23a4cf1c6ff5e1273e87ad2ac5cb706a9076661fc07` |
| [app/src/main/java/com/mapcontrol/util/ImmersiveFullscreenHelper.java](../../app/src/main/java/com/mapcontrol/util/ImmersiveFullscreenHelper.java) | 52 | `067bb9acddf290f36578307b4ae6ba1bb6cb1703833b58d2ee3f5228ea735245` |
| [app/src/main/java/com/mapcontrol/util/InstallerConfirmClicker.java](../../app/src/main/java/com/mapcontrol/util/InstallerConfirmClicker.java) | 613 | `3fad0269c1a0524575d8e420c409b8b2aeb83aeb7a5ec39fa87805a146cb9714` |
| [app/src/main/java/com/mapcontrol/util/LauncherLegacyPrefs.java](../../app/src/main/java/com/mapcontrol/util/LauncherLegacyPrefs.java) | 27 | `389f97c752b8308db2338df8ca2d37863515e74a43b752d4b5b4bd0152d88087` |
| [app/src/main/java/com/mapcontrol/util/LauncherModeManager.java](../../app/src/main/java/com/mapcontrol/util/LauncherModeManager.java) | 151 | `84932524024a83436575ceb9bdbf8e2f9cc275fcfb4113514c1b1823e8273a36` |
| [app/src/main/java/com/mapcontrol/util/LauncherQuickAppsStore.java](../../app/src/main/java/com/mapcontrol/util/LauncherQuickAppsStore.java) | 110 | `b672cf8e2961c6f3bbfd5c08b43eecbc3d0364cffde2cb9a631c80c139535797` |
| [app/src/main/java/com/mapcontrol/util/LauncherSwipeStore.java](../../app/src/main/java/com/mapcontrol/util/LauncherSwipeStore.java) | 95 | `06d7c42908fdbd1395005fafd9f4623833d6a99bc11e3cd99a9c5a36fc799d71` |
| [app/src/main/java/com/mapcontrol/util/MarkdownUtil.java](../../app/src/main/java/com/mapcontrol/util/MarkdownUtil.java) | 51 | `4ff271fc8e69fa602860aa5b323dd3295199842ef367f37cb89add40d2c87900` |
| [app/src/main/java/com/mapcontrol/util/NetworkWifiHelper.java](../../app/src/main/java/com/mapcontrol/util/NetworkWifiHelper.java) | 74 | `214634d367abdfc0336cacf6ecedd475bcfa40e56fafd6faf33fb6120d3c416c` |
| [app/src/main/java/com/mapcontrol/util/OnboardingHelper.java](../../app/src/main/java/com/mapcontrol/util/OnboardingHelper.java) | 166 | `ea973e6b17caf68b8d6e4ca34fc29c3308f37d3a7e330843f4b7dc6ac1e239d4` |
| [app/src/main/java/com/mapcontrol/util/OverlayDismissal.java](../../app/src/main/java/com/mapcontrol/util/OverlayDismissal.java) | 51 | `0b3baf280172bbbe336e33b60cca77b7aebece4d3b52f4847cdcbaf546572050` |
| [app/src/main/java/com/mapcontrol/util/PermissionProbe.java](../../app/src/main/java/com/mapcontrol/util/PermissionProbe.java) | 695 | `4883058e8c4b6e556ee84a1b14a687710791b7e636b517e6ceb1bd4d3afa9f10` |
| [app/src/main/java/com/mapcontrol/util/PermissionProbeDialog.java](../../app/src/main/java/com/mapcontrol/util/PermissionProbeDialog.java) | 176 | `90cca65f1548c100ef26a8e3eee981bf42e91618e6ac99d5ba2fda22fc12c981` |
| [app/src/main/java/com/mapcontrol/util/ProjectionTargetApps.java](../../app/src/main/java/com/mapcontrol/util/ProjectionTargetApps.java) | 175 | `c7b58b7aa6a64478cc3bffe17430924b9bd0d03958a364688c5349122cde148c` |
| [app/src/main/java/com/mapcontrol/util/SilentDexInstaller.java](../../app/src/main/java/com/mapcontrol/util/SilentDexInstaller.java) | 164 | `e31d2ccfb51d1ff89b76ad212f6afd78a90a283b7cd282df40306d16d42b1f67` |
| [app/src/main/java/com/mapcontrol/util/StartupGate.java](../../app/src/main/java/com/mapcontrol/util/StartupGate.java) | 39 | `e3e336507467b7160c8613da7d758df9e47bcf99641f8513082f11d61178f028` |
| [app/src/main/java/com/mapcontrol/util/SystemAppListOpener.java](../../app/src/main/java/com/mapcontrol/util/SystemAppListOpener.java) | 187 | `7ee458171ff85f003405c4b2e2d2730a5e07879c15d84f3ba99630e3f9cc0feb` |
| [app/src/main/java/com/mapcontrol/util/TargetPackageStore.java](../../app/src/main/java/com/mapcontrol/util/TargetPackageStore.java) | 53 | `0d2379fdbf877ba8e9bc8159022a4374e05313c02833d7537e9153ed9cc2d19c` |
| [app/src/main/java/com/mapcontrol/util/UsageAccessHelper.java](../../app/src/main/java/com/mapcontrol/util/UsageAccessHelper.java) | 56 | `490f95ddb47a497002360ed7dea9bc87f16bb3f5d034be514fb3dc02749c7b40` |
| [app/src/main/java/com/mapcontrol/vehicle/CarSeatMemoryManager.java](../../app/src/main/java/com/mapcontrol/vehicle/CarSeatMemoryManager.java) | 327 | `b9277dece6e297815153985dd90d908802874f8646bae3d5a8f350a4b59a6a21` |
| [app/src/main/java/com/mapcontrol/vehicle/ClimateAutomationEngine.java](../../app/src/main/java/com/mapcontrol/vehicle/ClimateAutomationEngine.java) | 935 | `ad91ad3d3d6947dc74c8c85fa4e21b52036ae62b00296ff998ef01fc666337df` |
| [app/src/main/java/com/mapcontrol/vehicle/ClimateAutomationStore.java](../../app/src/main/java/com/mapcontrol/vehicle/ClimateAutomationStore.java) | 217 | `54eb8097e4b9568d3a4eea5b35948811ea40d23613d20dab0bd0f295ebe9afe1` |
| [app/src/main/java/com/mapcontrol/vehicle/ClimateAutomationVdbusDiagnostics.java](../../app/src/main/java/com/mapcontrol/vehicle/ClimateAutomationVdbusDiagnostics.java) | 384 | `e7247617d5ce9c12b249abbc9dd876410341a814721ea497f19d476b297fc045` |
| [app/src/main/java/com/mapcontrol/vehicle/DmsFaceMatcher.java](../../app/src/main/java/com/mapcontrol/vehicle/DmsFaceMatcher.java) | 276 | `01c2c850002adc5dfc4e683f5884ca8d008e825067beffd986d1dfe7faf75fd8` |
| [app/src/main/java/com/mapcontrol/vehicle/DriveModeStyle.java](../../app/src/main/java/com/mapcontrol/vehicle/DriveModeStyle.java) | 89 | `ef3953b976286e55d4e85beb73025edd979cad2008a0cebc3ab7e319820946fd` |
| [app/src/main/java/com/mapcontrol/vehicle/FaceProfileStore.java](../../app/src/main/java/com/mapcontrol/vehicle/FaceProfileStore.java) | 155 | `12a17364537f76ffa2e26b7a61df48e1e77b8427b10c7143555c63f822cf479b` |
| [app/src/main/java/com/mapcontrol/vehicle/LocationSamplePolicy.java](../../app/src/main/java/com/mapcontrol/vehicle/LocationSamplePolicy.java) | 12 | `bbacb8671232f2b3b755ecb7d22c3eb5b365445f4025b87633b7b542eaa06a23` |
| [app/src/main/java/com/mapcontrol/vehicle/material/MaterialVehicleCatalog.java](../../app/src/main/java/com/mapcontrol/vehicle/material/MaterialVehicleCatalog.java) | 85 | `f47a4dcfb4c6417316703d581dab1f3c4ead2fc4418480f9e8d03b033766d551` |
| [app/src/main/java/com/mapcontrol/vehicle/material/MaterialVehiclePackageResolver.java](../../app/src/main/java/com/mapcontrol/vehicle/material/MaterialVehiclePackageResolver.java) | 93 | `0b3abefa08744142df331e5ecbbeb99d8256d777727af4f77ab51cf91ba93265` |
| [app/src/main/java/com/mapcontrol/vehicle/material/MaterialVehiclePreferences.java](../../app/src/main/java/com/mapcontrol/vehicle/material/MaterialVehiclePreferences.java) | 95 | `098c87cec14fd60653f2784bb8c1d8c1d0791932720ce326312b68a8af1e1cfc` |
| [app/src/main/java/com/mapcontrol/vehicle/material/MaterialVehicleResources.java](../../app/src/main/java/com/mapcontrol/vehicle/material/MaterialVehicleResources.java) | 254 | `1c17c46220cebc74e83588494c0ad4554f881b73272c502a631356e31a93be1e` |
| [app/src/main/java/com/mapcontrol/vehicle/material/OemEolConfigReader.java](../../app/src/main/java/com/mapcontrol/vehicle/material/OemEolConfigReader.java) | 363 | `b477986412f61f2ff395d561eb695406de69881e41d4daa1c24cc378f3f924fa` |
| [app/src/main/java/com/mapcontrol/vehicle/material/VehicleMaterialPickerDialog.java](../../app/src/main/java/com/mapcontrol/vehicle/material/VehicleMaterialPickerDialog.java) | 242 | `868b415d902ab53d050d8871a1294d40723dd967f39add1834f556a6b6882195` |
| [app/src/main/java/com/mapcontrol/vehicle/OutsideTemperatureVdbusTest.java](../../app/src/main/java/com/mapcontrol/vehicle/OutsideTemperatureVdbusTest.java) | 151 | `8ce91f17e1302ccda1c99e85b9757459f5557c724bbfa083167f50fa9c93d363` |
| [app/src/main/java/com/mapcontrol/vehicle/PowerModeSample.java](../../app/src/main/java/com/mapcontrol/vehicle/PowerModeSample.java) | 11 | `457b766143b7786d1a558ac3a955406388f9f024994bcce370257300a24fbdc6` |
| [app/src/main/java/com/mapcontrol/vehicle/SeatMemoryVdbusTest.java](../../app/src/main/java/com/mapcontrol/vehicle/SeatMemoryVdbusTest.java) | 200 | `6d417b1857c588b560decba8732ab46b2d98d64ac9af5acd442a5a48ba08f9a7` |
| [app/src/main/java/com/mapcontrol/vehicle/SportAnimationPolicy.java](../../app/src/main/java/com/mapcontrol/vehicle/SportAnimationPolicy.java) | 38 | `013baa70bc54af6fc880648d615972dd2c3db381da190cf7175a6a1200d60aa8` |
| [app/src/main/java/com/mapcontrol/vehicle/VehicleIdentity.java](../../app/src/main/java/com/mapcontrol/vehicle/VehicleIdentity.java) | 39 | `9bfe76fdac8692d6bc770b687e73692199db760d2638df1dacd2148d8f6d1954` |
| [app/src/main/java/com/mapcontrol/vehicle/VehicleIdentityManager.java](../../app/src/main/java/com/mapcontrol/vehicle/VehicleIdentityManager.java) | 464 | `10fbce484f0451717f6208201c0da47d37f5f79995ab292082ad3b001d44b82b` |
| [app/src/main/java/com/mapcontrol/vehicle/VehicleLocationProvider.java](../../app/src/main/java/com/mapcontrol/vehicle/VehicleLocationProvider.java) | 35 | `0ebed4a63ae63207ae1c4fe9c081e8b21f385c6c0a34c89db645db28472f036d` |
| [app/src/main/java/com/mapcontrol/vehicle/VehicleMetricsCache.java](../../app/src/main/java/com/mapcontrol/vehicle/VehicleMetricsCache.java) | 65 | `1c8e886367a32d7dee9666ecfa179c7f5934a797f9808b35bbfedb547df3a2ff` |
| [app/src/main/java/com/mapcontrol/vehicle/VehicleMetricsFormatter.java](../../app/src/main/java/com/mapcontrol/vehicle/VehicleMetricsFormatter.java) | 308 | `aab1a3f659bef25b2c8a2cb84355d8144de54743eee1989fbe542c7087e313c3` |
| [app/src/main/java/com/mapcontrol/vehicle/VehicleMetricsRepository.java](../../app/src/main/java/com/mapcontrol/vehicle/VehicleMetricsRepository.java) | 349 | `c856e8f7c2279d04cd5ad20046b4577b490345d61e4213cda360c13329e02d5f` |
| [app/src/main/java/com/mapcontrol/vehicle/VehicleMetricsSnapshot.java](../../app/src/main/java/com/mapcontrol/vehicle/VehicleMetricsSnapshot.java) | 224 | `7786f9a9f82b9bb3baea6d4ece98574c40abb5e51acb457e3c3aeb12fffab77b` |
| [app/src/main/java/com/mapcontrol/vehicle/VehicleQuickControls.java](../../app/src/main/java/com/mapcontrol/vehicle/VehicleQuickControls.java) | 269 | `517b443adde5def27fb6a2d39f2168bd4467d9c5d2cab83f0febec31bfdaa45e` |
| [app/src/main/java/com/mapcontrol/vehicle/VehicleVdbusDiscoveryTest.java](../../app/src/main/java/com/mapcontrol/vehicle/VehicleVdbusDiscoveryTest.java) | 328 | `86734fdcca7a0aac0288db075769c17e2cb853fcb3b7b1bd6cf41da2cf72108f` |
| [app/src/main/java/com/mapcontrol/weather/OpenMeteoWeatherClient.java](../../app/src/main/java/com/mapcontrol/weather/OpenMeteoWeatherClient.java) | 175 | `3decdbb59814709461378b69bf2b6012953b08d8419aab6bc79f11d894723a1f` |
| [app/src/main/java/com/mapcontrol/weather/OpenMeteoWeatherLabels.java](../../app/src/main/java/com/mapcontrol/weather/OpenMeteoWeatherLabels.java) | 86 | `b4f8ee48590175e5216134db06dc821e1ae96b3dd84d69e194325a11c92a78d7` |
| [app/src/main/java/com/mapcontrol/weather/OpenMeteoWeatherSnapshot.java](../../app/src/main/java/com/mapcontrol/weather/OpenMeteoWeatherSnapshot.java) | 44 | `45bbf6ea4765b1618aaa883e18f29cf54befef21982c77a0edd92c00aca0b638` |
| [app/src/main/res/drawable/bg_card_glass.xml](../../app/src/main/res/drawable/bg_card_glass.xml) | 9 | `8b18b6ecefd973f66e754f1a78e24b6b9962935f885d5e419bd6c90db33e475c` |
| [app/src/main/res/drawable/bg_choice_control.xml](../../app/src/main/res/drawable/bg_choice_control.xml) | 4 | `f5935bc7d7d5125ac8b05ddfead531cee3169f5aa671becec9295db8997ce622` |
| [app/src/main/res/drawable/bg_cluster_nav_card.xml](../../app/src/main/res/drawable/bg_cluster_nav_card.xml) | 9 | `81c903477d062485a10f43bf2fdcc520aad3424dc1813936100e6697ff65cbba` |
| [app/src/main/res/drawable/bg_cluster_nav_eta.xml](../../app/src/main/res/drawable/bg_cluster_nav_eta.xml) | 9 | `e4ebfa86e618a67a63405c58f9fe9599d3bc7638244352ee98d7ebc6a278d099` |
| [app/src/main/res/drawable/bg_cluster_nav_maneuver.xml](../../app/src/main/res/drawable/bg_cluster_nav_maneuver.xml) | 10 | `338e63f74979395d89a839d93820809264714d32b62c76f1e1b13f3a53ddac12` |
| [app/src/main/res/drawable/bg_global_wifi_toast_error.xml](../../app/src/main/res/drawable/bg_global_wifi_toast_error.xml) | 14 | `f4d8138c8d6289f958da908d6e7105b3187701d9decc42c3d71247d325340f42` |
| [app/src/main/res/drawable/bg_global_wifi_toast_halo_error.xml](../../app/src/main/res/drawable/bg_global_wifi_toast_halo_error.xml) | 13 | `dd7e7f718ad300589ebcf2eee1a73157dcd94aa816a19165b9126a6c4e6dd624` |
| [app/src/main/res/drawable/bg_global_wifi_toast_halo_ok.xml](../../app/src/main/res/drawable/bg_global_wifi_toast_halo_ok.xml) | 13 | `9473c1de29c23b9af77bf183ad4d8be3fbdaeb82d6fc05d6054a21a28ac81a4c` |
| [app/src/main/res/drawable/bg_global_wifi_toast_halo_preparing.xml](../../app/src/main/res/drawable/bg_global_wifi_toast_halo_preparing.xml) | 13 | `aeb718a9196d4b30a16dcc3df5a0d1a67ae65f40ae61b79ded847610e1840328` |
| [app/src/main/res/drawable/bg_global_wifi_toast_preparing.xml](../../app/src/main/res/drawable/bg_global_wifi_toast_preparing.xml) | 14 | `dbfc29fb3e2b9d20e035d058a95a129f6833f22b9220568c1e792b66edd2b8db` |
| [app/src/main/res/drawable/bg_global_wifi_toast_success.xml](../../app/src/main/res/drawable/bg_global_wifi_toast_success.xml) | 14 | `7fed653a8e316966632d547c22477cbe3d55e6d89e9814a08b887e2135e8940c` |
| [app/src/main/res/drawable/bg_launch_splash.xml](../../app/src/main/res/drawable/bg_launch_splash.xml) | 11 | `fc768ca5b4458c59664728051f68aab97314e31420399ed86021d59fa012177e` |
| [app/src/main/res/drawable/bg_launcher_dashboard.xml](../../app/src/main/res/drawable/bg_launcher_dashboard.xml) | 14 | `564a7cafec2ce921d59f6e65d88b041c7afcca4c1ce6d3e7f89ab1d61dff87a8` |
| [app/src/main/res/drawable/bg_launcher_fuel_progress.xml](../../app/src/main/res/drawable/bg_launcher_fuel_progress.xml) | 17 | `63990d328b7033a198fc736dcb5ba5760d07a0ccdc975662a2c7d16d0a2b601c` |
| [app/src/main/res/drawable/bg_launcher_icon_slot.xml](../../app/src/main/res/drawable/bg_launcher_icon_slot.xml) | 9 | `e5c36dd829bb97cac0d69d653b9757c0453c10a58cf3213f32df531c3b2eb104` |
| [app/src/main/res/drawable/bg_launcher_icon_slot_exit.xml](../../app/src/main/res/drawable/bg_launcher_icon_slot_exit.xml) | 9 | `bc01df13e699ef28485b59822e8fb62cca935d94e2dfa13f482e0dcf4b6cf83d` |
| [app/src/main/res/drawable/bg_launcher_icon_slot_shortcut.xml](../../app/src/main/res/drawable/bg_launcher_icon_slot_shortcut.xml) | 13 | `4dc0041a85ec32211dbe24122e767489cdc631e85af6329a7dc43a2b52d00788` |
| [app/src/main/res/drawable/bg_launcher_quick_exit_circle.xml](../../app/src/main/res/drawable/bg_launcher_quick_exit_circle.xml) | 8 | `0aa9ca6f6f388e201814025314e2b9ec93145ff056c71ed98bbc7a6575ec57d2` |
| [app/src/main/res/drawable/bg_launcher_vehicle_glow.xml](../../app/src/main/res/drawable/bg_launcher_vehicle_glow.xml) | 11 | `c6192cd1197e94af33f90b0583d55960d31c9a92310a937512a0e5f5649be57e` |
| [app/src/main/res/drawable/bg_nav_pill_selected.xml](../../app/src/main/res/drawable/bg_nav_pill_selected.xml) | 13 | `8465982fbf898c5d81d77636cebfac7b36e842f09b6e5012ba24c7065f6a6d62` |
| [app/src/main/res/drawable/bg_rail_panel.xml](../../app/src/main/res/drawable/bg_rail_panel.xml) | 5 | `0b2439bd9a22c0513bbbba5303604cdf945cc703c7e5943e4ada8c88732e149a` |
| [app/src/main/res/drawable/bg_root_gradient.xml](../../app/src/main/res/drawable/bg_root_gradient.xml) | 10 | `f1332cb676363472a1a540143fb5de626ca9749353b760ce15e3fe5226e59017` |
| [app/src/main/res/drawable/bg_segment_thumb.xml](../../app/src/main/res/drawable/bg_segment_thumb.xml) | 6 | `07f008d91faa111fb9f339ce9b09919d0ddebfd5ff30d93c8e5beef95b121747` |
| [app/src/main/res/drawable/bg_segment_track.xml](../../app/src/main/res/drawable/bg_segment_track.xml) | 6 | `5c898c0aab387130cd2cdfc6a83295803e03345d44073bd7cf9e5c4f77ad812f` |
| [app/src/main/res/drawable/bg_topbar_divider.xml](../../app/src/main/res/drawable/bg_topbar_divider.xml) | 5 | `f889347b662a733197954f1707ca5f6b0c8325440e86c9c26bdb5ece8ffb09ec` |
| [app/src/main/res/drawable/bg_vehicle_quick_control.xml](../../app/src/main/res/drawable/bg_vehicle_quick_control.xml) | 34 | `42e0da268add970bba5afb100cc904891fd5e5e42a4c1d4e0c0db8c8a31a7471` |
| [app/src/main/res/drawable/ic_choice_empty.xml](../../app/src/main/res/drawable/ic_choice_empty.xml) | 3 | `df4a754ea7576eeed0ad28f9a50eb22d76592c14c018bb7ab2e9ded8f44d363f` |
| [app/src/main/res/drawable/ic_choice_selected.xml](../../app/src/main/res/drawable/ic_choice_selected.xml) | 4 | `c2a3a9c8c8de76e73add6d2dee5c57f03a373989822da5f0af6c02f2f9ac6806` |
| [app/src/main/res/drawable/ic_climate_seat_vent.xml](../../app/src/main/res/drawable/ic_climate_seat_vent.xml) | 21 | `1252be06168a27300aa14d1dc25a34e59f0664c5668cf97bd575dc709d59bfc3` |
| [app/src/main/res/drawable/ic_hub_arrow_left.xml](../../app/src/main/res/drawable/ic_hub_arrow_left.xml) | 11 | `64a2d57fe5cb55fd265b7a3a62bcb105325c4cb6b740fecefa9b9cdea0c27f02` |
| [app/src/main/res/drawable/ic_hub_monitor.xml](../../app/src/main/res/drawable/ic_hub_monitor.xml) | 11 | `f744c393844d7c642024381d88b284e7d6b4b17cb72ab65e675a58014346a852` |
| [app/src/main/res/drawable/ic_launcher_vehicle_top.xml](../../app/src/main/res/drawable/ic_launcher_vehicle_top.xml) | 23 | `9181776bf161137fcf7df5ffb67b2eee4f69dbe758605cbfe35572fcbf3c4df2` |
| [app/src/main/res/drawable/ic_mdi_account.xml](../../app/src/main/res/drawable/ic_mdi_account.xml) | 9 | `8ff731fcdcb130fcb11fdd001316c8cd50bd618465543c36dc15fe484cc9b27c` |
| [app/src/main/res/drawable/ic_mdi_beach.xml](../../app/src/main/res/drawable/ic_mdi_beach.xml) | 9 | `26abe32a77c016027bf3502633f3966c5555d1c0055951a16db6c3f06da36ec3` |
| [app/src/main/res/drawable/ic_mdi_bell.xml](../../app/src/main/res/drawable/ic_mdi_bell.xml) | 9 | `b5cf0f2536e4576e9824ffc94b5464eac77e72c7c62daf1e3c6eb14b2c23b065` |
| [app/src/main/res/drawable/ic_mdi_camera.xml](../../app/src/main/res/drawable/ic_mdi_camera.xml) | 9 | `d201d7b7eb9e97a78222ab4095b3935c1e846d5230ffc944e469a59f0ce34ad7` |
| [app/src/main/res/drawable/ic_mdi_car.xml](../../app/src/main/res/drawable/ic_mdi_car.xml) | 9 | `905a6ef5a55f66e20183365468d103691024c597abd69e45af0684252238d20f` |
| [app/src/main/res/drawable/ic_mdi_car_brake_abs.xml](../../app/src/main/res/drawable/ic_mdi_car_brake_abs.xml) | 9 | `259eed838657a9af1307e04af2be04b9154da96dd03b44b1f6504bd270b5e950` |
| [app/src/main/res/drawable/ic_mdi_car_brake_alert.xml](../../app/src/main/res/drawable/ic_mdi_car_brake_alert.xml) | 9 | `ce8ed5bcdfc26e59f5bfc9a07f68eece9c5a5e4edd322ff0671d79a1f11daff4` |
| [app/src/main/res/drawable/ic_mdi_car_defrost.xml](../../app/src/main/res/drawable/ic_mdi_car_defrost.xml) | 10 | `f1b8f891873a5e7eb9eed9e076d5f13ddb298f66184f446e454cc870a1b99c79` |
| [app/src/main/res/drawable/ic_mdi_car_door.xml](../../app/src/main/res/drawable/ic_mdi_car_door.xml) | 11 | `e185b246bc068754491be7d046b15bc5ef041af9c44e626cd6ac301a0a501d99` |
| [app/src/main/res/drawable/ic_mdi_car_seat.xml](../../app/src/main/res/drawable/ic_mdi_car_seat.xml) | 11 | `c1799ee29145c061dda6be57cb280666e7818a1323b4cd84f4bd5eec81b2f465` |
| [app/src/main/res/drawable/ic_mdi_car_sports.xml](../../app/src/main/res/drawable/ic_mdi_car_sports.xml) | 9 | `67ad7dfe80f4bf7e8530244e267988d45c8680afb842bcc647efb35736caa923` |
| [app/src/main/res/drawable/ic_mdi_car_sunroof.xml](../../app/src/main/res/drawable/ic_mdi_car_sunroof.xml) | 11 | `97912e6703e0f8ac3ffa096c92212e9ce75e61e8977242a1afe2868ad604feed` |
| [app/src/main/res/drawable/ic_mdi_car_trunk.xml](../../app/src/main/res/drawable/ic_mdi_car_trunk.xml) | 11 | `432b0afe9e8c0c4e4ccd4189ac3812f35c6ef086ad1886baee7e128195507aa7` |
| [app/src/main/res/drawable/ic_mdi_cellphone.xml](../../app/src/main/res/drawable/ic_mdi_cellphone.xml) | 9 | `a9cb5bd1c6624d6a8c134ec194d6255921459313b4f006ae5b27baa3afc4f362` |
| [app/src/main/res/drawable/ic_mdi_check.xml](../../app/src/main/res/drawable/ic_mdi_check.xml) | 9 | `dfb17dc804f51ef26aef76892e726c99b056180f2f94969e0cae838d6c17dc79` |
| [app/src/main/res/drawable/ic_mdi_chevron_double_left.xml](../../app/src/main/res/drawable/ic_mdi_chevron_double_left.xml) | 9 | `a1cbfb60273e3ab2bb5f0fb8f2ac936eec1ef3c844ad7c5cd720192070fa5e76` |
| [app/src/main/res/drawable/ic_mdi_chevron_up.xml](../../app/src/main/res/drawable/ic_mdi_chevron_up.xml) | 10 | `4ea110a61d921b9c0b1ce666a42f2123b83592558a23ccd69794044a3e6a6f00` |
| [app/src/main/res/drawable/ic_mdi_close.xml](../../app/src/main/res/drawable/ic_mdi_close.xml) | 9 | `16ff819f8d9738d06cab891c96cbe8697f6ac971f498f7254a88cf54391822db` |
| [app/src/main/res/drawable/ic_mdi_cog.xml](../../app/src/main/res/drawable/ic_mdi_cog.xml) | 9 | `e50c051400ab294154b78145458914eb274097c0b2931fa0165c6edaea1b5969` |
| [app/src/main/res/drawable/ic_mdi_delete.xml](../../app/src/main/res/drawable/ic_mdi_delete.xml) | 9 | `9684fe3717a4b214c635ac093c5eded0550cbcf6789eab07661723d6c5cee8f0` |
| [app/src/main/res/drawable/ic_mdi_dots_vertical.xml](../../app/src/main/res/drawable/ic_mdi_dots_vertical.xml) | 9 | `2bf198e85eff20e30b6f25b3373ffa2925cb4213044c2065a96c060505639a71` |
| [app/src/main/res/drawable/ic_mdi_download.xml](../../app/src/main/res/drawable/ic_mdi_download.xml) | 9 | `062353f93bba618fa66cfa4f45ee82bb4323e6398d46cfa4a4987b280c81506e` |
| [app/src/main/res/drawable/ic_mdi_email_outline.xml](../../app/src/main/res/drawable/ic_mdi_email_outline.xml) | 9 | `70ae3002252ad5502360de37c0faaca4fc0430b85008974e57065705bbab314d` |
| [app/src/main/res/drawable/ic_mdi_equalizer.xml](../../app/src/main/res/drawable/ic_mdi_equalizer.xml) | 9 | `159cd03ad4aa2e584115601df0774c29654eeb790a9addf5eb33d0d6aa1b8f79` |
| [app/src/main/res/drawable/ic_mdi_fan.xml](../../app/src/main/res/drawable/ic_mdi_fan.xml) | 10 | `e5ba2181b8f33397f499f76d1f2429a20175b3fac9520bc9a8e322c986ed89bc` |
| [app/src/main/res/drawable/ic_mdi_folder.xml](../../app/src/main/res/drawable/ic_mdi_folder.xml) | 9 | `419dc9cd5119e9d48abb5279bb4ce61917c87a0df1bbfad6948ba0d06f49c7c6` |
| [app/src/main/res/drawable/ic_mdi_fullscreen_exit.xml](../../app/src/main/res/drawable/ic_mdi_fullscreen_exit.xml) | 9 | `bb05ad218885dbe874b5765b2d44f0addfc0a64dd38f503d1c65b95570ed567a` |
| [app/src/main/res/drawable/ic_mdi_inbox_outline.xml](../../app/src/main/res/drawable/ic_mdi_inbox_outline.xml) | 9 | `42d08bf38f3e2d12a2f82159ef7d96967b464fdd4bc2a6311b661fdcf87d46dc` |
| [app/src/main/res/drawable/ic_mdi_leaf.xml](../../app/src/main/res/drawable/ic_mdi_leaf.xml) | 9 | `932d61930f1a7ce1e00d245d4bae48a28aad5bb8ad747240cedf91e8c70fed55` |
| [app/src/main/res/drawable/ic_mdi_lightning_bolt.xml](../../app/src/main/res/drawable/ic_mdi_lightning_bolt.xml) | 9 | `3be9828fb3e845c84d87cb6f225d27506194a2d3ac3a78e4ac994ca40bae4ec6` |
| [app/src/main/res/drawable/ic_mdi_loading.xml](../../app/src/main/res/drawable/ic_mdi_loading.xml) | 9 | `514c9311d90e1d0bd6c3f10565223e00170dcebc4cf0d31309b7c1fdf815d247` |
| [app/src/main/res/drawable/ic_mdi_map.xml](../../app/src/main/res/drawable/ic_mdi_map.xml) | 9 | `7f3913f1fb7563cbc94b84d86ff2c9e6d53e69ff8f0e8c90b1ce043609afd66f` |
| [app/src/main/res/drawable/ic_mdi_map_marker.xml](../../app/src/main/res/drawable/ic_mdi_map_marker.xml) | 9 | `deff5c63d900eca89be2865adf80db56255f041b34cd7bae2371ca871059ceb6` |
| [app/src/main/res/drawable/ic_mdi_music_note.xml](../../app/src/main/res/drawable/ic_mdi_music_note.xml) | 9 | `0a4c5e91a4f045e85913737fd3562d939324e57bb900fc09140979f27bc08fbc` |
| [app/src/main/res/drawable/ic_mdi_package_variant.xml](../../app/src/main/res/drawable/ic_mdi_package_variant.xml) | 9 | `1cfc30e5691e28f00278329a59957a3c589febb92d5de4161ab1c887bba29b63` |
| [app/src/main/res/drawable/ic_mdi_pause.xml](../../app/src/main/res/drawable/ic_mdi_pause.xml) | 9 | `8303838f0599625cb588e78a5f8cf13da83357e228ae27cd31337333e2bdf8a0` |
| [app/src/main/res/drawable/ic_mdi_play.xml](../../app/src/main/res/drawable/ic_mdi_play.xml) | 9 | `2dede3fff7bf5058072d129b483eadb569b0039ad835885152c4d2bc3a3d0deb` |
| [app/src/main/res/drawable/ic_mdi_plus.xml](../../app/src/main/res/drawable/ic_mdi_plus.xml) | 10 | `3246db964c5f95bd5832a94429fbcc71aa2b577a3821c80556a58ae8abf7bf82` |
| [app/src/main/res/drawable/ic_mdi_radiobox_blank.xml](../../app/src/main/res/drawable/ic_mdi_radiobox_blank.xml) | 9 | `926fbe0e916b4c6b16835efb2baa6f6c7f4bb46df0db2f700f0d43294b95fc7a` |
| [app/src/main/res/drawable/ic_mdi_refresh.xml](../../app/src/main/res/drawable/ic_mdi_refresh.xml) | 9 | `bb90c56970e5e1b0d3210115d922d25e7399380e2a44cefa73173d00b9494caf` |
| [app/src/main/res/drawable/ic_mdi_restart.xml](../../app/src/main/res/drawable/ic_mdi_restart.xml) | 9 | `9b21561fb0626da29461a9a954df222e3829b7b28440f6c264acdc4d6f294f29` |
| [app/src/main/res/drawable/ic_mdi_road_variant.xml](../../app/src/main/res/drawable/ic_mdi_road_variant.xml) | 9 | `e40d9e97779e055376519a7ea1d6e69e745faefc737fffae8a085eac75c75637` |
| [app/src/main/res/drawable/ic_mdi_shield_car.xml](../../app/src/main/res/drawable/ic_mdi_shield_car.xml) | 9 | `a2a40cee93212f1910e1f9569054b304ad95bf7889cc8671e3a4b6bce8d73f53` |
| [app/src/main/res/drawable/ic_mdi_skip_next.xml](../../app/src/main/res/drawable/ic_mdi_skip_next.xml) | 10 | `c0919dd7dd6d66cb262c44567af0ace74e3ced82609ddcc556945d0dceb24597` |
| [app/src/main/res/drawable/ic_mdi_skip_previous.xml](../../app/src/main/res/drawable/ic_mdi_skip_previous.xml) | 10 | `435af7a77be5ca8b8e0179d39eeecf8b08e7399b4a8773018fd802df755e6c0d` |
| [app/src/main/res/drawable/ic_mdi_snowflake.xml](../../app/src/main/res/drawable/ic_mdi_snowflake.xml) | 9 | `0b609af2ef60a9bb1df8c467643c5cfec52dbbf58820099074e77641a2b58d05` |
| [app/src/main/res/drawable/ic_mdi_speedometer.xml](../../app/src/main/res/drawable/ic_mdi_speedometer.xml) | 9 | `acb77a45cbed91ea79089b50070e51451989350773db378d65e55aac9064bff6` |
| [app/src/main/res/drawable/ic_mdi_steering.xml](../../app/src/main/res/drawable/ic_mdi_steering.xml) | 10 | `b77d2c8c38d6e6ca252ad6ed5a9ac4ae3c0877a26c6e1caceadce77e40601b52` |
| [app/src/main/res/drawable/ic_mdi_terrain.xml](../../app/src/main/res/drawable/ic_mdi_terrain.xml) | 9 | `d803bcd979431b0d9d760d8fea28a21b83bb30f65fdd02c9b8c89b1f3718b624` |
| [app/src/main/res/drawable/ic_mdi_thermometer.xml](../../app/src/main/res/drawable/ic_mdi_thermometer.xml) | 10 | `22d886978026b8e852cb3e00d24a5d7f698015404f54b9dac9004dc5c0b72f3c` |
| [app/src/main/res/drawable/ic_mdi_timer_sand.xml](../../app/src/main/res/drawable/ic_mdi_timer_sand.xml) | 9 | `55d4332d95cc75fb6b81f8c4175f9f9d0113935fd03c5f7df3a604c9f2a01e92` |
| [app/src/main/res/drawable/ic_mdi_upload.xml](../../app/src/main/res/drawable/ic_mdi_upload.xml) | 9 | `937ce6f708e8404d27ca4b2c7d2a69d59fc2a521439fa3e95c8ddcd7ac21003d` |
| [app/src/main/res/drawable/ic_mdi_volume_high.xml](../../app/src/main/res/drawable/ic_mdi_volume_high.xml) | 9 | `8ddef371f7b8250dbe00e1f804f402aba3621c991f5fa64ff46324b0851bf0cb` |
| [app/src/main/res/drawable/ic_mdi_volume_minus.xml](../../app/src/main/res/drawable/ic_mdi_volume_minus.xml) | 11 | `540d7c0fc0835d78e1ba68927f86c2c69ddf0ec380bcfba870612c8dd1496ee6` |
| [app/src/main/res/drawable/ic_mdi_volume_mute.xml](../../app/src/main/res/drawable/ic_mdi_volume_mute.xml) | 11 | `eed929d4d3ecd41f0d9381ee0da25e9b763246d9e116ccbc6d920156cfdf7658` |
| [app/src/main/res/drawable/ic_mdi_volume_plus.xml](../../app/src/main/res/drawable/ic_mdi_volume_plus.xml) | 11 | `109560960318e9ae174d9a8e3ae39348519661dd08c507e837ccda3972f0f4ae` |
| [app/src/main/res/drawable/ic_mdi_waves.xml](../../app/src/main/res/drawable/ic_mdi_waves.xml) | 9 | `7cf890436a0895121d9554a1150e6ef7311780335d3f7ce7fd2d6b1da1ba4cff` |
| [app/src/main/res/drawable/ic_mdi_web.xml](../../app/src/main/res/drawable/ic_mdi_web.xml) | 9 | `1aac6181c030d7b8b4114321a0fcaa47960bae6b8b89f6c8cb65f7c2b07247bb` |
| [app/src/main/res/drawable/ic_mdi_wifi.xml](../../app/src/main/res/drawable/ic_mdi_wifi.xml) | 9 | `c901510e89449174aa6bdd8d83bbc32bde0febcb3e6e3fd7046be759fd00e8f2` |
| [app/src/main/res/drawable/ic_swipe_arrow_up.xml](../../app/src/main/res/drawable/ic_swipe_arrow_up.xml) | 10 | `85df32d887733205941be4b2de700ecf6667703d37109851ab14b72337d9a374` |
| [app/src/main/res/drawable/ic_vehicle_back.xml](../../app/src/main/res/drawable/ic_vehicle_back.xml) | 9 | `21d044c664c3fcceb7f7b99a6b475dd8b7f65b024513f5dc88d07429d77393aa` |
| [app/src/main/res/drawable/ic_vehicle_lock.xml](../../app/src/main/res/drawable/ic_vehicle_lock.xml) | 9 | `86918e0d0b6ed8bd14215ea12a9bbd3b57c407f6b8e08de2b4b3a5c8590cd9e2` |
| [app/src/main/res/drawable/ic_vehicle_trunk.xml](../../app/src/main/res/drawable/ic_vehicle_trunk.xml) | 9 | `905a6ef5a55f66e20183365468d103691024c597abd69e45af0684252238d20f` |
| [app/src/main/res/layout/activity_camera.xml](../../app/src/main/res/layout/activity_camera.xml) | 161 | `96f86c18d4539dc639fa1577c70f9d818ccece53ea11ee32fe1e50f9933dc16b` |
| [app/src/main/res/layout/activity_main.xml](../../app/src/main/res/layout/activity_main.xml) | 51 | `5671a16ab6359da77984095c91caa0a2a4ecfd8b1af7e2f7d7c333d4f7e9349f` |
| [app/src/main/res/mipmap-anydpi-v26/ic_launcher.xml](../../app/src/main/res/mipmap-anydpi-v26/ic_launcher.xml) | 6 | `6a7c66aeab4b039febf250b2abc7c87dfd2ab81bb0eda1495249c9f38a4af810` |
| [app/src/main/res/values/colors.xml](../../app/src/main/res/values/colors.xml) | 184 | `fef8e929ba5b7a3d6219644d7117ede3e1b97fa7a2dc2e6ce85a1eacbeaf60a5` |
| [app/src/main/res/values/dimens.xml](../../app/src/main/res/values/dimens.xml) | 97 | `fe87ee0af149c6281f63fc08f5aad511fd16a86d905aa2677cedb8d592b0bdf8` |
| [app/src/main/res/values/ids.xml](../../app/src/main/res/values/ids.xml) | 18 | `e64f9830da54408fed2992b4e113ffce638def244a36f7b634d58ce386faedc7` |
| [app/src/main/res/values/integers.xml](../../app/src/main/res/values/integers.xml) | 10 | `281c65ad474d1f26ce042b4daed92670852e9a2bc3721701e82d49a2a135dc64` |
| [app/src/main/res/values/strings.xml](../../app/src/main/res/values/strings.xml) | 263 | `b98952c7810d4868b594ee8830019def431794bb4281aa238e1b362c85b9f2f2` |
| [app/src/main/res/values/strings_ivi_diagnostics.xml](../../app/src/main/res/values/strings_ivi_diagnostics.xml) | 4 | `b489c167ae2ea11363aa5494c2200f6cd51a217336dd03367f3c2c9ec108c277` |
| [app/src/main/res/values/strings_ui.xml](../../app/src/main/res/values/strings_ui.xml) | 706 | `7d5c21b1d86fbcb8158413935c3fafce219409d02c973cffcdc7c346e452c1fc` |
| [app/src/main/res/values/themes.xml](../../app/src/main/res/values/themes.xml) | 62 | `415ecb96f6fa35d07a5445aeab3bdf64c2bbd693f6ab9d2c8484d66ed8167616` |
| [app/src/main/res/values-en/strings.xml](../../app/src/main/res/values-en/strings.xml) | 260 | `51d09da76c6e58e62480d518cbacda5ced80d21e0df2581bccf0c54fe1de35dc` |
| [app/src/main/res/values-en/strings_ivi_diagnostics.xml](../../app/src/main/res/values-en/strings_ivi_diagnostics.xml) | 4 | `b489c167ae2ea11363aa5494c2200f6cd51a217336dd03367f3c2c9ec108c277` |
| [app/src/main/res/values-en/strings_ui.xml](../../app/src/main/res/values-en/strings_ui.xml) | 704 | `32d31b68a9ee8d2f329d88521778cb1e47b7108aa442f91a0b51f52bf215880a` |
| [app/src/main/res/values-night/colors.xml](../../app/src/main/res/values-night/colors.xml) | 183 | `7db30e28ecf7775b584492449edb2b5edb35081db46b15a12b64e3e5e98681f8` |
| [app/src/main/res/values-night/themes.xml](../../app/src/main/res/values-night/themes.xml) | 15 | `e61f28fc25a334591dfd4c3aa5769b8ff5c2a399c7c7789e06655337904fb1cc` |
| [app/src/main/res/values-tr/strings.xml](../../app/src/main/res/values-tr/strings.xml) | 265 | `70a85b8484964dc4a058c5495a3bab61670784db508933e4a8522dc20d8abe22` |
| [app/src/main/res/values-tr/strings_ivi_diagnostics.xml](../../app/src/main/res/values-tr/strings_ivi_diagnostics.xml) | 4 | `ef686d531463e2a520c2de914bea18b58dc47a598b94becf1d08c77d49b62849` |
| [app/src/main/res/values-tr/strings_ui.xml](../../app/src/main/res/values-tr/strings_ui.xml) | 725 | `ef6943f371bf3628ffde842a29354877a0b16f6c925df3179f11d53271152880` |
| [app/src/main/res/xml/accessibility_service_config.xml](../../app/src/main/res/xml/accessibility_service_config.xml) | 11 | `be8d4c50599c8d56160181b6c3c7b0f048c86bd0a899a783b3e3f54752bfb48a` |
| [app/src/main/res/xml/device_admin_mapcontrol.xml](../../app/src/main/res/xml/device_admin_mapcontrol.xml) | 6 | `41aab5882836738d088f25b604126b50b3dc5e707017cbad734a45bb3ef00f2d` |
| [app/src/main/res/xml/file_provider_paths.xml](../../app/src/main/res/xml/file_provider_paths.xml) | 11 | `6534ca6149ba938b5dc265ba7eb8cadf78f62ff539adcba607d3043125c166cc` |
| [app/src/main/res/xml/network_security_config.xml](../../app/src/main/res/xml/network_security_config.xml) | 22 | `d78797ec288ed8634f745543392c74334508a5cfd9f571649b809e4e8919221c` |

## Görsel literal renkler

Adlandırılmış açık/koyu palet design.md içindedir. Aşağıda diğer kaynaklardaki hex, CSS rgb/rgba/hsl/hsla, Color.rgb/argb ve Color sabit kullanımları vardır. Java 0x adayları yalnız uygulama UI/çizim dosyalarında listelenir; maske/sentinel olabilen değerler bağlamıyla değerlendirilmelidir. PNG/GLB pikselleri semantik palet değildir.

### app/src/main/assets/welcome.html

```text
11: background: linear-gradient(135deg, #10141B 0%, #1A212C 100%);
12: color: #FFFFFF;
22: background: rgba(30, 38, 48, 0.95);
25: box-shadow: 0 20px 60px rgba(0, 0, 0, 0.5);
27: border: 1px solid rgba(115, 178, 245, 0.15);
58: color: #FFFFFF;
64: color: #AAAAAA;
80: background: rgba(115, 178, 245, 0.05);
81: border: 2px dashed rgba(115, 178, 245, 0.3);
88: background: rgba(115, 178, 245, 0.1);
89: border-color: rgba(115, 178, 245, 0.5);
91: box-shadow: 0 8px 24px rgba(115, 178, 245, 0.2);
94: background: rgba(115, 178, 245, 0.15);
95: border-color: #73B2F5;
97: box-shadow: 0 12px 32px rgba(115, 178, 245, 0.3);
100: background: rgba(115, 178, 245, 0.1);
101: border-color: #73B2F5;
114: color: #CCCCCC;
120: color: #888888;
134: background: rgba(115, 178, 245, 0.1);
144: color: #73B2F5;
151: color: #AAAAAA;
166: color: #CCCCCC;
169: color: #73B2F5;
175: background: rgba(255, 255, 255, 0.1);
182: background: linear-gradient(90deg, #73B2F5 0%, #4586C5 100%);
195: background: linear-gradient(90deg, transparent, rgba(255,255,255,0.3), transparent);
203: background: linear-gradient(135deg, #4586C5 0%, #73B2F5 100%);
204: color: #FFFFFF;
213: box-shadow: 0 4px 16px rgba(115, 178, 245, 0.4);
226: background: rgba(255, 255, 255, 0.2);
236: box-shadow: 0 8px 24px rgba(115, 178, 245, 0.5);
242: background: #555555;
253: background: rgba(115, 178, 245, 0.1);
256: color: #73B2F5;
263: background: #73B2F5;
281: background: rgba(30, 38, 48, 0.98);
282: border: 1px solid rgba(115, 178, 245, 0.3);
287: box-shadow: 0 8px 32px rgba(0, 0, 0, 0.4);
317: color: #4CAF50;
320: color: #F44336;
323: color: #73B2F5;
358: background: rgba(0, 0, 0, 0.7);
374: background: rgba(30, 38, 48, 0.98);
379: box-shadow: 0 20px 60px rgba(0, 0, 0, 0.5);
380: border: 1px solid rgba(115, 178, 245, 0.2);
400: color: #FFFFFF;
405: color: #CCCCCC;
424: background: linear-gradient(135deg, #4586C5 0%, #73B2F5 100%);
425: color: #FFFFFF;
426: box-shadow: 0 4px 12px rgba(115, 178, 245, 0.3);
430: box-shadow: 0 6px 20px rgba(115, 178, 245, 0.4);
433: background: rgba(255, 255, 255, 0.1);
434: color: #CCCCCC;
435: border: 1px solid rgba(255, 255, 255, 0.2);
438: background: rgba(255, 255, 255, 0.15);
452: color: #FFFFFF;
455: background: rgba(115, 178, 245, 0.1);
456: border: 1px solid rgba(115, 178, 245, 0.3);
457: color: #73B2F5;
468: background: rgba(115, 178, 245, 0.2);
478: scrollbar-color: rgba(115, 178, 245, 0.3) transparent;
487: background: rgba(115, 178, 245, 0.3);
491: background: rgba(115, 178, 245, 0.5);
498: background: rgba(255, 255, 255, 0.03);
502: border: 1px solid rgba(255, 255, 255, 0.05);
505: background: rgba(255, 255, 255, 0.06);
507: border-color: rgba(115, 178, 245, 0.2);
515: color: #FFFFFF;
522: color: #AAAAAA;
530: background: rgba(244, 67, 54, 0.1);
531: border: 1px solid rgba(244, 67, 54, 0.3);
532: color: #F44336;
543: background: rgba(244, 67, 54, 0.2);
545: box-shadow: 0 4px 12px rgba(244, 67, 54, 0.3);
551: background: rgba(76, 175, 80, 0.1);
552: border: 1px solid rgba(76, 175, 80, 0.3);
553: color: #4CAF50;
564: background: rgba(76, 175, 80, 0.2);
566: box-shadow: 0 4px 12px rgba(76, 175, 80, 0.3);
574: color: #888888;
585: color: #73B2F5;
592: border: 3px solid rgba(115, 178, 245, 0.3);
593: border-top-color: #73B2F5;
603: .app-brand .brand-eyebrow { font-size: 12px; color: #9CA3AF; letter-spacing: 0.12em; text-transform: uppercase; }
604: .app-brand .brand-name { font-size: 20px; font-weight: 600; color: #E6E6E6; margin-top: 4px; }
610: border: 1px solid rgba(115, 178, 245, 0.28); background: transparent;
611: color: #9CA3AF; font-size: 12px; font-weight: 700;
613: .lang-switch button.active { background: rgba(69, 134, 197, 0.22); color: #fff; border-color: #73B2F5; }
620: border: 1px solid rgba(115, 178, 245, 0.28);
621: background: rgba(30, 38, 48, 0.75); color: #E6E6E6; font-size: 14px; font-weight: 600;
625: .web-nav button .nav-ico { fill: #73B2F5; }
626: .web-nav button:hover { background: rgba(115, 178, 245, 0.08); border-color: rgba(115, 178, 245, 0.5); }
627: .web-nav button.active { background: rgba(69, 134, 197, 0.15); border-color: #73B2F5; color: #FFFFFF; }
631: .icon-header svg, .icon-svg { width: 48px; height: 48px; fill: #73B2F5; }
635: border: 1px solid rgba(115, 178, 245, 0.3); background: rgba(0,0,0,0.2); color: #fff;
639: .help-text { font-size: 12px; color: #9CA3AF; margin-top: 8px; line-height: 1.45; }
640: .lock-badge { font-size: 12px; color: #6B8EBF; margin-left: 6px; }
643: font-size: 13px; color: #E6E6E6; cursor: pointer; user-select: none;
645: .inline-check input { width: 18px; height: 18px; margin-top: 2px; flex-shrink: 0; accent-color: #73B2F5; cursor: pointer; }
1365: filesList.innerHTML = '<div class="empty-state" style="color: #F44336;"><div class="empty-state-icon">⚠️</div><div>' + escapeHtml(t('filesLoadFailed')) + '</div></div>';
1571: appsList.innerHTML = '<div class="empty-state" style="color: #F44336;"><div class="empty-state-icon">⚠️</div><div>' + escapeHtml(t('appsLoadFailed')) + '</div></div>';
```

### app/src/main/java/com/mapcontrol/manager/FloatingOverlayBarSpec.java

```text
88: mask.setColor(Color.WHITE);
```

### app/src/main/java/com/mapcontrol/manager/FloatingProjectionControlsManager.java

```text
224: root.setBackgroundColor(Color.TRANSPARENT);
```

### app/src/main/java/com/mapcontrol/manager/ProjectionVDBusTargetPickerManager.java

```text
612: bg.setColor(0x00000000);
```

### app/src/main/java/com/mapcontrol/service/DmsFaceRecognitionManager.java

```text
526: pixels[row * width + col] = 0xFF000000 | (y << 16) | (y << 8) | y;
```

### app/src/main/java/com/mapcontrol/ui/activity/DmsClusterPreview.java

```text
69: root.setBackgroundColor(0x00000000);
73: image.setBackgroundColor(0xFF000000);
88: prompt.setTextColor(0xFFFFFFFF);
92: prompt.setShadowLayer(8f * density, 0f, 2f * density, 0xCC000000);
99: instruction.setTextColor(0xFFE8EEF6);
102: instruction.setShadowLayer(6f * density, 0f, 2f * density, 0xCC000000);
```

### app/src/main/java/com/mapcontrol/ui/activity/DmsFaceProfileActivity.java

```text
214: backBtn.setTextColor(0xFFFFFFFF);
218: UiStyles.applySolidRoundedBackgroundDp(backBtn, 0xFF263244, 12f);
235: clearButton.setTextColor(0xFFFFFFFF);
239: UiStyles.applySolidRoundedBackgroundDp(clearButton, 0xFFB91C1C, 12f);
271: cameraFrame.setBackgroundColor(0xFF000000);
356: nameInput.setHintTextColor(0xFF94A3B8);
357: nameInput.setTextColor(0xFFFFFFFF);
360: UiStyles.applySolidRoundedBackgroundDp(nameInput, 0xFF1E293B, 10f);
406: enrollButton.setTextColor(0xFFFFFFFF);
411: UiStyles.applySolidRoundedBackgroundDp(enrollButton, 0xFF2563EB, 12f);
451: btn.setTextColor(0xFFFFFFFF);
454: btn, isSelected ? 0xFF2563EB : 0xFF334155, 10f);
499: UiStyles.applySolidRoundedBackgroundDp(row, 0xFF1E293B, 10f);
510: nameTv.setTextColor(0xFFFFFFFF);
518: subTv.setTextColor(0xFF60A5FA);
532: memBtn.setTextColor(0xFFFFFFFF);
538: memBtn, active ? 0xFF2563EB : 0xFF334155, 8f);
549: delBtn.setTextColor(0xFFFFFFFF);
552: UiStyles.applySolidRoundedBackgroundDp(delBtn, 0xFFB91C1C, 8f);
666: UiStyles.applySolidRoundedBackgroundDp(enrollButton, 0xFFB91C1C, 12f);
672: enrollButton, canEnroll ? 0xFF2563EB : 0xFF475569, 12f);
1058: toneCanvas.drawColor(0xFF000000);
```

### app/src/main/java/com/mapcontrol/ui/builder/AssistTabBuilder.java

```text
44: scrollView.setBackgroundColor(Color.TRANSPARENT);
49: container.setBackgroundColor(Color.TRANSPARENT);
92: assistGrid.setBackgroundColor(Color.TRANSPARENT);
```

### app/src/main/java/com/mapcontrol/ui/builder/ClimateAutomationTabBuilder.java

```text
273: badgeBg.setColor((UiStyles.color(context, R.color.oemAccent) & 0x00FFFFFF) | 0x22000000);
397: new int[]{accentColor, (accentColor & 0x00FFFFFF) | 0x33000000});
482: compareBg.setColor((accentColor & 0x00FFFFFF) | 0x1A000000);
484: compareBg.setStroke(dp(1, density), (accentColor & 0x00FFFFFF) | 0x66000000);
508: conflictNote.setTextColor(0xFFFF4D4F); // Belirgin kırmızı/turuncu uyarı
828: return 0xFFEF4444; // Modern canlı kırmızı
832: return 0xFF38BDF8; // Modern canlı açık mavi / cyan
```

### app/src/main/java/com/mapcontrol/ui/builder/DmsFaceRecognitionTabBuilder.java

```text
134: profilesButton.setTextColor(0xFFFFFFFF);
135: UiStyles.applySolidRoundedBackgroundDp(profilesButton, 0xFF2563EB, 12f);
278: badge.setTextColor(0xFF60A5FA);
```

### app/src/main/java/com/mapcontrol/ui/builder/DriveModeTabBuilder.java

```text
66: scrollView.setBackgroundColor(Color.TRANSPARENT);
167: driveModeTrack.setBackgroundColor(Color.TRANSPARENT);
```

### app/src/main/java/com/mapcontrol/ui/builder/EqualizerTabBuilder.java

```text
83: scrollView.setBackgroundColor(Color.TRANSPARENT);
317: chip.setBackgroundColor(Color.TRANSPARENT);
```

### app/src/main/java/com/mapcontrol/ui/builder/FileUploadTabBuilder.java

```text
47: scrollView.setBackgroundColor(Color.TRANSPARENT);
199: bitmap.setPixel(x, y, bitMatrix.get(x, y) ? Color.BLACK : Color.WHITE);
```

### app/src/main/java/com/mapcontrol/ui/builder/LauncherDashboardBuilder.java

```text
1316: driveTab.setBackgroundColor(android.graphics.Color.TRANSPARENT);
1323: musicTab.setBackgroundColor(android.graphics.Color.TRANSPARENT);
```

### app/src/main/java/com/mapcontrol/ui/builder/LogTabBuilder.java

```text
58: tabContent.setBackgroundColor(Color.TRANSPARENT);
```

### app/src/main/java/com/mapcontrol/ui/builder/ProfileTabBuilder.java

```text
94: profileScrollView.setBackgroundColor(Color.TRANSPARENT);
916: bitmap.setPixel(x, y, bitMatrix.get(x, y) ? Color.BLACK : Color.WHITE);
```

### app/src/main/java/com/mapcontrol/ui/builder/ProjectionTabBuilder.java

```text
83: scrollView.setBackgroundColor(Color.TRANSPARENT);
```

### app/src/main/java/com/mapcontrol/ui/builder/SettingsTabBuilder.java

```text
68: scrollView.setBackgroundColor(Color.TRANSPARENT);
```

### app/src/main/java/com/mapcontrol/ui/builder/SideRailBuilder.java

```text
84: sideRailTopBar.setBackgroundColor(Color.TRANSPARENT);
126: menuScrollView.setBackgroundColor(Color.TRANSPARENT);
131: menuContainer.setBackgroundColor(Color.TRANSPARENT);
502: row.setBackgroundColor(Color.TRANSPARENT);
```

### app/src/main/java/com/mapcontrol/ui/builder/WelcomeSoundScreenBuilder.java

```text
82: scrollView.setBackgroundColor(android.graphics.Color.TRANSPARENT);
```

### app/src/main/java/com/mapcontrol/ui/theme/UiStyles.java

```text
126: ? Color.BLACK : Color.WHITE;
517: track.setBackgroundColor(Color.TRANSPARENT);
```

### app/src/main/java/com/mapcontrol/ui/welcome/MountainHikeScene.java

```text
94: paint.setColor(0xFF3E5878);
96: paint.setColor(0xFF24344C);
99: paint.setColor(0xFF121A28);
102: paint.setColor(0xFF0C121C);
117: new int[]{0xFF1A2744, 0xFF243656, 0xFF6A4A58, 0xFFC46A3A},
121: 0x88FFB060, 0x00FFB060, Shader.TileMode.CLAMP);
135: paint.setColor(0x22101828);
141: paint.setColor(0x18F0C8A0);
149: paint.setColor(0x66FF9A48);
151: paint.setColor(0xFFFFE0B0);
153: paint.setColor(0xFFFFF6E4);
158: paint.setColor(0xFF1A2838);
197: paint.setColor(0xCC1A2433);
216: paint.setColor(0x99D5E2F0);
220: paint.setColor(0xE8F0A060);
```

### app/src/main/java/com/mapcontrol/ui/welcome/MountainHikeWelcomeDesign.java

```text
38: private static final int INK = 0xFFF4F7FB;
39: private static final int INK_SOFT = 0xFFC9D0DA;
40: private static final int RIDGE = 0xFFD5DCE4;
67: root.setBackgroundColor(0xFF1A2744);
174: line.setBackgroundColor(0x99D5DCE6);
```

### app/src/main/java/com/mapcontrol/ui/welcome/SignatureWelcomeDesign.java

```text
80: Color.argb(110, Color.red(accent), Color.green(accent), Color.blue(accent)),
81: Color.TRANSPARENT
285: oval.setColor(Color.TRANSPARENT);
287: Color.argb(170, Color.red(color), Color.green(color), Color.blue(color)));
```

### app/src/main/java/com/mapcontrol/ui/welcome/WelcomeScreenPresenter.java

```text
64: host.setBackgroundColor(0xFF10141B);
69: curtain.setBackgroundColor(0xFF10141B);
```

### app/src/main/java/com/mapcontrol/ui/welcome/WinterTouchScene.java

```text
88: petal.color = i % 3 == 0 ? 0xCCFFF4F8 : (i % 3 == 1 ? 0xD8F3C2D4 : 0xB8E7A8C0);
124: new int[]{0xFF161028, 0xFF2A1844, 0xFF4A2A58, 0xFF6E3A62},
128: 0x66E7A0C0, 0x00000000, Shader.TileMode.CLAMP);
149: paint.setColor(0x18F6C6DC);
151: paint.setColor(0x28F8D0E2);
159: paint.setColor(0x33E8B0C8);
162: paint.setColor(0x22F3D0DE);
172: paint.setColor(0x66C98AA4);
182: flower(canvas, dp(28) + sway, dp(22), dp(16), 0xD8F8D0DE, 0xE6FFF6FA);
183: flower(canvas, dp(62), dp(8) + sway * 0.4f, dp(12), 0xC8F3C4D6, 0xDDFFF8FB);
184: flower(canvas, dp(96) + sway * 0.3f, dp(26), dp(18), 0xE0FBE4EE, 0xF0FFF9FC);
185: flower(canvas, dp(18), dp(58), dp(11), 0x99F0B8CE, 0xAAFFF4F8);
186: flower(canvas, w - dp(30) - sway, h - dp(26), dp(17), 0xD8F8D0DE, 0xE6FFF6FA);
187: flower(canvas, w - dp(68), h - dp(12) - sway * 0.4f, dp(13), 0xC8F3C4D6, 0xDDFFF8FB);
188: flower(canvas, w - dp(104) - sway * 0.3f, h - dp(34), dp(19), 0xE0FBE4EE, 0xF0FFF9FC);
189: flower(canvas, w - dp(22), h - dp(64), dp(10), 0x99F0B8CE, 0xAAFFF4F8);
238: paint.setColor(0x66F4C9DA);
241: paint.setColor(0xE6F8D7E6);
253: paint.setColor(i % 2 == 0 ? 0xCCF7D0E0 : 0x99F3C4D6);
267: paint.setColor(Color.argb(28 - i * 3, 248, 220, 232));
276: paint.setColor(0xE8F8E4EE);
297: paint.setColor(0x88F8D5E4);
299: paint.setColor(0xE8F8E4EE);
```

### app/src/main/java/com/mapcontrol/ui/welcome/WinterTouchWelcomeDesign.java

```text
38: private static final int INK = 0xFFF6F1F6;
39: private static final int INK_SOFT = 0xFFD9CED8;
40: private static final int BLUSH = 0xFFE7C3D4;
67: root.setBackgroundColor(0xFF161028);
182: line.setBackgroundColor(0x99E7C3D4);
```

### app/src/main/java/com/mapcontrol/util/InstallerConfirmClicker.java

```text
155: background.setColor(0xE61B1F27);
160: text.setTextColor(Color.WHITE);
170: cancel.setTextColor(Color.WHITE);
173: cancelBg.setColor(0xFF5C6370);
```

### app/src/main/java/com/mapcontrol/util/OnboardingHelper.java

```text
79: next.setTextColor(Color.WHITE);
117: chip.setTextColor(Color.WHITE);
```

### app/src/main/java/com/mapcontrol/util/PermissionProbeDialog.java

```text
29: private static final int COLOR_OK = 0xFF66BB6A;
30: private static final int COLOR_FAIL = 0xFFEF5350;
31: private static final int COLOR_SKIP = 0xFFFFB74D;
```

### app/src/main/res/drawable/bg_launcher_dashboard.xml

```text
7: android:startColor="#EEF3FA"
8: android:centerColor="#E2EBF6"
9: android:endColor="#D4E0F0" />
```

### app/src/main/res/drawable/bg_launcher_icon_slot_exit.xml

```text
8: android:color="#44A34040" />
```

### app/src/main/res/drawable/bg_launcher_quick_exit_circle.xml

```text
7: android:color="#44A34040" />
```

### app/src/main/res/drawable/bg_launcher_vehicle_glow.xml

```text
9: android:startColor="#554586C5"
10: android:endColor="#004586C5" />
```

### app/src/main/res/drawable/ic_choice_empty.xml

```text
2: <path android:fillColor="#00000000" android:strokeColor="#FF000000" android:strokeWidth="2.5" android:pathData="M16,3 A13,13 0,1 0,16,29 A13,13 0,1 0,16,3" />
```

### app/src/main/res/drawable/ic_choice_selected.xml

```text
2: <path android:fillColor="#00000000" android:strokeColor="#FF000000" android:strokeWidth="2.5" android:pathData="M16,3 A13,13 0,1 0,16,29 A13,13 0,1 0,16,3" />
3: <path android:fillColor="#FF000000" android:pathData="M16,9 A7,7 0,1 0,16,23 A7,7 0,1 0,16,9" />
```

### app/src/main/res/drawable/ic_climate_seat_vent.xml

```text
10: android:strokeColor="#FF181A1F"
17: android:strokeColor="#FF181A1F"
```

### app/src/main/res/drawable/ic_hub_arrow_left.xml

```text
9: android:fillColor="#FFFFFFFF"
```

### app/src/main/res/drawable/ic_hub_monitor.xml

```text
9: android:fillColor="#FFFFFFFF"
```

### app/src/main/res/drawable/ic_launcher_vehicle_top.xml

```text
9: android:fillColor="#CC2D3A4F"
12: android:fillColor="#99FFFFFF"
15: android:fillColor="#662D3A4F"
18: android:fillColor="#662D3A4F"
21: android:fillColor="#553568A0"
```

### app/src/main/res/drawable/ic_mdi_account.xml

```text
7: android:fillColor="#FFFFFFFF"
```

### app/src/main/res/drawable/ic_mdi_beach.xml

```text
7: android:fillColor="#FFFFFFFF"
```

### app/src/main/res/drawable/ic_mdi_bell.xml

```text
7: android:fillColor="#FFFFFFFF"
```

### app/src/main/res/drawable/ic_mdi_camera.xml

```text
7: android:fillColor="#FFFFFFFF"
```

### app/src/main/res/drawable/ic_mdi_car.xml

```text
7: android:fillColor="#FFFFFFFF"
```

### app/src/main/res/drawable/ic_mdi_car_brake_abs.xml

```text
7: android:fillColor="#FFFFFFFF"
```

### app/src/main/res/drawable/ic_mdi_car_brake_alert.xml

```text
7: android:fillColor="#FFFFFFFF"
```

### app/src/main/res/drawable/ic_mdi_car_defrost.xml

```text
8: android:fillColor="#FFFFFFFF"
```

### app/src/main/res/drawable/ic_mdi_car_door.xml

```text
9: android:fillColor="#FFFFFFFF"
```

### app/src/main/res/drawable/ic_mdi_car_seat.xml

```text
9: android:fillColor="#FFFFFFFF"
```

### app/src/main/res/drawable/ic_mdi_car_sports.xml

```text
7: android:fillColor="#FFFFFFFF"
```

### app/src/main/res/drawable/ic_mdi_car_sunroof.xml

```text
9: android:fillColor="#FFFFFFFF"
```

### app/src/main/res/drawable/ic_mdi_car_trunk.xml

```text
9: android:fillColor="#FFFFFFFF"
```

### app/src/main/res/drawable/ic_mdi_cellphone.xml

```text
7: android:fillColor="#FFFFFFFF"
```

### app/src/main/res/drawable/ic_mdi_check.xml

```text
7: android:fillColor="#FFFFFFFF"
```

### app/src/main/res/drawable/ic_mdi_chevron_double_left.xml

```text
7: android:fillColor="#FFFFFFFF"
```

### app/src/main/res/drawable/ic_mdi_chevron_up.xml

```text
8: android:fillColor="#FFFFFFFF"
```

### app/src/main/res/drawable/ic_mdi_close.xml

```text
7: android:fillColor="#FFFFFFFF"
```

### app/src/main/res/drawable/ic_mdi_cog.xml

```text
7: android:fillColor="#FFFFFFFF"
```

### app/src/main/res/drawable/ic_mdi_delete.xml

```text
7: android:fillColor="#FFFFFFFF"
```

### app/src/main/res/drawable/ic_mdi_dots_vertical.xml

```text
7: android:fillColor="#FFFFFFFF"
```

### app/src/main/res/drawable/ic_mdi_download.xml

```text
7: android:fillColor="#FFFFFFFF"
```

### app/src/main/res/drawable/ic_mdi_email_outline.xml

```text
7: android:fillColor="#FFFFFFFF"
```

### app/src/main/res/drawable/ic_mdi_equalizer.xml

```text
7: android:fillColor="#FFFFFFFF"
```

### app/src/main/res/drawable/ic_mdi_fan.xml

```text
8: android:fillColor="#FFFFFFFF"
```

### app/src/main/res/drawable/ic_mdi_folder.xml

```text
7: android:fillColor="#FFFFFFFF"
```

### app/src/main/res/drawable/ic_mdi_fullscreen_exit.xml

```text
7: android:fillColor="#FFFFFFFF"
```

### app/src/main/res/drawable/ic_mdi_inbox_outline.xml

```text
7: android:fillColor="#FFFFFFFF"
```

### app/src/main/res/drawable/ic_mdi_leaf.xml

```text
7: android:fillColor="#FFFFFFFF"
```

### app/src/main/res/drawable/ic_mdi_lightning_bolt.xml

```text
7: android:fillColor="#FFFFFFFF"
```

### app/src/main/res/drawable/ic_mdi_loading.xml

```text
7: android:fillColor="#FFFFFFFF"
```

### app/src/main/res/drawable/ic_mdi_map.xml

```text
7: android:fillColor="#FFFFFFFF"
```

### app/src/main/res/drawable/ic_mdi_map_marker.xml

```text
7: android:fillColor="#FFFFFFFF"
```

### app/src/main/res/drawable/ic_mdi_music_note.xml

```text
7: android:fillColor="#FFFFFFFF"
```

### app/src/main/res/drawable/ic_mdi_package_variant.xml

```text
7: android:fillColor="#FFFFFFFF"
```

### app/src/main/res/drawable/ic_mdi_pause.xml

```text
7: android:fillColor="#FFFFFFFF"
```

### app/src/main/res/drawable/ic_mdi_play.xml

```text
7: android:fillColor="#FFFFFFFF"
```

### app/src/main/res/drawable/ic_mdi_plus.xml

```text
8: android:fillColor="#FFFFFFFF"
```

### app/src/main/res/drawable/ic_mdi_radiobox_blank.xml

```text
7: android:fillColor="#FFFFFFFF"
```

### app/src/main/res/drawable/ic_mdi_refresh.xml

```text
7: android:fillColor="#FFFFFFFF"
```

### app/src/main/res/drawable/ic_mdi_restart.xml

```text
7: android:fillColor="#FFFFFFFF"
```

### app/src/main/res/drawable/ic_mdi_road_variant.xml

```text
7: android:fillColor="#FFFFFFFF"
```

### app/src/main/res/drawable/ic_mdi_shield_car.xml

```text
7: android:fillColor="#FFFFFFFF"
```

### app/src/main/res/drawable/ic_mdi_skip_next.xml

```text
8: android:fillColor="#FFFFFFFF"
```

### app/src/main/res/drawable/ic_mdi_skip_previous.xml

```text
8: android:fillColor="#FFFFFFFF"
```

### app/src/main/res/drawable/ic_mdi_snowflake.xml

```text
7: android:fillColor="#FFFFFFFF"
```

### app/src/main/res/drawable/ic_mdi_speedometer.xml

```text
7: android:fillColor="#FFFFFFFF"
```

### app/src/main/res/drawable/ic_mdi_steering.xml

```text
8: android:fillColor="#FFFFFFFF"
```

### app/src/main/res/drawable/ic_mdi_terrain.xml

```text
7: android:fillColor="#FFFFFFFF"
```

### app/src/main/res/drawable/ic_mdi_thermometer.xml

```text
8: android:fillColor="#FFFFFFFF"
```

### app/src/main/res/drawable/ic_mdi_timer_sand.xml

```text
7: android:fillColor="#FFFFFFFF"
```

### app/src/main/res/drawable/ic_mdi_upload.xml

```text
7: android:fillColor="#FFFFFFFF"
```

### app/src/main/res/drawable/ic_mdi_volume_high.xml

```text
7: android:fillColor="#FFFFFFFF"
```

### app/src/main/res/drawable/ic_mdi_volume_minus.xml

```text
9: android:fillColor="#FFFFFFFF"
```

### app/src/main/res/drawable/ic_mdi_volume_mute.xml

```text
9: android:fillColor="#FFFFFFFF"
```

### app/src/main/res/drawable/ic_mdi_volume_plus.xml

```text
9: android:fillColor="#FFFFFFFF"
```

### app/src/main/res/drawable/ic_mdi_waves.xml

```text
7: android:fillColor="#FFFFFFFF"
```

### app/src/main/res/drawable/ic_mdi_web.xml

```text
7: android:fillColor="#FFFFFFFF"
```

### app/src/main/res/drawable/ic_mdi_wifi.xml

```text
7: android:fillColor="#FFFFFFFF"
```

### app/src/main/res/drawable/ic_swipe_arrow_up.xml

```text
8: android:fillColor="#FFFFFFFF"
```

### app/src/main/res/drawable/ic_vehicle_back.xml

```text
7: android:fillColor="#FFFFFFFF"
```

### app/src/main/res/drawable/ic_vehicle_lock.xml

```text
7: android:fillColor="#FFFFFFFF"
```

### app/src/main/res/drawable/ic_vehicle_trunk.xml

```text
7: android:fillColor="#FFFFFFFF"
```

## XML yüzey ve tema tarifleri

Shape/selector/gradient/layer-list ve tema özellikleri birebir kaynak metnidir. Renk ve boyut referansları design.md tablolarında çözülür.

### app/src/main/res/drawable/bg_card_glass.xml

```xml
<?xml version="1.0" encoding="utf-8"?>
<shape xmlns:android="http://schemas.android.com/apk/res/android"
    android:shape="rectangle">
    <solid android:color="@color/glassFill" />
    <corners android:radius="@dimen/oem_card_radius" />
    <stroke
        android:width="1dp"
        android:color="@color/glassStroke" />
</shape>
```

### app/src/main/res/drawable/bg_choice_control.xml

```xml
<selector xmlns:android="http://schemas.android.com/apk/res/android">
    <item android:state_checked="true" android:drawable="@drawable/ic_choice_selected" />
    <item android:drawable="@drawable/ic_choice_empty" />
</selector>
```

### app/src/main/res/drawable/bg_cluster_nav_card.xml

```xml
<?xml version="1.0" encoding="utf-8"?>
<shape xmlns:android="http://schemas.android.com/apk/res/android"
    android:shape="rectangle">
    <solid android:color="@color/cluster_nav_card_fill" />
    <corners android:radius="@dimen/oem_card_radius" />
    <stroke
        android:width="1.5dp"
        android:color="@color/cluster_nav_card_stroke" />
</shape>
```

### app/src/main/res/drawable/bg_cluster_nav_eta.xml

```xml
<?xml version="1.0" encoding="utf-8"?>
<shape xmlns:android="http://schemas.android.com/apk/res/android"
    android:shape="rectangle">
    <solid android:color="@color/cluster_nav_eta_fill" />
    <corners android:radius="18dp" />
    <stroke
        android:width="1dp"
        android:color="@color/cluster_nav_eta_stroke" />
</shape>
```

### app/src/main/res/drawable/bg_cluster_nav_maneuver.xml

```xml
<?xml version="1.0" encoding="utf-8"?>
<shape xmlns:android="http://schemas.android.com/apk/res/android"
    android:shape="rectangle">
    <gradient
        android:angle="135"
        android:endColor="@color/cluster_nav_maneuver_end"
        android:startColor="@color/cluster_nav_maneuver_start"
        android:type="linear" />
    <corners android:radius="18dp" />
</shape>
```

### app/src/main/res/drawable/bg_global_wifi_toast_error.xml

```xml
<?xml version="1.0" encoding="utf-8"?>
<shape xmlns:android="http://schemas.android.com/apk/res/android"
    android:shape="rectangle">
    <gradient
        android:angle="135"
        android:centerColor="@color/wifi_toast_error_grad_center"
        android:endColor="@color/wifi_toast_error_grad_end"
        android:startColor="@color/wifi_toast_error_grad_start"
        android:type="linear" />
    <corners android:radius="@dimen/oem_card_radius" />
    <stroke
        android:width="1dp"
        android:color="@color/wifi_toast_error_stroke" />
</shape>
```

### app/src/main/res/drawable/bg_global_wifi_toast_halo_error.xml

```xml
<?xml version="1.0" encoding="utf-8"?>
<shape xmlns:android="http://schemas.android.com/apk/res/android"
    android:shape="oval">
    <gradient
        android:angle="90"
        android:centerColor="@color/wifi_toast_halo_error_grad_center"
        android:endColor="@color/wifi_toast_halo_error_grad_end"
        android:startColor="@color/wifi_toast_halo_error_grad_start"
        android:type="linear" />
    <stroke
        android:width="1dp"
        android:color="@color/wifi_toast_halo_error_stroke" />
</shape>
```

### app/src/main/res/drawable/bg_global_wifi_toast_halo_ok.xml

```xml
<?xml version="1.0" encoding="utf-8"?>
<shape xmlns:android="http://schemas.android.com/apk/res/android"
    android:shape="oval">
    <gradient
        android:angle="90"
        android:centerColor="@color/wifi_toast_halo_ok_grad_center"
        android:endColor="@color/wifi_toast_halo_ok_grad_end"
        android:startColor="@color/wifi_toast_halo_ok_grad_start"
        android:type="linear" />
    <stroke
        android:width="1dp"
        android:color="@color/wifi_toast_halo_ok_stroke" />
</shape>
```

### app/src/main/res/drawable/bg_global_wifi_toast_halo_preparing.xml

```xml
<?xml version="1.0" encoding="utf-8"?>
<shape xmlns:android="http://schemas.android.com/apk/res/android"
    android:shape="oval">
    <gradient
        android:angle="90"
        android:centerColor="@color/wifi_toast_halo_preparing_grad_center"
        android:endColor="@color/wifi_toast_halo_preparing_grad_end"
        android:startColor="@color/wifi_toast_halo_preparing_grad_start"
        android:type="linear" />
    <stroke
        android:width="1dp"
        android:color="@color/wifi_toast_halo_preparing_stroke" />
</shape>
```

### app/src/main/res/drawable/bg_global_wifi_toast_preparing.xml

```xml
<?xml version="1.0" encoding="utf-8"?>
<shape xmlns:android="http://schemas.android.com/apk/res/android"
    android:shape="rectangle">
    <gradient
        android:angle="135"
        android:centerColor="@color/wifi_toast_preparing_grad_center"
        android:endColor="@color/wifi_toast_preparing_grad_end"
        android:startColor="@color/wifi_toast_preparing_grad_start"
        android:type="linear" />
    <corners android:radius="@dimen/oem_card_radius" />
    <stroke
        android:width="1dp"
        android:color="@color/wifi_toast_preparing_stroke" />
</shape>
```

### app/src/main/res/drawable/bg_global_wifi_toast_success.xml

```xml
<?xml version="1.0" encoding="utf-8"?>
<shape xmlns:android="http://schemas.android.com/apk/res/android"
    android:shape="rectangle">
    <gradient
        android:angle="135"
        android:centerColor="@color/wifi_toast_success_grad_center"
        android:endColor="@color/wifi_toast_success_grad_end"
        android:startColor="@color/wifi_toast_success_grad_start"
        android:type="linear" />
    <corners android:radius="@dimen/oem_card_radius" />
    <stroke
        android:width="1dp"
        android:color="@color/wifi_toast_success_stroke" />
</shape>
```

### app/src/main/res/drawable/bg_launch_splash.xml

```xml
<?xml version="1.0" encoding="utf-8"?>
<!-- Process start windowBackground — soft çok renkli gradient -->
<shape xmlns:android="http://schemas.android.com/apk/res/android"
    android:shape="rectangle">
    <gradient
        android:type="linear"
        android:angle="135"
        android:startColor="@color/boot_splash_grad_a"
        android:centerColor="@color/boot_splash_grad_c"
        android:endColor="@color/boot_splash_grad_d" />
</shape>
```

### app/src/main/res/drawable/bg_launcher_dashboard.xml

```xml
<?xml version="1.0" encoding="utf-8"?>
<shape xmlns:android="http://schemas.android.com/apk/res/android"
    android:shape="rectangle">
    <gradient
        android:type="linear"
        android:angle="135"
        android:startColor="#EEF3FA"
        android:centerColor="#E2EBF6"
        android:endColor="#D4E0F0" />
    <corners android:radius="@dimen/oem_card_radius" />
    <stroke
        android:width="1dp"
        android:color="@color/glassStroke" />
</shape>
```

### app/src/main/res/drawable/bg_launcher_fuel_progress.xml

```xml
<?xml version="1.0" encoding="utf-8"?>
<layer-list xmlns:android="http://schemas.android.com/apk/res/android">
    <item android:id="@android:id/background">
        <shape android:shape="rectangle">
            <corners android:radius="4dp" />
            <solid android:color="@color/glassStroke" />
        </shape>
    </item>
    <item android:id="@android:id/progress">
        <clip>
            <shape android:shape="rectangle">
                <corners android:radius="4dp" />
                <solid android:color="@color/accentHighlight" />
            </shape>
        </clip>
    </item>
</layer-list>
```

### app/src/main/res/drawable/bg_launcher_icon_slot.xml

```xml
<?xml version="1.0" encoding="utf-8"?>
<shape xmlns:android="http://schemas.android.com/apk/res/android"
    android:shape="rectangle">
    <solid android:color="@color/surfaceCard" />
    <corners android:radius="20dp" />
    <stroke
        android:width="1dp"
        android:color="@color/outlineSubtle" />
</shape>
```

### app/src/main/res/drawable/bg_launcher_icon_slot_exit.xml

```xml
<?xml version="1.0" encoding="utf-8"?>
<shape xmlns:android="http://schemas.android.com/apk/res/android"
    android:shape="rectangle">
    <solid android:color="@color/buttonDestructiveBg" />
    <corners android:radius="20dp" />
    <stroke
        android:width="1dp"
        android:color="#44A34040" />
</shape>
```

### app/src/main/res/drawable/bg_launcher_icon_slot_shortcut.xml

```xml
<?xml version="1.0" encoding="utf-8"?>
<shape xmlns:android="http://schemas.android.com/apk/res/android"
    android:shape="rectangle">
    <gradient
        android:type="linear"
        android:angle="135"
        android:startColor="@color/nav_pill_selected_start"
        android:endColor="@color/assistCardActive" />
    <corners android:radius="20dp" />
    <stroke
        android:width="1dp"
        android:color="@color/glassStroke" />
</shape>
```

### app/src/main/res/drawable/bg_launcher_quick_exit_circle.xml

```xml
<?xml version="1.0" encoding="utf-8"?>
<shape xmlns:android="http://schemas.android.com/apk/res/android"
    android:shape="oval">
    <solid android:color="@color/buttonDestructiveBg" />
    <stroke
        android:width="1dp"
        android:color="#44A34040" />
</shape>
```

### app/src/main/res/drawable/bg_launcher_vehicle_glow.xml

```xml
<?xml version="1.0" encoding="utf-8"?>
<shape xmlns:android="http://schemas.android.com/apk/res/android"
    android:shape="oval">
    <gradient
        android:type="radial"
        android:gradientRadius="120dp"
        android:centerX="0.5"
        android:centerY="0.5"
        android:startColor="#554586C5"
        android:endColor="#004586C5" />
</shape>
```

### app/src/main/res/drawable/bg_nav_pill_selected.xml

```xml
<?xml version="1.0" encoding="utf-8"?>
<shape xmlns:android="http://schemas.android.com/apk/res/android"
    android:shape="rectangle">
    <gradient
        android:type="linear"
        android:angle="0"
        android:startColor="@color/nav_pill_selected_start"
        android:endColor="@color/nav_pill_selected_end" />
    <corners android:radius="@dimen/oem_nav_pill_radius" />
    <stroke
        android:width="1dp"
        android:color="@color/glassStroke" />
</shape>
```

### app/src/main/res/drawable/bg_rail_panel.xml

```xml
<?xml version="1.0" encoding="utf-8"?>
<shape xmlns:android="http://schemas.android.com/apk/res/android"
    android:shape="rectangle">
    <solid android:color="@color/surfaceRail" />
</shape>
```

### app/src/main/res/drawable/bg_root_gradient.xml

```xml
<?xml version="1.0" encoding="utf-8"?>
<shape xmlns:android="http://schemas.android.com/apk/res/android"
    android:shape="rectangle">
    <gradient
        android:type="linear"
        android:angle="135"
        android:startColor="@color/root_gradient_start"
        android:centerColor="@color/root_gradient_center"
        android:endColor="@color/root_gradient_end" />
</shape>
```

### app/src/main/res/drawable/bg_segment_thumb.xml

```xml
<?xml version="1.0" encoding="utf-8"?>
<shape xmlns:android="http://schemas.android.com/apk/res/android"
    android:shape="rectangle">
    <solid android:color="@color/oemAccent" />
    <corners android:radius="@dimen/oem_segment_thumb_radius" />
</shape>
```

### app/src/main/res/drawable/bg_segment_track.xml

```xml
<?xml version="1.0" encoding="utf-8"?>
<shape xmlns:android="http://schemas.android.com/apk/res/android"
    android:shape="rectangle">
    <solid android:color="@color/segmentTrackFill" />
    <corners android:radius="@dimen/oem_segment_track_radius" />
</shape>
```

### app/src/main/res/drawable/bg_topbar_divider.xml

```xml
<?xml version="1.0" encoding="utf-8"?>
<shape xmlns:android="http://schemas.android.com/apk/res/android"
    android:shape="rectangle">
    <solid android:color="@color/outlineSubtle" />
</shape>
```

### app/src/main/res/drawable/bg_vehicle_quick_control.xml

```xml
<?xml version="1.0" encoding="utf-8"?>
<selector xmlns:android="http://schemas.android.com/apk/res/android">
    <item android:state_activated="true">
        <shape android:shape="rectangle">
            <gradient
                android:angle="135"
                android:endColor="@color/assistCardActive"
                android:startColor="@color/nav_pill_selected_start"
                android:type="linear" />
            <corners android:radius="16dp" />
            <stroke
                android:width="1dp"
                android:color="@color/assistStrokeActive" />
        </shape>
    </item>
    <item android:state_pressed="true">
        <shape android:shape="rectangle">
            <solid android:color="@color/surfaceCardInner" />
            <corners android:radius="16dp" />
            <stroke
                android:width="1dp"
                android:color="@color/glassStroke" />
        </shape>
    </item>
    <item>
        <shape android:shape="rectangle">
            <solid android:color="@color/glassFill" />
            <corners android:radius="16dp" />
            <stroke
                android:width="1dp"
                android:color="@color/glassStroke" />
        </shape>
    </item>
</selector>
```

### app/src/main/res/drawable/ic_choice_empty.xml

```xml
<vector xmlns:android="http://schemas.android.com/apk/res/android" android:width="32dp" android:height="32dp" android:viewportWidth="32" android:viewportHeight="32">
    <path android:fillColor="#00000000" android:strokeColor="#FF000000" android:strokeWidth="2.5" android:pathData="M16,3 A13,13 0,1 0,16,29 A13,13 0,1 0,16,3" />
</vector>
```

### app/src/main/res/drawable/ic_choice_selected.xml

```xml
<vector xmlns:android="http://schemas.android.com/apk/res/android" android:width="32dp" android:height="32dp" android:viewportWidth="32" android:viewportHeight="32">
    <path android:fillColor="#00000000" android:strokeColor="#FF000000" android:strokeWidth="2.5" android:pathData="M16,3 A13,13 0,1 0,16,29 A13,13 0,1 0,16,3" />
    <path android:fillColor="#FF000000" android:pathData="M16,9 A7,7 0,1 0,16,23 A7,7 0,1 0,16,9" />
</vector>
```

### app/src/main/res/values/themes.xml

```xml
<?xml version="1.0" encoding="utf-8"?>
<resources>
    <!-- Shared: Material DayNight + app colors (status bar flag in Theme.MapControl) -->
    <style name="Theme.MapControl.Base" parent="Theme.MaterialComponents.DayNight.NoActionBar">
        <item name="windowActionBar">false</item>
        <item name="windowNoTitle">true</item>
        <item name="android:windowBackground">@color/backgroundColor</item>
        <item name="android:windowFullscreen">false</item>
        <item name="android:windowContentOverlay">@null</item>

        <item name="android:statusBarColor">@color/backgroundColor</item>
        <item name="android:navigationBarColor">@color/backgroundColor</item>

        <item name="colorPrimary">@color/primaryColor</item>
        <item name="colorPrimaryDark">@color/primaryDarkColor</item>
        <item name="colorAccent">@color/accentColor</item>

        <item name="android:textColorPrimary">@color/textPrimary</item>
        <item name="android:textColorSecondary">@color/textSecondary</item>

        <item name="alertDialogTheme">@style/CustomAlertDialog</item>
        <item name="android:alertDialogTheme">@style/CustomAlertDialog</item>
    </style>

    <!-- Light UI: dark icons in status bar -->
    <style name="Theme.MapControl" parent="Theme.MapControl.Base">
        <item name="android:windowLightStatusBar">true</item>
    </style>

    <!-- Cold start: process window siyah olmasın; MainActivity sonra Theme.MapControl'e geçer -->
    <style name="Theme.MapControl.Splash" parent="Theme.MapControl">
        <item name="android:windowBackground">@drawable/bg_launch_splash</item>
        <item name="android:statusBarColor">@color/boot_splash_gradient_mid</item>
        <item name="android:navigationBarColor">@color/boot_splash_gradient_mid</item>
        <item name="android:windowFullscreen">false</item>
    </style>

    <!-- Legacy name: follows system day/night via Theme.MapControl parent -->
    <style name="Theme.AppCompat.Light.NoActionBar" parent="Theme.MapControl" />

    <style name="CustomAlertDialog" parent="Theme.MaterialComponents.DayNight.Dialog.Alert">
        <item name="android:windowBackground">@color/surfaceColor</item>
        <item name="android:background">@color/surfaceColor</item>
        <item name="colorAccent">@color/accentColor</item>
        <item name="android:textColorPrimary">@color/textPrimary</item>
        <item name="android:textColorSecondary">@color/textSecondary</item>
        <item name="buttonBarPositiveButtonStyle">@style/CustomAlertDialogButton</item>
        <item name="buttonBarNegativeButtonStyle">@style/CustomAlertDialogButton</item>
        <item name="buttonBarNeutralButtonStyle">@style/CustomAlertDialogButton</item>
        <item name="android:buttonBarButtonStyle">@style/CustomAlertDialogButton</item>
        <item name="android:buttonBarPositiveButtonStyle">@style/CustomAlertDialogButton</item>
        <item name="android:buttonBarNegativeButtonStyle">@style/CustomAlertDialogButton</item>
        <item name="android:buttonBarNeutralButtonStyle">@style/CustomAlertDialogButton</item>
        <item name="android:colorBackground">@color/surfaceColor</item>
    </style>

    <style name="CustomAlertDialogButton" parent="Widget.AppCompat.Button.ButtonBar.AlertDialog">
        <item name="android:textColor">@color/accentColor</item>
        <item name="android:textSize">16sp</item>
        <item name="android:textStyle">bold</item>
    </style>
</resources>
```

### app/src/main/res/values-night/themes.xml

```xml
<?xml version="1.0" encoding="utf-8"?>
<resources>
    <!-- Dark UI: light icons in status bar -->
    <style name="Theme.MapControl" parent="Theme.MapControl.Base">
        <item name="android:windowLightStatusBar">false</item>
    </style>

    <style name="Theme.MapControl.Splash" parent="Theme.MapControl">
        <item name="android:windowBackground">@drawable/bg_launch_splash</item>
        <item name="android:statusBarColor">@color/boot_splash_gradient_mid</item>
        <item name="android:navigationBarColor">@color/boot_splash_gradient_mid</item>
    </style>

    <style name="Theme.AppCompat.Light.NoActionBar" parent="Theme.MapControl" />
</resources>
```

## Programatik stil, tipografi, layout ve hareket ifadeleri

Aşağıdaki satırlar doğrudan kaynak ifadeleridir; çok satırlı çağrılarda sonraki satırlar aynı ifadeye aittir. 12 satırdan uzun/closure içeren ifadeler kaynak dosyasından tamamlanır. Tekil satırda olmayan değerler, fonksiyon ve kaynak referansları bağlamdan hesaplanır. Bu çıkarım erişim bölgesi tespiti için elle kod okumasının yerine geçmez.

### app/src/main/assets/welcome.html

```text
10:             font-family: -apple-system, BlinkMacSystemFont, 'Segoe UI', Roboto, Oxygen, Ubuntu, Cantarell, sans-serif;
23:             border-radius: 24px;
25:             box-shadow: 0 20px 60px rgba(0, 0, 0, 0.5);
29:             animation: fadeInUp 0.6s cubic-bezier(0.4, 0, 0.2, 1);
46:             font-size: 72px;
49:             animation: float 3s ease-in-out infinite;
56:             font-size: 32px;
63:             font-size: 16px;
82:             border-radius: 20px;
84:             transition: all 0.4s cubic-bezier(0.4, 0, 0.2, 1);
91:             box-shadow: 0 8px 24px rgba(115, 178, 245, 0.2);
97:             box-shadow: 0 12px 32px rgba(115, 178, 245, 0.3);
104:             font-size: 48px;
107:             transition: transform 0.3s ease;
113:             font-size: 18px;
119:             font-size: 14px;
135:             border-radius: 16px;
137:             animation: slideDown 0.3s ease;
143:             font-size: 15px;
150:             font-size: 13px;
159:             animation: slideDown 0.3s ease;
165:             font-size: 14px;
176:             border-radius: 10px;
183:             border-radius: 10px;
184:             transition: width 0.4s cubic-bezier(0.4, 0, 0.2, 1);
196:             animation: shimmer 1.5s infinite;
207:             font-size: 17px;
209:             border-radius: 14px;
212:             transition: all 0.3s cubic-bezier(0.4, 0, 0.2, 1);
213:             box-shadow: 0 4px 16px rgba(115, 178, 245, 0.4);
225:             border-radius: 50%;
228:             transition: width 0.6s, height 0.6s;
236:             box-shadow: 0 8px 24px rgba(115, 178, 245, 0.5);
244:             box-shadow: none;
254:             border-radius: 24px;
255:             font-size: 14px;
262:             border-radius: 50%;
264:             animation: pulse 2s infinite;
283:             border-radius: 16px;
287:             box-shadow: 0 8px 32px rgba(0, 0, 0, 0.4);
292:             animation: toastSlideIn 0.4s cubic-bezier(0.4, 0, 0.2, 1);
326:             font-size: 24px;
331:             font-size: 14px;
332:             line-height: 1.5;
338:             font-size: 20px;
341:             transition: opacity 0.2s;
364:             animation: fadeIn 0.3s ease;
375:             border-radius: 24px;
379:             box-shadow: 0 20px 60px rgba(0, 0, 0, 0.5);
381:             animation: modalSlideUp 0.4s cubic-bezier(0.4, 0, 0.2, 1);
398:             font-size: 24px;
404:             font-size: 15px;
406:             line-height: 1.6;
416:             border-radius: 12px;
417:             font-size: 15px;
420:             transition: all 0.3s cubic-bezier(0.4, 0, 0.2, 1);
426:             box-shadow: 0 4px 12px rgba(115, 178, 245, 0.3);
430:             box-shadow: 0 6px 20px rgba(115, 178, 245, 0.4);
450:             font-size: 20px;
459:             border-radius: 12px;
461:             font-size: 14px;
462:             transition: all 0.3s cubic-bezier(0.4, 0, 0.2, 1);
488:             border-radius: 3px;
499:             border-radius: 16px;
501:             transition: all 0.3s cubic-bezier(0.4, 0, 0.2, 1);
514:             font-size: 15px;
521:             font-size: 13px;
534:             border-radius: 12px;
536:             font-size: 14px;
537:             transition: all 0.3s cubic-bezier(0.4, 0, 0.2, 1);
545:             box-shadow: 0 4px 12px rgba(244, 67, 54, 0.3);
555:             border-radius: 12px;
557:             font-size: 14px;
558:             transition: all 0.3s cubic-bezier(0.4, 0, 0.2, 1);
566:             box-shadow: 0 4px 12px rgba(76, 175, 80, 0.3);
575:             font-size: 15px;
578:             font-size: 48px;
586:             font-size: 15px;
594:             border-radius: 50%;
595:             animation: spin 0.8s linear infinite;
603:         .app-brand .brand-eyebrow { font-size: 12px; color: #9CA3AF; letter-spacing: 0.12em; text-transform: uppercase; }
604:         .app-brand .brand-name { font-size: 20px; font-weight: 600; color: #E6E6E6; margin-top: 4px; }
609:             min-width: 42px; padding: 6px 10px; border-radius: 10px; cursor: pointer;
611:             color: #9CA3AF; font-size: 12px; font-weight: 700;
619:             display: inline-flex; align-items: center; gap: 8px; padding: 12px 18px; border-radius: 14px;
621:             background: rgba(30, 38, 48, 0.75); color: #E6E6E6; font-size: 14px; font-weight: 600;
622:             cursor: pointer; transition: background 0.2s, border-color 0.2s, color 0.2s;
628:         .web-panel { display: none; animation: fadeInUp 0.4s ease; }
634:             width: 100%; box-sizing: border-box; padding: 14px 16px; border-radius: 14px;
636:             font-size: 14px; min-height: 48px; font-family: inherit;
639:         .help-text { font-size: 12px; color: #9CA3AF; margin-top: 8px; line-height: 1.45; }
640:         .lock-badge { font-size: 12px; color: #6B8EBF; margin-left: 6px; }
643:             font-size: 13px; color: #E6E6E6; cursor: pointer; user-select: none;
646:         .inline-check span { line-height: 1.4; }
1355:                                 fileItem.style.transition = 'all 0.3s ease';
1461:                 toast.style.animation = 'toastSlideIn 0.3s reverse';
1561:                                 appItem.style.transition = 'all 0.3s ease';
```

### app/src/main/java/com/mapcontrol/manager/DisplayRunningAppsOverlay.java

```text
152:         scrim.setBackgroundColor(UiStyles.color(themedContext, R.color.modal_overlay_scrim));
156:         card.setOrientation(LinearLayout.VERTICAL);
160:         card.setElevation(12 * density);
162:         card.setPadding(cardPad, cardPad, cardPad, cardPad);
165:         titleRow.setOrientation(LinearLayout.HORIZONTAL);
166:         titleRow.setGravity(Gravity.CENTER_VERTICAL);
170:         titleView.setTextSize(20);
171:         titleView.setTypeface(null, Typeface.BOLD);
172:         titleView.setTextColor(UiStyles.color(themedContext, R.color.textPrimary));
173:         LinearLayout.LayoutParams titleLp = new LinearLayout.LayoutParams(
174:                 0, ViewGroup.LayoutParams.WRAP_CONTENT, 1f);
179:         btnClose.setTextSize(22);
180:         btnClose.setTextColor(UiStyles.color(themedContext, R.color.textHint));
181:         btnClose.setPadding((int) (8 * density), 0, 0, 0);
183:         titleRow.addView(btnClose, ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT);
184:         card.addView(titleRow, new LinearLayout.LayoutParams(
185:                 ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT));
189:         subtitle.setTextSize(13);
190:         subtitle.setTextColor(UiStyles.color(themedContext, R.color.textHint));
191:         LinearLayout.LayoutParams subLp = new LinearLayout.LayoutParams(
192:                 ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT);
216:         LinearLayout.LayoutParams listLp = new LinearLayout.LayoutParams(
217:                 ViewGroup.LayoutParams.MATCH_PARENT, 0, 1f);
223:         FrameLayout.LayoutParams cardLp = new FrameLayout.LayoutParams(cardMaxW, cardMaxH);
229:                 ? WindowManager.LayoutParams.TYPE_APPLICATION_OVERLAY
230:                 : WindowManager.LayoutParams.TYPE_PHONE;
231:         WindowManager.LayoutParams rootParams = new WindowManager.LayoutParams(
232:                 WindowManager.LayoutParams.MATCH_PARENT,
233:                 WindowManager.LayoutParams.MATCH_PARENT,
234:                 type,
235:                 WindowManager.LayoutParams.FLAG_LAYOUT_IN_SCREEN
236:                         | WindowManager.LayoutParams.FLAG_ALT_FOCUSABLE_IM,
237:                 PixelFormat.TRANSLUCENT);
357:                 row.setOrientation(LinearLayout.HORIZONTAL);
358:                 row.setPadding(p, (int) (10 * d), p, (int) (10 * d));
359:                 row.setGravity(Gravity.CENTER_VERTICAL);
362:                 LinearLayout.LayoutParams iconLp = new LinearLayout.LayoutParams(iconPx, iconPx);
363:                 iconLp.setMargins(0, 0, (int) (12 * d), 0);
366:                 texts.setOrientation(LinearLayout.VERTICAL);
368:                 t1.setTextSize(16);
369:                 t1.setTextColor(UiStyles.color(themedContext, R.color.textPrimary));
371:                 t2.setTextSize(12);
372:                 t2.setTextColor(UiStyles.color(themedContext, R.color.textHint));
375:                 row.addView(texts, new LinearLayout.LayoutParams(0, ViewGroup.LayoutParams.WRAP_CONTENT, 1f));
```

### app/src/main/java/com/mapcontrol/manager/FloatingBackButtonManager.java

```text
3: import android.animation.AnimatorSet;
4: import android.animation.ObjectAnimator;
12: import android.graphics.drawable.RippleDrawable;
24: import android.view.animation.AccelerateInterpolator;
25: import android.view.animation.DecelerateInterpolator;
26: import android.view.animation.OvershootInterpolator;
67:     private WindowManager.LayoutParams params;
200:     private static LinearLayout.LayoutParams newCellLp(int cellSide, int gap) {
201:         LinearLayout.LayoutParams lp = new LinearLayout.LayoutParams(cellSide, cellSide);
203:         lp.setMargins(gap / 2, 0, gap / 2, 0);
252:         if (bg instanceof RippleDrawable) {
253:             int[] loc = new int[2];
371:         params.width = WindowManager.LayoutParams.WRAP_CONTENT;
372:         params.height = WindowManager.LayoutParams.WRAP_CONTENT;
382:         b.setMinimumWidth(0);
383:         b.setMinimumHeight(0);
385:         b.setPadding(pad, pad, pad, pad);
428:         menuContainer.setAlpha(0f);
429:         menuContainer.setScaleX(0.82f);
436:                     .setDuration(320)
437:                     .setInterpolator(new OvershootInterpolator(1.08f))
438:                     .withEndAction(this::remeasureAndUpdateWindow)
439:                     .start();
451:                 .setDuration(240)
452:                 .setInterpolator(new AccelerateInterpolator())
453:                 .withEndAction(() -> {
454:                     menuContainer.setVisibility(View.GONE);
455:                     menuContainer.setAlpha(1f);
456:                     menuContainer.setScaleX(1f);
578:         cardInner.setOrientation(LinearLayout.HORIZONTAL);
579:         cardInner.setPadding(padH, padV, padH, padV);
582:         cardInner.setAlpha(0.96f);
586:         LinearLayout.LayoutParams backLp = new LinearLayout.LayoutParams(cellSide, cellSide);
591:         menuContainer.setOrientation(LinearLayout.HORIZONTAL);
611:         qaActionsRow.setOrientation(LinearLayout.HORIZONTAL);
624:         LinearLayout.LayoutParams menuLp = new LinearLayout.LayoutParams(
625:                 LinearLayout.LayoutParams.WRAP_CONTENT, LinearLayout.LayoutParams.WRAP_CONTENT);
629:         root.addView(cardInner, new FrameLayout.LayoutParams(
630:                 FrameLayout.LayoutParams.WRAP_CONTENT, FrameLayout.LayoutParams.WRAP_CONTENT));
653:                 ? WindowManager.LayoutParams.TYPE_APPLICATION_OVERLAY
654:                 : WindowManager.LayoutParams.TYPE_PHONE;
656:         params = new WindowManager.LayoutParams(
657:                 WindowManager.LayoutParams.WRAP_CONTENT,
658:                 WindowManager.LayoutParams.WRAP_CONTENT,
659:                 type,
660:                 WindowManager.LayoutParams.FLAG_NOT_FOCUSABLE,
661:                 PixelFormat.TRANSLUCENT);
854:         animatorSet.setDuration(200);
855:         animatorSet.setInterpolator(new DecelerateInterpolator());
```

### app/src/main/java/com/mapcontrol/manager/FloatingOverlayBarSpec.java

```text
9: import android.graphics.drawable.RippleDrawable;
17: import androidx.core.graphics.ColorUtils;
27:     public static final float ROW_TEXT_SIZE_SP = 12f;
29:     public static final float OVERLAY_ROW_TEXT_SIZE_SP = ROW_TEXT_SIZE_SP * 2f;
30:     public static final float ROW_ICON_SIZE_DP = 20f;
31:     public static final float ROW_INNER_PAD_DP = 4f;
32:     public static final float BAR_CARD_PAD_H_DP = 5f;
33:     public static final float BAR_CARD_PAD_V_DP = 4f;
34:     public static final float BAR_CORNER_DP = 10f;
35:     public static final float BAR_COLUMN_GAP_DP = 2f;
37:     public static final float CELL_SCALE = 1.7f;
38:     public static final float ELEVATION_BACK_DP = 2f;
82:         content.setColor(fill);
83:         content.setCornerRadius(BAR_CORNER_DP * d);
87:         mask.setCornerRadius(BAR_CORNER_DP * d);
88:         mask.setColor(Color.WHITE);
91:         int rippleAlpha = ColorUtils.setAlphaComponent(rippleRgb, 0x55);
92:         RippleDrawable ripple =
93:                 new RippleDrawable(ColorStateList.valueOf(rippleAlpha), content, mask);
95:         v.setAlpha(0.96f);
97:             v.setStateListAnimator(null);
98:             v.setElevation(ELEVATION_BACK_DP * d);
107:         b.setMinWidth(0);
108:         b.setMinHeight(0);
110:         b.setAlpha(0.96f);
112:             b.setStateListAnimator(null);
113:             b.setElevation(ELEVATION_BACK_DP * d);
143:         p.setTypeface(Typeface.create(Typeface.DEFAULT, Typeface.BOLD));
144:         p.setTextSize(TypedValue.applyDimension(
145:                 TypedValue.COMPLEX_UNIT_SP, textSizeSp, ctx.getResources().getDisplayMetrics()));
```

### app/src/main/java/com/mapcontrol/manager/FloatingProjectionControlsManager.java

```text
49:     private WindowManager.LayoutParams params;
224:         root.setBackgroundColor(Color.TRANSPARENT);
225:         root.setAlpha(1f);
230:                 b.setTextColor(textC);
233:                         DrawableCompat.setTint(dr2, textC);
267:         b.setIncludeFontPadding(false);
268:         b.setTextSize(TypedValue.COMPLEX_UNIT_SP, FloatingOverlayBarSpec.ROW_TEXT_SIZE_SP);
269:         b.setTypeface(null, Typeface.BOLD);
270:         b.setTextColor(UiStyles.color(context, R.color.textPrimary));
271:         b.setMaxLines(2);
272:         b.setGravity(Gravity.CENTER);
279:             DrawableCompat.setTint(dr, UiStyles.color(context, R.color.textPrimary));
283:         b.setPadding(p, p, p, p);
```

### app/src/main/java/com/mapcontrol/manager/FloatingQuickActionsManager.java

```text
48:     private WindowManager.LayoutParams params;
172:             DrawableCompat.setTint(d, UiStyles.color(context, R.color.textPrimary));
175:         btnToggle.setIncludeFontPadding(false);
176:         btnToggle.setTextSize(TypedValue.COMPLEX_UNIT_SP, FloatingOverlayBarSpec.ROW_TEXT_SIZE_SP);
177:         btnToggle.setTypeface(null, Typeface.BOLD);
178:         btnToggle.setTextColor(UiStyles.color(context, R.color.textPrimary));
187:         btnToggle.setPadding(p, p, p, p);
188:         btnToggle.setMaxLines(2);
189:         btnToggle.setGravity(Gravity.CENTER);
287:             quickInner.setAlpha(0.95f);
303:         b.setGravity(Gravity.CENTER);
304:         b.setTextColor(UiStyles.color(context, R.color.textPrimary));
305:         b.setIncludeFontPadding(false);
306:         b.setTextSize(TypedValue.COMPLEX_UNIT_SP, FloatingOverlayBarSpec.ROW_TEXT_SIZE_SP);
313:             DrawableCompat.setTint(d, UiStyles.color(context, R.color.textPrimary));
318:         b.setPadding(pad, pad, pad, pad);
351:         b.setGravity(Gravity.CENTER);
353:         b.setIncludeFontPadding(false);
354:         b.setTextSize(TypedValue.COMPLEX_UNIT_SP, FloatingOverlayBarSpec.ROW_TEXT_SIZE_SP);
355:         b.setTypeface(null, Typeface.BOLD);
356:         b.setTextColor(UiStyles.color(context, R.color.textPrimary));
357:         b.setMaxLines(2);
364:             DrawableCompat.setTint(d, UiStyles.color(context, R.color.textPrimary));
369:         b.setPadding(p, p, p, p);
```

### app/src/main/java/com/mapcontrol/manager/ProjectionVDBusTargetPickerManager.java

```text
3: import android.animation.Animator;
4: import android.animation.AnimatorListenerAdapter;
5: import android.animation.ValueAnimator;
65:     private WindowManager.LayoutParams rootParams;
216:         scrimView.setBackgroundColor(UiStyles.color(themedContext, R.color.modal_overlay_scrim));
220:         cardView.setOrientation(LinearLayout.VERTICAL);
224:         cardView.setElevation(12 * density);
226:         cardView.setPadding(cardPad, cardPad, cardPad, cardPad);
229:         titleRow.setOrientation(LinearLayout.HORIZONTAL);
230:         titleRow.setGravity(Gravity.CENTER_VERTICAL);
234:         titleView.setTextSize(20);
235:         titleView.setTypeface(null, Typeface.BOLD);
236:         titleView.setTextColor(UiStyles.color(themedContext, R.color.textPrimary));
237:         LinearLayout.LayoutParams titleLp = new LinearLayout.LayoutParams(
238:                 0, ViewGroup.LayoutParams.WRAP_CONTENT, 1f);
243:         closeBtn.setTextSize(22);
244:         closeBtn.setTextColor(UiStyles.color(themedContext, R.color.textHint));
245:         closeBtn.setPadding((int) (8 * density), 0, 0, 0);
247:         titleRow.addView(closeBtn, ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT);
248:         cardView.addView(titleRow, new LinearLayout.LayoutParams(
249:                 ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT));
253:         subtitleView.setTextSize(13);
254:         subtitleView.setTextColor(UiStyles.color(themedContext, R.color.textHint));
255:         LinearLayout.LayoutParams subLp = new LinearLayout.LayoutParams(
256:                 ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT);
274:         LinearLayout.LayoutParams listLp = new LinearLayout.LayoutParams(
275:                 ViewGroup.LayoutParams.MATCH_PARENT, 0, 1f);
282:         footer.setOrientation(LinearLayout.HORIZONTAL);
283:         footer.setGravity(Gravity.END);
286:         clearBtn.setTextSize(15);
287:         clearBtn.setTypeface(null, Typeface.BOLD);
288:         clearBtn.setTextColor(UiStyles.color(themedContext, R.color.textLoading));
290:         clearBtn.setPadding(fp, fp, fp, fp);
293:         LinearLayout.LayoutParams footLp = new LinearLayout.LayoutParams(
294:                 ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT);
298:         FrameLayout.LayoutParams cardLp = new FrameLayout.LayoutParams(cardMaxW, cardMaxH);
304:                 ? WindowManager.LayoutParams.TYPE_APPLICATION_OVERLAY
305:                 : WindowManager.LayoutParams.TYPE_PHONE;
306:         rootParams = new WindowManager.LayoutParams(
307:                 WindowManager.LayoutParams.MATCH_PARENT,
308:                 WindowManager.LayoutParams.MATCH_PARENT,
309:                 type,
310:                 WindowManager.LayoutParams.FLAG_LAYOUT_IN_SCREEN
311:                         | WindowManager.LayoutParams.FLAG_NOT_FOCUSABLE
312:                         | WindowManager.LayoutParams.FLAG_ALT_FOCUSABLE_IM,
313:                 PixelFormat.TRANSLUCENT);
315:         rootParams.softInputMode = WindowManager.LayoutParams.SOFT_INPUT_ADJUST_NOTHING;
353:                 fillAnimator.setDuration(FILL_MS);
362:                     public void onAnimationEnd(Animator animation) {
363:                         completeSelectionAndOpenCluster();
459:             scrimView.setBackgroundColor(scrim);
465:             titleView.setTextColor(textPri);
468:             subtitleView.setTextColor(textHint);
471:             closeBtn.setTextColor(textHint);
474:             clearBtn.setTextColor(textLoad);
528:         outer.setOrientation(LinearLayout.VERTICAL);
530:         rowInner.setOrientation(LinearLayout.HORIZONTAL);
531:         rowInner.setPadding(p, (int) (10 * d), p, (int) (10 * d));
532:         rowInner.setGravity(Gravity.CENTER_VERTICAL);
535:         LinearLayout.LayoutParams iconLp = new LinearLayout.LayoutParams(iconPx, iconPx);
536:         iconLp.setMargins(0, 0, (int) (12 * d), 0);
539:         texts.setOrientation(LinearLayout.VERTICAL);
541:         t1.setTextSize(16);
542:         t1.setTextColor(UiStyles.color(themedContext, R.color.textPrimary));
544:         t2.setTextSize(12);
545:         t2.setTextColor(UiStyles.color(themedContext, R.color.textHint));
548:         rowInner.addView(texts, new LinearLayout.LayoutParams(0, ViewGroup.LayoutParams.WRAP_CONTENT, 1f));
549:         outer.addView(rowInner, new LinearLayout.LayoutParams(
550:                 ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT));
553:         LinearLayout.LayoutParams barLp = new LinearLayout.LayoutParams(
554:                 ViewGroup.LayoutParams.MATCH_PARENT, Math.max(2, (int) (3 * d)));
603:             v.t1.setTextColor(tPri);
604:             v.t2.setTextColor(tHi);
608:                 bg.setColor(UiStyles.color(appContext, R.color.surfaceCardInner));
609:                 bg.setCornerRadius(12f * d);
610:                 bg.setStroke(strokePx, tPri);
612:                 bg.setColor(0x00000000);
613:                 bg.setCornerRadius(12f * d);
```

### app/src/main/java/com/mapcontrol/manager/TargetAppPickerOverlay.java

```text
48:     private WindowManager.LayoutParams rootParams;
106:         scrim.setBackgroundColor(UiStyles.color(themedContext, R.color.modal_overlay_scrim));
110:         card.setOrientation(LinearLayout.VERTICAL);
114:         card.setElevation(12 * density);
116:         card.setPadding(cardPad, cardPad, cardPad, cardPad);
119:         titleRow.setOrientation(LinearLayout.HORIZONTAL);
120:         titleRow.setGravity(Gravity.CENTER_VERTICAL);
124:         title.setTextSize(20);
125:         title.setTypeface(null, Typeface.BOLD);
126:         title.setTextColor(UiStyles.color(themedContext, R.color.textPrimary));
127:         LinearLayout.LayoutParams titleLp = new LinearLayout.LayoutParams(
128:                 0, ViewGroup.LayoutParams.WRAP_CONTENT, 1f);
133:         btnClose.setTextSize(22);
134:         btnClose.setTextColor(UiStyles.color(themedContext, R.color.textHint));
135:         btnClose.setPadding((int) (8 * density), 0, 0, 0);
137:         titleRow.addView(btnClose, ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT);
138:         card.addView(titleRow, new LinearLayout.LayoutParams(
139:                 ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT));
143:         subtitle.setTextSize(13);
144:         subtitle.setTextColor(UiStyles.color(themedContext, R.color.textHint));
145:         LinearLayout.LayoutParams subLp = new LinearLayout.LayoutParams(
146:                 ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT);
152:         searchInput.setSingleLine(true);
153:         searchInput.setTextSize(30);
154:         searchInput.setTextColor(UiStyles.color(themedContext, R.color.textPrimary));
159:         searchInput.setPadding(sp, sp, sp, sp);
160:         LinearLayout.LayoutParams searchLp = new LinearLayout.LayoutParams(
161:                 ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT);
176:         LinearLayout.LayoutParams listLp = new LinearLayout.LayoutParams(
177:                 ViewGroup.LayoutParams.MATCH_PARENT, 0, 1f);
184:         footer.setOrientation(LinearLayout.HORIZONTAL);
185:         footer.setGravity(Gravity.END);
188:         btnClear.setTextSize(30);
189:         btnClear.setTypeface(null, Typeface.BOLD);
190:         btnClear.setTextColor(UiStyles.color(themedContext, R.color.textLoading));
192:         btnClear.setPadding(fp, fp, fp, fp);
195:         LinearLayout.LayoutParams footLp = new LinearLayout.LayoutParams(
196:                 ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT);
215:         FrameLayout.LayoutParams cardLp = new FrameLayout.LayoutParams(cardMaxW, cardMaxH);
221:                 ? WindowManager.LayoutParams.TYPE_APPLICATION_OVERLAY
222:                 : WindowManager.LayoutParams.TYPE_PHONE;
223:         rootParams = new WindowManager.LayoutParams(
224:                 WindowManager.LayoutParams.MATCH_PARENT,
225:                 WindowManager.LayoutParams.MATCH_PARENT,
226:                 type,
227:                 WindowManager.LayoutParams.FLAG_LAYOUT_IN_SCREEN
228:                         | WindowManager.LayoutParams.FLAG_ALT_FOCUSABLE_IM,
229:                 PixelFormat.TRANSLUCENT);
231:         rootParams.softInputMode = WindowManager.LayoutParams.SOFT_INPUT_ADJUST_RESIZE;
327:                 row.setOrientation(LinearLayout.HORIZONTAL);
328:                 row.setPadding(p, (int) (10 * d), p, (int) (10 * d));
329:                 row.setGravity(Gravity.CENTER_VERTICAL);
332:                 LinearLayout.LayoutParams iconLp = new LinearLayout.LayoutParams(iconPx, iconPx);
333:                 iconLp.setMargins(0, 0, (int) (12 * d), 0);
336:                 texts.setOrientation(LinearLayout.VERTICAL);
338:                 t1.setTextSize(16);
339:                 t1.setTextColor(UiStyles.color(themedContext, R.color.textPrimary));
341:                 t2.setTextSize(12);
342:                 t2.setTextColor(UiStyles.color(themedContext, R.color.textHint));
345:                 row.addView(texts, new LinearLayout.LayoutParams(0, ViewGroup.LayoutParams.WRAP_CONTENT, 1f));
```

### app/src/main/java/com/mapcontrol/manager/WebServerManager.java

```text
327:                    "<body style=\"font-family: sans-serif; background: " + bg + "; color: " + fg + "; padding: 40px; text-align: center;\">" +
```

### app/src/main/java/com/mapcontrol/nav/ClusterNavOverlayOffsets.java

```text
21:     private static final int OFFSET_RANGE_FALLBACK_DP = 80;
227:         card.setScaleX(scale);
228:         card.setScaleY(scale);
229:         card.setTranslationX(Math.round(offsetXDp * density));
230:         card.setTranslationY(Math.round(offsetYDp * density));
```

### app/src/main/java/com/mapcontrol/nav/GoogleMapsClusterNavOverlay.java

```text
37:     private static final int CARD_WIDTH_DP = 188;
38:     private static final int TOP_MARGIN_DP = 110;
39:     private static final int CENTER_GAP_DP = 300;
63:     private FrameLayout.LayoutParams maneuverCardLp;
64:     private FrameLayout.LayoutParams etaCardLp;
70:     private WindowManager.LayoutParams overlayLayoutParams;
160:      * overlay aynı {@link WindowManager.LayoutParams#TYPE_APPLICATION_OVERLAY} tipinde
161:      * kalırsa eski ekleme sırasında altta kalır. remove + add ile en üste alınır.
162:      */
163:     public void raiseToTopIfShowing() {
164:         mainHandler.post(() -> raiseToTopOnMain(true));
375:             overlayLayoutParams = new WindowManager.LayoutParams(
376:                     WindowManager.LayoutParams.MATCH_PARENT,
377:                     WindowManager.LayoutParams.MATCH_PARENT,
378:                     WindowManager.LayoutParams.TYPE_APPLICATION_OVERLAY,
379:                     WindowManager.LayoutParams.FLAG_NOT_FOCUSABLE
380:                             | WindowManager.LayoutParams.FLAG_NOT_TOUCHABLE
381:                             | WindowManager.LayoutParams.FLAG_NOT_TOUCH_MODAL
382:                             | WindowManager.LayoutParams.FLAG_LAYOUT_IN_SCREEN,
383:                     PixelFormat.TRANSLUCENT);
411:         nextStreetView.setMaxLines(2);
412:         LinearLayout.LayoutParams streetLp = wrapLp();
417:         maneuverStatusView.setMaxLines(2);
418:         LinearLayout.LayoutParams statusLp = wrapLp();
436:         etaStatusView.setTextColor(UiStyles.color(ctx, R.color.textMuted));
437:         etaStatusView.setMaxLines(2);
438:         LinearLayout.LayoutParams etaStatusLp = wrapLp();
448:         maneuverCardLp = new FrameLayout.LayoutParams(
449:                 cardWidthPx,
450:                 FrameLayout.LayoutParams.WRAP_CONTENT);
456:         etaCardLp = new FrameLayout.LayoutParams(
457:                 cardWidthPx,
458:                 FrameLayout.LayoutParams.WRAP_CONTENT);
481:         row.setOrientation(LinearLayout.HORIZONTAL);
482:         row.setGravity(Gravity.CENTER_VERTICAL);
486:         labelView.setTextColor(UiStyles.color(ctx, R.color.textMuted));
487:         row.addView(labelView, new LinearLayout.LayoutParams(
488:                 dp(ctx, 52),
489:                 LinearLayout.LayoutParams.WRAP_CONTENT));
492:         row.addView(valueView, new LinearLayout.LayoutParams(
493:                 LinearLayout.LayoutParams.WRAP_CONTENT,
494:                 LinearLayout.LayoutParams.WRAP_CONTENT));
496:         LinearLayout.LayoutParams rowLp = wrapLp();
504:         tv.setTextSize(TypedValue.COMPLEX_UNIT_SP, sp);
506:             tv.setTypeface(null, Typeface.BOLD);
507:             tv.setTextColor(UiStyles.color(ctx, R.color.cluster_nav_maneuver_text));
509:             tv.setTextColor(UiStyles.color(ctx, R.color.cluster_nav_maneuver_subtext));
516:         tv.setTextSize(TypedValue.COMPLEX_UNIT_SP, sp);
518:             tv.setTypeface(null, Typeface.BOLD);
520:         tv.setTextColor(UiStyles.color(ctx, R.color.textPrimary));
524:     private static LinearLayout.LayoutParams wrapLp() {
525:         return new LinearLayout.LayoutParams(
526:                 LinearLayout.LayoutParams.WRAP_CONTENT,
527:                 LinearLayout.LayoutParams.WRAP_CONTENT);
530:     private static LinearLayout.LayoutParams cardLp(Context ctx) {
531:         return new LinearLayout.LayoutParams(
532:                 dp(ctx, CARD_WIDTH_DP),
533:                 LinearLayout.LayoutParams.WRAP_CONTENT);
538:         card.setOrientation(LinearLayout.VERTICAL);
539:         card.setGravity(Gravity.CENTER_HORIZONTAL);
543:         card.setPadding(padH, padV, padH, padV);
549:         card.setOrientation(LinearLayout.VERTICAL);
552:         card.setPadding(pad, pad, pad, pad);
```

### app/src/main/java/com/mapcontrol/nav/YandexClusterNavOverlay.java

```text
37:     private static final int TOP_MARGIN_DP = 80;
38:     private static final int CENTER_GAP_DP = 300;
61:     private FrameLayout.LayoutParams topRowLp;
63:     private WindowManager.LayoutParams overlayLayoutParams;
154:      * overlay aynı {@link WindowManager.LayoutParams#TYPE_APPLICATION_OVERLAY} tipinde
155:      * kalırsa eski ekleme sırasında altta kalır. remove + add ile en üste alınır.
156:      */
157:     public void raiseToTopIfShowing() {
158:         mainHandler.post(() -> raiseToTopOnMain(true));
285:             topRow.setTranslationX(0f);
286:             topRow.setTranslationY(0f);
354:             overlayLayoutParams = new WindowManager.LayoutParams(
355:                     WindowManager.LayoutParams.MATCH_PARENT,
356:                     WindowManager.LayoutParams.MATCH_PARENT,
357:                     WindowManager.LayoutParams.TYPE_APPLICATION_OVERLAY,
358:                     WindowManager.LayoutParams.FLAG_NOT_FOCUSABLE
359:                             | WindowManager.LayoutParams.FLAG_NOT_TOUCHABLE
360:                             | WindowManager.LayoutParams.FLAG_NOT_TOUCH_MODAL
361:                             | WindowManager.LayoutParams.FLAG_LAYOUT_IN_SCREEN,
362:                     PixelFormat.TRANSLUCENT);
376:         topRow.setOrientation(LinearLayout.HORIZONTAL);
377:         topRow.setGravity(Gravity.CENTER_HORIZONTAL);
386:         nextStreetView.setMaxLines(2);
387:         LinearLayout.LayoutParams streetLp = wrapLp();
392:         maneuverStatusView.setMaxLines(2);
393:         LinearLayout.LayoutParams statusLp = wrapLp();
400:         topRow.addView(centerGap, new LinearLayout.LayoutParams(
401:                 dp(ctx, CENTER_GAP_DP),
402:                 LinearLayout.LayoutParams.WRAP_CONTENT));
418:         etaStatusView.setTextColor(UiStyles.color(ctx, R.color.textMuted));
419:         etaStatusView.setMaxLines(2);
420:         LinearLayout.LayoutParams etaStatusLp = wrapLp();
427:         topRowLp = new FrameLayout.LayoutParams(
428:                 FrameLayout.LayoutParams.WRAP_CONTENT,
429:                 FrameLayout.LayoutParams.WRAP_CONTENT);
451:         row.setOrientation(LinearLayout.HORIZONTAL);
452:         row.setGravity(Gravity.CENTER_VERTICAL);
456:         labelView.setTextColor(UiStyles.color(ctx, R.color.textMuted));
457:         row.addView(labelView, new LinearLayout.LayoutParams(
458:                 dp(ctx, 52),
459:                 LinearLayout.LayoutParams.WRAP_CONTENT));
462:         row.addView(valueView, new LinearLayout.LayoutParams(
463:                 LinearLayout.LayoutParams.WRAP_CONTENT,
464:                 LinearLayout.LayoutParams.WRAP_CONTENT));
466:         LinearLayout.LayoutParams rowLp = wrapLp();
474:         tv.setTextSize(TypedValue.COMPLEX_UNIT_SP, sp);
476:             tv.setTypeface(null, Typeface.BOLD);
477:             tv.setTextColor(UiStyles.color(ctx, R.color.cluster_nav_maneuver_text));
479:             tv.setTextColor(UiStyles.color(ctx, R.color.cluster_nav_maneuver_subtext));
486:         tv.setTextSize(TypedValue.COMPLEX_UNIT_SP, sp);
488:             tv.setTypeface(null, Typeface.BOLD);
490:         tv.setTextColor(UiStyles.color(ctx, R.color.textPrimary));
494:     private static LinearLayout.LayoutParams wrapLp() {
495:         return new LinearLayout.LayoutParams(
496:                 LinearLayout.LayoutParams.WRAP_CONTENT,
497:                 LinearLayout.LayoutParams.WRAP_CONTENT);
500:     private static LinearLayout.LayoutParams cardLp(Context ctx) {
501:         return new LinearLayout.LayoutParams(
502:                 dp(ctx, 136),
503:                 LinearLayout.LayoutParams.WRAP_CONTENT);
508:         card.setOrientation(LinearLayout.VERTICAL);
509:         card.setGravity(Gravity.CENTER_HORIZONTAL);
513:         card.setPadding(padH, padV, padH, padV);
519:         card.setOrientation(LinearLayout.VERTICAL);
522:         card.setPadding(pad, pad, pad, pad);
```

### app/src/main/java/com/mapcontrol/ui/activity/ClusterVDBusTestActivity.java

```text
46:         root.setOrientation(LinearLayout.VERTICAL);
47:         root.setBackgroundColor(UiStyles.color(this, R.color.backgroundPage));
50:         backBar.setOrientation(LinearLayout.HORIZONTAL);
51:         backBar.setBackgroundColor(UiStyles.color(this, R.color.surfaceBar));
52:         backBar.setPadding(16, 16, 16, 16);
53:         backBar.setGravity(Gravity.CENTER_VERTICAL);
56:         btnBack.setTextColor(UiStyles.color(this, R.color.textPrimary));
64:         title.setTextSize(TypedValue.COMPLEX_UNIT_SP, 16);
65:         title.setTextColor(UiStyles.color(this, R.color.textPrimary));
66:         title.setPadding(16, 12, 16, 4);
70:         tvOut.setTextSize(TypedValue.COMPLEX_UNIT_SP, 11);
71:         tvOut.setTextColor(UiStyles.color(this, R.color.textMuted));
72:         tvOut.setPadding(16, 0, 16, 8);
96:         scroll.setFillViewport(true);
98:         col.setOrientation(LinearLayout.VERTICAL);
99:         col.setPadding(16, 8, 16, 24);
113:         temperatureTestButton.setTextColor(UiStyles.color(this, R.color.textPrimary));
114:         temperatureTestButton.setTextSize(TypedValue.COMPLEX_UNIT_SP, 14);
124:         LinearLayout.LayoutParams temperatureButtonLp = new LinearLayout.LayoutParams(
125:                 LinearLayout.LayoutParams.MATCH_PARENT,
126:                 LinearLayout.LayoutParams.WRAP_CONTENT);
127:         temperatureButtonLp.setMargins(0, 0, 0, gap);
132:         automationDiagnosticsButton.setTextColor(UiStyles.color(this, R.color.textPrimary));
133:         automationDiagnosticsButton.setTextSize(TypedValue.COMPLEX_UNIT_SP, 14);
141:         LinearLayout.LayoutParams automationButtonLp = new LinearLayout.LayoutParams(
142:                 LinearLayout.LayoutParams.MATCH_PARENT,
143:                 LinearLayout.LayoutParams.WRAP_CONTENT);
144:         automationButtonLp.setMargins(0, 0, 0, gap);
149:         liveAutomationCaptureButton.setTextColor(UiStyles.color(this, R.color.textPrimary));
150:         liveAutomationCaptureButton.setTextSize(TypedValue.COMPLEX_UNIT_SP, 14);
167:         LinearLayout.LayoutParams liveCaptureLp = new LinearLayout.LayoutParams(
168:                 LinearLayout.LayoutParams.MATCH_PARENT,
169:                 LinearLayout.LayoutParams.WRAP_CONTENT);
170:         liveCaptureLp.setMargins(0, 0, 0, gap);
175:         seatMemoryTestButton.setTextColor(UiStyles.color(this, R.color.textPrimary));
176:         seatMemoryTestButton.setTextSize(TypedValue.COMPLEX_UNIT_SP, 14);
193:         LinearLayout.LayoutParams seatMemoryLp = new LinearLayout.LayoutParams(
194:                 LinearLayout.LayoutParams.MATCH_PARENT,
195:                 LinearLayout.LayoutParams.WRAP_CONTENT);
196:         seatMemoryLp.setMargins(0, 0, 0, gap);
201:         vehicleVdbusDiscoveryButton.setTextColor(UiStyles.color(this, R.color.textPrimary));
202:         vehicleVdbusDiscoveryButton.setTextSize(TypedValue.COMPLEX_UNIT_SP, 14);
219:         LinearLayout.LayoutParams vdbusDiscoveryLp = new LinearLayout.LayoutParams(
220:                 LinearLayout.LayoutParams.MATCH_PARENT,
221:                 LinearLayout.LayoutParams.WRAP_CONTENT);
222:         vdbusDiscoveryLp.setMargins(0, 0, 0, gap);
226:         LinearLayout.LayoutParams scrollLp = new LinearLayout.LayoutParams(
227:                 LinearLayout.LayoutParams.MATCH_PARENT,
228:                 0, 1f);
316:         b.setTextColor(UiStyles.color(this, R.color.textPrimary));
317:         b.setTextSize(TypedValue.COMPLEX_UNIT_SP, 14);
320:         LinearLayout.LayoutParams lp = new LinearLayout.LayoutParams(
321:                 LinearLayout.LayoutParams.MATCH_PARENT,
322:                 LinearLayout.LayoutParams.WRAP_CONTENT);
323:         lp.setMargins(0, 0, 0, gapBottom);
```

### app/src/main/java/com/mapcontrol/ui/activity/DmsClusterPreview.java

```text
69:         root.setBackgroundColor(0x00000000);
73:         image.setBackgroundColor(0xFF000000);
75:         root.addView(image, new FrameLayout.LayoutParams(previewW, previewH, Gravity.CENTER));
78:         promptColumn.setOrientation(LinearLayout.VERTICAL);
79:         promptColumn.setGravity(Gravity.CENTER_HORIZONTAL);
80:         FrameLayout.LayoutParams columnLp = new FrameLayout.LayoutParams(
81:                 FrameLayout.LayoutParams.MATCH_PARENT,
82:                 FrameLayout.LayoutParams.WRAP_CONTENT,
83:                 Gravity.CENTER);
85:         promptColumn.setTranslationY(-screenH * 0.16f);
88:         prompt.setTextColor(0xFFFFFFFF);
89:         prompt.setTextSize(32);
90:         prompt.setTypeface(null, Typeface.BOLD);
91:         prompt.setGravity(Gravity.CENTER);
92:         prompt.setShadowLayer(8f * density, 0f, 2f * density, 0xCC000000);
94:         promptColumn.addView(prompt, new LinearLayout.LayoutParams(
95:                 LinearLayout.LayoutParams.MATCH_PARENT,
96:                 LinearLayout.LayoutParams.WRAP_CONTENT));
99:         instruction.setTextColor(0xFFE8EEF6);
100:         instruction.setTextSize(22);
101:         instruction.setGravity(Gravity.CENTER);
102:         instruction.setShadowLayer(6f * density, 0f, 2f * density, 0xCC000000);
104:         LinearLayout.LayoutParams instructionLp = new LinearLayout.LayoutParams(
105:                 LinearLayout.LayoutParams.MATCH_PARENT,
106:                 LinearLayout.LayoutParams.WRAP_CONTENT);
110:         WindowManager.LayoutParams params = new WindowManager.LayoutParams(
111:                 WindowManager.LayoutParams.MATCH_PARENT,
112:                 WindowManager.LayoutParams.MATCH_PARENT,
113:                 WindowManager.LayoutParams.TYPE_APPLICATION_OVERLAY,
114:                 WindowManager.LayoutParams.FLAG_NOT_FOCUSABLE
115:                         | WindowManager.LayoutParams.FLAG_NOT_TOUCHABLE
116:                         | WindowManager.LayoutParams.FLAG_LAYOUT_IN_SCREEN,
117:                 PixelFormat.TRANSLUCENT);
```

### app/src/main/java/com/mapcontrol/ui/activity/DmsFaceProfileActivity.java

```text
199:         root.setOrientation(LinearLayout.VERTICAL);
200:         root.setPadding(dp(18), dp(14), dp(18), dp(16));
205:         topBar.setOrientation(LinearLayout.HORIZONTAL);
206:         topBar.setGravity(Gravity.CENTER_VERTICAL);
207:         LinearLayout.LayoutParams topBarLp = new LinearLayout.LayoutParams(
208:                 LinearLayout.LayoutParams.MATCH_PARENT, LinearLayout.LayoutParams.WRAP_CONTENT);
214:         backBtn.setTextColor(0xFFFFFFFF);
215:         backBtn.setTextSize(14);
217:         backBtn.setPadding(dp(18), dp(8), dp(18), dp(8));
220:         topBar.addView(backBtn, new LinearLayout.LayoutParams(
221:                 LinearLayout.LayoutParams.WRAP_CONTENT, dp(42)));
225:         titleView.setTextColor(textPrimary);
226:         titleView.setTextSize(20);
227:         titleView.setTypeface(null, Typeface.BOLD);
228:         LinearLayout.LayoutParams titleLp = new LinearLayout.LayoutParams(
229:                 0, LinearLayout.LayoutParams.WRAP_CONTENT, 1f);
235:         clearButton.setTextColor(0xFFFFFFFF);
236:         clearButton.setTextSize(13);
238:         clearButton.setPadding(dp(16), dp(8), dp(16), dp(8));
241:         topBar.addView(clearButton, new LinearLayout.LayoutParams(
242:                 LinearLayout.LayoutParams.WRAP_CONTENT, dp(40)));
249:         contentRow.setOrientation(portrait ? LinearLayout.VERTICAL : LinearLayout.HORIZONTAL);
250:         root.addView(contentRow, new LinearLayout.LayoutParams(
251:                 LinearLayout.LayoutParams.MATCH_PARENT, 0, 1f));
255:         leftCard.setOrientation(LinearLayout.VERTICAL);
256:         leftCard.setPadding(dp(14), dp(14), dp(14), dp(14));
258:         LinearLayout.LayoutParams leftLp = portrait
259:                 ? new LinearLayout.LayoutParams(
260:                         LinearLayout.LayoutParams.MATCH_PARENT, 0, 1.15f)
261:                 : new LinearLayout.LayoutParams(
262:                         0, LinearLayout.LayoutParams.MATCH_PARENT, 1.15f);
271:         cameraFrame.setBackgroundColor(0xFF000000);
272:         LinearLayout.LayoutParams camFrameLp = new LinearLayout.LayoutParams(
273:                 LinearLayout.LayoutParams.MATCH_PARENT, 0, 1f);
278:         cameraFrame.addView(preview, new FrameLayout.LayoutParams(
279:                 FrameLayout.LayoutParams.MATCH_PARENT,
280:                 FrameLayout.LayoutParams.MATCH_PARENT));
287:         cameraFrame.addView(tonePreview, new FrameLayout.LayoutParams(
288:                 FrameLayout.LayoutParams.MATCH_PARENT,
289:                 FrameLayout.LayoutParams.MATCH_PARENT));
295:         LinearLayout.LayoutParams progLp = new LinearLayout.LayoutParams(
296:                 LinearLayout.LayoutParams.MATCH_PARENT, dp(10));
301:         status.setTextColor(textPrimary);
302:         status.setTextSize(15);
303:         status.setTypeface(null, Typeface.BOLD);
304:         status.setGravity(Gravity.CENTER);
305:         status.setPadding(dp(10), dp(10), dp(10), dp(6));
306:         leftCard.addView(status, new LinearLayout.LayoutParams(
307:                 LinearLayout.LayoutParams.MATCH_PARENT, LinearLayout.LayoutParams.WRAP_CONTENT));
311:         rightScroll.setFillViewport(true);
312:         LinearLayout.LayoutParams rightLp = portrait
313:                 ? new LinearLayout.LayoutParams(
314:                         LinearLayout.LayoutParams.MATCH_PARENT, 0, 1f)
315:                 : new LinearLayout.LayoutParams(
316:                         0, LinearLayout.LayoutParams.MATCH_PARENT, 1f);
320:         rightColumn.setOrientation(LinearLayout.VERTICAL);
321:         rightScroll.addView(rightColumn, new FrameLayout.LayoutParams(
322:                 FrameLayout.LayoutParams.MATCH_PARENT, FrameLayout.LayoutParams.WRAP_CONTENT));
326:         enrollCard.setOrientation(LinearLayout.VERTICAL);
327:         enrollCard.setPadding(dp(16), dp(14), dp(16), dp(16));
329:         LinearLayout.LayoutParams enrollCardLp = new LinearLayout.LayoutParams(
330:                 LinearLayout.LayoutParams.MATCH_PARENT, LinearLayout.LayoutParams.WRAP_CONTENT);
336:         enrollHeader.setTextColor(textPrimary);
337:         enrollHeader.setTextSize(16);
338:         enrollHeader.setTypeface(null, Typeface.BOLD);
345:         enrollSub.setTextColor(textSecondary);
346:         enrollSub.setTextSize(13);
347:         LinearLayout.LayoutParams subLp = new LinearLayout.LayoutParams(
348:                 LinearLayout.LayoutParams.MATCH_PARENT, LinearLayout.LayoutParams.WRAP_CONTENT);
354:         nameInput.setSingleLine(true);
357:         nameInput.setTextColor(0xFFFFFFFF);
358:         nameInput.setTextSize(15);
359:         nameInput.setPadding(dp(14), dp(8), dp(14), dp(8));
361:         enrollCard.addView(nameInput, new LinearLayout.LayoutParams(
362:                 LinearLayout.LayoutParams.MATCH_PARENT, dp(44)));
366:         memoryLabel.setTextColor(textPrimary);
367:         memoryLabel.setTextSize(13);
368:         memoryLabel.setTypeface(null, Typeface.BOLD);
369:         LinearLayout.LayoutParams memLabelLp = new LinearLayout.LayoutParams(
370:                 LinearLayout.LayoutParams.MATCH_PARENT, LinearLayout.LayoutParams.WRAP_CONTENT);
376:         memoryRow.setOrientation(LinearLayout.HORIZONTAL);
377:         enrollCard.addView(memoryRow, new LinearLayout.LayoutParams(
378:                 LinearLayout.LayoutParams.MATCH_PARENT, dp(42)));
389:             btn.setTextSize(13);
395:             LinearLayout.LayoutParams btnLp = new LinearLayout.LayoutParams(
396:                     0, LinearLayout.LayoutParams.MATCH_PARENT, 1f);
406:         enrollButton.setTextColor(0xFFFFFFFF);
407:         enrollButton.setTextSize(15);
408:         enrollButton.setTypeface(null, Typeface.BOLD);
413:         LinearLayout.LayoutParams enrollBtnLp = new LinearLayout.LayoutParams(
414:                 LinearLayout.LayoutParams.MATCH_PARENT, dp(46));
420:         listCard.setOrientation(LinearLayout.VERTICAL);
421:         listCard.setPadding(dp(16), dp(14), dp(16), dp(14));
423:         rightColumn.addView(listCard, new LinearLayout.LayoutParams(
424:                 LinearLayout.LayoutParams.MATCH_PARENT, LinearLayout.LayoutParams.WRAP_CONTENT));
428:         listHeader.setTextColor(textPrimary);
429:         listHeader.setTextSize(16);
430:         listHeader.setTypeface(null, Typeface.BOLD);
434:         profilesListContainer.setOrientation(LinearLayout.VERTICAL);
435:         LinearLayout.LayoutParams listContainerLp = new LinearLayout.LayoutParams(
436:                 LinearLayout.LayoutParams.MATCH_PARENT, LinearLayout.LayoutParams.WRAP_CONTENT);
451:             btn.setTextColor(0xFFFFFFFF);
452:             btn.setTypeface(null, isSelected ? Typeface.BOLD : Typeface.NORMAL);
486:             empty.setTextColor(textSecondary);
487:             empty.setTextSize(14);
488:             empty.setPadding(0, dp(6), 0, dp(4));
496:             row.setOrientation(LinearLayout.HORIZONTAL);
497:             row.setGravity(Gravity.CENTER_VERTICAL);
498:             row.setPadding(dp(12), dp(10), dp(12), dp(10));
500:             LinearLayout.LayoutParams rowLp = new LinearLayout.LayoutParams(
501:                     LinearLayout.LayoutParams.MATCH_PARENT, LinearLayout.LayoutParams.WRAP_CONTENT);
507:             infoCol.setOrientation(LinearLayout.VERTICAL);
510:             nameTv.setTextColor(0xFFFFFFFF);
511:             nameTv.setTextSize(15);
512:             nameTv.setTypeface(null, Typeface.BOLD);
518:                 subTv.setTextColor(0xFF60A5FA);
519:                 subTv.setTextSize(13);
523:             row.addView(infoCol, new LinearLayout.LayoutParams(
524:                     0, LinearLayout.LayoutParams.WRAP_CONTENT, 1f));
532:                 memBtn.setTextColor(0xFFFFFFFF);
533:                 memBtn.setTextSize(12);
534:                 memBtn.setPadding(0, 0, 0, 0);
536:                 memBtn.setTypeface(null, active ? Typeface.BOLD : Typeface.NORMAL);
540:                 LinearLayout.LayoutParams memLp = new LinearLayout.LayoutParams(dp(34), dp(34));
549:             delBtn.setTextColor(0xFFFFFFFF);
550:             delBtn.setTextSize(12);
551:             delBtn.setPadding(dp(12), dp(4), dp(12), dp(4));
554:             row.addView(delBtn, new LinearLayout.LayoutParams(
555:                     LinearLayout.LayoutParams.WRAP_CONTENT, dp(34)));
```

### app/src/main/java/com/mapcontrol/ui/activity/MainActivity.java

```text
2: import android.animation.Animator;
3: import android.animation.AnimatorListenerAdapter;
4: import android.animation.ValueAnimator;
204:     private LinearLayout.LayoutParams mainContentParams;
427:         mainContent.setOrientation(LinearLayout.VERTICAL);
428:         mainContent.setBackgroundColor(UiStyles.color(this, R.color.transparent));
461:         mainContent.addView(topBar, new LinearLayout.LayoutParams(
462:                 LinearLayout.LayoutParams.MATCH_PARENT,
463:                 LinearLayout.LayoutParams.WRAP_CONTENT));
467:         tabContentArea.setPadding(0, 0, 0, 0);
469:         LinearLayout.LayoutParams tabContentParams = new LinearLayout.LayoutParams(
470:                 LinearLayout.LayoutParams.MATCH_PARENT,
471:                 0, 1f);
485:                         webServerStatusText.setTextColor(UiStyles.color(MainActivity.this, R.color.accentHighlight));
502:                         webServerStatusText.setTextColor(UiStyles.color(MainActivity.this, R.color.textDialogButtonSecondary));
521:                         webServerStatusText.setTextColor(UiStyles.color(MainActivity.this, R.color.statusErrorBright));
728:         driveModeTabContent.addView(assistTabBuilder.build(), new LinearLayout.LayoutParams(
729:                 LinearLayout.LayoutParams.MATCH_PARENT,
730:                 LinearLayout.LayoutParams.WRAP_CONTENT));
775:         faceRecognitionScrollView.setFillViewport(true);
777:                 new ScrollView.LayoutParams(
778:                         ScrollView.LayoutParams.MATCH_PARENT,
779:                         ScrollView.LayoutParams.WRAP_CONTENT));
1149:         FrameLayout.LayoutParams matchParent = new FrameLayout.LayoutParams(
1150:                 FrameLayout.LayoutParams.MATCH_PARENT,
1151:                 FrameLayout.LayoutParams.MATCH_PARENT);
1192:     private static void addTabChild(FrameLayout parent, View child, FrameLayout.LayoutParams lp) {
1193:         if (child.getParent() instanceof android.view.ViewGroup) {
1194:             ((android.view.ViewGroup) child.getParent()).removeView(child);
1249:                 mainContentParams.width = LinearLayout.LayoutParams.MATCH_PARENT;
1254:                 mainContentParams.height = LinearLayout.LayoutParams.MATCH_PARENT;
1307:         target.setAlpha(1f);
1319:         veil.setAlpha(0);
1327:         cover.setDuration(coverMs);
1328:         cover.addUpdateListener(animation -> veil.setAlpha((int) animation.getAnimatedValue()));
1333:             public void onAnimationCancel(Animator animation) {
1334:                 dropped[0] = true;
1339:             public void onAnimationEnd(Animator animation) {
1340:                 if (dropped[0] || transitionVeils.get(target) != cover) {
1341:                     return;
1350:                     reveal.setDuration(revealMs);
1351:                     reveal.addUpdateListener(revealAnim -> veil.setAlpha((int) revealAnim.getAnimatedValue()));
1356:                         public void onAnimationCancel(Animator animation) {
1357:                             revealDropped[0] = true;
1362:                         public void onAnimationEnd(Animator animation) {
1363:                             if (revealDropped[0]) {
1364:                                 return;
1382:         target.setAlpha(1f);
1650:         LinearLayout.LayoutParams railParams = portraitShell
1651:                 ? new LinearLayout.LayoutParams(
1652:                         LinearLayout.LayoutParams.MATCH_PARENT,
1653:                         LinearLayout.LayoutParams.WRAP_CONTENT)
1654:                 : new LinearLayout.LayoutParams(
1655:                         sidebarWidthPx,
1656:                         LinearLayout.LayoutParams.MATCH_PARENT);
1678:             shellHost.setBackgroundColor(UiStyles.color(this, R.color.transparent));
1679:             mainRootContainer.addView(shellHost, new FrameLayout.LayoutParams(
1680:                     FrameLayout.LayoutParams.MATCH_PARENT,
1681:                     FrameLayout.LayoutParams.MATCH_PARENT));
1683:         shellHost.setOrientation(portrait ? LinearLayout.VERTICAL : LinearLayout.HORIZONTAL);
1687:         LinearLayout.LayoutParams railParams = portrait
1688:                 ? new LinearLayout.LayoutParams(
1689:                         LinearLayout.LayoutParams.MATCH_PARENT,
1690:                         LinearLayout.LayoutParams.WRAP_CONTENT)
1691:                 : new LinearLayout.LayoutParams(
1692:                         sidebarWidthPx,
1693:                         LinearLayout.LayoutParams.MATCH_PARENT);
1700:             mainContentParams = new LinearLayout.LayoutParams(
1701:                     LinearLayout.LayoutParams.MATCH_PARENT,
1702:                     0,
1703:                     1f);
1705:             mainContentParams = new LinearLayout.LayoutParams(
1706:                     screenWidthPx - sidebarWidthPx,
1707:                     LinearLayout.LayoutParams.MATCH_PARENT);
1765:                             webServerStatusText.setTextColor(UiStyles.color(this, R.color.accentHighlight));
1806:                     driveModeTabContent.addView(assistTabBuilder.build(), new LinearLayout.LayoutParams(
1807:                             LinearLayout.LayoutParams.MATCH_PARENT,
1808:                             LinearLayout.LayoutParams.WRAP_CONTENT));
1855:                             new ScrollView.LayoutParams(
1856:                                     ScrollView.LayoutParams.MATCH_PARENT,
1857:                                     ScrollView.LayoutParams.WRAP_CONTENT));
1874:             climateAutomationScrollView.setFillViewport(true);
1879:                 new ScrollView.LayoutParams(
1880:                         ScrollView.LayoutParams.MATCH_PARENT,
1881:                         ScrollView.LayoutParams.WRAP_CONTENT));
```

### app/src/main/java/com/mapcontrol/ui/builder/AppsTabBuilder.java

```text
201:         tabContent.setOrientation(LinearLayout.VERTICAL);
203:         tabContent.setPadding(margin, margin, margin, margin);
204:         tabContent.setBackgroundColor(UiStyles.color(context, R.color.transparent));
207:         card.setOrientation(LinearLayout.VERTICAL);
209:         card.setPadding(inner, inner, inner, inner);
215:         LinearLayout.LayoutParams actionsParams = new LinearLayout.LayoutParams(-1, -2);
220:         appsListScrollView.setBackgroundColor(UiStyles.color(context, R.color.transparent));
221:         appsListScrollView.setPadding(0, 0, 0, 0);
224:         appsListContainer.setOrientation(LinearLayout.VERTICAL);
227:         LinearLayout.LayoutParams appsListParams = new LinearLayout.LayoutParams(
228:                 LinearLayout.LayoutParams.MATCH_PARENT,
229:                 0, 1f);
232:         tabContent.addView(card, new LinearLayout.LayoutParams(
233:                 LinearLayout.LayoutParams.MATCH_PARENT,
234:                 0, 1f));
249:         container.setOrientation(LinearLayout.HORIZONTAL);
250:         container.setGravity(android.view.Gravity.CENTER_VERTICAL);
253:         UiStyles.setTextSize(btnRefreshApps, R.dimen.text_size_large);
254:         btnRefreshApps.setTextColor(UiStyles.color(ctx, R.color.textPrimary));
255:         btnRefreshApps.setBackgroundColor(UiStyles.color(ctx, R.color.transparent));
256:         btnRefreshApps.setPadding(12, 12, 12, 12);
259:         LinearLayout.LayoutParams refreshParams = new LinearLayout.LayoutParams(
260:                 LinearLayout.LayoutParams.WRAP_CONTENT,
261:                 LinearLayout.LayoutParams.WRAP_CONTENT);
262:         refreshParams.setMargins(0, 0, 4, 0);
273:         UiStyles.setTextSize(btnDownloadedFiles, R.dimen.text_size_large);
274:         btnDownloadedFiles.setTextColor(UiStyles.color(ctx, R.color.textPrimary));
275:         btnDownloadedFiles.setBackgroundColor(UiStyles.color(ctx, R.color.transparent));
276:         btnDownloadedFiles.setPadding(12, 12, 12, 12);
279:         LinearLayout.LayoutParams filesParams = new LinearLayout.LayoutParams(
280:                 LinearLayout.LayoutParams.WRAP_CONTENT,
281:                 LinearLayout.LayoutParams.WRAP_CONTENT);
282:         filesParams.setMargins(0, 0, 4, 0);
288:         UiStyles.setTextSize(btnModeToggle, R.dimen.text_size_normal);
289:         btnModeToggle.setTextColor(UiStyles.color(ctx, R.color.textPrimary));
290:         btnModeToggle.setBackgroundColor(UiStyles.color(ctx, R.color.transparent));
291:         btnModeToggle.setPadding(12, 12, 12, 12);
302:         LinearLayout.LayoutParams toggleParams = new LinearLayout.LayoutParams(
303:                 LinearLayout.LayoutParams.WRAP_CONTENT,
304:                 LinearLayout.LayoutParams.WRAP_CONTENT);
305:         toggleParams.setMargins(0, 0, 4, 0);
328:     private static LinearLayout.LayoutParams pageActionParams() {
329:         LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(
330:                 LinearLayout.LayoutParams.WRAP_CONTENT,
331:                 LinearLayout.LayoutParams.WRAP_CONTENT);
332:         params.setMargins(0, 0, 4, 0);
338:         UiStyles.setTextSize(button, R.dimen.text_size_normal);
339:         button.setTextColor(color);
340:         button.setBackgroundColor(UiStyles.color(button.getContext(), R.color.transparent));
341:         button.setPadding(12, 12, 12, 12);
342:         button.setMinWidth(0);
343:         button.setMinimumWidth(0);
377:                     loadingText.setTextColor(UiStyles.color(context, R.color.textLoading));
378:                     UiStyles.setTextSize(loadingText, R.dimen.text_size_small);
379:                     loadingText.setPadding(8, 8, 8, 8);
396:                     errorText.setTextColor(UiStyles.color(context, R.color.statusErrorBright));
397:                     UiStyles.setTextSize(errorText, R.dimen.text_size_small);
398:                     errorText.setPadding(8, 8, 8, 8);
451:                 appCard.setOrientation(LinearLayout.HORIZONTAL);
452:                 appCard.setPadding(20, 20, 20, 20);
453:                 appCard.setGravity(android.view.Gravity.CENTER_VERTICAL);
461:                     appCard.setElevation(2f);
464:                 LinearLayout.LayoutParams cardParams = new LinearLayout.LayoutParams(
465:                         LinearLayout.LayoutParams.MATCH_PARENT,
466:                         LinearLayout.LayoutParams.WRAP_CONTENT);
467:                 cardParams.setMargins(0, 0, 0, UiStyles.dimenPx(context, R.dimen.oem_card_margin));
468:                 appCard.setMinimumHeight((int) (120 * context.getResources().getDisplayMetrics().density));
478:                 LinearLayout.LayoutParams iconParams = new LinearLayout.LayoutParams(
479:                         (int) (44 * context.getResources().getDisplayMetrics().density),
480:                         (int) (44 * context.getResources().getDisplayMetrics().density));
481:                 iconParams.setMargins(0, 0, 16, 0);
485:                 infoContainer.setOrientation(LinearLayout.VERTICAL);
486:                 infoContainer.setGravity(android.view.Gravity.CENTER_VERTICAL);
490:                 nameText.setTextColor(UiStyles.color(context, R.color.textPrimary));
491:                 UiStyles.setTextSize(nameText, R.dimen.text_size_medium);
492:                 nameText.setTypeface(null, android.graphics.Typeface.NORMAL);
497:                 statusText.setTextColor(UiStyles.color(context, R.color.textSecondaryCool));
498:                 UiStyles.setTextSize(statusText, R.dimen.text_size_small);
499:                 LinearLayout.LayoutParams statusParams = new LinearLayout.LayoutParams(
500:                         LinearLayout.LayoutParams.WRAP_CONTENT,
501:                         LinearLayout.LayoutParams.WRAP_CONTENT);
502:                 statusParams.setMargins(0, 4, 0, 0);
505:                 LinearLayout.LayoutParams infoParams = new LinearLayout.LayoutParams(
506:                         0, LinearLayout.LayoutParams.WRAP_CONTENT, 1f);
510:                 rightContainer.setOrientation(LinearLayout.HORIZONTAL);
511:                 rightContainer.setGravity(android.view.Gravity.CENTER_VERTICAL);
515:                 UiStyles.setTextSize(actionButton, R.dimen.text_size_small);
516:                 actionButton.setTypeface(null, android.graphics.Typeface.BOLD);
517:                 actionButton.setPadding(24, 12, 24, 12);
520:                 actionButton.setTextColor(UiStyles.color(context, R.color.textPrimary));
539:                 UiStyles.setTextSize(removeButton, R.dimen.text_size_small);
540:                 removeButton.setTypeface(null, android.graphics.Typeface.BOLD);
541:                 removeButton.setTextColor(UiStyles.color(context, R.color.textDestructive));
542:                 removeButton.setBackgroundColor(UiStyles.color(context, R.color.transparent));
543:                 removeButton.setPadding(18, 12, 18, 12);
545:                 LinearLayout.LayoutParams removeParams = new LinearLayout.LayoutParams(
546:                         LinearLayout.LayoutParams.WRAP_CONTENT,
547:                         LinearLayout.LayoutParams.WRAP_CONTENT);
548:                 removeParams.setMargins(UiStyles.dimenPx(context, R.dimen.spacing_medium), 0, 0, 0);
558:                 emptyCard.setOrientation(LinearLayout.VERTICAL);
559:                 emptyCard.setGravity(android.view.Gravity.CENTER);
560:                 emptyCard.setPadding(32, 48, 32, 48);
566:                 emptyIcon.setLayoutParams(new LinearLayout.LayoutParams(iconPx, iconPx));
572:                 emptyText.setTextColor(UiStyles.color(context, R.color.textMuted));
573:                 UiStyles.setTextSize(emptyText, R.dimen.text_size_normal);
574:                 emptyText.setGravity(android.view.Gravity.CENTER);
575:                 LinearLayout.LayoutParams etLp = new LinearLayout.LayoutParams(
576:                         LinearLayout.LayoutParams.MATCH_PARENT,
577:                         LinearLayout.LayoutParams.WRAP_CONTENT);
705:         loadingText.setTextColor(UiStyles.color(context, reconnecting ? R.color.textSecondaryCool : R.color.textLoading));
706:         UiStyles.setTextSize(loadingText, R.dimen.text_size_small);
707:         loadingText.setPadding(8, 8, 8, 8);
800:                 emptyCard.setOrientation(LinearLayout.VERTICAL);
801:                 emptyCard.setGravity(android.view.Gravity.CENTER);
802:                 emptyCard.setPadding(32, 48, 32, 48);
807:                 emptyIcon.setLayoutParams(new LinearLayout.LayoutParams(iconPx, iconPx));
812:                 emptyText.setTextColor(UiStyles.color(context, R.color.textMuted));
813:                 UiStyles.setTextSize(emptyText, R.dimen.text_size_normal);
814:                 emptyText.setGravity(android.view.Gravity.CENTER);
815:                 LinearLayout.LayoutParams etLp = new LinearLayout.LayoutParams(
816:                         LinearLayout.LayoutParams.MATCH_PARENT,
817:                         LinearLayout.LayoutParams.WRAP_CONTENT);
833:         section.setOrientation(LinearLayout.VERTICAL);
834:         section.setPadding(pad, pad, pad, Math.round(4 * density));
841:         title.setTextColor(UiStyles.color(context, R.color.textPrimary));
842:         UiStyles.setTextSize(title, R.dimen.text_size_normal);
843:         title.setTypeface(null, android.graphics.Typeface.BOLD);
849:             warn.setTextColor(UiStyles.color(context, R.color.statusWarning));
850:             UiStyles.setTextSize(warn, R.dimen.text_size_small);
851:             LinearLayout.LayoutParams warnParams = new LinearLayout.LayoutParams(
852:                     LinearLayout.LayoutParams.MATCH_PARENT,
853:                     LinearLayout.LayoutParams.WRAP_CONTENT);
866:         LinearLayout.LayoutParams sectionParams = new LinearLayout.LayoutParams(
867:                 LinearLayout.LayoutParams.MATCH_PARENT,
868:                 LinearLayout.LayoutParams.WRAP_CONTENT);
869:         sectionParams.setMargins(0, 0, 0, UiStyles.dimenPx(context, R.dimen.oem_card_margin));
904:         appCard.setOrientation(LinearLayout.HORIZONTAL);
905:         appCard.setPadding(20, 20, 20, 20);
906:         appCard.setGravity(android.view.Gravity.CENTER_VERTICAL);
913:             appCard.setElevation(2f);
916:         LinearLayout.LayoutParams cardParams = new LinearLayout.LayoutParams(
917:                 LinearLayout.LayoutParams.MATCH_PARENT,
918:                 LinearLayout.LayoutParams.WRAP_CONTENT);
919:         cardParams.setMargins(0, 0, 0, UiStyles.dimenPx(context, R.dimen.oem_card_margin));
920:         appCard.setMinimumHeight((int) (120 * context.getResources().getDisplayMetrics().density));
934:         LinearLayout.LayoutParams iconParams = new LinearLayout.LayoutParams(
935:                 (int) (44 * context.getResources().getDisplayMetrics().density),
936:                 (int) (44 * context.getResources().getDisplayMetrics().density));
937:         iconParams.setMargins(0, 0, 16, 0);
941:         infoContainer.setOrientation(LinearLayout.VERTICAL);
942:         infoContainer.setGravity(android.view.Gravity.CENTER_VERTICAL);
950:         nameText.setTextColor(UiStyles.color(context, R.color.textPrimary));
951:         UiStyles.setTextSize(nameText, R.dimen.text_size_medium);
952:         nameText.setTypeface(null, android.graphics.Typeface.NORMAL);
958:             statusText.setTextColor(UiStyles.color(context, R.color.statusSuccess));
961:             statusText.setTextColor(UiStyles.color(context, R.color.textSecondaryCool));
964:             statusText.setTextColor(UiStyles.color(context, R.color.textMuted));
966:         UiStyles.setTextSize(statusText, R.dimen.text_size_small);
967:         LinearLayout.LayoutParams statusParams = new LinearLayout.LayoutParams(
968:                 LinearLayout.LayoutParams.WRAP_CONTENT,
969:                 LinearLayout.LayoutParams.WRAP_CONTENT);
970:         statusParams.setMargins(0, 4, 0, 0);
977:             companionText.setTextColor(UiStyles.color(context, R.color.statusWarning));
978:             UiStyles.setTextSize(companionText, R.dimen.text_size_small);
979:             LinearLayout.LayoutParams companionParams = new LinearLayout.LayoutParams(
980:                     LinearLayout.LayoutParams.WRAP_CONTENT,
981:                     LinearLayout.LayoutParams.WRAP_CONTENT);
982:             companionParams.setMargins(0, 2, 0, 0);
986:         LinearLayout.LayoutParams infoParams = new LinearLayout.LayoutParams(
987:                 0, LinearLayout.LayoutParams.WRAP_CONTENT, 1f);
991:         rightContainer.setOrientation(LinearLayout.HORIZONTAL);
992:         rightContainer.setGravity(android.view.Gravity.CENTER_VERTICAL);
995:         UiStyles.setTextSize(actionButton, R.dimen.text_size_small);
996:         actionButton.setTypeface(null, android.graphics.Typeface.BOLD);
997:         actionButton.setPadding(24, 12, 24, 12);
1003:             actionButton.setTextColor(UiStyles.color(context, R.color.textPrimary));
1011:             actionButton.setTextColor(UiStyles.color(context, R.color.textPrimary));
1016:             actionButton.setTextColor(UiStyles.color(context, R.color.textSecondaryCool));
1023:             actionButton.setTextColor(UiStyles.color(context, R.color.textPrimary));
1038:             UiStyles.setTextSize(removeButton, R.dimen.text_size_small);
1039:             removeButton.setTypeface(null, android.graphics.Typeface.BOLD);
1040:             removeButton.setTextColor(UiStyles.color(context, R.color.textDestructive));
1041:             removeButton.setBackgroundColor(UiStyles.color(context, R.color.transparent));
1042:             removeButton.setPadding(18, 12, 18, 12);
1044:             LinearLayout.LayoutParams removeParams = new LinearLayout.LayoutParams(
1045:                     LinearLayout.LayoutParams.WRAP_CONTENT,
1046:                     LinearLayout.LayoutParams.WRAP_CONTENT);
1047:             removeParams.setMargins(UiStyles.dimenPx(context, R.dimen.spacing_medium), 0, 0, 0);
1141:         dialog.getButton(AlertDialog.BUTTON_POSITIVE).setTextColor(UiStyles.color(context, R.color.statusSuccess));
1142:         dialog.getButton(AlertDialog.BUTTON_NEUTRAL).setTextColor(UiStyles.color(context, R.color.textPrimary));
1143:         dialog.getButton(AlertDialog.BUTTON_NEGATIVE).setTextColor(UiStyles.color(context, R.color.statusNeutralGray));
1305:         dialog.getButton(AlertDialog.BUTTON_POSITIVE).setTextColor(UiStyles.color(context, R.color.statusErrorBright));
1306:         dialog.getButton(AlertDialog.BUTTON_POSITIVE).setTextSize(17);
1307:         dialog.getButton(AlertDialog.BUTTON_POSITIVE).setTypeface(null, android.graphics.Typeface.BOLD);
1308:         dialog.getButton(AlertDialog.BUTTON_NEGATIVE).setTextColor(UiStyles.color(context, R.color.statusNeutralGray));
1309:         dialog.getButton(AlertDialog.BUTTON_NEGATIVE).setTextSize(17);
1310:         dialog.getButton(AlertDialog.BUTTON_NEGATIVE).setTypeface(null, android.graphics.Typeface.BOLD);
1340:         status.setTextColor(UiStyles.color(context, R.color.statusSuccess));
1343:         button.setTextColor(UiStyles.color(context, R.color.textPrimary));
1381:             button.setTextColor(iconColor);
1383:             status.setTextColor(UiStyles.color(context, R.color.statusInfo));
1389:             button.setTextColor(iconColor);
1391:             status.setTextColor(UiStyles.color(context, R.color.statusInfo));
1397:             button.setTextColor(iconColor);
1399:             status.setTextColor(UiStyles.color(context, R.color.statusError));
1677:             dialogLayout.setOrientation(LinearLayout.VERTICAL);
1678:             dialogLayout.setPadding(0, 0, 0, 0);
1679:             dialogLayout.setBackgroundColor(UiStyles.color(context, R.color.backgroundPage));
1682:             headerLayout.setOrientation(LinearLayout.HORIZONTAL);
1683:             headerLayout.setBackgroundColor(UiStyles.color(context, R.color.surfaceCardInner));
1684:             headerLayout.setPadding(24, 20, 24, 20);
1685:             headerLayout.setGravity(android.view.Gravity.CENTER_VERTICAL);
1688:             titleContainer.setOrientation(LinearLayout.VERTICAL);
1692:             titleText.setTextColor(UiStyles.color(context, R.color.textPrimary));
1693:             UiStyles.setTextSize(titleText, R.dimen.text_size_large);
1694:             titleText.setTypeface(null, android.graphics.Typeface.BOLD);
1699:             subtitleText.setTextColor(UiStyles.color(context, R.color.textSecondaryCool));
1700:             UiStyles.setTextSize(subtitleText, R.dimen.text_size_small);
1701:             subtitleText.setPadding(0, 4, 0, 0);
1704:             LinearLayout.LayoutParams titleContainerParams = new LinearLayout.LayoutParams(
1705:                     0, LinearLayout.LayoutParams.WRAP_CONTENT, 1f);
1711:                 UiStyles.setTextSize(deleteAllButton, R.dimen.text_size_small);
1712:                 deleteAllButton.setTextColor(UiStyles.color(context, R.color.textDestructive));
1713:                 deleteAllButton.setBackgroundColor(UiStyles.color(context, R.color.transparent));
1714:                 deleteAllButton.setPadding(12, 8, 12, 8);
1746:             scrollView.setBackgroundColor(UiStyles.color(context, R.color.backgroundPage));
1747:             scrollView.setPadding(16, 16, 16, 16);
1748:             scrollView.setLayoutParams(new LinearLayout.LayoutParams(
1749:                     LinearLayout.LayoutParams.MATCH_PARENT,
1750:                     500));
1753:             filesLayout.setOrientation(LinearLayout.VERTICAL);
1757:                 emptyWrap.setOrientation(LinearLayout.VERTICAL);
1758:                 emptyWrap.setGravity(android.view.Gravity.CENTER);
1759:                 emptyWrap.setPadding(32, 64, 32, 64);
1764:                 emptyIco.setLayoutParams(new LinearLayout.LayoutParams(epx, epx));
1770:                 emptyText.setTextColor(UiStyles.color(context, R.color.textSecondaryCool));
1771:                 UiStyles.setTextSize(emptyText, R.dimen.text_size_normal);
1772:                 emptyText.setGravity(android.view.Gravity.CENTER);
1773:                 LinearLayout.LayoutParams etP = new LinearLayout.LayoutParams(
1774:                         LinearLayout.LayoutParams.MATCH_PARENT,
1775:                         LinearLayout.LayoutParams.WRAP_CONTENT);
1784:                     fileCard.setOrientation(LinearLayout.HORIZONTAL);
1785:                     fileCard.setPadding(UiStyles.dimenPx(context, R.dimen.spacing_medium),
1786:                             UiStyles.dimenPx(context, R.dimen.spacing_medium),
1787:                             UiStyles.dimenPx(context, R.dimen.spacing_medium),
1788:                             UiStyles.dimenPx(context, R.dimen.spacing_medium));
1791:                     LinearLayout.LayoutParams cardParams = new LinearLayout.LayoutParams(
1792:                             LinearLayout.LayoutParams.MATCH_PARENT,
1793:                             LinearLayout.LayoutParams.WRAP_CONTENT);
1794:                     cardParams.setMargins(0, 0, 0, UiStyles.dimenPx(context, R.dimen.oem_card_margin));
1797:                     iconBox.setOrientation(LinearLayout.VERTICAL);
1798:                     iconBox.setGravity(android.view.Gravity.CENTER);
1801:                     iconBox.setPadding(16, 16, 16, 16);
1807:                     iconApk.setLayoutParams(new LinearLayout.LayoutParams(innerIcon, innerIcon));
1812:                     LinearLayout.LayoutParams iconParams = new LinearLayout.LayoutParams(60, 60);
1813:                     iconParams.setMargins(0, 0, 12, 0);
1817:                     infoLayout.setOrientation(LinearLayout.VERTICAL);
1818:                     infoLayout.setGravity(android.view.Gravity.CENTER_VERTICAL);
1823:                     nameText.setTextColor(UiStyles.color(context, R.color.textPrimary));
1824:                     UiStyles.setTextSize(nameText, R.dimen.text_size_small);
1825:                     nameText.setTypeface(null, android.graphics.Typeface.BOLD);
1826:                     nameText.setMaxLines(1);
1827:                     nameText.setEllipsize(android.text.TextUtils.TruncateAt.END);
1832:                     sizeText.setTextColor(UiStyles.color(context, R.color.textSecondaryCool));
1833:                     UiStyles.setTextSize(sizeText, R.dimen.text_size_small);
1834:                     sizeText.setPadding(0, 2, 0, 0);
1837:                     fileCard.addView(infoLayout, new LinearLayout.LayoutParams(0, LinearLayout.LayoutParams.WRAP_CONTENT, 1f));
1840:                     buttonsContainer.setOrientation(LinearLayout.HORIZONTAL);
1841:                     buttonsContainer.setGravity(android.view.Gravity.CENTER_VERTICAL);
1845:                     installButton.setTextColor(UiStyles.color(context, R.color.textPrimary));
1847:                     UiStyles.setTextSize(installButton, R.dimen.text_size_small);
1848:                     installButton.setTypeface(null, android.graphics.Typeface.BOLD);
1849:                     installButton.setPadding(16, 12, 16, 12);
1856:                     deleteButton.setTextColor(UiStyles.color(context, R.color.textPrimary));
1858:                     UiStyles.setTextSize(deleteButton, R.dimen.text_size_medium);
1859:                     deleteButton.setPadding(16, 12, 16, 12);
```

### app/src/main/java/com/mapcontrol/ui/builder/AssistTabBuilder.java

```text
44:         scrollView.setBackgroundColor(Color.TRANSPARENT);
45:         scrollView.setFillViewport(false);
48:         container.setOrientation(LinearLayout.VERTICAL);
49:         container.setBackgroundColor(Color.TRANSPARENT);
53:         assistTitleRow.setOrientation(LinearLayout.HORIZONTAL);
54:         assistTitleRow.setGravity(android.view.Gravity.CENTER_VERTICAL);
55:         assistTitleRow.setPadding(16, 24, 16, 8);
61:         assistTitleIcon.setLayoutParams(new LinearLayout.LayoutParams(titleIconPx, titleIconPx));
68:         UiStyles.setTextSize(assistTitle, R.dimen.text_size_large);
69:         assistTitle.setTextColor(UiStyles.color(context, R.color.textPrimary));
70:         assistTitle.setTypeface(null, android.graphics.Typeface.BOLD);
71:         LinearLayout.LayoutParams titleLp = new LinearLayout.LayoutParams(
72:                 0, LinearLayout.LayoutParams.WRAP_CONTENT, 1f);
73:         titleLp.setMargins((int) (12 * densityTitle), 0, 0, 0);
76:         container.addView(assistTitleRow, new LinearLayout.LayoutParams(
77:                 LinearLayout.LayoutParams.MATCH_PARENT,
78:                 LinearLayout.LayoutParams.WRAP_CONTENT));
82:         UiStyles.setTextSize(assistSubtitle, R.dimen.text_size_small);
83:         assistSubtitle.setTextColor(UiStyles.color(context, R.color.textSecondaryCool));
84:         assistSubtitle.setPadding(16, 0, 16, 16);
85:         container.addView(assistSubtitle, new LinearLayout.LayoutParams(
86:                 LinearLayout.LayoutParams.MATCH_PARENT,
87:                 LinearLayout.LayoutParams.WRAP_CONTENT));
90:         assistGrid.setColumnCount(2);
91:         assistGrid.setPadding(16, 0, 16, 16);
92:         assistGrid.setBackgroundColor(Color.TRANSPARENT);
94:         LinearLayout.LayoutParams gridParams = new LinearLayout.LayoutParams(
95:                 LinearLayout.LayoutParams.MATCH_PARENT,
96:                 LinearLayout.LayoutParams.WRAP_CONTENT);
97:         gridParams.setMargins(0, 0, 0, 16);
143:             card.setOrientation(LinearLayout.HORIZONTAL);
144:             card.setGravity(android.view.Gravity.CENTER_VERTICAL);
145:             card.setPadding((int) (16 * density), (int) (16 * density), (int) (16 * density), (int) (16 * density));
146:             card.setMinimumHeight((int) (96 * density));
156:                 cardBg.setColor(UiStyles.color(context, R.color.assistCardActive));
157:                 cardBg.setCornerRadius(16 * density);
158:                 cardBg.setStroke((int) (2 * density), UiStyles.color(context, R.color.assistStrokeActive));
160:                 cardBg.setColor(UiStyles.color(context, R.color.assistCardIdle));
161:                 cardBg.setCornerRadius(16 * density);
162:                 cardBg.setStroke((int) (1 * density), UiStyles.color(context, R.color.outline));
170:             LinearLayout.LayoutParams iconLp = new LinearLayout.LayoutParams(iconPx, iconPx);
179:             labels.setOrientation(LinearLayout.VERTICAL);
180:             LinearLayout.LayoutParams labelsLp = new LinearLayout.LayoutParams(
181:                     0, LinearLayout.LayoutParams.WRAP_CONTENT, 1f);
182:             labelsLp.setMargins((int) (12 * density), 0, (int) (8 * density), 0);
186:             UiStyles.setTextSize(titleView, R.dimen.text_size_normal);
187:             titleView.setGravity(android.view.Gravity.START);
188:             titleView.setTextColor(UiStyles.color(context, R.color.textPrimary));
189:             titleView.setTypeface(null, isActiveRef[0] ? android.graphics.Typeface.BOLD : android.graphics.Typeface.NORMAL);
190:             titleView.setPadding(0, (int) (8 * density), 0, (int) (4 * density));
196:             UiStyles.setTextSize(statusView, R.dimen.text_size_small);
197:             statusView.setGravity(android.view.Gravity.START);
198:             statusView.setTextColor(UiStyles.color(context, R.color.textSecondaryCool));
199:             statusView.setMaxLines(3);
200:             statusView.setEllipsize(android.text.TextUtils.TruncateAt.END);
218:             GridLayout.LayoutParams cardParams = new GridLayout.LayoutParams();
220:             cardParams.height = GridLayout.LayoutParams.WRAP_CONTENT;
223:             cardParams.setMargins((int) (8 * density), (int) (8 * density), (int) (8 * density), (int) (8 * density));
274:         UiStyles.setTextSize(cpdDisclaimer, R.dimen.text_size_small);
275:         cpdDisclaimer.setTextColor(UiStyles.color(context, R.color.textSecondaryCool));
276:         cpdDisclaimer.setPadding(16, 0, 16, 16);
277:         container.addView(cpdDisclaimer, new LinearLayout.LayoutParams(
278:                 LinearLayout.LayoutParams.MATCH_PARENT,
279:                 LinearLayout.LayoutParams.WRAP_CONTENT));
281:         scrollView.addView(container, new ScrollView.LayoutParams(
282:                 ScrollView.LayoutParams.MATCH_PARENT,
283:                 ScrollView.LayoutParams.WRAP_CONTENT));
292:             newBg.setColor(UiStyles.color(context, R.color.assistCardActive));
293:             newBg.setCornerRadius(16 * density);
294:             newBg.setStroke((int) (2 * density), UiStyles.color(context, R.color.assistStrokeActive));
296:             newBg.setColor(UiStyles.color(context, R.color.assistCardIdle));
297:             newBg.setCornerRadius(16 * density);
298:             newBg.setStroke((int) (1 * density), UiStyles.color(context, R.color.outline));
303:         titleViews[cardIndex].setTypeface(null, isActive ? android.graphics.Typeface.BOLD : android.graphics.Typeface.NORMAL);
```

### app/src/main/java/com/mapcontrol/ui/builder/ClimateAutomationTabBuilder.java

```text
54:         root.setOrientation(LinearLayout.VERTICAL);
59:         root.setPadding(pagePadding, dp(8, density), pagePadding, dp(24, density));
62:         titleRow.setOrientation(LinearLayout.HORIZONTAL);
63:         titleRow.setGravity(Gravity.CENTER_VERTICAL);
67:         titleIconBackground.setColor(UiStyles.color(context, R.color.surfaceCardInner));
68:         titleIconBackground.setCornerRadius(dp(16, density));
75:         FrameLayout.LayoutParams titleIconLp = new FrameLayout.LayoutParams(titleIconPx, titleIconPx, Gravity.CENTER);
77:         titleRow.addView(titleIconBadge, new LinearLayout.LayoutParams(dp(52, density), dp(52, density)));
80:         heading.setOrientation(LinearLayout.VERTICAL);
81:         heading.setPadding(dp(14, density), 0, 0, 0);
84:         title.setTextSize(24);
85:         title.setTextColor(UiStyles.color(context, R.color.textPrimary));
86:         title.setTypeface(null, Typeface.BOLD);
91:         subtitle.setTextSize(14);
92:         subtitle.setTextColor(UiStyles.color(context, R.color.textSecondaryCool));
94:             LinearLayout.LayoutParams subtitleLp = new LinearLayout.LayoutParams(
95:                     LinearLayout.LayoutParams.MATCH_PARENT, LinearLayout.LayoutParams.WRAP_CONTENT);
102:             heading.addView(subtitle, new LinearLayout.LayoutParams(
103:                     LinearLayout.LayoutParams.WRAP_CONTENT, LinearLayout.LayoutParams.WRAP_CONTENT));
105:         titleRow.addView(heading, new LinearLayout.LayoutParams(0, LinearLayout.LayoutParams.WRAP_CONTENT, 1f));
112:         statusCard.setOrientation(LinearLayout.HORIZONTAL);
113:         statusCard.setGravity(Gravity.CENTER_VERTICAL);
114:         statusCard.setPadding(dp(18, density), dp(16, density), dp(18, density), dp(16, density));
118:         statusCard.setElevation(dp(2, density));
119:         LinearLayout.LayoutParams statusCardLp = new LinearLayout.LayoutParams(
120:                 LinearLayout.LayoutParams.MATCH_PARENT, LinearLayout.LayoutParams.WRAP_CONTENT);
128:         statusCard.addView(statusIcon, new LinearLayout.LayoutParams(dp(30, density), dp(30, density)));
131:         statusTextColumn.setOrientation(LinearLayout.VERTICAL);
132:         LinearLayout.LayoutParams statusTextLp = new LinearLayout.LayoutParams(
133:                 0, LinearLayout.LayoutParams.WRAP_CONTENT, 1f);
134:         statusTextLp.setMargins(dp(14, density), 0, dp(12, density), 0);
138:         sensorLabel.setTextSize(11);
139:         sensorLabel.setTypeface(null, Typeface.BOLD);
140:         sensorLabel.setLetterSpacing(0.08f);
141:         sensorLabel.setTextColor(UiStyles.color(context, R.color.textSecondaryCool));
145:         liveStatusView.setTextSize(19);
146:         liveStatusView.setTypeface(null, Typeface.BOLD);
147:         liveStatusView.setTextColor(UiStyles.color(context, R.color.automationText));
148:         LinearLayout.LayoutParams liveStatusLp = new LinearLayout.LayoutParams(
149:                 LinearLayout.LayoutParams.MATCH_PARENT, LinearLayout.LayoutParams.WRAP_CONTENT);
155:         indicatorBackground.setColor(UiStyles.color(context, R.color.statusWarning));
157:         statusCard.addView(liveStatusIndicator, new LinearLayout.LayoutParams(dp(10, density), dp(10, density)));
161:         masterCard.setOrientation(LinearLayout.VERTICAL);
162:         masterCard.setPadding(dp(18, density), dp(14, density), dp(18, density), dp(14, density));
166:         masterCard.setElevation(dp(2, density));
167:         LinearLayout.LayoutParams masterCardLp = new LinearLayout.LayoutParams(
168:                 LinearLayout.LayoutParams.MATCH_PARENT, LinearLayout.LayoutParams.WRAP_CONTENT);
173:         masterRow.setOrientation(LinearLayout.HORIZONTAL);
174:         masterRow.setGravity(Gravity.CENTER_VERTICAL);
178:         masterText.setOrientation(LinearLayout.VERTICAL);
179:         masterRow.addView(masterText, new LinearLayout.LayoutParams(
180:                 0, LinearLayout.LayoutParams.WRAP_CONTENT, 1f));
183:         masterTitle.setTextSize(16);
184:         masterTitle.setTypeface(null, Typeface.BOLD);
185:         masterTitle.setTextColor(UiStyles.color(context, R.color.automationText));
196:         masterRow.addView(masterSwitch, new LinearLayout.LayoutParams(
197:                 LinearLayout.LayoutParams.WRAP_CONTENT, LinearLayout.LayoutParams.WRAP_CONTENT));
238:         outerCard.setOrientation(LinearLayout.VERTICAL);
239:         outerCard.setPadding(dp(16, density), dp(16, density), dp(16, density), dp(16, density));
242:         outerBg.setColor(UiStyles.color(context, R.color.automationPanel));
243:         outerBg.setCornerRadius(dp(22, density));
244:         outerBg.setStroke(dp(1, density), UiStyles.color(context, R.color.automationCardStroke));
246:         outerCard.setElevation(dp(2, density));
248:         LinearLayout.LayoutParams outerLp = new LinearLayout.LayoutParams(
249:                 LinearLayout.LayoutParams.MATCH_PARENT, LinearLayout.LayoutParams.WRAP_CONTENT);
255:         headerRow.setOrientation(LinearLayout.HORIZONTAL);
256:         headerRow.setGravity(Gravity.CENTER_VERTICAL);
257:         headerRow.setPadding(dp(4, density), 0, dp(4, density), dp(12, density));
261:         sectionTitle.setTextSize(17);
262:         sectionTitle.setTypeface(null, Typeface.BOLD);
263:         sectionTitle.setTextColor(UiStyles.color(context, R.color.automationText));
264:         headerRow.addView(sectionTitle, new LinearLayout.LayoutParams(0, LinearLayout.LayoutParams.WRAP_CONTENT, 1f));
268:         seatBadge.setTextSize(10);
269:         seatBadge.setTypeface(null, Typeface.BOLD);
270:         seatBadge.setTextColor(UiStyles.color(context, R.color.oemAccent));
271:         seatBadge.setPadding(dp(8, density), dp(3, density), dp(8, density), dp(3, density));
273:         badgeBg.setColor((UiStyles.color(context, R.color.oemAccent) & 0x00FFFFFF) | 0x22000000);
274:         badgeBg.setCornerRadius(dp(8, density));
282:         row.setOrientation(LinearLayout.HORIZONTAL);
287:         LinearLayout.LayoutParams p1 = new LinearLayout.LayoutParams(
288:                 0, LinearLayout.LayoutParams.WRAP_CONTENT, 1f);
292:         LinearLayout.LayoutParams p2 = new LinearLayout.LayoutParams(
293:                 0, LinearLayout.LayoutParams.WRAP_CONTENT, 1f);
306:         outerCard.setOrientation(LinearLayout.VERTICAL);
307:         outerCard.setPadding(dp(16, density), dp(16, density), dp(16, density), dp(16, density));
310:         outerBg.setColor(UiStyles.color(context, R.color.automationPanel));
311:         outerBg.setCornerRadius(dp(22, density));
312:         outerBg.setStroke(dp(1, density), UiStyles.color(context, R.color.automationCardStroke));
314:         outerCard.setElevation(dp(2, density));
316:         LinearLayout.LayoutParams outerLp = new LinearLayout.LayoutParams(
317:                 LinearLayout.LayoutParams.MATCH_PARENT, LinearLayout.LayoutParams.WRAP_CONTENT);
323:         sectionTitle.setTextSize(17);
324:         sectionTitle.setTypeface(null, Typeface.BOLD);
325:         sectionTitle.setTextColor(UiStyles.color(context, R.color.automationText));
326:         sectionTitle.setPadding(dp(4, density), 0, dp(4, density), dp(12, density));
330:         grid.setColumnCount(columnsForWidth(context.getResources().getDisplayMetrics().widthPixels, density));
335:                 grid.setColumnCount(columns);
351:             GridLayout.LayoutParams lp = new GridLayout.LayoutParams();
353:             lp.height = GridLayout.LayoutParams.WRAP_CONTENT;
355:             lp.setMargins(dp(6, density), dp(6, density), dp(6, density), dp(6, density));
388:         card.setOrientation(LinearLayout.VERTICAL);
390:         card.setPadding(cardPadding, cardPadding, cardPadding, dp(compactLayout ? 8 : 12, density));
392:         card.setElevation(dp(2, density));
398:         stripBg.setCornerRadius(dp(3, density));
400:         LinearLayout.LayoutParams stripLp = new LinearLayout.LayoutParams(
401:                 LinearLayout.LayoutParams.MATCH_PARENT, dp(4, density));
406:         header.setOrientation(LinearLayout.VERTICAL);
407:         header.setGravity(Gravity.CENTER_HORIZONTAL);
416:         iconBackground.setColor(UiStyles.color(context, R.color.automationIconSurface));
417:         iconBackground.setCornerRadius(dp(16, density));
418:         iconBackground.setStroke(dp(1, density), UiStyles.color(context, R.color.automationCardStroke));
421:         iconBadge.addView(icon, new FrameLayout.LayoutParams(iconPx, iconPx, Gravity.CENTER));
422:         LinearLayout.LayoutParams iconBadgeLp = new LinearLayout.LayoutParams(badgePx, badgePx);
429:         name.setTextSize(13);
430:         name.setTypeface(null, Typeface.BOLD);
431:         name.setTextColor(UiStyles.color(context, R.color.automationText));
432:         name.setGravity(Gravity.CENTER);
434:         name.setMaxLines(2);
435:         LinearLayout.LayoutParams nameLp = new LinearLayout.LayoutParams(
436:                 LinearLayout.LayoutParams.MATCH_PARENT, LinearLayout.LayoutParams.WRAP_CONTENT);
449:         switchRow.setGravity(Gravity.CENTER);
450:         switchRow.setPadding(0, dp(6, density), 0, 0);
451:         switchRow.addView(ruleSwitch, new LinearLayout.LayoutParams(
452:                 LinearLayout.LayoutParams.WRAP_CONTENT, LinearLayout.LayoutParams.WRAP_CONTENT));
453:         header.addView(switchRow, new LinearLayout.LayoutParams(
454:                 LinearLayout.LayoutParams.MATCH_PARENT, LinearLayout.LayoutParams.WRAP_CONTENT));
458:         delayLabel.setTextSize(11);
459:         delayLabel.setTextColor(UiStyles.color(context, R.color.automationTextMuted));
460:         delayLabel.setGravity(Gravity.CENTER);
462:         delayLabel.setPadding(0, dp(8, density), 0, 0);
472:         compareRow.setOrientation(LinearLayout.HORIZONTAL);
473:         compareRow.setGravity(Gravity.CENTER_VERTICAL);
474:         compareRow.setPadding(0, dp(8, density), 0, 0);
477:         compareChip.setTextSize(14);
478:         compareChip.setTypeface(null, Typeface.BOLD);
479:         compareChip.setPadding(dp(14, density), dp(6, density), dp(14, density), dp(6, density));
480:         compareChip.setTextColor(accentColor);
482:         compareBg.setColor((accentColor & 0x00FFFFFF) | 0x1A000000);
483:         compareBg.setCornerRadius(dp(10, density));
484:         compareBg.setStroke(dp(1, density), (accentColor & 0x00FFFFFF) | 0x66000000);
491:         conditionDesc.setTextSize(11);
492:         conditionDesc.setTextColor(UiStyles.color(context, R.color.textSecondaryCool));
493:         conditionDesc.setPadding(dp(10, density), 0, 0, 0);
506:         conflictNote.setTextSize(11);
507:         conflictNote.setTypeface(null, Typeface.BOLD);
508:         conflictNote.setTextColor(0xFFFF4D4F); // Belirgin kırmızı/turuncu uyarı
509:         conflictNote.setPadding(0, dp(6, density), 0, 0);
584:             levelLabel.setTextSize(11);
585:             levelLabel.setTextColor(UiStyles.color(context, R.color.automationTextMuted));
586:             levelLabel.setGravity(Gravity.CENTER);
588:             levelLabel.setPadding(0, dp(8, density), 0, 0);
648:         bar.setMinHeight(track);
656:         thumb.setColor(accent);
662:         bar.setPadding(side, vertical, side, vertical);
663:         LinearLayout.LayoutParams lp = new LinearLayout.LayoutParams(
664:                 LinearLayout.LayoutParams.MATCH_PARENT, dp(52, density));
696:         bg.setCornerRadius(dp(18, density));
698:             bg.setColor(UiStyles.color(context, R.color.automationCardSurface));
699:             bg.setStroke(dp(2, density), UiStyles.color(context, R.color.oemAccent));
701:             bg.setColor(UiStyles.color(context, R.color.automationCardSurface));
702:             bg.setStroke(dp(1, density), UiStyles.color(context, R.color.automationCardStroke));
709:         background.setColor(fill);
710:         background.setCornerRadius(dp(radiusDp, density));
711:         background.setStroke(dp(1, density), stroke);
728:             background.setColor(UiStyles.color(context, colorRes));
```

### app/src/main/java/com/mapcontrol/ui/builder/DmsFaceRecognitionTabBuilder.java

```text
54:         root.setOrientation(LinearLayout.VERTICAL);
56:         root.setPadding(padding, dp(16), padding, dp(24));
60:         mainCard.setOrientation(LinearLayout.VERTICAL);
61:         mainCard.setPadding(dp(18), dp(16), dp(18), dp(18));
63:         root.addView(mainCard, new LinearLayout.LayoutParams(
64:                 LinearLayout.LayoutParams.MATCH_PARENT, LinearLayout.LayoutParams.WRAP_CONTENT));
68:         title.setTextSize(22);
69:         title.setTextColor(UiStyles.color(activity, R.color.textPrimary));
70:         title.setTypeface(null, Typeface.BOLD);
74:         description.setTextSize(14);
75:         description.setTextColor(UiStyles.color(activity, R.color.textSecondaryCool));
76:         LinearLayout.LayoutParams descriptionLp = new LinearLayout.LayoutParams(
77:                 LinearLayout.LayoutParams.MATCH_PARENT, LinearLayout.LayoutParams.WRAP_CONTENT);
82:         toggleRow.setGravity(Gravity.CENTER_VERTICAL);
83:         toggleRow.setPadding(dp(14), dp(12), dp(14), dp(12));
86:         LinearLayout.LayoutParams toggleRowLp = new LinearLayout.LayoutParams(
87:                 LinearLayout.LayoutParams.MATCH_PARENT, LinearLayout.LayoutParams.WRAP_CONTENT);
92:         toggleLabel.setTextSize(15);
93:         toggleLabel.setTypeface(null, Typeface.BOLD);
94:         toggleLabel.setTextColor(UiStyles.color(activity, R.color.textPrimary));
95:         toggleRow.addView(toggleLabel, new LinearLayout.LayoutParams(0,
96:                 LinearLayout.LayoutParams.WRAP_CONTENT, 1f));
122:         status.setTextColor(UiStyles.color(activity, R.color.textSecondaryCool));
123:         status.setTextSize(14);
124:         LinearLayout.LayoutParams statusLp = new LinearLayout.LayoutParams(
125:                 LinearLayout.LayoutParams.MATCH_PARENT, LinearLayout.LayoutParams.WRAP_CONTENT);
132:         profilesButton.setTextSize(15);
133:         profilesButton.setTypeface(null, Typeface.BOLD);
134:         profilesButton.setTextColor(0xFFFFFFFF);
138:         LinearLayout.LayoutParams profilesLp = new LinearLayout.LayoutParams(
139:                 LinearLayout.LayoutParams.MATCH_PARENT, dp(48));
145:         profilesCard.setOrientation(LinearLayout.VERTICAL);
146:         profilesCard.setPadding(dp(18), dp(14), dp(18), dp(16));
148:         LinearLayout.LayoutParams profilesCardLp = new LinearLayout.LayoutParams(
149:                 LinearLayout.LayoutParams.MATCH_PARENT, LinearLayout.LayoutParams.WRAP_CONTENT);
155:         profilesTitle.setTextSize(16);
156:         profilesTitle.setTypeface(null, Typeface.BOLD);
157:         profilesTitle.setTextColor(UiStyles.color(activity, R.color.textPrimary));
161:         savedProfilesContainer.setOrientation(LinearLayout.VERTICAL);
162:         LinearLayout.LayoutParams containerLp = new LinearLayout.LayoutParams(
163:                 LinearLayout.LayoutParams.MATCH_PARENT, LinearLayout.LayoutParams.WRAP_CONTENT);
246:             empty.setTextSize(14);
247:             empty.setTextColor(UiStyles.color(activity, R.color.textSecondaryCool));
254:             row.setOrientation(LinearLayout.HORIZONTAL);
255:             row.setGravity(Gravity.CENTER_VERTICAL);
256:             row.setPadding(dp(12), dp(10), dp(12), dp(10));
259:             LinearLayout.LayoutParams rowLp = new LinearLayout.LayoutParams(
260:                     LinearLayout.LayoutParams.MATCH_PARENT, LinearLayout.LayoutParams.WRAP_CONTENT);
267:             nameTv.setTextSize(15);
268:             nameTv.setTypeface(null, Typeface.BOLD);
269:             nameTv.setTextColor(UiStyles.color(activity, R.color.textPrimary));
270:             row.addView(nameTv, new LinearLayout.LayoutParams(
271:                     0, LinearLayout.LayoutParams.WRAP_CONTENT, 1f));
276:                 badge.setTextSize(13);
277:                 badge.setTypeface(null, Typeface.BOLD);
278:                 badge.setTextColor(0xFF60A5FA);
```

### app/src/main/java/com/mapcontrol/ui/builder/DriveModeTabBuilder.java

```text
3: import android.animation.Animator;
4: import android.animation.AnimatorListenerAdapter;
17: import android.view.animation.AccelerateDecelerateInterpolator;
28: import androidx.core.graphics.ColorUtils;
66:         scrollView.setBackgroundColor(Color.TRANSPARENT);
67:         scrollView.setPadding(0, 0, 0, 0);
68:         scrollView.setFillViewport(true);
71:         outer.setOrientation(LinearLayout.VERTICAL);
73:         outer.setPadding(margin, margin, margin, margin);
76:         tabContent.setOrientation(LinearLayout.VERTICAL);
78:         outer.addView(tabContent, new LinearLayout.LayoutParams(
79:                 LinearLayout.LayoutParams.MATCH_PARENT,
80:                 LinearLayout.LayoutParams.WRAP_CONTENT));
81:         scrollView.addView(outer, new ScrollView.LayoutParams(
82:                 LinearLayout.LayoutParams.MATCH_PARENT,
83:                 LinearLayout.LayoutParams.WRAP_CONTENT));
101:         driveModeComponent.addView(bgBase, new FrameLayout.LayoutParams(
102:                 FrameLayout.LayoutParams.MATCH_PARENT, FrameLayout.LayoutParams.MATCH_PARENT));
106:         driveModeComponent.addView(bgReveal, new FrameLayout.LayoutParams(
107:                 FrameLayout.LayoutParams.MATCH_PARENT, FrameLayout.LayoutParams.MATCH_PARENT));
110:         driveModeContent.setOrientation(LinearLayout.VERTICAL);
112:         driveModeContent.setPadding(inner, inner, inner, inner);
113:         driveModeComponent.addView(driveModeContent, new FrameLayout.LayoutParams(
114:                 FrameLayout.LayoutParams.MATCH_PARENT, FrameLayout.LayoutParams.WRAP_CONTENT));
116:         tabContent.addView(driveModeComponent, new LinearLayout.LayoutParams(
117:                 LinearLayout.LayoutParams.MATCH_PARENT,
118:                 LinearLayout.LayoutParams.WRAP_CONTENT));
121:         driveTitleRow.setOrientation(LinearLayout.HORIZONTAL);
122:         driveTitleRow.setGravity(Gravity.CENTER_VERTICAL);
123:         driveTitleRow.setPadding(16, 24, 16, 12);
129:         driveTitleIcon.setLayoutParams(new LinearLayout.LayoutParams(titleIconPx, titleIconPx));
136:         UiStyles.setTextSize(driveModeTitle, R.dimen.text_size_large);
137:         driveModeTitle.setTextColor(UiStyles.color(context, R.color.textPrimary));
138:         driveModeTitle.setTypeface(null, android.graphics.Typeface.BOLD);
139:         LinearLayout.LayoutParams titleRowTextLp = new LinearLayout.LayoutParams(
140:                 0, LinearLayout.LayoutParams.WRAP_CONTENT, 1f);
141:         titleRowTextLp.setMargins((int) (12 * density), 0, 0, 0);
147:         UiStyles.setTextSize(fourWheelModes, R.dimen.text_size_small);
148:         fourWheelModes.setTextColor(UiStyles.color(context, R.color.textPrimary));
154:         fourWheelModes.setMinHeight(UiStyles.dimenPx(context, R.dimen.button_height));
156:         fourWheelModes.setPadding(Math.round(12 * density), 0, Math.round(8 * density), 0);
158:         driveTitleRow.addView(fourWheelModes, new LinearLayout.LayoutParams(
159:                 LinearLayout.LayoutParams.WRAP_CONTENT, LinearLayout.LayoutParams.WRAP_CONTENT));
161:         driveModeContent.addView(driveTitleRow, new LinearLayout.LayoutParams(
162:                 LinearLayout.LayoutParams.MATCH_PARENT,
163:                 LinearLayout.LayoutParams.WRAP_CONTENT));
166:         driveModeTrack.setOrientation(LinearLayout.HORIZONTAL);
167:         driveModeTrack.setBackgroundColor(Color.TRANSPARENT);
168:         driveModeTrack.setPadding(0, 0, 0, 0);
169:         LinearLayout.LayoutParams driveModeTrackParams = new LinearLayout.LayoutParams(
170:                 LinearLayout.LayoutParams.MATCH_PARENT,
171:                 LinearLayout.LayoutParams.WRAP_CONTENT);
172:         driveModeTrackParams.setMargins(0, 0, 0, UiStyles.dimenPx(context, R.dimen.spacing_medium));
175:         driveModeRadioGroup.setOrientation(LinearLayout.HORIZONTAL);
176:         RadioGroup.LayoutParams radioGroupLp = new RadioGroup.LayoutParams(
177:                 LinearLayout.LayoutParams.MATCH_PARENT,
178:                 LinearLayout.LayoutParams.WRAP_CONTENT);
214:             modeCard.setOrientation(LinearLayout.VERTICAL);
215:             modeCard.setGravity(Gravity.CENTER_HORIZONTAL);
217:             modeCard.setPadding(keyPadH, 10, keyPadH, 8);
223:             RadioGroup.LayoutParams cardParams = new RadioGroup.LayoutParams(
224:                     0, pianoKeyH, 1f);
226:             cardParams.setMargins(cardMargin, 0, cardMargin, 0);
229:             titleBand.setOrientation(LinearLayout.VERTICAL);
230:             titleBand.setGravity(Gravity.CENTER);
231:             LinearLayout.LayoutParams titleBandLp = new LinearLayout.LayoutParams(
232:                     LinearLayout.LayoutParams.MATCH_PARENT,
233:                     0,
234:                     1f);
241:             LinearLayout.LayoutParams modeIconLp = new LinearLayout.LayoutParams(modeIconPx, modeIconPx);
252:             modeTitle.setTextColor(UiStyles.color(context, isSelected ? R.color.textPrimary : R.color.textPrimary85));
253:             UiStyles.setTextSize(modeTitle, R.dimen.text_size_small);
254:             modeTitle.setTypeface(null, isSelected ? android.graphics.Typeface.BOLD : android.graphics.Typeface.NORMAL);
255:             modeTitle.setGravity(Gravity.CENTER);
256:             modeTitle.setMaxLines(2);
257:             modeTitle.setEllipsize(TextUtils.TruncateAt.END);
258:             modeTitle.setLineSpacing(2, 1.0f);
259:             LinearLayout.LayoutParams titleLp = new LinearLayout.LayoutParams(
260:                     LinearLayout.LayoutParams.MATCH_PARENT,
261:                     LinearLayout.LayoutParams.WRAP_CONTENT);
265:             LinearLayout.LayoutParams indicatorParams = new LinearLayout.LayoutParams(
266:                     Math.round(24 * density), Math.round(3 * density));
308:         UiStyles.setTextSize(clearSelection, R.dimen.text_size_small);
309:         clearSelection.setTextColor(UiStyles.color(context, R.color.textSecondary));
310:         clearSelection.setGravity(Gravity.CENTER);
311:         clearSelection.setMinHeight(UiStyles.dimenPx(context, R.dimen.button_height));
313:         clearSelection.setPadding(clearPadding, 0, clearPadding, 0);
325:         LinearLayout.LayoutParams clearParams = new LinearLayout.LayoutParams(
326:                 LinearLayout.LayoutParams.WRAP_CONTENT, LinearLayout.LayoutParams.WRAP_CONTENT);
327:         clearParams.setMarginEnd(Math.round(16 * density));
390:             gd.setColor(baseSurface);
391:             gd.setCornerRadius(cornerRadius);
392:             gd.setStroke(Math.max(1, Math.round(1f * density)),
393:                     UiStyles.color(context, R.color.outline));
398:         int startColor = ColorUtils.blendARGB(baseSurface, accent, 0.42f);
399:         int midColor = ColorUtils.blendARGB(baseSurface, accent, 0.24f);
400:         int endColor = ColorUtils.blendARGB(baseSurface, accent, 0.12f);
406:         gd.setCornerRadius(cornerRadius);
407:         int strokeColor = ColorUtils.setAlphaComponent(accent, 160);
408:         gd.setStroke(Math.max(1, Math.round(1.5f * density)), strokeColor);
448:         anim.setDuration(550);
449:         anim.setInterpolator(new AccelerateDecelerateInterpolator());
452:             public void onAnimationEnd(Animator animation) {
453:                 bgBase.setBackground(createModeBackgroundDrawable(context, modeValue));
483:         background.setCornerRadius(16 * density);
485:             background.setColor(ColorUtils.blendARGB(UiStyles.color(context, R.color.assistCardActive), accent, 0.22f));
486:             background.setStroke(Math.round(2 * density), accent);
488:             background.setColor(UiStyles.color(context, R.color.assistCardIdle));
489:             background.setStroke(Math.round(1 * density), UiStyles.color(context, R.color.outline));
494:         title.setTextColor(UiStyles.color(context, R.color.textPrimary));
495:         title.setTypeface(null, selected ? android.graphics.Typeface.BOLD : android.graphics.Typeface.NORMAL);
498:         indBg.setCornerRadius(2 * density);
499:         indBg.setColor(accent);
```

### app/src/main/java/com/mapcontrol/ui/builder/EqualizerTabBuilder.java

```text
83:         scrollView.setBackgroundColor(Color.TRANSPARENT);
84:         scrollView.setFillViewport(true);
87:         outer.setOrientation(LinearLayout.VERTICAL);
89:         outer.setPadding(margin, margin, margin, margin);
91:         scrollView.addView(outer, new ScrollView.LayoutParams(
92:                 LinearLayout.LayoutParams.MATCH_PARENT,
93:                 LinearLayout.LayoutParams.WRAP_CONTENT));
121:         card.setOrientation(LinearLayout.VERTICAL);
123:         card.setPadding(inner, inner, inner, inner);
146:         gainHeader.setOrientation(LinearLayout.HORIZONTAL);
147:         gainHeader.setGravity(Gravity.CENTER_VERTICAL);
149:         gainHeader.setPadding(hPad, UiStyles.dimenPx(context, R.dimen.spacing_small), hPad, 0);
153:         UiStyles.setTextSize(gainTitle, R.dimen.text_size_medium);
154:         gainTitle.setTextColor(UiStyles.color(context, R.color.textPrimary));
155:         gainTitle.setTypeface(null, Typeface.BOLD);
156:         gainHeader.addView(gainTitle, new LinearLayout.LayoutParams(0, LinearLayout.LayoutParams.WRAP_CONTENT, 1f));
160:         UiStyles.setTextSize(gainValue, R.dimen.text_size_medium);
161:         gainValue.setTextColor(UiStyles.color(context, R.color.oemAccent));
162:         gainValue.setTypeface(null, Typeface.BOLD);
189:         UiStyles.setTextSize(limiterNote, R.dimen.text_size_small);
190:         limiterNote.setTextColor(UiStyles.color(context, R.color.textHint));
191:         limiterNote.setPadding(hPad, 0, hPad, UiStyles.dimenPx(context, R.dimen.spacing_small));
198:         presetsHeader.setOrientation(LinearLayout.HORIZONTAL);
199:         presetsHeader.setGravity(Gravity.CENTER_VERTICAL);
200:         presetsHeader.setPadding(hPad, UiStyles.dimenPx(context, R.dimen.spacing_small), hPad, UiStyles.dimenPx(context, R.dimen.spacing_tiny));
204:         UiStyles.setTextSize(presetsTitle, R.dimen.text_size_medium);
205:         presetsTitle.setTextColor(UiStyles.color(context, R.color.textPrimary));
206:         presetsTitle.setTypeface(null, Typeface.BOLD);
207:         presetsHeader.addView(presetsTitle, new LinearLayout.LayoutParams(0, LinearLayout.LayoutParams.WRAP_CONTENT, 1f));
210:         UiStyles.setTextSize(presetStatusBadge, R.dimen.text_size_small);
211:         presetStatusBadge.setTextColor(UiStyles.color(context, R.color.oemAccent));
212:         presetStatusBadge.setTypeface(null, Typeface.BOLD);
221:         UiStyles.setTextSize(btnReset, R.dimen.text_size_small);
225:         LinearLayout.LayoutParams resetLp = new LinearLayout.LayoutParams(
226:                 LinearLayout.LayoutParams.WRAP_CONTENT,
227:                 LinearLayout.LayoutParams.WRAP_CONTENT);
228:         resetLp.setMargins(hPad, UiStyles.dimenPx(context, R.dimen.spacing_medium), hPad, UiStyles.dimenPx(context, R.dimen.spacing_small));
269:         track.setOrientation(LinearLayout.HORIZONTAL);
272:         track.setPadding(pad, pad, pad, pad);
284:             UiStyles.setTextSize(chip, R.dimen.text_size_normal);
285:             chip.setGravity(Gravity.CENTER);
286:             chip.setMinHeight(UiStyles.dimenPx(context, R.dimen.button_height));
287:             chip.setPadding(pad, pad, pad, pad);
290:             track.addView(chip, new LinearLayout.LayoutParams(
291:                     0, LinearLayout.LayoutParams.WRAP_CONTENT, 1f));
296:         LinearLayout.LayoutParams lp = new LinearLayout.LayoutParams(
297:                 LinearLayout.LayoutParams.MATCH_PARENT,
298:                 LinearLayout.LayoutParams.WRAP_CONTENT);
300:         lp.setMargins(h, UiStyles.dimenPx(context, R.dimen.spacing_small), h, UiStyles.dimenPx(context, R.dimen.spacing_small));
314:                 chip.setTextColor(UiStyles.color(context, R.color.textPrimary));
315:                 chip.setTypeface(null, Typeface.BOLD);
317:                 chip.setBackgroundColor(Color.TRANSPARENT);
318:                 chip.setTextColor(UiStyles.color(context, R.color.textSecondary));
319:                 chip.setTypeface(null, Typeface.NORMAL);
340:             bandContainer.setOrientation(LinearLayout.VERTICAL);
342:             bandContainer.setPadding(0, vPad, 0, vPad);
345:             bandHeader.setOrientation(LinearLayout.HORIZONTAL);
346:             bandHeader.setGravity(Gravity.CENTER_VERTICAL);
348:             bandHeader.setPadding(h, 0, h, 0);
354:             UiStyles.setTextSize(hz, R.dimen.text_size_normal);
355:             hz.setTextColor(UiStyles.color(context, R.color.textPrimary));
356:             hz.setTypeface(null, Typeface.BOLD);
357:             bandHeader.addView(hz, new LinearLayout.LayoutParams(0, LinearLayout.LayoutParams.WRAP_CONTENT, 1f));
361:             UiStyles.setTextSize(bandValues[i], R.dimen.text_size_normal);
362:             bandValues[i].setTextColor(UiStyles.color(context, R.color.oemAccent));
363:             bandValues[i].setTypeface(null, Typeface.BOLD);
427:         thumb.setColor(accent);
432:         bar.setMinHeight(trackPx);
439:         bar.setPadding(side, 0, side, 0);
442:         LinearLayout.LayoutParams lp = new LinearLayout.LayoutParams(
443:                 LinearLayout.LayoutParams.MATCH_PARENT,
444:                 UiStyles.dimenPx(context, R.dimen.equalizer_slider_height));
445:         lp.setMargins(h, 0, h, UiStyles.dimenPx(context, R.dimen.spacing_tiny));
452:         divider.setBackgroundColor(UiStyles.color(context, R.color.dividerWhite12));
453:         LinearLayout.LayoutParams lp = new LinearLayout.LayoutParams(
454:                 LinearLayout.LayoutParams.MATCH_PARENT, 1);
456:         lp.setMargins(m, m, m, m);
463:         UiStyles.setTextSize(title, R.dimen.text_size_medium);
464:         title.setTextColor(UiStyles.color(context, R.color.textPrimary));
465:         title.setTypeface(null, Typeface.BOLD);
467:         title.setPadding(h, UiStyles.dimenPx(context, R.dimen.spacing_medium), h, UiStyles.dimenPx(context, R.dimen.spacing_tiny));
474:         UiStyles.setTextSize(desc, R.dimen.text_size_small);
475:         desc.setTextColor(UiStyles.color(context, R.color.textHint));
477:         desc.setPadding(h, 0, h, UiStyles.dimenPx(context, R.dimen.spacing_medium));
481:     private LinearLayout.LayoutParams matchWrap() {
482:         return new LinearLayout.LayoutParams(
483:                 LinearLayout.LayoutParams.MATCH_PARENT,
484:                 LinearLayout.LayoutParams.WRAP_CONTENT);
```

### app/src/main/java/com/mapcontrol/ui/builder/FileUploadTabBuilder.java

```text
47:         scrollView.setBackgroundColor(Color.TRANSPARENT);
48:         scrollView.setPadding(0, 0, 0, 0);
49:         scrollView.setFillViewport(true);
52:         outer.setOrientation(LinearLayout.VERTICAL);
54:         outer.setPadding(margin, margin, margin, margin);
57:         fileUploadTabContent.setOrientation(LinearLayout.VERTICAL);
59:         fileUploadTabContent.setPadding(inner, inner, inner, inner);
63:         row.setOrientation(LinearLayout.HORIZONTAL);
64:         row.setGravity(Gravity.TOP);
68:         leftCol.setOrientation(LinearLayout.VERTICAL);
69:         leftCol.setGravity(Gravity.TOP);
70:         leftCol.setPadding(dp(8), dp(8), dp(16), dp(8));
74:         UiStyles.setTextSize(fileUploadTitle, R.dimen.text_size_large);
75:         fileUploadTitle.setTextColor(UiStyles.color(context, R.color.textPrimary));
76:         fileUploadTitle.setTypeface(null, Typeface.BOLD);
81:         UiStyles.setTextSize(fileUploadDesc, R.dimen.text_size_small);
82:         fileUploadDesc.setTextColor(UiStyles.color(context, R.color.textHint));
83:         fileUploadDesc.setPadding(0, dp(6), 0, dp(16));
88:         btnWebServerToggle.setTextColor(UiStyles.color(context, R.color.textPrimary));
89:         UiStyles.setTextSize(btnWebServerToggle, R.dimen.text_size_normal);
90:         btnWebServerToggle.setTypeface(null, Typeface.BOLD);
92:         btnWebServerToggle.setPadding(dp(16), dp(16), dp(16), dp(16));
93:         leftCol.addView(btnWebServerToggle, new LinearLayout.LayoutParams(
94:                 LinearLayout.LayoutParams.MATCH_PARENT, LinearLayout.LayoutParams.WRAP_CONTENT));
96:         row.addView(leftCol, new LinearLayout.LayoutParams(0, LinearLayout.LayoutParams.WRAP_CONTENT, 0.32f));
100:         divider.setBackgroundColor(UiStyles.color(context, R.color.outlineSubtle));
101:         LinearLayout.LayoutParams divParams = new LinearLayout.LayoutParams(Math.max(1, dp(1)), LinearLayout.LayoutParams.MATCH_PARENT);
102:         divParams.setMargins(dp(8), dp(8), dp(8), dp(8));
107:         rightCol.setOrientation(LinearLayout.VERTICAL);
108:         rightCol.setGravity(Gravity.TOP | Gravity.CENTER_HORIZONTAL);
109:         rightCol.setPadding(dp(16), dp(8), dp(16), dp(8));
113:         UiStyles.setTextSize(webServerStatusText, R.dimen.text_size_medium);
114:         webServerStatusText.setTextColor(UiStyles.color(context, R.color.textHint));
115:         webServerStatusText.setGravity(Gravity.CENTER);
116:         webServerStatusText.setPadding(dp(8), dp(4), dp(8), dp(4));
117:         webServerStatusText.setTypeface(null, Typeface.BOLD);
118:         rightCol.addView(webServerStatusText, new LinearLayout.LayoutParams(
119:                 LinearLayout.LayoutParams.MATCH_PARENT, LinearLayout.LayoutParams.WRAP_CONTENT));
129:         LinearLayout.LayoutParams qrParams = new LinearLayout.LayoutParams(qrSize, qrSize);
134:         row.addView(rightCol, new LinearLayout.LayoutParams(0, LinearLayout.LayoutParams.WRAP_CONTENT, 0.68f));
136:         fileUploadTabContent.addView(row, new LinearLayout.LayoutParams(
137:                 LinearLayout.LayoutParams.MATCH_PARENT, LinearLayout.LayoutParams.WRAP_CONTENT));
147:         outer.addView(fileUploadTabContent, new LinearLayout.LayoutParams(
148:                 LinearLayout.LayoutParams.MATCH_PARENT,
149:                 LinearLayout.LayoutParams.WRAP_CONTENT));
150:         scrollView.addView(outer, new ScrollView.LayoutParams(
151:                 LinearLayout.LayoutParams.MATCH_PARENT,
152:                 LinearLayout.LayoutParams.WRAP_CONTENT));
```

### app/src/main/java/com/mapcontrol/ui/builder/LauncherDashboardBuilder.java

```text
42: import androidx.core.graphics.ColorUtils;
160:         root.setOrientation(LinearLayout.VERTICAL);
162:         root.setBackgroundColor(UiStyles.color(context, R.color.transparent));
167:         cardsRow.setOrientation(portrait ? LinearLayout.VERTICAL : LinearLayout.HORIZONTAL);
175:         musicDriveCard.setMinimumWidth(0);
178:             LinearLayout.LayoutParams quickLp = new LinearLayout.LayoutParams(
179:                     LinearLayout.LayoutParams.MATCH_PARENT, 0, 0.28f);
183:             LinearLayout.LayoutParams vehicleLp = new LinearLayout.LayoutParams(
184:                     LinearLayout.LayoutParams.MATCH_PARENT, 0, 0.44f);
189:             LinearLayout.LayoutParams musicDriveLp = new LinearLayout.LayoutParams(
190:                     LinearLayout.LayoutParams.MATCH_PARENT, 0, 0.28f);
194:             LinearLayout.LayoutParams quickLp = weightedLp(0.22f);
195:             quickLp.setMarginEnd(cardGap / 2);
198:             LinearLayout.LayoutParams vehicleLp = weightedLp(0.56f);
199:             vehicleLp.setMarginStart(cardGap / 2);
200:             vehicleLp.setMarginEnd(cardGap / 2);
203:             LinearLayout.LayoutParams musicDriveLp = weightedLp(0.22f);
204:             musicDriveLp.setMarginStart(cardGap / 2);
208:         LinearLayout.LayoutParams cardsRowLp = new LinearLayout.LayoutParams(
209:                 LinearLayout.LayoutParams.MATCH_PARENT,
210:                 0,
211:                 1f);
270:             quickAccessTitleView.setTextColor(UiStyles.color(context, R.color.textMuted));
281:             quickAccessDivider.setBackgroundColor(
282:                     UiStyles.color(context, R.color.outlineSubtle));
285:             shortcutsLabelView.setTextColor(UiStyles.color(context, R.color.textSecondary));
288:             quickAppsLabelView.setTextColor(UiStyles.color(context, R.color.textSecondary));
327:             view.setTextColor(UiStyles.color(view.getContext(), colorRes));
368:         speedValueView.setTextColor(speedColor);
374:         lowFuelValueView.setTextColor(lowFuelColor);
487:         card.setOrientation(LinearLayout.VERTICAL);
490:         card.setPadding(pad, pad, pad, pad);
494:         titleRow.setOrientation(LinearLayout.HORIZONTAL);
495:         titleRow.setGravity(Gravity.CENTER_VERTICAL);
499:         titleView.setTextSize(TypedValue.COMPLEX_UNIT_PX,
500:                 context.getResources().getDimension(R.dimen.text_size_small));
501:         titleView.setTypeface(null, Typeface.BOLD);
502:         titleView.setTextColor(UiStyles.color(context, R.color.textMuted));
504:         titleView.setLetterSpacing(0.08f);
506:         titleRow.addView(titleView, new LinearLayout.LayoutParams(
507:                 0, LinearLayout.LayoutParams.WRAP_CONTENT, 1f));
514:         gearButton.setPadding(0, 0, 0, 0);
521:         LinearLayout.LayoutParams gearLp = new LinearLayout.LayoutParams(headerBtnSize, headerBtnSize);
528:         exitButton.setPadding(0, 0, 0, 0);
539:         LinearLayout.LayoutParams exitLp = new LinearLayout.LayoutParams(headerBtnSize, headerBtnSize);
540:         exitLp.setMarginStart(UiStyles.dimenPx(context, R.dimen.spacing_tiny));
548:         quickScroll.setFillViewport(false);
551:         body.setOrientation(LinearLayout.VERTICAL);
552:         body.setGravity(Gravity.TOP);
553:         quickScroll.addView(body, new android.widget.FrameLayout.LayoutParams(-1, -2));
554:         LinearLayout.LayoutParams quickScrollLp = new LinearLayout.LayoutParams(-1, 0, 1);
563:         LinearLayout.LayoutParams gridLp = new LinearLayout.LayoutParams(
564:                 LinearLayout.LayoutParams.MATCH_PARENT,
565:                 LinearLayout.LayoutParams.WRAP_CONTENT);
570:         divider.setBackgroundColor(UiStyles.color(context, R.color.outlineSubtle));
572:         LinearLayout.LayoutParams dividerLp = new LinearLayout.LayoutParams(
573:                 LinearLayout.LayoutParams.MATCH_PARENT,
574:                 Math.max(1, UiStyles.dimenPx(context, R.dimen.spacing_tiny) / 2));
584:         quickAppsGrid.setColumnCount(LauncherQuickAppsStore.GRID_COLUMNS);
587:         LinearLayout.LayoutParams appsGridLp = new LinearLayout.LayoutParams(-1, -2);
621:                 ((TextView) label).setTextColor(labelColor);
650:             GridLayout.LayoutParams lp = new GridLayout.LayoutParams();
652:             lp.height = GridLayout.LayoutParams.WRAP_CONTENT;
654:             lp.setMargins(gap, gap, gap, gap);
686:             GridLayout.LayoutParams lp = new GridLayout.LayoutParams();
688:             lp.height = GridLayout.LayoutParams.WRAP_CONTENT;
690:             lp.setMargins(gap, gap, gap, gap);
697:         tile.setOrientation(LinearLayout.VERTICAL);
698:         tile.setGravity(Gravity.CENTER_HORIZONTAL);
708:         LinearLayout.LayoutParams slotLp = new LinearLayout.LayoutParams(slotSize, slotSize);
715:         FrameLayout.LayoutParams iconLp = new FrameLayout.LayoutParams(iconSize, iconSize);
760:         centerPanelHost.addView(litePanelView, new FrameLayout.LayoutParams(
761:                 FrameLayout.LayoutParams.MATCH_PARENT,
762:                 FrameLayout.LayoutParams.MATCH_PARENT,
763:                 Gravity.CENTER));
799:         label.setTextSize(TypedValue.COMPLEX_UNIT_PX,
800:                 context.getResources().getDimension(R.dimen.text_size_small));
801:         label.setTypeface(null, Typeface.BOLD);
802:         label.setTextColor(UiStyles.color(context, R.color.textSecondary));
804:         label.setLetterSpacing(0.06f);
810:         tile.setOrientation(LinearLayout.VERTICAL);
811:         tile.setGravity(Gravity.CENTER_HORIZONTAL);
820:         LinearLayout.LayoutParams slotLp = new LinearLayout.LayoutParams(slotSize, slotSize);
826:         FrameLayout.LayoutParams iconLp = new FrameLayout.LayoutParams(iconSize, iconSize);
950:         list.setOrientation(LinearLayout.VERTICAL);
952:         list.setPadding(pad, pad / 2, pad, pad / 2);
957:             row.setTextSize(TypedValue.COMPLEX_UNIT_PX,
958:                     context.getResources().getDimension(R.dimen.text_size_medium));
959:             row.setTextColor(UiStyles.color(context, R.color.textPrimary));
960:             row.setPadding(pad / 2, pad, pad / 2, pad);
974:             list.addView(row, new LinearLayout.LayoutParams(
975:                     LinearLayout.LayoutParams.MATCH_PARENT,
976:                     LinearLayout.LayoutParams.WRAP_CONTENT));
1126:         grid.setColumnCount(QUICK_SHORTCUT_COLUMNS);
1169:         tile.setOrientation(LinearLayout.VERTICAL);
1170:         tile.setGravity(Gravity.CENTER_HORIZONTAL);
1189:         LinearLayout.LayoutParams slotLp = new LinearLayout.LayoutParams(slotSize, slotSize);
1195:         FrameLayout.LayoutParams iconLp = new FrameLayout.LayoutParams(iconSize, iconSize);
1204:         label.setTextSize(TypedValue.COMPLEX_UNIT_PX,
1205:                 context.getResources().getDimension(R.dimen.text_size_small));
1206:         label.setGravity(Gravity.CENTER);
1207:         label.setMinHeight(UiStyles.dimenPx(context, R.dimen.launcher_tile_label_min_height));
1208:         label.setMaxLines(2);
1209:         label.setEllipsize(android.text.TextUtils.TruncateAt.END);
1210:         label.setTextColor(UiStyles.color(context, colorRes));
1211:         label.setPadding(0, UiStyles.dimenPx(context, R.dimen.spacing_tiny), 0, 0);
1217:         GridLayout.LayoutParams lp = new GridLayout.LayoutParams();
1219:         lp.height = GridLayout.LayoutParams.WRAP_CONTENT;
1221:         lp.setMargins(spacing / 2, spacing / 2, spacing / 2, spacing / 2);
1235:         card.setMinimumWidth(0);
1243:         LinearLayout.LayoutParams hostLp = new LinearLayout.LayoutParams(
1244:                 LinearLayout.LayoutParams.MATCH_PARENT,
1245:                 0,
1246:                 1f);
1252:         panelHost.addView(musicPanel, new FrameLayout.LayoutParams(
1253:                 FrameLayout.LayoutParams.MATCH_PARENT,
1254:                 FrameLayout.LayoutParams.MATCH_PARENT));
1255:         panelHost.addView(drivePanel, new FrameLayout.LayoutParams(
1256:                 FrameLayout.LayoutParams.MATCH_PARENT,
1257:                 FrameLayout.LayoutParams.MATCH_PARENT));
1265:         track.setOrientation(LinearLayout.HORIZONTAL);
1268:         track.setPadding(tpad, tpad, tpad, tpad);
1295:         LinearLayout.LayoutParams segLp = new LinearLayout.LayoutParams(
1296:                 0, LinearLayout.LayoutParams.WRAP_CONTENT, 1f);
1303:         tab.setTextSize(TypedValue.COMPLEX_UNIT_PX,
1304:                 context.getResources().getDimension(R.dimen.text_size_normal));
1305:         tab.setGravity(Gravity.CENTER);
1306:         tab.setMinHeight(UiStyles.dimenPx(context, R.dimen.button_height));
1314:             musicTab.setTextColor(UiStyles.color(context, R.color.textPrimary));
1315:             musicTab.setTypeface(null, Typeface.BOLD);
1316:             driveTab.setBackgroundColor(android.graphics.Color.TRANSPARENT);
1317:             driveTab.setTextColor(UiStyles.color(context, R.color.textSecondary));
1318:             driveTab.setTypeface(null, Typeface.NORMAL);
1321:             driveTab.setTextColor(UiStyles.color(context, R.color.textPrimary));
1322:             driveTab.setTypeface(null, Typeface.BOLD);
1323:             musicTab.setBackgroundColor(android.graphics.Color.TRANSPARENT);
1324:             musicTab.setTextColor(UiStyles.color(context, R.color.textSecondary));
1325:             musicTab.setTypeface(null, Typeface.NORMAL);
1340:         panel.setOrientation(LinearLayout.VERTICAL);
1341:         panel.setGravity(Gravity.CENTER);
1348:         body.setOrientation(LinearLayout.VERTICAL);
1351:         driveSection.setOrientation(LinearLayout.VERTICAL);
1352:         driveSection.setGravity(Gravity.CENTER);
1357:         divider.setBackgroundColor(UiStyles.color(context, R.color.glassStroke));
1358:         LinearLayout.LayoutParams dividerLp = new LinearLayout.LayoutParams(
1359:                 LinearLayout.LayoutParams.MATCH_PARENT,
1360:                 Math.max(1, UiStyles.dimenPx(context, R.dimen.spacing_tiny) / 2));
1366:         fuelTitle.setTextSize(TypedValue.COMPLEX_UNIT_PX,
1367:                 context.getResources().getDimension(R.dimen.launcher_dashboard_fuel_section_title));
1368:         fuelTitle.setIncludeFontPadding(false);
1372:         fuelSection.setOrientation(LinearLayout.VERTICAL);
1374:         LinearLayout.LayoutParams fuelLp = weightedSectionLp(42f);
1384:         speedValueView.setTextSize(TypedValue.COMPLEX_UNIT_PX,
1385:                 context.getResources().getDimension(R.dimen.launcher_dashboard_speed_text));
1386:         speedValueView.setTypeface(Typeface.create("sans-serif-light", Typeface.NORMAL));
1387:         speedValueView.setIncludeFontPadding(false);
1388:         speedValueView.setTextColor(UiStyles.color(context, R.color.textPrimary));
1389:         speedValueView.setGravity(Gravity.CENTER);
1390:         speedValueView.setMaxLines(1);
1391:         speedValueView.setEllipsize(android.text.TextUtils.TruncateAt.END);
1396:         unit.setTextSize(TypedValue.COMPLEX_UNIT_PX,
1397:                 context.getResources().getDimension(R.dimen.launcher_dashboard_drive_unit));
1398:         unit.setTextColor(UiStyles.color(context, R.color.textSecondary));
1399:         unit.setGravity(Gravity.CENTER);
1400:         unit.setIncludeFontPadding(false);
1406:         gearLabelView.setTextSize(TypedValue.COMPLEX_UNIT_PX,
1407:                 context.getResources().getDimension(R.dimen.launcher_dashboard_drive_gear));
1408:         gearLabelView.setTypeface(null, Typeface.BOLD);
1409:         gearLabelView.setTextColor(UiStyles.color(context, R.color.accentHighlight));
1410:         gearLabelView.setGravity(Gravity.CENTER);
1411:         gearLabelView.setIncludeFontPadding(false);
1416:         powerStatusView.setTextSize(TypedValue.COMPLEX_UNIT_PX,
1417:                 context.getResources().getDimension(R.dimen.launcher_dashboard_drive_status));
1418:         powerStatusView.setTextColor(UiStyles.color(context, R.color.textSecondary));
1419:         powerStatusView.setGravity(Gravity.CENTER);
1420:         powerStatusView.setIncludeFontPadding(false);
1440:         headerRow.setOrientation(LinearLayout.HORIZONTAL);
1441:         headerRow.setGravity(Gravity.CENTER_VERTICAL);
1445:         titleView.setTextSize(TypedValue.COMPLEX_UNIT_PX,
1446:                 context.getResources().getDimension(R.dimen.text_size_small));
1447:         titleView.setTypeface(null, Typeface.BOLD);
1448:         titleView.setTextColor(UiStyles.color(context, R.color.textMuted));
1450:         titleView.setLetterSpacing(0.08f);
1451:         headerRow.addView(titleView, new LinearLayout.LayoutParams(
1452:                 0, LinearLayout.LayoutParams.WRAP_CONTENT, 1f));
1455:         headerRow.addView(statusStrip, new LinearLayout.LayoutParams(
1456:                 LinearLayout.LayoutParams.WRAP_CONTENT,
1457:                 LinearLayout.LayoutParams.WRAP_CONTENT));
1465:         body.addView(centerPanelHost, new LinearLayout.LayoutParams(
1466:                 LinearLayout.LayoutParams.MATCH_PARENT,
1467:                 0,
1468:                 1f));
1476:         strip.setOrientation(LinearLayout.HORIZONTAL);
1477:         strip.setGravity(Gravity.CENTER_VERTICAL);
1487:         LinearLayout.LayoutParams modeLp = new LinearLayout.LayoutParams(
1488:                 LinearLayout.LayoutParams.WRAP_CONTENT, LinearLayout.LayoutParams.WRAP_CONTENT);
1489:         modeLp.setMarginStart(gap);
1492:         LinearLayout.LayoutParams phoneLp = new LinearLayout.LayoutParams(
1493:                 LinearLayout.LayoutParams.WRAP_CONTENT, LinearLayout.LayoutParams.WRAP_CONTENT);
1494:         phoneLp.setMarginStart(gap);
1497:         LinearLayout.LayoutParams projLp = new LinearLayout.LayoutParams(
1498:                 LinearLayout.LayoutParams.WRAP_CONTENT, LinearLayout.LayoutParams.WRAP_CONTENT);
1499:         projLp.setMarginStart(gap);
1505:         LinearLayout.LayoutParams soundLp = new LinearLayout.LayoutParams(
1506:                 LinearLayout.LayoutParams.WRAP_CONTENT, LinearLayout.LayoutParams.WRAP_CONTENT);
1507:         soundLp.setMarginStart(gap);
1629:             setOrientation(HORIZONTAL);
1630:             setGravity(Gravity.CENTER_VERTICAL);
1633:             setPadding(padH, padV, padH, padV);
1646:             addView(icon, new LinearLayout.LayoutParams(iconSize, iconSize));
1650:             value.setSingleLine(true);
1651:             value.setTypeface(null, Typeface.BOLD);
1652:             value.setTextSize(TypedValue.COMPLEX_UNIT_PX,
1653:                     context.getResources().getDimension(R.dimen.text_size_small));
1654:             value.setIncludeFontPadding(false);
1655:             LinearLayout.LayoutParams valParams = new LinearLayout.LayoutParams(
1656:                     LinearLayout.LayoutParams.WRAP_CONTENT,
1657:                     LinearLayout.LayoutParams.WRAP_CONTENT);
1658:             valParams.setMarginStart(dp(6));
1664:             value.setTextColor(accent);
1667:             background.setCornerRadius(dp(14));
1668:             background.setColor(ColorUtils.blendARGB(
1669:                     UiStyles.color(context, R.color.surfaceCardInner), accent, 0.08f));
1670:             background.setStroke(Math.max(1, dp(1)), ColorUtils.setAlphaComponent(accent, 45));
1679:         mediaHost.setBackgroundColor(UiStyles.color(context, R.color.transparent));
1683:         mediaActiveContainer.setOrientation(LinearLayout.VERTICAL);
1684:         mediaActiveContainer.setGravity(Gravity.CENTER_HORIZONTAL);
1686:         mediaActiveContainer.setPadding(mediaPad, mediaPad, mediaPad, mediaPad);
1690:         metaBlock.setOrientation(LinearLayout.VERTICAL);
1691:         metaBlock.setGravity(Gravity.CENTER);
1701:         LinearLayout.LayoutParams thumbLp = new LinearLayout.LayoutParams(thumbSize, thumbSize);
1707:         trackTitleView.setTypeface(null, Typeface.BOLD);
1708:         trackTitleView.setGravity(Gravity.CENTER);
1712:         trackArtistView.setGravity(Gravity.CENTER);
1714:         LinearLayout.LayoutParams artistLp = matchWidthWrap();
1721:         progressRow.setOrientation(LinearLayout.HORIZONTAL);
1722:         progressRow.setGravity(Gravity.CENTER_VERTICAL);
1723:         progressRow.setPadding(0, UiStyles.dimenPx(context, R.dimen.spacing_small), 0, 0);
1725:         mediaPositionView.setGravity(Gravity.CENTER);
1727:         mediaDurationView.setGravity(Gravity.CENTER);
1731:         LinearLayout.LayoutParams progressLp = new LinearLayout.LayoutParams(
1732:                 0, LinearLayout.LayoutParams.WRAP_CONTENT, 1f);
1733:         progressLp.setMargins(UiStyles.dimenPx(context, R.dimen.spacing_tiny), 0,
1734:                 UiStyles.dimenPx(context, R.dimen.spacing_tiny), 0);
1740:         mediaActiveContainer.addView(metaBlock, new LinearLayout.LayoutParams(
1741:                 LinearLayout.LayoutParams.MATCH_PARENT,
1742:                 0,
1743:                 1f));
1747:         bottomControls.setOrientation(LinearLayout.VERTICAL);
1748:         bottomControls.setGravity(Gravity.CENTER_HORIZONTAL);
1777:         LinearLayout.LayoutParams volumeRowLp = matchWidthWrap();
1783:         LinearLayout.LayoutParams bottomLp = matchWidthWrap();
1787:         mediaHost.addView(mediaActiveContainer, new FrameLayout.LayoutParams(
1788:                 FrameLayout.LayoutParams.MATCH_PARENT,
1789:                 FrameLayout.LayoutParams.MATCH_PARENT));
1792:         mediaEmptyContainer.setOrientation(LinearLayout.VERTICAL);
1793:         mediaEmptyContainer.setGravity(Gravity.CENTER);
1795:         mediaEmptyContainer.setPadding(emptyPad, emptyPad, emptyPad, emptyPad);
1800:         LinearLayout.LayoutParams iconLp = new LinearLayout.LayoutParams(emptyIconSize, emptyIconSize);
1805:         mediaEmptyTitleView.setTextSize(TypedValue.COMPLEX_UNIT_PX,
1806:                 context.getResources().getDimension(R.dimen.launcher_dashboard_media_title));
1807:         mediaEmptyTitleView.setTypeface(null, Typeface.BOLD);
1808:         mediaEmptyTitleView.setTextColor(UiStyles.color(context, R.color.textPrimary));
1809:         mediaEmptyTitleView.setGravity(Gravity.CENTER);
1810:         mediaEmptyTitleView.setPadding(0, UiStyles.dimenPx(context, R.dimen.spacing_small), 0, 0);
1814:         mediaEmptyBodyView.setTextSize(TypedValue.COMPLEX_UNIT_PX,
1815:                 context.getResources().getDimension(R.dimen.launcher_dashboard_media_artist));
1816:         mediaEmptyBodyView.setTextColor(UiStyles.color(context, R.color.textSecondary));
1817:         mediaEmptyBodyView.setGravity(Gravity.CENTER);
1820:         mediaHost.addView(mediaEmptyContainer, new FrameLayout.LayoutParams(
1821:                 FrameLayout.LayoutParams.MATCH_PARENT,
1822:                 FrameLayout.LayoutParams.MATCH_PARENT));
1824:         mediaSection.addView(mediaHost, new LinearLayout.LayoutParams(
1825:                 LinearLayout.LayoutParams.MATCH_PARENT,
1826:                 LinearLayout.LayoutParams.MATCH_PARENT));
1831:         card.setOrientation(LinearLayout.VERTICAL);
1834:         card.setPadding(pad, pad, pad, pad);
1843:         titleView.setTextSize(TypedValue.COMPLEX_UNIT_PX,
1844:                 context.getResources().getDimension(R.dimen.text_size_small));
1845:         titleView.setTypeface(null, Typeface.BOLD);
1846:         titleView.setTextColor(UiStyles.color(context, R.color.textMuted));
1848:         titleView.setLetterSpacing(0.08f);
1855:         body.setOrientation(LinearLayout.VERTICAL);
1856:         LinearLayout.LayoutParams bodyLp = new LinearLayout.LayoutParams(
1857:                 LinearLayout.LayoutParams.MATCH_PARENT,
1858:                 0,
1859:                 1f);
1867:         row.setOrientation(LinearLayout.HORIZONTAL);
1868:         row.setGravity(Gravity.CENTER);
1869:         row.setPadding(0, 0, 0, 0);
1873:         label.setTextSize(TypedValue.COMPLEX_UNIT_PX,
1874:                 context.getResources().getDimension(R.dimen.launcher_dashboard_drive_metric_label));
1875:         label.setTextColor(UiStyles.color(context, R.color.textMuted));
1876:         label.setIncludeFontPadding(false);
1877:         label.setPadding(0, 0, UiStyles.dimenPx(context, R.dimen.spacing_tiny), 0);
1882:         value.setTextSize(TypedValue.COMPLEX_UNIT_PX,
1883:                 context.getResources().getDimension(R.dimen.launcher_dashboard_drive_metric_value));
1884:         value.setTypeface(null, Typeface.BOLD);
1885:         value.setTextColor(UiStyles.color(context, R.color.textPrimary));
1886:         value.setIncludeFontPadding(false);
1895:         block.setOrientation(LinearLayout.VERTICAL);
1897:         block.setPadding(0, padV, 0, padV);
1900:         header.setOrientation(LinearLayout.HORIZONTAL);
1901:         header.setGravity(Gravity.CENTER_VERTICAL);
1905:         label.setTextSize(TypedValue.COMPLEX_UNIT_PX,
1906:                 context.getResources().getDimension(R.dimen.launcher_dashboard_fuel_label));
1907:         label.setTextColor(UiStyles.color(context, R.color.textMuted));
1908:         label.setIncludeFontPadding(false);
1909:         header.addView(label, new LinearLayout.LayoutParams(0, LinearLayout.LayoutParams.WRAP_CONTENT, 1f));
1913:         value.setTextSize(TypedValue.COMPLEX_UNIT_PX,
1914:                 context.getResources().getDimension(R.dimen.launcher_dashboard_stat_value));
1915:         value.setTypeface(null, Typeface.BOLD);
1916:         value.setTextColor(UiStyles.color(context, R.color.textPrimary));
1917:         value.setIncludeFontPadding(false);
1926:         LinearLayout.LayoutParams barLp = matchWidthWrap();
1936:         row.setOrientation(LinearLayout.HORIZONTAL);
1937:         row.setGravity(Gravity.CENTER_VERTICAL);
1938:         row.setPadding(0, 0, 0, 0);
1942:         label.setTextSize(TypedValue.COMPLEX_UNIT_PX,
1943:                 context.getResources().getDimension(R.dimen.launcher_dashboard_fuel_label));
1944:         label.setTextColor(UiStyles.color(context, R.color.textMuted));
1945:         label.setIncludeFontPadding(false);
1946:         label.setMaxLines(1);
1947:         label.setEllipsize(android.text.TextUtils.TruncateAt.END);
1948:         row.addView(label, new LinearLayout.LayoutParams(0, LinearLayout.LayoutParams.WRAP_CONTENT, 1f));
1952:         value.setTextSize(TypedValue.COMPLEX_UNIT_PX,
1953:                 context.getResources().getDimension(R.dimen.launcher_dashboard_stat_value));
1954:         value.setTypeface(null, Typeface.BOLD);
1955:         value.setTextColor(UiStyles.color(context, R.color.textPrimary));
1956:         value.setGravity(Gravity.END);
1957:         value.setIncludeFontPadding(false);
1958:         value.setMaxLines(1);
1959:         value.setEllipsize(android.text.TextUtils.TruncateAt.END);
1961:         value.setMinWidth(0);
1962:         LinearLayout.LayoutParams valueLp = new LinearLayout.LayoutParams(
1963:                 LinearLayout.LayoutParams.WRAP_CONTENT,
1964:                 LinearLayout.LayoutParams.WRAP_CONTENT);
1965:         valueLp.setMarginStart(UiStyles.dimenPx(context, R.dimen.spacing_tiny));
1975:         textView.setTextSize(TypedValue.COMPLEX_UNIT_PX,
1976:                 context.getResources().getDimension(textSizeRes));
1977:         textView.setTextColor(UiStyles.color(context, colorRes));
1979:             textView.setMaxLines(maxLines);
1980:             textView.setEllipsize(android.text.TextUtils.TruncateAt.END);
1988:         row.setOrientation(LinearLayout.HORIZONTAL);
1989:         row.setGravity(Gravity.CENTER_VERTICAL);
1990:         row.setPadding(0, UiStyles.dimenPx(context, R.dimen.spacing_tiny), 0, 0);
1993:                 new LinearLayout.LayoutParams(0, LinearLayout.LayoutParams.WRAP_CONTENT, 1f));
1995:                 new LinearLayout.LayoutParams(0, LinearLayout.LayoutParams.WRAP_CONTENT, 1f));
1997:                 new LinearLayout.LayoutParams(0, LinearLayout.LayoutParams.WRAP_CONTENT, 1f));
2003:         FrameLayout.LayoutParams btnLp = new FrameLayout.LayoutParams(buttonSize, buttonSize);
2028:         FrameLayout.LayoutParams iconLp = new FrameLayout.LayoutParams(iconSize, iconSize);
2034:     private static LinearLayout.LayoutParams weightedLp(float weight) {
2035:         return new LinearLayout.LayoutParams(0, LinearLayout.LayoutParams.MATCH_PARENT, weight);
2038:     private static LinearLayout.LayoutParams weightedSectionLp(float weight) {
2039:         return new LinearLayout.LayoutParams(
2040:                 LinearLayout.LayoutParams.MATCH_PARENT,
2041:                 0,
2042:                 weight);
2045:     private LinearLayout.LayoutParams matchWidthWrap() {
2046:         return new LinearLayout.LayoutParams(
2047:                 LinearLayout.LayoutParams.MATCH_PARENT,
2048:                 LinearLayout.LayoutParams.WRAP_CONTENT);
```

### app/src/main/java/com/mapcontrol/ui/builder/LauncherTabBuilder.java

```text
41:         root.setBackgroundColor(UiStyles.color(context, R.color.transparent));
43:         root.setPadding(margin, margin, margin, margin);
47:         root.addView(dashboardBuilder.build(), new FrameLayout.LayoutParams(
48:                 FrameLayout.LayoutParams.MATCH_PARENT,
49:                 FrameLayout.LayoutParams.MATCH_PARENT));
```

### app/src/main/java/com/mapcontrol/ui/builder/LogTabBuilder.java

```text
54:         tabContent.setOrientation(LinearLayout.VERTICAL);
57:         tabContent.setPadding(margin, margin, margin, margin);
58:         tabContent.setBackgroundColor(Color.TRANSPARENT);
62:         glassShell.setOrientation(LinearLayout.VERTICAL);
65:         glassShell.setPadding(inner, inner, inner, inner);
67:         LinearLayout.LayoutParams shellParams = new LinearLayout.LayoutParams(
68:                 LinearLayout.LayoutParams.MATCH_PARENT,
69:                 0, 1f);
73:         logControlPanel.setOrientation(LinearLayout.HORIZONTAL);
74:         logControlPanel.setPadding(0, 0, 0, 8);
75:         logControlPanel.setGravity(android.view.Gravity.CENTER_VERTICAL);
79:         logTitle.setTextSize(20);
80:         logTitle.setTextColor(UiStyles.color(context, R.color.textPrimary));
81:         logTitle.setTypeface(null, android.graphics.Typeface.BOLD);
82:         LinearLayout.LayoutParams logTitleParams = new LinearLayout.LayoutParams(
83:                 0, LinearLayout.LayoutParams.WRAP_CONTENT, 1f);
88:         btnCameraTest.setTextColor(UiStyles.color(context, R.color.textPrimary));
89:         btnCameraTest.setTextSize(14);
91:         btnCameraTest.setPadding(16, 8, 16, 8);
109:         LinearLayout.LayoutParams cameraTestParams = new LinearLayout.LayoutParams(
110:                 LinearLayout.LayoutParams.WRAP_CONTENT,
111:                 LinearLayout.LayoutParams.WRAP_CONTENT);
112:         cameraTestParams.setMargins(0, 0, 8, 0);
117:         btnDmsFaces.setTextColor(UiStyles.color(context, R.color.textPrimary));
118:         btnDmsFaces.setTextSize(14);
120:         btnDmsFaces.setPadding(16, 8, 16, 8);
123:         LinearLayout.LayoutParams dmsFacesParams = new LinearLayout.LayoutParams(
124:                 LinearLayout.LayoutParams.WRAP_CONTENT,
125:                 LinearLayout.LayoutParams.WRAP_CONTENT);
126:         dmsFacesParams.setMargins(0, 0, 8, 0);
131:         btnWelcome.setTextColor(UiStyles.color(context, R.color.textPrimary));
132:         btnWelcome.setTextSize(14);
133:         btnWelcome.setTypeface(null, Typeface.BOLD);
135:         btnWelcome.setPadding(16, 8, 16, 8);
140:         LinearLayout.LayoutParams welcomeParams = new LinearLayout.LayoutParams(
141:                 LinearLayout.LayoutParams.WRAP_CONTENT,
142:                 LinearLayout.LayoutParams.WRAP_CONTENT);
143:         welcomeParams.setMargins(0, 0, 8, 0);
147:         btnClearLogs.setTextColor(UiStyles.color(context, R.color.textPrimary));
148:         btnClearLogs.setTextSize(20);
149:         btnClearLogs.setBackgroundColor(UiStyles.color(context, R.color.buttonDestructiveBg));
150:         btnClearLogs.setPadding(0, 0, 0, 0);
153:         LinearLayout.LayoutParams clearLogsParams = new LinearLayout.LayoutParams(56, 56);
157:         ttsCard.setOrientation(LinearLayout.VERTICAL);
160:         ttsCard.setPadding(16, 14, 16, 14);
161:         LinearLayout.LayoutParams ttsCardParams = new LinearLayout.LayoutParams(
162:                 LinearLayout.LayoutParams.MATCH_PARENT,
163:                 LinearLayout.LayoutParams.WRAP_CONTENT);
164:         ttsCardParams.setMargins(0, 0, 0, 10);
168:         ttsSectionTitle.setTextSize(TypedValue.COMPLEX_UNIT_SP, 13);
169:         ttsSectionTitle.setTextColor(UiStyles.color(context, R.color.textSecondary));
170:         ttsCard.addView(ttsSectionTitle, new LinearLayout.LayoutParams(
171:                 LinearLayout.LayoutParams.MATCH_PARENT,
172:                 LinearLayout.LayoutParams.WRAP_CONTENT));
176:         ttsInput.setTextColor(UiStyles.color(context, R.color.textTertiary));
178:         ttsInput.setTextSize(TypedValue.COMPLEX_UNIT_SP, 14);
179:         ttsInput.setTypeface(Typeface.MONOSPACE);
181:         ttsInput.setMaxLines(5);
184:         ttsInput.setPadding(14, 12, 14, 12);
186:         ttsFieldBg.setColor(UiStyles.color(context, R.color.logFieldBg));
187:         ttsFieldBg.setCornerRadius(context.getResources().getDimension(R.dimen.oem_button_radius));
188:         ttsFieldBg.setStroke(1, UiStyles.color(context, R.color.outlineStrong));
190:         LinearLayout.LayoutParams etParams = new LinearLayout.LayoutParams(
191:                 LinearLayout.LayoutParams.MATCH_PARENT,
192:                 LinearLayout.LayoutParams.WRAP_CONTENT);
193:         etParams.setMargins(0, 8, 0, 0);
197:         ttsButtons.setOrientation(LinearLayout.HORIZONTAL);
198:         ttsButtons.setGravity(Gravity.END);
199:         ttsButtons.setPadding(0, 10, 0, 0);
203:         btnReadAloud.setTextColor(UiStyles.color(context, R.color.textPrimary));
204:         btnReadAloud.setTextSize(15);
205:         btnReadAloud.setTypeface(null, Typeface.BOLD);
207:         btnReadAloud.setPadding(20, 12, 20, 12);
213:         LinearLayout.LayoutParams readParams = new LinearLayout.LayoutParams(
214:                 LinearLayout.LayoutParams.WRAP_CONTENT,
215:                 LinearLayout.LayoutParams.WRAP_CONTENT);
217:         ttsCard.addView(ttsButtons, new LinearLayout.LayoutParams(
218:                 LinearLayout.LayoutParams.MATCH_PARENT,
219:                 LinearLayout.LayoutParams.WRAP_CONTENT));
223:         btnClusterVDBusTest.setTextSize(TypedValue.COMPLEX_UNIT_SP, 14);
224:         btnClusterVDBusTest.setTextColor(UiStyles.color(context, R.color.textPrimary));
226:         btnClusterVDBusTest.setPadding(16, 12, 16, 12);
229:         LinearLayout.LayoutParams benchBtnLp = new LinearLayout.LayoutParams(
230:                 LinearLayout.LayoutParams.MATCH_PARENT,
231:                 LinearLayout.LayoutParams.WRAP_CONTENT);
232:         benchBtnLp.setMargins(0, 10, 0, 0);
235:         upperScroll.setFillViewport(true);
237:         upperColumn.setOrientation(LinearLayout.VERTICAL);
239:         upperColumn.addView(logControlPanel, new LinearLayout.LayoutParams(
240:                 LinearLayout.LayoutParams.MATCH_PARENT,
241:                 LinearLayout.LayoutParams.WRAP_CONTENT));
244:         upperScroll.addView(upperColumn, new LinearLayout.LayoutParams(
245:                 LinearLayout.LayoutParams.MATCH_PARENT,
246:                 LinearLayout.LayoutParams.WRAP_CONTENT));
247:         LinearLayout.LayoutParams upperParams = new LinearLayout.LayoutParams(
248:                 LinearLayout.LayoutParams.MATCH_PARENT,
249:                 0, 0.34f);
253:         terminalContainer.setOrientation(LinearLayout.VERTICAL);
254:         terminalContainer.setBackgroundColor(UiStyles.color(context, R.color.logTerminalChrome));
255:         terminalContainer.setPadding(0, 0, 0, 0);
258:         terminalHeader.setOrientation(LinearLayout.HORIZONTAL);
259:         terminalHeader.setBackgroundColor(UiStyles.color(context, R.color.surfaceColor));
260:         terminalHeader.setPadding(16, 12, 16, 12);
261:         terminalHeader.setGravity(android.view.Gravity.CENTER_VERTICAL);
264:         dotsLayout.setOrientation(LinearLayout.HORIZONTAL);
268:         redDot.setTextColor(UiStyles.color(context, R.color.terminalDotRed));
269:         redDot.setTextSize(16);
270:         redDot.setPadding(0, 0, 8, 0);
275:         yellowDot.setTextColor(UiStyles.color(context, R.color.terminalDotYellow));
276:         yellowDot.setTextSize(16);
277:         yellowDot.setPadding(0, 0, 8, 0);
282:         greenDot.setTextColor(UiStyles.color(context, R.color.terminalDotGreen));
283:         greenDot.setTextSize(16);
286:         terminalHeader.addView(dotsLayout, new LinearLayout.LayoutParams(
287:                 LinearLayout.LayoutParams.WRAP_CONTENT,
288:                 LinearLayout.LayoutParams.WRAP_CONTENT));
292:         terminalPath.setTextColor(UiStyles.color(context, R.color.textSecondaryCool));
293:         terminalPath.setTextSize(11);
294:         terminalPath.setTypeface(android.graphics.Typeface.MONOSPACE);
295:         LinearLayout.LayoutParams pathParams = new LinearLayout.LayoutParams(
296:                 0, LinearLayout.LayoutParams.WRAP_CONTENT, 1f);
300:         terminalContainer.addView(terminalHeader, new LinearLayout.LayoutParams(
301:                 LinearLayout.LayoutParams.MATCH_PARENT,
302:                 LinearLayout.LayoutParams.WRAP_CONTENT));
305:         logScrollView.setFillViewport(true);
307:         logScrollView.setBackgroundColor(UiStyles.color(context, R.color.logTerminalChrome));
308:         logScrollView.setPadding(12, 12, 12, 12);
311:         logsTextView.setTextColor(UiStyles.color(context, R.color.textTertiary));
312:         logsTextView.setTextSize(TypedValue.COMPLEX_UNIT_SP, 14);
313:         logsTextView.setTypeface(android.graphics.Typeface.MONOSPACE);
314:         logsTextView.setPadding(10, 12, 10, 12);
315:         logsTextView.setLineSpacing(TypedValue.applyDimension(
316:                 TypedValue.COMPLEX_UNIT_DIP, 5f, context.getResources().getDisplayMetrics()), 1.0f);
317:         logsTextView.setMinHeight((int) TypedValue.applyDimension(
318:                 TypedValue.COMPLEX_UNIT_DIP, 120f, context.getResources().getDisplayMetrics()));
321:         LinearLayout.LayoutParams scrollParams = new LinearLayout.LayoutParams(
322:                 LinearLayout.LayoutParams.MATCH_PARENT,
323:                 0, 1f);
326:         LinearLayout.LayoutParams terminalParams = new LinearLayout.LayoutParams(
327:                 LinearLayout.LayoutParams.MATCH_PARENT,
328:                 0, 0.66f);
329:         terminalParams.setMargins(0, 8, 0, 0);
```

### app/src/main/java/com/mapcontrol/ui/builder/ProfileTabBuilder.java

```text
94:         profileScrollView.setBackgroundColor(Color.TRANSPARENT);
95:         profileScrollView.setFillViewport(true);
98:         outer.setOrientation(LinearLayout.VERTICAL);
100:         outer.setPadding(margin, margin, margin, margin);
103:         profileTabContent.setOrientation(LinearLayout.VERTICAL);
105:         profileTabContent.setPadding(inner, inner, inner, inner);
110:         loginCard.setOrientation(LinearLayout.VERTICAL);
111:         loginCard.setPadding(20, 20, 20, 20);
113:         cardBg.setColor(UiStyles.color(context, R.color.surfaceCard));
114:         cardBg.setCornerRadius(context.getResources().getDimension(R.dimen.oem_button_radius));
115:         cardBg.setStroke(1, UiStyles.color(context, R.color.outline));
119:         profileLoginStatusText.setTextSize(15);
120:         profileLoginStatusText.setTextColor(UiStyles.color(context, R.color.textPrimary87));
121:         profileLoginStatusText.setPadding(0, 0, 0, 20);
122:         profileLoginStatusText.setLineSpacing(4, 1.2f);
126:         profileEmailLabel.setTextSize(14);
127:         profileEmailLabel.setTextColor(UiStyles.color(context, R.color.textSecondary));
128:         profileEmailLabel.setPadding(0, 0, 0, 8);
134:         profileEmailInput.setTextSize(16);
135:         profileEmailInput.setPadding(16, 16, 16, 16);
137:         inputBg.setColor(UiStyles.color(context, R.color.surfaceColor));
138:         inputBg.setCornerRadius(context.getResources().getDimension(R.dimen.oem_button_radius));
139:         inputBg.setStroke(1, UiStyles.color(context, R.color.outlineMuted));
141:         profileEmailInput.setTextColor(UiStyles.color(context, R.color.textPrimary));
143:         LinearLayout.LayoutParams emailParams = new LinearLayout.LayoutParams(
144:                 LinearLayout.LayoutParams.MATCH_PARENT,
145:                 LinearLayout.LayoutParams.WRAP_CONTENT);
146:         emailParams.setMargins(0, 0, 0, 16);
151:         profileCodeLabel.setTextSize(14);
152:         profileCodeLabel.setTextColor(UiStyles.color(context, R.color.textSecondary));
153:         profileCodeLabel.setPadding(0, 0, 0, 8);
159:         profileCodeInput.setTextSize(16);
160:         profileCodeInput.setPadding(16, 16, 16, 16);
162:         profileCodeInput.setTextColor(UiStyles.color(context, R.color.textPrimary));
164:         LinearLayout.LayoutParams codeParams = new LinearLayout.LayoutParams(
165:                 LinearLayout.LayoutParams.MATCH_PARENT,
166:                 LinearLayout.LayoutParams.WRAP_CONTENT);
167:         codeParams.setMargins(0, 0, 0, 16);
171:         profileButtonsContainer.setOrientation(LinearLayout.HORIZONTAL);
172:         profileButtonsContainer.setPadding(0, 0, 0, 0);
176:         profileSendCodeButton.setTextSize(14);
177:         profileSendCodeButton.setTextColor(UiStyles.color(context, R.color.textPrimary));
179:         profileSendCodeButton.setPadding(20, 14, 20, 14);
180:         LinearLayout.LayoutParams sendBtnParams = new LinearLayout.LayoutParams(
181:                 0, LinearLayout.LayoutParams.WRAP_CONTENT, 1.0f);
182:         sendBtnParams.setMargins(0, 0, 8, 0);
188:         profileLoginButton.setTextSize(14);
189:         profileLoginButton.setTextColor(UiStyles.color(context, R.color.textPrimary));
191:         profileLoginButton.setPadding(20, 14, 20, 14);
192:         LinearLayout.LayoutParams loginBtnParams = new LinearLayout.LayoutParams(
193:                 0, LinearLayout.LayoutParams.WRAP_CONTENT, 1.0f);
194:         loginBtnParams.setMargins(8, 0, 0, 0);
202:         profileLogoutButton.setTextSize(14);
203:         profileLogoutButton.setTextColor(UiStyles.color(context, R.color.textPrimary));
205:         profileLogoutButton.setPadding(20, 14, 20, 14);
207:         LinearLayout.LayoutParams logoutBtnParams = new LinearLayout.LayoutParams(
208:                 LinearLayout.LayoutParams.MATCH_PARENT,
209:                 LinearLayout.LayoutParams.WRAP_CONTENT);
210:         logoutBtnParams.setMargins(0, 16, 0, 8);
215:         profilePlatformButton.setTextSize(14);
216:         profilePlatformButton.setTextColor(UiStyles.color(context, R.color.textPrimary));
218:         profilePlatformButton.setPadding(20, 14, 20, 14);
220:         LinearLayout.LayoutParams platformBtnParams = new LinearLayout.LayoutParams(
221:                 LinearLayout.LayoutParams.MATCH_PARENT,
222:                 LinearLayout.LayoutParams.WRAP_CONTENT);
223:         platformBtnParams.setMargins(0, 0, 0, 0);
242:         LinearLayout.LayoutParams loginCardParams = new LinearLayout.LayoutParams(
243:                 LinearLayout.LayoutParams.MATCH_PARENT,
244:                 LinearLayout.LayoutParams.WRAP_CONTENT);
245:         loginCardParams.setMargins(16, 0, 16, 32);
343:         actionsCard.setOrientation(LinearLayout.VERTICAL);
344:         actionsCard.setPadding(20, 20, 20, 20);
346:         actionsCardBg.setColor(UiStyles.color(context, R.color.surfaceCard));
347:         actionsCardBg.setCornerRadius(context.getResources().getDimension(R.dimen.oem_button_radius));
348:         actionsCardBg.setStroke(1, UiStyles.color(context, R.color.outline));
353:         actionsTitle.setTextSize(17);
354:         actionsTitle.setTextColor(UiStyles.color(context, R.color.textPrimary));
355:         actionsTitle.setTypeface(null, android.graphics.Typeface.NORMAL);
356:         actionsTitle.setPadding(0, 0, 0, 16);
360:         buttonsRow.setOrientation(LinearLayout.HORIZONTAL);
361:         buttonsRow.setPadding(0, 0, 0, 0);
365:         profileSaveDataButton.setTextSize(14);
366:         profileSaveDataButton.setTextColor(UiStyles.color(context, R.color.textPrimary));
368:         profileSaveDataButton.setPadding(16, 14, 16, 14);
372:         LinearLayout.LayoutParams saveBtnParams = new LinearLayout.LayoutParams(
373:                 0, LinearLayout.LayoutParams.WRAP_CONTENT, 1.0f);
374:         saveBtnParams.setMargins(0, 0, 8, 0);
379:         profileLoadDataButton.setTextSize(14);
380:         profileLoadDataButton.setTextColor(UiStyles.color(context, R.color.textPrimary));
382:         profileLoadDataButton.setPadding(16, 14, 16, 14);
386:         LinearLayout.LayoutParams loadBtnParams = new LinearLayout.LayoutParams(
387:                 0, LinearLayout.LayoutParams.WRAP_CONTENT, 1.0f);
388:         loadBtnParams.setMargins(8, 0, 0, 0);
391:         LinearLayout.LayoutParams buttonsRowParams = new LinearLayout.LayoutParams(
392:                 LinearLayout.LayoutParams.MATCH_PARENT,
393:                 LinearLayout.LayoutParams.WRAP_CONTENT);
394:         buttonsRowParams.setMargins(0, 0, 0, 16);
478:         LinearLayout.LayoutParams actionsCardParams = new LinearLayout.LayoutParams(
479:                 LinearLayout.LayoutParams.MATCH_PARENT,
480:                 LinearLayout.LayoutParams.WRAP_CONTENT);
481:         actionsCardParams.setMargins(16, 0, 16, 32);
490:         outer.addView(profileTabContent, new LinearLayout.LayoutParams(
491:                 LinearLayout.LayoutParams.MATCH_PARENT,
492:                 LinearLayout.LayoutParams.WRAP_CONTENT));
493:         profileScrollView.addView(outer, new ScrollView.LayoutParams(
494:                 LinearLayout.LayoutParams.MATCH_PARENT,
495:                 LinearLayout.LayoutParams.WRAP_CONTENT));
603:         profileSaveLocationButton.setTextSize(16);
604:         profileSaveLocationButton.setTextColor(UiStyles.color(context, R.color.textPrimary));
606:         profileSaveLocationButton.setPadding(24, 18, 24, 18);
610:         LinearLayout.LayoutParams locationBtnParams = new LinearLayout.LayoutParams(
611:                 LinearLayout.LayoutParams.MATCH_PARENT,
612:                 LinearLayout.LayoutParams.WRAP_CONTENT);
613:         locationBtnParams.setMargins(0, 0, 0, 16);
618:         autoLocationTitle.setTextSize(15);
619:         autoLocationTitle.setTextColor(UiStyles.color(context, R.color.textPrimary87));
620:         autoLocationTitle.setTypeface(null, android.graphics.Typeface.NORMAL);
621:         autoLocationTitle.setPadding(0, 0, 0, 8);
626:         autoLocationDesc.setTextSize(13);
627:         autoLocationDesc.setTextColor(UiStyles.color(context, R.color.textHint));
628:         autoLocationDesc.setPadding(0, 0, 0, 12);
632:         autoLocationSegmentBlock.setOrientation(LinearLayout.VERTICAL);
658:         LinearLayout.LayoutParams autoLocationRadioGroupParams = new LinearLayout.LayoutParams(
659:                 LinearLayout.LayoutParams.MATCH_PARENT,
660:                 LinearLayout.LayoutParams.WRAP_CONTENT);
661:         autoLocationRadioGroupParams.setMargins(0, 0, 0, 0);
880:         dialogLayout.setOrientation(LinearLayout.VERTICAL);
881:         dialogLayout.setPadding(32, 32, 32, 32);
882:         dialogLayout.setGravity(android.view.Gravity.CENTER);
890:         LinearLayout.LayoutParams qrParams = new LinearLayout.LayoutParams(qrSize, qrSize);
892:         qrParams.setMargins(0, 0, 0, 0);
```

### app/src/main/java/com/mapcontrol/ui/builder/ProjectionTabBuilder.java

```text
83:         scrollView.setBackgroundColor(Color.TRANSPARENT);
84:         scrollView.setPadding(0, 0, 0, 0);
85:         scrollView.setFillViewport(true);
88:         outer.setOrientation(LinearLayout.VERTICAL);
90:         outer.setPadding(margin, margin, margin, margin);
93:         projectionTabContent.setOrientation(LinearLayout.VERTICAL);
95:         projectionTabContent.setPadding(inner, inner, inner, inner);
97:         outer.addView(projectionTabContent, new LinearLayout.LayoutParams(
98:                 LinearLayout.LayoutParams.MATCH_PARENT,
99:                 LinearLayout.LayoutParams.WRAP_CONTENT));
100:         scrollView.addView(outer, new ScrollView.LayoutParams(
101:                 LinearLayout.LayoutParams.MATCH_PARENT,
102:                 LinearLayout.LayoutParams.WRAP_CONTENT));
105:         projectionTabContent.addView(grid, new LinearLayout.LayoutParams(
106:                 LinearLayout.LayoutParams.MATCH_PARENT,
107:                 LinearLayout.LayoutParams.WRAP_CONTENT));
125:         card.setOrientation(LinearLayout.VERTICAL);
127:         card.setPadding(padding, padding, padding, padding);
136:         UiStyles.setTextSize(title, R.dimen.text_size_medium);
137:         title.setTextColor(UiStyles.color(context, R.color.textPrimary));
138:         title.setTypeface(null, Typeface.BOLD);
139:         title.setPadding(0, 0, 0, UiStyles.dimenPx(context, R.dimen.spacing_tiny));
145:             UiStyles.setTextSize(desc, R.dimen.text_size_small);
146:             desc.setTextColor(UiStyles.color(context, R.color.textHint));
147:             desc.setPadding(0, 0, 0, UiStyles.dimenPx(context, R.dimen.spacing_small));
152:     private LinearLayout.LayoutParams matchWrap() {
153:         return new LinearLayout.LayoutParams(
154:                 LinearLayout.LayoutParams.MATCH_PARENT,
155:                 LinearLayout.LayoutParams.WRAP_CONTENT);
164:         UiStyles.setTextSize(projectionStatusText, R.dimen.text_size_small);
165:         projectionStatusText.setTextColor(UiStyles.color(context, R.color.textHint));
166:         projectionStatusText.setPadding(0, 0, 0, UiStyles.dimenPx(context, R.dimen.spacing_small));
170:         controlButtonContainer.setOrientation(LinearLayout.HORIZONTAL);
171:         controlButtonContainer.setPadding(0, 0, 0, UiStyles.dimenPx(context, R.dimen.spacing_medium));
175:         btnOpen.setTextColor(UiStyles.color(context, R.color.textPrimary));
176:         UiStyles.setTextSize(btnOpen, R.dimen.text_size_normal);
177:         btnOpen.setTypeface(null, Typeface.BOLD);
179:         btnOpen.setPadding(16, 20, 16, 20);
180:         LinearLayout.LayoutParams openParams = new LinearLayout.LayoutParams(
181:                 0, LinearLayout.LayoutParams.WRAP_CONTENT, 1f);
182:         openParams.setMargins(0, 0, 8, 0);
188:         btnClose.setTextColor(UiStyles.color(context, R.color.textPrimary));
189:         UiStyles.setTextSize(btnClose, R.dimen.text_size_normal);
190:         btnClose.setTypeface(null, Typeface.BOLD);
192:         btnClose.setPadding(16, 20, 16, 20);
193:         LinearLayout.LayoutParams closeParams = new LinearLayout.LayoutParams(
194:                 0, LinearLayout.LayoutParams.WRAP_CONTENT, 1f);
217:         appInfo.setOrientation(LinearLayout.HORIZONTAL);
218:         appInfo.setGravity(Gravity.CENTER_VERTICAL);
219:         appInfo.setPadding(0, UiStyles.dimenPx(context, R.dimen.spacing_small), 0, UiStyles.dimenPx(context, R.dimen.spacing_small));
222:         iconBox.setOrientation(LinearLayout.VERTICAL);
225:         iconBox.setGravity(Gravity.CENTER);
227:         LinearLayout.LayoutParams iconBoxParams = new LinearLayout.LayoutParams(iconBoxPx, iconBoxPx);
228:         iconBoxParams.setMargins(0, 0, UiStyles.dimenPx(context, R.dimen.spacing_small), 0);
234:         mapIcon.setLayoutParams(new LinearLayout.LayoutParams(mapIconPx, mapIconPx));
240:         textInfo.setOrientation(LinearLayout.VERTICAL);
244:         targetAppLabel.setTextColor(UiStyles.color(context, R.color.textPrimary));
245:         UiStyles.setTextSize(targetAppLabel, R.dimen.text_size_medium);
246:         targetAppLabel.setTypeface(null, Typeface.BOLD);
251:         appDesc.setTextColor(UiStyles.color(context, R.color.textHint));
252:         UiStyles.setTextSize(appDesc, R.dimen.text_size_small);
255:         appInfo.addView(textInfo, new LinearLayout.LayoutParams(0, LinearLayout.LayoutParams.WRAP_CONTENT, 1f));
259:         appButtons.setOrientation(LinearLayout.HORIZONTAL);
260:         appButtons.setPadding(0, UiStyles.dimenPx(context, R.dimen.spacing_tiny), 0, 0);
264:         btnSelectApp.setTextColor(UiStyles.color(context, R.color.textPrimary80));
265:         UiStyles.setTextSize(btnSelectApp, R.dimen.text_size_small);
266:         btnSelectApp.setTypeface(null, Typeface.NORMAL);
268:         btnSelectApp.setPadding(16, 14, 16, 14);
269:         LinearLayout.LayoutParams selectParams = new LinearLayout.LayoutParams(
270:                 0, LinearLayout.LayoutParams.WRAP_CONTENT, 1f);
271:         selectParams.setMargins(0, 0, 6, 0);
277:         btnLaunchOnCluster.setTextColor(UiStyles.color(context, R.color.textPrimary));
278:         UiStyles.setTextSize(btnLaunchOnCluster, R.dimen.text_size_small);
279:         btnLaunchOnCluster.setTypeface(null, Typeface.BOLD);
281:         btnLaunchOnCluster.setPadding(16, 14, 16, 14);
282:         LinearLayout.LayoutParams launchParams = new LinearLayout.LayoutParams(
283:                 0, LinearLayout.LayoutParams.WRAP_CONTENT, 1f);
284:         launchParams.setMargins(6, 0, 0, 0);
431:         card.setOrientation(LinearLayout.VERTICAL);
463:         card.setOrientation(LinearLayout.VERTICAL);
565:         parent.addView(layoutToggle, new LinearLayout.LayoutParams(
566:                 LinearLayout.LayoutParams.WRAP_CONTENT, LinearLayout.LayoutParams.WRAP_CONTENT));
568:         expanded.setOrientation(LinearLayout.VERTICAL);
580:         UiStyles.setTextSize(desc, R.dimen.text_size_small);
581:         desc.setTextColor(UiStyles.color(context, R.color.textHint));
582:         desc.setPadding(20, 0, 20, 8);
583:         parent.addView(desc, new LinearLayout.LayoutParams(
584:                 LinearLayout.LayoutParams.MATCH_PARENT,
585:                 LinearLayout.LayoutParams.WRAP_CONTENT));
588:         block.setOrientation(LinearLayout.VERTICAL);
589:         block.setPadding(20, 0, 20, 8);
590:         parent.addView(block, new LinearLayout.LayoutParams(
591:                 LinearLayout.LayoutParams.MATCH_PARENT,
592:                 LinearLayout.LayoutParams.WRAP_CONTENT));
607:         btnResetLayout.setTextColor(UiStyles.color(context, R.color.textPrimary80));
608:         UiStyles.setTextSize(btnResetLayout, R.dimen.text_size_small);
610:         btnResetLayout.setPadding(16, 14, 16, 14);
611:         LinearLayout.LayoutParams resetLp = new LinearLayout.LayoutParams(
612:                 LinearLayout.LayoutParams.MATCH_PARENT,
613:                 LinearLayout.LayoutParams.WRAP_CONTENT);
694:         UiStyles.setTextSize(cardTitle, R.dimen.text_size_small);
695:         cardTitle.setTextColor(UiStyles.color(context, R.color.textPrimary));
696:         cardTitle.setTypeface(null, Typeface.BOLD);
697:         cardTitle.setPadding(0, 12, 0, 4);
698:         parent.addView(cardTitle, new LinearLayout.LayoutParams(
699:                 LinearLayout.LayoutParams.MATCH_PARENT,
700:                 LinearLayout.LayoutParams.WRAP_CONTENT));
753:         row.setOrientation(LinearLayout.HORIZONTAL);
754:         row.setGravity(Gravity.CENTER_VERTICAL);
755:         row.setPadding(0, 8, 0, 0);
759:         UiStyles.setTextSize(label, R.dimen.text_size_small);
760:         label.setTextColor(UiStyles.color(context, R.color.textSecondary));
761:         row.addView(label, new LinearLayout.LayoutParams(
762:                 0, LinearLayout.LayoutParams.WRAP_CONTENT, 1f));
765:         UiStyles.setTextSize(value, R.dimen.text_size_small);
766:         value.setTextColor(UiStyles.color(context, R.color.textHint));
767:         value.setGravity(Gravity.END);
768:         row.addView(value, new LinearLayout.LayoutParams(
769:                 LinearLayout.LayoutParams.WRAP_CONTENT,
770:                 LinearLayout.LayoutParams.WRAP_CONTENT));
772:         parent.addView(row, new LinearLayout.LayoutParams(
773:                 LinearLayout.LayoutParams.MATCH_PARENT,
774:                 LinearLayout.LayoutParams.WRAP_CONTENT));
805:         row.setOrientation(LinearLayout.HORIZONTAL);
806:         row.setGravity(Gravity.CENTER_VERTICAL);
807:         row.setPadding(0, 8, 0, 0);
811:         UiStyles.setTextSize(label, R.dimen.text_size_small);
812:         label.setTextColor(UiStyles.color(context, R.color.textSecondary));
813:         row.addView(label, new LinearLayout.LayoutParams(
814:                 0, LinearLayout.LayoutParams.WRAP_CONTENT, 1f));
817:         UiStyles.setTextSize(value, R.dimen.text_size_small);
818:         value.setTextColor(UiStyles.color(context, R.color.textHint));
819:         value.setGravity(Gravity.END);
822:         row.addView(value, new LinearLayout.LayoutParams(
823:                 LinearLayout.LayoutParams.WRAP_CONTENT,
824:                 LinearLayout.LayoutParams.WRAP_CONTENT));
826:         parent.addView(row, new LinearLayout.LayoutParams(
827:                 LinearLayout.LayoutParams.MATCH_PARENT,
828:                 LinearLayout.LayoutParams.WRAP_CONTENT));
862:         thumb.setColor(accent);
867:         bar.setMinHeight(trackPx);
874:         bar.setPadding(side, 0, side, 0);
878:     private LinearLayout.LayoutParams clusterOffsetSeekBarLayoutParams() {
879:         int h = UiStyles.dimenPx(context, R.dimen.spacing_small);
880:         LinearLayout.LayoutParams lp = new LinearLayout.LayoutParams(
881:                 LinearLayout.LayoutParams.MATCH_PARENT,
882:                 UiStyles.dimenPx(context, R.dimen.equalizer_slider_height));
883:         lp.setMargins(0, 0, 0, h);
891:         button.setAlpha(0.6f);
894:             button.setAlpha(1.0f);
```

### app/src/main/java/com/mapcontrol/ui/builder/SettingsTabBuilder.java

```text
68:         scrollView.setBackgroundColor(Color.TRANSPARENT);
69:         scrollView.setPadding(0, 0, 0, 0);
70:         scrollView.setFillViewport(true);
73:         outer.setOrientation(LinearLayout.VERTICAL);
75:         outer.setPadding(margin, margin, margin, margin);
78:         settingsTabContent.setOrientation(LinearLayout.VERTICAL);
80:         settingsTabContent.setPadding(inner, inner, inner, inner);
82:         outer.addView(settingsTabContent, new LinearLayout.LayoutParams(
83:                 LinearLayout.LayoutParams.MATCH_PARENT,
84:                 LinearLayout.LayoutParams.WRAP_CONTENT));
86:         scrollView.addView(outer, new ScrollView.LayoutParams(
87:                 LinearLayout.LayoutParams.MATCH_PARENT,
88:                 LinearLayout.LayoutParams.WRAP_CONTENT));
103:         settingsTabContent.addView(advancedToggle, new LinearLayout.LayoutParams(
104:                 LinearLayout.LayoutParams.WRAP_CONTENT, LinearLayout.LayoutParams.WRAP_CONTENT));
106:         advanced.setOrientation(LinearLayout.VERTICAL);
123:         card.setOrientation(LinearLayout.VERTICAL);
125:         card.setPadding(padding, padding, padding, padding);
127:         LinearLayout.LayoutParams lp = new LinearLayout.LayoutParams(
128:                 LinearLayout.LayoutParams.MATCH_PARENT, LinearLayout.LayoutParams.WRAP_CONTENT);
202:         UiStyles.setTextSize(sectionTitle, R.dimen.text_size_medium);
203:         sectionTitle.setTextColor(UiStyles.color(context, R.color.textPrimary));
204:         sectionTitle.setTypeface(null, android.graphics.Typeface.BOLD);
205:         sectionTitle.setPadding(0, 0, 0, 8);
206:         parentContainer.addView(sectionTitle, new LinearLayout.LayoutParams(
207:                 LinearLayout.LayoutParams.MATCH_PARENT,
208:                 LinearLayout.LayoutParams.WRAP_CONTENT));
212:         UiStyles.setTextSize(sectionDesc, R.dimen.text_size_small);
213:         sectionDesc.setTextColor(UiStyles.color(context, R.color.textHint));
214:         sectionDesc.setPadding(0, 0, 0, 8);
215:         parentContainer.addView(sectionDesc, new LinearLayout.LayoutParams(
216:                 LinearLayout.LayoutParams.MATCH_PARENT,
217:                 LinearLayout.LayoutParams.WRAP_CONTENT));
222:         LinearLayout.LayoutParams toggleLp = new LinearLayout.LayoutParams(
223:                 LinearLayout.LayoutParams.WRAP_CONTENT,
224:                 LinearLayout.LayoutParams.WRAP_CONTENT);
225:         toggleLp.setMargins(16, 0, 16, 8);
305:         UiStyles.setTextSize(appInfoTitle, R.dimen.text_size_medium);
306:         appInfoTitle.setTextColor(UiStyles.color(context, R.color.textPrimary));
307:         appInfoTitle.setTypeface(null, android.graphics.Typeface.BOLD);
308:         appInfoTitle.setPadding(16, 24, 16, 8);
309:         parentContainer.addView(appInfoTitle, new LinearLayout.LayoutParams(
310:                 LinearLayout.LayoutParams.MATCH_PARENT,
311:                 LinearLayout.LayoutParams.WRAP_CONTENT));
315:         UiStyles.setTextSize(versionTitle, R.dimen.text_size_normal);
316:         versionTitle.setTextColor(UiStyles.color(context, R.color.textPrimary));
317:         versionTitle.setTypeface(null, android.graphics.Typeface.BOLD);
318:         versionTitle.setPadding(16, 16, 16, 8);
319:         parentContainer.addView(versionTitle, new LinearLayout.LayoutParams(
320:                 LinearLayout.LayoutParams.MATCH_PARENT,
321:                 LinearLayout.LayoutParams.WRAP_CONTENT));
330:         UiStyles.setTextSize(versionText, R.dimen.text_size_small);
331:         versionText.setTextColor(UiStyles.color(context, R.color.textHint));
332:         versionText.setPadding(16, 0, 16, 16);
333:         parentContainer.addView(versionText, new LinearLayout.LayoutParams(
334:                 LinearLayout.LayoutParams.MATCH_PARENT,
335:                 LinearLayout.LayoutParams.WRAP_CONTENT));
340:         UiStyles.setTextSize(latestVersionText, R.dimen.text_size_small);
341:         latestVersionText.setTextColor(UiStyles.color(context, R.color.textHint));
342:         latestVersionText.setPadding(16, 0, 16, 16);
343:         parentContainer.addView(latestVersionText, new LinearLayout.LayoutParams(
344:                 LinearLayout.LayoutParams.MATCH_PARENT,
345:                 LinearLayout.LayoutParams.WRAP_CONTENT));
349:         UiStyles.setTextSize(freeInstallTitle, R.dimen.text_size_normal);
350:         freeInstallTitle.setTextColor(UiStyles.color(context, R.color.textPrimary));
351:         freeInstallTitle.setTypeface(null, android.graphics.Typeface.BOLD);
352:         freeInstallTitle.setPadding(16, 16, 16, 8);
353:         parentContainer.addView(freeInstallTitle, new LinearLayout.LayoutParams(
354:                 LinearLayout.LayoutParams.MATCH_PARENT,
355:                 LinearLayout.LayoutParams.WRAP_CONTENT));
359:         UiStyles.setTextSize(freeInstallText, R.dimen.text_size_small);
360:         freeInstallText.setTextColor(UiStyles.color(context, R.color.textHint));
361:         freeInstallText.setPadding(16, 0, 16, 16);
362:         parentContainer.addView(freeInstallText, new LinearLayout.LayoutParams(
363:                 LinearLayout.LayoutParams.MATCH_PARENT,
364:                 LinearLayout.LayoutParams.WRAP_CONTENT));
368:         UiStyles.setTextSize(changelogTitle, R.dimen.text_size_normal);
369:         changelogTitle.setTextColor(UiStyles.color(context, R.color.textPrimary));
370:         changelogTitle.setTypeface(null, android.graphics.Typeface.BOLD);
371:         changelogTitle.setPadding(16, 16, 16, 8);
372:         parentContainer.addView(changelogTitle, new LinearLayout.LayoutParams(
373:                 LinearLayout.LayoutParams.MATCH_PARENT,
374:                 LinearLayout.LayoutParams.WRAP_CONTENT));
379:         UiStyles.setTextSize(changelogText, R.dimen.text_size_small);
380:         changelogText.setTextColor(UiStyles.color(context, R.color.textHint));
381:         changelogText.setPadding(16, 0, 16, 16);
382:         changelogText.setLineSpacing(4, 1.0f);
383:         parentContainer.addView(changelogText, new LinearLayout.LayoutParams(
384:                 LinearLayout.LayoutParams.MATCH_PARENT,
385:                 LinearLayout.LayoutParams.WRAP_CONTENT));
523:         container.setOrientation(LinearLayout.VERTICAL);
540:         UiStyles.setTextSize(launcherHomeSettingsLink, R.dimen.text_size_small);
541:         launcherHomeSettingsLink.setTextColor(UiStyles.color(context, R.color.accentHighlight));
542:         launcherHomeSettingsLink.setPadding(16, 4, 16, 16);
547:         container.addView(launcherHomeSettingsLink, new LinearLayout.LayoutParams(
548:                 LinearLayout.LayoutParams.MATCH_PARENT,
549:                 LinearLayout.LayoutParams.WRAP_CONTENT));
556:         UiStyles.setTextSize(sectionTitle, R.dimen.text_size_medium);
557:         sectionTitle.setTextColor(UiStyles.color(context, R.color.textPrimary));
558:         sectionTitle.setTypeface(null, android.graphics.Typeface.BOLD);
559:         sectionTitle.setPadding(0, 0, 0, 8);
560:         parentContainer.addView(sectionTitle, new LinearLayout.LayoutParams(
561:                 LinearLayout.LayoutParams.MATCH_PARENT,
562:                 LinearLayout.LayoutParams.WRAP_CONTENT));
566:         UiStyles.setTextSize(sectionDesc, R.dimen.text_size_small);
567:         sectionDesc.setTextColor(UiStyles.color(context, R.color.textHint));
568:         sectionDesc.setLineSpacing(3, 1.05f);
569:         sectionDesc.setPadding(0, 0, 0, 8);
570:         parentContainer.addView(sectionDesc, new LinearLayout.LayoutParams(
571:                 LinearLayout.LayoutParams.MATCH_PARENT,
572:                 LinearLayout.LayoutParams.WRAP_CONTENT));
575:         UiStyles.setTextSize(currentLabel, R.dimen.text_size_small);
576:         currentLabel.setTextColor(UiStyles.color(context, R.color.textSecondary));
577:         currentLabel.setPadding(16, 0, 16, 8);
579:         parentContainer.addView(currentLabel, new LinearLayout.LayoutParams(
580:                 LinearLayout.LayoutParams.MATCH_PARENT,
581:                 LinearLayout.LayoutParams.WRAP_CONTENT));
586:         LinearLayout.LayoutParams pickLp = new LinearLayout.LayoutParams(
587:                 LinearLayout.LayoutParams.WRAP_CONTENT,
588:                 LinearLayout.LayoutParams.WRAP_CONTENT);
589:         pickLp.setMargins(16, 0, 16, 16);
```

### app/src/main/java/com/mapcontrol/ui/builder/SideRailBuilder.java

```text
78:         sideRail.setOrientation(LinearLayout.VERTICAL);
80:         sideRail.setPadding(0, 0, 0, 0);
83:         sideRailTopBar.setOrientation(LinearLayout.HORIZONTAL);
84:         sideRailTopBar.setBackgroundColor(Color.TRANSPARENT);
85:         sideRailTopBar.setPadding(36, 26, 28, 22);
86:         sideRailTopBar.setGravity(Gravity.CENTER_VERTICAL);
87:         sideRailTopBar.setMinimumHeight((int) (76 * context.getResources().getDisplayMetrics().density));
116:         appTitleText.setTextSize(28);
117:         appTitleText.setTextColor(UiStyles.color(context, R.color.textPrimary));
118:         appTitleText.setTypeface(null, android.graphics.Typeface.BOLD);
121:         sideRail.addView(sideRailTopBar, new LinearLayout.LayoutParams(
122:                 LinearLayout.LayoutParams.MATCH_PARENT,
123:                 LinearLayout.LayoutParams.WRAP_CONTENT));
126:         menuScrollView.setBackgroundColor(Color.TRANSPARENT);
127:         menuScrollView.setFillViewport(false);
130:         menuContainer.setOrientation(LinearLayout.VERTICAL);
131:         menuContainer.setBackgroundColor(Color.TRANSPARENT);
168:         menuScrollView.addView(menuContainer, new LinearLayout.LayoutParams(
169:                 LinearLayout.LayoutParams.MATCH_PARENT,
170:                 LinearLayout.LayoutParams.WRAP_CONTENT));
172:         LinearLayout.LayoutParams menuScrollParams = new LinearLayout.LayoutParams(
173:                 LinearLayout.LayoutParams.MATCH_PARENT,
174:                 0,
175:                 1.0f);
188:         sideRail.setOrientation(LinearLayout.VERTICAL);
193:         menuScroll.setFillViewport(true);
196:         menuContainer.setOrientation(LinearLayout.HORIZONTAL);
197:         menuContainer.setGravity(Gravity.CENTER_VERTICAL);
232:         menuScroll.addView(menuContainer, new HorizontalScrollView.LayoutParams(
233:                 HorizontalScrollView.LayoutParams.WRAP_CONTENT,
234:                 HorizontalScrollView.LayoutParams.WRAP_CONTENT));
235:         sideRail.addView(menuScroll, new LinearLayout.LayoutParams(
236:                 LinearLayout.LayoutParams.MATCH_PARENT,
237:                 LinearLayout.LayoutParams.WRAP_CONTENT));
330:             appTitleText.setTextColor(UiStyles.color(context, R.color.textPrimary));
399:         itemLayout.setOrientation(LinearLayout.HORIZONTAL);
402:         itemLayout.setPadding(padH, padV, padH, padV);
403:         itemLayout.setGravity(Gravity.CENTER_VERTICAL | Gravity.START);
406:         itemLayout.setMinimumHeight(minTouchPx);
408:         LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(
409:                 LinearLayout.LayoutParams.MATCH_PARENT,
410:                 LinearLayout.LayoutParams.WRAP_CONTENT);
413:         params.setMargins(mH, mV, mH, mV);
421:         LinearLayout.LayoutParams iconLp = new LinearLayout.LayoutParams(iconSize, iconSize);
427:         UiStyles.setTextSize(textView, R.dimen.text_size_large);
428:         textView.setTextColor(UiStyles.color(context, R.color.textSecondary));
429:         textView.setGravity(Gravity.START | Gravity.CENTER_VERTICAL);
430:         LinearLayout.LayoutParams textParams = new LinearLayout.LayoutParams(
431:                 0,
432:                 LinearLayout.LayoutParams.WRAP_CONTENT,
433:                 1.0f);
434:         textParams.setMargins((int) (16 * density), 0, 0, 0);
446:         itemLayout.setOrientation(LinearLayout.VERTICAL);
449:         itemLayout.setPadding(padH, padV, padH, padV);
450:         itemLayout.setGravity(Gravity.CENTER_HORIZONTAL);
453:         itemLayout.setMinimumWidth((int) (88 * density));
455:         LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(
456:                 LinearLayout.LayoutParams.WRAP_CONTENT,
457:                 LinearLayout.LayoutParams.WRAP_CONTENT);
459:         params.setMargins(m, m, m, m);
467:         LinearLayout.LayoutParams iconLp = new LinearLayout.LayoutParams(iconSize, iconSize);
473:         UiStyles.setTextSize(textView, R.dimen.text_size_small);
474:         textView.setMaxLines(1);
475:         textView.setEllipsize(TextUtils.TruncateAt.END);
476:         textView.setTextColor(UiStyles.color(context, R.color.textSecondary));
477:         textView.setGravity(Gravity.CENTER_HORIZONTAL);
478:         LinearLayout.LayoutParams textParams = new LinearLayout.LayoutParams(
479:                 LinearLayout.LayoutParams.WRAP_CONTENT,
480:                 LinearLayout.LayoutParams.WRAP_CONTENT);
498:                 label.setTextColor(UiStyles.color(context, R.color.oemAccent));
499:                 label.setTypeface(null, android.graphics.Typeface.BOLD);
502:             row.setBackgroundColor(Color.TRANSPARENT);
507:                 label.setTextColor(UiStyles.color(context, R.color.textSecondary));
508:                 label.setTypeface(null, android.graphics.Typeface.NORMAL);
521:         box.setOrientation(LinearLayout.VERTICAL);
522:         box.setPadding(pad, pad, pad, Math.round(8 * density));
526:         title.setTextSize(28);
527:         title.setTypeface(null, Typeface.BOLD);
528:         title.setTextColor(UiStyles.color(context, R.color.textPrimary));
529:         title.setGravity(Gravity.CENTER);
534:         message.setTextSize(22);
535:         message.setTextColor(UiStyles.color(context, R.color.textPrimary));
536:         message.setGravity(Gravity.CENTER);
537:         message.setPadding(0, Math.round(18 * density), 0, 0);
551:                 ok.setTextSize(22);
552:                 ok.setTypeface(null, Typeface.BOLD);
```

### app/src/main/java/com/mapcontrol/ui/builder/TopBarBuilder.java

```text
16: import androidx.core.graphics.ColorUtils;
58:         topBar.setOrientation(LinearLayout.VERTICAL);
60:         row.setPadding(dp(20), dp(16), dp(16), dp(16));
61:         row.setGravity(Gravity.CENTER_VERTICAL);
63:         titleArea.setGravity(Gravity.CENTER_VERTICAL);
66:         titleArea.setOrientation(LinearLayout.HORIZONTAL);
71:         launcherBackButton.setPadding(dp(8), dp(8), dp(8), dp(8));
75:         LinearLayout.LayoutParams backParams = new LinearLayout.LayoutParams(dp(36), dp(36));
76:         backParams.setMarginEnd(dp(8));
82:         UiStyles.setTextSize(titleView, R.dimen.text_size_medium);
83:         titleView.setTypeface(null, Typeface.BOLD);
84:         titleView.setGravity(Gravity.CENTER_VERTICAL);
85:         titleView.setSingleLine(true);
86:         titleView.setMinHeight(dp(48));
89:         titleArea.addView(titleView, new LinearLayout.LayoutParams(0,
90:                 LinearLayout.LayoutParams.MATCH_PARENT, 1));
107:         topBar.addView(row, new LinearLayout.LayoutParams(-1, -2));
109:         topBar.addView(topBarDivider, new LinearLayout.LayoutParams(-1, Math.max(1, dp(1))));
247:             bg.setColor(UiStyles.color(context, R.color.surfaceBar));
248:             bg.setCornerRadii(new float[]{r, r, 0, 0, 0, 0, r, r});
251:         if (titleView != null) titleView.setTextColor(UiStyles.color(context, R.color.textPrimary));
274:             setGravity(Gravity.CENTER_VERTICAL);
275:             setPadding(dp(12), dp(8), dp(12), dp(8));
280:             addView(icon, new LinearLayout.LayoutParams(dp(24), dp(24)));
284:             texts.setOrientation(VERTICAL);
285:             texts.setGravity(Gravity.CENTER_VERTICAL);
288:             LinearLayout.LayoutParams textParams = new LinearLayout.LayoutParams(0, -2, 1);
289:             textParams.setMarginStart(dp(6));
293:             label.setSingleLine(true);
294:             label.setIncludeFontPadding(true);
295:             label.setTextSize(android.util.TypedValue.COMPLEX_UNIT_SP, 12.5f);
299:             value.setSingleLine(true);
300:             value.setTypeface(null, Typeface.BOLD);
301:             value.setIncludeFontPadding(true);
302:             value.setTextSize(android.util.TypedValue.COMPLEX_UNIT_SP, 16.5f);
306:             label.setTextColor(UiStyles.color(context, R.color.textSecondary));
308:             value.setTextColor(accent);
311:             background.setCornerRadius(dp(14));
312:             background.setColor(ColorUtils.blendARGB(UiStyles.color(context, R.color.surfaceCardInner), accent, .07f));
328:                 setOrientation(stacked ? VERTICAL : HORIZONTAL);
329:                 getChildAt(0).setLayoutParams(new LinearLayout.LayoutParams(stacked ? -1 : 0,
330:                     -2, stacked ? 0 : 1));
331:                 getChildAt(1).setLayoutParams(new LinearLayout.LayoutParams(stacked ? -1 : dp(660), -2));
```

### app/src/main/java/com/mapcontrol/ui/builder/VehicleInfoTabBuilder.java

```text
113:         scrollView.setFillViewport(true);
115:         content.setOrientation(LinearLayout.VERTICAL);
117:         content.setPadding(pad, pad, pad, pad);
118:         scrollView.addView(content, new ScrollView.LayoutParams(
119:                 ScrollView.LayoutParams.MATCH_PARENT, ScrollView.LayoutParams.WRAP_CONTENT));
130:         filterGroup.setOrientation(LinearLayout.HORIZONTAL);
131:         filterGroup.setGravity(Gravity.CENTER_VERTICAL);
142:             LinearLayout.LayoutParams lp = new LinearLayout.LayoutParams(
143:                     LinearLayout.LayoutParams.WRAP_CONTENT, LinearLayout.LayoutParams.WRAP_CONTENT);
144:             lp.setMarginEnd(hGap * 2);
158:         LinearLayout.LayoutParams actLp = new LinearLayout.LayoutParams(
159:                 LinearLayout.LayoutParams.WRAP_CONTENT, LinearLayout.LayoutParams.WRAP_CONTENT);
160:         actLp.setMarginEnd(hGap * 2);
177:         filterScroll.addView(filterGroup, new HorizontalScrollView.LayoutParams(
178:                 HorizontalScrollView.LayoutParams.WRAP_CONTENT, HorizontalScrollView.LayoutParams.WRAP_CONTENT));
179:         LinearLayout.LayoutParams filterScrollLp = new LinearLayout.LayoutParams(
180:                 LinearLayout.LayoutParams.MATCH_PARENT, LinearLayout.LayoutParams.WRAP_CONTENT);
181:         filterScrollLp.setMargins(0, 0, 0, pad);
244:         card.setOrientation(LinearLayout.HORIZONTAL);
245:         card.setGravity(Gravity.CENTER_VERTICAL);
246:         card.setPadding(pad, pad, pad, pad);
248:         LinearLayout.LayoutParams cardLp = new LinearLayout.LayoutParams(
249:                 LinearLayout.LayoutParams.MATCH_PARENT, LinearLayout.LayoutParams.WRAP_CONTENT);
250:         cardLp.setMargins(0, 0, 0, pad);
254:         UiStyles.setTextSize(identityModelYearText, R.dimen.text_size_large);
255:         identityModelYearText.setTypeface(null, Typeface.BOLD);
256:         identityModelYearText.setTextColor(UiStyles.color(uiContext, R.color.textPrimary));
258:         identityModelYearText.setSingleLine(true);
259:         card.addView(identityModelYearText, new LinearLayout.LayoutParams(
260:                 0, LinearLayout.LayoutParams.WRAP_CONTENT, 1f));
264:         card.addView(refresh, new LinearLayout.LayoutParams(
265:                 LinearLayout.LayoutParams.WRAP_CONTENT, LinearLayout.LayoutParams.WRAP_CONTENT));
318:         card.setOrientation(LinearLayout.VERTICAL);
320:         card.setPadding(pad, pad / 2, pad, pad / 2);
323:         label.setTextColor(UiStyles.color(uiContext, R.color.textHint));
326:         UiStyles.setTextSize(value, R.dimen.text_size_title);
327:         value.setTypeface(null, Typeface.BOLD);
328:         value.setTextColor(UiStyles.color(uiContext, R.color.textPrimary));
329:         value.setPadding(0, pad / 2, 0, 0);
341:         btn.setTextColor(textColor);
359:             statusText.setTextColor(UiStyles.color(uiContext,
360:                     snapshot.connected ? R.color.statusSuccess : R.color.textHint));
373:         UiStyles.setTextSize(text, sizeRes);
374:         text.setTextColor(UiStyles.color(uiContext, R.color.textPrimary));
```

### app/src/main/java/com/mapcontrol/ui/builder/WelcomeSoundScreenBuilder.java

```text
58:         row.setGravity(android.view.Gravity.CENTER);
61:         row.setMinimumHeight(dp(context, 56));
62:         row.setPadding(dp(context, 12), dp(context, 8), dp(context, 12), dp(context, 8));
64:         title.setGravity(android.view.Gravity.CENTER);
65:         row.addView(title, new LinearLayout.LayoutParams(-1, -2));
67:         row.setBackground(new android.graphics.drawable.RippleDrawable(
68:                 android.content.res.ColorStateList.valueOf(androidx.core.graphics.ColorUtils.setAlphaComponent(
69:                         UiStyles.color(context, R.color.oemAccent), 55)), row.getBackground(), null));
70:         LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(-1, -2);
81:         scrollView.setFillViewport(true);
82:         scrollView.setBackgroundColor(android.graphics.Color.TRANSPARENT);
85:         sections.setPadding(margin / 2, margin / 2, margin / 2, margin / 2);
97:         tvFilePath.setMaxLines(2);
98:         tvFilePath.setEllipsize(android.text.TextUtils.TruncateAt.END);
99:         tvFilePath.setMinHeight(dp(context, 48));
100:         tvFilePath.setGravity(android.view.Gravity.CENTER_VERTICAL);
101:         tvFilePath.setPadding(dp(context, 12), dp(context, 6), dp(context, 12), dp(context, 6));
110:         playbackRow.setAlpha(.45f);
112:         LinearLayout.LayoutParams chooseParams = new LinearLayout.LayoutParams(0, -2, 1);
113:         chooseParams.setMarginEnd(dp(context, 8));
115:         controls.addView(playbackRow, new LinearLayout.LayoutParams(0, -2, 1));
133:         usbList.setOrientation(LinearLayout.VERTICAL);
135:         audio.addView(usbList, new LinearLayout.LayoutParams(-1, -2));
141:         help.setTextColor(UiStyles.color(context, R.color.textPrimary));
142:         help.setMinHeight(dp(context, 56));
150:             if (title != null) UiStyles.setTextSize(title, R.dimen.text_size_medium);
152:             if (message != null) UiStyles.setTextSize(message, R.dimen.text_size_small);
153:             UiStyles.setTextSize(dialog.getButton(android.content.DialogInterface.BUTTON_POSITIVE), R.dimen.text_size_normal);
158:         scrollView.addView(sections, new ScrollView.LayoutParams(-1, -2));
179:         btnPreview.setMinHeight(dp(context, 56));
185:                 designTitles[i].setTypeface(null, checked ? Typeface.BOLD : Typeface.NORMAL);
186:                 designTitles[i].setTextColor(UiStyles.color(context,
187:                         checked ? R.color.textPrimary : R.color.textSecondary));
192:             btnPreview.setAlpha(enabled ? 1f : .45f);
198:             card.setOrientation(LinearLayout.HORIZONTAL);
199:             card.setGravity(android.view.Gravity.CENTER_VERTICAL);
200:             card.setPadding(dp(context, 16), dp(context, 14), dp(context, 16), dp(context, 14));
201:             card.setMinimumHeight(dp(context, 64));
206:             title.setGravity(android.view.Gravity.CENTER_VERTICAL);
207:             card.addView(title, new LinearLayout.LayoutParams(0, -2, 1));
228:         parent.addView(choices, new LinearLayout.LayoutParams(-1, -2));
234:         lineRow.setGravity(android.view.Gravity.CENTER_VERTICAL);
238:         UiStyles.setTextSize(lineInput, R.dimen.text_size_normal);
239:         lineInput.setSingleLine(true);
242:         lineInput.setPadding(dp(context, 12), dp(context, 8), dp(context, 12), dp(context, 8));
243:         lineInput.setMinHeight(dp(context, 56));
244:         lineInput.setTextColor(UiStyles.color(context, R.color.textPrimary));
258:         lineRow.addView(lineInput, new LinearLayout.LayoutParams(0, -2, 1));
259:         LinearLayout.LayoutParams previewParams = new LinearLayout.LayoutParams(-2, -2);
260:         previewParams.setMarginStart(dp(context, 8));
262:         parent.addView(lineRow, new LinearLayout.LayoutParams(-1, -2));
268:         card.setOrientation(LinearLayout.VERTICAL);
270:         card.setPadding(inset, inset, inset, inset);
273:         title.setTypeface(null, Typeface.BOLD);
285:         UiStyles.setTextSize(text, sizeRes);
286:         text.setTextColor(UiStyles.color(context, secondary ? R.color.textSecondary : R.color.textPrimary));
292:         LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(-1, -2);
293:         params.setMargins(0, dp(context, topDp), 0, dp(context, bottomDp));
305:         bg.setCornerRadius(16 * density);
307:             bg.setColor(UiStyles.color(context, R.color.assistCardActive));
308:             bg.setStroke(Math.round(2 * density), UiStyles.color(context, R.color.assistStrokeActive));
310:             bg.setColor(UiStyles.color(context, R.color.assistCardIdle));
311:             bg.setStroke(Math.round(1 * density), UiStyles.color(context, R.color.outline));
```

### app/src/main/java/com/mapcontrol/ui/builder/WelcomeSoundTabBuilder.java

```text
353:         playbackRow.setAlpha(ready ? 1f : 0.45f);
```

### app/src/main/java/com/mapcontrol/ui/builder/WifiTabBuilder.java

```text
52:         UiStyles.setTextSize(button, R.dimen.text_size_normal);
53:         button.setTextColor(UiStyles.textOnColor(backgroundColorArgb));
54:         button.setTypeface(null, android.graphics.Typeface.BOLD);
56:         button.setSingleLine(true);
57:         button.setGravity(android.view.Gravity.CENTER);
59:         button.setPadding(inset, 0, inset, 0);
61:         button.setMinHeight(btnHeight);
62:         button.setMinimumHeight(btnHeight);
67:         wifiTabContent.setOrientation(LinearLayout.VERTICAL);
68:         wifiTabContent.setBackgroundColor(UiStyles.color(context, R.color.transparent));
70:         wifiTabContent.setPadding(outerPad, outerPad, outerPad, outerPad);
73:         card.setOrientation(LinearLayout.VERTICAL);
76:         card.setPadding(innerPad, innerPad, innerPad, innerPad);
79:         controlButtonsRow.setOrientation(LinearLayout.HORIZONTAL);
80:         controlButtonsRow.setPadding(0, UiStyles.dimenPx(context, R.dimen.spacing_small), 0, UiStyles.dimenPx(context, R.dimen.spacing_medium));
81:         controlButtonsRow.setGravity(android.view.Gravity.CENTER_VERTICAL);
82:         controlButtonsRow.setBackgroundColor(UiStyles.color(context, R.color.transparent));
91:         LinearLayout.LayoutParams toggleParams = new LinearLayout.LayoutParams(0, btnHeight, 1f);
92:         toggleParams.setMargins(0, 0, gap / 2, 0);
99:         LinearLayout.LayoutParams scanParams = new LinearLayout.LayoutParams(0, btnHeight, 1f);
100:         scanParams.setMargins(gap / 2, 0, 0, 0);
103:         card.addView(controlButtonsRow, new LinearLayout.LayoutParams(
104:                 LinearLayout.LayoutParams.MATCH_PARENT,
105:                 LinearLayout.LayoutParams.WRAP_CONTENT));
108:         wifiListScrollView.setBackgroundColor(UiStyles.color(context, R.color.transparent));
109:         wifiListScrollView.setPadding(0, 0, 0, 0);
112:         wifiListContainer.setOrientation(LinearLayout.VERTICAL);
115:         LinearLayout.LayoutParams listParams = new LinearLayout.LayoutParams(
116:                 LinearLayout.LayoutParams.MATCH_PARENT,
117:                 0, 1f);
120:         LinearLayout.LayoutParams cardLp = new LinearLayout.LayoutParams(
121:                 LinearLayout.LayoutParams.MATCH_PARENT,
122:                 0, 1f);
189:                         emptyText.setTextColor(UiStyles.color(context, R.color.textLoading));
190:                         UiStyles.setTextSize(emptyText, R.dimen.text_size_small);
191:                         emptyText.setPadding(8, 8, 8, 8);
215:                 emptyText.setTextColor(UiStyles.color(context, R.color.textLoading));
216:                 UiStyles.setTextSize(emptyText, R.dimen.text_size_small);
217:                 emptyText.setPadding(8, 8, 8, 8);
269:                 emptyCard.setOrientation(LinearLayout.VERTICAL);
270:                 emptyCard.setGravity(android.view.Gravity.CENTER);
271:                 emptyCard.setPadding(32, 48, 32, 48);
277:                 emptyIcon.setLayoutParams(new LinearLayout.LayoutParams(emptyIconPx, emptyIconPx));
283:                 noNetworks.setTextColor(UiStyles.color(context, R.color.textMuted));
284:                 UiStyles.setTextSize(noNetworks, R.dimen.text_size_normal);
285:                 noNetworks.setGravity(android.view.Gravity.CENTER);
286:                 LinearLayout.LayoutParams noNetLp = new LinearLayout.LayoutParams(
287:                         LinearLayout.LayoutParams.MATCH_PARENT,
288:                         LinearLayout.LayoutParams.WRAP_CONTENT);
318:         wifiCard.setOrientation(LinearLayout.HORIZONTAL);
319:         wifiCard.setPadding(20, 20, 20, 20);
320:         wifiCard.setGravity(android.view.Gravity.CENTER_VERTICAL);
327:         LinearLayout.LayoutParams cardParams = new LinearLayout.LayoutParams(
328:                 LinearLayout.LayoutParams.MATCH_PARENT,
329:                 LinearLayout.LayoutParams.WRAP_CONTENT);
330:         cardParams.setMargins(0, 0, 0, 12);
331:         wifiCard.setMinimumHeight((int) (130 * context.getResources().getDisplayMetrics().density));
334:         iconContainer.setOrientation(LinearLayout.VERTICAL);
335:         iconContainer.setGravity(android.view.Gravity.CENTER);
341:         iconImg.setLayoutParams(new LinearLayout.LayoutParams(listIconPx, listIconPx));
346:         LinearLayout.LayoutParams iconParams = new LinearLayout.LayoutParams(
347:                 LinearLayout.LayoutParams.WRAP_CONTENT,
348:                 LinearLayout.LayoutParams.WRAP_CONTENT);
349:         iconParams.setMargins(0, 0, 20, 0);
353:         infoContainer.setOrientation(LinearLayout.VERTICAL);
354:         infoContainer.setGravity(android.view.Gravity.CENTER_VERTICAL);
359:         ssidText.setTextColor(UiStyles.color(context, R.color.textPrimary));
360:         UiStyles.setTextSize(ssidText, R.dimen.text_size_medium);
361:         ssidText.setTypeface(null, android.graphics.Typeface.NORMAL);
365:         detailRow.setOrientation(LinearLayout.HORIZONTAL);
366:         detailRow.setGravity(android.view.Gravity.CENTER_VERTICAL);
367:         detailRow.setPadding(0, 6, 0, 0);
407:             detailText.setTextColor(UiStyles.color(context, R.color.accentHighlight));
410:             detailText.setTextColor(UiStyles.color(context, R.color.textSecondaryCool));
412:         UiStyles.setTextSize(detailText, R.dimen.text_size_small);
417:         LinearLayout.LayoutParams infoParams = new LinearLayout.LayoutParams(
418:                 0, LinearLayout.LayoutParams.WRAP_CONTENT, 1f);
428:             LinearLayout.LayoutParams checkParams = new LinearLayout.LayoutParams(cpx, cpx);
429:             checkParams.setMargins(8, 0, 0, 0);
496:         dialogLayout.setOrientation(LinearLayout.VERTICAL);
497:         dialogLayout.setPadding(40, 30, 40, 30);
498:         dialogLayout.setBackgroundColor(UiStyles.color(context, R.color.cardColor));
502:         titleView.setTextColor(UiStyles.color(context, R.color.textPrimary));
503:         UiStyles.setTextSize(titleView, R.dimen.text_size_large);
504:         titleView.setTypeface(null, android.graphics.Typeface.BOLD);
505:         titleView.setPadding(0, 0, 0, 20);
510:         networkName.setTextColor(UiStyles.color(context, R.color.accentHighlight));
511:         UiStyles.setTextSize(networkName, R.dimen.text_size_medium);
512:         networkName.setTypeface(null, android.graphics.Typeface.BOLD);
513:         networkName.setPadding(0, 0, 0, 20);
519:         passwordInput.setTextColor(UiStyles.color(context, R.color.textPrimary));
521:         UiStyles.setTextSize(passwordInput, R.dimen.text_size_medium);
522:         passwordInput.setPadding(40, 30, 40, 30);
523:         passwordInput.setBackgroundColor(UiStyles.color(context, R.color.surfaceColor));
546:                 pos.setTextColor(UiStyles.color(context, R.color.buttonSuccessBright));
547:                 UiStyles.setTextSize(pos, R.dimen.text_size_medium);
548:                 pos.setTypeface(null, android.graphics.Typeface.BOLD);
551:                 neg.setTextColor(UiStyles.color(context, R.color.statusErrorBright));
552:                 UiStyles.setTextSize(neg, R.dimen.text_size_medium);
553:                 neg.setTypeface(null, android.graphics.Typeface.BOLD);
```

### app/src/main/java/com/mapcontrol/ui/theme/FeatureGridLayout.java

```text
18:         setOrientation(HORIZONTAL);
24:         LayoutParams lp = new LayoutParams();
26:         lp.setMargins(gap, gap, gap, gap);
27:         lp.height = LayoutParams.WRAP_CONTENT;
41:             LayoutParams lp = (LayoutParams) child.getLayoutParams();
49:             lp.setGravity(Gravity.FILL);
53:         setColumnCount(columns);
55:         setRowCount(totalRows);
```

### app/src/main/java/com/mapcontrol/ui/theme/UiStyles.java

```text
6: import android.graphics.RenderEffect;
10: import androidx.core.graphics.ColorUtils;
42:     public static void setTextSize(TextView view, int sizeResId) {
43:         view.setTextSize(android.util.TypedValue.COMPLEX_UNIT_PX,
44:                 view.getResources().getDimension(sizeResId));
105:         view.setBackgroundColor(color(view.getContext(), R.color.tabContentScrim));
110:         d.setColor(colorArgb);
111:         d.setCornerRadius(view.getResources().getDimension(R.dimen.oem_button_radius));
118:         d.setColor(colorArgb);
119:         d.setCornerRadius(cornerRadiusDp * density);
125:         return androidx.core.graphics.ColorUtils.calculateLuminance(backgroundColorArgb) > 0.179
126:                 ? Color.BLACK : Color.WHITE;
131:         setTextSize(button, R.dimen.text_size_normal);
132:         button.setTextColor(textOnColor(backgroundColorArgb));
135:         button.setPadding(inset, inset / 4, inset, inset / 4);
136:         button.setMinHeight(dimenPx(button.getContext(), R.dimen.button_height));
137:         button.setMinimumHeight(dimenPx(button.getContext(), R.dimen.button_height));
146:             view.setRenderEffect(RenderEffect.createBlurEffect(radiusPx, radiusPx, Shader.TileMode.CLAMP));
152:             view.setRenderEffect(null);
203:             leftSeg.setAlpha(a);
204:             rightSeg.setAlpha(a);
206:                 helpSeg.setAlpha(a);
238:         setTextSize(heading, R.dimen.text_size_medium);
239:         heading.setTypeface(null, android.graphics.Typeface.BOLD);
240:         heading.setTextColor(color(context, R.color.textPrimary));
243:         setTextSize(help, R.dimen.text_size_small);
244:         help.setTextColor(color(context, R.color.textHint));
245:         help.setPadding(0, gap / 2, 0, gap / 2);
253:                 container.setOrientation(LinearLayout.VERTICAL);
257:                 row.setOrientation(LinearLayout.HORIZONTAL);
258:                 row.setGravity(Gravity.CENTER_VERTICAL);
259:                 row.addView(heading, new LinearLayout.LayoutParams(0, LinearLayout.LayoutParams.WRAP_CONTENT, 1f));
267:             track.setOrientation(LinearLayout.HORIZONTAL);
277:                 card.setOrientation(LinearLayout.HORIZONTAL);
278:                 card.setGravity(Gravity.CENTER);
279:                 card.setPadding(Math.round(16 * density), Math.round(12 * density),
280:                         Math.round(16 * density), Math.round(12 * density));
281:                 card.setMinimumHeight(Math.round(52 * density));
288:                 LinearLayout.LayoutParams rbLp = new LinearLayout.LayoutParams(
289:                         LinearLayout.LayoutParams.WRAP_CONTENT, LinearLayout.LayoutParams.WRAP_CONTENT);
290:                 rbLp.setMarginEnd(Math.round(6 * density));
295:                 setTextSize(tv, R.dimen.text_size_normal);
296:                 tv.setGravity(Gravity.CENTER_VERTICAL);
297:                 card.addView(tv, new LinearLayout.LayoutParams(
298:                         LinearLayout.LayoutParams.WRAP_CONTENT, LinearLayout.LayoutParams.WRAP_CONTENT));
304:                 LinearLayout.LayoutParams cardLp = new LinearLayout.LayoutParams(
305:                         0, LinearLayout.LayoutParams.WRAP_CONTENT, 1f);
306:                 if (i > 0) cardLp.setMarginStart(langGap);
310:             LinearLayout.LayoutParams trackLp = new LinearLayout.LayoutParams(
311:                     LinearLayout.LayoutParams.MATCH_PARENT, LinearLayout.LayoutParams.WRAP_CONTENT);
312:             trackLp.setMargins(0, Math.round(4 * density), 0, Math.round(8 * density));
330:                     bg.setCornerRadius(16 * density);
332:                         bg.setColor(color(context, R.color.assistCardActive));
333:                         bg.setStroke(Math.round(2 * density), color(context, R.color.assistStrokeActive));
334:                         langTvs[i].setTextColor(color(context, R.color.textPrimary));
335:                         langTvs[i].setTypeface(null, android.graphics.Typeface.BOLD);
337:                         bg.setColor(color(context, R.color.assistCardIdle));
338:                         bg.setStroke(Math.round(1 * density), color(context, R.color.outline));
339:                         langTvs[i].setTextColor(color(context, R.color.textSecondary));
340:                         langTvs[i].setTypeface(null, android.graphics.Typeface.NORMAL);
365:             card.setOrientation(LinearLayout.HORIZONTAL);
366:             card.setGravity(Gravity.CENTER_VERTICAL);
367:             card.setPadding(Math.round(16 * density), Math.round(14 * density),
368:                     Math.round(16 * density), Math.round(14 * density));
369:             card.setMinimumHeight(Math.round(64 * density));
374:             labels.setOrientation(LinearLayout.VERTICAL);
375:             LinearLayout.LayoutParams labelsLp = new LinearLayout.LayoutParams(
376:                     0, LinearLayout.LayoutParams.WRAP_CONTENT, 1f);
377:             labelsLp.setMargins(0, 0, Math.round(12 * density), 0);
382:                 heading.setPadding(0, 0, 0, 0);
389:             help.setPadding(0, Math.round(3 * density), 0, 0);
402:                 LinearLayout.LayoutParams cardLp = new LinearLayout.LayoutParams(
403:                         LinearLayout.LayoutParams.MATCH_PARENT, LinearLayout.LayoutParams.WRAP_CONTENT);
404:                 cardLp.setMargins(0, Math.round(4 * density), 0, Math.round(6 * density));
416:                 cardBg.setCornerRadius(16 * density);
418:                     cardBg.setColor(color(context, R.color.assistCardActive));
419:                     cardBg.setStroke(Math.round(2 * density), color(context, R.color.assistStrokeActive));
420:                     heading.setTypeface(null, android.graphics.Typeface.BOLD);
421:                     heading.setTextColor(color(context, R.color.textPrimary));
423:                     cardBg.setColor(color(context, R.color.assistCardIdle));
424:                     cardBg.setStroke(Math.round(1 * density), color(context, R.color.outline));
425:                     heading.setTypeface(null, android.graphics.Typeface.NORMAL);
426:                     heading.setTextColor(color(context, R.color.textSecondary));
430:                 help.setTextColor(color(context, R.color.textHint));
504:             setTextSize(rowTitle, R.dimen.text_size_medium);
505:             rowTitle.setTextColor(UiStyles.color(context, R.color.textPrimary));
506:             rowTitle.setTypeface(null, android.graphics.Typeface.BOLD);
508:             rowTitle.setPadding(hPad, dimenPx(context, R.dimen.spacing_medium), hPad,
509:                     dimenPx(context, R.dimen.spacing_small));
510:             parent.addView(rowTitle, new LinearLayout.LayoutParams(
511:                     LinearLayout.LayoutParams.MATCH_PARENT,
512:                     LinearLayout.LayoutParams.WRAP_CONTENT));
516:         track.setOrientation(LinearLayout.HORIZONTAL);
517:         track.setBackgroundColor(Color.TRANSPARENT);
518:         track.setPadding(0, 0, 0, 0);
530:             card.setOrientation(LinearLayout.HORIZONTAL);
531:             card.setGravity(Gravity.CENTER);
532:             card.setPadding(Math.round(14 * density), Math.round(12 * density),
533:                     Math.round(14 * density), Math.round(12 * density));
534:             card.setMinimumHeight(Math.round(52 * density));
541:             LinearLayout.LayoutParams rbLp = new LinearLayout.LayoutParams(
542:                     LinearLayout.LayoutParams.WRAP_CONTENT, LinearLayout.LayoutParams.WRAP_CONTENT);
543:             rbLp.setMarginEnd(Math.round(6f * density));
548:             setTextSize(tv, R.dimen.text_size_normal);
549:             tv.setGravity(Gravity.CENTER_VERTICAL);
550:             tv.setMaxLines(2);
551:             card.addView(tv, new LinearLayout.LayoutParams(
552:                     LinearLayout.LayoutParams.WRAP_CONTENT, LinearLayout.LayoutParams.WRAP_CONTENT));
560:         setTextSize(help, R.dimen.text_size_small);
561:         help.setTextColor(UiStyles.color(context, R.color.textHint));
563:         help.setPadding(hPad, dimenPx(context, R.dimen.spacing_small), hPad,
564:                 dimenPx(context, R.dimen.spacing_medium));
587:                 bg.setCornerRadius(cornerRadius);
590:                     bg.setColor(color(context, R.color.assistCardActive));
591:                     bg.setStroke(Math.round(2 * density), color(context, R.color.assistStrokeActive));
592:                     tv.setTextColor(color(context, R.color.textPrimary));
593:                     tv.setTypeface(null, android.graphics.Typeface.BOLD);
595:                     bg.setColor(color(context, R.color.assistCardIdle));
596:                     bg.setStroke(Math.round(1 * density), color(context, R.color.outline));
597:                     tv.setTextColor(color(context, R.color.textSecondary));
598:                     tv.setTypeface(null, android.graphics.Typeface.NORMAL);
619:             LinearLayout.LayoutParams cardLp = new LinearLayout.LayoutParams(
620:                     0, LinearLayout.LayoutParams.WRAP_CONTENT, 1f);
622:                 cardLp.setMarginStart(gap);
627:         LinearLayout.LayoutParams trackLp = new LinearLayout.LayoutParams(
628:                 LinearLayout.LayoutParams.MATCH_PARENT,
629:                 LinearLayout.LayoutParams.WRAP_CONTENT);
630:         trackLp.setMargins(0, 0, 0, 0);
632:         help.setPadding(0, dimenPx(context, R.dimen.spacing_small), 0,
633:                 dimenPx(context, R.dimen.spacing_small));
634:         parent.addView(help, new LinearLayout.LayoutParams(
635:                 LinearLayout.LayoutParams.MATCH_PARENT,
636:                 LinearLayout.LayoutParams.WRAP_CONTENT));
646:             DrawableCompat.setTint(d, colorArgb);
```

### app/src/main/java/com/mapcontrol/ui/welcome/MountainHikeScene.java

```text
94:         paint.setColor(0xFF3E5878);
96:         paint.setColor(0xFF24344C);
99:         paint.setColor(0xFF121A28);
102:         paint.setColor(0xFF0C121C);
135:         paint.setColor(0x22101828);
141:         paint.setColor(0x18F0C8A0);
149:         paint.setColor(0x66FF9A48);
151:         paint.setColor(0xFFFFE0B0);
153:         paint.setColor(0xFFFFF6E4);
158:         paint.setColor(0xFF1A2838);
166:             paint.setColor((alpha << 24) | 0x8FB4C8);
176:             paint.setColor((Math.max(alpha, 12) << 24) | 0xE8A060);
188:             paint.setColor((Math.max(40, Math.min(220, alpha)) << 24) | 0xF2D48A);
197:         paint.setColor(0xCC1A2433);
216:         paint.setColor(0x99D5E2F0);
220:         paint.setColor(0xE8F0A060);
```

### app/src/main/java/com/mapcontrol/ui/welcome/MountainHikeWelcomeDesign.java

```text
3: import android.animation.Animator;
4: import android.animation.AnimatorSet;
5: import android.animation.ObjectAnimator;
18: import android.view.animation.DecelerateInterpolator;
67:         root.setBackgroundColor(0xFF1A2744);
68:         root.addView(new MountainHikeScene(context), new FrameLayout.LayoutParams(
69:                 FrameLayout.LayoutParams.MATCH_PARENT,
70:                 FrameLayout.LayoutParams.MATCH_PARENT));
73:         column.setOrientation(LinearLayout.VERTICAL);
74:         column.setGravity(Gravity.CENTER);
76:         column.setPadding(side, 0, side, 0);
83:         LinearLayout.LayoutParams dayParams = new LinearLayout.LayoutParams(
84:                 LinearLayout.LayoutParams.WRAP_CONTENT,
85:                 LinearLayout.LayoutParams.WRAP_CONTENT);
92:         LinearLayout.LayoutParams nameParams = new LinearLayout.LayoutParams(
93:                 LinearLayout.LayoutParams.WRAP_CONTENT,
94:                 LinearLayout.LayoutParams.WRAP_CONTENT);
143:         root.addView(column, new FrameLayout.LayoutParams(
144:                 FrameLayout.LayoutParams.MATCH_PARENT,
145:                 FrameLayout.LayoutParams.MATCH_PARENT,
146:                 Gravity.CENTER));
152:         row.setOrientation(LinearLayout.HORIZONTAL);
153:         row.setGravity(Gravity.CENTER);
154:         row.addView(rule(context), new LinearLayout.LayoutParams(dp(context, 36), dp(context, 1)));
155:         LinearLayout.LayoutParams peak = new LinearLayout.LayoutParams(dp(context, 16), dp(context, 10));
159:         row.addView(rule(context), new LinearLayout.LayoutParams(dp(context, 36), dp(context, 1)));
163:     private static LinearLayout.LayoutParams dividerParams(Context context) {
164:         LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(
165:                 LinearLayout.LayoutParams.WRAP_CONTENT,
166:                 LinearLayout.LayoutParams.WRAP_CONTENT);
174:         line.setBackgroundColor(0x99D5DCE6);
180:         view.setTextColor(color);
181:         view.setTextSize(TypedValue.COMPLEX_UNIT_SP, sp);
182:         view.setTypeface(face);
183:         view.setLetterSpacing(tracking);
184:         view.setGravity(Gravity.CENTER);
185:         view.setIncludeFontPadding(false);
193:         view.setAlpha(0f);
194:         view.setTranslationY(dp(view.getContext(), 16));
199:         set.setStartDelay(delay);
200:         set.setDuration(700);
201:         set.setInterpolator(new DecelerateInterpolator());
216:             return Typeface.create("sans-serif-light", Typeface.NORMAL);
218:         return Typeface.create(base, weight, false);
232:             paint.setColor(color);
```

### app/src/main/java/com/mapcontrol/ui/welcome/SignatureWelcomeDesign.java

```text
3: import android.animation.Animator;
4: import android.animation.AnimatorSet;
5: import android.animation.ObjectAnimator;
6: import android.animation.ValueAnimator;
18: import android.view.animation.AccelerateDecelerateInterpolator;
19: import android.view.animation.DecelerateInterpolator;
85:         root.addView(haloView, new android.widget.FrameLayout.LayoutParams(
86:                 android.widget.FrameLayout.LayoutParams.MATCH_PARENT,
87:                 android.widget.FrameLayout.LayoutParams.MATCH_PARENT));
94:             root.addView(rings[i], new android.widget.FrameLayout.LayoutParams(
95:                     ringSize, ringSize, Gravity.CENTER));
99:         column.setOrientation(LinearLayout.VERTICAL);
100:         column.setGravity(Gravity.CENTER);
102:         column.setPadding(side, 0, side, 0);
105:         clock.setTextColor(UiStyles.color(context, R.color.textPrimary));
106:         clock.setTextSize(TypedValue.COMPLEX_UNIT_SP, 92);
107:         clock.setTypeface(display);
108:         clock.setLetterSpacing(-0.03f);
109:         clock.setIncludeFontPadding(false);
110:         clock.setGravity(Gravity.CENTER);
114:         day.setTextColor(UiStyles.color(context, R.color.textSecondary));
115:         day.setTextSize(TypedValue.COMPLEX_UNIT_SP, 16);
116:         day.setTypeface(regular);
117:         day.setLetterSpacing(0.22f);
119:         day.setGravity(Gravity.CENTER);
120:         LinearLayout.LayoutParams dayParams = new LinearLayout.LayoutParams(
121:                 LinearLayout.LayoutParams.WRAP_CONTENT,
122:                 LinearLayout.LayoutParams.WRAP_CONTENT);
128:         ruleBg.setColor(UiStyles.color(context, R.color.oemAccent));
129:         ruleBg.setCornerRadius(dp(context, 1));
131:         LinearLayout.LayoutParams ruleParams = new LinearLayout.LayoutParams(dp(context, 56), dp(context, 2));
136:         name.setTextColor(UiStyles.color(context, R.color.textPrimary));
137:         name.setTextSize(TypedValue.COMPLEX_UNIT_SP, 32);
138:         name.setTypeface(light);
139:         name.setLetterSpacing(0.08f);
140:         name.setGravity(Gravity.CENTER);
141:         name.setIncludeFontPadding(false);
142:         LinearLayout.LayoutParams nameParams = new LinearLayout.LayoutParams(
143:                 LinearLayout.LayoutParams.MATCH_PARENT,
144:                 LinearLayout.LayoutParams.WRAP_CONTENT);
191:         root.addView(column, new android.widget.FrameLayout.LayoutParams(
192:                 android.widget.FrameLayout.LayoutParams.MATCH_PARENT,
193:                 android.widget.FrameLayout.LayoutParams.MATCH_PARENT,
194:                 Gravity.CENTER));
200:         halo.setScaleX(0.86f);
201:         halo.setScaleY(0.86f);
207:         haloIn.setDuration(900);
208:         haloIn.setInterpolator(new DecelerateInterpolator());
215:             part.setStartDelay(900);
216:             part.setDuration(2400);
217:             part.setRepeatCount(ValueAnimator.INFINITE);
218:             part.setRepeatMode(ValueAnimator.REVERSE);
219:             part.setInterpolator(new AccelerateDecelerateInterpolator());
236:         ring.setScaleX(0.42f);
237:         ring.setScaleY(0.42f);
238:         ring.setAlpha(0f);
243:             part.setStartDelay(delay);
244:             part.setDuration(2800);
245:             part.setRepeatCount(ValueAnimator.INFINITE);
246:             part.setInterpolator(new DecelerateInterpolator());
252:         view.setAlpha(0f);
253:         view.setTranslationY(dp(view.getContext(), 22));
258:         set.setStartDelay(delay);
259:         set.setDuration(720);
260:         set.setInterpolator(new DecelerateInterpolator());
265:         rule.setAlpha(0f);
266:         rule.setScaleX(0f);
271:         set.setStartDelay(delay);
272:         set.setDuration(640);
273:         set.setInterpolator(new DecelerateInterpolator());
285:         oval.setColor(Color.TRANSPARENT);
286:         oval.setStroke(Math.max(1, dp(context, 1)),
287:                 Color.argb(170, Color.red(color), Color.green(color), Color.blue(color)));
303:             return Typeface.create("sans-serif-light", Typeface.NORMAL);
305:         return Typeface.create(base, weight, false);
```

### app/src/main/java/com/mapcontrol/ui/welcome/WelcomeScreenPresenter.java

```text
15: import android.view.animation.AccelerateInterpolator;
26:     private static final long DISMISS_MS = 620L;
64:         host.setBackgroundColor(0xFF10141B);
65:         host.addView(content, new FrameLayout.LayoutParams(
66:                 FrameLayout.LayoutParams.MATCH_PARENT,
67:                 FrameLayout.LayoutParams.MATCH_PARENT));
69:         curtain.setBackgroundColor(0xFF10141B);
70:         curtain.setAlpha(0f);
71:         host.addView(curtain, new FrameLayout.LayoutParams(
72:                 FrameLayout.LayoutParams.MATCH_PARENT,
73:                 FrameLayout.LayoutParams.MATCH_PARENT));
82:         WindowManager.LayoutParams params = new WindowManager.LayoutParams(
83:                 WindowManager.LayoutParams.MATCH_PARENT,
84:                 WindowManager.LayoutParams.MATCH_PARENT,
85:                 WindowManager.LayoutParams.TYPE_APPLICATION_OVERLAY,
86:                 WindowManager.LayoutParams.FLAG_NOT_FOCUSABLE
87:                         | WindowManager.LayoutParams.FLAG_NOT_TOUCH_MODAL
88:                         | WindowManager.LayoutParams.FLAG_LAYOUT_IN_SCREEN
89:                         | WindowManager.LayoutParams.FLAG_LAYOUT_NO_LIMITS
90:                         | WindowManager.LayoutParams.FLAG_FULLSCREEN
91:                         | WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS,
92:                 PixelFormat.OPAQUE);
100:                     WindowManager.LayoutParams.LAYOUT_IN_DISPLAY_CUTOUT_MODE_SHORT_EDGES;
121:     private static void applyFullDisplaySize(WindowManager wm, WindowManager.LayoutParams params) {
122:         if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.R) {
123:             Rect bounds = wm.getMaximumWindowMetrics().getBounds();
157:                 .setDuration(DISMISS_MS)
158:                 .setInterpolator(new AccelerateInterpolator())
159:                 .withEndAction(() -> finishHide(view, wm))
160:                 .start();
```

### app/src/main/java/com/mapcontrol/ui/welcome/WinterTouchScene.java

```text
149:             paint.setColor(0x18F6C6DC);
151:             paint.setColor(0x28F8D0E2);
159:         paint.setColor(0x33E8B0C8);
162:         paint.setColor(0x22F3D0DE);
172:         paint.setColor(0x66C98AA4);
198:             paint.setColor(petal);
203:         paint.setColor(center);
226:             paint.setColor(petal.color);
238:         paint.setColor(0x66F4C9DA);
241:         paint.setColor(0xE6F8D7E6);
253:             paint.setColor(i % 2 == 0 ? 0xCCF7D0E0 : 0x99F3C4D6);
267:             paint.setColor(Color.argb(28 - i * 3, 248, 220, 232));
276:         paint.setColor(0xE8F8E4EE);
297:         paint.setColor(0x88F8D5E4);
299:         paint.setColor(0xE8F8E4EE);
```

### app/src/main/java/com/mapcontrol/ui/welcome/WinterTouchWelcomeDesign.java

```text
3: import android.animation.Animator;
4: import android.animation.AnimatorSet;
5: import android.animation.ObjectAnimator;
18: import android.view.animation.DecelerateInterpolator;
67:         root.setBackgroundColor(0xFF161028);
69:         root.addView(scene, new FrameLayout.LayoutParams(
70:                 FrameLayout.LayoutParams.MATCH_PARENT,
71:                 FrameLayout.LayoutParams.MATCH_PARENT));
74:         column.setOrientation(LinearLayout.VERTICAL);
75:         column.setGravity(Gravity.CENTER);
77:         column.setPadding(side, 0, side, 0);
84:         LinearLayout.LayoutParams dayParams = new LinearLayout.LayoutParams(
85:                 LinearLayout.LayoutParams.WRAP_CONTENT,
86:                 LinearLayout.LayoutParams.WRAP_CONTENT);
93:         LinearLayout.LayoutParams nameParams = new LinearLayout.LayoutParams(
94:                 LinearLayout.LayoutParams.WRAP_CONTENT,
95:                 LinearLayout.LayoutParams.WRAP_CONTENT);
100:         LinearLayout.LayoutParams heartParams = new LinearLayout.LayoutParams(dp(context, 12), dp(context, 12));
151:         root.addView(column, new FrameLayout.LayoutParams(
152:                 FrameLayout.LayoutParams.MATCH_PARENT,
153:                 FrameLayout.LayoutParams.MATCH_PARENT,
154:                 Gravity.CENTER));
160:         row.setOrientation(LinearLayout.HORIZONTAL);
161:         row.setGravity(Gravity.CENTER);
162:         row.addView(rule(context), new LinearLayout.LayoutParams(dp(context, 36), dp(context, 1)));
163:         LinearLayout.LayoutParams heart = new LinearLayout.LayoutParams(dp(context, 10), dp(context, 10));
167:         row.addView(rule(context), new LinearLayout.LayoutParams(dp(context, 36), dp(context, 1)));
171:     private static LinearLayout.LayoutParams dividerParams(Context context, int top) {
172:         LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(
173:                 LinearLayout.LayoutParams.WRAP_CONTENT,
174:                 LinearLayout.LayoutParams.WRAP_CONTENT);
182:         line.setBackgroundColor(0x99E7C3D4);
188:         view.setTextColor(color);
189:         view.setTextSize(TypedValue.COMPLEX_UNIT_SP, sp);
190:         view.setTypeface(face);
191:         view.setLetterSpacing(tracking);
192:         view.setGravity(Gravity.CENTER);
193:         view.setIncludeFontPadding(false);
201:         view.setAlpha(0f);
202:         view.setTranslationY(dp(view.getContext(), 16));
207:         set.setStartDelay(delay);
208:         set.setDuration(700);
209:         set.setInterpolator(new DecelerateInterpolator());
224:             return Typeface.create("sans-serif-light", Typeface.NORMAL);
226:         return Typeface.create(base, weight, false);
240:             paint.setColor(color);
```

### app/src/main/java/com/mapcontrol/ui/widget/LauncherClockSwipeLayout.java

```text
33:     private static final float RECOGNIZE_DP = 48f;
34:     private static final float COMMIT_DP = 96f;
35:     private static final int HINT_SIZE_DP = 72;
36:     private static final float HINT_MARGIN_DP = 4f;
91:     public void addView(View child, int index, ViewGroup.LayoutParams params) {
92:         super.addView(child, index, params);
163:         hintView.setAlpha(0f);
164:         hintView.setScaleX(1f);
165:         hintView.setScaleY(1f);
166:         hintView.setTranslationX(0f);
167:         hintView.setTranslationY(0f);
175:             hintView.setAlpha(0f);
176:             hintView.setTranslationX(0f);
177:             hintView.setTranslationY(0f);
185:         hintView.setAlpha(0.82f + 0.18f * progress);
233:         hintView.setScaleX(scale);
234:         hintView.setScaleY(scale);
235:         hintView.setTranslationX(x - (hostW - iconW) / 2f);
236:         hintView.setTranslationY(y - (hostH - iconH) / 2f);
342:             hintView.setAlpha(0f);
343:             hintView.setScaleX(1f);
344:             hintView.setScaleY(1f);
345:             hintView.setTranslationX(0f);
346:             hintView.setTranslationY(0f);
351:                 .setDuration(180L)
352:                 .start();
384:         view.setPadding(0, 0, 0, 0);
385:         view.setAlpha(0f);
392:     private LayoutParams hintLayoutParams(float density) {
393:         int size = Math.round(HINT_SIZE_DP * density);
394:         LayoutParams lp = new LayoutParams(size, size);
```

### app/src/main/java/com/mapcontrol/ui/widget/LauncherLitePanelView.java

```text
60:         setOrientation(VERTICAL);
61:         setGravity(Gravity.CENTER);
63:         setPadding(pad, pad, pad, pad);
74:         clockView.setTextSize(TypedValue.COMPLEX_UNIT_PX,
75:                 getResources().getDimension(R.dimen.launcher_lite_clock));
76:         clockView.setTypeface(Typeface.create("sans-serif-light", Typeface.NORMAL));
77:         clockView.setTextColor(UiStyles.color(getContext(), R.color.textPrimary));
78:         clockView.setGravity(Gravity.CENTER);
79:         clockView.setIncludeFontPadding(false);
80:         clockView.setLetterSpacing(0.04f);
84:         dateView.setTextSize(TypedValue.COMPLEX_UNIT_PX,
85:                 getResources().getDimension(R.dimen.launcher_lite_date));
86:         dateView.setTypeface(Typeface.create("sans-serif", Typeface.NORMAL));
87:         dateView.setTextColor(UiStyles.color(getContext(), R.color.textMuted));
88:         dateView.setGravity(Gravity.CENTER);
89:         dateView.setIncludeFontPadding(false);
90:         dateView.setLetterSpacing(0.02f);
91:         LinearLayout.LayoutParams dateLp = matchWidthWrap();
97:         UiStyles.setTextSize(driveModeBadge, R.dimen.text_size_small);
98:         driveModeBadge.setTypeface(null, Typeface.BOLD);
99:         driveModeBadge.setGravity(Gravity.CENTER);
101:         driveModeBadge.setPadding(badgePad * 2, badgePad, badgePad * 2, badgePad);
102:         LinearLayout.LayoutParams badgeLp = wrapContent();
112:         weatherRow.setOrientation(HORIZONTAL);
113:         weatherRow.setGravity(Gravity.CENTER);
114:         LinearLayout.LayoutParams weatherRowLp = matchWidthWrap();
119:         weatherGlyphView.setTextSize(TypedValue.COMPLEX_UNIT_PX,
120:                 getResources().getDimension(R.dimen.launcher_lite_weather_glyph));
121:         weatherGlyphView.setTypeface(Typeface.DEFAULT);
122:         weatherGlyphView.setIncludeFontPadding(false);
123:         weatherGlyphView.setGravity(Gravity.CENTER);
124:         LinearLayout.LayoutParams glyphLp = wrapContent();
125:         glyphLp.setMarginEnd(UiStyles.dimenPx(getContext(), R.dimen.spacing_small));
129:         weatherTempView.setTextSize(TypedValue.COMPLEX_UNIT_PX,
130:                 getResources().getDimension(R.dimen.launcher_lite_weather_temp));
131:         weatherTempView.setTypeface(Typeface.create("sans-serif", Typeface.NORMAL));
132:         weatherTempView.setTextColor(UiStyles.color(getContext(), R.color.textPrimary));
133:         weatherTempView.setIncludeFontPadding(false);
134:         LinearLayout.LayoutParams tempLp = wrapContent();
135:         tempLp.setMarginEnd(UiStyles.dimenPx(getContext(), R.dimen.spacing_small));
139:         weatherLabelView.setTextSize(TypedValue.COMPLEX_UNIT_PX,
140:                 getResources().getDimension(R.dimen.launcher_lite_weather_label));
141:         weatherLabelView.setTypeface(Typeface.create("sans-serif", Typeface.NORMAL));
142:         weatherLabelView.setTextColor(UiStyles.color(getContext(), R.color.textSecondary));
143:         weatherLabelView.setIncludeFontPadding(false);
150:         statsRow.setOrientation(HORIZONTAL);
151:         statsRow.setGravity(Gravity.CENTER);
152:         statsRow.setWeightSum(3f);
153:         LinearLayout.LayoutParams statsRowLp = matchWidthWrap();
181:         col.setOrientation(VERTICAL);
182:         col.setGravity(Gravity.CENTER);
186:         value.setTextSize(TypedValue.COMPLEX_UNIT_PX,
187:                 getResources().getDimension(R.dimen.launcher_lite_stat_value));
188:         value.setTypeface(Typeface.create("sans-serif-light", Typeface.NORMAL));
189:         value.setTextColor(UiStyles.color(getContext(), R.color.textPrimary));
190:         value.setGravity(Gravity.CENTER);
191:         value.setIncludeFontPadding(false);
196:         caption.setTextSize(TypedValue.COMPLEX_UNIT_PX,
197:                 getResources().getDimension(R.dimen.launcher_lite_stat_label));
198:         caption.setTypeface(Typeface.create("sans-serif", Typeface.NORMAL));
199:         caption.setTextColor(UiStyles.color(getContext(), R.color.textMuted));
200:         caption.setGravity(Gravity.CENTER);
201:         caption.setIncludeFontPadding(false);
203:         caption.setLetterSpacing(0.08f);
204:         LinearLayout.LayoutParams captionLp = matchWidthWrap();
212:         divider.setBackgroundColor(UiStyles.color(getContext(), R.color.outlineSubtle));
216:     private LinearLayout.LayoutParams dividerLp() {
217:         LinearLayout.LayoutParams lp = new LinearLayout.LayoutParams(
218:                 LinearLayout.LayoutParams.MATCH_PARENT,
219:                 UiStyles.dimenPx(getContext(), R.dimen.launcher_lite_divider_height));
224:     private static LinearLayout.LayoutParams matchWidthWrap() {
225:         return new LinearLayout.LayoutParams(
226:                 LinearLayout.LayoutParams.MATCH_PARENT,
227:                 LinearLayout.LayoutParams.WRAP_CONTENT);
230:     private static LinearLayout.LayoutParams wrapContent() {
231:         return new LinearLayout.LayoutParams(
232:                 LinearLayout.LayoutParams.WRAP_CONTENT,
233:                 LinearLayout.LayoutParams.WRAP_CONTENT);
236:     private LinearLayout.LayoutParams weightedColLp() {
237:         LinearLayout.LayoutParams lp = new LinearLayout.LayoutParams(
238:                 0,
239:                 LinearLayout.LayoutParams.WRAP_CONTENT,
240:                 1f);
242:         lp.setMarginStart(gap);
243:         lp.setMarginEnd(gap);
292:         background.setColor(fill);
293:         background.setCornerRadius(UiStyles.dimenPx(getContext(), R.dimen.oem_button_radius));
295:         driveModeBadge.setTextColor(available ? UiStyles.textOnColor(fill)
296:                 : UiStyles.color(getContext(), R.color.textHint));
305:             clockView.setTextColor(primary);
308:             dateView.setTextColor(muted);
311:             weatherTempView.setTextColor(primary);
314:             weatherLabelView.setTextColor(secondary);
317:             statSpeedValueView.setTextColor(primary);
320:             statGearValueView.setTextColor(primary);
323:             statFuelValueView.setTextColor(primary);
327:             weatherDivider.setBackgroundColor(UiStyles.color(getContext(), R.color.outlineSubtle));
330:             statsDivider.setBackgroundColor(UiStyles.color(getContext(), R.color.outlineSubtle));
340:             statSpeedUnitView.setTextColor(muted);
343:             statGearLabelView.setTextColor(muted);
346:             statFuelLabelView.setTextColor(muted);
```

### app/src/main/java/com/mapcontrol/ui/widget/VehicleModelHostView.java

```text
3: import android.animation.Animator;
4: import android.animation.AnimatorListenerAdapter;
5: import android.animation.AnimatorSet;
6: import android.animation.ObjectAnimator;
71:         addView(carImageView, new LayoutParams(LayoutParams.MATCH_PARENT, LayoutParams.MATCH_PARENT));
76:         addView(animationView, new LayoutParams(LayoutParams.MATCH_PARENT, LayoutParams.MATCH_PARENT));
84:         addView(fallbackView, new LayoutParams(LayoutParams.MATCH_PARENT, LayoutParams.MATCH_PARENT));
91:             LayoutParams glowLp = createGlowLayoutParams(h);
96:     private LayoutParams createGlowLayoutParams(int hostHeight) {
97:         int reference = hostHeight > 0
98:                 ? hostHeight
99:                 : getResources().getDimensionPixelSize(R.dimen.launcher_dashboard_vehicle_height);
100:         LayoutParams glowLp = new LayoutParams((int) (reference * 0.95f), (int) (reference * 0.72f));
207:         fadeOut.setDuration(300L);
210:             public void onAnimationEnd(Animator animation) {
211:                 outgoing.setVisibility(GONE);
219:         incoming.setAlpha(0f);
220:         incoming.setScaleX(0f);
221:         incoming.setScaleY(0f);
229:         set.setStartDelay(200L);
230:         set.setDuration(300L);
```

### app/src/main/java/com/mapcontrol/util/DialogHelper.java

```text
32:         scrollView.setBackgroundColor(UiStyles.color(context, R.color.backgroundPage));
34:         contentContainer.addView(scrollView, new LinearLayout.LayoutParams(
35:                 LinearLayout.LayoutParams.MATCH_PARENT, 0, 1f));
36:         mainContainer.addView(contentContainer, new LinearLayout.LayoutParams(
37:                 LinearLayout.LayoutParams.MATCH_PARENT, 0, 1f));
60:         scrollView.setBackgroundColor(UiStyles.color(context, R.color.backgroundPage));
62:         contentContainer.addView(scrollView, new LinearLayout.LayoutParams(
63:                 LinearLayout.LayoutParams.MATCH_PARENT, 0, 1f));
64:         mainContainer.addView(contentContainer, new LinearLayout.LayoutParams(
65:                 LinearLayout.LayoutParams.MATCH_PARENT, 0, 1f));
84:         dialogLayout.setOrientation(LinearLayout.VERTICAL);
85:         dialogLayout.setPadding(48, 40, 48, 24);
86:         dialogLayout.setBackgroundColor(UiStyles.color(context, R.color.backgroundPage));
90:         titleView.setTextSize(20);
91:         titleView.setTextColor(UiStyles.color(context, R.color.accentHighlight));
92:         titleView.setTypeface(null, android.graphics.Typeface.BOLD);
93:         titleView.setGravity(android.view.Gravity.CENTER);
98:         messageView.setTextSize(15);
99:         messageView.setTextColor(UiStyles.color(context, R.color.textMessage));
100:         messageView.setPadding(0, 32, 0, 32);
101:         messageView.setLineSpacing(6, 1);
112:         dialog.getButton(AlertDialog.BUTTON_POSITIVE).setTextColor(UiStyles.color(context, R.color.accentHighlight));
113:         dialog.getButton(AlertDialog.BUTTON_NEGATIVE).setTextColor(UiStyles.color(context, R.color.textDialogButtonSecondary));
136:                     textView.setTextColor(UiStyles.color(context, R.color.accentHighlight));
137:                     textView.setTextSize(16);
158:             dialog.getButton(AlertDialog.BUTTON_POSITIVE).setTextColor(UiStyles.color(context, R.color.buttonSuccessBright));
159:             dialog.getButton(AlertDialog.BUTTON_POSITIVE).setTextSize(17);
160:             dialog.getButton(AlertDialog.BUTTON_POSITIVE).setTypeface(null, android.graphics.Typeface.BOLD);
163:             dialog.getButton(AlertDialog.BUTTON_NEGATIVE).setTextColor(UiStyles.color(context, R.color.statusErrorBright));
164:             dialog.getButton(AlertDialog.BUTTON_NEGATIVE).setTextSize(17);
165:             dialog.getButton(AlertDialog.BUTTON_NEGATIVE).setTypeface(null, android.graphics.Typeface.BOLD);
168:             dialog.getButton(AlertDialog.BUTTON_NEUTRAL).setTextColor(UiStyles.color(context, R.color.textLoading));
169:             dialog.getButton(AlertDialog.BUTTON_NEUTRAL).setTextSize(17);
170:             dialog.getButton(AlertDialog.BUTTON_NEUTRAL).setTypeface(null, android.graphics.Typeface.BOLD);
176:         mainContainer.setOrientation(LinearLayout.VERTICAL);
177:         mainContainer.setBackgroundColor(UiStyles.color(context, R.color.backgroundPage));
183:         titleContainer.setOrientation(LinearLayout.VERTICAL);
184:         titleContainer.setPadding(32, 32, 32, 24);
185:         titleContainer.setBackgroundColor(UiStyles.color(context, R.color.surfaceCard));
189:         titleView.setTextSize(24);
190:         titleView.setTextColor(UiStyles.color(context, R.color.textPrimary));
191:         titleView.setTypeface(null, android.graphics.Typeface.BOLD);
196:         subtitleView.setTextSize(16);
197:         subtitleView.setTextColor(UiStyles.color(context, R.color.accentHighlight));
198:         subtitleView.setPadding(0, 8, 0, 0);
205:         contentContainer.setOrientation(LinearLayout.VERTICAL);
206:         contentContainer.setPadding(32, 24, 32, 24);
207:         contentContainer.setBackgroundColor(UiStyles.color(context, R.color.backgroundPage));
214:         messageView.setTextSize(15);
215:         messageView.setTextColor(UiStyles.color(context, R.color.textMessage));
216:         messageView.setLineSpacing(12, 1.4f);
227:                 positiveButton.setTextColor(UiStyles.color(dialog.getContext(), R.color.textPrimary));
228:                 positiveButton.setTextSize(16);
229:                 positiveButton.setTypeface(null, android.graphics.Typeface.BOLD);
231:                 positiveBg.setColor(UiStyles.color(dialog.getContext(), R.color.buttonPrimary));
232:                 positiveBg.setCornerRadius(12);
237:                 negativeButton.setTextColor(UiStyles.color(dialog.getContext(), R.color.textSecondary));
238:                 negativeButton.setBackgroundColor(UiStyles.color(dialog.getContext(), R.color.transparent));
239:                 negativeButton.setTextSize(16);
240:                 negativeButton.setTypeface(null, android.graphics.Typeface.NORMAL);
```

### app/src/main/java/com/mapcontrol/util/DisplayHelper.java

```text
2: import android.animation.AnimatorSet;
3: import android.animation.ObjectAnimator;
4: import android.animation.ValueAnimator;
20: import android.view.animation.AccelerateDecelerateInterpolator;
21: import android.view.animation.LinearInterpolator;
39:     private static android.view.WindowManager.LayoutParams preparingParams = null;
41:     private static final List<android.animation.Animator> preparingAnimators = new ArrayList<>();
46:     private static android.view.WindowManager.LayoutParams bootSplashParams = null;
50:     private static final List<android.animation.Animator> bootSplashAnimators = new ArrayList<>();
93:             preparingParams = new android.view.WindowManager.LayoutParams(
94:                     android.view.WindowManager.LayoutParams.MATCH_PARENT,
95:                     android.view.WindowManager.LayoutParams.MATCH_PARENT,
96:                     android.view.WindowManager.LayoutParams.TYPE_APPLICATION_OVERLAY,
97:                     android.view.WindowManager.LayoutParams.FLAG_NOT_FOCUSABLE
98:                             | android.view.WindowManager.LayoutParams.FLAG_NOT_TOUCH_MODAL
99:                             | android.view.WindowManager.LayoutParams.FLAG_LAYOUT_IN_SCREEN,
100:                     android.graphics.PixelFormat.TRANSLUCENT
101:             );
113:         root.setBackgroundColor(UiStyles.color(ctx, R.color.preparing_overlay_root));
117:         card.setOrientation(LinearLayout.VERTICAL);
118:         card.setGravity(Gravity.CENTER);
122:         card.setPadding(padH, padV, padH, padV);
124:         FrameLayout.LayoutParams cardLp = new FrameLayout.LayoutParams(
125:                 FrameLayout.LayoutParams.WRAP_CONTENT,
126:                 FrameLayout.LayoutParams.WRAP_CONTENT);
132:         LinearLayout.LayoutParams spinnerLp = new LinearLayout.LayoutParams(wrapSize, wrapSize);
139:         FrameLayout.LayoutParams haloLp = new FrameLayout.LayoutParams(wrapSize, wrapSize);
148:         FrameLayout.LayoutParams spLp = new FrameLayout.LayoutParams(spinnerSize, spinnerSize);
157:         title.setTextColor(UiStyles.color(ctx, R.color.textPrimary));
158:         title.setTextSize(TypedValue.COMPLEX_UNIT_SP, 20);
159:         title.setTypeface(null, Typeface.BOLD);
160:         title.setLetterSpacing(0.02f);
161:         title.setGravity(Gravity.CENTER);
162:         LinearLayout.LayoutParams titleLp = new LinearLayout.LayoutParams(
163:                 LinearLayout.LayoutParams.WRAP_CONTENT,
164:                 LinearLayout.LayoutParams.WRAP_CONTENT);
171:         sub.setTextColor(UiStyles.color(ctx, R.color.textSecondaryCool));
172:         sub.setTextSize(TypedValue.COMPLEX_UNIT_SP, 14);
173:         sub.setGravity(Gravity.CENTER);
174:         LinearLayout.LayoutParams subLp = new LinearLayout.LayoutParams(
175:                 LinearLayout.LayoutParams.WRAP_CONTENT,
176:                 LinearLayout.LayoutParams.WRAP_CONTENT);
183:         dots.setOrientation(LinearLayout.HORIZONTAL);
184:         dots.setGravity(Gravity.CENTER);
185:         LinearLayout.LayoutParams dotsLp = new LinearLayout.LayoutParams(
186:                 LinearLayout.LayoutParams.WRAP_CONTENT,
187:                 LinearLayout.LayoutParams.WRAP_CONTENT);
198:             bg.setColor(accent);
200:             LinearLayout.LayoutParams dotLp = new LinearLayout.LayoutParams(dotSize, dotSize);
203:             dot.setAlpha(0.25f);
220:         root.setAlpha(0f);
221:         root.animate().alpha(1f).setDuration(220).start();
224:             card.setAlpha(0f);
225:             card.setTranslationY(dp(root.getContext(), 12));
229:                     .setStartDelay(60)
230:                     .setDuration(320)
231:                     .setInterpolator(new AccelerateDecelerateInterpolator())
232:                     .start();
237:             rotate.setDuration(900);
238:             rotate.setInterpolator(new LinearInterpolator());
239:             rotate.setRepeatCount(ValueAnimator.INFINITE);
249:                 a.setDuration(1100);
250:                 a.setInterpolator(new AccelerateDecelerateInterpolator());
251:                 a.setRepeatCount(ValueAnimator.INFINITE);
252:                 a.setRepeatMode(ValueAnimator.REVERSE);
268:                     a.setDuration(520);
269:                     a.setStartDelay(i * 160L);
270:                     a.setRepeatCount(ValueAnimator.INFINITE);
271:                     a.setRepeatMode(ValueAnimator.REVERSE);
272:                     a.setInterpolator(new AccelerateDecelerateInterpolator());
303:         for (android.animation.Animator a : preparingAnimators) {
304:             try {
305:                 a.cancel();
361:             bootSplashParams = new android.view.WindowManager.LayoutParams(
362:                     android.view.WindowManager.LayoutParams.MATCH_PARENT,
363:                     android.view.WindowManager.LayoutParams.MATCH_PARENT,
364:                     android.view.WindowManager.LayoutParams.TYPE_APPLICATION_OVERLAY,
365:                     android.view.WindowManager.LayoutParams.FLAG_NOT_FOCUSABLE
366:                             | android.view.WindowManager.LayoutParams.FLAG_NOT_TOUCH_MODAL
367:                             | android.view.WindowManager.LayoutParams.FLAG_LAYOUT_IN_SCREEN,
368:                     android.graphics.PixelFormat.TRANSLUCENT
369:             );
492:         root.addView(bgGradient, new FrameLayout.LayoutParams(
493:                 FrameLayout.LayoutParams.MATCH_PARENT,
494:                 FrameLayout.LayoutParams.MATCH_PARENT));
499:         root.addView(grid, new FrameLayout.LayoutParams(
500:                 FrameLayout.LayoutParams.MATCH_PARENT,
501:                 FrameLayout.LayoutParams.MATCH_PARENT));
513:         FrameLayout.LayoutParams haloLp = new FrameLayout.LayoutParams(haloSize, haloSize);
520:         center.setOrientation(LinearLayout.VERTICAL);
521:         center.setGravity(Gravity.CENTER);
536:         LinearLayout.LayoutParams logoLp = new LinearLayout.LayoutParams(logoSize, logoSize);
544:         wordmark.setTextColor(UiStyles.color(ctx, R.color.textPrimary));
545:         wordmark.setTextSize(TypedValue.COMPLEX_UNIT_SP, 32);
546:         wordmark.setTypeface(null, Typeface.BOLD);
547:         wordmark.setLetterSpacing(0.18f);
548:         wordmark.setGravity(Gravity.CENTER);
549:         LinearLayout.LayoutParams wmLp = new LinearLayout.LayoutParams(
550:                 LinearLayout.LayoutParams.WRAP_CONTENT,
551:                 LinearLayout.LayoutParams.WRAP_CONTENT);
559:         tagline.setTextColor(UiStyles.color(ctx, R.color.textSecondaryCool));
560:         tagline.setTextSize(TypedValue.COMPLEX_UNIT_SP, 14);
561:         tagline.setLetterSpacing(0.12f);
562:         tagline.setGravity(Gravity.CENTER);
563:         LinearLayout.LayoutParams tagLp = new LinearLayout.LayoutParams(
564:                 LinearLayout.LayoutParams.WRAP_CONTENT,
565:                 LinearLayout.LayoutParams.WRAP_CONTENT);
576:         LinearLayout.LayoutParams radarLp = new LinearLayout.LayoutParams(radarW, radarH);
582:         FrameLayout.LayoutParams centerLp = new FrameLayout.LayoutParams(
583:                 FrameLayout.LayoutParams.WRAP_CONTENT,
584:                 FrameLayout.LayoutParams.WRAP_CONTENT);
595:         root.setAlpha(0f);
596:         root.animate().alpha(1f).setDuration(360).start();
608:             flow.setDuration(10_000);
609:             flow.setRepeatCount(ValueAnimator.INFINITE);
610:             flow.setInterpolator(new LinearInterpolator());
619:             scroll.setDuration(8000);
620:             scroll.setRepeatCount(ValueAnimator.INFINITE);
621:             scroll.setInterpolator(new LinearInterpolator());
629:             halo.setAlpha(0.0f);
631:             fadeIn.setDuration(900);
632:             fadeIn.setInterpolator(new AccelerateDecelerateInterpolator());
640:                 a.setDuration(2200);
641:                 a.setStartDelay(900);
642:                 a.setInterpolator(new AccelerateDecelerateInterpolator());
643:                 a.setRepeatCount(ValueAnimator.INFINITE);
644:                 a.setRepeatMode(ValueAnimator.REVERSE);
653:             center.setAlpha(0f);
654:             center.setTranslationY(dp(root.getContext(), 18));
658:                     .setStartDelay(220)
659:                     .setDuration(520)
660:                     .setInterpolator(new AccelerateDecelerateInterpolator())
661:                     .start();
667:             floatY.setDuration(3200);
668:             floatY.setRepeatCount(ValueAnimator.INFINITE);
669:             floatY.setInterpolator(new AccelerateDecelerateInterpolator());
676:             blink.setDuration(1400);
677:             blink.setRepeatCount(ValueAnimator.INFINITE);
678:             blink.setRepeatMode(ValueAnimator.REVERSE);
679:             blink.setInterpolator(new AccelerateDecelerateInterpolator());
687:             pulse.setDuration(2200);
688:             pulse.setRepeatCount(ValueAnimator.INFINITE);
689:             pulse.setInterpolator(new LinearInterpolator());
696:             pinBounce.setDuration(1800);
697:             pinBounce.setRepeatCount(ValueAnimator.INFINITE);
698:             pinBounce.setInterpolator(new AccelerateDecelerateInterpolator());
705:         for (android.animation.Animator a : bootSplashAnimators) {
706:             try {
707:                 a.cancel();
757:             drawable.setOrientation(orientationForAngle(angle));
818:             linePaint.setColor(UiStyles.color(ctx, R.color.map_grid_line_accent));
822:             glowPaint.setColor(UiStyles.color(ctx, R.color.map_grid_glow_accent));
867:             glowPaint.setAlpha((int) (glowPulseAlphaMax * (1f - Math.abs(0.5f - t) * 2f)));
908:         @Override public void setAlpha(int alpha) { paint.setAlpha(alpha); }
929:             wavePaint.setColor(accent);
932:             pinFillPaint.setColor(accent);
935:             pinDotPaint.setColor(UiStyles.color(ctx, R.color.boot_splash_pin_dot));
938:             pinShadowPaint.setColor(UiStyles.color(ctx, R.color.boot_splash_pin_shadow));
961:                 wavePaint.setAlpha(alpha);
```

### app/src/main/java/com/mapcontrol/util/InstallerConfirmClicker.java

```text
150:             bar.setOrientation(LinearLayout.HORIZONTAL);
151:             bar.setGravity(Gravity.CENTER_VERTICAL);
153:             bar.setPadding(pad, dp(service, 12), pad, dp(service, 12));
155:             background.setColor(0xE61B1F27);
156:             background.setCornerRadius(dp(service, 18));
160:             text.setTextColor(Color.WHITE);
161:             text.setTextSize(TypedValue.COMPLEX_UNIT_SP, 15);
165:             bar.addView(text, new LinearLayout.LayoutParams(0, LinearLayout.LayoutParams.WRAP_CONTENT, 1f));
170:             cancel.setTextColor(Color.WHITE);
171:             cancel.setTextSize(TypedValue.COMPLEX_UNIT_SP, 14);
173:             cancelBg.setColor(0xFF5C6370);
174:             cancelBg.setCornerRadius(dp(service, 12));
176:             cancel.setPadding(dp(service, 18), dp(service, 6), dp(service, 18), dp(service, 6));
177:             cancel.setMinWidth(0);
178:             cancel.setMinHeight(0);
179:             cancel.setMinimumWidth(0);
180:             cancel.setMinimumHeight(0);
187:             LinearLayout.LayoutParams cancelLp = new LinearLayout.LayoutParams(
188:                     LinearLayout.LayoutParams.WRAP_CONTENT,
189:                     LinearLayout.LayoutParams.WRAP_CONTENT);
193:             WindowManager.LayoutParams params = new WindowManager.LayoutParams(
194:                     WindowManager.LayoutParams.MATCH_PARENT,
195:                     WindowManager.LayoutParams.WRAP_CONTENT,
196:                     WindowManager.LayoutParams.TYPE_ACCESSIBILITY_OVERLAY,
197:                     WindowManager.LayoutParams.FLAG_NOT_FOCUSABLE
198:                             | WindowManager.LayoutParams.FLAG_LAYOUT_IN_SCREEN,
199:                     android.graphics.PixelFormat.TRANSLUCENT);
```

### app/src/main/java/com/mapcontrol/util/OnboardingHelper.java

```text
3: import android.animation.ObjectAnimator;
4: import android.animation.ValueAnimator;
14: import android.view.animation.AccelerateDecelerateInterpolator;
15: import android.view.animation.DecelerateInterpolator;
39:         content.setOrientation(LinearLayout.VERTICAL);
40:         content.setGravity(Gravity.CENTER_HORIZONTAL);
41:         FrameLayout.LayoutParams contentLp = new FrameLayout.LayoutParams(
42:                 FrameLayout.LayoutParams.WRAP_CONTENT, FrameLayout.LayoutParams.WRAP_CONTENT);
54:         content.addView(logo, new LinearLayout.LayoutParams(dp(ctx, 88), dp(ctx, 88)));
57:         title.setTypeface(null, Typeface.BOLD);
58:         title.setLetterSpacing(0.12f);
65:         langLabel.setLetterSpacing(0.1f);
70:         langRow.setOrientation(LinearLayout.HORIZONTAL);
71:         langRow.setGravity(Gravity.CENTER);
79:         next.setTextColor(Color.WHITE);
80:         next.setTypeface(null, Typeface.BOLD);
81:         next.setPadding(dp(ctx, 48), dp(ctx, 14), dp(ctx, 48), dp(ctx, 14));
89:             steps[i].setAlpha(0f);
90:             steps[i].setTranslationY(dp(ctx, 22));
92:                     .setStartDelay(250L + i * 130L).setDuration(520)
93:                     .setInterpolator(new DecelerateInterpolator()).start();
97:         floatY.setStartDelay(1200);
98:         floatY.setDuration(3200);
99:         floatY.setRepeatCount(ValueAnimator.INFINITE);
100:         floatY.setInterpolator(new AccelerateDecelerateInterpolator());
113:         chip.setTypeface(null, Typeface.BOLD);
114:         chip.setPadding(dp(ctx, 28), dp(ctx, 12), dp(ctx, 28), dp(ctx, 12));
117:             chip.setTextColor(Color.WHITE);
133:         d.setColor(fill);
134:         d.setCornerRadius(1000f);
135:         if (strokeWidth > 0) d.setStroke(strokeWidth, stroke);
142:         tv.setTextSize(TypedValue.COMPLEX_UNIT_SP, sp);
143:         tv.setTextColor(UiStyles.color(ctx, colorRes));
144:         tv.setGravity(Gravity.CENTER);
148:     private static LinearLayout.LayoutParams lp(int topMargin, int bottomMargin) {
149:         LinearLayout.LayoutParams lp = new LinearLayout.LayoutParams(
150:                 LinearLayout.LayoutParams.WRAP_CONTENT, LinearLayout.LayoutParams.WRAP_CONTENT);
156:     private static LinearLayout.LayoutParams chipLp(Context ctx) {
157:         LinearLayout.LayoutParams lp = new LinearLayout.LayoutParams(
158:                 LinearLayout.LayoutParams.WRAP_CONTENT, LinearLayout.LayoutParams.WRAP_CONTENT);
159:         lp.setMargins(dp(ctx, 8), 0, dp(ctx, 8), 0);
```

### app/src/main/java/com/mapcontrol/util/OverlayDismissal.java

```text
3: import android.animation.Animator;
4: import android.animation.AnimatorListenerAdapter;
43:             view.animate().alpha(0f).setDuration(durationMs).setListener(new AnimatorListenerAdapter() {
44:                 @Override public void onAnimationEnd(Animator animation) { remove.run(); }
45:                 @Override public void onAnimationCancel(Animator animation) { remove.run(); }
```

### app/src/main/java/com/mapcontrol/util/PermissionProbeDialog.java

```text
46:         root.setOrientation(LinearLayout.VERTICAL);
47:         root.setPadding(36, 28, 36, 12);
48:         root.setBackgroundColor(UiStyles.color(activity, R.color.backgroundPage));
52:         title.setTextSize(20);
53:         title.setTypeface(null, Typeface.BOLD);
54:         title.setTextColor(UiStyles.color(activity, R.color.textPrimary));
59:         legend.setTextSize(13);
60:         legend.setTextColor(UiStyles.color(activity, R.color.textHint));
61:         legend.setPadding(0, 12, 0, 8);
66:         summary.setTextSize(15);
67:         summary.setTypeface(null, Typeface.BOLD);
68:         summary.setTextColor(UiStyles.color(activity, R.color.textPrimary));
69:         summary.setPadding(0, 4, 0, 12);
73:         rows.setOrientation(LinearLayout.VERTICAL);
75:         scroll.setFillViewport(true);
76:         scroll.addView(rows, new ScrollView.LayoutParams(
77:                 ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT));
78:         root.addView(scroll, new LinearLayout.LayoutParams(
79:                 ViewGroup.LayoutParams.MATCH_PARENT, 0, 1f));
98:                         .setTextColor(UiStyles.color(activity, R.color.textDialogButtonSecondary));
146:         line.setOrientation(LinearLayout.VERTICAL);
147:         line.setPadding(0, 10, 0, 10);
157:         name.setTextSize(15);
158:         name.setTypeface(null, Typeface.BOLD);
159:         name.setTextColor(color);
164:         detail.setTextSize(13);
165:         detail.setTextColor(UiStyles.color(context, R.color.textHint));
166:         detail.setPadding(0, 4, 0, 0);
167:         line.addView(detail, new LinearLayout.LayoutParams(
168:                 ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT));
170:         LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(
171:                 ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT);
```

### app/src/main/java/com/mapcontrol/vehicle/material/VehicleMaterialPickerDialog.java

```text
78:         scroll.setPadding(metrics.pad, metrics.pad, metrics.pad, metrics.pad);
79:         scroll.setFillViewport(false);
83:         grid.setColumnCount(metrics.columns);
85:         scroll.addView(grid, new ScrollView.LayoutParams(gridWidth, ScrollView.LayoutParams.WRAP_CONTENT));
107:             window.setLayout(metrics.dialogWidthPx, WindowManager.LayoutParams.WRAP_CONTENT);
146:         LinearLayout.LayoutParams iconLp = new LinearLayout.LayoutParams(iconSize, iconSize);
156:         hint.setTextSize(TypedValue.COMPLEX_UNIT_SP, 10);
157:         hint.setTextColor(UiStyles.color(activity, R.color.textHint));
158:         hint.setGravity(Gravity.CENTER);
187:         LinearLayout.LayoutParams previewLp = new LinearLayout.LayoutParams(
188:                 LinearLayout.LayoutParams.MATCH_PARENT, 0, 1f);
209:         tile.setOrientation(LinearLayout.VERTICAL);
210:         tile.setGravity(Gravity.CENTER_HORIZONTAL);
212:         tile.setPadding(inner, inner, inner, inner);
217:         GridLayout.LayoutParams lp = new GridLayout.LayoutParams();
220:         lp.setMargins(metrics.gap, metrics.gap, metrics.gap, metrics.gap);
225:     private static LinearLayout.LayoutParams wrapContentLp() {
226:         return new LinearLayout.LayoutParams(
227:                 LinearLayout.LayoutParams.MATCH_PARENT,
228:                 LinearLayout.LayoutParams.WRAP_CONTENT);
234:         label.setTextSize(TypedValue.COMPLEX_UNIT_SP, 11);
235:         label.setTypeface(null, Typeface.BOLD);
236:         label.setTextColor(UiStyles.color(activity, R.color.textPrimary));
237:         label.setGravity(Gravity.CENTER);
238:         label.setPadding(0, UiStyles.dimenPx(activity, R.dimen.spacing_tiny), 0, 0);
239:         label.setMaxLines(2);
```

### app/src/main/java/com/mapcontrol/vehicle/PowerModeSample.java

```text
3: /** Missing/invalid VDBus samples cannot initiate an ignition transition. */
4: public final class PowerModeSample {
5:     private PowerModeSample() { }
6:     public static int read(int[] values) {
7:         if (values == null || values.length == 0) return -1;
```

## Builder / widget API imzaları

Java kurucu argümanları, callback alanları ve public metotlar React prop karşılığıdır; dönüş tipi/yaşam döngüsü korunmalıdır. Örneklerde kullanılacak gerçek API için kaynakta gövdeyi de inceleyin.

### app/src/main/java/com/mapcontrol/ui/activity/CameraActivity.java

```text
31: public class CameraActivity extends AppCompatActivity implements TextureView.SurfaceTextureListener
73: public void onCameraAvailable(@NonNull String str)
97: public void onCameraUnavailable(@NonNull String str)
110: public void onDisconnected(@NonNull CameraDevice cameraDevice)
125: public void onError(@NonNull CameraDevice cameraDevice, int i2)
131: public void onOpened(@NonNull CameraDevice cameraDevice)
142: public void onLog(String message)
405: public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults)
414: public void onSurfaceTextureAvailable(@NonNull SurfaceTexture surfaceTexture, int i2, int i3)
446: public boolean onSurfaceTextureDestroyed(@NonNull SurfaceTexture surfaceTexture)
468: public void onSurfaceTextureSizeChanged(@NonNull SurfaceTexture surfaceTexture, int i2, int i3)
472: public void onSurfaceTextureUpdated(@NonNull SurfaceTexture surfaceTexture)
```

### app/src/main/java/com/mapcontrol/ui/activity/ClusterVDBusTestActivity.java

```text
26: public class ClusterVDBusTestActivity extends AppCompatActivity implements ClusterVDBusBenchManager.UiActions
345: public void navKeyToggleLikeVDBus()
350: public void alertToneLikeVDBus()
355: public void clusterOpenDirectUi()
360: public void clusterCloseDirectUi()
```

### app/src/main/java/com/mapcontrol/ui/activity/DmsFaceProfileActivity.java

```text
63: public final class DmsFaceProfileActivity extends AppCompatActivity implements TextureView.SurfaceTextureListener
122: public void run()
138: public void onCameraAvailable(@NonNull String cameraId)
146: public void onCameraUnavailable(@NonNull String cameraId)
912: public void onOpened(@NonNull CameraDevice camera)
919: public void onDisconnected(@NonNull CameraDevice camera)
931: public void onError(@NonNull CameraDevice camera, int error)
966: public void onConfigured(@NonNull CameraCaptureSession session)
986: public void onConfigureFailed(@NonNull CameraCaptureSession session)
1001: public void onSurfaceTextureAvailable(@NonNull SurfaceTexture surface, int width, int height)
1008: public void onSurfaceTextureSizeChanged(@NonNull SurfaceTexture surface, int width, int height)
1012: public boolean onSurfaceTextureDestroyed(@NonNull SurfaceTexture surface)
1018: public void onSurfaceTextureUpdated(@NonNull SurfaceTexture surface)
1249: public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults)
```

### app/src/main/java/com/mapcontrol/ui/activity/MainActivity.java

```text
108: public class MainActivity extends AppCompatActivity
111: public static final String EXTRA_OPEN_PROJECTION_TARGET_PICKER = "com.mapcontrol.extra.OPEN_PROJECTION_TARGET_PICKER";
115: public static final int TAB_CLIMATE_AUTOMATION = 12;
116: public static final int TAB_FACE_RECOGNITION = 13;
124: public void onReceive(Context context, Intent intent)
220: public void onReceive(Context context, Intent intent)
235: public void onReceive(Context context, Intent intent)
256: public void onReceive(Context context, Intent intent)
351: public void log(String msg)
363: public void onNavigationStateChanged(boolean isOpen)
373: public String getTargetPackage()
378: public void log(String message)
388: public void onLogReceived(String message)
393: public void log(String msg)
408: public void onTabSelected(int tabIndex, String title)
415: public void onAppManagementRequested()
420: public void log(String msg)
435: public void onStatusSelected(int tabIndex)
441: public void onLogTabToggle(boolean show)
453: public void log(String msg)
479: public void onServerStarted(int port, String localIp)
498: public void onServerStopped()
517: public void onError(String error)
529: public void onInstallApk(String fileName)
536: public void onDeleteApp(String packageName)
543: public void onLaunchApp(String packageName)
550: public void onLog(String message)
557: public void onOpenMapUrl(String url)
596: public void onTypeKeyboardText(String text, boolean showDeviceFeedback)
633: public void onOpenCluster()
638: public void onCloseCluster()
643: public void onSavePowerMode(int mode)
648: public void onStartKeyEventListener()
653: public void onStopKeyEventListener()
658: public String getTargetPackage()
663: public void onTargetPackageSelected(String packageName)
670: public boolean isSystemOrPrivApp(ApplicationInfo appInfo)
675: public void log(String msg)
680: public void onBringToMainDisplayCheckClusterSplash()
695: public void onModeSelected(int modeValue)
704: public void log(String msg)
714: public void onSettingChanged(String key, int value)
719: public void onSafetyWarningRequired(String key, int value, Runnable onUserConfirmed)
724: public void log(String message)
738: public void onClearLogs()
747: public void log(String msg)
752: public void onReadAloud(String text)
757: public void onWelcomeTts()
792: public boolean isSystemOrPrivApp(ApplicationInfo appInfo)
797: public boolean isSystemOrPrivApp(String packageName)
802: public void log(String msg)
814: public void log(String msg)
819: public String getCarToken()
824: public void onLauncherModeChanged(boolean enabled)
835: public void onLocaleChanged()
840: public void onNightModeChanged(int mode)
851: public void onShortcutSelected(int tabIndex, String title)
859: public void onExitLauncherRequested()
864: public void onAppLaunchRequested(String packageName)
871: public void onClusterProjectionRequested(String action)
1333: public void onAnimationCancel(Animator animation)
1339: public void onAnimationEnd(Animator animation)
1356: public void onAnimationCancel(Animator animation)
1362: public void onAnimationEnd(Animator animation)
1515: public void onWindowFocusChanged(boolean hasFocus)
1535: public void onBackPressed()
1555: public void onConfigurationChanged(Configuration newConfig)
1585: public void applyNightModeDynamically(int mode)
1885: public void onRequestPermissionsResult(int requestCode, String[] permissions, int[] grantResults)
2282: public static boolean dispatchOverlayBack()
2300: public static void appendBenchLogToMainIfActive(String msg)
2309: public static void benchNavKeyToggle()
2328: public static void benchAlertTone()
2337: public static void benchClusterOpenDirect()
2352: public static void benchClusterCloseDirect()
```

### app/src/main/java/com/mapcontrol/ui/builder/AppsTabBuilder.java

```text
56: public class AppsTabBuilder
61: public interface AppsCallback
190: public AppsTabBuilder(Context context, AppsCallback callback)
197: public LinearLayout build()
243: public LinearLayout getTabContent()
368: public void loadLocalApps()
589: public void onTabVisible()
601: public void clearInstallingProgress()
620: public void onTabHidden()
626: public void loadAppsFromServer()
739: public void onAvailable(@NonNull Network network)
1223: public void deleteApp(String packageName)
1249: public void launchApp(String packageName)
1641: public void showDownloadedFiles()
1918: public void installApkFile(String fileName)
1929: public void installApkFile(File apkFile)
2019: public void performReset()
```

### app/src/main/java/com/mapcontrol/ui/builder/AssistTabBuilder.java

```text
24: public class AssistTabBuilder
25: public interface AssistCallback
36: public AssistTabBuilder(Context context, SharedPreferences prefs, AssistCallback callback)
42: public ScrollView build()
```

### app/src/main/java/com/mapcontrol/ui/builder/ClimateAutomationTabBuilder.java

```text
26: public class ClimateAutomationTabBuilder implements ClimateAutomationEngine.Listener
28: public interface Callback
42: public ClimateAutomationTabBuilder(Context context, SharedPreferences prefs, Callback callback)
49: public LinearLayout build()
364: public void onClimateStatus(ClimateAutomationEngine.Status status)
540: public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser)
549: public void onStartTrackingTouch(SeekBar seekBar)
554: public void onStopTrackingTouch(SeekBar seekBar)
669: public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser)
676: public void onStartTrackingTouch(SeekBar seekBar)
680: public void onStopTrackingTouch(SeekBar seekBar)
```

### app/src/main/java/com/mapcontrol/ui/builder/DmsFaceRecognitionTabBuilder.java

```text
28: public final class DmsFaceRecognitionTabBuilder
29: public interface Logger
33: public static final int CAMERA_PERMISSION_REQUEST = 4217;
46: public DmsFaceRecognitionTabBuilder(Activity activity, SharedPreferences prefs, Logger logger)
52: public LinearLayout build()
171: public void onCameraPermissionResult(boolean granted)
183: public void refresh()
```

### app/src/main/java/com/mapcontrol/ui/builder/DriveModeTabBuilder.java

```text
36: public class DriveModeTabBuilder
37: public interface DriveModeCallback
57: public DriveModeTabBuilder(Context context, SharedPreferences prefs, DriveModeCallback callback)
64: public ScrollView build()
93: public void getOutline(View view, Outline outline)
452: public void onAnimationEnd(Animator animation)
535: public void release()
545: public ScrollView getScrollView()
549: public LinearLayout getTabContent()
```

### app/src/main/java/com/mapcontrol/ui/builder/EqualizerTabBuilder.java

```text
25: public class EqualizerTabBuilder
59: public EqualizerTabBuilder(Context context)
64: public void rebuild()
68: public ScrollView getScrollView()
72: public ScrollView build()
168: public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser)
178: public void onStartTrackingTouch(SeekBar seekBar)
182: public void onStopTrackingTouch(SeekBar seekBar)
371: public void onProgressChanged(SeekBar seekBar, int value, boolean fromUser)
385: public void onStartTrackingTouch(SeekBar seekBar)
389: public void onStopTrackingTouch(SeekBar seekBar)
```

### app/src/main/java/com/mapcontrol/ui/builder/FileUploadTabBuilder.java

```text
28: public class FileUploadTabBuilder
39: public FileUploadTabBuilder(Context context, WebServerManager webServerManager)
45: public ScrollView build()
157: public LinearLayout getFileUploadTabContent()
165: public ScrollView getScrollView()
172: public Button getToggleButton()
176: public TextView getStatusText()
180: public ImageView getQrImageView()
184: public void generateQRCode(String url)
```

### app/src/main/java/com/mapcontrol/ui/builder/LauncherDashboardBuilder.java

```text
54: public final class LauncherDashboardBuilder implements VehicleMetricsRepository.Listener, LauncherMediaController.Listener, HeaderStatusSink
147: public LauncherDashboardBuilder( Context context, VehicleMetricsRepository repository, LauncherTabBuilder.LauncherCallback launcherCallback)
158: public View build()
219: public void start()
234: public void stop()
247: public void release()
253: public void reapplyTheme()
332: public void onMetricsUpdated(VehicleMetricsSnapshot snapshot)
337: public void onMediaStateChanged(LauncherMediaController.MediaState state)
1573: public void setSoundBoost(boolean active, int gainMb)
1581: public void setWifiState(TopBarBuilder.WifiState state)
1588: public void setDriveMode(boolean connected, int mode)
1603: public void setServerRunning(boolean running)
1610: public void setProjectionActive(boolean active)
```

### app/src/main/java/com/mapcontrol/ui/builder/LauncherTabBuilder.java

```text
14: public class LauncherTabBuilder
16: public interface LauncherCallback
29: public LauncherTabBuilder( Context context, VehicleMetricsRepository vehicleMetricsRepository, LauncherCallback callback)
38: public View build()
54: public LauncherDashboardBuilder getDashboardBuilder()
58: public View getRootView()
62: public void refreshInstalledApps()
67: public void onTabVisible()
74: public void onTabHidden()
80: public void release()
87: public void onUiModeChanged()
```

### app/src/main/java/com/mapcontrol/ui/builder/LogTabBuilder.java

```text
28: public class LogTabBuilder
29: public interface LogCallback
46: public LogTabBuilder(Context context, LogCallback callback)
52: public LinearLayout build()
337: public LinearLayout getTabContent()
341: public TextView getLogsTextView()
345: public ScrollView getScrollView()
349: public EditText getTtsInput()
```

### app/src/main/java/com/mapcontrol/ui/builder/ProfileTabBuilder.java

```text
46: public class ProfileTabBuilder
47: public interface ProfileCallback
80: public ProfileTabBuilder(Context context, SharedPreferences prefs, ProfileApiService apiService, ProfileCallback callback)
87: public ScrollView build()
264: public void onSuccess(String message, JSONObject data)
276: public void onError(String error)
307: public void onSuccess(String message, JSONObject data)
318: public void onError(String error)
440: public void onSuccess(String message, JSONObject data)
450: public void onError(String error)
522: public void onSuccess(String message, JSONObject responseData)
587: public void onError(String error)
705: public void onSuccess(String message, JSONObject data)
715: public void onError(String error)
729: public void onLocationChanged(Location location)
735: public void onSuccess(String message, JSONObject data)
745: public void onError(String error)
761: public void onStatusChanged(String provider, int status, Bundle extras)
764: public void onProviderEnabled(String provider)
767: public void onProviderDisabled(String provider)
```

### app/src/main/java/com/mapcontrol/ui/builder/ProjectionTabBuilder.java

```text
47: public class ProjectionTabBuilder
48: public interface ProjectionCallback
73: public ProjectionTabBuilder(Context context, SharedPreferences prefs, ProjectionCallback callback)
81: public ScrollView build()
526: public void refreshProjectionStatusUi()
535: public void updateGoogleMapsNavSummary(GoogleMapsNavSnapshot snapshot)
782: public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser)
791: public void onStartTrackingTouch(SeekBar seekBar)
795: public void onStopTrackingTouch(SeekBar seekBar)
834: public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser)
843: public void onStartTrackingTouch(SeekBar seekBar)
847: public void onStopTrackingTouch(SeekBar seekBar)
910: public void openTargetAppPicker()
957: public ScrollView getScrollView()
961: public LinearLayout getProjectionTabContent()
965: public TextView getTargetAppLabel()
```

### app/src/main/java/com/mapcontrol/ui/builder/SettingsTabBuilder.java

```text
37: public class SettingsTabBuilder
38: public interface SettingsCallback
58: public SettingsTabBuilder(Context context, SharedPreferences prefs, SettingsCallback callback)
66: public ScrollView build()
228: public void onClick(View v)
595: public void onManualSelected( com.mapcontrol.vehicle.material.MaterialVehicleCatalog.Entry entry)
604: public void onAutoDetectionSelected()
642: public void syncLauncherModeFromPrefs()
650: public LinearLayout getSettingsTabContent()
654: public ScrollView getScrollView()
```

### app/src/main/java/com/mapcontrol/ui/builder/SideRailBuilder.java

```text
28: public class SideRailBuilder
29: public interface SideRailCallback
70: public SideRailBuilder(Context context, SharedPreferences prefs, SideRailCallback callback)
76: public LinearLayout build()
186: public LinearLayout buildPortrait()
313: public void setLauncherModeActive(boolean active)
320: public boolean isLauncherModeActive()
325: public void reapplyTheme()
345: public void setSelectionForTabIndex(int tabIndex)
```

### app/src/main/java/com/mapcontrol/ui/builder/TopBarBuilder.java

```text
23: public class TopBarBuilder implements HeaderStatusSink
24: public enum WifiState
26: public interface TopBarCallback
50: public TopBarBuilder(Context context, TopBarCallback callback)
55: public LinearLayout build()
125: public void setWifiState(WifiState state)
131: public void setDriveMode(boolean connected, int mode)
145: public void setServerRunning(boolean running)
151: public void setProjectionActive(boolean active)
158: public void setSoundBoost(boolean active, int gainMb)
165: public void setTitle(int titleRes)
229: public void setLauncherBackButtonVisible(boolean visible)
233: public void setTopBarVisible(boolean visible)
237: public void setLauncherBackButtonListener(View.OnClickListener listener)
240: public TextView getTitleView()
241: public void release()
243: public void reapplyTheme()
```

### app/src/main/java/com/mapcontrol/ui/builder/VehicleInfoTabBuilder.java

```text
30: public class VehicleInfoTabBuilder implements VehicleMetricsRepository.Listener
31: public interface VehicleInfoCallback
33: public enum Category
74: public VehicleInfoTabBuilder(Context context, VehicleMetricsRepository repository, VehicleInfoCallback callback)
83: public ScrollView getScrollView()
84: public void rebuild()
85: public void release()
87: public void startListening()
95: public void stopListening()
100: public void refreshSnapshot()
```

### app/src/main/java/com/mapcontrol/ui/builder/WelcomeSoundScreenBuilder.java

```text
26: public final class WelcomeSoundScreenBuilder
31: public static final class Screen
32: public final ScrollView scrollView;
33: public final TextView usbStatus;
34: public final LinearLayout usbList;
35: public final TextView tvFilePath;
36: public final LinearLayout rowSelect;
37: public final LinearLayout playbackRow;
38: public final TextView playbackLabel;
39: public final Button btnPreview;
56: public static LinearLayout actionRow(Context context, CharSequence label)
76: public static Screen buildTabScrollView(Context context, boolean autoPlayInitial, Consumer<Boolean> onAutoPlayCommitted, String selectedDesignId, Consumer<String> onDesignCommitted, String customLine, Consumer<String> onLineCommitted)
```

### app/src/main/java/com/mapcontrol/ui/builder/WelcomeSoundTabBuilder.java

```text
35: public class WelcomeSoundTabBuilder
43: public void onReceive(Context context, Intent intent)
61: public WelcomeSoundTabBuilder(AppCompatActivity activity, SharedPreferences prefs, ActivityResultLauncher<Intent> audioPicker)
71: public void rebuild()
101: public ScrollView getScrollView()
106: public void onTabVisible()
111: public void onAudioPicked(ActivityResult result)
128: public void onHostPause()
136: public void releaseAudioFully()
```

### app/src/main/java/com/mapcontrol/ui/builder/WifiTabBuilder.java

```text
28: public class WifiTabBuilder
29: public interface WifiCallback
43: public WifiTabBuilder(Context context, WifiCallback callback)
65: public LinearLayout build()
129: public LinearLayout getTabContent()
133: public void updateWifiStatus()
```

### app/src/main/java/com/mapcontrol/ui/HeaderStatusMonitor.java

```text
23: public final class HeaderStatusMonitor
40: public HeaderStatusMonitor(Context context, HeaderStatusSink initialSink, VehicleMetricsRepository metrics, BooleanSupplier serverRunning)
50: public void addSink(HeaderStatusSink sink)
71: public void removeSink(HeaderStatusSink sink)
77: public void start()
84: public void stop()
91: public void refresh()
```

### app/src/main/java/com/mapcontrol/ui/HeaderStatusSink.java

```text
8: public interface HeaderStatusSink
```

### app/src/main/java/com/mapcontrol/ui/theme/FeatureGridLayout.java

```text
9: public final class FeatureGridLayout extends GridLayout
13: public FeatureGridLayout(Context context)
15: public FeatureGridLayout(Context context, int twoColumnMinWidthDp)
23: public void addCard(View card)
```

### app/src/main/java/com/mapcontrol/ui/theme/UiStyles.java

```text
32: public final class UiStyles
42: public static void setTextSize(TextView view, int sizeResId)
47: public static int dimenPx(Context context, int dimenResId)
52: public static void setUiModeOverride(@Nullable Configuration config)
60: public static Context uiModeContext(Context context)
78: public static int color(Context context, @ColorRes int colorRes)
83: public static Drawable drawable(Context context, @DrawableRes int drawableRes)
88: public static void setBackgroundRes(View view, @DrawableRes int drawableRes)
92: public static void setRootBackground(View view)
96: public static void setRailPanelBackground(View view)
100: public static void setGlassCardBackground(View view)
104: public static void setTabContentBackdrop(View view)
108: public static void applySolidRoundedBackground(View view, int colorArgb)
115: public static void applySolidRoundedBackgroundDp(View view, int colorArgb, float cornerRadiusDp)
124: public static int textOnColor(int backgroundColorArgb)
129: public static void styleOemButton(Button button, int backgroundColorArgb)
144: public static void applyOptionalBackdropBlur(View view, float radiusPx)
150: public static void clearRenderEffect(View view)
160: public static final class BinarySegmentHandle
181: public void setLeftSelected(boolean left)
191: public void syncVisualWithoutCommit(boolean left)
199: public void setInteractionEnabled(boolean enabled)
210: public boolean isLeftSelected()
215: public void reapplyTheme()
228: public static BinarySegmentHandle addBinarySegmentedControl(Context context, android.view.ViewGroup parent, @Nullable String title, String leftLabel, String rightLabel, String leftHelp, String rightHelp, boolean initialLeftSelected, Consumer<Boolean> onCommit)
456: public static final class TernarySegmentHandle
468: public void syncVisualFromModeId(int modeId)
483: public int getSelectedModeId()
491: public static TernarySegmentHandle addTernarySegmentedControl(Context context, LinearLayout parent, @Nullable String title, String[] labels, String[] helps, int[] modeIds, int initialModeId, Consumer<Integer> onCommit)
642: public static void setButtonStartIconTinted(Button button, @DrawableRes int iconRes, int colorArgb, int drawablePaddingPx)
652: public static void setButtonIconOnlyTinted(Button button, @DrawableRes int iconRes, int colorArgb, CharSequence contentDescription)
658: public static void clearButtonCompoundDrawables(Button button)
```

### app/src/main/java/com/mapcontrol/ui/welcome/MountainHikeScene.java

```text
20: public class MountainHikeScene extends View
35: public MountainHikeScene(Context context)
39: public MountainHikeScene(Context context, @Nullable AttributeSet attrs)
```

### app/src/main/java/com/mapcontrol/ui/welcome/MountainHikeWelcomeDesign.java

```text
36: public final class MountainHikeWelcomeDesign implements WelcomeDesign
43: public String id()
48: public int titleRes()
53: public int helpRes()
58: public View createView(Context context)
116: public void run()
124: public void onViewAttachedToWindow(View v)
134: public void onViewDetachedFromWindow(View v)
```

### app/src/main/java/com/mapcontrol/ui/welcome/SignatureWelcomeDesign.java

```text
37: public final class SignatureWelcomeDesign implements WelcomeDesign
40: public String id()
45: public int titleRes()
50: public int helpRes()
55: public View createView(Context context)
166: public void run()
174: public void onViewAttachedToWindow(View v)
182: public void onViewDetachedFromWindow(View v)
```

### app/src/main/java/com/mapcontrol/ui/welcome/WelcomeAudioFiles.java

```text
29: public final class WelcomeAudioFiles
31: public static final int MAX_FILES = 100;
52: public static final class UsbScan
53: public final boolean present;
54: public final List<File> files;
62: public static UsbScan scanUsb(Context context)
85: public static List<File> scanDownloads()
99: public static File copyFile(Context context, File source) throws IOException
109: public static File copyUri(Context context, Uri uri) throws IOException
```

### app/src/main/java/com/mapcontrol/ui/welcome/WelcomeDesign.java

```text
13: public interface WelcomeDesign
```

### app/src/main/java/com/mapcontrol/ui/welcome/WelcomeDesignCatalog.java

```text
18: public final class WelcomeDesignCatalog
20: public static final String PREFS_NAME = "MapControlPrefs";
21: public static final String PREF_KEY = "welcomeDesignId";
22: public static final String PREF_LINE = "welcomeCustomText";
23: public static final String ID_OFF = "off";
24: public static final String ID_SIGNATURE = "signature";
25: public static final String ID_WINTER = "winter";
26: public static final String ID_HIKE = "hike";
30: public String id()
35: public int titleRes()
40: public int helpRes()
46: public View createView(Context context)
61: public static List<WelcomeDesign> designs()
65: public static String savedId(SharedPreferences prefs)
74: public static WelcomeDesign find(String id)
87: public static View createView(Context context, String id)
```

### app/src/main/java/com/mapcontrol/ui/welcome/WelcomeScreenPresenter.java

```text
22: public final class WelcomeScreenPresenter
40: public static void showSaved(Context context)
134: public static void hide()
```

### app/src/main/java/com/mapcontrol/ui/welcome/WinterTouchScene.java

```text
21: public class WinterTouchScene extends View
44: public WinterTouchScene(Context context)
48: public WinterTouchScene(Context context, @Nullable AttributeSet attrs)
```

### app/src/main/java/com/mapcontrol/ui/welcome/WinterTouchWelcomeDesign.java

```text
36: public final class WinterTouchWelcomeDesign implements WelcomeDesign
43: public String id()
48: public int titleRes()
53: public int helpRes()
58: public View createView(Context context)
124: public void run()
132: public void onViewAttachedToWindow(View v)
142: public void onViewDetachedFromWindow(View v)
```

### app/src/main/java/com/mapcontrol/ui/widget/LauncherClockSwipeLayout.java

```text
27: public class LauncherClockSwipeLayout extends FrameLayout
29: public interface Listener
55: public LauncherClockSwipeLayout(@NonNull Context context)
59: public LauncherClockSwipeLayout(@NonNull Context context, @Nullable AttributeSet attrs)
68: public boolean onDown(MotionEvent e)
73: public boolean onFling(MotionEvent e1, MotionEvent e2, float velocityX, float velocityY)
86: public void setListener(@Nullable Listener swipeListener)
91: public void addView(View child, int index, ViewGroup.LayoutParams params)
99: public boolean onInterceptTouchEvent(MotionEvent ev)
120: public boolean onTouchEvent(MotionEvent ev)
```

### app/src/main/java/com/mapcontrol/ui/widget/LauncherLitePanelView.java

```text
32: public final class LauncherLitePanelView extends LinearLayout implements OpenMeteoWeatherClient.Listener
56: public LauncherLitePanelView(@NonNull Context context)
247: public void start()
253: public void stop()
258: public void destroy()
263: public void bindVehicleMetrics(@NonNull VehicleMetricsSnapshot snapshot)
299: public void reapplyTheme()
351: public void onWeatherUpdated(@NonNull OpenMeteoWeatherSnapshot snapshot)
356: public void onWeatherFailed()
```

### app/src/main/java/com/mapcontrol/ui/widget/OemFrameAnimationView.java

```text
20: public final class OemFrameAnimationView extends View
37: public void run()
60: public OemFrameAnimationView(Context context)
64: public OemFrameAnimationView(Context context, @Nullable AttributeSet attrs)
68: public void setResources(MaterialVehicleResources resources)
72: public void play(String prefix, int startIndex, int endIndex, boolean reverse)
83: public void showFrame(String prefix, int index)
94: public void stop()
99: public void hide()
```

### app/src/main/java/com/mapcontrol/ui/widget/VehicleModelHostView.java

```text
30: public final class VehicleModelHostView extends FrameLayout
32: public enum ViewMode
52: public VehicleModelHostView(@NonNull Context context)
56: public VehicleModelHostView(@NonNull Context context, @Nullable AttributeSet attrs)
105: public void onHostStart()
112: public void reloadVehicleImage()
125: public void onManualSelected(MaterialVehicleCatalog.Entry entry)
130: public void onAutoDetectionSelected()
148: public void onHostStop()
154: public void showTrunkMode()
166: public void showSunroofMode()
174: public void showNormalMode()
210: public void onAnimationEnd(Animator animation)
```

## Mevcut test kaynakları (çalıştırma sonucu değildir)

Bu görevde listelenmiş testler yeniden çalıştırılmadı. Eski raporların silinmesiyle test kaynakları veya önizleme kanıtları silinmez.

- [app/src/test/java/com/mapcontrol/nav/GoogleMapsNavNotificationParserTest.java](../../app/src/test/java/com/mapcontrol/nav/GoogleMapsNavNotificationParserTest.java)
- [app/src/test/java/com/mapcontrol/nav/NavigationQuantitiesTest.java](../../app/src/test/java/com/mapcontrol/nav/NavigationQuantitiesTest.java)
- [app/src/test/java/com/mapcontrol/ui/HeaderStatusSinkTest.java](../../app/src/test/java/com/mapcontrol/ui/HeaderStatusSinkTest.java)
- [app/src/test/java/com/mapcontrol/util/BoundedLogBufferTest.java](../../app/src/test/java/com/mapcontrol/util/BoundedLogBufferTest.java)
- [app/src/test/java/com/mapcontrol/util/DisplayTaskParserTest.java](../../app/src/test/java/com/mapcontrol/util/DisplayTaskParserTest.java)
- [app/src/test/java/com/mapcontrol/util/StartupGateTest.java](../../app/src/test/java/com/mapcontrol/util/StartupGateTest.java)
- [app/src/test/java/com/mapcontrol/vehicle/LocationSamplePolicyTest.java](../../app/src/test/java/com/mapcontrol/vehicle/LocationSamplePolicyTest.java)
- [app/src/test/java/com/mapcontrol/vehicle/PowerModeSampleTest.java](../../app/src/test/java/com/mapcontrol/vehicle/PowerModeSampleTest.java)
- [app/src/test/java/com/mapcontrol/vehicle/SportAnimationPolicyTest.java](../../app/src/test/java/com/mapcontrol/vehicle/SportAnimationPolicyTest.java)
- [app/src/test/java/com/mapcontrol/vehicle/VehicleIdentityManagerTest.java](../../app/src/test/java/com/mapcontrol/vehicle/VehicleIdentityManagerTest.java)
- [app/src/androidTest/java/com/mapcontrol/audio/EqualizerDisabledTest.java](../../app/src/androidTest/java/com/mapcontrol/audio/EqualizerDisabledTest.java)
- [app/src/androidTest/java/com/mapcontrol/bean/CameraResourceTest.java](../../app/src/androidTest/java/com/mapcontrol/bean/CameraResourceTest.java)
- [app/src/androidTest/java/com/mapcontrol/manager/SportAnimationControllerTest.java](../../app/src/androidTest/java/com/mapcontrol/manager/SportAnimationControllerTest.java)
- [app/src/androidTest/java/com/mapcontrol/nav/YandexOverlayUpdateTest.java](../../app/src/androidTest/java/com/mapcontrol/nav/YandexOverlayUpdateTest.java)
- [app/src/androidTest/java/com/mapcontrol/ui/activity/SharedHeaderNavigationTest.java](../../app/src/androidTest/java/com/mapcontrol/ui/activity/SharedHeaderNavigationTest.java)
- [app/src/androidTest/java/com/mapcontrol/ui/builder/DriveModeOptionsTest.java](../../app/src/androidTest/java/com/mapcontrol/ui/builder/DriveModeOptionsTest.java)
- [app/src/androidTest/java/com/mapcontrol/ui/builder/SharedHeaderTest.java](../../app/src/androidTest/java/com/mapcontrol/ui/builder/SharedHeaderTest.java)
- [app/src/androidTest/java/com/mapcontrol/ui/theme/CompactControlsTest.java](../../app/src/androidTest/java/com/mapcontrol/ui/theme/CompactControlsTest.java)
- [app/src/androidTest/java/com/mapcontrol/ui/widget/DriveModeBadgeTest.java](../../app/src/androidTest/java/com/mapcontrol/ui/widget/DriveModeBadgeTest.java)
- [app/src/androidTest/java/com/mapcontrol/util/AccessibilityEntryPointTest.java](../../app/src/androidTest/java/com/mapcontrol/util/AccessibilityEntryPointTest.java)
- [app/src/androidTest/java/com/mapcontrol/util/OverlayDismissalTest.java](../../app/src/androidTest/java/com/mapcontrol/util/OverlayDismissalTest.java)
- [app/src/androidTest/java/com/mapcontrol/util/PassiveDiagnosticsTest.java](../../app/src/androidTest/java/com/mapcontrol/util/PassiveDiagnosticsTest.java)
- [app/src/androidTest/java/com/mapcontrol/util/RecordingWindowManager.java](../../app/src/androidTest/java/com/mapcontrol/util/RecordingWindowManager.java)
- [app/src/androidTest/java/com/mapcontrol/vehicle/VehicleMetricsLiveHeaderTest.java](../../app/src/androidTest/java/com/mapcontrol/vehicle/VehicleMetricsLiveHeaderTest.java)
- [app/src/androidTest/java/com/mapcontrol/weather/WeatherLifecycleTest.java](../../app/src/androidTest/java/com/mapcontrol/weather/WeatherLifecycleTest.java)
