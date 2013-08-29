package net.lazlecraft.donotdrop;

import java.util.List;

import org.bukkit.ChatColor;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.PlayerDeathEvent;
import org.bukkit.event.player.PlayerDropItemEvent;
import org.bukkit.plugin.java.JavaPlugin;

public class DoNotDrop extends JavaPlugin implements Listener {
	
	public String prefix;
	public String onDeath;
	public String onDrop;
	public List<String> nodropworld;
	
	public void onEnable() {
		this.getConfig().options().copyDefaults(true);
		this.saveConfig();
		getServer().getPluginManager().registerEvents(this, this);
		prefix = getConfig().getString("prefix");
		onDeath = getConfig().getString("MessageOnDeath");
		onDrop = getConfig().getString("MessageOnDrop");
		nodropworld = getConfig().getStringList("Worlds");
	}
	
	@EventHandler
	public void onDrop(PlayerDropItemEvent ev) {
		Player p = ev.getPlayer();
				if (nodropworld.contains(p.getLocation().getWorld().getName())) {
					ev.setCancelled(true);
					p.sendMessage(ChatColor.translateAlternateColorCodes('&', prefix) + ChatColor.translateAlternateColorCodes('&', onDrop));
		}
	}
	
	@EventHandler
	public void onDeath(PlayerDeathEvent ev) {
		Player p = ev.getEntity();
		if (nodropworld.contains(p.getLocation().getWorld().getName())) {
			ev.getDrops().clear();
			p.sendMessage(ChatColor.translateAlternateColorCodes('&', prefix) + ChatColor.translateAlternateColorCodes('&', onDeath));
		}
	}
}
