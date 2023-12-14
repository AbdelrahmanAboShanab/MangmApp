package com.brightskiesinc.mangm.domain.usecase

import com.brightskiesinc.mangm.domain.repository.AuthRepository
import javax.inject.Inject

class DisableOnBoardingUseCase @Inject constructor(
    private val authRepository: AuthRepository
)  {
    operator fun invoke() {
        authRepository.setFirstLaunch(false)
    }
}