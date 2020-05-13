package me.pixelmania.wolfschool.tabcompleters;

import java.util.ArrayList;
import java.util.List;

import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.command.TabCompleter;
import org.bukkit.entity.Player;

public class TabAddclass implements TabCompleter {

	@Override
	public List<String> onTabComplete(CommandSender sender, Command cmd, String string, String[] args) {
		if (sender instanceof Player) {
			Player player = (Player) sender;
			if (!player.hasPermission("wolfschool.addclass")) {
				return new ArrayList<String>();
			} else {
				if (args.length > 1) {
					return new ArrayList<String>();
				}
			}
		}
		return null;
	}

}
