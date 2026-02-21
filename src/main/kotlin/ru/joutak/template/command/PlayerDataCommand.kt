package ru.joutak.template.command

import org.bukkit.command.Command
import org.bukkit.command.CommandExecutor
import org.bukkit.command.CommandSender
import org.bukkit.entity.Player
import ru.joutak.template.data.PlayerDataManager

class PlayerDataCommand(private val playerDataManager: PlayerDataManager) : CommandExecutor {
    override fun onCommand(sender: CommandSender, command: Command, entered: String, args: Array<out String>): Boolean {
        if (sender is Player) {
            if (command.name.equals("playerdata", ignoreCase = true)) {
                if (args.size == 1) {
                    if (args[0].equals("save", ignoreCase = true)) {
                        playerDataManager.save(sender)
                    }
                }
            }
        }

        return true
    }
}