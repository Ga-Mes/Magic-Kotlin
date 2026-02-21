package ru.joutak.template.event

import org.bukkit.event.EventHandler
import org.bukkit.event.Listener
import org.bukkit.event.world.LootGenerateEvent
import ru.joutak.template.item.CustomItemManager
import ru.joutak.template.item.CustomType

class GenerateEvent(private val customItemManager: CustomItemManager) : Listener {

    @EventHandler
    fun onGenerate(event: LootGenerateEvent) {
        val structure = event.lootTable.key.toString()

        when (structure) {
            "minecraft:chests/jungle_temple" -> {
                if (Math.random() < 0.3) {
                    event.loot.add(customItemManager.create(CustomType.ARMOR))
                }
            }
            "minecraft:chests/woodland_mansion" -> {
                if (Math.random() < 0.4) {
                    event.loot.add(customItemManager.create(CustomType.HIT))
                }
            }
            "minecraft:chests/buried_treasure" -> {
                if (Math.random() < 0.5) {
                    event.loot.add(customItemManager.create(CustomType.JUMP))
                }
            }
        }
    }
}