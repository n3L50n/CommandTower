package com.nodecoyote.commandtower.fragments

import android.arch.lifecycle.ViewModelProviders
import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v7.widget.GridLayoutManager
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.nodecoyote.commandtower.Player

import com.nodecoyote.commandtower.R
import kotlinx.android.synthetic.main.cell_current_game_player.view.*
import kotlinx.android.synthetic.main.fragment_current_game.*

class CurrentGameFragment : Fragment() {

    companion object {
        fun newInstance() = CurrentGameFragment()
    }

    private lateinit var viewModel: CurrentGameViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        viewModel = ViewModelProviders.of(this).get(CurrentGameViewModel::class.java)
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.fragment_current_game, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        current_game_recycler.layoutManager = GridLayoutManager(context, 2)
        current_game_recycler.adapter = CurrentGameAdapter(viewModel.players())
    }

}

class CurrentGameAdapter(private val players: List<Player>) : RecyclerView.Adapter<CurrentPlayerViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CurrentPlayerViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.cell_current_game_player, parent, false)
        return CurrentPlayerViewHolder(view)
    }

    override fun getItemCount(): Int {
        return players.count()
    }

    override fun onBindViewHolder(holder: CurrentPlayerViewHolder, position: Int) {
        holder.bindView(players[position])
    }

}

class CurrentPlayerViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
    var life = "30"
    fun bindView(player: Player) {
        itemView.current_player_life.text = life
        itemView.current_player_name.text = player.name
        itemView.current_player_avatar.background = itemView.context.resources.getDrawable(R.drawable.sword_avatar)
        itemView.current_player_plus_life.setOnClickListener {
            var newLife = life.toInt()
            newLife += 1
            life = "$newLife"
            itemView.current_player_life.text = life
        }
        itemView.current_player_minus_life.setOnClickListener {
            var newLife = life.toInt()
            newLife -= 1
            life = "$newLife"
            itemView.current_player_life.text = life
        }
    }


}