package me.pixelmania.wolfschool.tabcompleters;

import java.util.ArrayList;
import java.util.List;

import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.command.TabCompleter;
import org.bukkit.entity.Player;

import me.pixelmania.wolfschool.main.Core;

public class TabClass implements TabCompleter {

	@Override
	public List<String> onTabComplete(CommandSender sender, Command cmd, String string, String[] args) {
		if (sender instanceof Player) {
			Player player = (Player) sender;
			if (args.length == 1) {
				List<String> list = new ArrayList<String>();
				if (player.hasPermission("wolfschool.classstart")) {
					list.add("start");
				}
				if (player.hasPermission("wolfschool.classend")) {
					list.add("end");
				}
				if (player.hasPermission("wolfschool.classlist")) {
					list.add("list");
				}
				return list;
			} else if (args.length == 2) {
				if (args[0].equalsIgnoreCase("start")) {
					if (player.hasPermission("wolfschool.classstart")) {
						List<String> list = new ArrayList<String>();
						for (String className : Core.plugin.getConfig().getStringList("class-regions")) {
							String[] classNames = className.split(":");
							if (!Core.startedClasses.contains(classNames[0])) {
								list.add(classNames[0]);
							}
						}
						return list;
					} else {
						return new ArrayList<String>();
					}
				} else if (args[0].equalsIgnoreCase("end")) {
					if (player.hasPermission("wolfschool.classend")) {
						List<String> list = new ArrayList<String>();
						for (String className : Core.plugin.getConfig().getStringList("class-regions")) {
							String[] classNames = className.split(":");
							if (Core.startedClasses.contains(classNames[0])) {
								list.add(classNames[0]);
							}
						}
						return list;
					} else {
						return new ArrayList<String>();
					}
				} else {
					return new ArrayList<String>();
				}
			} else if (args.length > 2) {
				return new ArrayList<String>();
			}
		} else {
			return new ArrayList<String>();
		}
		return null;
	}

}
