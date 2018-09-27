package me.phelix.custom.Event;

import me.phelix.custom.Utils.CustomPlayer;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.plugin.Plugin;

public class Join implements Listener {

    private Plugin plugin;
    public Join(Plugin main) { this.plugin = main; }

    @EventHandler
    void onJoin(PlayerJoinEvent event) {

        CustomPlayer player = new CustomPlayer(plugin, event.getPlayer());
        if(!plugin.getConfig().contains("Players." + player.getUniqueId().toString() + player.getName())){
            player.setLevel(0);
            player.setXP(0);
        }
    }
}
