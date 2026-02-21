package ru.joutak.template.data

import kotlinx.serialization.Serializable

@Serializable
data class PlayerData(
    val health: Double,
    val items: String,
    val time: Long
)