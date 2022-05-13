package com.abdelHalimMahmoud.knowledgehunt.domain.use_case

import com.abdelHalimMahmoud.knowledgehunt.domain.repository.IFirebaseAuth

class Login(
    private val repository: IFirebaseAuth
) {
    suspend operator fun invoke(
        email: String, password: String
    ) = repository.login(email, password)
}