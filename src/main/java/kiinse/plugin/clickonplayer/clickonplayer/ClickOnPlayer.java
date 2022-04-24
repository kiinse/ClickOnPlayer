package kiinse.plugin.clickonplayer.clickonplayer;

import org.bukkit.plugin.Plugin;
import org.bukkit.plugin.java.JavaPlugin;

import java.util.logging.Level;

public final class ClickOnPlayer extends JavaPlugin {

    @Override
    public void onEnable() {
        start(this);
    }

    @Override
    public void onDisable() {
        stop(this);
    }

    public void start(Plugin plugin) {
        try {
            var init = new Initialize();
            plugin.getLogger().log(Level.INFO, "Starting ClickOnPlayer...");
            init.loadAPIs();
            init.loadFiles();
            init.loadMessages();
            init.registerEvents();
            init.registerCommands();
            init.sendInfo();
        } catch (Exception e) {
            plugin.getLogger().log(Level.SEVERE, "Error on starting ClickOnPlayer: " + e.getMessage());
            plugin.getPluginLoader().disablePlugin(plugin);
        }
    }

    public void stop(Plugin plugin) {
        try {
            plugin.getLogger().log(Level.INFO, "Disabling ClickOnPlayer...");
            plugin.saveConfig();
            plugin.getLogger().log(Level.INFO, "ClickOnPlayer disabled");
        } catch (Exception e) {
            plugin.getLogger().log(Level.SEVERE, "Error on disabling ClickOnPlayer: " + e.getMessage());
        }
    }
}
