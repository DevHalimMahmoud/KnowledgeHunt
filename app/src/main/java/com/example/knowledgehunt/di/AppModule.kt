package com.example.knowledgehunt.di

import com.example.knowledgehunt.data.repository.FirebaseAuthImpl
import com.example.knowledgehunt.data.repository.FirebaseFirestoreImpl
import com.example.knowledgehunt.data.repository.FirebaseStorageImpl
import com.example.knowledgehunt.data.repository.ImageImpl
import com.example.knowledgehunt.domain.repository.IFirebaseAuth
import com.example.knowledgehunt.domain.repository.IFirebaseFirestore
import com.example.knowledgehunt.domain.repository.IFirebaseStorage
import com.example.knowledgehunt.domain.repository.IImage
import com.example.knowledgehunt.domain.use_case.*
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import kotlinx.coroutines.ExperimentalCoroutinesApi

@Module
@ExperimentalCoroutinesApi
@InstallIn(SingletonComponent::class)
object AppModule {

    @Provides
    fun provideFirebaseAuthImpl(

    ): IFirebaseAuth = FirebaseAuthImpl()

    @Provides
    fun provideFirebaseStorageImpl(

    ): IFirebaseStorage = FirebaseStorageImpl()

    @Provides
    fun provideFirebaseFirestoreImpl(

    ): IFirebaseFirestore = FirebaseFirestoreImpl()

    @Provides
    fun provideImageImpl(

    ): IImage = ImageImpl()

    @Provides
    fun provideUseCases(
        firebaseFirestoreRepository: IFirebaseFirestore,
        firebaseStorageRepository: IFirebaseStorage,
        firebaseAuthRepository: IFirebaseAuth,
        imageRepository: IImage,
    ) = UseCases(
        UploadStorageImage(firebaseStorageRepository),
        CreateUserWithEmailAndPassword(firebaseAuthRepository),
        Logout(firebaseAuthRepository),
        GetCurrentUser(firebaseAuthRepository),
        CompressImage(imageRepository),
        SendEmailVerification(firebaseAuthRepository),
        AddUserDataToFirestore(firebaseFirestoreRepository),
        GetStorageImage(firebaseStorageRepository),
        GetCurrentUserID(firebaseAuthRepository),
        Login(firebaseAuthRepository),
        ResetPassword(firebaseAuthRepository),
        AddArticleDataToFirestore(firebaseFirestoreRepository),
    )
}