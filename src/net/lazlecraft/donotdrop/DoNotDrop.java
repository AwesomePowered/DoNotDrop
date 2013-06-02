package net.lazlecraft.donotdrop;

import org.bukkit.ChatColor;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerDropItemEvent;
import org.bukkit.plugin.java.JavaPlugin;

public class DoNotDrop extends JavaPlugin implements Listener {
	
	public String prefix;
	
	public void onEnable() {
		this.getConfig().options().copyDefaults(true);
		this.saveConfig();
		getServer().getPluginManager().registerEvents(this, this);
		prefix = getConfig().getString("prefix");
	}
	
	@EventHandler
	public void onDrop(PlayerDropItemEvent ev) {
		Player p = ev.getPlayer();
				if (ev.getPlayer().getLocation().getWorld().getName() == getConfig().getString("World")) {
					ev.setCancelled(true);
					p.sendMessage(ChatColor.translateAlternateColorCodes('&', prefix) + ChatColor.RED + " You may not drop items in this world!");
		}
	}
}
