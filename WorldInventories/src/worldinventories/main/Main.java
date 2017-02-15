package worldinventories.main;

import org.bukkit.plugin.PluginManager;
import org.bukkit.plugin.java.JavaPlugin;

import worldinventories.events.PlayerDeath;
import worldinventories.events.PlayerJoin;
import worldinventories.events.PlayerLeave;
import worldinventories.events.PlayerRespawn;
import worldinventories.events.PlayerSwitchWorld;

public class Main extends JavaPlugin {

	public static JavaPlugin plugin;

	@Override
	public void onEnable() {

		plugin = this;
		registerConfig();
		registerEvents();

	}

	private void registerConfig() {
		getConfig().options().copyDefaults(true);
		saveConfig();
	}

	public void registerEvents() {
		PluginManager pm = getServer().getPluginManager();
		pm.registerEvents(new PlayerSwitchWorld(), this);
		pm.registerEvents(new PlayerJoin(), this);
		pm.registerEvents(new PlayerLeave(), this);
		pm.registerEvents(new PlayerDeath(), this);
		pm.registerEvents(new PlayerRespawn(), this);

	}
}
