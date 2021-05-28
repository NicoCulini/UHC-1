package com.nakeseal.uhc;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.entity.Player;

import java.util.ArrayList;
import java.util.List;

public class UHCFunctions
{
    public static void setColorsTeam()
    {
        UHCBoard.teams[0].setPrefix(ChatColor.RED + "[RED] " + ChatColor.WHITE);
        UHCBoard.teams[1].setPrefix(ChatColor.GREEN + "[GREEN] " + ChatColor.WHITE);
        UHCBoard.teams[2].setPrefix(ChatColor.BLUE + "[BLUE] " + ChatColor.WHITE);
        UHCBoard.teams[3].setPrefix(ChatColor.BLACK + "[BLACK] " + ChatColor.WHITE);
        UHCBoard.teams[4].setPrefix(ChatColor.LIGHT_PURPLE + "[PINK] " + ChatColor.WHITE);
        UHCBoard.teams[5].setPrefix(ChatColor.YELLOW + "[YELLOW] " + ChatColor.WHITE);
        UHCBoard.teams[6].setPrefix(ChatColor.GRAY + "[WHITE] " + ChatColor.WHITE);
        UHCBoard.teams[7].setPrefix(ChatColor.MAGIC + "[LGBT] " + ChatColor.WHITE);
        UHCBoard.teams[8].setPrefix(ChatColor.GRAY + "[DEFAULT] " + ChatColor.WHITE);
    }
    public static List<Player> getOperators()
    {
        List<Player> operators = new ArrayList<>();
        for (Player player : Bukkit.getServer().getOnlinePlayers())
        {
            if (player.isOp())
            {
                operators.add(player);
            }
        }
        return operators;
    }
    public static List<Player> getOnlinePlayers()
    {
        return new ArrayList<>(Bukkit.getServer().getOnlinePlayers());
    }
}
