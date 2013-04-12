package com.skyost.imcbans;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Scanner;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;
import com.skyost.imcbans.main;

public class playerListener implements Listener {
	 private final main plugin;
	 String playerlogin = null;
	 String bl = null;
	 String banlist = null;

	    public playerListener(main instance) {
	        plugin = instance;
	    }

	    @EventHandler
	    public void onPlayerJoin(PlayerJoinEvent event) {
		    	plugin.getLogger();
		    	playerlogin = event.getPlayer().getName();
		    	System.out.println("[iMC Bans] " + main.s26 + playerlogin + main.s27);
		    	readList("list.txt");
		    		if(bl.contains(playerlogin)) {
		    			System.out.println("[iMC Bans] " + playerlogin + main.s28);
		    			event.getPlayer().kickPlayer(main.kickbanMessage);
		    			readBanList("banned-players.txt");
		    		 		if(banlist.contains(playerlogin)) {
		    		 			System.out.println("[iMC Bans] " + playerlogin + main.s22);
		    		 		}
		    		 		else {
		    		 			writeFile("banned-players.txt", playerlogin + "|2012-12-21 00:00:00 +0100|(Unknown)|Forever|" + main.kickbanMessage);
				    		 	System.out.println("[iMC Bans] " + main.s23 + playerlogin + ".");
		    		 		}
		    		}
		    	 else {
		    		 System.out.println("[iMC Bans] " + playerlogin + main.s29);
		    	 }
	    }

	    public void readList(String file) {
			Scanner sc;
			try {
				sc = new Scanner(new File(file));
				while (sc.hasNextLine())
				{
				    bl = sc.nextLine();
				}
				sc.close();
			} catch (FileNotFoundException e) {
				e.printStackTrace();
			}
		}

	    public void readBanList(String file) {
				Scanner sc;
				try {
					sc = new Scanner(new File(file));
					while (sc.hasNextLine())
					{
					    banlist = sc.nextLine();
					}
					sc.close();
				} catch (FileNotFoundException e) {
					e.printStackTrace();
				}
		}

	    public void writeFile(String file, String ligne) {
	    	try { 
	    		FileWriter fw=new FileWriter(file,true); 
	    		fw.write(ligne); 
	    		fw.close(); 
	    		} 
	    		catch (IOException ioe) 
	    		{ 
	    		System.out.println("[iMC Bans] "+ioe.getMessage()); 
	    	} 
	    }
}