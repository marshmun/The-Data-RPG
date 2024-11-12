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
            player.inventory.forEachIndexed() { index, item ->
                println("-${index + 1}. ${item.name} (${item.type}) )")
            }
        }
    }
    fun useItem(player: Player, item: Item){
        if (player.inventory.contains(item)){
            when(item.type) {
                models.ItemType.CONSUMABLE -> {
                    println("${player.name} uses ${item.name}. Effect applied!")
                    removeItem(player, item)
                }

                models.ItemType.WEAPON -> {
                    println("${item.name} is equipped as your weapon.")
                }

                models.ItemType.ARMOR -> {
                    println("${item.name} is equipped as your armor.")
                }

                models.ItemType.KEY_ITEM -> {
                    println("${item.name} is a key item and cannot be used directly.")
                }
            }
        }else{
            println("${item.name} is not in your inventory.")
        }
    }
}