package me.pixelmania.wolfschool.commands;

import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.World;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import me.pixelmania.wolfschool.main.Core;
import me.pixelmania.wolfschool.utils.ChatFormat;

public class Sendoffice implements CommandExecutor {

	@SuppressWarnings("deprecation")
	@Override
	public boolean onCommand(CommandSender sender, Command cmd, String string, String[] args) {
		if (sender instanceof Player) {
			Player player = (Player) sender;
			if (player.hasPermission("wolfschool.sendoffice")) {
				if (args.length == 0) {
					player.sendMessage(ChatFormat.colors(Core.plugin.getConfig().getString("messages.invalid-usage").replace("</usage>", "/sendoffice <player>")));
				} else {
					if (Core.plugin.getConfig().getString("office-location") == null || Core.plugin.getConfig().getString("office-location").isEmpty()) {
						player.sendMessage(ChatFormat.colors(Core.plugin.getConfig().getString("messages.office-not-set")));
					} else {
						if (Bukkit.getServer().getPlayer(args[0]) != null) {
							Player officePlayer = Bukkit.getServer().getPlayer(args[0]);
							World world = Bukkit.getServer().getWorld(Core.plugin.getConfig().getString("office-location.world"));
							double x = Core.plugin.getConfig().getDouble("office-location.x");
							double y = Core.plugin.getConfig().getDouble("office-location.y");
							double z = Core.plugin.getConfig().getDouble("office-location.z");
							float yaw = (float) Core.plugin.getConfig().getDouble("office-location.yaw");
							float pitch = (float) Core.plugin.getConfig().getDouble("office-location.pitch");
							Location location = new Location(world, x, y, z, yaw, pitch);
							try {
								officePlayer.teleport(location);
								officePlayer.sendMessage(ChatFormat.colors(Core.plugin.getConfig().getString("messages.sent-to-office").replace("</player>", player.getName())));
							} catch (Exception exception) {
								exception.printStackTrace();
							}
						} else {
							player.sendMessage(ChatFormat.colors(Core.plugin.getConfig().getString("messages.invalid-player")));
						}
					}
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
