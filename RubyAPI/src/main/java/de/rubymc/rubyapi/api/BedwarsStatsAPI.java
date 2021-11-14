package de.rubymc.rubyapi.api;

import de.rubymc.rubyapi.mysql.MySQL;

import java.sql.ResultSet;
import java.sql.SQLException;

public class BedwarsStatsAPI {

    public static boolean playerExists(String uuid)
    {
        try
        {
            ResultSet rs = MySQL.getResult("SELECT * FROM BedwarsStatsAPI WHERE UUID='" + uuid + "'");
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
            MySQL.update("INSERT INTO BedwarsStatsAPI (UUID, KILLS, DEATHS, WIN, LOSE) VALUES ('" + uuid + "', '0', '0', '0', '0');");
        }
    }

    public static Integer getKills(String uuid)
    {
        Integer i = Integer.valueOf(0);
        if (playerExists(uuid))
        {
            try
            {
                ResultSet rs = MySQL.getResult("SELECT * FROM BedwarsStatsAPI WHERE UUID='" + uuid + "'");
                if (rs.next()) {
                    Integer.valueOf(rs.getInt("KILLS"));
                }
                i = Integer.valueOf(rs.getInt("KILLS"));
            }
            catch (SQLException e)
            {
                e.printStackTrace();
            }
        }
        else
        {
            createPlayer(uuid);
            getKills(uuid);
        }
        return i;
    }

    public static Integer getWins(String uuid)
    {
        Integer i = Integer.valueOf(0);
        if (playerExists(uuid))
        {
            try
            {
                ResultSet rs = MySQL.getResult("SELECT * FROM BedwarsStatsAPI WHERE UUID='" + uuid + "'");
                if (rs.next()) {
                    Integer.valueOf(rs.getInt("WIN"));
                }
                i = Integer.valueOf(rs.getInt("WIN"));
            }
            catch (SQLException e)
            {
                e.printStackTrace();
            }
        }
        else
        {
            createPlayer(uuid);
            getWins(uuid);
        }
        return i;
    }

    public static Integer getDestroyedBeds(String uuid) {

        Integer i = Integer.valueOf(0);
        if(playerExists(uuid)) {
            try {
                ResultSet rs = MySQL.getResult("SELECT * FROM BedwarsStatsAPI  WHERE UUID='" + uuid + "'");
                if(rs.next()) {
                    Integer.valueOf(rs.getInt("BEDS"));
                }
                i = Integer.valueOf(rs.getInt("BEDS"));
            } catch (SQLException e) {
                e.printStackTrace();
            }

        } else {
            createPlayer(uuid);
            getDestroyedBeds(uuid);
        }
        return i;
    }

    public static Integer getLoses(String uuid)
    {
        Integer i = Integer.valueOf(0);
        if (playerExists(uuid))
        {
            try
            {
                ResultSet rs = MySQL.getResult("SELECT * FROM BedwarsStatsAPI WHERE UUID='" + uuid + "'");
                if (rs.next()) {
                    Integer.valueOf(rs.getInt("LOSE"));
                }
                i = Integer.valueOf(rs.getInt("LOSE"));
            }
            catch (SQLException e)
            {
                e.printStackTrace();
            }
        }
        else
        {
            createPlayer(uuid);
            getLoses(uuid);
        }
        return i;
    }

    public static Integer getDeaths(String uuid)
    {
        Integer i = Integer.valueOf(0);
        if (playerExists(uuid))
        {
            try
            {
                ResultSet rs = MySQL.getResult("SELECT * FROM BedwarsStatsAPI WHERE UUID='" + uuid + "'");
                if (rs.next()) {
                    Integer.valueOf(rs.getInt("DEATHS"));
                }
                i = Integer.valueOf(rs.getInt("DEATHS"));
            }
            catch (SQLException e)
            {
                e.printStackTrace();
            }
        }
        else
        {
            createPlayer(uuid);
            getDeaths(uuid);
        }
        return i;
    }


    public static void setKills(String uuid, Integer kills)
    {
        if (playerExists(uuid))
        {
            MySQL.update("UPDATE BedwarsStatsAPI SET KILLS='" + kills + "' WHERE UUID='" + uuid + "'");
        }
        else
        {
            createPlayer(uuid);
            setKills(uuid, kills);
        }
    }

    public static void setDeaths(String uuid, Integer deaths)
    {
        if (playerExists(uuid))
        {
            MySQL.update("UPDATE BedwarsStatsAPI SET DEATHS='" + deaths + "' WHERE UUID='" + uuid + "'");
        }
        else
        {
            createPlayer(uuid);
            setDeaths(uuid, deaths);
        }
    }

    public static void setWins(String uuid, Integer wins)
    {
        if (playerExists(uuid))
        {
            MySQL.update("UPDATE BedwarsStatsAPI SET WIN='" + wins + "' WHERE UUID='" + uuid + "'");
        }
        else
        {
            createPlayer(uuid);
            setWins(uuid, wins);
        }
    }

    public static void setDestroyedBeds(String uuid, Integer beds) {
        if(playerExists(uuid)) {
            MySQL.update("UPDATE BedwarsStatsAPI SET BEDS='" + beds + "'WHERE UUID='" + uuid + "'");
        } else {
            createPlayer(uuid);
            setDestroyedBeds(uuid, beds);
        }
    }

    public static void setLose(String uuid, Integer lose)
    {
        if (playerExists(uuid))
        {
            MySQL.update("UPDATE BedwarsStatsAPI SET LOSE='" + lose + "' WHERE UUID='" + uuid + "'");
        }
        else
        {
            createPlayer(uuid);
            setLose(uuid, lose);
        }
    }


    public static void addKills(String uuid, Integer kills)
    {
        if (playerExists(uuid))
        {
            setKills(uuid, Integer.valueOf(getKills(uuid).intValue() + kills.intValue()));
        }
        else
        {
            createPlayer(uuid);
            addKills(uuid, kills);
        }
    }

    public static void addDeaths(String uuid, Integer deaths)
    {
        if (playerExists(uuid))
        {
            setDeaths(uuid, Integer.valueOf(getDeaths(uuid).intValue() + deaths.intValue()));
        }
        else
        {
            createPlayer(uuid);
            addDeaths(uuid, deaths);
        }
    }

    public static void addWin(String uuid, Integer wins)
    {
        if (playerExists(uuid))
        {
            setWins(uuid, Integer.valueOf(getWins(uuid).intValue() + wins.intValue()));
        }
        else
        {
            createPlayer(uuid);
            addWin(uuid, wins);
        }
    }

    public static void addLose(String uuid, Integer lose)
    {
        if (playerExists(uuid))
        {
            setLose(uuid, Integer.valueOf(getLoses(uuid).intValue() + lose.intValue()));
        }
        else
        {
            createPlayer(uuid);
            addLose(uuid, lose);
        }
    }

    public static void addDestroyedBed(String uuid, Integer bed) {
        if(playerExists(uuid)) {
            setDestroyedBeds(uuid, Integer.valueOf(getDestroyedBeds(uuid).intValue() + bed.intValue()));
        } else {
            createPlayer(uuid);
            addDestroyedBed(uuid, bed);
        }
    }


    public static void removeKills(String uuid, Integer kills)
    {
        if (playerExists(uuid))
        {
            setKills(uuid, Integer.valueOf(getKills(uuid).intValue() - kills.intValue()));
        }
        else
        {
            createPlayer(uuid);
            removeKills(uuid, kills);
        }
    }

    public static void removeDeaths(String uuid, Integer deaths)
    {
        if (playerExists(uuid))
        {
            setKills(uuid, Integer.valueOf(getDeaths(uuid).intValue() - deaths.intValue()));
        }
        else
        {
            createPlayer(uuid);
            removeDeaths(uuid, deaths);
        }
    }

    public static void removeDestroyedBeds(String uuid, Integer beds) {
        if(playerExists(uuid)) {
            setDestroyedBeds(uuid, Integer.valueOf(getDestroyedBeds(uuid).intValue() - beds.intValue()));
        } else {
            createPlayer(uuid);
            removeDestroyedBeds(uuid, beds);
        }
    }

    public static void removeWin(String uuid, Integer wins) {
        if (playerExists(uuid)) {
            setWins(uuid, Integer.valueOf(getWins(uuid).intValue() - wins.intValue()));
        }
        else {
            createPlayer(uuid);
            removeWin(uuid, wins);
        }
    }

    public static void removeLose(String uuid, Integer lose) {
        if (playerExists(uuid)) {
            setLose(uuid, Integer.valueOf(getLoses(uuid).intValue() - lose.intValue()));
        }
        else {
            createPlayer(uuid);
            removeLose(uuid, lose);
        }
    }
}