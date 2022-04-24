package kiinse.plugin.clickonplayer.clickonplayer.files.utils;

import kiinse.plugin.clickonplayer.clickonplayer.ClickOnPlayer;
import kiinse.plugins.api.darkwaterapi.files.utils.FileManager;
import kiinse.plugins.api.darkwaterapi.files.utils.FilesPathes;
import java.io.IOException;
import java.util.logging.Level;

public class ExportFiles {

    private final ClickOnPlayer plugin = ClickOnPlayer.getPlugin(ClickOnPlayer.class);

    public void createFiles() throws IOException {
        if (FileManager.copyFile(FileManager.accessFile(ClickOnPlayer.class, "messages.json"), FilesPathes.getFile(plugin,"messages", FilesPathes.types.JSON))) {
            plugin.getLogger().log(Level.INFO, "File 'messages.json' created");
        }
        if (FileManager.copyFile(FileManager.accessFile(ClickOnPlayer.class, "worlds.yml"), FilesPathes.getFile(plugin,"worlds", FilesPathes.types.YML))) {
            plugin.getLogger().log(Level.INFO, "File 'worlds.json' created");
        }
    }

}
