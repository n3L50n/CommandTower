package com.nodecoyote.commandtower.fragments

import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v7.widget.GridLayoutManager
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import com.nodecoyote.commandtower.Decoration
import com.nodecoyote.commandtower.R
import kotlinx.android.synthetic.main.cell_format.view.*
import kotlinx.android.synthetic.main.fragment_format.*

private const val mTag = "FormatViews"

enum class Formats { Classic, Brawl, Commander, Custom }

class FormatFragment : Fragment() {

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        if (container == null) {
            return null
        }
        return inflater.inflate(R.layout.fragment_format, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setUpRecycler()
    }

    private fun setUpRecycler() {
        val formatRecyclerView: RecyclerView = formatRecycler
        val formatGridLayoutManager = GridLayoutManager(context, 2)
        val formatAdapter = FormatAdapter()
        formatRecyclerView.layoutManager = formatGridLayoutManager
        formatRecyclerView.adapter = formatAdapter
        formatRecyclerView.addItemDecoration(Decoration())
    }

}

class FormatAdapter : RecyclerView.Adapter<FormatViewHolder>() {

    private val formats = listOf("Classic", "Brawl", "Commander", "Custom")

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): FormatViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.cell_format, parent, false)
        return FormatViewHolder(view)
    }

    override fun getItemCount(): Int {
        return formats.count()
    }

    override fun onBindViewHolder(holder: FormatViewHolder, position: Int) {
        holder.bindView(formats[position])
    }

}

class FormatViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

    fun bindView(format: String) {
        itemView.formatTitle.text = format
        itemView.formatItemContainer.setOnClickListener {

            val fragment = PlayerListFragment()
            Toast.makeText(itemView.context, "Clicked $format", Toast.LENGTH_SHORT).show()
        }
    }
}