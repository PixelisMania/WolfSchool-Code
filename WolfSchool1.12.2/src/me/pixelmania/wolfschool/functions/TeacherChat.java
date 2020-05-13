package me.pixelmania.wolfschool.functions;

import org.bukkit.Bukkit;
import org.bukkit.entity.Player;

import me.pixelmania.wolfschool.main.Core;
import me.pixelmania.wolfschool.utils.ChatFormat;

public class TeacherChat {
	
	public static void sendMessage(Player player, String message) {
		if (!Bukkit.getServer().getOnlinePlayers().isEmpty()) {
			for (Player sendPlayer : Bukkit.getServer().getOnlinePlayers()) {
				if (sendPlayer.hasPermission("wolfschool.teacherchat")) {
					sendPlayer.sendMessage(ChatFormat.colors(Core.plugin.getConfig().getString("messages.teacher-chat-format").replace("</player>", player.getName()).replace("</message>", ChatFormat.stripColors(message))));
				}
			}
		}
	}
	
}
