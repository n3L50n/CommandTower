package com.nodecoyote.commandtower

import android.arch.persistence.room.ColumnInfo
import android.arch.persistence.room.Entity
import android.arch.persistence.room.PrimaryKey

data class Round (
        val id: String,
        val start: String,
        val end: String,
        val totalPlayers: List<Player>
)

@Entity
class RoundEntity{
    @PrimaryKey(autoGenerate = true)
    var id: Int = 0

    @ColumnInfo(name = "round_id")
    var roundId: Int = 0

    @ColumnInfo(name = "start")
    var start: String = ""

    @ColumnInfo(name = "")
    var end: String = ""

    /**
     * Use playerId/player_id to fetch players
     */
    @ColumnInfo(name = "total_players")
    var totalPlayers: List<String> = emptyList()

}