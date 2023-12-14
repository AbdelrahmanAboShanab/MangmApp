package com.brightskiesinc.mangm.domain.repository

interface AuthRepository {

    fun isFirstLaunch(): Boolean

    fun setFirstLaunch(isFirstLaunch: Boolean)

}