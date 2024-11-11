package models

data class Player(
    val name: String,
    var level: Int =1,
    var experience: Int = 0,
    var playerClass: PlayerClass,
    var inventory: MutableList<Item> = mutableListOf()
)