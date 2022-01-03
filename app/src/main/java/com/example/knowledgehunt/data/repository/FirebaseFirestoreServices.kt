package com.example.knowledgehunt.data.repository

import androidx.lifecycle.MutableLiveData
import com.example.knowledgehunt.data.repository.FirebaseAuthServices.getCurrentUserId
import com.google.android.gms.tasks.Task
import com.google.firebase.firestore.DocumentReference
import com.google.firebase.firestore.FirebaseFirestore
import java.util.*


object FirebaseFirestoreServices {

    suspend fun addDonationData(data: HashMap<String, Any?>): Task<DocumentReference> {

        return FirebaseFirestore.getInstance().collection("requests")
            .add(data).addOnSuccessListener {
                return@addOnSuccessListener
            }.addOnFailureListener {
                return@addOnFailureListener
            }
    }


    suspend fun getUserName(): MutableLiveData<String> {

        return MutableLiveData<String>().apply {

            val db = FirebaseFirestore.getInstance()
            getCurrentUserId().let {
                db.collection("user").document(
                    it!!
                )
            }.addSnapshotListener { value, e ->

                setValue(value?.get("name").toString())

            }
        }
    }


    suspend fun addUserDataToFirestore(data: MutableMap<String, Any?>): Task<Void> {

        return FirebaseFirestore.getInstance().collection("user")
            .document(getCurrentUserId().toString())
            .set(data).addOnSuccessListener {
                return@addOnSuccessListener
            }.addOnFailureListener {
                return@addOnFailureListener
            }
    }

    suspend fun addArticleDataToFirestore(data: MutableMap<String, Any?>): Task<DocumentReference> {

        return FirebaseFirestore.getInstance().collection("articles")
            .add(data).addOnSuccessListener {

                return@addOnSuccessListener
            }.addOnFailureListener {
                return@addOnFailureListener
            }
    }
}