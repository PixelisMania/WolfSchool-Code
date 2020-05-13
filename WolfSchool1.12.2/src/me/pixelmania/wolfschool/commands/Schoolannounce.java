package me.pixelmania.wolfschool.commands;

import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import me.pixelmania.wolfschool.main.Core;
import me.pixelmania.wolfschool.utils.ChatFormat;

public class Schoolannounce implements CommandExecutor {

	@Override
	public boolean onCommand(CommandSender sender, Command cmd, String string, String[] args) {
		if (sender instanceof Player) {
			Player player = (Player) sender;
			if (player.hasPermission("wolfschool.announce")) {
				if (args.length > 1) {
					String message = "";
					for (int i = 1; i != args.length; i++) {
						if (message.isEmpty()) {
							message = args[i];
						} else {
							message += " " + args[i];
						}
					}
					if (args[0].equalsIgnoreCase("chat")) {
						for (Player onlinePlayer : Bukkit.getServer().getOnlinePlayers()) {
							onlinePlayer.sendMessage(ChatFormat.colors(Core.plugin.getConfig().getString("messages.schoolannounce-chat-format").replace("</announcement>", ChatFormat.colors(message))));
						}
					} else if (args[0].equalsIgnoreCase("title")) {
						for (Player onlinePlayer : Bukkit.getServer().getOnlinePlayers()) {
							onlinePlayer.sendTitle(ChatFormat.colors(Core.plugin.getConfig().getString("messages.schoolannounce-title-format")), ChatFormat.colors(message), 20, 60, 20);
						}
					} else {
						player.sendMessage(ChatFormat.colors(Core.plugin.getConfig().getString("messages.invalid-usage").replace("</usage>", "/schoolannounce <chat/title> <message>")));
					}
				} else {
					player.sendMessage(ChatFormat.colors(Core.plugin.getConfig().getString("messages.invalid-usage").replace("</usage>", "/schoolannounce <chat/title> <message>")));
				}
			} else {
				player.sendMessage(ChatFormat.colors(Core.plugin.getConfig().getString("messages.no-permission")));
			}
		} else {
			sender.sendMessage("[WolfSchool] This command can only be used by players!");
		}
		return true;
	}

}
