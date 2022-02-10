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
    fun provideAuthUseCases(
        firebaseAuthRepository: IFirebaseAuth,
    ) = AuthUseCases(
        CreateUserWithEmailAndPassword(firebaseAuthRepository),
        Logout(firebaseAuthRepository),
        GetCurrentUser(firebaseAuthRepository),
        SendEmailVerification(firebaseAuthRepository),
        GetCurrentUserID(firebaseAuthRepository),
        Login(firebaseAuthRepository),
        ResetPassword(firebaseAuthRepository),
        GetCurrentUserEmail(firebaseAuthRepository)
    )

    @Provides
    fun provideStorageUseCases(
        firebaseStorageRepository: IFirebaseStorage,
    ) = StorageUseCases(
        UploadStorageImage(firebaseStorageRepository),
        GetStorageImageUrl(firebaseStorageRepository),
    )

    @Provides
    fun provideFirestoreUseCases(
        firebaseFirestoreRepository: IFirebaseFirestore,
    ) = FirestoreUseCases(
        AddUserDataToFirestore(firebaseFirestoreRepository),
        AddArticleDataToFirestore(firebaseFirestoreRepository),
        GetArticles(firebaseFirestoreRepository),
        AddImageUrlToArticleDocument(firebaseFirestoreRepository),
        GetArticleAuthorNameById(firebaseFirestoreRepository),
        GetArticleViewsCount(firebaseFirestoreRepository),
        UpdateArticleViewsCount(firebaseFirestoreRepository),
        GetCurrentUserData(firebaseFirestoreRepository),
        GetMyArticles(firebaseFirestoreRepository),
    )

    @Provides
    fun provideImageUseCases(
        imageRepository: IImage,
    ) = ImageUseCases(
        CompressImage(imageRepository),
    )
}