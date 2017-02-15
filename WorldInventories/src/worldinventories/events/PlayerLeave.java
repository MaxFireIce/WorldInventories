package worldinventories.events;

import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerQuitEvent;

import worldinventories.main.Main;
import worldinventories.main.PlayerInv;

public class PlayerLeave implements Listener {

	@EventHandler
	public void onPlayerDisconnect(PlayerQuitEvent event) {

		Player player = event.getPlayer();
		FileConfiguration config = Main.plugin.getConfig();
		
		PlayerInv playerInv = new PlayerInv(player);
		playerInv.saveInv(config, player.getWorld());
	}
}
