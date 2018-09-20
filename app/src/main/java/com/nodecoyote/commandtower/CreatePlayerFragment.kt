package com.nodecoyote.commandtower

import android.os.Build
import android.os.Bundle
import android.support.v4.app.Fragment
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewAnimationUtils
import android.view.ViewGroup
import kotlinx.android.synthetic.main.fragment_create_player.*

class CreatePlayerFragment: Fragment() {

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        if (container == null) {
            return null
        }
        return inflater.inflate(R.layout.fragment_create_player, container, false)
    }

    override fun onViewCreated(itemView: View, savedInstanceState: Bundle?) {
        super.onViewCreated(itemView, savedInstanceState)
        create_player_container_layout.isSelected = true
        val factor = 2
        val finalRadius = Math.hypot(itemView.width.toDouble(), itemView.height.toDouble()).toInt()

        Log.v("Create", "Creating Fragment")

        if (Build.VERSION.SDK_INT >= 21) {

            val animator = ViewAnimationUtils.createCircularReveal (
                    itemView,
                    itemView.width / factor,
                    itemView.height / factor,
                    0f,
                    finalRadius.toFloat()
            )
            animator.start()
        }

    }

}