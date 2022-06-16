package de.himmelswanderer.luckpermbouncer;

import de.himmelswanderer.luckpermbouncer.listeners.PlayerJoinListener;
import org.bukkit.plugin.java.JavaPlugin;

public final class LuckPermBouncer extends JavaPlugin {

    //On Enable
    @Override
    public void onEnable() {
        //register the PlayerJoinListener
        getServer().getPluginManager().registerEvents(new PlayerJoinListener(), this);
        //log
        System.out.println("started!");
    }
}
