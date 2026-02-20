package ru.joutak.template.event

import net.kyori.adventure.text.Component
import org.bukkit.event.EventHandler
import org.bukkit.event.Listener
import org.bukkit.event.player.PlayerJoinEvent
import org.bukkit.plugin.Plugin
import ru.joutak.template.item.CustomItemManager
import ru.joutak.template.item.CustomType

class JoinEvent(private val plugin: Plugin, private val customItemManager: CustomItemManager) : Listener {

    @EventHandler
    fun onJoin(event: PlayerJoinEvent) {
        val player = event.player

        player.sendMessage(Component.text("Привет, ").append(player.displayName()).append(Component.text("!")))

        player.inventory.addItem(customItemManager.create(CustomType.ARMOR))
        player.inventory.addItem(customItemManager.create(CustomType.HIT))
        player.inventory.addItem(customItemManager.create(CustomType.JUMP))
    }
}