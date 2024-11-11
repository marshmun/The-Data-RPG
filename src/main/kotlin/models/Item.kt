package models

data class Item(
    val name: String,
    val type: ItemType,
    val attributes: Map<String, Int>
)