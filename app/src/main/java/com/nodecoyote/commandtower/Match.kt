package com.nodecoyote.commandtower

import android.arch.persistence.room.Entity


data class Match (
        val id: String,
        val start: String,
        val end: String,
        val totalRounds: List<Round>,
        val players: List<Player>
)

@Entity
class MatchEntity{

}