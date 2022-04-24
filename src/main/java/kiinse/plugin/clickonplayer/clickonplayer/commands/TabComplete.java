package kiinse.plugin.clickonplayer.clickonplayer.commands;

import kiinse.plugin.clickonplayer.clickonplayer.ClickOnPlayer;
import kiinse.plugins.api.darkwaterapi.utilities.Utils;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.command.TabCompleter;
import org.bukkit.entity.Player;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class TabComplete implements TabCompleter {

    private final ClickOnPlayer plugin = ClickOnPlayer.getPlugin(ClickOnPlayer.class);
    private final Utils utils = new Utils(plugin);

    public List<String> onTabComplete(CommandSender sender, Command cmd, String commandLabel, String[] args) {
        var list = new ArrayList<String>();
        if (sender instanceof Player) {
            if (cmd.getName().equalsIgnoreCase("click")) {
                if (args.length == 1) {
                    if (utils.hasPermission(sender, "ClickOnPlayer.reload")) {
                        list.add("reload");
                    }
                    Collections.sort(list);
                    return list;
                }
            }
        }
        return null;
    }
}
