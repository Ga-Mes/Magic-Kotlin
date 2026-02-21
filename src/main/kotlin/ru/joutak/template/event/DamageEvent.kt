package ru.joutak.template.event

import org.bukkit.entity.Player
import org.bukkit.event.EventHandler
import org.bukkit.event.Listener
import org.bukkit.event.entity.EntityDamageByEntityEvent
import ru.joutak.template.data.PlayerDataManager
import ru.joutak.template.item.CustomType

class DamageEvent(private val playerDataManager: PlayerDataManager) : Listener {

    @EventHandler
    fun onDamage(event: EntityDamageByEntityEvent) {
        if (event.damager is Player) {
            val player = event.damager

            if (playerDataManager.states[player.uniqueId]!![CustomType.HIT] == true) {
                event.damage = event.damage * 2
            }
        } else if (event.entity is Player) {
            val player = event.entity

            if (playerDataManager.states[player.uniqueId]!![CustomType.ARMOR] == true) {
                event.damage = event.damage * 0.5
            }
        }
    }
}