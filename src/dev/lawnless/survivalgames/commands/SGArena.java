package dev.lawnless.survivalgames.commands;

import dev.lawnless.survivalgames.arena.Arena;
import dev.lawnless.survivalgames.arena.ArenaManager;
import org.bukkit.Location;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import java.util.HashMap;

public class SGArena implements CommandExecutor {

    HashMap<Integer, Location> tempSpawnPoints = new HashMap<Integer, Location>();

    @Override
    public boolean onCommand(CommandSender sender, Command command, String commandLabel, String[] args) {
        if (command.getName().equalsIgnoreCase("sgarena")) {
            if (sender instanceof Player) {
                Player player = (Player) sender;
                if (player.hasPermission("survivalgames.admin")) {
                    if (args.length == 0) {
                        player.sendMessage("§6/sgarena create <arena name>");
                        player.sendMessage("§6/sgarena delete <arena name>");
                        player.sendMessage("§6/sgarena setwaitinglobby <arena name>");
                        player.sendMessage("§6/sgarena setmainlobby <arena name>");
                        player.sendMessage("§6/sgarena setminplayers <arena name> <min players>");
                        player.sendMessage("§6/sgarena setmaxplayers <arena name> <max players>");
                        player.sendMessage("§6/sgarena setspawnpoint <arena name> <spawnpoint>");
                    } else if (args.length == 2) {
                        switch (args[0].toLowerCase()) {
                            case "create": {
                                if (ArenaManager.getArena(args[1]) == null) {
                                    for (int i = 0; i < 24; i++) {
                                        tempSpawnPoints.put(i, new Location(player.getWorld(), 0.0, 0.0, 0.0));
                                    }
                                    Arena arena = new Arena(args[1], 6, 24, player.getLocation(), player.getLocation(), tempSpawnPoints);
                                    player.sendMessage("§aArena " + arena.getName() + " successfully created.");
                                } else {
                                    player.sendMessage("§cArena " + args[1] + " already exists!");
                                }
                                break;
                            }
                            case "delete": {
                                if (ArenaManager.getArena(args[1]) != null) {
                                    ArenaManager.getArena(args[1]).delete();
                                    player.sendMessage("§aArena " + args[1] + " successfully deleted.");
                                } else {
                                    player.sendMessage("§cArena " + args[1] + " doesn't exists!");
                                }
                                break;
                            }
                            case "setwaitinglobby": {
                                if (ArenaManager.getArena(args[1]) != null) {
                                    ArenaManager.getArena(args[1]).setWaitingLoc(player.getLocation());
                                    player.sendMessage("§aArena " + args[1] + " successfully set Waiting Lobby.");
                                } else {
                                    player.sendMessage("§cArena " + args[1] + " doesn't exists!");
                                }
                                break;
                            }
                            case "setmainlobby": {
                                if (ArenaManager.getArena(args[1]) != null) {
                                    ArenaManager.getArena(args[1]).setEndLoc(player.getLocation());
                                    player.sendMessage("§aArena " + args[1] + " successfully set Main Lobby.");
                                } else {
                                    player.sendMessage("§cArena " + args[1] + " doesn't exists!");
                                }
                                break;
                            }
                        }
                    } else if (args.length == 3) {
                        switch (args[0].toLowerCase()) {
                            case "setminplayers": {
                                ArenaManager.getArena(args[1]).setMinPlayers(Integer.valueOf(args[2]));
                                player.sendMessage("§aArena " + args[1] + " was set, new minimum players amount of " + args[2] + "!");
                                break;
                            }
                            case "setmaxplayers": {
                                ArenaManager.getArena(args[1]).setMaxPlayers(Integer.valueOf(args[2]));
                                player.sendMessage("§aArena " + args[1] + " was set, new maximum players amount of " + args[2] + "!");
                                break;
                            }
                            case "setspawnpoint": {
                                if (tempSpawnPoints.containsKey(args[2])) {
                                    tempSpawnPoints.remove(args[2]);
                                }
                                tempSpawnPoints.put(Integer.valueOf(args[2]), new Location(player.getWorld(), player.getLocation().getX(), player.getLocation().getY(), player.getLocation().getZ()));
                                ArenaManager.getArena(args[1]).setSpawnpoints(tempSpawnPoints);
                                player.sendMessage("§aArena " + args[1] + " spawnpoint " + args[2] + " was set, refreshed!");
                                break;
                            }
                        }
                    }
                } else {
                    player.sendMessage("§cI'm sorry but you do not have permission!");
                }
            } else {
                sender.sendMessage("§cYou need to be player!");
            }
        }
        return false;
    }

}
