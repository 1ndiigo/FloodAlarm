package me.cobble.floodalarm;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.World;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.scheduler.BukkitTask;

import java.util.concurrent.atomic.AtomicInteger;

public class FloodCommand implements CommandExecutor {

    public static AtomicInteger y = new AtomicInteger(4);
    public static World world;
    public static boolean canFlood = false;

    private final FloodAlarm plugin;
    private BukkitTask task = null;

    public FloodCommand(FloodAlarm plugin) {
        this.plugin = plugin;
        plugin.getCommand("flood").setExecutor(this);
    }

    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {

        if (args.length == 0) {
            sender.sendMessage(ChatColor.RED + "Please provide a world to flood");
        }

        if (sender.isOp() && args.length == 1) {
            world = Bukkit.getWorld(args[0]);
            canFlood = true;
            y.set(4);

            task = Bukkit.getScheduler().runTaskTimer(plugin, () -> {

                if(canFlood) {

                    for (int j = (int) (-world.getWorldBorder().getSize() / 2); j < world.getWorldBorder().getSize() / 2; j++) {
                        for (int i = (int) (-world.getWorldBorder().getSize() / 2); i < world.getWorldBorder().getSize() / 2; i++) {
                            if (world.getBlockAt(i, y.get(), j).getType().isAir()) {
                                world.getBlockAt(i, y.get(), j).setType(Material.WATER);
                            }
                        }
                    }
                }

                y.getAndIncrement();

            }, 100, 100);
        }
        return false;
    }
}
