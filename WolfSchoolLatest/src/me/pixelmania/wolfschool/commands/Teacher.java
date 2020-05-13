package me.pixelmania.wolfschool.commands;

import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import me.pixelmania.wolfschool.main.Core;
import me.pixelmania.wolfschool.utils.ChatFormat;
import ru.tehkode.permissions.bukkit.PermissionsEx;

public class Teacher implements CommandExecutor {

	@SuppressWarnings("deprecation")
	@Override
	public boolean onCommand(CommandSender sender, Command cmd, String string, String[] args) {
		if (sender instanceof Player) {
			Player player = (Player) sender;
			if (player.hasPermission("wolfschool.teacherpromote")) {
				if (args.length > 2) {
					if (Bukkit.getServer().getPlayer(args[0]) != null) {
						Player teacherPlayer = Bukkit.getServer().getPlayer(args[0]);
						if (args[1].equalsIgnoreCase("promote") && args[2].equalsIgnoreCase("teacher")) {
							if (PermissionsEx.getUser(teacherPlayer).inGroup("teacher")) {
								player.sendMessage(ChatFormat.colors(Core.plugin.getConfig().getString("messages.already-teacher").replace("</player>", teacherPlayer.getName())));
							} else {
								PermissionsEx.getUser(teacherPlayer).addGroup("teacher");
								PermissionsEx.getUser(teacherPlayer).save();
								player.sendMessage(ChatFormat.colors(Core.plugin.getConfig().getString("messages.promoted-teacher").replace("</player>", teacherPlayer.getName())));
							}
						} else if (args[1].equalsIgnoreCase("demote") && args[2].equalsIgnoreCase("teacher")) {
							if (!PermissionsEx.getUser(teacherPlayer).inGroup("teacher")) {
								player.sendMessage(ChatFormat.colors(Core.plugin.getConfig().getString("messages.not-a-teacher").replace("</player>", teacherPlayer.getName())));
							} else {
								PermissionsEx.getUser(teacherPlayer).removeGroup("teacher");
								PermissionsEx.getUser(teacherPlayer).save();
								player.sendMessage(ChatFormat.colors(Core.plugin.getConfig().getString("messages.demoted-teacher").replace("</player>", teacherPlayer.getName())));
							}
						} else {
							player.sendMessage(ChatFormat.colors(Core.plugin.getConfig().getString("messages.invalid-usage").replace("</usage>", "/teacher <player> promote/demote teacher")));
						}
					} else {
						player.sendMessage(ChatFormat.colors(Core.plugin.getConfig().getString("messages.invalid-player")));
					}
				} else {
					if (args.length > 0) {
						if (Bukkit.getServer().getPlayer(args[0]) == null) {
							player.sendMessage(ChatFormat.colors(Core.plugin.getConfig().getString("messages.invalid-player")));
						} else {
							player.sendMessage(ChatFormat.colors(Core.plugin.getConfig().getString("messages.invalid-usage").replace("</usage>", "/teacher <player> promote/demote teacher")));
						}
					} else {
						player.sendMessage(ChatFormat.colors(Core.plugin.getConfig().getString("messages.invalid-usage").replace("</usage>", "/teacher <player> promote/demote teacher")));
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
