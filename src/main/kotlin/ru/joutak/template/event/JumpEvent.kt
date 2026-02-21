package ru.joutak.template.event

import com.destroystokyo.paper.event.player.PlayerJumpEvent
import org.bukkit.event.EventHandler
import org.bukkit.event.Listener
import ru.joutak.template.data.PlayerDataManager
import ru.joutak.template.item.CustomType

class JumpEvent(private val playerDataManager: PlayerDataManager) : Listener {

    @EventHandler
    fun onJump(event: PlayerJumpEvent) {
        val player = event.player

        if (playerDataManager.states[player.uniqueId]!![CustomType.JUMP] == true) {
            val velocity = player.velocity
            velocity.y += 1
            player.velocity = velocity
        }
    }
}