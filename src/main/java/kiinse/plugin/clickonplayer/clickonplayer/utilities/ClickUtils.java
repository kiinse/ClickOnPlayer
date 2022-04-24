package kiinse.plugin.clickonplayer.clickonplayer.utilities;

import kiinse.plugin.clickonplayer.clickonplayer.ClickOnPlayer;
import kiinse.plugin.clickonplayer.clickonplayer.data.Worlds;
import lombok.extern.slf4j.Slf4j;
import org.bukkit.entity.Player;
import org.bukkit.event.player.PlayerInteractEntityEvent;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;
import java.util.logging.Level;

@Slf4j
public class ClickUtils {

    public static boolean canInteract(PlayerInteractEntityEvent event) {
        var player = event.getPlayer();
        var entity = event.getRightClicked();
        return entity instanceof Player && ((player.hasPermission("ClickOnPlayer.see") && entity.hasPermission("ClickOnPlayer.show")) || player.hasPermission("ClickOnPlayer.admin.see")) && Worlds.hasWorld(player.getWorld());
    }

    public static String getCurrentApiVersion() {
        var plugin = ClickOnPlayer.getPlugin(ClickOnPlayer.class);
        try {
            var input = ClickOnPlayer.class.getResourceAsStream(File.separator + "resources" + File.separator + "info.properties");
            InputStream info;
            if (input != null) {
                info = input;
            } else {
                info = ClickOnPlayer.class.getClassLoader().getResourceAsStream("info.properties");
            }
            var property = new Properties();
            property.load(info);
            return property.getProperty("api.version");
        } catch (IOException e) {
            plugin.getLogger().log(Level.INFO, "Error on getting api version: " + e.getMessage());
        }
        return null;
    }
}
