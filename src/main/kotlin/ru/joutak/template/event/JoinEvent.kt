package ru.joutak.template.event

import org.bukkit.event.EventHandler
import org.bukkit.event.Listener
import org.bukkit.event.player.PlayerJoinEvent
import ru.joutak.template.data.PlayerDataManager

class JoinEvent(private val playerDataManager: PlayerDataManager) : Listener {

    @EventHandler
    fun onJoin(event: PlayerJoinEvent) {
        playerDataManager.load(event.player)
    }
}