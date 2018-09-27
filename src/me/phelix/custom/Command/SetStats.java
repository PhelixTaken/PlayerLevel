package me.phelix.custom.Command;

import me.phelix.custom.Utils.CustomPlayer;
import me.phelix.custom.Utils.Messages;
import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.plugin.Plugin;

public class SetStats implements CommandExecutor {

    private Plugin plugin;
    public SetStats(Plugin main) { this.plugin = main; }

    public boolean onCommand(CommandSender sender, Command cmd, String Label, String[] args) {

        if(!(sender instanceof Player)){
            sender.sendMessage(Messages.noPlayer);
            return true;
        }

        CustomPlayer player = new CustomPlayer(plugin, (Player) sender);

        if(!sender.hasPermission("custom.set")){
            player.sendMessage(Messages.noPerm);
            return true;
        }

        if(cmd.getName().equalsIgnoreCase("set")) {
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

            if (args[0].equalsIgnoreCase("level")) {
                target.setLevel(value);
                player.sendMessage(target.getName() + "'s level is set to " + value);
                target.sendMessage("Your level is set to " + value);
                return true;
            }

            if (args[0].equalsIgnoreCase("xp")) {
                target.setXP(value);
                player.sendMessage(target.getName() + "'s xp is set to " + value);
                target.sendMessage("Your xp is set to " + value);
                return true;
            }
        }
        return true;
    }

}
