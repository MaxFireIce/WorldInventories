package worldinventories.events;

import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerRespawnEvent;

import worldinventories.main.Main;
import worldinventories.main.PlayerInv;

public class PlayerRespawn implements Listener {

	@EventHandler
	public void onPlayerRespawn(PlayerRespawnEvent event) {
		Player player = event.getPlayer();
		FileConfiguration config = Main.plugin.getConfig();
		
		PlayerInv playerInv = new PlayerInv(player);
		playerInv.setInv(config, event.getRespawnLocation().getWorld());
	}
}
