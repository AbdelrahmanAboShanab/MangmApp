package com.brightskiesinc.mangm.di

import androidx.lifecycle.ViewModel
import com.brightskiesinc.mangm.presentation.investingterms.InvestingTermsViewModel
import dagger.Binds
import dagger.Module
import dagger.multibindings.IntoMap
@Module
abstract class ViewModelModule {

    @Binds
    @IntoMap
    @ViewModelKey(InvestingTermsViewModel::class)
    abstract fun bindInvestingViewModel(userViewModel: InvestingTermsViewModel): ViewModel

}