package kr.hungrybrothres.donotforget.features.base

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.annotation.LayoutRes
import androidx.databinding.DataBindingComponent
import androidx.databinding.DataBindingUtil
import androidx.databinding.ViewDataBinding
import androidx.fragment.app.Fragment

abstract class BaseFragment<T: ViewDataBinding> constructor(@LayoutRes val contentLayoutId: Int) : Fragment() {
    private var bindingComponent: DataBindingComponent? = DataBindingUtil.getDefaultComponent()
    private var _binding: T? = null
    val binding: T get() = _binding!!

//    val binding: T get() = checkNotNull(_binding)

//    protected inline fun binding(block: T.() -> Unit): T {
//        return binding.apply(block)
//    }

    abstract fun initView(savedInstanceState: Bundle?)

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        _binding = DataBindingUtil.inflate(inflater, contentLayoutId, container, false, bindingComponent)
        binding.lifecycleOwner = viewLifecycleOwner

        initView(savedInstanceState = savedInstanceState)

        return binding.root
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding?.unbind()
        _binding = null
    }
}