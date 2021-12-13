package com.example.knowledgehunt.services

import com.google.android.gms.tasks.Task
import com.google.firebase.auth.AuthResult
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.FirebaseUser


object FirebaseAuthServices {

    fun getCurrentUserId(): String? {

        return FirebaseAuth.getInstance().currentUser?.tenantId
    }

    fun getCurrentUser(): FirebaseUser? {

        return FirebaseAuth.getInstance().currentUser
    }

    fun sendEmailVerification(): Task<Void> {

        return getCurrentUser()!!.sendEmailVerification().addOnCompleteListener { }
    }

    fun getAuthInstance(): FirebaseAuth {

        return FirebaseAuth.getInstance()
    }

    fun createUserWithEmailAndPassword(email: String, password: String): Task<AuthResult> {

        return getAuthInstance().createUserWithEmailAndPassword(email, password)
    }

    fun getUserEmail(): String? {

        return FirebaseAuth.getInstance().currentUser?.email
    }

    suspend fun login(email: String, password: String): Task<AuthResult> {
        return getAuthInstance().signInWithEmailAndPassword(
            email,
            password
        )
    }

    suspend fun resetPassword(email: String): Task<Void> {
        return getAuthInstance().sendPasswordResetEmail(email)
    }
}
