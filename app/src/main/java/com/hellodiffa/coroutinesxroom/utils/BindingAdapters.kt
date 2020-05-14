package com.hellodiffa.coroutinesxroom.utils

import android.widget.ImageView
import androidx.databinding.BindingAdapter
import com.hellodiffa.coroutinesxroom.R
import com.squareup.picasso.Picasso

/*
* created by Diffa
*/

@BindingAdapter("image")
fun loadImage(img: ImageView, imagePath: String?) {
    Picasso.get()
        .load(imagePath)
        .error(R.drawable.error_list_image)
        .placeholder(R.drawable.default_list_image)
        .transform(CircleTransformation())
        .into(img)
}
