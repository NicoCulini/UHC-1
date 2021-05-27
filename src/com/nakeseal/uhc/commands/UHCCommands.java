package com.nakeseal.uhc.commands;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import com.nakeseal.uhc.UHCBoard;
import org.bukkit.inventory.Inventory;
import org.bukkit.scoreboard.DisplaySlot;

import java.util.Objects;
import java.util.Random;

public class UHCCommands implements CommandExecutor {
    public int getHighestY(int x, int z) {
        for (int i = 255; i > 0; i--) {
            if(new Location(Bukkit.getWorlds().get(0), x, i, z).getBlock().getType() != Material.AIR) {
                if(new Location(Bukkit.getWorlds().get(0), x, i, z).getBlock().getType() == Material.WATER) {
                    new Location(Bukkit.getWorlds().get(0), x, i, z).getBlock().setType(Material.BEDROCK);
                }
                return i+1;
            }
        }
        return 0;
    }
    @Override
    public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {
        if (!(sender instanceof Player)) {
            sender.sendMessage("Comando solo habilitado para jugadores");
            return true;
        }
        Player player = (Player) sender;
        Random rand = new Random();



        if (cmd.getName().equalsIgnoreCase("team")) {
            if (args.length >= 1) {
                if (args[0].equals("list")) {
                    for(int i=0; i<9; i++) {
                        player.sendMessage(UHCBoard.teams[i].getDisplayName() + UHCBoard.teams[i].getEntries());
                    }
                }
                else if (args[0].equals("join")) {
                    String t_name = args[1];
                    for(int i=0; i<8; i++){
                        if(UHCBoard.teams[i].getDisplayName().equalsIgnoreCase(t_name)) UHCBoard.teams[i].addEntry(player.getName());
                    }
                }
            } else {
                player.sendMessage("/team <list|join> <|team-name>");
            }
        }
        if (cmd.getName().equalsIgnoreCase("tc")) {
            if (args.length >= 1) {
                StringBuilder sb = new StringBuilder();
                for (String arg : args) {
                    sb.append(arg);
                    sb.append(" ");
                }
                String msg = sb.toString();
                for(String team_str : Objects.requireNonNull(UHCBoard.board.getEntryTeam(player.getName())).getEntries()) {
                    Player team_member = Bukkit.getServer().getPlayer(team_str);
                    Objects.requireNonNull(team_member).sendMessage("[CHAT DE EQUIPO] " + player.getName() + ": " + msg);
                }
            } else {
                player.sendMessage("/tc <message>");
            }
        }
        if (cmd.getName().equalsIgnoreCase("inv")) {
            if (args.length >= 1) {
                Player objective = Bukkit.getServer().getPlayer(args[0]);
                assert objective != null;
                Inventory inv = objective.getInventory();
                player.openInventory(inv);
            } else {
                player.sendMessage("/inv <player>");
            }
        }
        for(Player on_player : Bukkit.getOnlinePlayers()) {
            UHCBoard.obj.setDisplaySlot(DisplaySlot.PLAYER_LIST);
            on_player.setScoreboard(UHCBoard.board);
        }
        return true;
    }
}
