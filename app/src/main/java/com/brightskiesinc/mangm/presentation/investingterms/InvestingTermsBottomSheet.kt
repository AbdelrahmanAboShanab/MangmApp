package com.brightskiesinc.mangm.presentation.investingterms

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.brightskiesinc.mangm.di.DaggerAppComponent
import com.brightskiesinc.mangm.presentation.base.ViewModelFactory
import com.brightskiesinc.mangm.presentation.base.BaseBottomSheet
import com.brightskiesinc.mangmapp.databinding.BottomSheetInvestingTermsBinding
import javax.inject.Inject

/**
 * Change app language bottom sheet
 */
class InvestingTermsBottomSheet : BaseBottomSheet<BottomSheetInvestingTermsBinding>() {

    @Inject
    lateinit var viewModelFactory: ViewModelFactory
    private lateinit var vmInvestingTerms: InvestingTermsViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        DaggerAppComponent.factory().create(requireActivity().application).inject(this)
        vmInvestingTerms = this.viewModelFactory.create(InvestingTermsViewModel::class.java)
    }
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?
    ) = getView(BottomSheetInvestingTermsBinding.inflate(inflater, container, false))

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setupViews()
    }

    private fun setupViews() {
        binding.btnUnderstand.setOnClickListener {
            saveFirstOpen()
        }
    }

    private fun saveFirstOpen() = vmInvestingTerms.disableOnBoarding()
}