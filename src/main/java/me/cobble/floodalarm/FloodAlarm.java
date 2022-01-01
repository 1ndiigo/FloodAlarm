package me.cobble.floodalarm;

import org.bukkit.plugin.java.JavaPlugin;

public final class FloodAlarm extends JavaPlugin {

    @Override
    public void onEnable() {
        // Plugin startup logic
        new AlarmCommand(this);
        new FloodCommand(this);
        new Listeners(this);

    }

    @Override
    public void onDisable() {
        // Plugin shutdown logic
    }
}
