package com.skyost.imcbans;

import java.io.File;

import org.bukkit.plugin.Plugin;

public class iMCBConfig extends Config {
	public iMCBConfig(Plugin plugin) {
		CONFIG_FILE = new File(plugin.getDataFolder(), "config.yml");
		CONFIG_HEADER = "iMC Bans Configuration";
	}
	public String language = "EN";
	public boolean autoUpdateListOnStartup = true;
	public boolean showListOnStartup = true;
	public boolean checkUpdateOnStartup = false;
}
