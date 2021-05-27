package com.nakeseal.uhc.commands;

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
            if (args.length == 0 || args.length > 2 || args.length == 2 && args[0].equalsIgnoreCase("list"))
            {
                player.sendMessage(ChatColor.RED + "/team <list|join> <|team-name>");
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
            else if (args[0].equals("join"))
            {
                String t_name = args[1];
                for (int i=0; i<8; i++)
                {
                    if (UHCBoard.teams[i].getDisplayName().equalsIgnoreCase(t_name))
                    {
                        UHCBoard.teams[i].addEntry(player.getName());
                        player.sendMessage(ChatColor.GREEN + "You joined to team " + UHCBoard.teams[i].getName());
                    }
                }
            }
            return true;
        }
        return false;
    }
}
