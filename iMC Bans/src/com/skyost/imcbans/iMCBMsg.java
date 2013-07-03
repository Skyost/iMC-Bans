package com.skyost.imcbans;

import java.io.File;

import org.bukkit.plugin.Plugin;

public class iMCBMsg extends Config {
	public iMCBMsg(Plugin plugin) {
		CONFIG_FILE = new File(plugin.getDataFolder(), "messages.yml");
		CONFIG_HEADER = "iMC Bans Messages";
	}
	public String Message_1 = "Downloading file list.txt from www.imc-bans.eu...";
	public String Message_2 = "Reading list.txt :";
	public String Message_3 = "Ready to ban griefers ;)";
	public String Message_4 = "Visit http://www.imc-bans.eu/ for more informations.";
	public String Message_5 = "Error when sending statistics :";
	public String Message_6 = "Bad URL, website probably down ? Try go to www.imc-bans.eu with your browser !";
	public String Message_7 = "Goodbye !";
	public String Message_8 = "Searching for updates...";
	public String Message_9 = "Your installation is completely up to date ;)";
	public String Message_10 = "You have the version /VersionA/ while the latest version is /VersionB/ :s";
	public String Message_11 = "Check http://www.imc-bans.eu for more information ;)";
	public String Message_12 = "Please specify a player name !";
	public String Message_13 = "The IP adress of /Player/ is /Ip/.";
	public String Message_14 = "/Sender/ got the ip adresse from iMC Bans of : /Player/ which is /Ip/.";
	public String Message_15 = "Username invalid when ordering /imcbip !";
	public String Message_16 = "Username invalid when ordering /imcbip used by /Player/.";
	public String Message_17 = "Player not found !";
	public String Message_18 = "You did not enter a player name !";
	public String Message_19 = "/Player/ is already banned !";
	public String Message_20 = "We have banned /Player/ with the reason '/Reason/'.";
	public String Message_21 = "Success :D";
	public String Message_22 = "Using iMC Bans v/Version/";
	public String Message_23 = "Searching for /Player/ in our database...";
	public String Message_24 = "/Player/ is contained in our database !!";
	public String Message_25 = "/Player/ is not included in our database.";
	public String Message_26 = "Please use this command from the console only !";
	public String Message_27 = "You do not have permission to execute this command :/";
	public String Message_28 = "This server use iMC Bans www.imc-bans.eu";
	public String Message_29 = "Incorrect arguments !";
	
	public String Update_1 = "Update found: The updater found an update, and has readied it to be loaded the next time the server restarts/reloads.";
	public String Update_2 = "No Update: The updater did not find an update, and nothing was downloaded. Check your version with /imcbver and if it's up to date with /imcbchk.";
	public String Update_3 = "Download Failed: The updater found an update, but was unable to download it.";
	public String Update_4 = "dev.bukkit.org Failed: For some reason, the updater was unable to contact DBO to download the file.";
	public String Update_5 = "No version found: When running the version check, the file on DBO did not contain the a version in the format 'vVersion' such as 'v1.0'.";
	public String Update_6 = "Bad slug: The slug provided by the plugin running the updater was invalid and doesn't exist on DBO.";
	public String Update_7 = "Update found: There was an update found but not be downloaded !";
}
