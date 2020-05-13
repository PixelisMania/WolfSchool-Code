package me.pixelmania.wolfschool.main;

import java.io.File;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import org.bukkit.Bukkit;
import org.bukkit.command.CommandExecutor;
import org.bukkit.plugin.java.JavaPlugin;

import com.sk89q.worldguard.bukkit.WorldGuardPlugin;

import me.pixelmania.wolfschool.commands.Addclass;
import me.pixelmania.wolfschool.commands.Class;
import me.pixelmania.wolfschool.commands.Enroll;
import me.pixelmania.wolfschool.commands.Removeclass;
import me.pixelmania.wolfschool.commands.Schoolannounce;
import me.pixelmania.wolfschool.commands.Sendoffice;
import me.pixelmania.wolfschool.commands.Setoffice;
import me.pixelmania.wolfschool.commands.Tc;
import me.pixelmania.wolfschool.commands.Teacher;
import me.pixelmania.wolfschool.commands.Wolfschool;
import me.pixelmania.wolfschool.listeners.ClassroomChatting;
import me.pixelmania.wolfschool.listeners.TeacherChatting;
import me.pixelmania.wolfschool.tabcompleters.TabAddclass;
import me.pixelmania.wolfschool.tabcompleters.TabClass;
import me.pixelmania.wolfschool.tabcompleters.TabEnroll;
import me.pixelmania.wolfschool.tabcompleters.TabRemoveclass;
import me.pixelmania.wolfschool.tabcompleters.TabSchoolannounce;
import me.pixelmania.wolfschool.tabcompleters.TabSendoffice;
import me.pixelmania.wolfschool.tabcompleters.TabSetoffice;
import me.pixelmania.wolfschool.tabcompleters.TabTc;
import me.pixelmania.wolfschool.tabcompleters.TabTeacher;
import me.pixelmania.wolfschool.tabcompleters.TabWolfschool;

public class Core extends JavaPlugin {
	
	public static List<UUID> teacherToggleChat = new ArrayList<UUID>();
	public static List<String> startedClasses = new ArrayList<String>();
	
	public static WorldGuardPlugin WG;
	
	public static Core plugin;
	
	@Override
	public void onEnable() {
		Bukkit.getConsoleSender().sendMessage("[WolfSchool] Successfully enabled!");
		plugin = this;
		WG = (WorldGuardPlugin) Bukkit.getPluginManager().getPlugin("WorldGuard");
		Bukkit.getServer().getPluginManager().registerEvents(new ClassroomChatting(), this);
		Bukkit.getServer().getPluginManager().registerEvents(new TeacherChatting(), this);
		if (!new File(this.getDataFolder() + "/config.yml").exists()) {
			this.saveDefaultConfig();
		}
		this.getCommand("tc").setExecutor((CommandExecutor) new Tc());
		this.getCommand("addclass").setExecutor((CommandExecutor) new Addclass());
		this.getCommand("class").setExecutor((CommandExecutor) new Class());
		this.getCommand("setoffice").setExecutor((CommandExecutor) new Setoffice());
		this.getCommand("sendoffice").setExecutor((CommandExecutor) new Sendoffice());
		this.getCommand("teacher").setExecutor((CommandExecutor) new Teacher());
		this.getCommand("schoolannounce").setExecutor((CommandExecutor) new Schoolannounce());
		this.getCommand("enroll").setExecutor((CommandExecutor) new Enroll());
		this.getCommand("wolfschool").setExecutor((CommandExecutor) new Wolfschool());
		this.getCommand("removeclass").setExecutor((CommandExecutor) new Removeclass());
		this.getCommand("class").setTabCompleter(new TabClass());
		this.getCommand("teacher").setTabCompleter(new TabTeacher());
		this.getCommand("tc").setTabCompleter(new TabTc());
		this.getCommand("setoffice").setTabCompleter(new TabSetoffice());
		this.getCommand("sendoffice").setTabCompleter(new TabSendoffice());
		this.getCommand("schoolannounce").setTabCompleter(new TabSchoolannounce());
		this.getCommand("enroll").setTabCompleter(new TabEnroll());
		this.getCommand("wolfschool").setTabCompleter(new TabWolfschool());
		this.getCommand("addclass").setTabCompleter(new TabAddclass());
		this.getCommand("removeclass").setTabCompleter(new TabRemoveclass());
	}
	
}
