package ru.joutak.template.event

import net.kyori.adventure.text.Component
import org.bukkit.Bukkit
import org.bukkit.event.EventHandler
import org.bukkit.event.Listener
import org.bukkit.event.player.PlayerJoinEvent
import org.bukkit.plugin.Plugin
import ru.joutak.template.data.PlayerDataManager

class JoinEvent(private val plugin: Plugin, private val playerDataManager: PlayerDataManager) : Listener {

    @EventHandler
    fun onJoin(event: PlayerJoinEvent) {
        val player = event.player

        playerDataManager.load(player)

        Bukkit.getScheduler().runTask(plugin, Runnable {player.sendMessage(Component.text("${playerDataManager.states[player.uniqueId]}"))})
    }
}