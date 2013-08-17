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
import java.util.Arrays;
import java.util.Scanner;
import java.util.logging.Level;

import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.plugin.java.JavaPlugin;


public class Main extends JavaPlugin implements Listener {
	public iMCBConfig config;
	public iMCBMsg messages;

	public void onEnable() {
		try {
			System.setOut(new PrintStream(System.out, true, "cp850"));
			config = new iMCBConfig(this);
			config.init();
			messages = new iMCBMsg(this);
			messages.init();
		}
		catch(Exception ex) {
	        getLogger().log(Level.SEVERE, "[iMC Bans] " + ex);
	        getServer().getPluginManager().disablePlugin(this);
	        return;
	    }
		if(config.autoUpdateListOnStartup == true) {
			updateList();
		}
    	getServer().getPluginManager().registerEvents(this, this);
	    if(config.checkForUpdateOnStartup == true){
	    	checkUpdate(true, "CONSOLE");
	    }
	    if(config.showListOnStartup == true) {
	    	readFile("list.txt");
	    }
		System.out.println("[iMC Bans] " + messages.Message_3);
		System.out.println("[iMC Bans] " + messages.Message_4);
		startMetricsLite();
	}
	
    @EventHandler
    public void onPlayerJoin(PlayerJoinEvent event) {
	    String playername = event.getPlayer().getName();
	    String messageSearch = messages.Message_23;
	    messageSearch = messageSearch.replaceAll("/Player/", playername);
	    System.out.println("[iMC Bans] " + messageSearch);
	    String list = getFile("list.txt");
	    if(list.contains(playername)) {
			String messageContainList = messages.Message_24;
			messageContainList = messageContainList.replaceAll("/Player/", playername);
	    	System.out.println("[iMC Bans] " + messageContainList);
	    	event.getPlayer().kickPlayer(messages.Message_28);
	    	String banlist = getFile("banned-players.txt");
	    	if(banlist.contains(playername)) {
	    		String messageContainBanList = messages.Message_19;
	    		messageContainBanList = messageContainBanList.replaceAll("/Player/", playername);
				System.out.println("[iMC Bans] " + messageContainBanList);
	    	}
	    	else {
	    		writeFile("banned-players.txt", playername + "|2012-12-21 00:00:00 +0100|(Unknown)|Forever|" + messages.Message_28);
	    		String messageBan = messages.Message_20;
	    		messageBan = messageBan.replaceAll("/Player/", playername);
	    		messageBan = messageBan.replaceAll("/Reason/", messages.Message_28);
			    System.out.println("[iMC Bans] " + messageBan);
	    	}
	    }
	    else {
	    	String message = messages.Message_25;
	    	message = message.replaceAll("/Player/", playername);
	    	System.out.println("[iMC Bans] " + message);
	    }
	}
	
	public void startMetricsLite() {
		try {
		    MetricsLite metrics = new MetricsLite(this);
		    metrics.start();
		} catch (IOException e) {
			System.out.println("[iMC Bans] " + messages.Message_5 + " " + e);
		}
	}
	
	public void updateList() {
		System.out.println("[iMC Bans] " + messages.Message_1);
		UrlUtils("http://www.imc-bans.eu/list.txt");
	}

	public void UrlUtils(String HOST) {
		try {
			URL racine = new URL(HOST);
			downloadFile(racine);
		}
		catch (MalformedURLException e) {
			System.err.println("[iMC Bans] " + HOST + " : " + messages.Message_6);
		}
		catch (IOException e) {
			System.err.println("[iMC Bans] " + e);
		}
	}

	public void downloadFile(URL u) throws IOException {
		URLConnection uc = u.openConnection();
		InputStream in = uc.getInputStream();
		String FileName = u.getFile();
		FileName = FileName.substring(FileName.lastIndexOf('/') + 1);
		FileOutputStream WritenFile = new FileOutputStream(FileName);
		byte[]buff = new byte[1024];
		int l = in.read(buff);
		while(l>0) {
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
				String s = null;
			    s = sc.nextLine();
			    System.out.println(s);
			}
			sc.close();
		}
		catch (FileNotFoundException e) {
			e.printStackTrace();
		}
	}

	public String getFile(String file) {
		Scanner sc;
		String contain = null;
		try {
			sc = new Scanner(new File(file));
			while (sc.hasNextLine())
			{
			    contain = sc.nextLine();
			}
			sc.close();
		}
		catch (FileNotFoundException e) {
			e.printStackTrace();
		}
		return contain;
	}

	public void deleteFile(String file) {
		File filename = new File(file);
		filename.delete();
	}

	public void onDisable() {
		System.out.println("[iMC Bans] " + messages.Message_7);
	}

	public void checkUpdate(boolean isConsole, String player) {
		if(isConsole == true) {
			System.out.println("[iMC Bans Update] " + messages.Message_8);
			UrlUtils("http://www.imc-bans.eu/version.txt");
			String version = getFile("version.txt");
			if(version.equals(this.getDescription().getName() + " " + this.getDescription().getVersion())) {
				System.out.println("[iMC Bans Update] " + messages.Message_9);
				deleteFile("version.txt");
			}
			else {
				String updateMessage = messages.Message_10;
				updateMessage = updateMessage.replaceAll("/VersionA/", this.getDescription().getName() + " " + this.getDescription().getVersion());
				updateMessage = updateMessage.replaceAll("/VersionB/", version);
				System.out.println("[iMC Bans Update] " + updateMessage);
				System.out.println("[iMC Bans Update] " + messages.Message_11);
				deleteFile("version.txt");
			}
		}
		else {
			Player sender = Bukkit.getPlayer(player);;
			System.out.println("[iMC Bans Update] " + messages.Message_8);
			UrlUtils("http://www.imc-bans.eu/version.txt");
			String version = getFile("version.txt");
			if(version.equals(this.getDescription().getName() + " " + this.getDescription().getVersion())) {
				sender.sendMessage("[iMC Bans Update] " + messages.Message_9);
				deleteFile("version.txt");
			}
			else {
				String updateMessage = messages.Message_10;
				updateMessage = updateMessage.replaceAll("/VersionA/", this.getDescription().getName() + " " + this.getDescription().getVersion());
				updateMessage = updateMessage.replaceAll("/VersionB/", version);
				sender.sendMessage("[iMC Bans Update] " + updateMessage);
				sender.sendMessage("[iMC Bans Update] " + messages.Message_11);
				deleteFile("version.txt");
			}
		}
	}
    
    public void writeFile(String file, String ligne) {
    	try { 
    		FileWriter fw=new FileWriter(file,true); 
    		fw.write(ligne); 
    		fw.close(); 
    	} 
    	catch (IOException ioe) { 
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
				Player target = Bukkit.getPlayer(args[0]);
				String ip = target.getAddress().getHostName();
				if(sender instanceof Player) {
					if(player.hasPermission("imcbans.player.getip")) {
						if(target.equals("")) {
							sender.sendMessage("[iMC Bans] " + messages.Message_12);
						}
				    	else {
				    		String messagePlayer = messages.Message_13;
				    		messagePlayer = messagePlayer.replaceAll("/Player/", target.getName());
				    		messagePlayer = messagePlayer.replaceAll("/Ip/", ip);
							sender.sendMessage("[iMC Bans] " + messagePlayer);
				    		String messageConsole = messages.Message_13;
				    		messageConsole = messageConsole.replaceAll("/Sender/", sender.getName());
				    		messageConsole = messageConsole.replaceAll("/Player/", target.getName());
				    		messageConsole = messageConsole.replaceAll("/Ip/", ip);
							System.out.println("[iMC Bans] " + messageConsole);
				    	}
					}
					else {
						sender.sendMessage("[iMC Bans] " + messages.Message_27);
					}
				}
				else {
					String message = messages.Message_13;
					message = message.replaceAll("/Player/", target.getName());
					message = message.replaceAll("/Ip/", ip);
					System.out.println("[iMC Bans]  " + message);
				}
			}
			catch (NullPointerException e) {
				if(sender instanceof Player) {
					sender.sendMessage("[iMC Bans] " + messages.Message_15);
				}
				else {
					System.out.println("[iMC Bans] " + messages.Message_15);
				}
			}
		}
		
		if(cmd.getName().equalsIgnoreCase("imcbreason")) {
			if(args.length >= 2) {
				if (sender instanceof Player) {
					if(player.hasPermission("imcbans.player.banplayer")) {
						Player targetPlayer = Bukkit.getPlayer(args[0]);
						try {
							String target = targetPlayer.getName();		
							String reason = null;
							reason = Arrays.toString(args).substring(1,  Arrays.toString(args).length() - 1);
							reason = reason.replaceAll(",", "");
							reason = reason.replaceAll(target + " ", "");
							if(target == null) {
								sender.sendMessage("[iMC Bans] " + messages.Message_18);
							}
							else {
								if(targetPlayer.isBanned() == true) {
									String message = messages.Message_19;
									message = message.replaceAll("/Player/", target);
									sender.sendMessage("[iMC Bans] " + message);
								}
								else {
									targetPlayer.kickPlayer(reason);
									targetPlayer.setBanned(true);
									String message = messages.Message_20;
									message = message.replaceAll("/Player/", target);
									message = message.replaceAll("/Reason/", reason);
									sender.sendMessage("[iMC Bans] " + message);
								}
							}
						}
						catch(NullPointerException e) {
							sender.sendMessage("[iMC Bans] " + messages.Message_17);
						}
					}
					else {
						sender.sendMessage("[iMC Bans] " + messages.Message_27);
					}
    			}
				else {
					Player targetPlayer = Bukkit.getPlayer(args[0]);
					try {
						String target = targetPlayer.getName();		
						String reason = null;
						reason = Arrays.toString(args).substring(1,  Arrays.toString(args).length() - 1);
						reason = reason.replaceAll(",", "");
						reason = reason.replaceAll(target + " ", "");
						if(target == null) {
							System.out.println("[iMC Bans] " + messages.Message_18);
						}
						else {
							if(targetPlayer.isBanned() == true) {
								String message = messages.Message_19;
								message = message.replaceAll("/Player/", target);
								System.out.println("[iMC Bans] " + message);
							}
							else {
								targetPlayer.kickPlayer(reason);
								targetPlayer.setBanned(true);
								String message = messages.Message_20;
								message = message.replaceAll("/Player/", target);
								message = message.replaceAll("/Reason/", reason);
								System.out.println("[iMC Bans] " + message);
							}
						}
					}
					catch(NullPointerException e) {
						System.out.println("[iMC Bans] " + messages.Message_17);
					}
				}
			}
			else {
				System.out.println("[iMC Bans] " + messages.Message_29);
			}
		}
		
		if(cmd.getName().equalsIgnoreCase("imcbdl")) {
			if (sender instanceof Player) {
				if(player.hasPermission("imcbans.player.dllist")) {
					sender.sendMessage("[iMC Bans] " + messages.Message_1);
					UrlUtils("http://www.imc-bans.eu/list.txt");
					String list = getFile("list.txt");
					sender.sendMessage(list);
					sender.sendMessage("[iMC Bans] " + messages.Message_21);
				}
				else {
					sender.sendMessage("[iMC Bans] " + messages.Message_27);
				}
			}
			else { 
				System.out.println("[iMC Bans] " + messages.Message_1);
				UrlUtils("http://www.imc-bans.eu/list.txt");
				String list = getFile("list.txt");
				System.out.println(list);
				System.out.println("[iMC Bans] " + messages.Message_21);
			}
		}
		
		if(cmd.getName().equalsIgnoreCase("imcbupdt")) {
			if(sender instanceof Player) {
				sender.sendMessage("[iMC Bans] " + messages.Message_26);
			}
			else { 
				try {
					Updater updater = new Updater(this, "imc-bans", this.getFile(), Updater.UpdateType.DEFAULT, false);
					Updater.UpdateResult result = updater.getResult();
			        switch(result) {
			            case SUCCESS:
							System.out.println("[iMC Bans] " + messages.Update_1);
			                break;
			            case NO_UPDATE:
			            	System.out.println("[iMC Bans] " + messages.Update_2);
			                break;
			            case FAIL_DOWNLOAD:
			            	System.out.println("[iMC Bans] " + messages.Update_3);
			                break;
			            case FAIL_DBO:
			            	System.out.println("[iMC Bans] " + messages.Update_4);
			                break;
			            case FAIL_NOVERSION:
			            	System.out.println("[iMC Bans] " + messages.Update_5);
			                break;
			            case FAIL_BADSLUG:
			            	System.out.println("[iMC Bans] " + messages.Update_6);
			                break;
			            case UPDATE_AVAILABLE:
			            	System.out.println("[iMC Bans] " + messages.Update_7);
			        }
				}
				catch (Exception e) {
					e.printStackTrace();
				}
			}
		}

		if(cmd.getName().equalsIgnoreCase("imcbchk")) {
			if(sender instanceof Player) {
				if(player.hasPermission("imcbans.player.checkforupdate")) {
					checkUpdate(false, sender.getName());
				}
				else {
					sender.sendMessage("[iMC Bans Update] " + messages.Message_27);
				}
			}
			else {
				checkUpdate(true, "CONSOLE");
			}
		}

		if(cmd.getName().equalsIgnoreCase("imcbver")) {
			if (sender instanceof Player) {
				if(player.hasPermission("imcbans.player.getversion")) {
					String message = messages.Message_22;
					message = message.replaceAll("/Version/", this.getDescription().getVersion());
					sender.sendMessage("[iMC Bans] " + message);
				}
				else {
					sender.sendMessage("[iMC Bans] " + messages.Message_27);
				}
			}
		    else {
		    	String message = messages.Message_22;
		    	message = message.replaceAll("/Version/", this.getDescription().getVersion());
		    	System.out.println("[iMC Bans] " + message);
		    }
		}	
		return true;
	}
}
