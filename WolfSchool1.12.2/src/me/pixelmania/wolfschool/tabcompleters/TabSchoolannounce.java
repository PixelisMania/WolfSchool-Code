package me.pixelmania.wolfschool.tabcompleters;

import java.util.ArrayList;
import java.util.List;

import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.command.TabCompleter;
import org.bukkit.entity.Player;

public class TabSchoolannounce implements TabCompleter {

	@Override
	public List<String> onTabComplete(CommandSender sender, Command cmd, String string, String[] args) {
		if (sender instanceof Player) {
			Player player = (Player) sender;
			if (args.length == 1) {
				List<String> list = new ArrayList<String>();
				if (player.hasPermission("wolfschool.announce")) {
					list.add("chat");
					list.add("title");
				}
				return list;
			} else if (args.length > 1) {
				if (!args[0].equalsIgnoreCase("chat") && !args[0].equalsIgnoreCase("title")) {
					return new ArrayList<String>();
				}
			}
		}
		return new ArrayList<String>();
	}

}
