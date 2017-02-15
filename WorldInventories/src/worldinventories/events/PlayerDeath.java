package worldinventories.events;

import java.util.ArrayList;

import org.bukkit.Material;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.PlayerDeathEvent;
import org.bukkit.inventory.ItemStack;

import worldinventories.main.Main;

public class PlayerDeath implements Listener {

	@EventHandler
	public void onPlayerDeath(PlayerDeathEvent event) {

		Player player = event.getEntity();
		String playerName = player.getName();
		FileConfiguration config = Main.plugin.getConfig();
		String playerWorld = player.getWorld().getName();

		// if keep inventory is true, forget it
		if (event.getKeepInventory())
			return;

		else {
			// reset inventory
			ItemStack[] in = player.getInventory().getContents();
			ArrayList<ItemStack> list = new ArrayList<ItemStack>();

			for (int i = 0; i < in.length; i++) {
				list.add(new ItemStack(Material.AIR));
			}
			
			config.set(playerName + ".Worlds." + playerWorld + ".Inventory", list);
			config.set(playerName + ".Worlds." + playerWorld + ".Health", 20.0);
			config.set(playerName + ".Worlds." + playerWorld + ".Food", 20);

			Main.plugin.saveConfig();
		}
	}
}
