package com.library.bpotiron.libraryapp

import android.content.Context
import android.util.AttributeSet
import android.widget.ImageView
import android.widget.LinearLayout
import android.widget.TextView
import com.bumptech.glide.Glide

class BookItemView @JvmOverloads constructor(context: Context, attrs: AttributeSet? = null, defStyleAttr: Int = 0): LinearLayout(context, attrs, defStyleAttr) {
    private var title: TextView? = null
    private var image: ImageView? = null

    fun bindView(book: Book) {
        this.title?.text = book.title
        this.image?.let {
            Glide.with(this).load(book.cover).into(it)
        }
    }

    override fun onFinishInflate() {
        super.onFinishInflate()
        this.title = findViewById(R.id.titleView)
        this.image = findViewById(R.id.imageView)
    }
}
