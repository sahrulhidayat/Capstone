package com.sahrulhidayat.core.utils

import android.content.Context
import android.view.View
import android.widget.ImageView
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions
import com.google.android.material.snackbar.Snackbar
import com.sahrulhidayat.core.R

fun Context.loadImage(url: String?, imageView: ImageView) {
    Glide.with(this)
        .load(url)
        .apply(
            RequestOptions
                .placeholderOf(R.drawable.ic_image_loading)
                .error(R.drawable.ic_image_error)
        )
        .centerCrop()
        .into(imageView)
}

fun View.showSnackbar( message: CharSequence): Snackbar {
    return Snackbar.make(this, message, Snackbar.LENGTH_LONG)
        .apply { show() }
}

fun View.visible() {
    visibility = View.VISIBLE
}

fun View.gone() {
    visibility = View.GONE
}