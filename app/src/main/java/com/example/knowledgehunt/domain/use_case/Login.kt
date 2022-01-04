package com.example.knowledgehunt.domain.use_case

import com.example.knowledgehunt.domain.repository.IFirebaseAuth

class Login(
    private val repository: IFirebaseAuth
) {
    suspend operator fun invoke(
        email: String, password: String
    ) = repository.login(email, password)
}