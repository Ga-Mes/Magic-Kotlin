package ru.joutak.template.data

import kotlinx.serialization.encodeToString
import kotlinx.serialization.json.Json
import net.kyori.adventure.text.Component
import org.bukkit.entity.Player
import org.bukkit.plugin.Plugin
import org.bukkit.scheduler.BukkitRunnable
import ru.joutak.template.item.CustomType
import java.io.File
import java.time.Duration
import java.time.Instant
import java.util.UUID

class Saver {
    val playersData: MutableMap<String, PlayerData>

    val json = Json { prettyPrint = true }

    val updater = object : BukkitRunnable() {
        override fun run() {
            update()
        }
    }

    val plugin: Plugin

    constructor(plugin: Plugin) {
        this.plugin = plugin

        val file = File(plugin.dataFolder, "playerdata.json")

        playersData =
            if (file.exists() && file.length() > 0) {
                json.decodeFromString(file.readText())
            } else {
                mutableMapOf()
            }

        updater.runTaskTimer(plugin, 0L, 1200L)
    }

    fun save(player: Player, states: MutableMap<UUID, MutableMap<CustomType, Boolean>>, times: MutableMap<UUID, Instant>) {
        val health = player.health

        val hadItems = mutableListOf<String>()

        for (type in states[player.uniqueId]!!.keys.iterator()) {
            if (states[player.uniqueId]!![type] == true) {
                hadItems.add("$type (${type.ordinal})")
            }
        }

        val items = hadItems.joinToString(", ")

        val time = Duration.between(times[player.uniqueId], Instant.now()).toMinutes()

        val playerData = PlayerData(health, items, time)

        playersData.put("${player.uniqueId}", playerData)
    }

    fun update() {
        val file = File(plugin.dataFolder, "playerdata.json")

        if (!check(file)) {
            return
        }

        file.writeText(json.encodeToString(playersData))
    }

    fun check(file: File): Boolean {
        if (!file.exists()) {
            try {
                val success = file.createNewFile()

                if (success) {
                    plugin.server.consoleSender.sendMessage(Component.text("[${plugin.pluginMeta.name}] Файл создан!"))
                } else {
                    plugin.server.consoleSender.sendMessage(Component.text("[${plugin.pluginMeta.name}] Не удалось создать файл с информацией игроков..."))
                    return false
                }
            } catch (e: Exception) {
                plugin.server.consoleSender.sendMessage(Component.text("[${plugin.pluginMeta.name}] Не удалось создать файл с информацией игроков (${e.message})..."))
                return false
            }
        }

        return true
    }

    fun stop() {
        updater.cancel()

        update()
    }
}