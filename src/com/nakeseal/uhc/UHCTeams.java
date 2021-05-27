package com.nakeseal.uhc;

import org.bukkit.ChatColor;

public class UHCTeams {
    public static void init(){
        teamColorAssing();
    }

    private static void teamColorAssing() {
        UHCBoard.teams[0].setPrefix(ChatColor.RED + "[ROJO] " + ChatColor.WHITE);
        UHCBoard.teams[1].setPrefix(ChatColor.GREEN + "[VERDE] " + ChatColor.WHITE);
        UHCBoard.teams[2].setPrefix(ChatColor.BLUE + "[AZUL] " + ChatColor.WHITE);
        UHCBoard.teams[3].setPrefix(ChatColor.BLACK + "[NEGRO] " + ChatColor.WHITE);
        UHCBoard.teams[4].setPrefix(ChatColor.LIGHT_PURPLE + "[ROSA] " + ChatColor.WHITE);
        UHCBoard.teams[5].setPrefix(ChatColor.YELLOW + "[AMARILLO] " + ChatColor.WHITE);
        UHCBoard.teams[6].setPrefix(ChatColor.GRAY + "[BLANCO] " + ChatColor.WHITE);
        UHCBoard.teams[7].setPrefix(ChatColor.MAGIC + "[LGBT] " + ChatColor.WHITE);
        UHCBoard.teams[8].setPrefix(ChatColor.GRAY + "[DEFAULT] " + ChatColor.WHITE);
    }
}
