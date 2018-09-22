package com.nodecoyote.commandtower

import android.arch.persistence.room.ColumnInfo
import android.arch.persistence.room.Entity
import android.arch.persistence.room.PrimaryKey


data class Player (
        val id: String,
        val name: String,
        val icon: String,
        val auth: String?,
        val createdOn: String,
        val phone: String?,
        val email: String?,
        val color: String?,
        val active: Boolean,
        val graveyard: Boolean,
        val poison: Boolean,
        val energy: Boolean,
        val experience: Boolean,
        val poisonPoints: Int,
        val energyPoints: Int,
        val experiencePoints: Int,
        val champion: Boolean,
        val rank: Long?,
        val opponents: List<Player>,
        val friends: List<Player>,
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

    }

}

@Entity(tableName = "players")
class PlayerEntity {
    @PrimaryKey(autoGenerate = true)
    var id: Int = 0

    @ColumnInfo(name = "player_name")
    var playerName: String = ""

    @ColumnInfo(name = "player_life")
    var playerLife: Int = 20

    @ColumnInfo(name = "command_player_life")
    var commandPlayerLife: Int = 40

    @ColumnInfo(name = "player_icon")
    var playerIcon: Int = 0

    @ColumnInfo(name = "command_zone_tally")
    var commandZoneTally: Int = 0

    @ColumnInfo(name = "graveyard")
    var graveyard: Int = 0

    @ColumnInfo(name = "win_count")
    var winCount: Int = 0

    @ColumnInfo(name = "energy")
    var energy: Int = 0

    @ColumnInfo(name = "experience")
    var experience: Int = 0

    @ColumnInfo(name = "poison")
    var poison: Int = 0

//    fun toModel(): Player {
//
//        return Player(
//                id = id,
//                playerName = playerName,
//                playerLife = playerLife,
//                playerIcon = playerIcon,
//                commandPlayerLife = commandPlayerLife,
//                commandZoneTally = commandZoneTally,
//                graveyard = graveyard,
//                winCount = winCount,
//                energy = energy,
//                experience = experience,
//                poison = poison,
//                opponents = opponents,
//                tokens = tokens,
//                decks = emptyList()
//        )
//    }


}


