package net.exstrasilly.fastxppickup;

import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.command.TabCompleter;

import java.util.ArrayList;
import java.util.List;

public class FastXPPCommand implements CommandExecutor, TabCompleter {

    private final FastXPPConfig config;

    public FastXPPCommand(FastXPPConfig config) {
        this.config = config;
    }

    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        if (!command.getName().equalsIgnoreCase("fastxpp")) {
            return false;
        }

        // Ensure at least one argument is provided
        if (args.length == 0) {
            sender.sendMessage("§eUsage: /fastxp <reload|config>");
            return true;
        }
        // Handle reload subcommand
        if (args[0].equalsIgnoreCase("reload")) {
            handleReload(sender);
            return true;
        }

        // Handle config subcommand
        if (args[0].equalsIgnoreCase("config")) {
            handleConfig(sender);
            return true;
        }
        return true;
    }

    private void handleReload(CommandSender sender) {
        if (!sender.hasPermission("fastxppickup.admin")) {
            sender.sendMessage("§cYou don't have permission to reload the configuration.");
            return;
        }

        config.loadConfig();
        sender.sendMessage("§aFastXPPickup configuration reloaded!");

    }

    private void handleConfig(CommandSender sender) {
        if (!sender.hasPermission("fastxppickup.admin")) {
            sender.sendMessage("§cYou don't have permission to view the configuration.");
            return;
        }

        showConfiguration(sender);
    }

    private void showConfiguration(CommandSender sender) {
        sender.sendMessage("§6FastXPPickup Configuration:");
        sender.sendMessage("§7Base delay: §f" + config.getBasePickupDelay() + " ticks");
        sender.sendMessage("§7Value-based delay: §f" + (config.isValueBasedDelayEnabled() ? "Enabled" : "Disabled"));

        if (config.isValueBasedDelayEnabled()) {
            sender.sendMessage("§7Delay per XP point: §f" + config.getDelayPerXpPoint() + " ticks");
            sender.sendMessage("§7Min delay: §f" + config.getMinDelay() + " ticks");
            sender.sendMessage("§7Max delay: §f" + config.getMaxDelay() + " ticks");
        }

        sender.sendMessage("§7Use §f/fastxpp reload §7to reload configuration.");
    }

    @Override
    public List<String> onTabComplete(CommandSender sender, Command command, String alias, String[] args) {
        List<String> completions = new ArrayList<>();

        if (args.length == 1) {
            if (sender.hasPermission("fastxppickup.use")) {
                completions.add("reload");
                completions.add("config");
            }
        }

        return completions;
    }
}
