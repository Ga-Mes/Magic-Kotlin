package ru.joutak.template.data

import ru.joutak.template.item.CustomType
import java.time.Duration
import java.time.Instant

class PlayerDataFactory {

    fun create(
        health: Double,
        states: Map<CustomType, Boolean>,
        startTime: Instant,
        now: Instant
    ): PlayerData {

        val hadItems = mutableListOf<String>()

        for (type in states.keys) {
            if (states[type] == true) {
                hadItems.add("$type (${type.ordinal})")
            }
        }

        val items = hadItems.joinToString(", ")

        val minutes = Duration.between(startTime, now).toMinutes()

        return PlayerData(health, items, minutes)
    }
}