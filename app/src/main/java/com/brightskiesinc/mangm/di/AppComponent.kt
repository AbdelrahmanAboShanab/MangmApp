package com.brightskiesinc.mangm.di

import android.app.Application
import com.brightskiesinc.mangm.presentation.investingterms.InvestingTermsBottomSheet
import dagger.BindsInstance
import dagger.Component
import javax.inject.Singleton

@Singleton
@Component(
    modules = [
        RepositoryModule::class,
        ViewModelModule::class,
        ContextModule::class
    ]
)
interface AppComponent {

    fun inject(bottomSheet: InvestingTermsBottomSheet)

    @Component.Factory
    interface Factory {
        fun create(@BindsInstance application: Application): AppComponent
    }
}