package me.pixelmania.wolfschool.tabcompleters;

import java.util.ArrayList;
import java.util.List;

import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.command.TabCompleter;
import org.bukkit.entity.Player;

public class TabTeacher implements TabCompleter {

	@Override
	public List<String> onTabComplete(CommandSender sender, Command cmd, String string, String[] args) {
		if (sender instanceof Player) {
			Player player = (Player) sender;
			if (player.hasPermission("wolfschool.teacherpromote")) {
				if (args.length == 2) {
					List<String> list = new ArrayList<String>();
					list.add("promote");
					list.add("demote");
					return list;
				} else if (args.length == 3) {
					if (args[1].equalsIgnoreCase("promote") || args[1].equalsIgnoreCase("demote")) {
						List<String> list = new ArrayList<String>();
						list.add("teacher");
						return list;
					} else {
						return new ArrayList<String>();
					}
				} else if (args.length > 3) {
					return new ArrayList<String>();
				}
			}
		}
		return null;
	}

}
