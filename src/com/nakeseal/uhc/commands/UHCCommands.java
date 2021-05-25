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

        UHCBoard.teams[0].setPrefix(ChatColor.RED + "[ROJO] " + ChatColor.WHITE);
        UHCBoard.teams[1].setPrefix(ChatColor.GREEN + "[VERDE] " + ChatColor.WHITE);
        UHCBoard.teams[2].setPrefix(ChatColor.BLUE + "[AZUL] " + ChatColor.WHITE);
        UHCBoard.teams[3].setPrefix(ChatColor.BLACK + "[NEGRO] " + ChatColor.WHITE);
        UHCBoard.teams[4].setPrefix(ChatColor.LIGHT_PURPLE + "[ROSA] " + ChatColor.WHITE);
        UHCBoard.teams[5].setPrefix(ChatColor.YELLOW + "[AMARILLO] " + ChatColor.WHITE);
        UHCBoard.teams[6].setPrefix(ChatColor.GRAY + "[BLANCO] " + ChatColor.WHITE);
        UHCBoard.teams[7].setPrefix(ChatColor.MAGIC + "[LGBT] " + ChatColor.WHITE);
        UHCBoard.teams[8].setPrefix(ChatColor.GRAY + "[DEFAULT] " + ChatColor.WHITE);

        if (cmd.getName().equalsIgnoreCase("prepare")) {
            if (args.length >= 1) {
                int map_size = Integer.parseInt(args[0]);
                int corner = (map_size/2)-1;
                boolean[] cor_used = {false, false, false, false, false, false, false, false};
                int[] cords_x = {corner, corner, -corner, -corner, corner/2, corner/2, -(corner/2), -(corner/2)};
                int[] cords_z = {corner, -corner, corner, -corner, corner, -corner, corner, -corner};
                Bukkit.getServer().dispatchCommand(Bukkit.getConsoleSender(),"worldborder center 0 0");
                Bukkit.getServer().dispatchCommand(Bukkit.getConsoleSender(),"worldborder set " + map_size);

                int cant = 0;
                for(int i=0; i<8; i++){
                    if(UHCBoard.teams[i].getSize() > 0) cant++;
                }

                for(int i=0; i<8; i++){
                    if(UHCBoard.teams[i].getSize() > 0){
                        int index = rand.nextInt(cant);
                        while (cor_used[index]) index = rand.nextInt(cant);
                        cor_used[index] = true;
                        int high = getHighestY(cords_x[index],cords_z[index]);
                        for (String pj : UHCBoard.teams[i].getEntries()) {
                            String comm = "tp " + pj + " " + cords_x[index] + " " + high + " " + cords_z[index];
                            Bukkit.getServer().dispatchCommand(Bukkit.getConsoleSender(),comm);
                            if (new Location(Bukkit.getWorlds().get(0), cords_x[index],high-1,cords_z[index]).getBlock().getType() == Material.BEDROCK) {
                                Bukkit.getServer().dispatchCommand(Bukkit.getConsoleSender(),"give " + pj + " minecraft:oak_boat");
                            }
                        }
                    }
                }
            } else {
                player.sendMessage("/prepare <map-size>");
            }
        }
        if (cmd.getName().equalsIgnoreCase("team")) {
            if (args.length >= 1) {
                if (args[0].equals("list")) {
                    player.sendMessage("ROJO " + UHCBoard.red.getEntries());
                    player.sendMessage("VERDE " + UHCBoard.green.getEntries());
                    player.sendMessage("AZUL " + UHCBoard.blue.getEntries());
                    player.sendMessage("NEGRO " + UHCBoard.black.getEntries());
                    player.sendMessage("ROSA " + UHCBoard.pink.getEntries());
                    player.sendMessage("AMARILLO " + UHCBoard.yellow.getEntries());
                    player.sendMessage("BLANCO " + UHCBoard.white.getEntries());
                    player.sendMessage("LGBT " + UHCBoard.gay.getEntries());
                    player.sendMessage("DEFAULT " + UHCBoard.def.getEntries());
                }
                else if (args[0].equals("join")) {
                    String t_name = args[1];
                    if(UHCBoard.red.getDisplayName().equalsIgnoreCase(t_name)) UHCBoard.red.addEntry(player.getName());
                    if(UHCBoard.green.getDisplayName().equalsIgnoreCase(t_name)) UHCBoard.green.addEntry(player.getName());
                    if(UHCBoard.blue.getDisplayName().equalsIgnoreCase(t_name)) UHCBoard.blue.addEntry(player.getName());
                    if(UHCBoard.black.getDisplayName().equalsIgnoreCase(t_name)) UHCBoard.black.addEntry(player.getName());
                    if(UHCBoard.pink.getDisplayName().equalsIgnoreCase(t_name)) UHCBoard.pink.addEntry(player.getName());
                    if(UHCBoard.yellow.getDisplayName().equalsIgnoreCase(t_name)) UHCBoard.yellow.addEntry(player.getName());
                    if(UHCBoard.white.getDisplayName().equalsIgnoreCase(t_name)) UHCBoard.white.addEntry(player.getName());
                    if(UHCBoard.gay.getDisplayName().equalsIgnoreCase(t_name)) UHCBoard.gay.addEntry(player.getName());
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
