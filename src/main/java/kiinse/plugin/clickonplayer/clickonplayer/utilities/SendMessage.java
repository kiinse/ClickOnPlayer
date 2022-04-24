package kiinse.plugin.clickonplayer.clickonplayer.utilities;

import kiinse.plugin.clickonplayer.clickonplayer.ClickOnPlayer;
import kiinse.plugins.api.darkwaterapi.utilities.Utils;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.scheduler.BukkitTask;
import java.util.concurrent.atomic.AtomicInteger;

public class SendMessage {

    private static final ClickOnPlayer plugin = ClickOnPlayer.getPlugin(ClickOnPlayer.class);
    private static final Utils utils = new Utils(plugin);
    private BukkitTask taskId;

    public void sendToPlayer(Player player, String message) {
        var time = new AtomicInteger(plugin.getConfig().getInt("displayTime"));
        taskId = Bukkit.getScheduler().runTaskTimerAsynchronously(plugin, () -> {
            if (time.get() == 0) {
                taskId.cancel();
            }
            utils.sendActionBar(player, message);
            time.getAndDecrement();
        }, 0, 20L);
    }
}
