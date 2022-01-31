package com.example.knowledgehunt.domain.repository

import androidx.lifecycle.MutableLiveData
import com.google.android.gms.tasks.Task
import com.google.firebase.firestore.CollectionReference
import com.google.firebase.firestore.DocumentReference

interface IFirebaseFirestore {
    suspend fun getUserName(): MutableLiveData<String>

    suspend fun addUserDataToFirestore(data: MutableMap<String, Any?>): Task<Void>

    suspend fun addArticleDataToFirestore(data: MutableMap<String, Any?>): Task<DocumentReference>

    suspend fun getCollectionFromFirestore(collectionPath: String): CollectionReference
}