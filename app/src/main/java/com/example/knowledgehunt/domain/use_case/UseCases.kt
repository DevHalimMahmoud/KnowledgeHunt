package com.example.knowledgehunt.domain.use_case

data class AuthUseCases(
    val createUserWithEmailAndPassword: CreateUserWithEmailAndPassword,
    val logout: Logout,
    val getCurrentUser: GetCurrentUser,
    val sendEmailVerification: SendEmailVerification,
    val getCurrentUserID: GetCurrentUserID,
    val login: Login,
    val resetPassword: ResetPassword,
)

data class FirestoreUseCases(
    val addUserDataToFirestore: AddUserDataToFirestore,
    val addArticleDataToFirestore: AddArticleDataToFirestore,
)

data class StorageUseCases(
    val uploadStorageImage: UploadStorageImage,
    val getStorageImageUrl: GetStorageImageUrl,
)

data class ImageUseCases(
    val compressImage: CompressImage,
)