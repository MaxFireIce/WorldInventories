package worldinventories.events;

import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;

import worldinventories.main.Main;
import worldinventories.main.PlayerInv;

public class PlayerJoin implements Listener {

	@EventHandler
	public void onPlayerJoin(PlayerJoinEvent event) {

		Player player = event.getPlayer();
		FileConfiguration config = Main.plugin.getConfig();

		if (!player.hasPlayedBefore())
			return;
		
		PlayerInv playerInv = new PlayerInv(player);
		playerInv.setInv(config, player.getWorld());
		
	}
}
