package me.cobble.floodalarm;

import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.World;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.PlayerDeathEvent;

import java.util.ArrayList;
import java.util.concurrent.atomic.AtomicInteger;

public class Listeners implements Listener {

    public ArrayList<String> accounts = new ArrayList<>();
    private FloodAlarm plugin;

    public Listeners(FloodAlarm plugin) {
        this.plugin = plugin;
        Bukkit.getPluginManager().registerEvents(this, plugin);

        accounts.add("TheFadingFemboy");
    }

    @EventHandler
    public void onDeath(PlayerDeathEvent e) {
        if (accounts.contains(e.getEntity().getName())) {

            World world = FloodCommand.world == null ? Bukkit.getWorlds().get(0) : FloodCommand.world;


            AtomicInteger y = new AtomicInteger(world.getMaxHeight());
            FloodCommand.canFlood = false;

            Bukkit.getScheduler().scheduleSyncRepeatingTask(plugin, () -> {

                for (int j = (int) (-world.getWorldBorder().getSize() / 2); j < world.getWorldBorder().getSize() / 2; j++) {
                    for (int i = (int) (-world.getWorldBorder().getSize() / 2); i < world.getWorldBorder().getSize() / 2; i++) {
                        if (world.getBlockAt(i, y.get(), j).getType() == Material.WATER) {
                            world.getBlockAt(i, y.get(), j).setType(Material.AIR);
                        }
                    }
                }

                y.getAndDecrement();
            }, 0, 5);
        }
    }
}
