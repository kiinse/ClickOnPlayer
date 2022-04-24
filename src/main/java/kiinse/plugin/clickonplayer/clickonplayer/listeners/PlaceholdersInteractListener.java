package kiinse.plugin.clickonplayer.clickonplayer.listeners;

import kiinse.plugin.clickonplayer.clickonplayer.ClickOnPlayer;
import kiinse.plugin.clickonplayer.clickonplayer.utilities.ClickUtils;
import kiinse.plugins.api.darkwaterapi.utilities.Utils;
import me.clip.placeholderapi.PlaceholderAPI;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerInteractEntityEvent;

public class PlaceholdersInteractListener implements Listener {

    private final ClickOnPlayer plugin = ClickOnPlayer.getPlugin(ClickOnPlayer.class);
    private final Utils utils = new Utils(plugin);

    @EventHandler
    public void interactEvent(PlayerInteractEntityEvent event) {
        if (ClickUtils.canInteract(event)) {
            var player = event.getPlayer();
            utils.sendActionBar(player, utils.colorize(PlaceholderAPI.setPlaceholders(player, utils.replaceWord(utils.getMessageFromConfig("MessageOnClick"), "{PLAYER}", event.getRightClicked().getName()))));
        }
    }
}
