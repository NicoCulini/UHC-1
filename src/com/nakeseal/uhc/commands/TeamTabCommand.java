package com.nakeseal.uhc.commands;

import com.nakeseal.uhc.UHCFunctions;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.command.TabCompleter;
import org.bukkit.entity.Player;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class TeamTabCommand implements TabCompleter
{
    List<String> argument_1 = new ArrayList<>();
    List<String> team_names = new ArrayList<>();
    List<String> player_names = new ArrayList<>();
    public List<String > onTabComplete(CommandSender sender, Command cmd, String label, String[] args)
    {
        // Variables definition
        if (argument_1.isEmpty())
        {
            argument_1.add("list");
            argument_1.add("join");
            argument_1.add("move");
        }
        List<String> result_1 = new ArrayList<>();
        if (team_names.isEmpty())
        {
            team_names.add("Rojo");
            team_names.add("Verde");
            team_names.add("Azul");
            team_names.add("Negro");
            team_names.add("Rosa");
            team_names.add("Amarillo");
            team_names.add("Blanco");
            team_names.add("LGBT");
            team_names.add("Default");
        }
        List<String> result_team_names = new ArrayList<>();
        player_names.clear();
        for (Player pj : UHCFunctions.getOnlinePlayers())
        {
            player_names.add(pj.getName());
        }
        List<String> result_player_names = new ArrayList<>();
        // Command Tabbing execution
        if (args.length == 1)
        {
            for (String x : argument_1)
            {
                if (x.toLowerCase().startsWith(args[0].toLowerCase()))
                    result_1.add(x);
            }
            return result_1;
        }
        // join sub command
        if (args.length == 2 && args[0].equalsIgnoreCase("join"))
        {
            for (String z : team_names)
            {
                if (z.toLowerCase().startsWith(args[1].toLowerCase()))
                    result_team_names.add(z);
            }
            return result_team_names;
        }
        // Move sub command
        if (args.length == 2 && args[0].equalsIgnoreCase("move"))
        {
            for (String z : player_names)
            {
                if (z.toLowerCase().startsWith(args[1].toLowerCase()))
                    result_player_names.add(z);
            }
            return result_player_names;
        }
        if (args.length == 3 && args[0].equalsIgnoreCase("move"))
        {
            for (String z : team_names)
            {
                if (z.toLowerCase().startsWith(args[2].toLowerCase()))
                    result_team_names.add(z);
            }
            return result_team_names;
        }
        return Collections.emptyList();
    }
}
