package models

import kotlinx.serialization.Serializable
import models.Item

@Serializable
data class Player(
    var name: String,
    var level: Int =1,
    var experience: Int = 0,
    var playerClass: PlayerClass,
    var health: Int = 100,
    var equippedWeapon: Item? = null,
    var equippedArmor: Item? = null,
    val inventory: MutableList<Item> = mutableListOf()
)