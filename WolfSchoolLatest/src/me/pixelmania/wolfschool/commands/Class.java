package me.pixelmania.wolfschool.commands;

import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import me.pixelmania.wolfschool.main.Core;
import me.pixelmania.wolfschool.utils.ChatFormat;

public class Class implements CommandExecutor {

	@Override
	public boolean onCommand(CommandSender sender, Command cmd, String string, String[] args) {
		if (sender instanceof Player) {
			Player player = (Player) sender;
			if (args.length > 1) {
				if (args[0].equalsIgnoreCase("start")) {
					if (player.hasPermission("wolfschool.classstart")) {
						if (Core.startedClasses.contains(args[1].toLowerCase())) {
							player.sendMessage(ChatFormat.colors(Core.plugin.getConfig().getString("messages.class-already-started")));
						} else {
							for (String classString : Core.plugin.getConfig().getStringList("class-regions")) {
								String[] classStrings = classString.split(":");
								if (args[1].equalsIgnoreCase(classStrings[0])) {
									Core.startedClasses.add(args[1].toLowerCase());
									player.sendMessage(ChatFormat.colors(Core.plugin.getConfig().getString("messages.class-started")));
									return true;
								}
							}
							player.sendMessage(ChatFormat.colors(Core.plugin.getConfig().getString("messages.class-doesnt-exist")));
						}
					} else {
						player.sendMessage(ChatFormat.colors(Core.plugin.getConfig().getString("messages.no-permission")));
					}
				} else if (args[0].equalsIgnoreCase("end")) {
					if (player.hasPermission("wolfschool.classend")) {
						if (Core.startedClasses.contains(args[1].toLowerCase())) {
							Core.startedClasses.remove(args[1].toLowerCase());
							player.sendMessage(ChatFormat.colors(Core.plugin.getConfig().getString("messages.class-ended")));
						} else {
							player.sendMessage(ChatFormat.colors(Core.plugin.getConfig().getString("messages.class-not-started")));
						}
					} else {
						player.sendMessage(ChatFormat.colors(Core.plugin.getConfig().getString("messages.no-permission")));
					}
				} else if (args[0].equalsIgnoreCase("list")) {
					if (player.hasPermission("wolfschool.classlist")) {
						String classes = "";
						for (String className : Core.plugin.getConfig().getStringList("class-regions")) {
							String[] classNames = className.split(":");
							if (classes.equals("")) {
								classes = classNames[0];
							} else {
								classes += "," + classNames[0];
							}
						}
						player.sendMessage(ChatFormat.colors(Core.plugin.getConfig().getString("messages.class-list").replace("</classes>", classes)));
					} else {
						player.sendMessage(ChatFormat.colors(Core.plugin.getConfig().getString("messages.no-permission")));
					}
				} else {
					player.sendMessage(ChatFormat.colors(Core.plugin.getConfig().getString("messages.invalid-usage").replace("</usage>", "/class <start/end/list> <classname>")));
				}
			} else if (args.length == 1) {
				if (args[0].equalsIgnoreCase("list")) {
					if (player.hasPermission("wolfschool.classlist")) {
						String classes = "";
						for (String className : Core.plugin.getConfig().getStringList("class-regions")) {
							String[] classNames = className.split(":");
							if (classes.equals("")) {
								classes = classNames[0];
							} else {
								classes += "," + classNames[0];
							}
						}
						player.sendMessage(ChatFormat.colors(Core.plugin.getConfig().getString("messages.class-list").replace("</classes>", classes)));
					} else {
						player.sendMessage(ChatFormat.colors(Core.plugin.getConfig().getString("messages.no-permission")));
					}
				} else {
					if (!player.hasPermission("wolfschool.classlist") && !player.hasPermission("wolfschool.classstart") && !player.hasPermission("wolfschool.classend")) {
						player.sendMessage(ChatFormat.colors(Core.plugin.getConfig().getString("messages.no-permission")));
					} else {
						player.sendMessage(ChatFormat.colors(Core.plugin.getConfig().getString("messages.invalid-usage").replace("</usage>", "/class <start/end/list> <classname>")));
					}
				}
			} else {
				if (!player.hasPermission("wolfschool.classlist") && !player.hasPermission("wolfschool.classstart") && !player.hasPermission("wolfschool.classend")) {
					player.sendMessage(ChatFormat.colors(Core.plugin.getConfig().getString("messages.no-permission")));
				} else {
					player.sendMessage(ChatFormat.colors(Core.plugin.getConfig().getString("messages.invalid-usage").replace("</usage>", "/class <start/end/list> <classname>")));
				}
			}
		} else {
			sender.sendMessage("[WolfSchool] This command can only be used by players!");
		}
		return true;
	}

}
