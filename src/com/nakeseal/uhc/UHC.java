package com.nakeseal.uhc;

import com.nakeseal.uhc.commands.PrepareCommand;
import com.nakeseal.uhc.commands.PrepareTabCommand;
import com.nakeseal.uhc.commands.UHCCommands;
import com.nakeseal.uhc.events.UHCEvents;
import com.nakeseal.uhc.items.UHCItems;

import org.bukkit.ChatColor;
import org.bukkit.plugin.java.JavaPlugin;

import java.util.Objects;

public class UHC extends JavaPlugin {
    @Override
    public void onEnable() {
        this.getServer().getPluginManager().registerEvents(new UHCEvents(), this);
        UHCItems.init();
        UHCTeams.init();
        UHCCommands commands = new UHCCommands();
        getServer().getConsoleSender().sendMessage(ChatColor.GREEN + "[UHC-NN]: Plugin initialized!");
        Objects.requireNonNull(this.getCommand("prepare")).setExecutor(new PrepareCommand());
        Objects.requireNonNull(this.getCommand("prepare")).setTabCompleter(new PrepareTabCommand());
        Objects.requireNonNull(getCommand("team")).setExecutor(commands);
        Objects.requireNonNull(getCommand("tc")).setExecutor(commands);
        Objects.requireNonNull(getCommand("inv")).setExecutor(commands);
    }

    @Override
    public void onDisable() {
        getServer().getConsoleSender().sendMessage(ChatColor.GREEN + "[UHC-NN]: Plugin disabled!");
    }
}
