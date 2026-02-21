package ru.joutak.template.event

import org.bukkit.Bukkit
import org.bukkit.entity.Player
import org.bukkit.event.EventHandler
import org.bukkit.event.Listener
import org.bukkit.event.entity.EntityDropItemEvent
import org.bukkit.event.entity.EntityPickupItemEvent
import org.bukkit.event.inventory.InventoryClickEvent
import org.bukkit.event.inventory.InventoryDragEvent
import org.bukkit.plugin.Plugin
import ru.joutak.template.data.PlayerDataManager

class InventoryChangeEvent(private val playerDataManager: PlayerDataManager, private val plugin: Plugin) : Listener {

    @EventHandler
    fun onInventoryClick(event: InventoryClickEvent) {
        if (event.whoClicked is Player) {
            Bukkit.getScheduler().runTask(plugin, Runnable {playerDataManager.update(event.whoClicked as Player)})
        }
    }

    @EventHandler
    fun onDrag(event: InventoryDragEvent) {
        if (event.whoClicked is Player) {
            Bukkit.getScheduler().runTask(plugin, Runnable {playerDataManager.update(event.whoClicked as Player)})
        }
    }

    @EventHandler
    fun onPickUp(event: EntityPickupItemEvent) {
        if (event.entity is Player) {
            Bukkit.getScheduler().runTask(plugin, Runnable {playerDataManager.update(event.entity as Player)})
        }
    }

    @EventHandler
    fun onDrop(event: EntityDropItemEvent) {
        if (event.entity is Player) {
            Bukkit.getScheduler().runTask(plugin, Runnable {playerDataManager.update(event.entity as Player)})
        }
    }
}