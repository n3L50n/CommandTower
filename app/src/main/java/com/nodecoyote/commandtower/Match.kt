package com.nodecoyote.commandtower


data class Match (
        val id: String,
        val start: String,
        val end: String,
        val totalRounds: List<Round>,
        val players: List<Player>
)