package com.nodecoyote.commandtower

import android.arch.persistence.room.ColumnInfo
import android.arch.persistence.room.Entity
import android.arch.persistence.room.PrimaryKey


data class Player (
        val id: Int,
        val name: String,
        val avatar: String,
        val auth: String?,
        val life: Int,
        val commandPlayerLife: Int,
        val commandZoneTally: Int,
        val createdOn: String,
        val phone: String?,
        val email: String?,
        val color: String?,
        val active: Boolean,
        val graveyard: Boolean,
        val poison: Boolean,
        val energy: Boolean,
        val experience: Boolean,
        val poisonActive: Boolean,
        val energyActive: Boolean,
        val experienceActive: Boolean,
        val champion: Boolean,
        val rank: Long?,
        val wins: Int,
        val opponents: List<String>,
        val friends: List<String>,
        val decks: List<Deck>,
        val currentDeck: Deck,
        val currentRound: String,
        val currentMatch: String,
        val currentEvent: String,
        val rounds: List<Round>,
        val matches: List<Match>,
        val events: List<Event>
)

interface PlayerService {
    val playerService
        get() = PlayerServiceObject.service
}

class PlayerServiceObject{
    companion object {
        val service = PlayerServiceImplemented()
    }
}

class PlayerServiceImplemented {

    fun addPlayer(avatar: String) {
        val entity = PlayerEntity()
        entity.avatar = avatar
    }

}

@Entity(tableName = "players")
class PlayerEntity {
    @PrimaryKey(autoGenerate = true)
    var id: Int = 0

    @ColumnInfo(name = "player_id")
    var playerId: String = ""

    @ColumnInfo(name = "name")
    var name: String = ""

    @ColumnInfo(name = "phone")
    var phone: String = ""

    @ColumnInfo(name = "email")
    var email: String = ""

    @ColumnInfo(name = "avatar")
    var avatar: String = ""

    @ColumnInfo(name = "life")
    var life: Int = 20

    @ColumnInfo(name = "command_player_life")
    var commandPlayerLife: Int = 40

    @ColumnInfo(name = "command_zone_tally")
    var commandZoneTally: Int = 0

    @ColumnInfo(name ="active")
    var active: Boolean = false

    @ColumnInfo(name = "graveyard")
    var graveyard: Boolean = false

    @ColumnInfo(name = "poison")
    var poison: Int = 0

    @ColumnInfo(name = "energy")
    var energy: Int = 0

    @ColumnInfo(name = "experience")
    var experience: Int = 0

    @ColumnInfo(name = "experience_active")
    var experienceActive: Boolean = false

    @ColumnInfo(name = "poison_active")
    var poisonActive: Boolean = false

    @ColumnInfo(name = "energy_active")
    var energyPoints: Boolean = false

    @ColumnInfo(name = "wins")
    var wins: Int = 0

    @ColumnInfo(name = "created_on")
    var createdOn: String = ""

    @ColumnInfo(name = "color")
    var color: String? = ""

    @ColumnInfo(name = "champion")
    var champion: Boolean = false

    @ColumnInfo(name = "friends")
    var friends: List<String> = emptyList()

    @ColumnInfo(name = "opponents")
    var opponents: List<String> = emptyList()

    @ColumnInfo(name = "decks")
    var decks: List<String> = emptyList()

    @ColumnInfo(name = "rank")
    var rank: Long? = -1

    @ColumnInfo(name = "")
    var currentDeck = DeckEntity()

    @ColumnInfo(name = "current_round")
    var currentRound = RoundEntity()

    @ColumnInfo(name = "current_match")
    var currentMatch = MatchEntity()

    @ColumnInfo(name = "current_event")
    val currentEvent: String = ""

    @ColumnInfo(name = "rounds")
    var rounds: List<RoundEntity> = emptyList()

    @ColumnInfo(name = "matches")
    var matches: List<Match> = emptyList()

    @ColumnInfo(name = "events")
    var events: List<Event> = emptyList()

//    fun toModel(): Player {
//
//        return Player(
//                id = id,
//                name = name,
//                life = life,
//                avatar = avatar,
//                commandPlayerLife = commandPlayerLife,
//                commandZoneTally = commandZoneTally,
//                graveyard = graveyard,
//                wins = wins,
//                energy = energy,
//                experience = experience,
//                poison = poison,
//                decks = emptyList()
//        )
//    }


}


