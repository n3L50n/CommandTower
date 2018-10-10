package com.nodecoyote.commandtower.fragments

import android.arch.lifecycle.ViewModel;
import com.nodecoyote.commandtower.Match
import com.nodecoyote.commandtower.Player
import com.nodecoyote.commandtower.Round
import io.reactivex.processors.BehaviorProcessor

class CurrentGameViewModel : ViewModel() {
    private val mRxMatch = BehaviorProcessor.createDefault(
            Match(
                    id = "",
                    start = "",
                    end = "",
                    totalRounds = emptyList(),
                    players = players()
            )
    )

    fun players(): List<Player> {
        return listOf(
                Player(
                        id = -1,
                        active = true,
                        auth = "",
                        avatar = "",
                        energy = false,
                        energyActive = false,
                        experience = false,
                        experienceActive = false,
                        life = 0,
                        commandPlayerLife = 0,
                        commandZoneTally = 0,
                        poison = false,
                        poisonActive = false,
                        champion = false,
                        friends = emptyList(),
                        createdOn = "",
                        currentMatch = "",
                        email = "",
                        graveyard = false,
                        name = "Robert Steven Heckey",
                        previousNames = emptyList(),
                        rank = 0L,
                        matches = emptyList(),
                        wins = 0,
                        currentDeck = null,
                        decks = emptyList(),
                        color = "",
                        currentEvent = "",
                        currentRound = "",
                        opponents = emptyList(),
                        events = emptyList(),
                        rounds = emptyList(),
                        phone = ""


                ),
                Player(
                        id = -1,
                        active = true,
                        auth = "",
                        avatar = "",
                        energy = false,
                        energyActive = false,
                        experience = false,
                        experienceActive = false,
                        life = 0,
                        commandPlayerLife = 0,
                        commandZoneTally = 0,
                        poison = false,
                        poisonActive = false,
                        champion = false,
                        friends = emptyList(),
                        createdOn = "",
                        currentMatch = "",
                        email = "",
                        graveyard = false,
                        name = "khecks",
                        previousNames = emptyList(),
                        rank = 0L,
                        matches = emptyList(),
                        wins = 0,
                        currentDeck = null,
                        decks = emptyList(),
                        color = "",
                        currentEvent = "",
                        currentRound = "",
                        opponents = emptyList(),
                        events = emptyList(),
                        rounds = emptyList(),
                        phone = ""


                ),
                Player(
                        id = -1,
                        active = true,
                        auth = "",
                        avatar = "",
                        energy = false,
                        energyActive = false,
                        experience = false,
                        experienceActive = false,
                        life = 0,
                        commandPlayerLife = 0,
                        commandZoneTally = 0,
                        poison = false,
                        poisonActive = false,
                        champion = false,
                        friends = emptyList(),
                        createdOn = "",
                        currentMatch = "",
                        email = "",
                        graveyard = false,
                        name = "node",
                        previousNames = emptyList(),
                        rank = 0L,
                        matches = emptyList(),
                        wins = 0,
                        currentDeck = null,
                        decks = emptyList(),
                        color = "",
                        currentEvent = "",
                        currentRound = "",
                        opponents = emptyList(),
                        events = emptyList(),
                        rounds = emptyList(),
                        phone = ""


                )
        )
    }
}
