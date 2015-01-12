package com.drewpercraft.blockrpg.commands;

import java.util.List;

import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.command.TabExecutor;
import org.bukkit.OfflinePlayer;

import com.drewpercraft.blockrpg.BlockRPG;

public class CommandRob implements TabExecutor {

	private final BlockRPG plugin;
	
	public CommandRob(BlockRPG plugin) {
		this.plugin = plugin;
	}
	
	@Override
	public List<String> onTabComplete(CommandSender sender, Command command, String alias, String[] args) {
		// TODO Auto-generated method stub
		return null;
	}
	
	@Override
	public boolean onCommand(CommandSender sender, Command command, String label, String[] params) {

		OfflinePlayer player;
		if (sender instanceof OfflinePlayer) {
			player = (OfflinePlayer) sender;	
		}else{
			sender.sendMessage(plugin.getMessage("InvalidConsoleCommand", label));
			return true;
		}
		sender.sendMessage("Not Implemented Yet");
		return true;
	}
}