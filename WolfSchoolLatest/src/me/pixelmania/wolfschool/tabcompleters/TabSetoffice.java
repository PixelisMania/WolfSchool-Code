package me.pixelmania.wolfschool.tabcompleters;

import java.util.ArrayList;
import java.util.List;

import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.command.TabCompleter;

public class TabSetoffice implements TabCompleter {

	@Override
	public List<String> onTabComplete(CommandSender sender, Command cmd, String string, String[] args) {
		return new ArrayList<String>();
	}

}
