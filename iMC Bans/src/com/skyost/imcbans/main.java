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
	public static String s31 = null;
	public static String s32 = null;
	public static String s33 = null;
	public static String s34 = null;
	public static String s35 = null;
	public static String s36 = null;
	public static String s37 = null;
	public static String s38 = null;
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
			UrlUtils("http://www.imc-bans.eu/list.txt");
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
			s4 = "Visit http://www.imc-bans.eu/ for more information.";
			s5 = "Error when sending statistics : ";
			s6 = "Bad URL, website probably down?";
			s7 = "Goodbye !";
			s8 = "Searching for updates...";
			s9 = "Your installation is completely up to date ;)";
			s10 = "You have the version 0.5.3 while the latest version is ";
			s11 = "Check http://www.imc-bans.eu for more information ;)";
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
			s25 = "Using iMC Bans v0.5.3";
			s26 = "Searching for ";
			s27 = " in our database...";
			s28 = " is contained in our database !!";
			s29 = " is not included in our database.";
			s30 = " Two arguments only !";
			s31 = "Please use this command from the console only !";
			s32 = "Update found: The updater found an update, and has readied it to be loaded the next time the server restarts/reloads.";
			s33 = "No Update: The updater did not find an update, and nothing was downloaded. Check your version with /imcbver and if it's up to date with /imcbchk.";
			s34 = "Download Failed: The updater found an update, but was unable to download it.";
			s35 = "dev.bukkit.org Failed: For some reason, the updater was unable to contact DBO to download the file.";
			s36 = "No version found: When running the version check, the file on DBO did not contain the a version in the format 'vVersion' such as 'v1.0'.";
			s37 = "Bad slug: The slug provided by the plugin running the updater was invalid and doesn't exist on DBO.";
			s38 = "Update found: There was an update found but not be downloaded !";
			permissionMessage = "You do not have permission to execute this command :/";
			kickbanMessage = "This server use iMC Bans www.imc-bans.eu";
		}
		else if(language.equals("FR")) {
			s1 = "T�l�chargement du fichier list.txt...";
			s2 = "Lecture du fichier list.txt :";
			s3 = "Pr�t � bannir les griefers ;)";
			s4 = "Visitez http://www.imc-bans.eu/ pour plus d'informations.";
			s5 = "Erreur lors de l'envoi des statistiques : ";
			s6 = "Mauvaise URL, site peut-�tre down ?";
			s7 = "� plus tard !";
			s8 = "Recherche de mises � jours...";
			s9 = "Votre installation est parfaitement � jour ;)";
			s10 = "Vous poss�dez la version 0.5.3 tandis que la version la plus r�cente est ";
			s11 = "Consultez http://www.imc-bans.eu pour plus d'informations ;)";
			s12 = "Veuillez sp�cifier un nom d'utilisateur !";
			s13 = "L'adresse IP de ";
			s14 = " est ";
			s15 = " a obtenu l'adresse ip � partir de iMC Bans du joueur : ";
			s16 = " qui est ";
			s17 = "L'adresse IP de ";
			s18 = "Nom d'utilisateur non-valide lors de la commande /imcbip !";
			s19 = "Nom d'utilisateur non-valide lors de la commande /imcbip ex�ctu�e par ";
			s20 = "Veuillez saisir un nom de joueur valide !";
			s21 = "Vous n'avez pas saisi de nom !";
			s22 = " est d�j� bannis !";
			s23 = "Nous avons bannis ";
			s24 = "Succ�s :D";
			s25 = "Utilisation de iMC Bans v0.5.3";
			s26 = "Recherche de ";
			s27 = " dans notre base de donn�es...";
			s28 = " est contenu dans notre base de donn�es !!";
			s29 = " n'est pas contenu dans notre base de donn�es.";
			s30 = "Uniquement deux arguments !";
			s31 = "Veuillez utiliser cette commande depuis la console !";
			s32 = "Mise � jour trouv�e: L'updater a trouv� une mise � jour et elle sera active � votre prochain restart/reload ;)";
			s33 = "Pas de mise � jour: L'updater n'a trouv� aucune mise � jour. Verifiez votre version avec /imcbver et si il existe une mise � jour avec /imcbchk.";
			s34 = "Echec du t�l�chargement: L'updater a trouv� une mise � jour mais impossible de la t�l�charger.";
			s35 = "dev.bukkit.org Echec: Impossible de se connecter sur DBO.";
			s36 = "Echec de la v�rification: L'updater n'a pas trouv� de version avec pour mod�le 'vVersion' exemple : 'v1.0'.";
			s37 = "Mauvais slug: Le slug donn� par le plugin n'est pas valide et n'a pas �tait trouv� sur DBO.";
			s38 = "Mise � jour trouv�e: Une mise � jour a �tait trouv�e mais n'a pas �tait t�l�charg�e.";
			permissionMessage = "Vous n'avez pas la permission d'�xecuter cette commande :/";
			kickbanMessage = "Ce serveur utilise iMC Bans www.imc-bans.eu";
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
		UrlUtils("http://www.imc-bans.eu/version.txt");
		readVersion("version.txt");
		if(versiontxt.equals("iMC Bans 0.5.3")) {
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
    		System.out.println("[iMC Bans] " + ioe.getMessage()); 
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
		else {
	    	System.out.println("[iMC Bans] " + s30);
		}
		}
		
		if(cmd.getName().equalsIgnoreCase("imcbdl")) {
			if (sender instanceof Player) {
				if(player.hasPermission("imcbans.player.dllist")) {
					sender.sendMessage("[iMC Bans] " + s1);
					UrlUtils("http://www.imc-bans.eu/list.txt");
					sender.sendMessage("[iMC Bans] " + s24);
				}
				else {
					sender.sendMessage("[iMC Bans] " + permissionMessage);
				}
			}
			else { 
				System.out.println("[iMC Bans] " + s1);
				UrlUtils("http://www.imc-bans.eu/list.txt");
				System.out.println("[iMC Bans] " + s2);
				readList("list.txt");
				System.out.println("[iMC Bans] " + s24);
			}
		}
		
		if(cmd.getName().equalsIgnoreCase("imcbupdt")) {
			if (sender instanceof Player) {
				sender.sendMessage("[iMC Bans] " + s31);
			}
			else { 
				try {
					Updater updater = new Updater(this, "imc-bans", this.getFile(), Updater.UpdateType.DEFAULT, false);
					Updater.UpdateResult result = updater.getResult();
			        switch(result)
			        {
			            case SUCCESS:
							System.out.println("[iMC Bans] " + s32);
			                break;
			            case NO_UPDATE:
			            	System.out.println("[iMC Bans] " + s33);
			                break;
			            case FAIL_DOWNLOAD:
			            	System.out.println("[iMC Bans] " + s34);
			                break;
			            case FAIL_DBO:
			            	System.out.println("[iMC Bans] " + s35);
			                break;
			            case FAIL_NOVERSION:
			            	System.out.println("[iMC Bans] " + s36);
			                break;
			            case FAIL_BADSLUG:
			            	System.out.println("[iMC Bans] " + s37);
			                break;
			            case UPDATE_AVAILABLE:
			            	System.out.println("[iMC Bans] " + s38);
			        }
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		}

		if(cmd.getName().equalsIgnoreCase("imcbchk")) {
			if (sender instanceof Player) {
				if(player.hasPermission("imcbans.player.checkforupdate")) {
					sender.sendMessage("[iMC Bans Update] " + s8);
					UrlUtils("http://www.imc-bans.eu/version.txt");
					readVersion("version.txt");
						if(versiontxt.equals("iMC Bans 0.5.3")) {
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
