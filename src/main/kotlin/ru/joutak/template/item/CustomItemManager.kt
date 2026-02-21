package ru.joutak.template.item

import net.kyori.adventure.text.Component
import net.kyori.adventure.text.format.TextDecoration
import org.bukkit.Material
import org.bukkit.NamespacedKey
import org.bukkit.enchantments.Enchantment
import org.bukkit.inventory.ItemFlag
import org.bukkit.inventory.ItemStack
import org.bukkit.persistence.PersistentDataContainer
import org.bukkit.persistence.PersistentDataType
import org.bukkit.plugin.Plugin

class CustomItemManager(plugin: Plugin) {
    val key = NamespacedKey(plugin, "custom_item")

    fun create(customType: CustomType): ItemStack {
        when (customType) {
            CustomType.ARMOR -> {
                val item = ItemStack(Material.COPPER_INGOT)

                val meta = item.itemMeta

                meta.persistentDataContainer.set(key, PersistentDataType.INTEGER, customType.ordinal)
                meta.displayName(Component.text("Амулет защиты").decoration(TextDecoration.ITALIC, false))
                meta.lore(listOf(Component.text("Создаёт ощущение ").append(Component.text("безопасности").decoration(TextDecoration.OBFUSCATED, true))))
                meta.addEnchant(Enchantment.LURE, 1, true)
                meta.addItemFlags(ItemFlag.HIDE_ENCHANTS)

                item.setItemMeta(meta)

                return item
            }
            CustomType.HIT -> {
                val item = ItemStack(Material.IRON_INGOT)

                val meta = item.itemMeta

                meta.persistentDataContainer.set(key, PersistentDataType.INTEGER, customType.ordinal)
                meta.displayName(Component.text("Амулет силы").decoration(TextDecoration.ITALIC, false))
                meta.lore(listOf(Component.text("Издаёт странный импульс...")))
                meta.addEnchant(Enchantment.LURE, 1, true)
                meta.addItemFlags(ItemFlag.HIDE_ENCHANTS)

                item.setItemMeta(meta)

                return item
            }
            CustomType.JUMP -> {
                val item = ItemStack(Material.GOLD_INGOT)

                val meta = item.itemMeta

                meta.persistentDataContainer.set(key, PersistentDataType.INTEGER, customType.ordinal)
                meta.displayName(Component.text("Амулет прыжка").decoration(TextDecoration.ITALIC, false))
                meta.lore(listOf(Component.text("Норовит выпрыгнуть из рук!")))
                meta.addEnchant(Enchantment.LURE, 1, true)
                meta.addItemFlags(ItemFlag.HIDE_ENCHANTS)

                item.setItemMeta(meta)

                return item
            }
        }
    }

    fun check(pdc: PersistentDataContainer): CustomType? {
        return CustomType.entries.getOrNull(pdc.get(key, PersistentDataType.INTEGER) ?: return null)
    }
}