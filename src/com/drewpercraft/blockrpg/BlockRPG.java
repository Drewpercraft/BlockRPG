package com.drewpercraft.blockrpg;

import java.io.File;
import java.io.FileInputStream;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.PropertyResourceBundle;
import java.util.ResourceBundle;
import java.util.Set;
import java.util.logging.Logger;

import org.bukkit.Bukkit;
import org.bukkit.World;
import org.bukkit.command.CommandSender;
import org.bukkit.configuration.ConfigurationSection;
import org.bukkit.plugin.java.JavaPlugin;

import com.drewpercraft.blockrpg.commands.CommandRob;
import com.drewpercraft.blockrpg.listeners.PlayerListener;


public final class BlockRPG extends JavaPlugin {
	
	protected Logger log;
	private ResourceBundle userMessages;
 		
	/*
	 * Get the message using the key from the language file
	 * with the config.yml dialogPrefix/dialogSuffix added
	 */
	public String getMessage(String key)
	{
		if (!userMessages.containsKey(key)) {
			log.severe(String.format("Language file is missing the %s key", key));
			return key;
		}
		return getConfig().getString("dialogPrefix", "") + userMessages.getString(key) + getConfig().getString("dialogSuffix", "");
	}
	
	/*
	 * Get the message using the key from the language file
	 * and format the message using the params provided
	 */
	public String getMessage(String key, Object... args)
	{
		return String.format(getMessage(key), args);
	}
	
	public boolean loadConfiguration() {
    	log.info("Loading configuration");
    	
    	try {
	   		//this.saveDefaultConfig();
	    	//this.saveConfig();
	    	
	    	// Load the language file
	    	File messageFile = new File(this.getDataFolder() + File.separator + "language.txt");
	    	InputStream fis;
	    	if (messageFile.exists())
	    	{
	    		log.info("Loading language settings from " + messageFile);
	    		fis = new FileInputStream(messageFile);
	    	}else{
	    		log.info("Using default language settings");
	    		fis = this.getClass().getClassLoader().getResourceAsStream("language.txt");
	    	}
	    	 
			try {
			  userMessages = new PropertyResourceBundle(fis);
			} 
			catch (Exception e) {
				log.severe("Error loading language file:");
				throw e;
			}
			finally {
			  fis.close();
			}
	    		    
	    	log.info("Configuration Load Completed");
    	}
    	catch (Exception e) {
    		e.printStackTrace();
    		log.severe("Error loading configuration. Disabling BlockBank.");
    		Bukkit.getPluginManager().disablePlugin(this);
    		return false;
    	}
    	return true;
    }

	@Override
    public void onDisable() {
        // TODO Insert logic to be performed when the plugin is disabled
    	log.info(String.format("%s Disabled", this.getName()));
    }

	@Override
    public void onEnable() {
		log = getLogger();
    	
    	if (loadConfiguration()) {
        	log.info(String.format("Enabling %s commands", this.getName()));
        	CommandRob commandRob = new CommandRob(this);
        	getCommand("rob").setExecutor(commandRob);
        	getCommand("rob").setTabCompleter(commandRob);
        	    	
        	log.info(String.format("Enabling %s event handlers", this.getName()));
        	getServer().getPluginManager().registerEvents(new PlayerListener(this), this);
	    	
	    	log.info(String.format("%s enabled", this.getName()));
    	}
    }

		
	/*
	 *  Send the player a message based on the language file
	 */
	public void sendMessage(CommandSender player, String key, Object... args) 
	{
		player.sendMessage(getMessage(key, args));		
	}

	public <T> void setConfig(String key, T setting) {
		getConfig().set(key, setting);
		//TODO instead of saveConfig, this can be a timer/dirty flag or trigger an event
		// so the save happens in another thread
		saveConfig();
	}

}
