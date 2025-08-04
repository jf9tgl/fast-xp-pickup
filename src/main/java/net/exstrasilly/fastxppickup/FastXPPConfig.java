package net.exstrasilly.fastxppickup;

import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.plugin.java.JavaPlugin;

public class FastXPPConfig {
    private final JavaPlugin plugin;

    // Configuration values
    private int basePickupDelay;
    private boolean valueBasedDelayEnabled;
    private double delayPerXpPoint;
    private int maxDelay;
    private int minDelay;

    public FastXPPConfig(JavaPlugin plugin) {
        this.plugin = plugin;
        loadConfig();
    }

    public void loadConfig() {
        plugin.saveDefaultConfig();
        plugin.reloadConfig();
        FileConfiguration config = plugin.getConfig();

        // Load configuration values
        basePickupDelay = config.getInt("base-pickup-delay", 0);
        valueBasedDelayEnabled = config.getBoolean("value-based-delay.enabled", false);
        delayPerXpPoint = config.getDouble("value-based-delay.delay-per-xp-point", 0.1);
        maxDelay = config.getInt("value-based-delay.max-delay", 20);
        minDelay = config.getInt("value-based-delay.min-delay", 0);
    }

    public int calculatePickupDelay(int xpValue) {
        if (!valueBasedDelayEnabled) {
            return basePickupDelay;
        }

        // Calculate delay based on XP value
        int calculatedDelay = (int) (xpValue * delayPerXpPoint);

        // Apply min/max constraints
        calculatedDelay = Math.max(calculatedDelay, minDelay);
        calculatedDelay = Math.min(calculatedDelay, maxDelay);

        return calculatedDelay;
    }

    // Getters
    public int getBasePickupDelay() {
        return basePickupDelay;
    }

    public boolean isValueBasedDelayEnabled() {
        return valueBasedDelayEnabled;
    }

    public double getDelayPerXpPoint() {
        return delayPerXpPoint;
    }

    public int getMaxDelay() {
        return maxDelay;
    }

    public int getMinDelay() {
        return minDelay;
    }
}
