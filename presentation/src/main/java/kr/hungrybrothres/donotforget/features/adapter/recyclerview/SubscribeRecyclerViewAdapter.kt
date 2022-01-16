package kr.hungrybrothres.donotforget.features.adapter.recyclerview

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.MotionEvent
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import kr.hungrybrothres.donotforget.databinding.ItemSubscribeBinding
import kr.hungrybrothres.donotforget.features.listener.OnItemMoveListener
import kr.hungrybrothres.donotforget.features.listener.OnStartDragListener
import kr.hungrybrothres.donotforget.features.ui.home.subscribe.Sample
import java.util.*

class SubscribeRecyclerViewAdapter : RecyclerView.Adapter<SubscribeRecyclerViewAdapter.SubscribeViewHolder>(), OnItemMoveListener {
    private lateinit var dragListener: OnStartDragListener
    private var items: MutableList<Sample> = mutableListOf()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): SubscribeViewHolder {
        return SubscribeViewHolder(ItemSubscribeBinding.inflate(LayoutInflater.from(parent.context), parent, false))
    }

    override fun onBindViewHolder(holder: SubscribeViewHolder, position: Int) {
        return holder.bind(position)
    }

    override fun getItemCount(): Int = items.size

    fun addItems(item: List<Sample>) {
        items = item.toMutableList()
        notifyDataSetChanged()
    }

    fun startDrag(listener: OnStartDragListener) {
        this.dragListener = listener
    }

    override fun onItemMoved(fromPosition: Int, toPosition: Int) {
        Collections.swap(items, fromPosition, toPosition)
        notifyItemMoved(fromPosition, toPosition)
    }

    override fun onItemSwiped(position: Int) {
        items.removeAt(position)
        notifyItemRemoved(position)
    }


    inner class SubscribeViewHolder(private val binding: ItemSubscribeBinding) : RecyclerView.ViewHolder(binding.root) {
        constructor(parent: ViewGroup) : this(ItemSubscribeBinding.inflate(LayoutInflater.from(parent.context), parent, false))
        @SuppressLint("ClickableViewAccessibility")
        fun bind(position: Int) {
            binding.data = items[position]
            binding.viewSpace.setOnTouchListener { v, event ->
                if(event.action == MotionEvent.ACTION_DOWN) {
                    dragListener.onStartDrag(this)
                }
                return@setOnTouchListener false
            }
        }
    }


}