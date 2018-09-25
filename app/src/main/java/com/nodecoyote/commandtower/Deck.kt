package com.nodecoyote.commandtower

import android.arch.persistence.room.ColumnInfo
import android.arch.persistence.room.Entity
import android.arch.persistence.room.PrimaryKey

data class Deck (
        val id: Int,
        val deckId: String,
        val name: String,
        val icon: String,
        val wins: Int,
        val losses: Int,
        val ties: Int,
        val cards: List<String>
)

@Entity
class DeckEntity{

    @PrimaryKey(autoGenerate = true)
    var id: Int = -1

    @ColumnInfo(name = "deck_id")
    var deckId: String = ""

    @ColumnInfo(name = "name")
    var name: String = ""

    @ColumnInfo(name = "")
    var icon: String = ""

    @ColumnInfo(name = "wins")
    var wins: Int = 0

    @ColumnInfo(name = "losses")
    var losses: Int = 0

    @ColumnInfo(name = "ties")
    var ties: Int = 0

    @ColumnInfo(name = "cards")
    var cards: List<String> = emptyList()

}