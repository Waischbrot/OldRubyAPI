package de.rubymc.rubyapi.utils;

import de.rubymc.rubyapi.Main;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.plugin.Plugin;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

public class ConfigManager {

    private static Plugin plugin = Main.plugin;

    public static void startup() {
        File configFile = new File(plugin.getDataFolder(), "config.yml");
        if(configFile.exists()){
            plugin.getLogger().info("§c  > Loading config");
        }
        else {
            plugin.getLogger().info("§c  > Creating Config");
            plugin.saveDefaultConfig();

            FileConfiguration c = plugin.getConfig();

            //Messages die konfigurierbar sind
            c.createSection("messages.prefix");
            c.createSection("messages.commands.reload");
            c.createSection("messages.commands.noperm");

            c.set("messages.prefix", "&cRubyAPI &8• &7");
            c.set("messages.commands.reload", "Du hast das Plugin erfolgreich neugeladen!");
            c.set("messages.commands.noperm", "&cDazu hast du leider keine Rechte!!!");

            plugin.saveConfig();
        }
    }
}
