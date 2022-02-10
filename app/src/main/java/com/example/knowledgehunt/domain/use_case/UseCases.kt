package com.example.knowledgehunt.domain.use_case

data class AuthUseCases(
    val createUserWithEmailAndPassword: CreateUserWithEmailAndPassword,
    val logout: Logout,
    val getCurrentUser: GetCurrentUser,
    val sendEmailVerification: SendEmailVerification,
    val getCurrentUserID: GetCurrentUserID,
    val login: Login,
    val resetPassword: ResetPassword,
    val getCurrentUserEmail: GetCurrentUserEmail,
)

data class FirestoreUseCases(
    val addUserDataToFirestore: AddUserDataToFirestore,
    val addArticleDataToFirestore: AddArticleDataToFirestore,
    val getArticles: GetArticles,
    val addImageUrlToArticleDocument: AddImageUrlToArticleDocument,
    val getArticleAuthorNameById: GetArticleAuthorNameById,
    val getArticleViewsCount: GetArticleViewsCount,
    val updateArticleViewsCount: UpdateArticleViewsCount,
    val getCurrentUserData: GetCurrentUserData,
    val getMyArticles: GetMyArticles,
    val getMyArticleId: GetMyArticleId,
)

data class StorageUseCases(
    val uploadStorageImage: UploadStorageImage,
    val getStorageImageUrl: GetStorageImageUrl,
)

data class ImageUseCases(
    val compressImage: CompressImage,
)