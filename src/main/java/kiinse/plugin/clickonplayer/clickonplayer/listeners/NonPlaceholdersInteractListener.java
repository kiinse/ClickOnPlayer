package kiinse.plugin.clickonplayer.clickonplayer.listeners;

import kiinse.plugin.clickonplayer.clickonplayer.ClickOnPlayer;
import kiinse.plugin.clickonplayer.clickonplayer.utilities.ClickUtils;
import kiinse.plugins.api.darkwaterapi.utilities.Utils;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerInteractEntityEvent;

public class NonPlaceholdersInteractListener implements Listener {

    private final ClickOnPlayer plugin = ClickOnPlayer.getPlugin(ClickOnPlayer.class);
    private final Utils utils = new Utils(plugin);

    @EventHandler
    public void interactEvent(PlayerInteractEntityEvent event) {
        if (ClickUtils.canInteract(event)) {
            utils.sendActionBar(event.getPlayer(), utils.colorize(utils.replaceWord(utils.getMessageFromConfig("MessageOnClick"), "{PLAYER}", event.getRightClicked().getName())));
        }
    }
}
