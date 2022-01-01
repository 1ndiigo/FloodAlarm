package me.cobble.floodalarm;

import com.xxmicloxx.NoteBlockAPI.model.Song;
import com.xxmicloxx.NoteBlockAPI.songplayer.RadioSongPlayer;
import com.xxmicloxx.NoteBlockAPI.utils.NBSDecoder;
import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import java.io.File;

public class AlarmCommand implements CommandExecutor {

    private final FloodAlarm plugin;

    public AlarmCommand(FloodAlarm plugin) {
        this.plugin = plugin;
        plugin.getCommand("alarm").setExecutor(this);
    }

    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        if (sender.isOp()) {

            if (Bukkit.getPluginManager().getPlugin(plugin.getName()).getDataFolder().exists()) {

                Song song = NBSDecoder.parse(new File(Bukkit.getPluginManager().getPlugin(plugin.getName()).getDataFolder() + "/alarm.nbs"));
                RadioSongPlayer songPlayer = new RadioSongPlayer(song);
                for (Player player : Bukkit.getOnlinePlayers()) {
                    songPlayer.addPlayer(player);
                }

                songPlayer.setPlaying(true);
            } else {
                plugin.saveDefaultConfig();
            }

        }
        return false;
    }
}
