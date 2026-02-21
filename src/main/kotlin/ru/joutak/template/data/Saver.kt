package ru.joutak.template.data

import kotlinx.serialization.encodeToString
import kotlinx.serialization.json.Json
import net.kyori.adventure.text.Component
import org.bukkit.entity.Player
import org.bukkit.plugin.Plugin
import java.io.File
import java.time.Duration
import java.time.Instant

class Saver(private val plugin: Plugin, private val playerDataManager: PlayerDataManager) {
    val json = Json { prettyPrint = true }

    fun save(player: Player) {
        val file = File(plugin.dataFolder, "playerdata.json")

        if (!file.exists()) {
            try {
                val success = file.createNewFile()

                if (success) {
                    plugin.server.consoleSender.sendMessage(Component.text("[${plugin.pluginMeta.name}] Файл создан!"))
                } else {
                    plugin.server.consoleSender.sendMessage(Component.text("[${plugin.pluginMeta.name}] Не удалось создать файл с информацией игроков..."))
                    return
                }
            } catch (e: Exception) {
                plugin.server.consoleSender.sendMessage(Component.text("[${plugin.pluginMeta.name}] Не удалось создать файл с информацией игроков..."))
                return
            }
        }

        val health = player.health

        val hadItems = mutableListOf<String>()

        for (type in playerDataManager.states[player.uniqueId]!!.keys.iterator()) {
            if (playerDataManager.states[player.uniqueId]!![type] == true) {
                hadItems.add("$type")
            }
        }

        val items = hadItems.joinToString(", ")

        val timeDifference = Duration.between(playerDataManager.times[player.uniqueId], Instant.now()).toMinutes()

        val playerData = PlayerData(health, items, timeDifference)

        val players: MutableMap<String, PlayerData> =
            if (file.exists() && file.length() > 0) {
                Json.decodeFromString(file.readText())
            } else {
                mutableMapOf()
            }

        players.put("${player.uniqueId}", playerData)

        file.writeText(json.encodeToString(players))
    }
}