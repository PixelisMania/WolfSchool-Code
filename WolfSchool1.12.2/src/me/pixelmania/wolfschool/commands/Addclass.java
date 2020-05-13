package me.pixelmania.wolfschool.commands;

import java.util.List;

import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import com.sk89q.worldguard.protection.regions.ProtectedRegion;

import me.pixelmania.wolfschool.main.Core;
import me.pixelmania.wolfschool.utils.ChatFormat;

public class Addclass implements CommandExecutor {

	@Override
	public boolean onCommand(CommandSender sender, Command cmd, String string, String[] args) {
		if (sender instanceof Player) {
			Player player = (Player) sender;
			if (player.hasPermission("wolfschool.addclass")) {
				if (args.length > 0) {
					if (!Core.WG.getRegionContainer().get(player.getWorld()).getRegions().isEmpty()) {
						for (String key : Core.WG.getRegionContainer().get(player.getWorld()).getRegions().keySet()) {
							ProtectedRegion region = Core.WG.getRegionContainer().get(player.getWorld()).getRegion(key);
							int x = player.getLocation().getBlockX();
							int y = player.getLocation().getBlockY();
							int z = player.getLocation().getBlockZ();
							if (region.contains(x, y, z)) {
								List<String> classList = Core.plugin.getConfig().getStringList("class-regions");
								classList.add(args[0] + ":" + key);
								Core.plugin.getConfig().set("class-regions", classList);
								Core.plugin.saveConfig();
								player.sendMessage(ChatFormat.colors(Core.plugin.getConfig().getString("messages.added-class").replace("</region>", key).replace("</classname>", args[0])));
							} else {
								player.sendMessage(ChatFormat.colors(Core.plugin.getConfig().getString("messages.not-in-region")));
							}
						}
					} else {
						player.sendMessage(ChatFormat.colors(Core.plugin.getConfig().getString("messages.not-in-region")));
					}
				} else {
					player.sendMessage(ChatFormat.colors(Core.plugin.getConfig().getString("messages.invalid-usage").replace("</usage>", "/addclass <classname>")));
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
