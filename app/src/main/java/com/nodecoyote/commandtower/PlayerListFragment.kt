package com.nodecoyote.commandtower

import android.graphics.Point
import android.graphics.PointF
import android.graphics.Rect
import android.os.Bundle
import android.support.animation.DynamicAnimation
import android.support.animation.SpringAnimation
import android.support.animation.SpringForce
import android.support.v4.app.Fragment
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import kotlinx.android.synthetic.main.cell_player.view.*
import kotlinx.android.synthetic.main.fragment_player.*


class PlayerListFragment : Fragment() {

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        if (container == null) {
            return null
        }
        return inflater.inflate(R.layout.fragment_player, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setUpRecycler()
        val point = Point()
        activity?.windowManager?.defaultDisplay?.getSize(point)
        val x = point.x
        val y = point.y

        val spring = SpringForce(850.57f)
                .setDampingRatio(SpringForce.DAMPING_RATIO_MEDIUM_BOUNCY)
                .setStiffness(SpringForce.STIFFNESS_LOW)

        Log.v("SYYSTEM"," SYSTEM: ::: $x $y ")

        val xAnim = SpringAnimation(add_player_button_layout, DynamicAnimation.X)
                .setStartValue(x.toFloat())
                .setSpring(spring)

        val yAnim = SpringAnimation(add_player_button_layout, DynamicAnimation.Y)
                .setStartValue(y.toFloat())
                .setSpring(spring)

        xAnim.start()
        yAnim.start()
        add_player_button_layout.visibility = View.VISIBLE

    }

    private fun setUpRecycler() {
        val playerRecyclerView: RecyclerView = playerRecyclerView
        val playerGridLayoutManager: RecyclerView.LayoutManager = LinearLayoutManager(context)
        val playerAdapter = PlayerAdapter()
        playerRecyclerView.layoutManager = playerGridLayoutManager
        playerRecyclerView.adapter = playerAdapter
    }

}

class PlayerAdapter : RecyclerView.Adapter<PlayerViewHolder>() {

    private val players = emptyList<String>()

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