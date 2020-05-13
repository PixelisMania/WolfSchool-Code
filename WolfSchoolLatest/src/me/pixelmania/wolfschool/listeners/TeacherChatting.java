package me.pixelmania.wolfschool.listeners;

import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.AsyncPlayerChatEvent;

import me.pixelmania.wolfschool.functions.TeacherChat;
import me.pixelmania.wolfschool.main.Core;

public class TeacherChatting implements Listener {
	
	@EventHandler
	public void onTeacherChatting(AsyncPlayerChatEvent event) {
		Player player = event.getPlayer();
		if (Core.teacherToggleChat.contains(player.getUniqueId())) {
			event.setCancelled(true);
			TeacherChat.sendMessage(player, event.getMessage());
		}
	}
	
}
