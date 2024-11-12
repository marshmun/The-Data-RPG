package services

import models.Player
import models.Enemy

class PlayerService {

    fun gainExperience(player: Player, amount:Int){
        player.experience += amount
        println("${player.name} gains $amount experience points.")

        checkLevelUp(player)
    }
    private fun checkLevelUp(player: Player) {
        val experienceThreshold = player.level * 100
        if (player.experience >= experienceThreshold) {
            player.level+=1
            player.experience -= experienceThreshold
            player.health += 20
            println("Congratulations ${player.name} has reached level ${player.level}. Health increased to ${player.health}.")
        }
    }
    fun viewStats(player: Player){
        println(
            """
            |------------------------------
            |Player Stats:
            |Name: ${player.name}
            |Class: ${player.playerClass}
            |Level: ${player.level}
            |Experience: ${player.experience}/${player.level * 100}
            |Health: ${player.health}
            |Inventory Size: ${player.inventory.size}
            |------------------------------
            """.trimMargin()
        )
    }
    fun attackEnemy(player: Player, enemy: Enemy){
        val damageDealt = playerLevelAttackpower(player)
        println("${player.name} attacks ${enemy.name} for $damageDealt damage.")
        enemy.health -= damageDealt
        if (enemy.health <= 0){
            println("${enemy.name} has been defeated.")
            gainExperience(player, enemy.experienceReward)
        }else{
            println("${enemy.name} has ${enemy.health} health remaining.")
            enemyCounterAttack(player, enemy)
        }
    }
    private fun playerLevelAttackpower(player: Player): Int {
        return 10 + (player.level *2)
    }
    private fun enemyCounterAttack(player: Player, enemy: Enemy){
        println("${enemy.name} counter-attacks ${player.name} for ${enemy.attackPower} damage.")
        player.health -= enemy.attackPower
        if (player.health <= 0){
            println("${player.name} has been defeated by ${enemy.name}...")
    }else{
            println("${player.name}'s health is now ${player.health}.")
        }
    }
}
