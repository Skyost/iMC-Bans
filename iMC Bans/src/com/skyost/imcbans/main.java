package com.skyost.imcbans;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileWriter;
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


public class main extends JavaPlugin {
	public String s = null;
	public String versiontxt = null;
	public iMCBConfig config;
	public String demandeur = null;
	public String banlist = null;
	public String banplayer = null;
	public static String s1 = null;
	public static String s2 = null;
	public static String s3 = null;
	public static String s4 = null;
	public static String s5 = null;
	public static String s6 = null;
	public static String s7 = null;
	public static String s8 = null;
	public static String s9 = null;
	public static String s10 = null;
	public static String s11 = null;
	public static String s12 = null;
	public static String s13 = null;
	public static String s14 = null;
	public static String s15 = null;
	public static String s16 = null;
	public static String s17 = null;
	public static String s18 = null;
	public static String s19 = null;
	public static String s20 = null;
	public static String s21 = null;
	public static String s22 = null;
	public static String s23 = null;
	public static String s24 = null;
	public static String s25 = null;
	public static String s26 = null;
	public static String s27 = null;
	public static String s28 = null;
	public static String s29 = null;
	public static String s30 = null;
	public static String permissionMessage = null;
	public static String kickbanMessage = null;
	private final playerListener playerListener = new playerListener(this);

	public void onEnable() {
		  try {
			    System.setOut(new PrintStream(System.out, true, "cp850"));
	            config = new iMCBConfig(this);
	            config.init();
	        } catch(Exception ex) {
	            getLogger().log(Level.SEVERE, "[iMC Bans] " + ex);
	            getServer().getPluginManager().disablePlugin(this);
	            return;
	    }
		setLanguage();
	    Boolean autoUpdate = config.autoUpdateListOnStartup;
		if(autoUpdate==true) {
			System.out.println("[iMC Bans] " + s1);
			UrlUtils("http://www.imcbans.cu.cc/list.txt");
			Boolean showList = config.showListOnStartup;
			if(showList == true) {
			System.out.println("[iMC Bans] " + s2);
			readList("list.txt");
			}
		}
    	PluginManager pm = getServer().getPluginManager();
    	pm.registerEvents(playerListener, this);
	    Boolean checkUpdate = config.checkUpdateOnStartup;
	    if(checkUpdate == true){
	    	checkUpdate();
	    }
		System.out.println("[iMC Bans] " + s3);
		System.out.println("[iMC Bans] " + s4);
		startMetricsLite();
	}
	
	public void startMetricsLite() {
		try {
		    MetricsLite metrics = new MetricsLite(this);
		    metrics.start();
		} catch (IOException e) {
			System.out.println("[iMC Bans] " + s5 + e);
		}
	}
	
	public void setLanguage() {
		String language = config.language;
		if(language.equals("EN")) {
			s1 = "Downloading file list.txt...";
			s2 = "Reading list.txt :";
			s3 = "Ready to ban griefers ;)";
			s4 = "Visit http://www.imcbans.cu.cc/ for more information.";
			s5 = "Error when sending statistics : ";
			s6 = "Bad URL, website probably down?";
			s7 = "Goodbye !";
			s8 = "Searching for updates...";
			s9 = "Your installation is completely up to date ;)";
			s10 = "You have the version 0.5.2 while the latest version is ";
			s11 = "Check http://www.imcbans.cu.cc for more information ;)";
			s12 = "Please specify a player name !";
			s13 = "The IP adress of ";
			s14 = " is ";
			s15 = " got the ip adresse from iMC Bans of : ";
			s16 = " which is ";
			s17 = "The IP adress of ";
			s18 = "Username invalid when ordering /imcbip !";
			s19 = "Username invalid when ordering /imcbip used by ";
			s20 = "Please enter a valid player name !";
			s21 = "You did not enter a player name !";
			s22 = " is already banned !";
			s23 = "We have banned ";
			s24 = "Success :D";
			s25 = "Using iMC Bans v0.5.2";
			s26 = "Searching for ";
			s27 = " in our database...";
			s28 = " is contained in our database !!";
			s29 = " is not included in our database.";
			s30 = " Two arguments only !";
			permissionMessage = "You do not have permission to execute this command :/";
			kickbanMessage = "This server use iMC Bans www.imcbans.cu.cc";
		}
		else if(language.equals("FR")) {
			s1 = "Téléchargement du fichier list.txt...";
			s2 = "Lecture du fichier list.txt :";
			s3 = "Prêt à bannir les griefers ;)";
			s4 = "Visitez http://www.imcbans.cu.cc/ pour plus d'informations.";
			s5 = "Erreur lors de l'envoi des statistiques : ";
			s6 = "Mauvaise URL, site peut-être down ?";
			s7 = "À plus tard !";
			s8 = "Recherche de mises à jours...";
			s9 = "Votre installation est parfaitement à jour ;)";
			s10 = "Vous possèdez la version 0.5.2 tandis que la version la plus récente est ";
			s11 = "Consultez http://www.imcbans.cu.cc pour plus d'informations ;)";
			s12 = "Veuillez spécifier un nom d'utilisateur !";
			s13 = "L'adresse IP de ";
			s14 = " est ";
			s15 = " a obtenu l'adresse ip à partir de iMC Bans du joueur : ";
			s16 = " qui est ";
			s17 = "L'adresse IP de ";
			s18 = "Nom d'utilisateur non-valide lors de la commande /imcbip !";
			s19 = "Nom d'utilisateur non-valide lors de la commande /imcbip exéctuée par ";
			s20 = "Veuillez saisir un nom de joueur valide !";
			s21 = "Vous n'avez pas saisi de nom !";
			s22 = " est déjà bannis !";
			s23 = "Nous avons bannis ";
			s24 = "Succès :D";
			s25 = "Utilisation de iMC Bans v0.5.2";
			s26 = "Recherche de ";
			s27 = " dans notre base de données...";
			s28 = " est contenu dans notre base de données !!";
			s29 = " n'est pas contenu dans notre base de données.";
			s30 = "Uniquement deux arguments !";
			permissionMessage = "Vous n'avez pas la permission d'éxecuter cette commande :/";
			kickbanMessage = "Ce serveur utilise iMC Bans www.imcbans.cu.cc";
		}
		else {
			System.out.println("[iMC Bans] ERROR WITH THE LANGUAGE CODE");
			onDisable();
		}
	}

	public void UrlUtils(String HOST) {
		try {
			URL racine = new URL(HOST);
			getFile(racine);
		} catch (MalformedURLException e) {
			System.err.println("[iMC Bans] " + HOST + " : " + s6);
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
		System.out.println("[iMC Bans] " + s7);
	}

	public void checkUpdate() {
		System.out.println("[iMC Bans Update] " + s8);
		UrlUtils("http://www.imcbans.cu.cc/version.txt");
		readVersion("version.txt");
		if(versiontxt.equals("iMC Bans 0.5.2")) {
			System.out.println("[iMC Bans Update] " + s9);
			deleteFile("version.txt");
		}
		else {
			System.out.println("[iMC Bans Update] " + s10 + versiontxt + " :s");
			System.out.println("[iMC Bans Update] " + s11);
			deleteFile("version.txt");
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
    
    

	@Override
	public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args){
		Player player = null;

		if (sender instanceof Player) {
			player = (Player) sender;
		}

		if(cmd.getName().equalsIgnoreCase("imcbip") && args.length == 1) {
			try {
		    Player target = Bukkit.getPlayerExact(args[0]);
		    String ip = target.getAddress().getHostName(); //TODO: Fix :/
		    String playerip = target.getName();
			if (sender instanceof Player) {
				if(player.hasPermission("imcbans.player.getip")) {
					Player s = (Player) sender;
				    String demandeur = s.getName();
				    if (target.equals("")) {
						sender.sendMessage("[iMC Bans] " + s12);
				    }
				    else {
						sender.sendMessage("[iMC Bans] " + s13 + playerip + s14 + ip);
						System.out.println("[iMC Bans] " + demandeur + s15 + playerip + s16 + ip);
				    }
				}
				else {
					sender.sendMessage("[iMC Bans] " + permissionMessage);
				}
			}
			else {
					System.out.println("[iMC Bans]  " + s17 + playerip + s14 + ip);
			}
		}
			catch (NullPointerException e) {
				if(sender instanceof Player) {
					System.out.println("[iMC Bans] " + s18);
				}
				else {
					System.out.println("[iMC Bans] " + s19 + demandeur);
				}
		}
	}
		
		if(cmd.getName().equalsIgnoreCase("imcbreason")) {
			if(args.length == 2) {
			if (sender instanceof Player) {
				if(player.hasPermission("imcbans.player.banplayer")) {
		    Player target = Bukkit.getPlayer(args[0]);
			try {
			    banplayer = target.getName();
				}
				catch(NullPointerException e) {
					sender.sendMessage("[iMC Bans] " + s20);
	 			}
		    String reason = args[1];
		    if(banplayer == null) {
	 			sender.sendMessage("[iMC Bans] " + s21);
		    }
		    else {
		    	readBanList("banned-players.txt");
		 		if(banlist.contains(banplayer)) {
		 			sender.sendMessage("[iMC Bans] " + banplayer + s22);
		 		}
		 		else {
	    			target.kickPlayer(reason);
	    			target.setBanned(true);
		 			sender.sendMessage("[iMC Bans] " + s23 + banplayer + ".");
		 		}
		    }
				}
				else {
					sender.sendMessage("[iMC Bans] " + permissionMessage);
				}
    		}
		else {
			Player target = Bukkit.getPlayer(args[0]);
			try {
		    banplayer = target.getName();
			}
			catch(NullPointerException e) {
		    	System.out.println("[iMC Bans] " + s20);
 			}
		    String reason = args[1];
		    if(banplayer == null) {
		    	System.out.println("[iMC Bans] " + s21);
		    }
		    else {
		    	readBanList("banned-players.txt");
		 		if(banlist.contains(banplayer)) {
		 			System.out.println("[iMC Bans] " + banplayer + s22);
		 		}
		 		else {
	    			target.kickPlayer(reason);
	    			target.setBanned(true);
		 			System.out.println("[iMC Bans] " + s23 + banplayer + ".");
		 		}
		    }
		}
		}
		}
		else {
	    	System.out.println("[iMC Bans] " + s30);
		}

		if(cmd.getName().equalsIgnoreCase("imcbdl")) {
			if (sender instanceof Player) {
				if(player.hasPermission("imcbans.player.dllist")) {
					sender.sendMessage("[iMC Bans] " + s1);
					UrlUtils("http://www.imcbans.cu.cc/list.txt");
					sender.sendMessage("[iMC Bans] " + s24);
				}
				else {
					sender.sendMessage("[iMC Bans] " + permissionMessage);
				}
			}
			else { 
				System.out.println("[iMC Bans] " + s1);
				UrlUtils("http://www.imcbans.cu.cc/list.txt");
				System.out.println("[iMC Bans] " + s2);
				readList("list.txt");
				System.out.println("[iMC Bans] " + s24);
			}
		}

		if(cmd.getName().equalsIgnoreCase("imcbchk")) {
			if (sender instanceof Player) {
				if(player.hasPermission("imcbans.player.checkforupdate")) {
					sender.sendMessage("[iMC Bans Update] " + s8);
					UrlUtils("http://www.imcbans.cu.cc/version.txt");
					readVersion("version.txt");
						if(versiontxt.equals("iMC Bans 0.5.2")) {
							sender.sendMessage("[iMC Bans Update] " + s9);
							deleteFile("version.txt");
						}
						else {
							sender.sendMessage("[iMC Bans Update] " + s10 + versiontxt + " :s");
							sender.sendMessage("[iMC Bans Update] " + s11);
							deleteFile("version.txt");
						}
					}
			else {
					sender.sendMessage("[iMC Bans Update] " + permissionMessage);
				}
			}
		else {
			checkUpdate();
			}
		}

		if(cmd.getName().equalsIgnoreCase("imcbver")) {
			if (sender instanceof Player) {
				if(player.hasPermission("imcbans.player.getversion")) {
					sender.sendMessage("[iMC Bans] " + s25);
					}
				else {
					sender.sendMessage("[iMC Bans] " + permissionMessage);
					}
		}
		    else
		    	System.out.println("[iMC Bans] " + s25);
		}
		return true;

	}

}
