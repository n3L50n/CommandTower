package com.nodecoyote.commandtower.fragments

import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v4.app.FragmentManager
import android.support.v7.widget.GridLayoutManager
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.fragment.findNavController
import com.nodecoyote.commandtower.Keys
import com.nodecoyote.commandtower.PlayerService
import com.nodecoyote.commandtower.R
import kotlinx.android.synthetic.main.cell_avatar.view.*
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
        activity?.let { a ->
            val avatars =  a.resources.getStringArray(R.array.avatar_icons)
            choose_avatar_recycler.layoutManager = GridLayoutManager(context, 3)
            choose_avatar_recycler.adapter = CreatePlayerAdapter(avatars, a.supportFragmentManager)
        }
    }

}

class CreatePlayerAdapter(avatars: Array<String>, private val fm: FragmentManager): RecyclerView.Adapter<ChooseAvatarViewHolder>(){

    private val mAvatars = avatars

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ChooseAvatarViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.cell_avatar, parent, false)
        return ChooseAvatarViewHolder(itemView = view)
    }

    override fun getItemCount(): Int {
        return 12
    }

    override fun onBindViewHolder(holder: ChooseAvatarViewHolder, position: Int) {
        val chosen = false
        imageLoader(holder.icon, position, chosen)
        holder.bindView(mAvatars[position], fm)
    }

}

class ChooseAvatarViewHolder(itemView: View): RecyclerView.ViewHolder(itemView), PlayerService {
    val icon: ImageView = itemView.avatar_image_view
    private val title: TextView = itemView.avatar_title

    fun bindView(avatar: String, fm: FragmentManager){
        title.text = avatar

        icon.setOnClickListener {
            for (fragment in fm.fragments) {
                if (fragment is NavHostFragment) {
                    val bundle = Bundle()
                    bundle.putInt(Keys.AvatarSelectedIconPosition, adapterPosition)
                    fragment.findNavController().navigate(R.id.createPlayerExtrasFragment, bundle)
                }
            }
        }
    }
}

fun imageLoader(icon: ImageView, position: Int, chosen: Boolean): Int {
//TODO CLUNKY TRIM DOWN
    if (chosen) icon.alpha = 0.3f

    when (position) {
        0 -> {
            icon.setImageResource(R.drawable.sword_avatar)
            return position
        }
        1 -> {
            icon.setImageResource(R.drawable.shield_avatar)
            return position
        }
        2 -> {
            icon.setImageResource(R.drawable.crown_avatar)
            return position
        }
        3 -> {
            icon.setImageResource(R.drawable.torch_avatar)
            return position
        }
        4 -> {
            icon.setImageResource(R.drawable.poison_avatar)
            return position
        }
        5 -> {
            icon.setImageResource(R.drawable.book_avatar)
            return position
        }
        6 -> {
            icon.setImageResource(R.drawable.hammer_avatar)
            return position
        }
        7 -> {
            icon.setImageResource(R.drawable.pyromancer_avatar)
            return position
        }
        8 -> {
            icon.setImageResource(R.drawable.scepter_avatar)
            return position
        }
        9 -> {
            icon.setImageResource(R.drawable.axe_avatar)
            return position
        }
        10 -> {
            icon.setImageResource(R.drawable.ring_avatar)
            return position
        }
        11 -> {
            icon.setImageResource(R.drawable.crossbow_avatar)
            return position
        }
        else -> {
            icon.setImageResource(R.drawable.sword_avatar)
            return position
        }
    }
}