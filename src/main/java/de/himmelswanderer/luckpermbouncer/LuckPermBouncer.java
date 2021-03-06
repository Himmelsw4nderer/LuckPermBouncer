package de.himmelswanderer.luckpermbouncer;

import de.himmelswanderer.luckpermbouncer.listeners.PlayerJoinListener;
import org.bukkit.plugin.java.JavaPlugin;

import java.util.logging.Level;
import java.util.logging.Logger;

public final class LuckPermBouncer extends JavaPlugin {

    @Override
    public void onEnable() {
        //init logger
        Logger log = getLogger();
        //config startup
        getConfig().options().copyDefaults();
        saveDefaultConfig();
        //register the PlayerJoinListener
        getServer().getPluginManager().registerEvents(new PlayerJoinListener(this), this);
        //log that the plugin is enabled
        log.log(Level.INFO, "LuckPermBouncer is enabled");
    }
}
