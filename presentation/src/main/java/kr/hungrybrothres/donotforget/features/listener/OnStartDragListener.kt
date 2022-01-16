package kr.hungrybrothres.donotforget.features.listener

import androidx.recyclerview.widget.RecyclerView

interface OnStartDragListener {
    fun onStartDrag(viewHolder: RecyclerView.ViewHolder)
}