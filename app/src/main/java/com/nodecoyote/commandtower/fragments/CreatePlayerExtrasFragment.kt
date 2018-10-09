package com.nodecoyote.commandtower.fragments

import android.os.Bundle
import android.support.v4.app.Fragment
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.nodecoyote.commandtower.Keys
import com.nodecoyote.commandtower.R
import kotlinx.android.synthetic.main.fragment_create_player_extras.*

class CreatePlayerExtrasFragment: Fragment() {

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        if (container == null) {
            return null
        }
        return inflater.inflate(R.layout.fragment_create_player_extras, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        arguments?.let {
            val position = it.getInt(Keys.AvatarSelectedIconPosition, -1)
            imageLoader(create_player_extras_avatar, position, false)
        }
    }

}