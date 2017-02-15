package worldinventories.main;

import java.util.ArrayList;
import java.util.List;

import org.bukkit.Material;
import org.bukkit.World;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;

public class PlayerInv {
	
	private Player player;
	
	public PlayerInv(Player player){
		this.player = player;
	}
	
	public void saveInv(FileConfiguration config, World world){
		// save the old inventory of the player
		String playerName = player.getName();
		String playerWorld = world.getName();
		double playerHealth = player.getHealth();
		int playerFood = player.getFoodLevel();
		
		ItemStack[] in = player.getInventory().getContents();
		ArrayList<ItemStack> list = new ArrayList<ItemStack>();

		for (int i = 0; i < in.length; i++) {
			ItemStack item = in[i];
			if (item != null) {
				list.add(item);
			} else {
				list.add(new ItemStack(Material.AIR));
			}
		}

		config.set(playerName + ".Worlds." + playerWorld + ".Inventory", list);
		config.set(playerName + ".Worlds." + playerWorld + ".Health", playerHealth);
		config.set(playerName + ".Worlds." + playerWorld + ".Food", playerFood);

		Main.plugin.saveConfig();
	}
	
	public void setInv(FileConfiguration config, World world){
		// set the new one
		String playerName = player.getName();
		String playerWorld = world.getName();
		
		player.getInventory().clear();
		ItemStack[] contents = player.getInventory().getContents();

		List<?> inv = config.getList(playerName + ".Worlds." + playerWorld + ".Inventory");
		if(inv == null) return;
		ArrayList<ItemStack> list1 = new ArrayList<ItemStack>();
		for (int i = 0; i < inv.size(); i++) {
			list1.add((ItemStack) inv.get(i));
		}

		for (int i = 0; i < list1.size(); i++) {
			contents[i] = list1.get(i);
		}

		player.getInventory().setContents(contents);
		player.setHealth(config.getDouble(playerName + ".Worlds." + playerWorld + ".Health"));
		player.setFoodLevel(config.getInt(playerName + ".Worlds." + playerWorld + ".Food"));
	}

}
