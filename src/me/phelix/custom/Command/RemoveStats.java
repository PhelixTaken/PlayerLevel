package me.phelix.custom.Command;

import me.phelix.custom.Utils.CustomPlayer;
import me.phelix.custom.Utils.Messages;
import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.plugin.Plugin;

public class RemoveStats implements CommandExecutor {

    private Plugin plugin;
    public RemoveStats(Plugin main) { this.plugin = main; }

    public boolean onCommand(CommandSender sender, Command cmd, String Label, String[] args) {

        if(!(sender instanceof Player)){
            sender.sendMessage(Messages.noPlayer);
            return true;
        }

        CustomPlayer player = new CustomPlayer(plugin, (Player) sender);

        if(!sender.hasPermission("custom.remove")){
            player.sendMessage(Messages.noPerm);
            return true;
        }

        if(cmd.getName().equalsIgnoreCase("remove")){
            if(args.length == 0){
                Messages.cmdList(player);
                return true;
            }

            if(!Bukkit.getOfflinePlayer(args[1]).isOnline()){
                player.sendMessage(args[1] + Messages.noOnlinePlayer);
                return true;
            }

            String number = args[2];
            int value = Integer.valueOf(number);
            CustomPlayer target = new CustomPlayer(plugin, (Player) Bukkit.getPlayer(args[1]));

            if(value < 0){
                player.sendMessage(Messages.lowerZero);
                return true;
            }

            if(args[0].equalsIgnoreCase("level")){
                target.removeLevel(value);
                player.sendMessage(target.getName() + "'s level got subtracted by " + value);
                target.sendMessage("Your levels got subtracted by " + value);
                return true;
            }

            if(args[0].equalsIgnoreCase("xp")){
                target.removeXP(value);
                player.sendMessage(target.getName() + "'s xp got subtracted by " + value);
                target.sendMessage("Your xp got subtracted by " + value);
                return true;
            }
        }
        return true;
    }
}
