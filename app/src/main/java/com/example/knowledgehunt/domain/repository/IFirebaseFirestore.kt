package com.example.knowledgehunt.domain.repository

import com.google.android.gms.tasks.Task
import com.google.firebase.Timestamp
import com.google.firebase.firestore.CollectionReference
import com.google.firebase.firestore.DocumentReference
import com.google.firebase.firestore.DocumentSnapshot
import com.google.firebase.firestore.QuerySnapshot

interface IFirebaseFirestore {
    suspend fun getCurrentUserData(): Task<DocumentSnapshot>

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

    suspend fun deleteDocument(collection: String, id: String): Task<Void>

    suspend fun addData(
        collection: String,
        data: MutableMap<String, Any?>,
    ): Task<DocumentReference>
}