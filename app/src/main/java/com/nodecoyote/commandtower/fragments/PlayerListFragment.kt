package com.nodecoyote.commandtower.fragments

import android.animation.ObjectAnimator
import android.graphics.Point
import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.findNavController
import androidx.navigation.fragment.findNavController
import com.nodecoyote.commandtower.R
import kotlinx.android.synthetic.main.cell_player.view.*
import kotlinx.android.synthetic.main.fragment_player_list.*


class PlayerListFragment : Fragment() {

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
    }

    private fun setUpRecycler() {
        val playerRecyclerView: RecyclerView = playerRecyclerView
        val playerGridLayoutManager: RecyclerView.LayoutManager = LinearLayoutManager(context)
        val playerAdapter = PlayerAdapter()
        playerRecyclerView.layoutManager = playerGridLayoutManager
        playerRecyclerView.adapter = playerAdapter
    }

    private fun setUpMainButton() {
        main_button_container.setOnClickListener {
            this@PlayerListFragment.findNavController().navigate(R.id.action_playerList_to_createPlayerFragment)
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

}

class PlayerAdapter : RecyclerView.Adapter<PlayerViewHolder>() {

    private val players = listOf("node coyote", "khecks", "dino king")

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
    }
}