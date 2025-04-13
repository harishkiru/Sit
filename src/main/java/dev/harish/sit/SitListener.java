package dev.harish.sit;

import org.bukkit.ChatColor;
import org.bukkit.entity.ArmorStand;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityDismountEvent;
import org.bukkit.event.player.PlayerQuitEvent;

import java.util.UUID;

public class SitListener implements Listener {

    private final SitManager manager;

    public SitListener(SitManager manager) {
        this.manager = manager;
    }

    @EventHandler
    public void onQuit(PlayerQuitEvent event) {
        manager.removeSittingPlayer(event.getPlayer());
    }

    @EventHandler
    public void onPlayerDismount(EntityDismountEvent event) {
        if (!(event.getEntity() instanceof Player player)) return;
        if (!(event.getDismounted() instanceof ArmorStand stand)) return;

        ArmorStand seatedStand = manager.getSittingStand(player);

        if (seatedStand != null && seatedStand.equals(stand)) {
            manager.removeSittingPlayer(player);
            player.teleport(player.getLocation().add(0, 0.5, 0));
            player.sendMessage(ChatColor.RED + " " + ChatColor.BOLD + "Sit >> You stood up.");
        }
    }

}
