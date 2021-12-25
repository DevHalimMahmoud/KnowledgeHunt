package com.example.knowledgehunt.services

import android.graphics.Bitmap
import com.google.firebase.storage.FirebaseStorage
import com.google.firebase.storage.StorageReference
import com.google.firebase.storage.StorageTask
import com.google.firebase.storage.UploadTask
import java.io.ByteArrayOutputStream

object FirebaseStorageServices {

    suspend fun getInstance(): FirebaseStorage {

        return FirebaseStorage.getInstance()

    }

    suspend fun getReference(): StorageReference {

        return getInstance().reference

    }

    suspend fun uploadProfileImage(bitmap: Bitmap): StorageTask<UploadTask.TaskSnapshot> {

        val userImagesRef =
            getReference().child("user/${FirebaseAuthServices.getCurrentUserId().toString()}.PNG")

        val baos = ByteArrayOutputStream()
        bitmap.compress(Bitmap.CompressFormat.PNG, 100, baos)
        val data = baos.toByteArray()
        val uploadTask = userImagesRef.putBytes(data)
        return uploadTask.addOnFailureListener {
        }.addOnSuccessListener { taskSnapshot ->
        }

    }

}