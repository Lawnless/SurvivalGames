package dev.lawnless.survivalgames.arena;

import dev.lawnless.survivalgames.Main;
import org.bukkit.Location;
import org.bukkit.entity.Player;

import java.util.ArrayList;
import java.util.HashMap;

import static dev.lawnless.survivalgames.arena.ArenaManager.arenasArray;

public class Arena {

    private String arenaName;
    public enum ArenaState {
        WAITING, STARTING, STARTED, DEATHMATCH, RESTARTING
    }
    private ArenaState arenaState;
    private int minPlayers;
    private int maxPlayers;
    private Location waitingLoc, endLoc;
    private HashMap<Integer, Location> spawnPoints = new HashMap<Integer, Location>();
    private ArrayList<Player> players = new ArrayList<Player>();

    public Arena(String arenaName, int minPlayers, int maxPlayers, Location waitingLoc, Location endLoc, HashMap<Integer, Location> spawnPoints) {
        this.arenaName = arenaName;
        this.minPlayers = minPlayers;
        this.maxPlayers = maxPlayers;
        this.waitingLoc = waitingLoc;
        this.endLoc = endLoc;
        this.spawnPoints = spawnPoints;
        this.arenaState = ArenaState.WAITING;

        arenasArray.add(this);

        saveArenaConfig();
    }

    public void saveArenaConfig() {
        Main.plugin.getConfig().set("arenas." + arenaName + ".name", arenaName);
        Main.plugin.getConfig().set("arenas." + arenaName + ".minPlayers", minPlayers);
        Main.plugin.getConfig().set("arenas." + arenaName + ".maxPlayers", maxPlayers);

        Main.plugin.getConfig().set("arenas." + arenaName + ".waitingLoc.x", waitingLoc.getX());
        Main.plugin.getConfig().set("arenas." + arenaName + ".waitingLoc.y", waitingLoc.getY());
        Main.plugin.getConfig().set("arenas." + arenaName + ".waitingLoc.z", waitingLoc.getZ());
        Main.plugin.getConfig().set("arenas." + arenaName + ".waitingLoc.pitch", waitingLoc.getPitch());
        Main.plugin.getConfig().set("arenas." + arenaName + ".waitingLoc.yaw", waitingLoc.getYaw());
        Main.plugin.getConfig().set("arenas." + arenaName + ".waitingLoc.world", waitingLoc.getWorld().getName());

        Main.plugin.getConfig().set("arenas." + arenaName + ".endLoc.x", endLoc.getX());
        Main.plugin.getConfig().set("arenas." + arenaName + ".endLoc.y", endLoc.getY());
        Main.plugin.getConfig().set("arenas." + arenaName + ".endLoc.z", endLoc.getZ());
        Main.plugin.getConfig().set("arenas." + arenaName + ".endLoc.pitch", endLoc.getPitch());
        Main.plugin.getConfig().set("arenas." + arenaName + ".endLoc.yaw", endLoc.getYaw());
        Main.plugin.getConfig().set("arenas." + arenaName + ".endLoc.world", endLoc.getWorld().getName());

        spawnPoints.forEach((Integer spid, Location sploc) -> {
            Main.plugin.getConfig().set("arenas." + arenaName + ".spawnPoints." + String.valueOf(spid) + ".x", sploc.getX());
            Main.plugin.getConfig().set("arenas." + arenaName + ".spawnPoints." + String.valueOf(spid) + ".y", sploc.getY());
            Main.plugin.getConfig().set("arenas." + arenaName + ".spawnPoints." + String.valueOf(spid) + ".z", sploc.getZ());
            Main.plugin.getConfig().set("arenas." + arenaName + ".spawnPoints." + String.valueOf(spid) + ".world", sploc.getWorld().getName());
        });

        Main.plugin.saveConfig();
    }

    public void delete() {
        this.arenaName = null;
        this.minPlayers = 0;
        this.maxPlayers = 0;
        this.waitingLoc = null;
        this.endLoc = null;
        this.spawnPoints = null;
        arenasArray.remove(this);
    }

    public String setName(String newName) {
        this.arenaName = newName;
        saveArenaConfig();
        return arenaName;
    }

    public ArenaState setState(ArenaState state) {
        this.arenaState = state;
        return arenaState;
    }

    public int setMinPlayers(int newPlayers) {
        this.minPlayers = newPlayers;
        saveArenaConfig();
        return minPlayers;
    }

    public int setMaxPlayers(int newPlayers) {
        this.maxPlayers = newPlayers;
        saveArenaConfig();
        return maxPlayers;
    }

    public Location setWaitingLoc(Location newLoc) {
        this.waitingLoc = newLoc;
        saveArenaConfig();
        return waitingLoc;
    }

    public Location setEndLoc(Location newLoc) {
        this.endLoc = newLoc;
        saveArenaConfig();
        return endLoc;
    }

    public HashMap<Integer, Location> setSpawnpoints(HashMap<Integer, Location> spawnLocs) {
        spawnPoints = spawnLocs;
        saveArenaConfig();
        return spawnPoints;
    }

    public String getName() {
        return arenaName;
    }

    public int getMinPlayers() {
        return minPlayers;
    }

    public int getMaxPlayers() {
        return maxPlayers;
    }

    public Location getWaitingLoc() {
        return waitingLoc;
    }

    public Location getEndLoc() {
        return endLoc;
    }

    public Location getSpawn(int id) {
        return spawnPoints.get(id).getBlock().getLocation();
    }

    public ArrayList<Player> getPlayers() {
        return players;
    }

    public void addPlayer(Player p) {
        players.add(p);
    }

    public void removePlayer(Player p) {
        players.remove(p);
    }

}
