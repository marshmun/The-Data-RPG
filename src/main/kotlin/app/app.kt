package app

import models.Enemy
import models.Player
import models.PlayerClass
import services.InventoryService
import services.PlayerService
import services.SaveLoadService
import data.sampleItems
import java.util.Scanner

fun main() {
    val inventoryService = InventoryService()
    val playerService = PlayerService()
    val saveLoadService = SaveLoadService()
    val scanner = Scanner(System.`in`)

    // Character Creation
    println("Welcome to The Data RPG!")
    println("Enter your character's name:")
    val name = scanner.nextLine()

    println("Choose your class:")
    PlayerClass.values().forEachIndexed { index, playerClass ->
        println("${index + 1}. $playerClass")
    }
    val classChoice = scanner.nextLine().toIntOrNull()
    val playerClass = if (classChoice != null && classChoice in 1..PlayerClass.values().size) {
        PlayerClass.values()[classChoice - 1]
    } else {
        println("Invalid choice. Defaulting to WARRIOR.")
        PlayerClass.WARRIOR
    }

    val player = Player(name = name, playerClass = playerClass)

    println("\nWelcome, ${player.name} the ${player.playerClass}!")

    // Add sample items to inventory
    sampleItems.forEach { item ->
        inventoryService.addItem(player, item)
    }

    // Main game loop
    gameLoop@ while (player.health > 0) {
        println(
            """
            |------------------------------
            |Choose an action:
            |1. View Stats
            |2. View Inventory
            |3. Use Item
            |4. Fight Enemy
            |5. Save Game
            |6. Load Game
            |7. Exit Game
            |------------------------------
            |Enter your choice:
            """.trimMargin()
        )

        when (scanner.nextLine().trim()) {
            "1" -> playerService.viewStats(player)
            "2" -> inventoryService.listInventory(player)
            "3" -> {
                if (player.inventory.isEmpty()) {
                    println("Your inventory is empty.")
                } else {
                    println("Enter the number of the item you want to use:")
                    inventoryService.listInventory(player)
                    val itemChoice = scanner.nextLine().toIntOrNull()
                    if (itemChoice != null && itemChoice in 1..player.inventory.size) {
                        val item = player.inventory[itemChoice - 1]
                        inventoryService.useItem(player, item)
                    } else {
                        println("Invalid item choice.")
                    }
                }
            }
            "4" -> {
                val enemy = generateRandomEnemy()
                println("\nA wild ${enemy.name} appears!")
                playerService.attackEnemy(player, enemy)
            }
            "5" -> {
                println("Enter the filename to save the game (default is 'savegame.json'):")
                val filename = scanner.nextLine().trim()
                if (filename.isBlank()) {
                    saveLoadService.savePlayer(player)
                } else {
                    saveLoadService.savePlayer(player, filename)
                }
            }
            "6" -> {
                println("Enter the filename to load the game (default is 'savegame.json'):")
                val filename = scanner.nextLine().trim()
                val loadedPlayer = if (filename.isBlank()) {
                    saveLoadService.loadPlayer()
                } else {
                    saveLoadService.loadPlayer(filename)
                }
                if (loadedPlayer != null) {
                    // Update the current player state
                    player.name = loadedPlayer.name
                    player.level = loadedPlayer.level
                    player.experience = loadedPlayer.experience
                    player.playerClass = loadedPlayer.playerClass
                    player.health = loadedPlayer.health
                    player.equippedWeapon = loadedPlayer.equippedWeapon
                    player.equippedArmor = loadedPlayer.equippedArmor
                    player.inventory.clear()
                    player.inventory.addAll(loadedPlayer.inventory)
                    println("Game state updated.")
                }
            }
            "7" -> {
                println("Exiting game. Goodbye!")
                break@gameLoop
            }
            else -> println("Invalid choice. Please try again.")
        }

        println()
    }

    if (player.health <= 0) {
        println("Game Over. ${player.name} has fallen in battle.")
    } else {
        println("Thank you for playing! Goodbye!")
    }
}

fun generateRandomEnemy(): Enemy {
    val enemies = listOf(
        Enemy(name = "Goblin", health = 30, attackPower = 5, experienceReward = 50),
        Enemy(name = "Orc", health = 50, attackPower = 10, experienceReward = 100),
        Enemy(name = "Troll", health = 80, attackPower = 15, experienceReward = 200)
    )
    return enemies.random()
}
