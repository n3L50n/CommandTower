package com.nodecoyote.commandtower

import android.os.Build
import android.os.Bundle
import android.support.annotation.RequiresApi
import android.support.v4.app.Fragment
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewAnimationUtils
import android.view.ViewGroup
import io.reactivex.Flowable
import kotlinx.android.synthetic.main.fragment_create_player.*
import java.util.concurrent.TimeUnit

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

        Log.v("Create", "Creating Fragment")

        if (Build.VERSION.SDK_INT >= 21) {
            animate(itemView)
        }
        create_player_container_layout.isSelected = false
    }

    @RequiresApi(Build.VERSION_CODES.LOLLIPOP)
    private fun animate(itemView: View){
        val factor = 2
        val finalRadius = Math.hypot(itemView.width.toDouble(), itemView.height.toDouble()).toInt()

        val animator = ViewAnimationUtils.createCircularReveal (
                itemView,
                itemView.width / factor,
                itemView.height / factor,
                0f,
                finalRadius.toFloat()
        )
                .setDuration(900)

        animator.start()

    }

}