package com.nakeseal.uhc;

import org.bukkit.Bukkit;
import org.bukkit.scoreboard.Objective;
import org.bukkit.scoreboard.ScoreboardManager;
import org.bukkit.scoreboard.Team;

import java.util.Objects;

public class UHCBoard
{
    static ScoreboardManager manager = Bukkit.getScoreboardManager();
    public static org.bukkit.scoreboard.Scoreboard board = Objects.requireNonNull(manager).getNewScoreboard();
    public static Objective obj = UHCBoard.board.registerNewObjective("Health", "health", "Health");
    public static Team teams[] =
            {
            board.registerNewTeam("Rojo"),
            board.registerNewTeam("Verde"),
            board.registerNewTeam("Azul"),
            board.registerNewTeam("Negro"),
            board.registerNewTeam("Rosa"),
            board.registerNewTeam("Amarillo"),
            board.registerNewTeam("Blanco"),
            board.registerNewTeam("LGBT"),
            board.registerNewTeam("Default")
            };
}
