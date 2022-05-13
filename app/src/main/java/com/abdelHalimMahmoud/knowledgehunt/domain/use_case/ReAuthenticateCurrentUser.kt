package com.abdelHalimMahmoud.knowledgehunt.domain.use_case

import com.abdelHalimMahmoud.knowledgehunt.domain.repository.IFirebaseAuth

class ReAuthenticateCurrentUser(
    private val repository: IFirebaseAuth
) {
    suspend operator fun invoke(
        password: String
    ) = repository.reAuthenticate(password)
}