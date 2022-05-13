package com.abdelHalimMahmoud.knowledgehunt.domain.use_case

import com.abdelHalimMahmoud.knowledgehunt.domain.repository.IFirebaseAuth

class ResetPassword(
    private val repository: IFirebaseAuth
) {
    suspend operator fun invoke(email: String) = repository.resetPassword(email)
}