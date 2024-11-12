package models

import kotlinx.serialization.Serializable

@Serializable
enum class ItemType{
    WEAPON,
    ARMOR,
    CONSUMABLE,
    KEY_ITEM
}