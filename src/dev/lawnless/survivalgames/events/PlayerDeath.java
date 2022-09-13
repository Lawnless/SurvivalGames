package dev.lawnless.survivalgames.events;

import dev.lawnless.survivalgames.arena.Arena;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.PlayerDeathEvent;

import static dev.lawnless.survivalgames.arena.ArenaManager.arenasArray;

public class PlayerDeath implements Listener {

    @EventHandler
    public void onDeath(PlayerDeathEvent event) {
        Player attacker = event.getEntity().getKiller();
        Player victim = event.getEntity();
        for (Arena arena : arenasArray) {
            if (arena.getPlayers().contains(attacker) && arena.getPlayers().contains(victim)) {
                event.setDeathMessage("");
                arena.getPlayers().forEach(player -> {
                    player.sendMessage("§e[Arena "+arena.getName()+"] §c"+victim.getName()+" was killed by "+attacker.getName()+"!");
                });
            }
        }
    }

}
