package com.brightskiesinc.mangm.di

import com.brightskiesinc.mangm.data.repository.AuthRepositoryImpl
import com.brightskiesinc.mangm.domain.repository.AuthRepository
import dagger.Binds
import dagger.Module
import javax.inject.Singleton

@Module
abstract class RepositoryModule {

    @Singleton
    @Binds
     abstract fun bindAuthRepository(authRepository: AuthRepositoryImpl): AuthRepository

}