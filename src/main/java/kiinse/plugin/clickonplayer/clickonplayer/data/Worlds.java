package kiinse.plugin.clickonplayer.clickonplayer.data;

import kiinse.plugin.clickonplayer.clickonplayer.ClickOnPlayer;
import lombok.extern.slf4j.Slf4j;
import org.bukkit.World;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;

@Slf4j
public class Worlds {

    private static final ClickOnPlayer plugin = ClickOnPlayer.getPlugin(ClickOnPlayer.class);
    private static List<String> enabledWorlds = new ArrayList<>();

    public static void load(List<String> enabledWorldsList) throws IllegalArgumentException {
        enabledWorlds.clear();
        if (enabledWorldsList.isEmpty()) {
            throw new IllegalArgumentException("Enabled worlds list is empty. Check file 'worlds.yml'");
        } else {
            for (var rawData : enabledWorldsList) {
                enabledWorlds.add(rawData);
                log.debug("World: {}", rawData);
            }
            plugin.getLogger().log(Level.INFO, "Enabled worlds hashmap loaded");
        }
    }

    public static boolean hasWorld(World world) {
        return enabledWorlds.contains(world.getName());
    }
}
