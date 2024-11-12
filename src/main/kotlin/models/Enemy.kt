package models

import kotlinx.serialization.Serializable

@Serializable
data class Enemy(
    val name: String,
    var health: Int,
    val attackPower: Int,
    val experienceReward: Int
)
