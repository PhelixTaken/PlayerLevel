package me.phelix.custom.Utils;

import org.bukkit.ChatColor;

import java.util.ArrayList;
import java.util.List;

public class Messages {

    public final static String noPlayer = ChatColor.RED + "You must be a player to perform this action!";
    public final static String noPerm = ChatColor.RED + "You don't have permissions to perform this action!";
    public final static String noOnlinePlayer = ChatColor.RED + " is not online!";
    public final static String lowerZero = ChatColor.RED + "Player can't go lower than 0!";

    private final static List<String> cmd = new ArrayList<>();

    public static void cmdList(CustomPlayer player){
        cmd.add("/stats");
        for(int i = 0; i < cmd.size(); i++){
            cmd.set(i, cmd.get(i).replace("/", ChatColor.RED + "/" + ChatColor.RESET));
            player.sendMessage(cmd.get(i));
        }
        cmd.clear();
    }

    public static void playerStats(CustomPlayer player){
        player.sendMessage(ChatColor.GREEN + ChatColor.STRIKETHROUGH.toString() + "--------------------------------");
        player.sendMessage(ChatColor.GREEN + ChatColor.BOLD.toString() + "STATS");
        player.sendMessage("");
        player.sendMessage(ChatColor.YELLOW + "Current Level: " + ChatColor.WHITE + player.getLevel());
        player.sendMessage(ChatColor.YELLOW + "Current Level: " + ChatColor.WHITE + (player.getLevel() + 1));
        player.sendMessage(ChatColor.YELLOW + "Progress: " + ChatColor.WHITE + player.neededXPPercentage() + "% " + ChatColor.GRAY + "/ " + player.neededXPBar());
        player.sendMessage("");
        player.sendMessage(ChatColor.YELLOW + "Current XP: " + ChatColor.WHITE + player.getXP());
        player.sendMessage(ChatColor.YELLOW + "Total required XP to levelup: " + ChatColor.WHITE + player.totalNeededXP());
        player.sendMessage(ChatColor.YELLOW + "Current required XP to levelup: " + ChatColor.WHITE + player.neededXP());
        player.sendMessage(ChatColor.GREEN + ChatColor.STRIKETHROUGH.toString() + "--------------------------------");
    }

}
