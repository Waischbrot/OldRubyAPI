package de.rubymc.rubyapi.mysql;

import de.rubymc.rubyapi.Main;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.plugin.Plugin;

import java.io.File;
import java.io.IOException;
import java.sql.*;

public class MySQL {

    public static String username;
    public static String password;
    public static String database;
    public static String host;
    public static String port;
    public static Connection con;
    private static Plugin plugin = Main.plugin;

    public MySQL(String user, String pass, String host2, String dB) {}

    public static void connect()
    {
        if (!isConnected()) {
            try
            {
                con = DriverManager.getConnection("jdbc:mysql://" + host + ":" + port + "/" + database + "?user=" + username + "&password=" + password + "&autoReconnect=true");
                plugin.getLogger().info("§c  > Successfully connected to MySQL");
            }
            catch (SQLException e)
            {
                plugin.getLogger().info("§c  > Something went wrong...");
            }
        }
    }

    public static void close()
    {
        if (isConnected()) {
            try
            {
                con.close();
                plugin.getLogger().info("§c  > Disconnected from MySQL");
            }
            catch (SQLException e)
            {
                e.printStackTrace();
            }
        }
    }

    public static boolean isConnected()
    {
        return con != null;
    }

    public static void createBedwarsStatsTable()
    {
        if (isConnected()) {
            try
            {
                con.createStatement().executeUpdate("CREATE TABLE IF NOT EXISTS BedwarsStatsAPI (UUID VARCHAR(100), KILLS int, DEATHS int , WIN int , LOSE int , BEDS int)");
                plugin.getLogger().info("§c  > Bedwarsstatstable was created successfully!");
            }
            catch (SQLException e)
            {
                e.printStackTrace();
            }
        }
    }

    public static void createCoinsTable()
    {
        if (isConnected()) {
            try
            {
                con.createStatement().executeUpdate("CREATE TABLE IF NOT EXISTS CoinsAPI (UUID VARCHAR(100), COINS int)");
                plugin.getLogger().info("§c  > Coinstable was created successfully!");
            }
            catch (SQLException e)
            {
                e.printStackTrace();
            }
        }
    }

    public static void update(String qry)
    {
        if (isConnected()) {
            try
            {
                con.createStatement().executeUpdate(qry);
            }
            catch (SQLException e)
            {
                e.printStackTrace();
            }
        }
    }

    public static ResultSet getResult(String qry)
    {
        ResultSet rs = null;
        try
        {
            Statement st = con.createStatement();
            rs = st.executeQuery(qry);
        }
        catch (SQLException e)
        {
            connect();
            System.err.println(e);
        }
        return rs;
    }

    public static File getMySQLFile()
    {
        return new File("plugins/RubyAPI", "mysql.yml");
    }

    public static FileConfiguration getMySQLFileConfiguration()
    {
        return YamlConfiguration.loadConfiguration(getMySQLFile());
    }

    public static void setStandardMySQL()
    {
        FileConfiguration cfg = getMySQLFileConfiguration();

        cfg.options().copyDefaults(true);
        cfg.addDefault("username", "root");
        cfg.addDefault("password", "password");
        cfg.addDefault("database", "localhost");
        cfg.addDefault("host", "localhost");
        cfg.addDefault("port", "3306");
        try
        {
            cfg.save(getMySQLFile());
        }
        catch (IOException e)
        {
            e.printStackTrace();
        }
    }

    public static void readMySQL()
    {
        FileConfiguration cfg = getMySQLFileConfiguration();
        username = cfg.getString("username");
        password = cfg.getString("password");
        database = cfg.getString("database");
        host = cfg.getString("host");
        port = cfg.getString("port");
    }

}