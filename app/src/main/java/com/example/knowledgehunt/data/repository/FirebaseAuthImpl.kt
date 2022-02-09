package com.example.knowledgehunt.data.repository

import com.example.knowledgehunt.domain.repository.IFirebaseAuth
import com.google.android.gms.tasks.Task
import com.google.firebase.auth.AuthResult
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.FirebaseUser


class FirebaseAuthImpl : IFirebaseAuth {

    override suspend fun getCurrentUserId(): String {

        return FirebaseAuth.getInstance().currentUser?.uid!!
    }

    override suspend fun getCurrentUser(): FirebaseUser? {

        return FirebaseAuth.getInstance().currentUser
    }

    override suspend fun sendEmailVerification(): Task<Void> {

        return FirebaseAuth.getInstance().currentUser!!.sendEmailVerification()
            .addOnCompleteListener { }
    }

    override suspend fun getAuthInstance(): FirebaseAuth {

        return FirebaseAuth.getInstance()
    }

    override suspend fun createUserWithEmailAndPassword(
        email: String,
        password: String
    ): Task<AuthResult> {

        return FirebaseAuth.getInstance().createUserWithEmailAndPassword(email, password)
    }

    override suspend fun getUserEmail(): String? {

        return FirebaseAuth.getInstance().currentUser?.email
    }

    override suspend fun login(email: String, password: String): Task<AuthResult> {
        return FirebaseAuth.getInstance().signInWithEmailAndPassword(
            email,
            password
        )
    }

    override suspend fun resetPassword(email: String): Task<Void> {
        return FirebaseAuth.getInstance().sendPasswordResetEmail(email)
    }

    override suspend fun Logout() {
        return FirebaseAuth.getInstance().signOut()
    }


}
