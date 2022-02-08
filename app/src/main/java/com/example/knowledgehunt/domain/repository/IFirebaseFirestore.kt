package com.example.knowledgehunt.domain.repository

import androidx.lifecycle.MutableLiveData
import com.google.android.gms.tasks.Task
import com.google.firebase.Timestamp
import com.google.firebase.firestore.*

interface IFirebaseFirestore {
    suspend fun getUserName(): MutableLiveData<String>

    suspend fun addUserDataToFirestore(data: MutableMap<String, Any?>): Task<Void>

    suspend fun addArticleDataToFirestore(data: MutableMap<String, Any?>): Task<DocumentReference>

    suspend fun getCollectionFromFirestore(collectionPath: String): CollectionReference

    suspend fun addDataToDocument(
        collection: String,
        id: String,
        data: MutableMap<String, Any?>
    ): Task<Void>

    suspend fun getDocumentById(
        collection: String,
        id: String,
    ): Task<DocumentSnapshot>

    suspend fun getDocumentByStringFieldAndTimestampField(
        collection: String,
        field1Value: String,
        filed1Name: String,
        field2Name: String,
        Field2Value: Timestamp?
    ): Task<QuerySnapshot>
}