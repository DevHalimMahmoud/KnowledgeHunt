package com.example.knowledgehunt.presentation.screens.article.articleDetailsScreen

import android.net.Uri
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.knowledgehunt.domain.use_case.StorageUseCases
import com.example.knowledgehunt.domain.utils.ArticleArguments
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class ArticleDetailsScreenViewModel @Inject constructor(
    private val storageUseCases: StorageUseCases,
) : ViewModel() {
    val authorImageUrl: MutableState<Uri> =
        mutableStateOf(Uri.parse("https://storage.googleapis.com/glaze-ecom.appspot.com/images/P2uSA0D_6/thumbs/232.png"))

    init {
        getAuthorImageUrl()
    }

    private fun getAuthorImageUrl() {
        viewModelScope.launch {
            storageUseCases.getStorageImageUrl(
                "user",
                ArticleArguments.instance?.articleItemData?.user_id!!
            ).addOnCompleteListener {
                authorImageUrl.value = it.result
            }
        }
    }

}