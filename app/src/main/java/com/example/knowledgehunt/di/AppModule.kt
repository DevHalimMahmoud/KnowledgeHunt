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
import javax.inject.Singleton

@Module
@ExperimentalCoroutinesApi
@InstallIn(SingletonComponent::class)
object AppModule {
    @Singleton
    @Provides
    fun provideFirebaseAuthImpl(

    ): IFirebaseAuth = FirebaseAuthImpl()

    @Singleton
    @Provides
    fun provideFirebaseStorageImpl(

    ): IFirebaseStorage = FirebaseStorageImpl()

    @Singleton
    @Provides
    fun provideFirebaseFirestoreImpl(

    ): IFirebaseFirestore = FirebaseFirestoreImpl()

    @Singleton
    @Provides
    fun provideImageImpl(

    ): IImage = ImageImpl()

    @Singleton
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
        GetCurrentUserEmail(firebaseAuthRepository),
        UpdateCurrentUserEmail(firebaseAuthRepository),
        ReAuthenticateCurrentUser(firebaseAuthRepository),
    )

    @Singleton
    @Provides
    fun provideStorageUseCases(
        firebaseStorageRepository: IFirebaseStorage,
    ) = StorageUseCases(
        UploadStorageImage(firebaseStorageRepository),
        GetStorageImageUrl(firebaseStorageRepository),
        DeleteArticleStorageImage(firebaseStorageRepository)
    )

    @Singleton
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
        GetMyArticleId(firebaseFirestoreRepository),
        DeleteArticleFirestoreDocument(firebaseFirestoreRepository),
        UpdateProfileData(firebaseFirestoreRepository),
        GetUserDataStatistics(firebaseFirestoreRepository),
        UpdateUserDataStatistics(firebaseFirestoreRepository),
        GetQuestions(firebaseFirestoreRepository),
        AddQuestion(firebaseFirestoreRepository),
        GetMyQuestions(firebaseFirestoreRepository),
        DeleteQuestionFirestoreDocument(firebaseFirestoreRepository),
        UpdateQuestionData(firebaseFirestoreRepository),
        GetUserDataById(firebaseFirestoreRepository),
        GetMCQTests(firebaseFirestoreRepository),
    )

    @Provides
    fun provideImageUseCases(
        imageRepository: IImage,
    ) = ImageUseCases(
        CompressImage(imageRepository),
    )
}