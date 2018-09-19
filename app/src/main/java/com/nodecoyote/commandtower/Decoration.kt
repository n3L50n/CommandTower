package com.nodecoyote.commandtower

import android.graphics.Canvas
import android.os.Build
import android.support.annotation.RequiresApi
import android.support.v7.widget.RecyclerView

class Decoration: RecyclerView.ItemDecoration() {

    @RequiresApi(Build.VERSION_CODES.LOLLIPOP)
    override fun onDraw(c: Canvas?, parent: RecyclerView?, state: RecyclerView.State?) {
        super.onDraw(c, parent, state)
        c?.save()

        val first = parent?.layoutManager?.findViewByPosition(0)
        first?.isActivated = true
        first?.elevation = 2f
        first?.alpha = 1f
    }

}