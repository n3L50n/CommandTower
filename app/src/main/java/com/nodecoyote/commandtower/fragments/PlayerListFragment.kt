package com.nodecoyote.commandtower.fragments

import android.animation.ObjectAnimator
import android.animation.ValueAnimator
import android.graphics.Point
import android.os.Build
import android.os.Bundle
import android.support.annotation.RequiresApi
import android.support.design.animation.ArgbEvaluatorCompat
import android.support.v4.app.Fragment
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewAnimationUtils
import android.view.ViewGroup
import androidx.navigation.fragment.findNavController
import com.nodecoyote.commandtower.R
import kotlinx.android.synthetic.main.cell_player.view.*
import kotlinx.android.synthetic.main.fragment_player_list.*
import android.animation.Animator
import android.animation.AnimatorListenerAdapter


class PlayerListFragment : Fragment() {

    private val players = listOf("node coyote", "khecks", "dino king")

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        if (container == null) {
            return null
        }
        return inflater.inflate(R.layout.fragment_player_list, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setUpRecycler()
        animateMainButton()
        setUpMainButton()
        animateSecondaryButton()
    }

    private fun setUpRecycler() {
        val playerRecyclerView: RecyclerView = playerRecyclerView
        val playerGridLayoutManager: RecyclerView.LayoutManager = LinearLayoutManager(context)
        val playerAdapter = PlayerAdapter(players)
        playerRecyclerView.layoutManager = playerGridLayoutManager
        playerRecyclerView.adapter = playerAdapter
    }

    private fun setUpMainButton() {
        main_button_container.setOnClickListener {
            this@PlayerListFragment.findNavController().navigate(R.id.createPlayerAction)
        }
    }

    private fun animateMainButton() {

        val point = Point()
        activity?.windowManager?.defaultDisplay?.getSize(point)
        val screenWidth = point.x.toFloat()
        val screenHeight = point.y.toFloat()

        val xAnimator = ObjectAnimator.ofFloat(
                main_button_container,
                "translationX",
                screenWidth,
                screenWidth - (screenWidth/1.5f)

        )

        val yAnimator = ObjectAnimator.ofFloat(
                main_button_container,
                "translationY",
                screenHeight,
                screenHeight - (screenHeight/3)
        )

        yAnimator.duration = 1200
        yAnimator.start()

        xAnimator.duration = 1200
        xAnimator.start()
    }

    private fun animateSecondaryButton() {

        val point = Point()
        activity?.windowManager?.defaultDisplay?.getSize(point)
        val screenWidth = point.x.toFloat()
        val screenHeight = point.y.toFloat()

        val xAnimator = ObjectAnimator.ofFloat(
                secondary_button_container,
                "translationX",
                screenWidth,
                screenWidth - (screenWidth/2f)

        )

        val yAnimator = ObjectAnimator.ofFloat(
                secondary_button_container,
                "translationY",
                screenHeight,
                screenHeight - (screenHeight/2)
        )

        yAnimator.duration = 1200
        yAnimator.start()

        xAnimator.duration = 1200
        xAnimator.start()
    }

}

class PlayerAdapter(private val players: List<String>): RecyclerView.Adapter<PlayerViewHolder>() {



    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PlayerViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.cell_player, parent, false)
        return PlayerViewHolder(view)
    }

    override fun getItemCount(): Int {
        return players.count()
    }

    override fun onBindViewHolder(holder: PlayerViewHolder, position: Int) {
        holder.bindView(players[position])
    }

}

class PlayerViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

    fun bindView(format: String) {
        itemView.playerName.text = format
        itemView.setOnClickListener {
            itemView.isActivated = !itemView.isActivated
        }
    }
}