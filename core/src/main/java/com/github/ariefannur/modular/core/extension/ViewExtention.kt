package com.github.ariefannur.modular.core.extension

import android.content.res.Resources
import android.view.View
import android.widget.EditText
import android.widget.ImageView
import androidx.annotation.CheckResult
import androidx.core.widget.doOnTextChanged
import com.bumptech.glide.Glide
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.channels.awaitClose
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.callbackFlow
import kotlinx.coroutines.flow.onStart
import com.bumptech.glide.request.RequestOptions

import com.bumptech.glide.load.resource.bitmap.RoundedCorners




@ExperimentalCoroutinesApi
@CheckResult
fun EditText.textChanges(): Flow<CharSequence?> {
    return callbackFlow {
        val listener = doOnTextChanged { text, _, _, _ -> trySend(text) }
        awaitClose { removeTextChangedListener(listener) }
    }.onStart { emit(text) }
}

fun View.gone() {
    visibility = View.GONE
}

fun View.visible() {
    visibility = View.VISIBLE
}

fun ImageView.displayAvatar(url: String) {
    val roundedCorners = RoundedCorners(20.dp)
    val options = RequestOptions.bitmapTransform(roundedCorners)
    Glide.with(context)
        .load(url)
        .apply(options)
        .into(this)
}

val Int.dp: Int
    get() = (this * Resources.getSystem().displayMetrics.density + 0.5f).toInt()