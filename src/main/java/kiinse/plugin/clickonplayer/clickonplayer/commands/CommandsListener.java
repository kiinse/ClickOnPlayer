package kiinse.plugin.clickonplayer.clickonplayer.commands;

import kiinse.plugin.clickonplayer.clickonplayer.ClickOnPlayer;
import kiinse.plugin.clickonplayer.clickonplayer.Initialize;
import kiinse.plugin.clickonplayer.clickonplayer.files.messages.Messages;
import kiinse.plugins.api.darkwaterapi.files.locale.LocaleUtils;
import kiinse.plugins.api.darkwaterapi.utilities.Utils;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.jetbrains.annotations.NotNull;
import java.io.IOException;
import java.util.logging.Level;

public class CommandsListener implements CommandExecutor {

    private final ClickOnPlayer plugin = ClickOnPlayer.getPlugin(ClickOnPlayer.class);
    private final Utils utils = new Utils(plugin);

    @Override
    public boolean onCommand(@NotNull CommandSender sender, @NotNull Command command, @NotNull String label, String[] args) {
        if (sender instanceof Player) {
            var senderLocale = LocaleUtils.getLocale(sender);
            if (args.length != 0) {
                if (args[0].equals("reload")) {
                    if (!utils.hasPermission(sender, "ClickOnPlayer.reload")) {
                        utils.sendMessageWithPrefix(sender, Messages.getClickMessages(), senderLocale, "noPermission");
                    } else {
                        try {
                            var init = new Initialize();
                            plugin.reloadConfig();
                            plugin.saveConfig();
                            init.loadFiles();
                            init.loadMessages();
                            utils.sendMessageWithPrefix(sender, Messages.getClickMessages(), senderLocale, "pluginReloaded");
                        } catch (IOException e) {
                            plugin.getLogger().log(Level.SEVERE, "Error on loading files. Message: " + e.getMessage());
                            utils.sendMessageWithPrefix(sender, Messages.getClickMessages(), senderLocale, "errorOnReloading");
                        }
                    }
                    return true;
                }
                utils.sendMessageWithPrefix(sender, Messages.getClickMessages(), senderLocale, "noCommand");
            }
            return true;
        }
        return true;
    }
}
