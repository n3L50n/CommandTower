package com.nodecoyote.commandtower

import android.os.Build
import android.os.Bundle
import android.support.annotation.RequiresApi
import android.support.v4.app.Fragment
import android.support.v7.widget.GridLayoutManager
import android.support.v7.widget.RecyclerView
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewAnimationUtils
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
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
        create_player_container_layout.isSelected = true

        Log.v("Create", "Creating Fragment")

        if (Build.VERSION.SDK_INT >= 21) {
            animate(itemView)
        }
        context?.let {
            val avatars =  it.resources.getStringArray(R.array.avatar_icons)
            choose_avatar_recycler.layoutManager = GridLayoutManager(context, 3)
            choose_avatar_recycler.adapter = CreatePlayerAdapter(avatars)
            create_player_container_layout.isSelected = false
        }
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

class CreatePlayerAdapter(avatars: Array<String>): RecyclerView.Adapter<ChooseAvatarViewHolder>(){

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

        holder.bindView(mAvatars[position])
    }

}

class ChooseAvatarViewHolder(itemView: View): RecyclerView.ViewHolder(itemView){
    val icon: ImageView = itemView.avatar_image_view
    private val title: TextView = itemView.avatar_title

    fun bindView(avatar: String){
        title.text = avatar
    }
}



//enum class Avatar{
//    Sword,
//    Shield,
//    Crown,
//    Torch,
//    Poison,
//    Book,
//    Hammer,
//    Goggles,
//    Scepter,
//    Axe,
//    Ring,
//    Crossbow
//}

//enum class Avatar(image: String){
//    Sword("sword_avatar"),
//    Shield("shield_avatar"),
//    Crown("crown_avatar"),
//    Torch("torch_avatar"),
//    Poison("poison_avatar"),
//    Book("book_avatar"),
//    Hammer("hammer_avatar"),
//    Goggles("pyromancer_avatar"),
//    Scepter("scepter_avatar"),
//    Axe("axe_avatar"),
//    Ring("ring_avatar"),
//    Crossbow("crossbow_avatar")
//}



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