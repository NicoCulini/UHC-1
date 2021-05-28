package com.nakeseal.uhc.commands;

import com.nakeseal.uhc.UHCFunctions;
import org.bukkit.ChatColor;
import org.bukkit.entity.Player;
import com.nakeseal.uhc.UHCBoard;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.command.CommandExecutor;

public class TeamCommand implements CommandExecutor
{
    // Command implementation
    public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args)
    {
        if (label.equalsIgnoreCase("team"))
        {
            // Not a player
            if (!(sender instanceof Player))
            {
                // Message to console
                sender.sendMessage("Command only available for player");
                return false;
            }
            Player player = (Player) sender;
            // Command incomplete
            if (args.length == 0 || args.length > 1 && args[0].equalsIgnoreCase("list") || args.length > 2 && args[0].equalsIgnoreCase("join") || args.length > 3)
            {
                player.sendMessage(ChatColor.RED + "/team <list╎join╎move> <null╎team-name╎player-name> <null╎null╎team-name>");
                return true;
            }
            // Not an operator
            if (args[0].equalsIgnoreCase("move") && !UHCFunctions.getOperators().contains(player))
            {
                sender.sendMessage(ChatColor.RED + "Command only available for operators");
                return true;
            }
            // Complete command or so
            if (args[0].equals("list"))
            {
                for (int i=0; i<9; i++)
                {
                    player.sendMessage(UHCBoard.teams[i].getDisplayName() + UHCBoard.teams[i].getEntries());
                }
            }
            if (args[0].equals("join"))
            {
                String t_name = args[1];
                for (int i=0; i<8; i++)
                {
                    if (UHCBoard.teams[i].getDisplayName().equalsIgnoreCase(t_name))
                    {
                        UHCBoard.teams[i].addEntry(player.getName());
                        player.sendMessage(ChatColor.GREEN + "You joined to team " + UHCBoard.teams[i].getName());
                        return true;
                    }
                }
            }
            if (args[0].equals("move"))
            {
                String moved = args[1];
                String t_name = args[2];
                for (int i=0; i<8; i++)
                {
                    if (UHCBoard.teams[i].getDisplayName().equalsIgnoreCase(t_name))
                    {
                        UHCBoard.teams[i].addEntry(moved);
                        player.sendMessage(ChatColor.GREEN + moved + " successfully moved to team " + UHCBoard.teams[i].getName());
                        for (Player pj : UHCFunctions.getOnlinePlayers())
                        {
                            if (pj.getName().equalsIgnoreCase(moved))
                            {
                                pj.sendMessage(ChatColor.GREEN + "You has been moved to team " + UHCBoard.teams[i].getName());
                            }
                        }
                        return true;
                    }
                }
            }
            return true;
        }
        return false;
    }
}
