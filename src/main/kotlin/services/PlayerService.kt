package services

import models.Player

class PlayerService {
    fun gainExperience(player: Player, amount: Int) {
        player.experience += amount
        while (player.experience >= experienceToLevelUp(player.level)) {
            player.experience -= experienceToLevelUp(player.level)
            player.level++
            println("${player.name} leveled up to level ${player.level}!")
        }
    }

    private fun experienceToLevelUp(level: Int): Int {
        return level * 100
    }
}
