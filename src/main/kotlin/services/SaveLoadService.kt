package services

import kotlinx.serialization.decodeFromString
import kotlinx.serialization.encodeToString
import kotlinx.serialization.json.Json
import models.Player
import java.io.File

class SaveLoadService {

    private val json = Json { prettyPrint = true }

    /**
     * Saves the current state of the player to a JSON file.
     *
     * @param player The player instance to save.
     * @param filename The name of the file to save to. Defaults to "savegame.json".
     */
    fun savePlayer(player: Player, filename: String = "savegame.json") {
        val jsonString = json.encodeToString(player)
        File(filename).writeText(jsonString)
        println("Game saved to '$filename'.")
    }

    /**
     * Loads the player state from a JSON file.
     *
     * @param filename The name of the file to load from. Defaults to "savegame.json".
     * @return The loaded player instance, or null if loading fails.
     */
    fun loadPlayer(filename: String = "savegame.json"): Player? {
        return try {
            val jsonString = File(filename).readText()
            val player = json.decodeFromString<Player>(jsonString)
            println("Game loaded from '$filename'.")
            player
        } catch (e: Exception) {
            println("Failed to load game: ${e.message}")
            null
        }
    }
}
