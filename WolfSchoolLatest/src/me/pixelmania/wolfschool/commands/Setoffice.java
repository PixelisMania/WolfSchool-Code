package me.pixelmania.wolfschool.commands;

import org.bukkit.Location;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import me.pixelmania.wolfschool.main.Core;
import me.pixelmania.wolfschool.utils.ChatFormat;

public class Setoffice implements CommandExecutor {

	@Override
	public boolean onCommand(CommandSender sender, Command cmd, String string, String[] args) {
		if (sender instanceof Player) {
			Player player = (Player) sender;
			if (player.hasPermission("wolfschool.setoffice")) {
				Location location = player.getLocation();
				Core.plugin.getConfig().set("office-location.world", location.getWorld().getName());
				Core.plugin.getConfig().set("office-location.x", location.getX());
				Core.plugin.getConfig().set("office-location.y", location.getY());
				Core.plugin.getConfig().set("office-location.z", location.getZ());
				Core.plugin.getConfig().set("office-location.yaw", location.getYaw());
				Core.plugin.getConfig().set("office-location.pitch", location.getPitch());
				Core.plugin.saveConfig();
				player.sendMessage(ChatFormat.colors(Core.plugin.getConfig().getString("messages.office-set")));
			} else {
				player.sendMessage(ChatFormat.colors(Core.plugin.getConfig().getString("messages.no-permission")));
			}
		} else {
			sender.sendMessage("[WolfSchool] This command can only be used by players!");
		}
		return true;
	}

}
