package net.exstrasilly.fastxppickup;

import org.bukkit.Bukkit;
import org.bukkit.entity.ExperienceOrb;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntitySpawnEvent;
import org.bukkit.event.player.PlayerExpCooldownChangeEvent;
import org.bukkit.permissions.Permission;
import org.bukkit.permissions.PermissionDefault;
import org.bukkit.plugin.java.JavaPlugin;

public class FastXPPickupPlugin extends JavaPlugin implements Listener {

    public final Permission FASTXP_PERMISSION = new Permission(
        "fastxppickup.pickup",
        "This permission allows the holder to pick up XP orbs ignoring the delay. By default all players have it.",
        PermissionDefault.TRUE
    );

    @Override
    public void onEnable() {
        Bukkit.getPluginManager().registerEvents(this, this);
    }

    @EventHandler
    public void onExpCooldown(PlayerExpCooldownChangeEvent event) {
        if (event.getPlayer().hasPermission(FASTXP_PERMISSION))
            event.setNewCooldown(0);
    }
}
