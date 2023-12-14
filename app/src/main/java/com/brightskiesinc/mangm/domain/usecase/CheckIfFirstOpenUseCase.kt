package com.brightskiesinc.mangm.domain.usecase

import com.brightskiesinc.mangm.domain.repository.AuthRepository
import javax.inject.Inject

class CheckIfFirstOpenUseCase @Inject constructor(
    private val authRepository: AuthRepository
)  {
     operator fun invoke() :Boolean {
      return authRepository.isFirstLaunch()
    }
}