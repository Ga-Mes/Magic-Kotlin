package ru.joutak.template

import org.bukkit.configuration.file.YamlConfiguration
import org.bukkit.plugin.java.JavaPlugin
import ru.joutak.template.data.PlayerDataManager
import ru.joutak.template.event.JoinEvent
import ru.joutak.template.event.QuitEvent
import ru.joutak.template.item.CustomItemManager
import java.io.File

class Magic : JavaPlugin() {
    companion object {
        @JvmStatic
        lateinit var instance: Magic
    }

    private var customConfig = YamlConfiguration()

    private fun loadConfig() {
        val fx = File(dataFolder, "config.yml")
        if (!fx.exists()) {
            saveResource("config.yml", true)
        }
    }

    /**
     * Plugin startup logic
     */
    override fun onEnable() {
        instance = this

        loadConfig()

        val customItemManager = CustomItemManager(instance)
        val playerDataManager = PlayerDataManager(instance, customItemManager)

        // Register commands and events
        server.pluginManager.registerEvents(JoinEvent(instance, playerDataManager), instance)
        server.pluginManager.registerEvents(QuitEvent(playerDataManager), instance)

        logger.info("Плагин ${pluginMeta.name} версии ${pluginMeta.version} включен!")
    }

    /**
     * Plugin shutdown logic
     */
    override fun onDisable() {
    }
}
