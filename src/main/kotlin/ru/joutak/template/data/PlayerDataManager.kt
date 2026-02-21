package ru.joutak.template.data

import org.bukkit.entity.Player
import org.bukkit.plugin.Plugin
import ru.joutak.template.item.CustomItemManager
import ru.joutak.template.item.CustomType
import java.time.Instant
import java.util.UUID

class PlayerDataManager(plugin: Plugin, private val customItemManager: CustomItemManager) {
    val states = mutableMapOf<UUID, MutableMap<CustomType, Boolean>>()

    val times = mutableMapOf<UUID, Instant>()

    val saver = Saver(plugin, this)

    fun load(player: Player) {
        states.put(player.uniqueId, mutableMapOf())

        times.put(player.uniqueId, Instant.now())

        for (name in CustomType.entries) {
            states[player.uniqueId]?.put(name, false)
        }

        update(player)
    }

    fun update(player: Player) {
        for (item in player.inventory.iterator()) {
            if (item != null) {
                if (item.itemMeta != null) {
                    val customType = customItemManager.check(item.itemMeta.persistentDataContainer)

                    if (customType != null) {
                        states[player.uniqueId]?.put(customType, true)
                    }
                }
            }
        }
    }

    fun save(player: Player) {
        saver.save(player)
    }

    fun unLoad(player: Player) {
        states.remove(player.uniqueId)
    }
}