package kr.hungrybrothres.donotforget.features.ui.home.subscribe

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.navigation.fragment.findNavController
import androidx.navigation.ui.AppBarConfiguration
import androidx.navigation.ui.NavigationUI
import androidx.recyclerview.widget.ItemTouchHelper
import androidx.recyclerview.widget.ItemTouchHelper.DOWN
import androidx.recyclerview.widget.ItemTouchHelper.UP
import androidx.recyclerview.widget.RecyclerView
import dagger.hilt.android.AndroidEntryPoint
import kr.hungrybrothres.donotforget.R
import kr.hungrybrothres.donotforget.databinding.FragmentSubscribeBinding
import kr.hungrybrothres.donotforget.features.adapter.recyclerview.SubscribeRecyclerViewAdapter
import kr.hungrybrothres.donotforget.features.base.BaseFragment
import kr.hungrybrothres.donotforget.features.listener.OnStartDragListener
import kr.hungrybrothres.donotforget.features.utility.ItemTouchHelperCallback

@AndroidEntryPoint
class SubscribeFragment : BaseFragment<FragmentSubscribeBinding>(R.layout.fragment_subscribe) {
    lateinit var subscribeAdapter: SubscribeRecyclerViewAdapter
    lateinit var callback: ItemTouchHelperCallback

    override fun initView(savedInstanceState: Bundle?) {
        setUpToolbar()
    }

    private fun setUpToolbar() {
        subscribeAdapter = SubscribeRecyclerViewAdapter()
        callback = ItemTouchHelperCallback(subscribeAdapter)
        val touchHelper = ItemTouchHelper(callback)
        touchHelper.attachToRecyclerView(binding.recyclerView)

        subscribeAdapter.addItems(
            listOf(
                Sample(product = "치약", url = "www.naver.com", price = "10000"),
                Sample(product = "칫솔", url = "www.naver.com", price = "20000"),
                Sample(product = "샴푸", url = "www.naver.com", price = "15000"),
                Sample(product = "커피콩", url = "www.naver.com", price = "12000"),
                Sample(product = "블라인더", url = "www.naver.com", price = "10000"),
                Sample(product = "티슈", url = "www.naver.com", price = "101000"),
                Sample(product = "치약", url = "www.naver.com", price = "100200"),
                Sample(product = "물티슈", url = "www.naver.com", price = "104000"),
                Sample(product = "쉐이빙폼", url = "www.naver.com", price = "10000"),
                Sample(product = "핸드크림", url = "www.naver.com", price = "100500"),
                Sample(product = "바디로션", url = "www.naver.com", price = "100010"),
                Sample(product = "면도기", url = "www.naver.com", price = "100002"),
                Sample(product = "사이다", url = "www.naver.com", price = "10000"),
                Sample(product = "콜라", url = "www.naver.com", price = "100030"),
            )
        )

        binding.recyclerView.apply {
            adapter = subscribeAdapter
            subscribeAdapter.startDrag(object : OnStartDragListener {
                override fun onStartDrag(viewHolder: RecyclerView.ViewHolder) {
                    touchHelper.startDrag(viewHolder)
                }

            })
        }
    }

//        (activity as? AppCompatActivity)?.setSupportActionBar(binding.layoutToolbar.toolbar)
//        NavigationUI.setupWithNavController(
//            binding.layoutToolbar.collapsingToolbarLayout,
//            binding.layoutToolbar.toolbar,
//            findNavController(),
//            AppBarConfiguration.Builder(R.id.subscribe, R.id.settings).build()
//        )



}

data class Sample(val product: String, val url: String, val price: String)