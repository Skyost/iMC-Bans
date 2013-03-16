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
	private final playerListener playerListener = new playerListener(this);

	public void onEnable(){
        PluginManager pm = getServer().getPluginManager();
        pm.registerEvents(playerListener, this);
		System.out.println("[iMC Bans] Telechargment du fichier list.txt...)");
		UrlUtils("http://www.imcbans.cu.cc/list.txt");
		System.out.println("[iMC Bans] Lecture du fichier list.txt...)");
		readFile("list.txt");
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
	
	public void readFile(String file) {
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
	
	public void onDisable(){
		System.out.println("[iMC Bans] A plus tard !");
	}
	
	@SuppressWarnings("unused")
	@Override
	public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args){
		Player player = null;
		
		if (sender instanceof Player) {
			player = (Player) sender;
		}
		
		if(cmd.getName().equalsIgnoreCase("imcbdl")){
			onEnable();
		}

		if(cmd.getName().equalsIgnoreCase("imcbver")){
			sender.sendMessage("iMC Bans version 0.2");
		}
		return true;

	}

}
