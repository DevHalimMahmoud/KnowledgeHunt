package com.abdelHalimMahmoud.knowledgehunt.domain.use_case

import com.abdelHalimMahmoud.knowledgehunt.domain.repository.IFirebaseAuth

class SendEmailVerification(
    private val repository: IFirebaseAuth
) {
    suspend operator fun invoke(

    ) = repository.sendEmailVerification()
}