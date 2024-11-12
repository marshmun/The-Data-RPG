package models

import kotlinx.serialization.Serializable

@Serializable
enum class PlayerClass{
    WARRIOR,
    MAGE,
    ARCHER,
    ROGUE,
    PALADIN,
    WARLOCK
}