package com.nodecoyote.commandtower

data class Round (
        val id: String,
        val start: String,
        val end: String,
        val totalPlayers: List<Player>
)