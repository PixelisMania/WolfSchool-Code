package me.pixelmania.wolfschool.commands;

import java.util.List;

import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import me.pixelmania.wolfschool.main.Core;
import me.pixelmania.wolfschool.utils.ChatFormat;

public class Removeclass implements CommandExecutor {

	@Override
	public boolean onCommand(CommandSender sender, Command cmd, String string, String[] args) {
		if (sender instanceof Player) {
			Player player = (Player) sender;
			if (player.hasPermission("wolfschool.removeclass")) {
				if (args.length > 0) {
					for (String classes : Core.plugin.getConfig().getStringList("class-regions")) {
						List<String> classList = Core.plugin.getConfig().getStringList("class-regions");
						String[] className = classes.split(":");
						if (className[0].equalsIgnoreCase(args[0])) {
							classList.remove(classes);
							Core.plugin.getConfig().set("class-regions", classList);
							Core.plugin.saveConfig();
							player.sendMessage(ChatFormat.colors(Core.plugin.getConfig().getString("messages.removed-class").replace("</classname>", args[0])));
							return true;
						}
					}
					player.sendMessage(ChatFormat.colors(Core.plugin.getConfig().getString("messages.class-doesnt-exist")));
				} else {
					player.sendMessage(ChatFormat.colors(Core.plugin.getConfig().getString("messages.invalid-usage").replace("</usage>", "/removeclass <classname>")));
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
