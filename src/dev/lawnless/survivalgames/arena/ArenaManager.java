package dev.lawnless.survivalgames.arena;

import dev.lawnless.survivalgames.Main;
import org.bukkit.Bukkit;
import org.bukkit.Location;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Set;

public class ArenaManager {

    public static ArrayList<Arena> arenasArray = new ArrayList<Arena>();

    public static Arena getArena(String name) {
        for (Arena arena : arenasArray) {
            if (arena.getName().equals(name)) {
                return arena;
            }
        }
        return null;
    }

    public static int loadArenas() {
        if (Main.plugin.getConfig().getConfigurationSection("arenas") != null) {
            Set<String> arenasSection = Main.plugin.getConfig().getConfigurationSection("arenas").getKeys(false);
            if (arenasSection == null) return 0;
            int count = 0;
            for (String path : arenasSection) {
                String arenaName = Main.plugin.getConfig().getString(path+".name");

                int minPlayers = Main.plugin.getConfig().getInt(path+".minPlayers");
                int maxPlayers = Main.plugin.getConfig().getInt(path+".maxPlayers");

                Location waitingLoc = new Location(Bukkit.getWorld(Main.plugin.getConfig().getString(path+".waitingLoc.world")), Main.plugin.getConfig().getDouble(path+".waitingLoc.x"), Main.plugin.getConfig().getDouble(path+".waitingLoc.y"), Main.plugin.getConfig().getDouble(path+".waitingLoc.z"));
                Location endLoc = new Location(Bukkit.getWorld(Main.plugin.getConfig().getString(path+".endLoc.world")), Main.plugin.getConfig().getDouble(path+".endLoc.x"), Main.plugin.getConfig().getDouble(path+".endLoc.y"), Main.plugin.getConfig().getDouble(path+".endLoc.z"));

                Set<String> spawnpointsSection = Main.plugin.getConfig().getConfigurationSection(path+".spawnPoints").getKeys(false);
                HashMap<Integer, Location> spawns = new HashMap<Integer, Location>();
                for (String spawnPath : spawnpointsSection) {
                    spawns.put(Integer.valueOf(Main.plugin.getConfig().getString(spawnPath)), new Location(Bukkit.getWorld(Main.plugin.getConfig().getString(spawnPath+".world")), Main.plugin.getConfig().getDouble(spawnPath+".x"), Main.plugin.getConfig().getDouble(spawnPath+".y"), Main.plugin.getConfig().getDouble(spawnPath+".z")));
                }

                new Arena(arenaName, minPlayers, maxPlayers, waitingLoc, endLoc, spawns);
                count++;
            }
            Main.plugin.getLogger().info(count+" arenas loaded!");
        } else {
            Main.plugin.getLogger().info("0 arenas loaded!");
        }
        return 1;
    }

}
