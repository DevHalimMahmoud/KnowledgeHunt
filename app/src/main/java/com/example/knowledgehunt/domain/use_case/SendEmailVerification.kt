package com.example.knowledgehunt.domain.use_case

import com.example.knowledgehunt.data.repository.FirebaseAuthImpl
import com.example.knowledgehunt.domain.repository.IFirebaseAuth

class SendEmailVerification (
    private val repository: IFirebaseAuth
) {
    suspend operator fun invoke(

    ) = repository.sendEmailVerification()
}