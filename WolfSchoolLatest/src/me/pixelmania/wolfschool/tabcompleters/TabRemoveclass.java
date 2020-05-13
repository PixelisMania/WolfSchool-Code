package me.pixelmania.wolfschool.tabcompleters;

import java.util.ArrayList;
import java.util.List;

import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.command.TabCompleter;
import org.bukkit.entity.Player;

import me.pixelmania.wolfschool.main.Core;

public class TabRemoveclass implements TabCompleter {

	@Override
	public List<String> onTabComplete(CommandSender sender, Command cmd, String string, String[] args) {
		if (sender instanceof Player) {
			Player player = (Player) sender;
			if (player.hasPermission("wolfschool.removeclass")) {
				if (args.length == 1) {
					List<String> list = new ArrayList<String>();
					for (String className : Core.plugin.getConfig().getStringList("class-regions")) {
						String[] classNames = className.split(":");
						list.add(classNames[0]);
					}
					return list;
				}
			} else {
				return new ArrayList<String>();
			}
		} else {
			return new ArrayList<String>();
		}
		return new ArrayList<String>();
	}

}
