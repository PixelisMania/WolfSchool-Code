package me.pixelmania.wolfschool.commands;

import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import me.pixelmania.wolfschool.functions.TeacherChat;
import me.pixelmania.wolfschool.main.Core;
import me.pixelmania.wolfschool.utils.ChatFormat;

public class Tc implements CommandExecutor {

	@Override
	public boolean onCommand(CommandSender sender, Command cmd, String string, String[] args) {
		if (sender instanceof Player) {
			Player player = (Player) sender;
			if (player.hasPermission("wolfschool.teacherchat")) {
					if (args.length == 1 && args[0].equalsIgnoreCase("toggle")) {
						if (player.hasPermission("wolfschool.teacherchat.toggle")) {
							if (Core.teacherToggleChat.contains(player.getUniqueId())) {
								Core.teacherToggleChat.remove(player.getUniqueId());
								player.sendMessage(ChatFormat.colors(Core.plugin.getConfig().getString("messages.teacher-chatting-toggled").replace("</status>", "off")));
							} else {
								Core.teacherToggleChat.add(player.getUniqueId());
								player.sendMessage(ChatFormat.colors(Core.plugin.getConfig().getString("messages.teacher-chatting-toggled").replace("</status>", "on")));
							}
						} else {
							player.sendMessage(ChatFormat.colors(Core.plugin.getConfig().getString("messages.no-permission")));
						}
					} else if (args.length > 0) {
						String message = args[0];
						for (int i = 1; i != args.length; i++) {
							message += " " + args[i];
						}
						TeacherChat.sendMessage(player, message);
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
