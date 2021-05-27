package com.nakeseal.uhc.commands;

import com.nakeseal.uhc.UHCFunctions;
import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import java.util.Arrays;
import java.util.Random;

public class ReportCommand implements CommandExecutor
{
    // Command implementation
    public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args)
    {
        if (label.equalsIgnoreCase("report"))
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
            if (args.length < 1)
            {
                player.sendMessage(ChatColor.RED + "/report <player-to-report> <message>");
                return true;
            }
            // Complete command or so
            // Variables definition
            String reported = args[0];
            args = Arrays.copyOfRange(args, 1, args.length);
            StringBuilder sb = new StringBuilder();
            for (String arg : args)
            {
                sb.append(arg);
                sb.append(" ");
            }
            String msg = sb.toString();
            // Command execution
            for (Player operator : UHCFunctions.getOperators())
            {
                operator.sendMessage(ChatColor.DARK_PURPLE + "Report realized to: " + ChatColor.WHITE + reported);
                operator.sendMessage(ChatColor.DARK_PURPLE + "Message of report: " + ChatColor.GRAY + msg);
            }
            player.sendMessage(ChatColor.GREEN + "Report to: " + ChatColor.WHITE + reported + ChatColor.GREEN + " Successful");
            return true;
        }
        return false;
    }
}
