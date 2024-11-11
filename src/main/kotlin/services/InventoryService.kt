package services

import models.Item
import models.Player


class InventoryService {
    fun addItem(player: Player, item: Item) {
        player.inventory.add(item)
        println("${item.name} has been added to ${player.name}'s inventory")
    }

    fun removeItem(player: Player, item: Item) {
        if (player.inventory.remove(item)) {
            println("${item.name} has been removed from ${player.name}'s inventory.")
        } else {
            println("${item.name} is not in ${player.name}'s inventory.")
        }
    }

    fun listInventory(player: Player) {
        if (player.inventory.isEmpty()) {
            println("${player.name}'s inventory is empty.")
        } else {
            println("${player.name}'s inventory:")
            player.inventory.forEach { item ->
                println("-${item.name} (${item.type})")
            }
        }
    }
}