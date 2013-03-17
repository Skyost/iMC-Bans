package com.skyost.imcbans;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Scanner;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;
import com.skyost.imcbans.list;

public class playerListener implements Listener {
	 private final list plugin;
	 String playerlogin = null;
	 String bl = null;
	 String banlist = null;
	 
	    public playerListener(list instance) {
	        plugin = instance;
	    }
	    
	    @EventHandler
	    public void onPlayerJoin(PlayerJoinEvent event) {
		    	plugin.getLogger().info("[iMC Bans] Recherche de " + event.getPlayer().getName() + " dans notre base de donnees...");
		    	playerlogin = event.getPlayer().getName();
		    	readList("list.txt");
		    		if(bl.contains(playerlogin)) {
		    			System.out.println("[iMC Bans] " + event.getPlayer().getName() + " est contenu dans notre base de donnee !!");
		    			event.getPlayer().kickPlayer("Ce serveur utilise iMC Bans www.imcbans.cu.cc");
		    			readBanList("banned-players.txt");
		    		 		if(banlist.contains(playerlogin)) {
		    		 			System.out.println("[iMC Bans] " + event.getPlayer().getName() + " est deja bannis !");
		    		 		}
		    		 		else {
		    		 			writeFile("banned-players.txt", event.getPlayer().getName() + "|2012-12-21 00:00:00 +0100|(Unknown)|Forever|Ce serveur utilise iMC Bans www.imcbans.cu.cc");
				    		 	System.out.println("[iMC Bans] Nous avons bannis " + event.getPlayer().getName() + ".");
		    		 		}
		    		}
		    	 else {
		    		 System.out.println("[iMC Bans] " + event.getPlayer().getName() + " n'est pas contenu dans notre base de donnee.");
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
	    		System.out.println("Message : "+ioe.getMessage()); 
	    	} 
	    }
}
