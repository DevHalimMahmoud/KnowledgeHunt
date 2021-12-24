package com.example.knowledgehunt.services

import com.google.android.gms.tasks.Task
import com.google.firebase.auth.AuthResult
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.FirebaseUser
import android.graphics.Bitmap

import java.io.FileOutputStream

import android.graphics.BitmapFactory
import java.io.File

import java.io.FileInputStream
import java.lang.Exception


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

    suspend fun Logout() {
        return getAuthInstance().signOut()
    }


    suspend fun saveBitmapToFile(file: File): File? {
        return try {

            // BitmapFactory options to downsize the image
            val o = BitmapFactory.Options()
            o.inJustDecodeBounds = true
            o.inSampleSize = 6
            // factor of downsizing the image
            var inputStream = FileInputStream(file)
            //Bitmap selectedBitmap = null;
            BitmapFactory.decodeStream(inputStream, null, o)
            inputStream.close()

            // The new size we want to scale to
            val REQUIRED_SIZE = 75

            // Find the correct scale value. It should be the power of 2.
            var scale = 1
            while (o.outWidth / scale / 2 >= REQUIRED_SIZE &&
                o.outHeight / scale / 2 >= REQUIRED_SIZE
            ) {
                scale *= 2
            }
            val o2 = BitmapFactory.Options()
            o2.inSampleSize = scale
            inputStream = FileInputStream(file)
            val selectedBitmap = BitmapFactory.decodeStream(inputStream, null, o2)
            inputStream.close()

            // here i override the original image file
            file.createNewFile()
            val outputStream = FileOutputStream(file)
            selectedBitmap!!.compress(Bitmap.CompressFormat.JPEG, 100, outputStream)
            file
        } catch (e: Exception) {
            null
        }
    }
}
