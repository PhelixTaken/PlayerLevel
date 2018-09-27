package me.phelix.custom.Command;

import me.phelix.custom.Utils.CustomPlayer;
import me.phelix.custom.Utils.Messages;
import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.plugin.Plugin;

public class Stats implements CommandExecutor {

    private Plugin plugin;
    public Stats(Plugin main) { this.plugin = main; }

    public boolean onCommand(CommandSender sender, Command cmd, String Label, String[] args) {

        if(!(sender instanceof Player)){
            sender.sendMessage(Messages.noPlayer);
            return true;
        }

        CustomPlayer player = new CustomPlayer(plugin, (Player) sender);

        if(!sender.hasPermission("custom.stats")){
            player.sendMessage(Messages.noPerm);
            return true;
        }

        if(cmd.getName().equalsIgnoreCase("stats")){

            if(args.length == 0){
                Messages.playerStats(player);
                return true;
            }

            if(!Bukkit.getOfflinePlayer(args[0]).isOnline()){
                player.sendMessage(args[0] + Messages.noOnlinePlayer);
                return true;
            }

            CustomPlayer target = new CustomPlayer(plugin, (Player) Bukkit.getServer().getPlayer(args[0]));

            if(args.length == 1) {
                Messages.playerStats(target);
                return true;
            }else {
                Messages.cmdList(player);
                return true;
            }
        }
        return true;
    }
}
