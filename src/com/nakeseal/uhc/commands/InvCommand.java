package com.nakeseal.uhc.commands;

import org.bukkit.Bukkit;
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
            if (!(sender instanceof Player)) {
                // Message to console
                sender.sendMessage("Command only available for player");
            }
            assert sender instanceof Player;
            Player player = (Player) sender;
            // Command incomplete
            if (args.length != 1)
            {
                player.sendMessage("/inv <player>");
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
