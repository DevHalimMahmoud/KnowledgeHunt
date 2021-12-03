package com.example.knowledgehunt.services

import androidx.lifecycle.MutableLiveData
import com.google.android.gms.tasks.Task
import com.google.firebase.firestore.DocumentReference
import com.google.firebase.firestore.FirebaseFirestore
import java.util.*

fun addDonationData(data: HashMap<String, Any?>): Task<DocumentReference> {

    return FirebaseFirestore.getInstance().collection("requests")
        .add(data).addOnSuccessListener {
            return@addOnSuccessListener
        }.addOnFailureListener {
            return@addOnFailureListener
        }
}

fun getUserName(): MutableLiveData<String> {

    return MutableLiveData<String>().apply {

        val db = FirebaseFirestore.getInstance()
        getCurrentUserId().let {
            db.collection("users").document(
                it!!
            )
        }.addSnapshotListener { value, e ->

            setValue(value?.get("name").toString())

        }
    }
}

fun addUserDataToFirestore(data: HashMap<String, Any?>): Task<Void> {

    return FirebaseFirestore.getInstance().collection("users")
        .document(getCurrentUserId().toString())
        .set(data).addOnSuccessListener {
            return@addOnSuccessListener
        }.addOnFailureListener {
            return@addOnFailureListener
        }
}