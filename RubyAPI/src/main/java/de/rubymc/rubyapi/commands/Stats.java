package de.rubymc.rubyapi.commands;

import de.rubymc.rubyapi.Main;
import de.rubymc.rubyapi.api.BedwarsStatsAPI;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.entity.Player;

public class Stats implements CommandExecutor {

    FileConfiguration config = Main.plugin.getConfig();

    @Override
    public boolean onCommand(CommandSender commandSender, Command command, String arg2, String[] args) {
        if (commandSender instanceof Player) {

            Player player = (Player) commandSender;
            String prefix = ChatColor.translateAlternateColorCodes('&', config.getString("messages.prefix"));

            if (player.hasPermission("rubyapi.statsadmin")) {

                Player target;

                if (args.length == 5) {

                    target = Bukkit.getPlayerExact(args[1]);
                    int value = Integer.valueOf(args[4]);
                    String gamemode = String.valueOf(args[2]);
                    String stat = String.valueOf(args[3]);

                    if (args[0].equalsIgnoreCase("add")) {

                        if (value <= 0) {
                            player.sendMessage(prefix + "§cGib bitte einen höheren Betrag an!");

                            return true;
                        }

                        //Beispiel Bedwarsstats
                        if (gamemode.equalsIgnoreCase("BEDWARS")) {

                            if (stat.equalsIgnoreCase("BEDS")) {

                                BedwarsStatsAPI.addDestroyedBed(target.getUniqueId().toString(), value);

                                if (!player.getName().equals(target.getName())) {

                                    player.sendMessage(prefix + "Dem Spieler §b" + target.getName() + " §7wurden §e" + value + " zerstörte Betten §7hinzugefügt.");

                                }
                                target.sendMessage(prefix + "Dir wurden §e" + value + " abgebaute Betten §7hinzugefügt. §8(§c§lBed§f§lWars§8)");

                            }
                            else if (stat.equalsIgnoreCase("WINS")) {

                                BedwarsStatsAPI.addWin(target.getUniqueId().toString(), value);

                                if (!player.getName().equals(target.getName())) {

                                    player.sendMessage(prefix + "Dem Spieler §b" + target.getName() + " §7wurden §e" + value + " gewonnene Spiele §7hinzugefügt.");

                                }
                                target.sendMessage(prefix + "Dir wurden §e" + value + " gewonnene Spiele §7hinzugefügt. §8(§c§lBed§f§lWars§8)");

                            }
                            else if (stat.equalsIgnoreCase("LOSES")) {

                                BedwarsStatsAPI.addLose(target.getUniqueId().toString(), value);

                                if (!player.getName().equals(target.getName())) {

                                    player.sendMessage(prefix + "Dem Spieler §b" + target.getName() + " §7wurden §e" + value + " verlorene Spiele §7hinzugefügt.");

                                }
                                target.sendMessage(prefix + "Dir wurden §e" + value + " verlorene Spiele §7hinzugefügt. §8(§c§lBed§f§lWars§8)");

                            }
                            else if (stat.equalsIgnoreCase("KILLS")) {

                                BedwarsStatsAPI.addKills(target.getUniqueId().toString(), value);

                                if (!player.getName().equals(target.getName())) {

                                    player.sendMessage(prefix + "Dem Spieler §b" + target.getName() + " §7wurden §e" + value + " Kills §7hinzugefügt.");

                                }
                                target.sendMessage(prefix + "Dir wurden §e" + value + " Kills §7hinzugefügt. §8(§c§lBed§f§lWars§8)");

                            }
                            else if (stat.equalsIgnoreCase("DEATHS")) {

                                BedwarsStatsAPI.addDeaths(target.getUniqueId().toString(), value);

                                if (!player.getName().equals(target.getName())) {

                                    player.sendMessage(prefix + "Dem Spieler §b" + target.getName() + " §7wurden §e" + value + " Tode §7hinzugefügt.");

                                }
                                target.sendMessage(prefix + "Dir wurden §e" + value + " Tode §7hinzugefügt. §8(§c§lBed§f§lWars§8)");

                            }
                            else {

                                player.sendMessage(prefix + "Verfügbare Statistiken für §aBEDWARS§7:");
                                player.sendMessage(prefix + "§aWINS");
                                player.sendMessage(prefix + "§aLOSES");
                                player.sendMessage(prefix + "§aBEDS");
                                player.sendMessage(prefix + "§aKILLS");
                                player.sendMessage(prefix + "§aDEATHS");

                            }
                        }
                        else {

                            player.sendMessage(prefix + "Verfügbare Spielmodi:");
                            player.sendMessage(prefix + "§aBEDWARS");

                        }
                    }
                    else if (args[0].equalsIgnoreCase("set")) {

                        if (value < 0) {
                            player.sendMessage(prefix + "§cGib bitte einen höheren Betrag an!");

                            return true;
                        }

                        //Bedwarsstats
                        if (gamemode.equalsIgnoreCase("BEDWARS")) {

                            if (stat.equalsIgnoreCase("WINS")) {

                                BedwarsStatsAPI.setWins(target.getUniqueId().toString(), value);

                                if (!player.getName().equals(target.getName())) {

                                    player.sendMessage(prefix + "Du hast die §egewonnenen Spiele §7von §b" + target.getName() + " §7auf §e" + value + " §7gesetzt.");

                                }
                                target.sendMessage(prefix + "Deine §egewonnenen Spiele §7wurden auf §e" + value + " §7gesetzt. §8(§c§lBed§f§lWars§8)");

                            }
                            else if (stat.equalsIgnoreCase("LOSES")) {

                                BedwarsStatsAPI.setLose(target.getUniqueId().toString(), value);

                                if (!player.getName().equals(target.getName())) {

                                    player.sendMessage(prefix + "Du hast die §everlorenen Spiele §7von §b" + target.getName() + " §7auf §e" + value + " §7gesetzt.");

                                }
                                target.sendMessage(prefix + "Deine §everlorenen Spiele §7wurden auf §e" + value + " §7gesetzt. §8(§c§lBed§f§lWars§8)");

                            }
                            else if (stat.equalsIgnoreCase("BEDS")) {

                                BedwarsStatsAPI.setDestroyedBeds(target.getUniqueId().toString(), value);

                                if (!player.getName().equals(target.getName())) {

                                    player.sendMessage(prefix + "Du hast die §ezerstörten Betten §7von §b" + target.getName() + " §7auf §e" + value + " §7gesetzt.");

                                }
                                target.sendMessage(prefix + "Deine §ezerstörten Betten §7wurden auf §e" + value + " §7gesetzt. §8(§c§lBed§f§lWars§8)");

                            }
                            else if (stat.equalsIgnoreCase("KILLS")) {

                                BedwarsStatsAPI.setKills(target.getUniqueId().toString(), value);

                                if (!player.getName().equals(target.getName())) {

                                    player.sendMessage(prefix + "Du hast die §eKills §7von §b" + target.getName() + " §7auf §e" + value + " §7gesetzt.");

                                }
                                target.sendMessage(prefix + "Deine §eKills §7wurden auf §e" + value + " §7gesetzt. §8(§c§lBed§f§lWars§8)");

                            }
                            else if (stat.equalsIgnoreCase("DEATHS")) {

                                BedwarsStatsAPI.setDeaths(target.getUniqueId().toString(), value);

                                if (!player.getName().equals(target.getName())) {

                                    player.sendMessage(prefix + "Du hast die §eTode §7von §b" + target.getName() + " §7auf §e" + value + " §7gesetzt.");

                                }
                                target.sendMessage(prefix + "Deine §eTode §7wurden auf §e" + value + " §7gesetzt. §8(§c§lBed§f§lWars§8)");

                            }
                            else {

                                player.sendMessage(prefix + "Verfügbare Statistiken für §aBEDWARS§7:");
                                player.sendMessage(prefix + "§aWINS");
                                player.sendMessage(prefix + "§aLOSES");
                                player.sendMessage(prefix + "§aBEDS");
                                player.sendMessage(prefix + "§aKILLS");
                                player.sendMessage(prefix + "§aDEATHS");

                            }
                        }
                        else {

                            player.sendMessage(prefix + "Verfügbare Spielmodi:");
                            player.sendMessage(prefix + "§aBEDWARS");

                        }
                    }
                    else if (args[0].equalsIgnoreCase("remove")) {

                        if (value <= 0) {
                            player.sendMessage(prefix + "§cGib bitte einen höheren Betrag an!");

                            return true;
                        }

                        //Beispiel Bedwarsstats
                        if (gamemode.equalsIgnoreCase("BEDWARS")) {

                            if (stat.equalsIgnoreCase("WINS")) {

                                BedwarsStatsAPI.removeWin(target.getUniqueId().toString(), value);

                                if (!player.getName().equals(target.getName())) {

                                    player.sendMessage(prefix + "Dem Spieler §b" + target.getName() + " §7wurden §e" + value + " gewonnene Spiele §7entfernt.");

                                }
                                target.sendMessage(prefix + "Dir wurden §e" + value + " gewonnene Spiele §7entfernt. §8(§c§lBed§f§lWars§8)");

                            }
                            else if (stat.equalsIgnoreCase("LOSES")) {

                                BedwarsStatsAPI.removeLose(target.getUniqueId().toString(), value);

                                if (!player.getName().equals(target.getName())) {

                                    player.sendMessage(prefix + "Dem Spieler §b" + target.getName() + " §7wurden §e" + value + " verlorene Spiele §7entfernt.");

                                }
                                target.sendMessage(prefix + "Dir wurden §e" + value + " verlorene Spiele §7entfernt. §8(§c§lBed§f§lWars§8)");

                            }
                            else if (stat.equalsIgnoreCase("BEDS")) {

                                BedwarsStatsAPI.removeDestroyedBeds(target.getUniqueId().toString(), value);

                                if (!player.getName().equals(target.getName())) {

                                    player.sendMessage(prefix + "Dem Spieler §b" + target.getName() + " §7wurden §e" + value + " zerstörte Betten §7entfernt.");

                                }
                                target.sendMessage(prefix + "Dir wurden §e" + value + " zerstörte Betten §7entfernt. §8(§c§lBed§f§lWars§8)");

                            }
                            else if (stat.equalsIgnoreCase("KILLS")) {

                                BedwarsStatsAPI.removeKills(target.getUniqueId().toString(), value);

                                if (!player.getName().equals(target.getName())) {

                                    player.sendMessage(prefix + "Dem Spieler §b" + target.getName() + " §7wurden §e" + value + " Kills §7entfernt.");

                                }
                                target.sendMessage(prefix + "Dir wurden §e" + value + " Kills §7entfernt. §8(§c§lBed§f§lWars§8)");

                            }
                            else if (stat.equalsIgnoreCase("DEATHS")) {

                                BedwarsStatsAPI.removeDeaths(target.getUniqueId().toString(), value);

                                if (!player.getName().equals(target.getName())) {

                                    player.sendMessage(prefix + "Dem Spieler §b" + target.getName() + " §7wurden §e" + value + " Tode §7entfernt.");

                                }
                                target.sendMessage(prefix + "Dir wurden §e" + value + " Tode §7entfernt. §8(§c§lBed§f§lWars§8)");

                            }
                            else {

                                player.sendMessage(prefix + "Verfügbare Statistiken für §aBEDWARS§7:");
                                player.sendMessage(prefix + "§aWINS");
                                player.sendMessage(prefix + "§aLOSES");
                                player.sendMessage(prefix + "§aBEDS");
                                player.sendMessage(prefix + "§aKILLS");
                                player.sendMessage(prefix + "§aDEATHS");

                            }
                        }
                        else {

                            player.sendMessage(prefix + "Verfügbare Spielmodi:");
                            player.sendMessage(prefix + "§aBEDWARS");

                        }
                    }
                }
                else if (args.length == 4) {

                    player.sendMessage(prefix + "/stats help");
                    player.sendMessage(prefix + "/stats <add/set/remove> <Name> <Spielmodus> <Stat> <Anzahl>");
                    player.sendMessage(prefix + "/stats reset <Name> <Spielmodus>");
                    player.sendMessage(prefix + "/stats <Name>");
                    player.sendMessage(prefix + "/stats");

                }
                else if (args.length == 3) {

                    target = Bukkit.getPlayerExact(args[1]);
                    String gamemode = String.valueOf(args[2]);

                    if (args[0].equalsIgnoreCase("reset")) {

                        if (gamemode.equalsIgnoreCase("BEDWARS")) {

                            BedwarsStatsAPI.setLose(target.getUniqueId().toString(), 0);
                            BedwarsStatsAPI.setWins(target.getUniqueId().toString(), 0);
                            BedwarsStatsAPI.setDeaths(target.getUniqueId().toString(), 0);
                            BedwarsStatsAPI.setKills(target.getUniqueId().toString(), 0);
                            BedwarsStatsAPI.setDestroyedBeds(target.getUniqueId().toString(), 0);

                            player.sendMessage(prefix + "Du hast die §cBedwars §7Statistiken von §b" + target.getName() + " §7zurückgesetzt.");

                        } else {

                            player.sendMessage(prefix + "Verfügbare Spielmodi:");
                            player.sendMessage(prefix + "§aBEDWARS");

                        }
                    }
                }
                else if (args.length == 2) {

                    player.sendMessage(prefix + "/stats help");
                    player.sendMessage(prefix + "/stats <add/set/remove> <Name> <Spielmodus> <Stat> <Anzahl>");
                    player.sendMessage(prefix + "/stats reset <Name> <Spielmodus>");
                    player.sendMessage(prefix + "/stats <Name>");
                    player.sendMessage(prefix + "/stats");

                }
                else if (args.length == 1) {

                    if (args[0].equalsIgnoreCase("help")) {

                        player.sendMessage(prefix + "/stats help");
                        player.sendMessage(prefix + "/stats <add/set/remove> <Name> <Spielmodus> <Stat> <Anzahl>");
                        player.sendMessage(prefix + "/stats reset <Name> <Spielmodus>");
                        player.sendMessage(prefix + "/stats <Name>");
                        player.sendMessage(prefix + "/stats");

                        return true;
                    }
                    target = Bukkit.getPlayerExact(args[0]);

                    //Hier Spielerspezifisches Menü einbauen!
                    player.sendMessage("Test" + BedwarsStatsAPI.getDestroyedBeds(target.getUniqueId().toString()));

                }
                else {

                    player.sendMessage(prefix + "Das §cStatsmenü §7wurde für dich geöffnet!");

                }
            }
            else {

                player.sendMessage(prefix + "Das §cStatsmenü §7wurde für dich geöffnet!");

            }
        }

        return false;
    }
}