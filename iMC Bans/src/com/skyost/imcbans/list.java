package com.skyost.imcbans;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;
import java.util.Scanner;

import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.plugin.PluginManager;
import org.bukkit.plugin.java.JavaPlugin;
import com.skyost.imcbans.playerListener;


public class list extends JavaPlugin{
	public String s = null;
	public String versiontxt = null;
	private final playerListener playerListener = new playerListener(this);

	public void onEnable() {
        PluginManager pm = getServer().getPluginManager();
        pm.registerEvents(playerListener, this);
		System.out.println("[iMC Bans] Telechargement du fichier list.txt...");
		UrlUtils("http://www.imcbans.cu.cc/list.txt");
		System.out.println("[iMC Bans] Lecture du fichier list.txt :");
		readList("list.txt");
		System.out.println("[iMC Bans] Pret a bannir les griefers ;)");
		System.out.println("[iMC Bans] Visitez http://www.imcbans.cu.cc/ pour plus d'informations.");
	}
	
	public void UrlUtils(String HOST) {
		try {
			URL racine = new URL(HOST);
			getFile(racine);
		} catch (MalformedURLException e) {
			System.err.println("[iMC Bans] " + HOST + " : Mauvaise URL, site peut-etre down ?");
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
	
	public void onDisable(){
		System.out.println("[iMC Bans] A plus tard !");
	}
	
	public void checkUpdate() {
		System.out.println("[iMC Bans Update] Recherche de mises a jour...");
		UrlUtils("http://www.imcbans.cu.cc/version.txt");
		readVersion("version.txt");
		if(versiontxt.equals("iMC Bans 0.3.1")) {
			System.out.println("[iMC Bans Update] Votre installation est parfaitement a jour ;)");
			deleteFile("version.txt");
		}
		else {
			System.out.println("[iMC Bans Update] Vous possedez la version 0.3.1 tandis que la version la plus recente est " + versiontxt + " :s");
			System.out.println("[iMC Bans Update] Consultez http://www.imcbans.cu.cc pour plus d informations ;)");
			deleteFile("version.txt");
		}
	}
	
	@SuppressWarnings("unused")
	@Override
	public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {
		Player player = null;
		
		if (sender instanceof Player) {
			player = (Player) sender;
		}
		
		if(cmd.getName().equalsIgnoreCase("imcbip") && args.length == 1) {
			if(sender instanceof Player) {
				Player s = (Player)sender;
			    Player target = sender.getServer().getPlayer(args[0]);
			    String ip = target.getAddress().getHostName();
			    String demandeur = s.getName();
			    String playerip = target.getName();
				sender.sendMessage("L adresse IP de " + playerip + " est " + ip);
		        }
			else {
				Player s = (Player)sender;
			    Player target = sender.getServer().getPlayer(args[0]);
			    String ip = target.getAddress().getHostName();
			    String demandeur = s.getName();
			    String playerip = target.getName();
				System.out.println("[iMC Bans] " + demandeur + " a obtenu l adresse ip a partir de iMC Bans du joueur : " + playerip + " qui est " + ip); //TODO : Fix this method       
		        }
		}
		
		if(cmd.getName().equalsIgnoreCase("imcbdl")) {
			sender.sendMessage("Pour des raisons de sécurite, les resultats de cette operation sont affcihe uniqement dans la console");
			onEnable();
		}
		
		if(cmd.getName().equalsIgnoreCase("imcbchk")) {
			sender.sendMessage("Pour des raisons de sécurite, les resultats de cette operation sont affcihe uniqement dans la console");
			checkUpdate();
		}

		if(cmd.getName().equalsIgnoreCase("imcbver")) {
			sender.sendMessage("Utilisation de iMC Bans 0.3.1");
		}
		return true;

	}

}
