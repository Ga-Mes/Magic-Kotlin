package ru.joutak.template.command

import org.bukkit.command.Command
import org.bukkit.command.CommandExecutor
import org.bukkit.command.CommandSender
import org.bukkit.entity.Player
import ru.joutak.template.data.PlayerDataManager
import ru.joutak.template.item.CustomItemManager
import ru.joutak.template.item.CustomType

class CustomGiveCommand(private val playerDataManager: PlayerDataManager, private val customItemManager: CustomItemManager) : CommandExecutor {
    override fun onCommand(sender: CommandSender, command: Command, entered: String, args: Array<out String>): Boolean {
        if (sender is Player) {
            if (command.name.equals("customgive", ignoreCase = true)) {
                if (args.size == 1) {
                    val index = args[0].toIntOrNull()

                    val customType = index?.let { CustomType.entries.getOrNull(it) }

                    if (customType != null) {
                        sender.inventory.addItem(customItemManager.create(customType))
                        playerDataManager.update(sender)
                        return true
                    }
                }
            }
        }

        return false
    }
}