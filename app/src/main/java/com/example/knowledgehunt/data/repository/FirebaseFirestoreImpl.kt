package com.example.knowledgehunt.data.repository

import com.example.knowledgehunt.domain.repository.IFirebaseFirestore
import com.google.android.gms.tasks.Task
import com.google.firebase.Timestamp
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.firestore.*


class FirebaseFirestoreImpl : IFirebaseFirestore {


    override suspend fun getCurrentUserData(): Task<DocumentSnapshot> {

        return FirebaseFirestore.getInstance().collection("user")
            .document(FirebaseAuth.getInstance().uid!!).get()

    }


    override suspend fun addUserDataToFirestore(data: MutableMap<String, Any?>): Task<Void> {

        return FirebaseFirestore.getInstance().collection("user")
            .document(FirebaseAuth.getInstance().uid.toString())
            .set(data)
    }

    override suspend fun addArticleDataToFirestore(data: MutableMap<String, Any?>): Task<DocumentReference> {

        return FirebaseFirestore.getInstance().collection("articles")
            .add(data)
    }

    override suspend fun getCollectionFromFirestore(collectionPath: String): CollectionReference {

        return FirebaseFirestore.getInstance().collection(collectionPath)
    }

    override suspend fun addDataToDocument(
        collection: String,
        id: String,
        data: MutableMap<String, Any?>
    ): Task<Void> {
        return FirebaseFirestore.getInstance().collection(collection).document(id)
            .set(data, SetOptions.merge())
    }

    override suspend fun getDocumentById(collection: String, id: String): Task<DocumentSnapshot> {
        return FirebaseFirestore.getInstance().collection(collection).document(id).get()
    }

    override suspend fun getDocumentByStringFieldAndTimestampField(
        collection: String,
        field1Value: String,
        filed1Name: String,
        field2Name: String,
        Field2Value: Timestamp?
    ): Task<QuerySnapshot> {
        return FirebaseFirestore.getInstance().collection(collection)
            .whereEqualTo(filed1Name, field1Value).whereEqualTo(field2Name, Field2Value).limit(1)
            .get()
    }


}