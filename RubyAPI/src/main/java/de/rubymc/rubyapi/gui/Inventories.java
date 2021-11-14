package de.rubymc.rubyapi.gui;

import de.rubymc.rubyapi.utils.ItemBuilder;
import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.inventory.Inventory;

import java.util.ArrayList;
import java.util.List;

public class Inventories {

    public static void openStats(Player player, Player target) {
        Inventory statsGUI = Bukkit.createInventory(null, 45, "§6§lStats §8| §e" + target.getName());

        for (int i = 0; i <= 8; i++) {
            statsGUI.setItem(i, new ItemBuilder(Material.GRAY_STAINED_GLASS_PANE).setDisplayName("§8").build());
        }
        for (int i = 36; i <= 44; i++) {
            statsGUI.setItem(i, new ItemBuilder(Material.GRAY_STAINED_GLASS_PANE).setDisplayName("§8").build());
        }
        statsGUI.setItem(4, new ItemBuilder(Material.PLAYER_HEAD).setDisplayName("§e" + player.getName()).setHead(player.getName()).build());
        statsGUI.setItem(20, new ItemBuilder(Material.PLAYER_HEAD).setDisplayName("§cKommt bald...").getSkull("http://textures.minecraft.net/texture/3ed1aba73f639f4bc42bd48196c715197be2712c3b962c97ebf9e9ed8efa025").build());

        List<String> fbstats = new ArrayList<>();
        fbstats.add("§7");
        fbstats.add("§7Kontostand:");
        fbstats.add("§7");
        statsGUI.setItem(19, new ItemBuilder(Material.PLAYER_HEAD).setDisplayName("§aFreebuild").setLore(fbstats).getSkull("http://textures.minecraft.net/texture/a33898d014250f264f6984d11bf152ec4af6478384aec8ae46965bd2c4f6f929").build());


        player.openInventory(statsGUI);
    }
}
