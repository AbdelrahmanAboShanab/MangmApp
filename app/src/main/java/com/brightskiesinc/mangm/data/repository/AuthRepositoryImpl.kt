package com.brightskiesinc.mangm.data.repository

import com.brightskiesinc.mangm.data.source.local.SharedPrefStorage
import com.brightskiesinc.mangm.domain.repository.AuthRepository
import javax.inject.Inject

class AuthRepositoryImpl @Inject constructor(
    private val sharedPrefStorage: SharedPrefStorage
) : AuthRepository {

    override fun isFirstLaunch():Boolean {
        return sharedPrefStorage.getBoolean(SharedPrefStorage.Key.FIRST_TIME_LAUNCH , true)
    }

    override fun setFirstLaunch(isFirstLaunch: Boolean) {
        sharedPrefStorage.putBoolean(SharedPrefStorage.Key.FIRST_TIME_LAUNCH , isFirstLaunch)
    }

}