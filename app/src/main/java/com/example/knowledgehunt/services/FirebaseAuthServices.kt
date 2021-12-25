package com.example.knowledgehunt.services

import android.graphics.Bitmap
import android.graphics.BitmapFactory
import com.google.android.gms.tasks.Task
import com.google.firebase.auth.AuthResult
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.FirebaseUser
import java.io.File
import java.io.FileInputStream
import java.io.FileOutputStream


object FirebaseAuthServices {

    suspend  fun getCurrentUserId(): String? {

        return FirebaseAuth.getInstance().currentUser?.uid
    }

    suspend fun getCurrentUser(): FirebaseUser? {

        return FirebaseAuth.getInstance().currentUser
    }

    suspend  fun sendEmailVerification(): Task<Void> {

        return getCurrentUser()!!.sendEmailVerification().addOnCompleteListener { }
    }

    suspend  fun getAuthInstance(): FirebaseAuth {

        return FirebaseAuth.getInstance()
    }

    suspend fun createUserWithEmailAndPassword(email: String, password: String): Task<AuthResult> {

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

    suspend fun Logout() {
        return getAuthInstance().signOut()
    }


}
