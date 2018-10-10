package com.nodecoyote.commandtower.fragments

import android.animation.ObjectAnimator
import android.graphics.Point
import android.os.Bundle
import android.support.v4.app.Fragment
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.fragment.findNavController
import com.nodecoyote.commandtower.Keys
import com.nodecoyote.commandtower.R
import kotlinx.android.synthetic.main.fragment_create_player_extras.*
import kotlinx.android.synthetic.main.fragment_main_button.view.*

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
        animateButton(save_button_container, 2.2f)
        setUpMainButton()
    }

    private fun setUpMainButton() {
        save_button_container.setOnClickListener {
            savePlayer()
            this@CreatePlayerExtrasFragment.findNavController().navigate(R.id.playerListAction)
        }
        save_button_container.create_player_text_view.text = resources.getString(R.string.save_player)
    }

    private fun savePlayer(){

    }

    private fun animateButton(container: View, x: Float) {

        val point = Point()
        activity?.windowManager?.defaultDisplay?.getSize(point)
        val screenWidth = point.x.toFloat()

        val xAnimator = ObjectAnimator.ofFloat(
                container,
                "translationX",
                screenWidth,
                screenWidth - (screenWidth/x)

        )

        xAnimator.duration = 1200
        xAnimator.start()
    }

}