package com.brightskiesinc.mangm.presentation.buygold

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.fragment.findNavController
import com.brightskiesinc.mangmapp.R


class BuyFragment : Fragment() {


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }



    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        findNavController().navigate(R.id.investingTermsBottomSheet)
        return inflater.inflate(R.layout.fragment_buy, container, false)
    }
}