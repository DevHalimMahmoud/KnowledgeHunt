package com.example.knowledgehunt.data.repository

import android.graphics.Bitmap
import com.example.knowledgehunt.domain.repository.IFirebaseStorage
import com.google.android.gms.tasks.Task
import com.google.firebase.storage.FirebaseStorage
import com.google.firebase.storage.StorageReference
import com.google.firebase.storage.StorageTask
import com.google.firebase.storage.UploadTask
import java.io.ByteArrayOutputStream

class FirebaseStorageImpl : IFirebaseStorage {

    override suspend fun getInstance(): FirebaseStorage {

        return FirebaseStorage.getInstance()

    }

    override suspend fun getReference(): StorageReference {

        return FirebaseStorage.getInstance().reference

    }

    override suspend fun uploadStorageImage(
        bitmap: Bitmap,
        bath: String,
        name: String
    ): StorageTask<UploadTask.TaskSnapshot> {

        val userImagesRef =
            FirebaseStorage.getInstance().reference.child("$bath/${name}.JPEG")

        val baos = ByteArrayOutputStream()
        bitmap.compress(Bitmap.CompressFormat.JPEG, 100, baos)
        val data = baos.toByteArray()
        val uploadTask = userImagesRef.putBytes(data)
        return uploadTask.addOnFailureListener {
        }.addOnSuccessListener { taskSnapshot ->
        }
    }

    override suspend fun getStorageImage(
        bath: String,
        name: String
    ): Task<ByteArray> {

        val islandRef =
            FirebaseStorage.getInstance().reference.child("$bath/${name}.JPEG")

        val TWO_MEGABYTE: Long = 2048 * 2048
        return islandRef.getBytes(TWO_MEGABYTE).addOnCompleteListener { task -> }
    }
}