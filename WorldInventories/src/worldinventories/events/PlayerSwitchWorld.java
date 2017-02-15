package worldinventories.events;

import org.bukkit.World;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerTeleportEvent;

import worldinventories.main.Main;
import worldinventories.main.PlayerInv;

public class PlayerSwitchWorld implements Listener {

	@EventHandler
	public void onPlayerTeleport(PlayerTeleportEvent event) {
		World oldWorld = event.getFrom().getWorld();
		World newWorld = event.getTo().getWorld();

		Player player = event.getPlayer();
		FileConfiguration config = Main.plugin.getConfig();

		// If player is telporting within the world, forget it
		if (oldWorld.getName().equalsIgnoreCase(newWorld.getName()))
			return;
		
		PlayerInv playerInv = new PlayerInv(player);
		playerInv.saveInv(config, oldWorld);
		playerInv.setInv(config, newWorld);
	}
}
