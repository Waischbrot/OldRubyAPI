package de.rubymc.rubyapi.commands;

import de.rubymc.rubyapi.Main;
import de.rubymc.rubyapi.api.CoinsAPI;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.entity.Player;

public class Rubine implements CommandExecutor {

    FileConfiguration config = Main.plugin.getConfig();

    public boolean onCommand(CommandSender sender, Command command, String arg2, String[] args) {

        if (sender instanceof Player) {

            Player player = (Player) sender;
            String prefix = ChatColor.translateAlternateColorCodes('&', config.getString("messages.prefix"));

            if (player.hasPermission("rubyapi.coinadmin")) {

                Player target;

                if (args.length == 3) {

                    target = Bukkit.getPlayerExact(args[1]);
                    int coins = Integer.valueOf(args[2]);

                    if (args[0].equalsIgnoreCase("add")) {
                        if (coins <= 0) {
                            player.sendMessage(prefix + "§cGib bitte einen höheren Betrag an!");
                            return true;
                        }

                        CoinsAPI.addCoins(target.getUniqueId().toString(), coins);
                        target.sendMessage(prefix + "Dir wurden §c" + coins + " Rubine §7hinzugefügt.");
                        player.sendMessage(prefix + "Dem Spieler §b" + target.getName() + " §7wurden §c" + coins + " Rubine §7hinzugefügt.");
                    }
                    else if (args[0].equalsIgnoreCase("set")) {

                        if (coins < 0) {
                            player.sendMessage(prefix + "§cGib bitte einen höheren Betrag an!");
                            return true;
                        }

                        CoinsAPI.setCoins(target.getUniqueId().toString(), coins);
                        target.sendMessage(prefix + "Deine §cRubine §7wurden auf §c" + coins + " §7gesetzt.");
                        player.sendMessage(prefix + "Der Spieler §b" + target.getName() + " §7wurde auf §c" + coins + " Rubine §7gesetzt.");
                    }
                    else if (args[0].equalsIgnoreCase("remove")) {

                        if (coins <= 0) {
                            player.sendMessage(prefix + "§cGib bitte einen höheren Betrag an!");
                            return true;
                        }

                        CoinsAPI.removeCoins(target.getUniqueId().toString(), coins);
                        target.sendMessage(prefix + "Dir wurden §c" + coins + " Rubine §7Rubine entfernt.");
                        player.sendMessage(prefix + "Dem Spieler §b" + target.getName() + " §7wurden §c" + coins + " Rubine §7entfernt.");
                    }
                }
                else if (args.length == 1) {
                    if (args[0].equalsIgnoreCase("?")) {
                        player.sendMessage(prefix + "/rubine ?");
                        player.sendMessage(prefix + "/rubine help");
                        player.sendMessage(prefix + "/rubine <add/set/remove> <Name> <Rubine>");
                        player.sendMessage(prefix + "/rubine see <Name>");
                        player.sendMessage(prefix + "/rubine");
                    }
                    else if (args[0].equalsIgnoreCase("help")) {
                        player.sendMessage(prefix + "/rubine ?");
                        player.sendMessage(prefix + "/rubine help");
                        player.sendMessage(prefix + "/rubine <add/set/remove> <Name> <Rubine>");
                        player.sendMessage(prefix + "/rubine see <Name>");
                        player.sendMessage(prefix + "/rubine");
                    }
                }
                else if (args.length == 2) {
                    if (args[0].equalsIgnoreCase("see")) {
                        target = Bukkit.getPlayerExact(args[1]);
                        player.sendMessage(prefix + "Der Spieler §b" + target.getName() + " §7hat §c" + CoinsAPI.getCoins(target.getUniqueId().toString()) + " Rubine§7.");
                    }
                }
                else {
                    player.sendMessage(prefix + "Du besitzt momentan §c" + CoinsAPI.getCoins(player.getUniqueId().toString()) + " Rubine§7!");
                }
            }
            else {
                player.sendMessage(prefix + "Du besitzt momentan §c" + CoinsAPI.getCoins(player.getUniqueId().toString()) + " Rubine§7!");
            }
        }
        return false;
    }
}
