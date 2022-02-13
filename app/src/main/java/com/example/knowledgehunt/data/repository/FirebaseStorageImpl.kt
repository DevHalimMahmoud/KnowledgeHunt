package com.example.knowledgehunt.data.repository

import android.graphics.Bitmap
import android.net.Uri
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
        bitmap.compress(Bitmap.CompressFormat.JPEG, 80, baos)
        val data = baos.toByteArray()
        return userImagesRef.putBytes(data)
    }

    override suspend fun getStorageImageUrl(
        bath: String,
        name: String
    ): Task<Uri> {
        return FirebaseStorage.getInstance().reference.child("$bath/${name}.JPEG").downloadUrl
    }

    override suspend fun deleteStorageImageUrl(
        bath: String,
        name: String
    ): Task<Void> {
        return FirebaseStorage.getInstance().reference.child("$bath/${name}.JPEG").delete()
    }
}