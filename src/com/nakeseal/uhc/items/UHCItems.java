package com.nakeseal.uhc.items;

import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.NamespacedKey;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.ShapedRecipe;
import org.bukkit.inventory.meta.ItemMeta;

public class UHCItems
{
    public static ItemStack hgapple;
    public static void init()
    {
        createGoldenHead();
    }

    private static void createGoldenHead()
    {
        ItemStack item = new ItemStack(Material.GOLDEN_APPLE,1);
        ItemMeta meta = item.getItemMeta();
        assert meta != null;
        meta.setDisplayName("Golden Head");
        item.setItemMeta(meta);
        hgapple = item;

        ShapedRecipe sr = new ShapedRecipe(NamespacedKey.minecraft("hgapple"),item);
        sr.shape("***","*-*","***");
        sr.setIngredient('*',Material.GOLD_INGOT);
        sr.setIngredient('-',Material.PLAYER_HEAD);
        Bukkit.getServer().addRecipe(sr);
    }

}

