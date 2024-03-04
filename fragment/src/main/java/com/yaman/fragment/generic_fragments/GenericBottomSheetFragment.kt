package com.yaman.fragment.generic_fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.annotation.LayoutRes
import androidx.databinding.DataBindingUtil
import androidx.databinding.ViewDataBinding
import com.google.android.material.bottomsheet.BottomSheetDialogFragment

open class GenericBottomSheetFragment<T : ViewDataBinding>(@LayoutRes private val layoutResId : Int) : BottomSheetDialogFragment(){

    private var _binding : T? = null
    val binding : T get() = _binding!!

    // Make it open, so it can be overridden in child fragment
    open fun T.initialize(){}

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        _binding = DataBindingUtil.inflate(inflater, layoutResId, container, false)

        // Optionally set lifecycle owner if needed
        binding.lifecycleOwner = viewLifecycleOwner

        // Calling the extension function
        binding.initialize()

        return binding.root
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

}