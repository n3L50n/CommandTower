package com.nodecoyote.commandtower

data class Event (
        val id: String,
        val type: String,
        val start: String,
        val end: String,
        val host: Player?,
        val totalMatches: List<Match>,
        val completedMatches: List<Match>,
        val players: List<Player>
)