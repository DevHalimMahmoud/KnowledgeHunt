package com.example.knowledgehunt.services

import com.google.android.datatransport.runtime.dagger.Module
import com.google.android.datatransport.runtime.dagger.Provides
import com.google.android.gms.tasks.Task
import com.google.firebase.auth.AuthResult
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.FirebaseUser
import dagger.hilt.android.scopes.ViewModelScoped

@ViewModelScoped
@Module
object FirebaseAuthServices {
    @Provides
    @ViewModelScoped
    fun getCurrentUserId(): String? {

        return FirebaseAuth.getInstance().currentUser?.tenantId
    }

    @Provides
    @ViewModelScoped
    fun getCurrentUser(): FirebaseUser? {

        return FirebaseAuth.getInstance().currentUser
    }

    @Provides
    @ViewModelScoped
    fun sendEmailVerification(): Task<Void> {

        return getCurrentUser()!!.sendEmailVerification().addOnCompleteListener { }
    }

    @Provides
    @ViewModelScoped
    fun getAuthInstance(): FirebaseAuth {

        return FirebaseAuth.getInstance()
    }

    @Provides
    @ViewModelScoped
    fun createUserWithEmailAndPassword(email: String, password: String): Task<AuthResult> {

        return getAuthInstance().createUserWithEmailAndPassword(email, password)
    }

    @Provides
    @ViewModelScoped
    fun getUserEmail(): String? {

        return FirebaseAuth.getInstance().currentUser?.email
    }

    @Provides
    @ViewModelScoped
    suspend fun login(email: String, password: String): Task<AuthResult> {
        return getAuthInstance().signInWithEmailAndPassword(
            email,
            password
        )


    }
}
