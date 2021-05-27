package com.nakeseal.uhc;

import com.nakeseal.uhc.commands.*;
import com.nakeseal.uhc.events.UHCEvents;
import com.nakeseal.uhc.items.UHCItems;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.entity.Player;
import org.bukkit.plugin.java.JavaPlugin;
import org.bukkit.scoreboard.DisplaySlot;

import java.util.Objects;

public class UHC extends JavaPlugin
{
    @Override
    public void onEnable()
    {
        this.getServer().getPluginManager().registerEvents(new UHCEvents(), this);
        UHCItems.init();
        UHCFunctions.setColorsTeam();
        getServer().getConsoleSender().sendMessage(ChatColor.GREEN + "[UHC-NN]: Plugin initialized!");
        // Prepare Command
        Objects.requireNonNull(this.getCommand("prepare")).setExecutor(new PrepareCommand());
        Objects.requireNonNull(this.getCommand("prepare")).setTabCompleter(new PrepareTabCommand());
        // Inv Command
        Objects.requireNonNull(this.getCommand("inv")).setExecutor(new InvCommand());
        // TeamChat Command
        Objects.requireNonNull(this.getCommand("tc")).setExecutor(new TeamChatCommand());
        // Team Command
        Objects.requireNonNull(this.getCommand("team")).setExecutor(new TeamCommand());
        Objects.requireNonNull(this.getCommand("team")).setTabCompleter(new TeamTabCommand());
        // Report Command
        Objects.requireNonNull(this.getCommand("report")).setExecutor(new ReportCommand());
        for (Player on_player : Bukkit.getOnlinePlayers())
        {
            UHCBoard.obj.setDisplaySlot(DisplaySlot.PLAYER_LIST);
            on_player.setScoreboard(UHCBoard.board);
        }
    }

    @Override
    public void onDisable()
    {
        getServer().getConsoleSender().sendMessage(ChatColor.GREEN + "[UHC-NN]: Plugin disabled!");
    }
}
