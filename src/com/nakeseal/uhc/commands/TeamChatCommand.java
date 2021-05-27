package com.nakeseal.uhc.commands;

import com.nakeseal.uhc.UHCBoard;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import java.util.Objects;

public class TeamChatCommand implements CommandExecutor
{
    // Command implementation
    public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args)
    {
        if (label.equalsIgnoreCase("tc"))
        {
            // Not a player
            if (!(sender instanceof Player))
            {
                // Message to console
                sender.sendMessage("Command only available for player");
            }
            assert sender instanceof Player;
            Player player = (Player) sender;
            // Command incomplete
            if (args.length == 0)
            {
                player.sendMessage(ChatColor.RED + "/tc <message>");
                return true;
            }
            // Complete command
            StringBuilder sb = new StringBuilder();
            for (String arg : args)
            {
                sb.append(arg);
                sb.append(" ");
            }
            String msg = sb.toString();
            for(String team_str : Objects.requireNonNull(UHCBoard.board.getEntryTeam(player.getName())).getEntries())
            {
                Player team_member = Bukkit.getServer().getPlayer(team_str);
                Objects.requireNonNull(team_member).sendMessage(ChatColor.BLUE + "[TEAM CHAT] " + player.getName() + ": " + msg);
            }
            return true;
        }
        return false;
    }
}
