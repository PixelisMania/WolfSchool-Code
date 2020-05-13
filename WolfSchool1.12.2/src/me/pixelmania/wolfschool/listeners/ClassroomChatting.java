package me.pixelmania.wolfschool.listeners;

import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.AsyncPlayerChatEvent;

import com.sk89q.worldguard.protection.regions.ProtectedRegion;

import me.pixelmania.wolfschool.main.Core;
import me.pixelmania.wolfschool.utils.ChatFormat;

public class ClassroomChatting implements Listener {
	
	@EventHandler
	public void onClassroomChatting(AsyncPlayerChatEvent event) {
		Player player = event.getPlayer();
		if (player.hasPermission("wolfschool.teacher") || player.hasPermission("wolfschool.student")) {
			if (!Core.teacherToggleChat.contains(player.getUniqueId())) {
				if (!Core.plugin.getConfig().getStringList("class-regions").isEmpty()) {
					for (String string : Core.plugin.getConfig().getStringList("class-regions")) {
						String[] strings = string.split(":");
						if (Core.startedClasses.contains(strings[0].toLowerCase())) {
							ProtectedRegion region = Core.WG.getRegionContainer().get(player.getWorld()).getRegion(strings[1]);
							if (region != null) {
								Location location = player.getLocation();
								int x = location.getBlockX();
								int y = location.getBlockY();
								int z = location.getBlockZ();
								if (region.contains(x, y, z)) {
									event.setCancelled(true);
									for (Player sendPlayer : Bukkit.getServer().getOnlinePlayers()) {
										if (sendPlayer.getLocation().getWorld() == location.getWorld()) {
											Location sendLocation = sendPlayer.getLocation();
											int sendX = sendLocation.getBlockX();
											int sendY = sendLocation.getBlockY();
											int sendZ = sendLocation.getBlockZ();
											if (region.contains(sendX, sendY, sendZ)) {
												sendPlayer.sendMessage(ChatFormat.colors(Core.plugin.getConfig().getString("messages.classroom-chat-format").replace("</classname>", strings[0]).replace("</player>", player.getName()).replace("</message>", ChatFormat.stripColors(event.getMessage()))));
												return;
											}
										}
									}
								}
							}
						}
					}
				}
			}
		}
	}
	
}
