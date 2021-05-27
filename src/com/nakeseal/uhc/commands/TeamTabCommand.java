package com.nakeseal.uhc.commands;

import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.command.TabCompleter;

import java.util.ArrayList;
import java.util.List;

import static com.nakeseal.uhc.UHCBoard.teams;

public class TeamTabCommand implements TabCompleter
{
    List<String> argument_1 = new ArrayList<String>();
    List<String> argument_2 = new ArrayList<String>();
    public List<String > onTabComplete(CommandSender sender, Command cmd, String label, String[] args)
    {
        // Variables definition
        if (argument_1.isEmpty())
        {
            argument_1.add("list");
            argument_1.add("join");
        }
        List<String> result_1 = new ArrayList<String>();
        if (argument_2.isEmpty())
        {
            argument_2.add("Rojo");
            argument_2.add("Verde");
            argument_2.add("Azul");
            argument_2.add("Negro");
            argument_2.add("Rosa");
            argument_2.add("Amarillo");
            argument_2.add("Blanco");
            argument_2.add("LGBT");
            argument_2.add("Default");
        }
        List<String> result_2 = new ArrayList<String>();

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

        if (args.length == 2 && args[0].equalsIgnoreCase("join"))
        {
            for (String z : argument_2)
            {
                if (z.toLowerCase().startsWith(args[1].toLowerCase()))
                    result_2.add(z);
            }
            return result_2;
        }
        return null;
    }
}
