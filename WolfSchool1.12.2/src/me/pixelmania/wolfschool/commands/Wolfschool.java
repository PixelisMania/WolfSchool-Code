package me.pixelmania.wolfschool.commands;

import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import me.pixelmania.wolfschool.main.Core;
import me.pixelmania.wolfschool.utils.ChatFormat;

public class Wolfschool implements CommandExecutor {

	@Override
	public boolean onCommand(CommandSender sender, Command cmd, String string, String[] args) {
		if (sender instanceof Player) {
			Player player = (Player) sender;
			if (player.hasPermission("wolfschool.reload")) {
				Core.plugin.reloadConfig();
				player.sendMessage(ChatFormat.colors(Core.plugin.getConfig().getString("messages.reloaded-config")));
			} else {
				player.sendMessage(ChatFormat.colors(Core.plugin.getConfig().getString("messages.no-permission")));
			}
		} else {
			Core.plugin.reloadConfig();
			sender.sendMessage(ChatFormat.stripColors(Core.plugin.getConfig().getString("messages.reloaded-config")));
		}
		return true;
	}

}
