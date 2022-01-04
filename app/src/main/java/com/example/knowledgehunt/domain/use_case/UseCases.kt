package com.example.knowledgehunt.domain.use_case

data class UseCases(
    val uploadStorageImage: UploadStorageImage,
    val createUserWithEmailAndPassword: CreateUserWithEmailAndPassword,
    val logout: Logout,
    val getCurrentUser: GetCurrentUser,
    val compressImage: CompressImage,
    val sendEmailVerification: SendEmailVerification,
    val addUserDataToFirestore: AddUserDataToFirestore,
    val getStorageImage: GetStorageImage,
    val getCurrentUserID: GetCurrentUserID,
    val login: Login,
    val resetPassword: ResetPassword,
    val addArticleDataToFirestore: AddArticleDataToFirestore,
)