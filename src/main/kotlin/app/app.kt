package app

import models.Item
import models.ItemType
import models.Player
import models.PlayerClass
import services.InventoryService

fun main() {
    val player = Player(name = "Hero", playerClass = PlayerClass.WARRIOR)
    val inventoryService = InventoryService()

    // Create items
    val sword = Item(name = "Sword", type = ItemType.WEAPON, attributes = mapOf("Attack" to 10))
    val shield = Item(name = "Shield", type = ItemType.ARMOR, attributes = mapOf("Defense" to 5))

    // Add items to player's inventory
    inventoryService.addItem(player, sword)
    inventoryService.addItem(player, shield)

    // List inventory
    inventoryService.listInventory(player)

    // Remove an item
    inventoryService.removeItem(player, sword)

    // List inventory again
    inventoryService.listInventory(player)
}