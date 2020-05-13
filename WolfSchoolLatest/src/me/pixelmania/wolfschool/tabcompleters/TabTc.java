package me.pixelmania.wolfschool.tabcompleters;

import java.util.ArrayList;
import java.util.List;

import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.command.TabCompleter;
import org.bukkit.entity.Player;

public class TabTc implements TabCompleter {

	@Override
	public List<String> onTabComplete(CommandSender sender, Command cmd, String string, String[] args) {
		if (sender instanceof Player) {
			Player player = (Player) sender;
			if (player.hasPermission("wolfschool.teacherchat.toggle")) {
				if (args.length == 1) {
					List<String> list = new ArrayList<String>();
					list.add("toggle");
					return list;
				}
			}
		}
		return new ArrayList<String>();
	}

}
