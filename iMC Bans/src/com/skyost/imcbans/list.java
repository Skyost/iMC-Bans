package com.skyost.imcbans;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.PrintStream;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;
import java.util.Scanner;
import java.util.logging.Level;

import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.plugin.PluginManager;
import org.bukkit.plugin.java.JavaPlugin;
import com.skyost.imcbans.playerListener;


public class list extends JavaPlugin{
	public String s = null;
	public String versiontxt = null;
	public iMCBConfig config;
	public Boolean autoUpdate = null;
	private final playerListener playerListener = new playerListener(this);

	public void onEnable() {
		  try {
			    System.setOut(new PrintStream(System.out, true, "cp850"));
	            config = new iMCBConfig(this);
	            config.init();
	        } catch(Exception ex) {
	            getLogger().log(Level.SEVERE, "[iMC Bans] Impossible de charger la configuration :/", ex);
	            getServer().getPluginManager().disablePlugin(this);
	            return;
	    }
        autoUpdate = config.autoUpdateList;
		if(autoUpdate==true) {
			System.out.println("[iMC Bans] Téléchargement du fichier list.txt...");
			UrlUtils("http://www.imcbans.cu.cc/list.txt");
			System.out.println("[iMC Bans] Lecture du fichier list.txt :");
			readList("list.txt");
		}
		else {
			System.out.println("[iMC Bans] Pas de téléchargement du fichier list.txt :/");
		}
    	PluginManager pm = getServer().getPluginManager();
    	pm.registerEvents(playerListener, this);
		System.out.println("[iMC Bans] Prêt à bannir les griefers ;)");
		System.out.println("[iMC Bans] Visitez http://www.imcbans.cu.cc/ pour plus d'informations.");
	}

	public void UrlUtils(String HOST) {
		try {
			URL racine = new URL(HOST);
			getFile(racine);
		} catch (MalformedURLException e) {
			System.err.println("[iMC Bans] " + HOST + " : " + "Mauvaise URL, site peut-être down ?");
		} catch (IOException e) {
			System.err.println("[iMC Bans] " + e);
		}
	}

	public void getFile(URL u) throws IOException {
		URLConnection uc = u.openConnection();
		InputStream in = uc.getInputStream();
		String FileName = u.getFile();
		FileName = FileName.substring(FileName.lastIndexOf('/') + 1);
		FileOutputStream WritenFile = new FileOutputStream(FileName);
		byte[]buff = new byte[1024];
		int l = in.read(buff);
		while(l>0)
		{
		WritenFile.write(buff, 0, l);
		l = in.read(buff);
		}
		WritenFile.flush();
		WritenFile.close();
	}

	public void readList(String file) {
		Scanner sc;
		try {
			sc = new Scanner(new File(file));
			while (sc.hasNextLine())
			{
			    s = sc.nextLine();
			    System.out.println(s);
			}
			sc.close();
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
	}

	public void readVersion(String file) {
		Scanner sc;
		try {
			sc = new Scanner(new File(file));
			while (sc.hasNextLine())
			{
			    versiontxt = sc.nextLine();
			}
			sc.close();
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
	}

	public void deleteFile(String file) {
		File filename = new File(file);
		filename.delete();
	}

	public void onDisable() {
		System.out.println("[iMC Bans] À plus tard !");
	}

	public void checkUpdate() {
		System.out.println("[iMC Bans Update] Recherche de mises à jours...");
		UrlUtils("http://www.imcbans.cu.cc/version.txt");
		readVersion("version.txt");
		if(versiontxt.equals("iMC Bans 0.4")) {
			System.out.println("[iMC Bans Update] Votre installation est parfaitement à jour ;)");
			deleteFile("version.txt");
		}
		else {
			System.out.println("[iMC Bans Update] Vous possèdez la version 0.4 tandis que la version la plus récente est" + versiontxt + " :s");
			System.out.println("[iMC Bans Update] Consultez http://www.imcbans.cu.cc pour plus d'informations ;)");
			deleteFile("version.txt");
		}
	}

	@Override
	public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args){
		Player player = null;

		if (sender instanceof Player) {
			player = (Player) sender;
		}

		if(cmd.getName().equalsIgnoreCase("imcbip") && args.length == 1){
			Player s = (Player) sender;
		    Player target = Bukkit.getPlayer(args[0]);
		    String ip = target.getAddress().getHostName(); //TODO: Fix :/
		    String demandeur = s.getName();
		    String playerip = target.getName();
			if (sender instanceof Player) {
				if(player.hasPermission("imcbans.player.getip")) {
					sender.sendMessage("[iMC Bans] L'adresse IP de " + playerip +" est " + ip);
					System.out.println("[iMC Bans] " + demandeur + " à obtenu l'adresse ip a partir de iMC Bans du joueur : " + playerip + " qui est " + ip);
				}
				else {
					sender.sendMessage("[iMC Bans] Vous n'avez pas la permission d'éxecuter cette commande :/");
				}
			}
			else {
				System.out.println("[iMC Bans] L'adresse IP de " + playerip +" est " + ip);
			}
		}

		if(cmd.getName().equalsIgnoreCase("imcbdl")) {
			if (sender instanceof Player) {
				if(player.hasPermission("imcbans.player.dllist")) {
					sender.sendMessage("[iMC Bans] Téléchargement du fichier list.txt...");
					UrlUtils("http://www.imcbans.cu.cc/list.txt");
					sender.sendMessage("[iMC Bans] Succès :D");
				}
				else {
					sender.sendMessage("[iMC Bans] Vous n'avez pas la permission d'éxecuter cette commande :/");
				}
			}
			else { 
				System.out.println("[iMC Bans] Téléchargement du fichier list.txt...");
				UrlUtils("http://www.imcbans.cu.cc/list.txt");
				System.out.println("[iMC Bans] Lecture du fichier list.txt :");
				readList("list.txt");
			}
		}

		if(cmd.getName().equalsIgnoreCase("imcbchk")) {
			if (sender instanceof Player) {
				if(player.hasPermission("imcbans.player.checkforupdate")) {
					sender.sendMessage("[iMC Bans Update] Recherche de mises à jours...");
					UrlUtils("http://www.imcbans.cu.cc/version.txt");
					readVersion("version.txt");
						if(versiontxt.equals("iMC Bans 0.4")) {
							sender.sendMessage("[iMC Bans Update] Votre installation est parfaitement à jour ;)");
							deleteFile("version.txt");
						}
						else {
							sender.sendMessage("[iMC Bans Update] Vous possèdez la version 0.4 tandis que la version la plus récente est" + versiontxt + " :s");
							sender.sendMessage("[iMC Bans Update] Consultez http://www.imcbans.cu.cc pour plus d'informations ;)");
							deleteFile("version.txt");
						}
					}
			else {
					sender.sendMessage("[iMC Bans Update] Vous n'avez pas la permission d'éxecuter cette commande :/");
				}
			}
		else {
			checkUpdate();
			}
		}

		if(cmd.getName().equalsIgnoreCase("imcbver")) {
			if (sender instanceof Player) {
				if(player.hasPermission("imcbans.player.getversion")) {
					sender.sendMessage("[iMC Bans] Utilisation de iMC Bans v0.4");
					}
				else {
					sender.sendMessage("[iMC Bans] Vous n'avez pas la permission d'éxecuter cette commande :/");
					}
		}
		    else
		    	System.out.println("[iMC Bans] Utilisation de iMC Bans v0.4");
		}
		return true;

	}

}
