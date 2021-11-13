package de.rubymc.rubyapi;

import de.rubymc.rubyapi.commands.Rubine;
import de.rubymc.rubyapi.commands.Stats;
import de.rubymc.rubyapi.mysql.MySQL;
import de.rubymc.rubyapi.utils.ConfigManager;
import org.bukkit.plugin.Plugin;
import org.bukkit.plugin.java.JavaPlugin;

import java.lang.module.Configuration;

public final class Main extends JavaPlugin {

    public static Plugin plugin;

    @Override
    public void onEnable() {
        plugin = this;

        connectMySQL();
        listenerRegistration();
        commandRegistration();

        ConfigManager.startup();

    }

    @Override
    public void onDisable() {
        MySQL.close();
    }

    private void connectMySQL() {
        MySQL.setStandardMySQL();
        MySQL.readMySQL();
        MySQL.connect();
        MySQL.createCoinsTable();
        MySQL.createBedwarsStatsTable();
    }

    private void listenerRegistration() {

    }

    private void commandRegistration() {
        this.getCommand("rubine").setExecutor(new Rubine());
        this.getCommand("stats").setExecutor(new Stats());
    }
}
