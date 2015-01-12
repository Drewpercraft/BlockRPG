/**
 * 
 */
package com.drewpercraft.blockrpg.listeners;

import org.bukkit.entity.Player;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.PlayerDeathEvent;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.event.EventHandler;

import com.drewpercraft.blockrpg.BlockRPG;



/**
 * @author jjarrell
 *
 */
public final class PlayerListener implements Listener {

	private BlockRPG plugin;

    public PlayerListener(BlockRPG plugin) {
        this.plugin = plugin;
    }
	
    @EventHandler
    public void onLogin(PlayerJoinEvent event)
    {
    }
    
    @EventHandler
    public void onDeath(PlayerDeathEvent event)
    {
    }
    

}
