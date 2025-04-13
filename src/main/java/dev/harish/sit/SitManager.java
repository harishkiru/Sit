package dev.harish.sit;

import org.bukkit.Location;
import org.bukkit.entity.ArmorStand;
import org.bukkit.entity.Player;
import org.bukkit.util.Vector;

import java.util.HashMap;
import java.util.UUID;

public class SitManager {

    private final HashMap<UUID, ArmorStand> sittingPlayer = new HashMap<>();

    public void setSittingPlayer(Player player) {

        if(sittingPlayer.containsKey(player.getUniqueId())) {
            return;
        }

        Location loc = player.getLocation().clone();
        loc.setY(loc.getY() - 1.8);

        ArmorStand chair = loc.getWorld().spawn(loc, ArmorStand.class, stand -> {
                    stand.setVisible(false);
                    stand.setMarker(false);
                    stand.setGravity(false);
                    stand.setInvulnerable(true);
                    stand.setCollidable(false);
                    stand.setCustomNameVisible(false);
                });

        chair.addPassenger(player);
        sittingPlayer.put(player.getUniqueId(), chair);

    }

    public void removeSittingPlayer(Player player) {
        ArmorStand chair = sittingPlayer.remove(player.getUniqueId());
        if (chair != null) {
            chair.remove();
            player.setVelocity(new Vector(0, 0.2, 0));
        }
    }

    public ArmorStand getSittingStand(Player player) {
        return sittingPlayer.get(player.getUniqueId());
    }


    public boolean isSitting(Player player) {
        return sittingPlayer.containsKey(player.getUniqueId());
    }

    public void cleanupAll() {
        for (ArmorStand stand : sittingPlayer.values()) {
            stand.remove();
        }
        sittingPlayer.clear();
    }

}
