package ru.joutak.template.event

import org.bukkit.event.EventHandler
import org.bukkit.event.Listener
import org.bukkit.event.player.PlayerQuitEvent
import ru.joutak.template.data.PlayerDataManager

class QuitEvent(private val playerDataManager: PlayerDataManager) : Listener {

    @EventHandler
    fun onQuit(event: PlayerQuitEvent) {
        playerDataManager.unload(event.player)
    }
}