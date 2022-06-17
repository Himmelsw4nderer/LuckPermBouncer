package de.himmelswanderer.luckpermbouncer.listeners;

import de.himmelswanderer.luckpermbouncer.LuckPermBouncer;
import net.kyori.adventure.text.Component;
import org.bukkit.Server;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerLoginEvent;

import java.util.Collection;
import java.util.logging.Level;
import java.util.logging.LogRecord;
import java.util.logging.Logger;

public class PlayerJoinListener implements Listener {

    private final Logger log;
    private final FileConfiguration config;

    public PlayerJoinListener(LuckPermBouncer plugin){
        //load config
        config = plugin.getConfig();
        log = plugin.getLogger();
    }

    @EventHandler
    public void onPlayerLogin(PlayerLoginEvent event){
        Player player = event.getPlayer();
        Server server = player.getServer();
        Collection<? extends Player> onlinePlayers = server.getOnlinePlayers();
        //if Server is full
        if(server.getMaxPlayers() <= onlinePlayers.size()){
            //if player is permitted to join when full
            if(player.hasPermission("luckpermbouncer.preferred")){
                //allow the event
                event.allow();
                log.log(new LogRecord(Level.INFO ,"joined "+ player.getName() + "with id: " + player.getUniqueId()));
                //if config is set to kick players
                if(config.getBoolean("kick-on-full")) {
                    //for each online player
                    for (Player onlinePlayer : onlinePlayers) {
                        //if they don't have permission
                        if (!onlinePlayer.hasPermission("luckpermbouncer.preferred")) {
                            //kick player with message You are kicked because the server is full and a privileged player joined
                            onlinePlayer.kick(Component.text("You are kicked because the server is full and a privileged player joined"));
                            log.log(new LogRecord(Level.INFO, "kicked" + onlinePlayer.getName() + "with id: " + onlinePlayer.getUniqueId()));
                            break;
                        }
                    }
                }
            }
        }
    }
}
