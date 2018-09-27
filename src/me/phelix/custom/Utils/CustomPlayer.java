package me.phelix.custom.Utils;

import org.bukkit.ChatColor;
import org.bukkit.Location;
import org.bukkit.World;
import org.bukkit.entity.Player;
import org.bukkit.plugin.Plugin;

import java.util.UUID;

public class CustomPlayer {

    private final Plugin plugin;
    private final Player player;


    public CustomPlayer(Plugin main, Player player){
        this.player = player;
        this.plugin = main;
    }

    public Player getPlayer(){
        return player;
    }

    public UUID getUniqueId(){
        return player.getUniqueId();
    }

    public void sendMessage(String s){
        player.sendMessage(s);
    }

    public void sendMessage(String[] s){
        player.sendMessage(s);
    }

    public void sendMessage(int i) { player.sendMessage(String.valueOf(i)); }

    public boolean hasPlayedBefore(){
        return player.hasPlayedBefore();
    }

    public World getWorld(){
        return player.getWorld();
    }

    public String getName(){ return player.getName(); }

    public void teleport(Location location){
        player.teleport(location);
    }



    public Integer getLevel(){
        return plugin.getConfig().getInt("Players." + player.getUniqueId().toString() + player.getName() + ".Level");
    }

    public Integer getXP(){
        return plugin.getConfig().getInt("Players." + player.getUniqueId().toString() + player.getName() + ".XP");
    }

    public void setLevel(int i){
        plugin.getConfig().set("Players." + player.getUniqueId().toString() + player.getName() + ".Level", i);
        calculateLevel();
        plugin.saveConfig();
    }

    public void setXP(int i){
        plugin.getConfig().set("Players." + player.getUniqueId().toString() + player.getName() + ".XP", i);
        calculateLevel();
        plugin.saveConfig();
    }

    public void addXP(int i){
        plugin.getConfig().set("Players." + player.getUniqueId().toString() + player.getName() + ".XP", getXP() + i);
        calculateLevel();
        plugin.saveConfig();
    }

    public void addLevel(int i){
        plugin.getConfig().set("Players." + player.getUniqueId().toString() + player.getName() + ".Level", getLevel() + i);
        calculateLevel();
        plugin.saveConfig();
    }

    public void removeLevel(int i){
        plugin.getConfig().set("Players." + player.getUniqueId().toString() + player.getName() + ".Level", getLevel() - i);
        calculateLevel();
        plugin.saveConfig();
    }

    public void removeXP(int i){
        plugin.getConfig().set("Players." + player.getUniqueId().toString() + player.getName() + ".XP", getXP() - i);
        calculateLevel();
        plugin.saveConfig();
    }

    private void calculateLevel(){
        int formula = 5 * getLevel() + 50;
        while(getXP() >= formula){
            plugin.getConfig().set("Players." + player.getUniqueId().toString() + player.getName() + ".XP", getXP() - formula);
            plugin.getConfig().set("Players." + player.getUniqueId().toString() + player.getName() + ".Level", getLevel() + 1);
            player.sendMessage(ChatColor.GREEN + ChatColor.STRIKETHROUGH.toString() + "------------------------------");
            player.sendMessage(ChatColor.GREEN + ChatColor.BOLD.toString() + "LEVELUP!");
            player.sendMessage("");
            player.sendMessage(ChatColor.YELLOW + "Current Level: " + ChatColor.WHITE + getLevel());
            player.sendMessage(ChatColor.YELLOW + "Current Level: " + ChatColor.WHITE + getLevel() + 1);
            player.sendMessage(ChatColor.YELLOW + "Progress: " + ChatColor.WHITE + neededXPPercentage() + "% " + ChatColor.GRAY + "/ " + neededXPBar());
            player.sendMessage("");
            player.sendMessage(ChatColor.YELLOW + "Current XP: " + ChatColor.WHITE + getXP());
            player.sendMessage(ChatColor.YELLOW + "Required XP to levelup: " + ChatColor.WHITE + totalNeededXP());
            player.sendMessage(ChatColor.YELLOW + "Current required XP to levelup: " + ChatColor.WHITE + neededXP());
            player.sendMessage(ChatColor.GREEN + ChatColor.STRIKETHROUGH.toString() + "------------------------------");
        }
    }


    // XP needed for next level
    public Integer neededXP(){
        int requiredXP = 5 * getLevel() + 50;
        return requiredXP - getXP();
    }

    // Total XP needed for next level
    public Integer totalNeededXP(){
        return (5 * getLevel()) + 50;
    }

    // Total experience in percentage
    public Integer neededXPPercentage(){
        double i = ((double) getXP()) / totalNeededXP() * 100;
        return (int) i;
    }

    public String neededXPBar(){
        int totalBars = 50;
        float percent = (float) getXP() / totalNeededXP();

        int progressBars = (int) (totalBars * percent);

        int leftOver = totalBars - progressBars;

        StringBuilder sb = new StringBuilder();
        String completed = ChatColor.GREEN + "|";
        String uncompleted = ChatColor.RED + "|";

        for(int i = 0; i < progressBars; i++){
            sb.append(completed);
        }
        for(int i = 0; i < leftOver; i++){
            sb.append(uncompleted);
        }
        return sb.toString();
    }


}
