package dev.harish.sit;

import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;

public class SitReloadCommand implements CommandExecutor {

    private final Sit plugin;

    public SitReloadCommand(Sit plugin) {
        this.plugin = plugin;
    }

    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        plugin.getSitManager().cleanupAll();

        sender.sendMessage(ChatColor.RED + " " + ChatColor.BOLD + "Sit >> Plugin reloaded safely.");

        return true;
    }
}
