package com.nakeseal.uhc;

import org.bukkit.Bukkit;
import org.bukkit.scoreboard.Objective;
import org.bukkit.scoreboard.ScoreboardManager;
import org.bukkit.scoreboard.Team;

import java.util.Objects;

public class UHCBoard {
    static ScoreboardManager manager = Bukkit.getScoreboardManager();
    public static org.bukkit.scoreboard.Scoreboard board = Objects.requireNonNull(manager).getNewScoreboard();
    public static Objective obj = UHCBoard.board.registerNewObjective("Health", "health", "Health");
    public static Team red = board.registerNewTeam("Rojo");
    public static Team green = board.registerNewTeam("Verde");
    public static Team blue = board.registerNewTeam("Azul");
    public static Team black = board.registerNewTeam("Negro");
    public static Team pink = board.registerNewTeam("Rosa");
    public static Team yellow = board.registerNewTeam("Amarillo");
    public static Team white = board.registerNewTeam("Blanco");
    public static Team gay = board.registerNewTeam("LGBT");
    public static Team def = board.registerNewTeam("DEFAULT");
}
