package net.exstrasilly.fastxppickup;

import org.bukkit.Bukkit;
import org.bukkit.entity.ExperienceOrb;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerExpCooldownChangeEvent;
import org.bukkit.plugin.java.JavaPlugin;

public class FastXPPickupPlugin extends JavaPlugin implements Listener {

    private FastXPPConfig pluginConfig;
    private FastXPPCommand commandHandler;

    @Override
    public void onEnable() {
        // Initialize configuration
        pluginConfig = new FastXPPConfig(this);

        // Initialize command handler
        commandHandler = new FastXPPCommand(pluginConfig);

        // Register events and commands
        Bukkit.getPluginManager().registerEvents(this, this);

        if (getServer().getPluginManager().getPermission("fastxppickup.pickup") == null) {
            getServer().getPluginManager().addPermission(new org.bukkit.permissions.Permission("fastxppickup.pickup", "Allows players to pick up XP orbs with configurable delay"));
        }
        if (getServer().getPluginManager().getPermission("fastxppickup.admin") == null) {
            getServer().getPluginManager().addPermission(new org.bukkit.permissions.Permission("fastxppickup.admin", "Allows players to use the /fastxp command"));
        }
        if (getServer().getPluginManager().getPermission("fastxppickup.use") == null) {
            getServer().getPluginManager().addPermission(new org.bukkit.permissions.Permission("fastxppickup.use", "Allows the player to use FastXPPickup command"));
        }

        // Register command executor and tab completer
        getCommand("fastxpp").setExecutor(commandHandler);
        getCommand("fastxpp").setTabCompleter(commandHandler);

        getLogger().info("FastXPPickup plugin enabled with configurable delays!");
    }

    @EventHandler
    public void onExpCooldown(PlayerExpCooldownChangeEvent event) {
        Player player = event.getPlayer();
        if (!player.hasPermission("fastxppickup.pickup")) {
            return;
        }

        // Get nearby XP orbs to calculate delay based on their values
        ExperienceOrb nearestOrb = player.getWorld().getNearbyEntities(
                        player.getLocation(), 1.5, 1.5, 1.5
                ).stream()
                .filter(entity -> entity instanceof ExperienceOrb)
                .map(entity -> (ExperienceOrb) entity)
                .findFirst()
                .orElse(null);

        int delay;
        if (nearestOrb != null) {
            // Calculate delay based on the XP orb value
            delay = pluginConfig.calculatePickupDelay(nearestOrb.getExperience());
        } else {
            // Use base delay if no orb is found
            delay = pluginConfig.getBasePickupDelay();
        }

        event.setNewCooldown(delay);
    }
}
