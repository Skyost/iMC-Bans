package com.skyost.imcbans;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;
import com.skyost.imcbans.list;

public class playerListener implements Listener {
	 private final list plugin;
	 String playerlogin = null;
	 String bl = null;

	    public playerListener(list instance) {
	        plugin = instance;
	    }
	    
	    @EventHandler
	    public void onPlayerJoin(PlayerJoinEvent event) {
		    	plugin.getLogger().info("[iMC Bans] Recherche de " + event.getPlayer().getName() + " dans notre base de donnees...");
		    	playerlogin = event.getPlayer().getName();
		    	readFile("list.txt");
		    	 if(bl.contains(playerlogin)){
		    		 System.out.println("[iMC Bans] " + event.getPlayer().getName() + " est contenu dans notre base de donnee !!");
		    		 event.getPlayer().kickPlayer("Ce serveur utilise iMC Bans v0.2 www.imcbans.cu.cc");
		    		 event.getPlayer().setBanned(true);
		    		 System.out.println("[iMC Bans] Nous avons bannis " + event.getPlayer().getName() + ".");
		    	 }
		    	 else {
		    		 System.out.println("[iMC Bans] " + event.getPlayer().getName() + " n'est pas contenu dans notre base de donnee.");
		    	 }
	    }
	    
	    public void readFile(String file) {
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
}
