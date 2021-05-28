package com.nakeseal.uhc.commands;

import org.bukkit.command.Command;
import org.bukkit.command.TabCompleter;
import org.bukkit.command.CommandSender;

import java.util.List;
import java.util.ArrayList;

public class PrepareTabCommand implements TabCompleter
{
    List<String> arguments = new ArrayList<>();
    public List<String > onTabComplete(CommandSender sender, Command cmd, String label, String[] args)
    {
        if (arguments.isEmpty())
        {
            arguments.add("2500");
            arguments.add("5000");
            arguments.add("7500");
            arguments.add("10000");
        }
        List<String> result = new ArrayList<>();
        if (args.length == 1)
        {
            for (String x : arguments)
            {
                if (x.toLowerCase().startsWith(args[0].toLowerCase()))
                    result.add(x);
            }
            return result;
        }
        return null;
    }
}
