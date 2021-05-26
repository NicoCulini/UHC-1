package com.nakeseal.uhc.events;

import com.nakeseal.uhc.UHCBoard;
import org.bukkit.ChatColor;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.AsyncPlayerChatEvent;
import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.entity.*;
import org.bukkit.event.block.BlockBreakEvent;
import org.bukkit.event.entity.EntityDeathEvent;
import org.bukkit.event.entity.PlayerDeathEvent;
import org.bukkit.event.player.PlayerItemConsumeEvent;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;
import org.bukkit.scoreboard.DisplaySlot;

import java.util.Objects;
import java.util.Random;

public class UHCEvents implements Listener {
    @EventHandler
    public static void onPlayerDeath(PlayerDeathEvent event) {
        Player player = event.getEntity().getPlayer();
        assert player != null;
        String comm1 = "setblock " + player.getLocation().getBlockX() + " ";
        int y = player.getLocation().getBlockY();
        int z = player.getLocation().getBlockZ();
        String head = " minecraft:player_head{ExtraType: " + player.getDisplayName() + "}";
        Bukkit.getServer().dispatchCommand(Bukkit.getConsoleSender(),comm1 + y + " " + z + " minecraft:oak_fence");
        y++; Bukkit.getServer().dispatchCommand(Bukkit.getConsoleSender(),comm1 + y + " " + z + head);
    }

    @EventHandler
    public void onBlockBreak(BlockBreakEvent event){
        if(event.getBlock().getType() == Material.IRON_ORE) {
            event.setCancelled(true);
            Random rand = new Random();
            int cant = rand.nextInt(3);
            if(cant==0)cant=1;
            event.getBlock().setType(Material.AIR);
            event.getBlock().getWorld().dropItemNaturally(event.getBlock().getLocation(), new ItemStack(Material.IRON_INGOT,cant));
            Player player = event.getPlayer();
            player.giveExp(3);
        }
        else if(event.getBlock().getType() == Material.GOLD_ORE) {
            event.setCancelled(true);
            Random rand = new Random();
            int cant = rand.nextInt(3);
            if(cant==0)cant=1;
            event.getBlock().setType(Material.AIR);
            event.getBlock().getWorld().dropItemNaturally(event.getBlock().getLocation(), new ItemStack(Material.GOLD_INGOT,cant));
            Player player = event.getPlayer();
            player.giveExp(3);
        }else if(event.getBlock().getType() == Material.DIAMOND) {
            event.setCancelled(true);
            Random rand = new Random();
            int cant = rand.nextInt(3);
            if(cant==0)cant=1;
            event.getBlock().setType(Material.AIR);
            event.getBlock().getWorld().dropItemNaturally(event.getBlock().getLocation(), new ItemStack(Material.DIAMOND,cant));
            Player player = event.getPlayer();
            player.giveExp(3);
        }else if(event.getBlock().getType() == Material.COAL_ORE) {
            event.setCancelled(true);
            Random rand = new Random();
            int cant = rand.nextInt(4);
            if(cant==0)cant=1;
            event.getBlock().setType(Material.AIR);
            event.getBlock().getWorld().dropItemNaturally(event.getBlock().getLocation(), new ItemStack(Material.TORCH,cant));
            Player player = event.getPlayer();
            player.giveExp(3);
        }else if(event.getBlock().getType() == Material.GRAVEL) {
            event.setCancelled(true);
            Random rand = new Random();
            int cant = rand.nextInt(3);
            if(cant==0)cant=1;
            int drop = rand.nextInt(2);
            event.getBlock().setType(Material.AIR);
            if(drop==0){
                event.getBlock().getWorld().dropItemNaturally(event.getBlock().getLocation(), new ItemStack(Material.ARROW,cant));
            } else {
                event.getBlock().getWorld().dropItemNaturally(event.getBlock().getLocation(), new ItemStack(Material.FLINT,1));
            }
            Player player = event.getPlayer();
            player.giveExp(2);
        }

    }

    @EventHandler
    public void onMobDeath(EntityDeathEvent event) {
        LivingEntity e = event.getEntity();
        if(e instanceof Cow) {
            Random rand = new Random();
            event.getDrops().clear();
            Objects.requireNonNull(e.getLocation().getWorld()).dropItem(e.getLocation(),new ItemStack(Material.COOKED_BEEF,rand.nextInt(3)+1));
            e.getLocation().getWorld().dropItem(e.getLocation(),new ItemStack(Material.LEATHER,rand.nextInt(3)+1));
        } else if(e instanceof Pig) {
            Random rand = new Random();
            event.getDrops().clear();
            Objects.requireNonNull(e.getLocation().getWorld()).dropItem(e.getLocation(),new ItemStack(Material.COOKED_PORKCHOP,rand.nextInt(3)+1));
        } else if(e instanceof Chicken) {
            Random rand = new Random();
            event.getDrops().clear();
            Objects.requireNonNull(e.getLocation().getWorld()).dropItem(e.getLocation(),new ItemStack(Material.COOKED_CHICKEN,rand.nextInt(3)+1));
            e.getLocation().getWorld().dropItem(e.getLocation(),new ItemStack(Material.FEATHER,rand.nextInt(2)+1));
        } else if(e instanceof Sheep) {
            Random rand = new Random();
            event.getDrops().clear();
            Objects.requireNonNull(e.getLocation().getWorld()).dropItem(e.getLocation(),new ItemStack(Material.COOKED_MUTTON,rand.nextInt(3)+1));
            e.getLocation().getWorld().dropItem(e.getLocation(),new ItemStack(Material.WHITE_WOOL,rand.nextInt(1)+1));
        } else if(e instanceof Fish){
            Random rand = new Random();
            event.getDrops().clear();
            Objects.requireNonNull(e.getLocation().getWorld()).dropItem(e.getLocation(),new ItemStack(Material.COOKED_SALMON,rand.nextInt(3)+1));
        }
    }

    @EventHandler
    public void onAppleConsume(PlayerItemConsumeEvent event) {
        if(event.getItem().getType() == Material.GOLDEN_APPLE) {
            Player player = event.getPlayer();
            if(player.getInventory().getItemInMainHand().hasItemMeta() && Objects.requireNonNull(player.getInventory().getItemInMainHand().getItemMeta()).hasDisplayName()) {
                if(player.getInventory().getItemInMainHand().getItemMeta().getDisplayName().equals("Golden Head")) {
                    player.removePotionEffect(PotionEffectType.ABSORPTION);
                    player.removePotionEffect(PotionEffectType.REGENERATION);
                    player.addPotionEffect(new PotionEffect(PotionEffectType.ABSORPTION,1200,1));
                    player.addPotionEffect(new PotionEffect(PotionEffectType.REGENERATION,200,1));
                }
            }
        }
    }

    @EventHandler
    public void onPlayerJoin(PlayerJoinEvent event) {
        UHCBoard.teams[8].addEntry(event.getPlayer().getDisplayName());
        UHCBoard.obj.setDisplaySlot(DisplaySlot.PLAYER_LIST);
        event.getPlayer().setScoreboard(UHCBoard.board);
    }

    @EventHandler
    public void ChatCheck(AsyncPlayerChatEvent event) {
        Player player = event.getPlayer();
        event.setFormat(Objects.requireNonNull(UHCBoard.board.getEntryTeam(player.getName())).getPrefix() + ChatColor.RESET +"%s: %s");
    }
}