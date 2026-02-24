package ru.joutak.template

import org.bukkit.configuration.file.YamlConfiguration
import org.bukkit.plugin.java.JavaPlugin
import ru.joutak.template.command.CustomGiveCommand
import ru.joutak.template.command.PlayerDataCommand
import ru.joutak.template.data.PlayerDataManager
import ru.joutak.template.event.DamageEvent
import ru.joutak.template.event.GenerateEvent
import ru.joutak.template.event.InventoryChangeEvent
import ru.joutak.template.event.JoinEvent
import ru.joutak.template.event.JumpEvent
import ru.joutak.template.event.QuitEvent
import ru.joutak.template.item.CustomItemManager
import java.io.File

class Magic : JavaPlugin() {
    companion object {
        @JvmStatic
        lateinit var instance: Magic
        lateinit var playerDataManager: PlayerDataManager
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

        playerDataManager = PlayerDataManager(instance, customItemManager)

        // Register commands and events
        getCommand("playerdata")?.setExecutor(PlayerDataCommand(playerDataManager))
        getCommand("customgive")?.setExecutor(CustomGiveCommand(playerDataManager, customItemManager))

        server.pluginManager.registerEvents(JoinEvent(playerDataManager), instance)
        server.pluginManager.registerEvents(QuitEvent(playerDataManager), instance)
        server.pluginManager.registerEvents(GenerateEvent(customItemManager), instance)
        server.pluginManager.registerEvents(InventoryChangeEvent(playerDataManager, instance), instance)
        server.pluginManager.registerEvents(JumpEvent(playerDataManager), instance)
        server.pluginManager.registerEvents(DamageEvent(playerDataManager), instance)

        logger.info("Плагин ${pluginMeta.name} версии ${pluginMeta.version} включен!")
    }

    /**
     * Plugin shutdown logic
     */
    override fun onDisable() {
        playerDataManager.fileSave()
    }
}
