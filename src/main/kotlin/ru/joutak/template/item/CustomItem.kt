package ru.joutak.template.item

import net.kyori.adventure.text.Component
import org.bukkit.Material
import org.bukkit.NamespacedKey
import org.bukkit.enchantments.Enchantment
import org.bukkit.inventory.ItemFlag
import org.bukkit.inventory.ItemStack
import org.bukkit.persistence.PersistentDataType
import org.bukkit.plugin.Plugin

class CustomItem {
    companion object {
        fun create(customType: CustomType, plugin: Plugin): ItemStack? {
            when (customType) {
                CustomType.HIT -> {
                    val item = ItemStack(Material.GOLD_NUGGET)

                    val meta = item.itemMeta

                    val key = NamespacedKey(plugin, "hit_item")

                    meta.persistentDataContainer.set(key, PersistentDataType.INTEGER, 0)
                    meta.displayName(Component.text("Кусочек бога"))
                    meta.lore(listOf<Component>(Component.text("Даёт носителю невероятную:"), Component.text("Резистентность")))
                    meta.addEnchant(Enchantment.LURE, 1, true)
                    meta.addItemFlags(ItemFlag.HIDE_ENCHANTS)
                }
                CustomType.ARMOR -> {
                    val item = ItemStack(Material.RESIN_BRICK)

                    val meta = item.itemMeta

                    val key = NamespacedKey(plugin, "armor_item")

                    meta.persistentDataContainer.set(key, PersistentDataType.INTEGER, 1)
                    meta.displayName(Component.text("Кусочек власти"))
                    meta.lore(listOf<Component>(Component.text("Даёт носителю невероятную:"), Component.text("Силу")))
                    meta.addEnchant(Enchantment.LURE, 1, true)
                    meta.addItemFlags(ItemFlag.HIDE_ENCHANTS)
                }
                CustomType.JUMP -> {
                    val item = ItemStack(Material.AMETHYST_SHARD)

                    val meta = item.itemMeta

                    val key = NamespacedKey(plugin, "jump_item")

                    meta.persistentDataContainer.set(key, PersistentDataType.INTEGER, 2)
                    meta.displayName(Component.text("Кусочек неба"))
                    meta.lore(listOf<Component>(Component.text("Даёт носителю невероятную:"), Component.text("Прыгучесть")))
                    meta.addEnchant(Enchantment.LURE, 1, true)
                    meta.addItemFlags(ItemFlag.HIDE_ENCHANTS)
                }
            }

            return null
        }
    }
}