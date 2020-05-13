package me.pixelmania.wolfschool.commands;

import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import me.pixelmania.wolfschool.main.Core;
import me.pixelmania.wolfschool.utils.ChatFormat;
import ru.tehkode.permissions.bukkit.PermissionsEx;

public class Enroll implements CommandExecutor {

	@SuppressWarnings("deprecation")
	@Override
	public boolean onCommand(CommandSender sender, Command cmd, String string, String[] args) {
		if (sender instanceof Player) {
			Player player = (Player) sender;
			if (player.hasPermission("wolfschool.enroll")) {
				if (args.length == 0) {
					player.sendMessage(ChatFormat.colors(Core.plugin.getConfig().getString("messages.invalid-usage").replace("</usage>", "/enroll <player>")));
				} else {
						if (Bukkit.getServer().getPlayer(args[0]) != null) {
							Player enrollPlayer = Bukkit.getServer().getPlayer(args[0]);
							if (enrollPlayer.hasPermission("wolfschool.student")) {
								player.sendMessage(ChatFormat.colors(Core.plugin.getConfig().getString("messages.already-enrolled").replace("</player>", enrollPlayer.getName())));
							} else {
								PermissionsEx.getUser(enrollPlayer).addPermission("wolfschool.student");
								player.sendMessage(ChatFormat.colors(Core.plugin.getConfig().getString("messages.player-enrolled").replace("</player>", enrollPlayer.getName())));
							}
						} else {
							player.sendMessage(ChatFormat.colors(Core.plugin.getConfig().getString("messages.invalid-player")));
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
