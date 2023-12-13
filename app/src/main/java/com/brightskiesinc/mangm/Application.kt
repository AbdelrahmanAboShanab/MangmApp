package com.brightskiesinc.mangm

import android.app.Application
import com.brightskiesinc.mangm.di.AppComponent
import com.brightskiesinc.mangm.di.DaggerAppComponent

open class MyApplication : Application() {

    lateinit var appComponent: AppComponent

    override fun onCreate() {
        super.onCreate()
        initDagger()
    }

    private fun initDagger() {
        appComponent = DaggerAppComponent.factory().create(this)
    }
}