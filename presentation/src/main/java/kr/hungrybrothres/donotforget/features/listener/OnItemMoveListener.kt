package kr.hungrybrothres.donotforget.features.listener

interface OnItemMoveListener {

    fun onItemMoved(fromPosition: Int, toPosition: Int)

    fun onItemSwiped(position: Int)
}