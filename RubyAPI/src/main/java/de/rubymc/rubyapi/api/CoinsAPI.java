package de.rubymc.rubyapi.api;

import de.rubymc.rubyapi.mysql.MySQL;

import java.sql.ResultSet;
import java.sql.SQLException;

public class CoinsAPI {

    public static boolean playerExists(String uuid)
    {
        try
        {
            ResultSet rs = MySQL.getResult("SELECT * FROM CoinsAPI WHERE UUID='" + uuid + "'");
            if (rs.next()) {
                return rs.getString("UUID") != null;
            }
            return false;
        }
        catch (SQLException e)
        {
            e.printStackTrace();
        }
        return false;
    }

    public static void createPlayer(String uuid)
    {
        if (!playerExists(uuid)) {
            MySQL.update("INSERT INTO CoinsAPI (UUID, Coins) VALUES ('" + uuid + "', 0);");
        }
    }

    public static Integer getCoins(String uuid) {
        Integer i = Integer.valueOf(0);
        if (playerExists(uuid)) {
            try {
                ResultSet rs = MySQL.getResult("SELECT * FROM CoinsAPI WHERE UUID='" + uuid + "'");
                if(rs.next()) {
                    Integer.valueOf(rs.getInt("COINS"));
                }
                i = Integer.valueOf(rs.getInt("COINS"));
            } catch(SQLException e) {
                e.printStackTrace();
            }
        } else {
            createPlayer(uuid);
            getCoins(uuid);
        }
        return i;
    }

    public static void setCoins(String uuid, Integer coins) {
        if(playerExists(uuid)) {
            MySQL.update("UPDATE CoinsAPI SET COINS='" + coins + "' WHERE UUID='" + uuid + "'");
        } else {
            createPlayer(uuid);
            setCoins(uuid, coins);
        }
    }

    public static void addCoins(String uuid, Integer coins) {
        if(playerExists(uuid)) {
            setCoins(uuid, Integer.valueOf(getCoins(uuid).intValue() + coins.intValue()));
        } else {
            createPlayer(uuid);
            addCoins(uuid, coins);
        }
    }

    public static void removeCoins(String uuid, Integer coins) {
        if(playerExists(uuid)) {
            setCoins(uuid, Integer.valueOf(getCoins(uuid).intValue() - coins.intValue()));
        } else {
            createPlayer(uuid);
            removeCoins(uuid, coins);
        }
    }

}
