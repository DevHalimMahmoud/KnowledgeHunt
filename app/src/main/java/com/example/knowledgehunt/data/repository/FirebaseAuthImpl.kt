package com.example.knowledgehunt.data.repository

import com.example.knowledgehunt.domain.repository.IFirebaseAuth
import com.google.android.gms.tasks.Task
import com.google.firebase.auth.AuthResult
import com.google.firebase.auth.EmailAuthProvider
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.FirebaseUser


class FirebaseAuthImpl : IFirebaseAuth {

    override suspend fun getAuthInstance(): FirebaseAuth {

        return FirebaseAuth.getInstance()
    }

    override suspend fun getCurrentUserId(): String {

        return getAuthInstance().currentUser?.uid!!
    }

    override suspend fun getCurrentUser(): FirebaseUser? {

        return getAuthInstance().currentUser
    }

    override suspend fun sendEmailVerification(): Task<Void> {

        return getAuthInstance().currentUser!!.sendEmailVerification()
            .addOnCompleteListener { }
    }

    override suspend fun createUserWithEmailAndPassword(
        email: String,
        password: String
    ): Task<AuthResult> {

        return getAuthInstance().createUserWithEmailAndPassword(email, password)
    }

    override suspend fun getUserEmail(): String? {

        return getAuthInstance().currentUser?.email
    }

    override suspend fun login(email: String, password: String): Task<AuthResult> {
        return getAuthInstance().signInWithEmailAndPassword(
            email,
            password
        )
    }

    override suspend fun resetPassword(): Task<Void> {
        return getAuthInstance().sendPasswordResetEmail(getUserEmail().toString())
    }

    override suspend fun logout() {
        return getAuthInstance().signOut()
    }

    override suspend fun updateEmail(email: String): Task<Void>? {
        return getAuthInstance().currentUser?.updateEmail(email)
    }

    override suspend fun reAuthenticate(password: String): Task<Void>? {
        return getAuthInstance().currentUser?.reauthenticate(
            EmailAuthProvider
                .getCredential(
                    getUserEmail().toString(),
                    password
                )
        )
    }
}
