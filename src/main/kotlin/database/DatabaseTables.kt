package database

import models.ItemType
import org.jetbrains.exposed.sql.Table
import org.jetbrains.exposed.dao.id.IntIdTable
import org.jetbrains.exposed.sql.ReferenceOption


object Players : IntIdTable() {
    val name = varchar("name", 50)
    val level = integer("level").default(1)
    val experience = integer("experience").default(0)
    val playerClass = varchar("player_class", 20)
    val health = integer("health").default(100)
}

object Items : IntIdTable() {
    val name = varchar("name", 50)
    val type = enumerationByName("type", 20, ItemType::class)
    val description = text("description").nullable()
    val value = integer("value").default(0)
    val playerId = integer("player_id").references(Players.id, onDelete = ReferenceOption.CASCADE, onUpdate = ReferenceOption.CASCADE)
}

object Quests : IntIdTable() {
    val name = varchar("name", 100)
    val description = text("description")
    val experienceReward = integer("experience_reward")
    val itemRewardId = reference("item_reward_id", Items, onDelete = ReferenceOption.SET_NULL, onUpdate = ReferenceOption.CASCADE).nullable()
    val isCompleted = bool("is_completed").default(false)
}

object PlayerQuests : Table() {
    val playerId = integer("player_id").references(Players.id, onDelete = ReferenceOption.CASCADE, onUpdate = ReferenceOption.CASCADE)
    val questId = integer("quest_id").references(Quests.id, onDelete = ReferenceOption.CASCADE, onUpdate = ReferenceOption.CASCADE)
    override val primaryKey = PrimaryKey(playerId, questId, name = "PK_PlayerQuest")
}
