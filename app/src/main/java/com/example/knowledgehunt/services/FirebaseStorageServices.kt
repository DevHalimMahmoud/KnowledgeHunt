package com.example.knowledgehunt.services

import android.graphics.Bitmap
import com.google.android.gms.tasks.Task
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
            getReference().child("user/${FirebaseAuthServices.getCurrentUserId().toString()}.JPEG")

        val baos = ByteArrayOutputStream()
        bitmap.compress(Bitmap.CompressFormat.JPEG, 75, baos)
        val data = baos.toByteArray()
        val uploadTask = userImagesRef.putBytes(data)
        return uploadTask.addOnFailureListener {
        }.addOnSuccessListener { taskSnapshot ->
        }
    }

    suspend fun getProfileImage(): Task<ByteArray> {

        val islandRef =
            getReference().child("user/${FirebaseAuthServices.getCurrentUserId().toString()}.JPEG")

        val FOUR_MEGABYTE: Long = 4096 * 4096
        return islandRef.getBytes(FOUR_MEGABYTE).addOnSuccessListener {
        }.addOnFailureListener {
        }
    }
}