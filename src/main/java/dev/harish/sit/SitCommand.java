package dev.harish.sit;

import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class SitCommand implements CommandExecutor {

    private final SitManager manager;

    public SitCommand(SitManager manager) {
        this.manager = manager;
    }

    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        if (!(sender instanceof Player player)) {
            sender.sendMessage(ChatColor.RED + "" + ChatColor.BOLD + "Sit >> Only players can access this command!");
            return true;
        }

        if (manager.isSitting(player)) {
            manager.removeSittingPlayer(player);
            player.sendMessage(ChatColor.RED + " " + ChatColor.BOLD + "Sit >> You stood up.");
        } else {
            manager.setSittingPlayer(player);
            player.sendMessage(ChatColor.RED + " " + ChatColor.BOLD + "Sit >> You sat down.");
        }

        return true;
    }
}
