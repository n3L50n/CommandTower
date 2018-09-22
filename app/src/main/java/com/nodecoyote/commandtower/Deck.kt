package com.nodecoyote.commandtower

data class Deck (
        val id: String,
        val name: String,
        val icon: String,
        val wins: Int,
        val losses: Int,
        val draw: Int
)