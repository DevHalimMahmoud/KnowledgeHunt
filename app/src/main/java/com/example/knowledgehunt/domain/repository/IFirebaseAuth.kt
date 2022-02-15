package com.example.knowledgehunt.domain.repository

import com.google.android.gms.tasks.Task
import com.google.firebase.auth.AuthResult
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.FirebaseUser

interface IFirebaseAuth {

    suspend fun getCurrentUserId(): String

    suspend fun getCurrentUser(): FirebaseUser?

    suspend fun sendEmailVerification(): Task<Void>

    suspend fun getAuthInstance(): FirebaseAuth

    suspend fun createUserWithEmailAndPassword(email: String, password: String): Task<AuthResult>

    suspend fun getUserEmail(): String?

    suspend fun login(email: String, password: String): Task<AuthResult>

    suspend fun resetPassword(email: String): Task<Void>

    suspend fun logout()
    suspend fun updateEmail(email: String): Task<Void>?
    suspend fun reAuthenticate(password: String): Task<Void>?
}