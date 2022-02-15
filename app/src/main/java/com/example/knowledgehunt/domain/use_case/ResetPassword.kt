package com.example.knowledgehunt.domain.use_case

import com.example.knowledgehunt.domain.repository.IFirebaseAuth

class ResetPassword(
    private val repository: IFirebaseAuth
) {
    suspend operator fun invoke(email: String) = repository.resetPassword(email)
}