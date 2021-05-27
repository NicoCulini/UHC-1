package com.nakeseal.uhc.commands;

import com.nakeseal.uhc.UHCFunctions;
import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.ChatColor;
import org.bukkit.entity.Player;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.command.CommandExecutor;

import com.nakeseal.uhc.UHCBoard;

import java.util.Random;

public class PrepareCommand implements CommandExecutor
{
    // Functions of the command
    public int getHighestY(int x, int z)
    {
        for (int i = 255; i > 0; i--)
        {
            if(new Location(Bukkit.getWorlds().get(0), x, i, z).getBlock().getType() != Material.AIR)
            {
                if(new Location(Bukkit.getWorlds().get(0), x, i, z).getBlock().getType() == Material.WATER)
                {
                    new Location(Bukkit.getWorlds().get(0), x, i, z).getBlock().setType(Material.BEDROCK);
                }
                return i+1;
            }
        }
        return 0;
    }
    // Command implementation
    public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args)
    {
        if (label.equalsIgnoreCase("prepare"))
        {
            // Not a player
            if (!(sender instanceof Player))
            {
                // Message to console
                sender.sendMessage("Command only available for player");
                return false;
            }
            Player player = (Player) sender;
            // Not an operator
            if (!UHCFunctions.getOperators().contains(player))
            {
                // Message to console
                sender.sendMessage(ChatColor.RED + "Command only available for operators");
                return true;
            }
            // Command incomplete
            if (args.length != 1)
            {
                player.sendMessage(ChatColor.RED + "/prepare <map-size>");
                return true;
            }

            // Complete command or so
            // Variables definition
            Random rand = new Random();
            int map_size = Integer.parseInt(args[0]);
            int corner = (map_size/2)-1;
            boolean[] cor_used = {false, false, false, false, false, false, false, false};
            int[] cords_x = {corner, corner, -corner, -corner, corner/2, corner/2, -(corner/2), -(corner/2)};
            int[] cords_z = {corner, -corner, corner, -corner, corner, -corner, corner, -corner};
            // Setting border
            Bukkit.getServer().dispatchCommand(Bukkit.getConsoleSender(),"worldborder center 0 0");
            Bukkit.getServer().dispatchCommand(Bukkit.getConsoleSender(),"worldborder set " + map_size);

            // Command Execution
            int cant = 0;
            for(int i=0; i<8; i++)
            {
                if(UHCBoard.teams[i].getSize() > 0) cant++;
            }

            for (int i=0; i<8; i++)
            {
                if(UHCBoard.teams[i].getSize() > 0)
                {
                    int index = rand.nextInt(cant);
                    while (cor_used[index]) index = rand.nextInt(cant);
                    cor_used[index] = true;
                    int high = getHighestY(cords_x[index],cords_z[index]);
                    for (String pj : UHCBoard.teams[i].getEntries())
                    {
                        String comm = "tp " + pj + " " + cords_x[index] + " " + high + " " + cords_z[index];
                        Bukkit.getServer().dispatchCommand(Bukkit.getConsoleSender(),comm);
                        if (new Location(Bukkit.getWorlds().get(0), cords_x[index],high-1,cords_z[index]).getBlock().getType() == Material.BEDROCK)
                        {
                            Bukkit.getServer().dispatchCommand(Bukkit.getConsoleSender(),"give " + pj + " minecraft:oak_boat");
                        }
                    }
                }
            }
            return true;
        }
        return false;
    }
}
