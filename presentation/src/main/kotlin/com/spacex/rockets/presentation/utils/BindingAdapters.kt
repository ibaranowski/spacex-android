package com.spacex.rockets.presentation.utils

import android.databinding.BindingAdapter
import android.graphics.drawable.Drawable
import android.support.v7.widget.RecyclerView
import android.view.View
import android.view.inputmethod.EditorInfo
import android.widget.*
import com.jakewharton.rxrelay2.Relay
import android.support.annotation.DrawableRes
import android.support.v4.content.ContextCompat
import android.text.*
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions
import java.io.File


@BindingAdapter("onClick")
fun View.onClick(listener: Relay<Boolean>) {
    this.setOnClickListener {
        if (!it.isEnabled) return@setOnClickListener
        listener.accept(true)
    }
}

@BindingAdapter("onTextChanged")
fun EditText.onTextChanged(listener: Relay<CharSequence>) {

    this.addTextChangedListener(object : TextWatcher {
        override fun afterTextChanged(text: Editable?) {}
        override fun beforeTextChanged(text: CharSequence?, p1: Int, p2: Int, p3: Int) {}
        override fun onTextChanged(text: CharSequence?, p1: Int, p2: Int, p3: Int) {
            if (text != null) {
                listener.accept(text)
            } else {
                listener.accept("")
            }
        }
    })
}

@BindingAdapter("visibility")
fun View.setVisibility(visible: Boolean) {
    this.visibility = if (visible) View.VISIBLE else View.GONE
}

@BindingAdapter("actionNext")
fun TextView.onImeActionLabel(listener: Relay<Boolean>) {
    this.setOnEditorActionListener { _, actionId, _ ->
        if (actionId == EditorInfo.IME_ACTION_NEXT) {
            listener.accept(true)
            true
        } else {
            false
        }
    }
}

@BindingAdapter("hasFixedSize")
fun RecyclerView.setHasFixedSize(hasFixedSize: Boolean) {
    this.setHasFixedSize(hasFixedSize)
}

@BindingAdapter("src")
fun ImageView.setImageUrl(url: String) {

    //FIXME Placeholder should be setted in the layout
    val placeholder = ContextCompat.getDrawable(context, android.R.drawable.btn_plus)
    Glide.with(context).load(File(url))
            .apply(RequestOptions().placeholder(placeholder).error(placeholder))
            .into(this)
}

@BindingAdapter("src")
fun ImageView.setImageResource(resId: Int) {
    setImageDrawable(ContextCompat.getDrawable(context, resId))
}

@BindingAdapter("drawableStartCompat")
fun TextView.drawableEndCompat(@DrawableRes drawableResId: Int) {
    val drawables = this.compoundDrawables
    val wrapped = ContextCompat.getDrawable(this.context, drawableResId)
    this.setCompoundDrawablesWithIntrinsicBounds(wrapped, drawables[1], drawables[2], drawables[3])
}

@BindingAdapter("textColorId")
fun TextView.setTextColorId(resId: Int) {
    this.setTextColor(ContextCompat.getColor(this.context, resId))
}

@BindingAdapter(value = ["src", "placeholder"], requireAll = false)
fun ImageView.setImageUrl(url: String?, placeholder: Drawable?) {

    if(url == null) return

    val glide = Glide.with(context).load(if(url.startsWith("http")) url else File(url))

    if(placeholder != null) {
//        val placeholderDrawable = ContextCompat.getDrawable(context, placeholder)
        glide.apply(RequestOptions().placeholder(placeholder).error(placeholder))
            .into(this)
    } else {
        glide.into(this)
    }
}

//@BindingAdapter("onEditorAction")
//fun EditText.onEditorAction(listener: Relay<Int>) {
//    this.setOnEditorActionListener { _, actionId, _ ->
//        listener.accept(actionId)
//        true
//    }
//}
//
//@BindingAdapter("stringAdapter")
//fun AutoCompleteTextView.stringAdapter(values: List<String>) {
//    val adapter = ArrayAdapter(this.context, R.layout.simple_list_item_1, values)
//    this.setAdapter(adapter)
//}