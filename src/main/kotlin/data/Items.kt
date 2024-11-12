package data

import models.Item
import models.ItemType

val sampleItems = listOf(
    Item(
        name = "Sword of Truth",
        type = ItemType.WEAPON,
        description = "A legendary sword with immense power.",
        value = 100
    ),
    Item(
        name ="Healing Potion",
        type = ItemType.CONSUMABLE,
        description = "Restores 50 health points.",
        value = 25
    ),
    Item(
        name = "Leather Armor",
        type = ItemType.ARMOR,
        description = "Basic protection against attacks.",
        value = 50
    ),
    Item(
        name = "Ancient Key",
        type = ItemType.KEY_ITEM,
        description = "Opens the door to the hidden chamber.",
        value = 0
    )
)