package com.brightskiesinc.mangm.presentation.investingterms

import android.os.Bundle
import android.view.LayoutInflater
import android.view.ViewGroup
import com.brightskiesinc.mangm.presentation.base.BaseBottomSheet
import com.brightskiesinc.mangmapp.databinding.BottomSheetInvestingTermsBinding

/**
 * Change app language bottom sheet
 */
class InvestingTermsBottomSheet : BaseBottomSheet<BottomSheetInvestingTermsBinding>() {


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?
    ) = getView(BottomSheetInvestingTermsBinding.inflate(inflater, container, false))

}