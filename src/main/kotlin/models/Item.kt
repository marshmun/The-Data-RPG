package models

import kotlinx.serialization.Serializable

@Serializable
data class Item(
    val name: String,
    val type: ItemType,
    val description: String ="",
    val value: Int = 0
)