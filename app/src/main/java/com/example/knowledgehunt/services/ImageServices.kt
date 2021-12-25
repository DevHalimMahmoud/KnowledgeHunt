package com.example.knowledgehunt.services

import android.content.Context
import android.graphics.Bitmap
import android.graphics.BitmapFactory
import android.graphics.ImageDecoder
import android.net.Uri
import android.os.Build
import android.provider.MediaStore
import androidx.compose.runtime.MutableState
import java.io.ByteArrayOutputStream

object ImageServices {

    suspend fun compressImage(context: Context, imageUri: MutableState<Uri?>?): Bitmap? {
        val tempBitmap: Bitmap = if (Build.VERSION.SDK_INT < 28) {
            MediaStore.Images
                .Media.getBitmap(context.contentResolver, imageUri?.value)

        } else {
            val source = ImageDecoder
                .createSource(context.contentResolver, imageUri?.value!!)
            ImageDecoder.decodeBitmap(source)
        }
        val ostream = ByteArrayOutputStream()

        tempBitmap.compress(Bitmap.CompressFormat.PNG, 100, ostream)
        return BitmapFactory.decodeByteArray(ostream.toByteArray(), 0, ostream.toByteArray().size);
    }


}