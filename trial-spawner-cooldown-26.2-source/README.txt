Trial Spawner Cooldown - Minecraft 26.2 Fabric

功能
----
修改所有 Trial Spawner 的 cooldown 時間。

設定檔
------
第一次啟動後會產生：

config/trial-spawner-cooldown.properties

預設：
cooldown_seconds=1800

也就是 30 分鐘。

範例
----
5 分鐘：
cooldown_seconds=300

1 分鐘：
cooldown_seconds=60

10 秒：
cooldown_seconds=10

5 秒：
cooldown_seconds=5

0 秒（無冷卻）：
cooldown_seconds=0

可以使用小數，例如：
cooldown_seconds=2.5

換算
----
1 秒 = 20 ticks
原版 30 分鐘 = 36000 ticks

使用方式
--------
1. 用 Java 25 執行 Minecraft 26.2 Fabric Server。
2. 將 build/libs/trial-spawner-cooldown-1.0.0.jar 放進 server/mods。
3. 啟動一次伺服器，讓 config/trial-spawner-cooldown.properties 生成。
4. 修改 cooldown_seconds。
5. 完全關閉並重新啟動伺服器。

注意
----
這個 Mod 修改的是 Trial Spawner 本身的 target cooldown length，
不是單純顯示倒數，也不是使用道具跳過 cooldown。

如果你改設定時已經有正在 cooldown 的 Trial Spawner，
建議重新啟動伺服器後再測試新的 cooldown。
