package kr.hungrybrothres.donotforget.features.utility

import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.ItemTouchHelper
import androidx.recyclerview.widget.RecyclerView
import kr.hungrybrothres.donotforget.features.listener.OnItemMoveListener

class ItemTouchHelperCallback(private val itemMoveListener: OnItemMoveListener): ItemTouchHelper.Callback() {

    override fun getMovementFlags(recyclerView: RecyclerView, viewHolder: RecyclerView.ViewHolder): Int {
        return if(recyclerView.layoutManager is GridLayoutManager) {
            val dragFlags = ItemTouchHelper.UP or ItemTouchHelper.DOWN or ItemTouchHelper.LEFT or ItemTouchHelper.RIGHT
            val swipeFlags = 0
            makeMovementFlags(dragFlags, swipeFlags)
        }else {
            val dragFlags = ItemTouchHelper.UP or ItemTouchHelper.DOWN
            val swipeFlags = ItemTouchHelper.START or ItemTouchHelper.END
            makeMovementFlags(dragFlags, swipeFlags)
        }
    }

    override fun onMove(recyclerView: RecyclerView, viewHolder: RecyclerView.ViewHolder, target: RecyclerView.ViewHolder): Boolean {
        itemMoveListener.onItemMoved(fromPosition = viewHolder.adapterPosition, toPosition = target.adapterPosition)
        return true
    }

    override fun onSwiped(viewHolder: RecyclerView.ViewHolder, direction: Int) {
        itemMoveListener.onItemSwiped(viewHolder.adapterPosition)
    }

    override fun isLongPressDragEnabled(): Boolean = false
}