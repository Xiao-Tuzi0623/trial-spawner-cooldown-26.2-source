package tw.xiaotuzi.trialcooldown;

import net.fabricmc.api.ModInitializer;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.Properties;

public final class TrialSpawnerCooldownMod implements ModInitializer {
    public static final String MOD_ID = "trial-spawner-cooldown";

    // Default: 1800 seconds = 30 minutes (36000 ticks).
    public static int cooldownTicks = 36000;

    @Override
    public void onInitialize() {
        loadConfig();
    }

    private static void loadConfig() {
        Path configDir = Path.of("config");
        Path configFile = configDir.resolve("trial-spawner-cooldown.properties");

        try {
            Files.createDirectories(configDir);

            Properties properties = new Properties();

            if (Files.exists(configFile)) {
                try (InputStream in = Files.newInputStream(configFile)) {
                    properties.load(in);
                }
            }

            String secondsText = properties.getProperty("cooldown_seconds", "1800").trim();

            double seconds;
            try {
                seconds = Double.parseDouble(secondsText);
            } catch (NumberFormatException e) {
                seconds = 1800.0;
            }

            if (!Double.isFinite(seconds) || seconds < 0.0) {
                seconds = 1800.0;
            }

            long ticks = Math.round(seconds * 20.0);
            cooldownTicks = (int) Math.min(Integer.MAX_VALUE, ticks);

            properties.setProperty("cooldown_seconds", formatSeconds(cooldownTicks / 20.0));
            properties.store(
                    Files.newOutputStream(configFile),
                    "Trial Spawner Cooldown - Minecraft 26.2 Fabric"
            );

            System.out.println("[Trial Spawner Cooldown] cooldown_seconds="
                    + formatSeconds(cooldownTicks / 20.0)
                    + " (" + cooldownTicks + " ticks)");
        } catch (IOException e) {
            System.err.println("[Trial Spawner Cooldown] Failed to read config: " + e);
            cooldownTicks = 36000;
        }
    }

    private static String formatSeconds(double seconds) {
        if (seconds == Math.rint(seconds)) {
            return Long.toString((long) seconds);
        }
        return Double.toString(seconds);
    }
}
