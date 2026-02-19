package ru.joutak.template.event

import net.kyori.adventure.text.Component
import org.bukkit.Bukkit
import org.bukkit.event.EventHandler
import org.bukkit.event.Listener
import org.bukkit.event.player.PlayerJoinEvent
import org.bukkit.plugin.Plugin
import ru.joutak.template.item.CustomItem
import ru.joutak.template.item.CustomType

class JoinEvent(private val plugin: Plugin) : Listener {

    @EventHandler
    fun onJoin(event: PlayerJoinEvent) {
        val player = event.player

        player.sendMessage(Component.text("Привет, ").append(player.displayName()).append(Component.text("!")))

        Bukkit.getScheduler().runTask(plugin, Runnable {CustomItem.create(CustomType.HIT, plugin)?.let { item -> player.inventory.addItem(item) }
                                                                  CustomItem.create(CustomType.ARMOR, plugin)?.let { item -> player.inventory.addItem(item) }
                                                                  CustomItem.create(CustomType.JUMP, plugin)?.let { item -> player.inventory.addItem(item) }})
    }
}