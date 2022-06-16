package de.himmelswanderer.luckpermbouncer.listeners;

import org.bukkit.Server;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;

import java.util.Collection;

public class PlayerJoinListener implements Listener {

    @EventHandler
    public void onPlayerJoin(PlayerJoinEvent event){
        Player player = event.getPlayer();
        Server server = player.getServer();
        Collection<? extends Player> onlinePlayers = server.getOnlinePlayers();
        //if Server is full
        if(server.getMaxPlayers() <= onlinePlayers.size()){
            //if player is permitted to join when full
            if(player.hasPermission("luckpermbouncer.preferred")){
                //for each online player
                for(Player onlinePlayer : onlinePlayers){
                    //if they dont have permission
                    if(!onlinePlayer.hasPermission("luckpermbouncer.preferred")){
                        onlinePlayer.kick();
                        System.out.println("kicked " + onlinePlayer.getName() + "with id: " + onlinePlayer.getUniqueId() + "for "+ player.getName() + "with id: " + player.getUniqueId());
                        break;
                    }
                }
            }
        }
    }
}
