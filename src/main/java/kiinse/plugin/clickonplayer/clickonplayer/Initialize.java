package kiinse.plugin.clickonplayer.clickonplayer;

import kiinse.plugin.clickonplayer.clickonplayer.commands.CommandsListener;
import kiinse.plugin.clickonplayer.clickonplayer.commands.TabComplete;
import kiinse.plugin.clickonplayer.clickonplayer.data.Worlds;
import kiinse.plugin.clickonplayer.clickonplayer.files.messages.Messages;
import kiinse.plugin.clickonplayer.clickonplayer.files.utils.ExportFiles;
import kiinse.plugin.clickonplayer.clickonplayer.listeners.NonPlaceholdersInteractListener;
import kiinse.plugin.clickonplayer.clickonplayer.listeners.PlaceholdersInteractListener;
import kiinse.plugin.clickonplayer.clickonplayer.utilities.ClickUtils;
import kiinse.plugins.api.darkwaterapi.files.Config;
import kiinse.plugins.api.darkwaterapi.files.Json;
import kiinse.plugins.api.darkwaterapi.files.utils.FileManager;
import kiinse.plugins.api.darkwaterapi.files.utils.FilesPathes;
import kiinse.plugins.api.darkwaterapi.utilities.Utils;
import org.bukkit.Bukkit;

import java.io.IOException;
import java.util.Objects;
import java.util.logging.Level;

public class Initialize {

    private final ClickOnPlayer plugin = ClickOnPlayer.getPlugin(ClickOnPlayer.class);
    private final Utils utils = new Utils(plugin);

    public void registerCommands() {
        plugin.getLogger().log(Level.INFO, "Registering commands...");
        Objects.requireNonNull(plugin.getCommand("click")).setExecutor(new CommandsListener());
        Objects.requireNonNull(plugin.getCommand("click")).setTabCompleter(new TabComplete());
        plugin.getLogger().log(Level.INFO, "Commands registered");
    }

    public void registerEvents() {
        if (plugin.getConfig().getBoolean("enabled")) {
            plugin.getLogger().log(Level.INFO, "Registering listeners...");
            if(Bukkit.getPluginManager().getPlugin("PlaceholderAPI") != null) {
                plugin.getServer().getPluginManager().registerEvents(new PlaceholdersInteractListener(), plugin);
            } else {
                plugin.getServer().getPluginManager().registerEvents(new NonPlaceholdersInteractListener(), plugin);
            }
            plugin.getLogger().log(Level.INFO, "Listeners registered");
        } else {
            plugin.getLogger().log(Level.INFO, "Plugin disabled in config. Events will not be registered");
        }
    }

    public void loadFiles() throws IOException {
        plugin.getLogger().log(Level.INFO, "Loading files and data...");
        new Config().load(plugin);
        new ExportFiles().createFiles();
        var worldsConfig = FileManager.loadConfig(FilesPathes.getFile(plugin,"worlds", FilesPathes.types.YML));
        Worlds.load(worldsConfig.getStringList("enabledWorlds"));
        plugin.getLogger().log(Level.INFO, "Files and data has been loaded");
    }

    public void loadMessages() throws IOException {
        plugin.getLogger().log(Level.INFO, "Loading messages...");
        Messages.setClickMessages(Json.load(FilesPathes.getPath(plugin,"messages", FilesPathes.types.JSON)));
        plugin.getLogger().log(Level.INFO, "Messages has been loaded");
    }

    public void loadAPIs() throws NullPointerException {
        plugin.getLogger().log(Level.INFO, "Loading DarkWaterAPI...");
        var currentVersion = utils.getApiVersion();
        var requestVersion = ClickUtils.getCurrentApiVersion();
        if(utils.formatVersion(requestVersion) > utils.formatVersion(currentVersion) ) {
            throw new NullPointerException("DarkWaterAPI is deprecated! You have version " + currentVersion + ", but plugin require version " + requestVersion + " and above.\nDownload it at: https://github.com/kiinse/ClickOnPlayer");
        } else {
            plugin.getLogger().log(Level.INFO, "DarkWaterAPI loaded");
        }
        plugin.getLogger().log(Level.INFO, "Registering PlaceHolderAPI...");
        if(Bukkit.getPluginManager().getPlugin("PlaceholderAPI") != null){
            plugin.getLogger().log(Level.INFO, "PlaceHolderAPI registered");
        } else {
            plugin.getLogger().log(Level.WARNING, "PlaceHolderAPI not found");
        }
    }

    public void sendInfo() {
        plugin.getLogger().log(Level.INFO, "=========================");
        plugin.getLogger().log(Level.INFO, " ");
        plugin.getLogger().log(Level.INFO, " ");
        plugin.getLogger().log(Level.INFO, " ");
        plugin.getLogger().log(Level.INFO, "ClickOnPlayer started!");
        plugin.getLogger().log(Level.INFO, "Creator: kiinse");
        plugin.getLogger().log(Level.INFO, "Website: https://github.com/kiinse");
        plugin.getLogger().log(Level.INFO, "Plugin version: " + plugin.getDescription().getVersion());
        plugin.getLogger().log(Level.INFO, " ");
        plugin.getLogger().log(Level.INFO, " ");
        plugin.getLogger().log(Level.INFO, " ");
        plugin.getLogger().log(Level.INFO, "========================");
    }
}
