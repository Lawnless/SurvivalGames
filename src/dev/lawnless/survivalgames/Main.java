package dev.lawnless.survivalgames;

import dev.lawnless.survivalgames.arena.ArenaManager;
import dev.lawnless.survivalgames.commands.SGArena;
import dev.lawnless.survivalgames.events.PlayerDeath;
import org.bukkit.event.Listener;
import org.bukkit.plugin.java.JavaPlugin;

import java.io.File;
import java.io.IOException;

public class Main extends JavaPlugin implements Listener {

    public static Main plugin;

    @Override
    public void onEnable() {
        plugin = this;
        getLogger().info("Loading configuration...");
        getConfig().options().copyDefaults(true);
        saveConfig();
        getLogger().info("Registering events...");
        getServer().getPluginManager().registerEvents(new PlayerDeath(), this);
        getLogger().info("Loading commands...");
        this.getCommand("sgarena").setExecutor(new SGArena());
        getLogger().info("Loading arenas...");
        ArenaManager.loadArenas();
        getLogger().info("Activated successfully with license code (DEMO) H78MZ-SWY4B-A7XJ6-QLSLZ.");
    }

    @Override
    public void onDisable() {
        getLogger().info("Saving all arenas...");
        getLogger().info("Disabled all systems!");
    }

}
