package com.brightskiesinc.mangm.presentation.investingterms

import androidx.lifecycle.viewModelScope
import com.brightskiesinc.mangm.domain.usecase.DisableOnBoardingUseCase
import com.brightskiesinc.mangm.presentation.base.BaseViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject
class InvestingTermsViewModel @Inject constructor(
    private val disableOnBoardingUseCase: DisableOnBoardingUseCase,
    ) : BaseViewModel() {

    fun disableOnBoarding() {
        viewModelScope.launch { disableOnBoardingUseCase() }
    }
}