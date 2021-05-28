package com.nakeseal.uhc.commands;

import com.nakeseal.uhc.UHCFunctions;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.entity.Player;
import org.bukkit.command.Command;
import org.bukkit.inventory.Inventory;
import org.bukkit.command.CommandSender;
import org.bukkit.command.CommandExecutor;

public class InvCommand implements CommandExecutor
{
    // Command implementation
    public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args)
    {
        if (label.equalsIgnoreCase("inv"))
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
                sender.sendMessage(ChatColor.RED + "Command only available for operators");
                return true;
            }
            // Command incomplete
            if (args.length != 1)
            {
                player.sendMessage(ChatColor.RED + "/inv <player>");
                return true;
            }
            // Complete command
            Player objective = Bukkit.getServer().getPlayer(args[0]);
            assert objective != null;
            Inventory inv = objective.getInventory();
            player.openInventory(inv);
            return true;
        }
        return false;
    }
}
