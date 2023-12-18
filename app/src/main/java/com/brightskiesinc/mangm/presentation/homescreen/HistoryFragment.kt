package com.brightskiesinc.mangm.presentation.homescreen

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.brightskiesinc.mangm.presentation.base.BaseFragment
import com.brightskiesinc.mangmapp.databinding.FragmentHistoryBinding

class HistoryFragment : BaseFragment<FragmentHistoryBinding>() {
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ) = getView(FragmentHistoryBinding.inflate(inflater, container, false))


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

//        setUpView()
    }

//    fun setUpView() {
//        TODO("Not yet implemented")
//    }
}
