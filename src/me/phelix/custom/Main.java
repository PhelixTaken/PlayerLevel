package me.phelix.custom;

import me.phelix.custom.Command.AddStats;
import me.phelix.custom.Command.RemoveStats;
import me.phelix.custom.Command.SetStats;
import me.phelix.custom.Command.Stats;
import me.phelix.custom.Event.Join;
import org.bukkit.Bukkit;
import org.bukkit.plugin.java.JavaPlugin;

public class Main extends JavaPlugin {

    public void onEnable(){
        getConfig().options().copyDefaults(true);
        saveConfig();

        registerCmds();
        registerEvents();
    }

    private void registerCmds(){
        getCommand("stats").setExecutor(new Stats(this));
        getCommand("add").setExecutor(new AddStats(this));
        getCommand("set").setExecutor(new SetStats(this));
        getCommand("remove").setExecutor(new RemoveStats(this));
    }

    private void registerEvents(){
        Bukkit.getPluginManager().registerEvents(new Join(this), this);
    }


}
