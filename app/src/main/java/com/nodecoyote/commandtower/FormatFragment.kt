package com.nodecoyote.commandtower

import android.arch.lifecycle.ViewModel
import android.arch.lifecycle.ViewModelProvider
import android.arch.lifecycle.ViewModelProviders
import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import kotlinx.android.synthetic.main.cell_format.view.*
import kotlinx.android.synthetic.main.fragment_format.*

private const val mTag = "FormatViews"

enum class Formats{Classic, Brawl, Commander, Custom}

class FormatFragment: Fragment() {

    private lateinit var mViewModel: FormatsViewModelInterface

    @Suppress("UNCHECKED_CAST")
    private val _factory = object : ViewModelProvider.Factory {
        override fun <T : ViewModel?> create(modelClass: Class<T>): T {
            when (modelClass) {
                FormatsViewModelInterface::class.java -> return FormatsViewModel() as T
            }
            throw IllegalArgumentException("Unknown model class $modelClass")
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        mViewModel = ViewModelProviders
                .of(this@FormatFragment, _factory)
                .get(FormatsViewModelInterface::class.java)
    }

        override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        if (container == null) {
            return null
        }
        return inflater.inflate(R.layout.fragment_format, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        formatRecycler.layoutManager = LinearLayoutManager(context)
        formatRecycler.adapter = FormatAdapter(mViewModel)
    }

}

abstract class FormatsViewModelInterface: ViewModel() {
    abstract val formats: List<String>
}

class FormatsViewModel: FormatsViewModelInterface() {
    override val formats = listOf("Classic", "Brawl", "Commander", "Custom")
}

class FormatAdapter(viewModel: FormatsViewModelInterface): RecyclerView.Adapter<FormatViewHolder>() {

    private val mViewModel = viewModel

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): FormatViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.cell_format, parent, false)
        return FormatViewHolder(view)
    }

    override fun getItemCount(): Int {
        return mViewModel.formats.size
    }

    override fun onBindViewHolder(holder: FormatViewHolder, position: Int) {
        Log.v("Format", "Binding holder with ${mViewModel.formats[position]}")
        holder.bindView(mViewModel.formats[position])
    }

}

class FormatViewHolder(itemView: View): RecyclerView.ViewHolder(itemView) {

    fun bindView(format: String) {
        itemView.formatTitle.text = format
    }
}