package com.library.bpotiron.libraryapp

import android.os.Bundle
import android.support.v4.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import com.bumptech.glide.Glide

class DetailsBookFragment : Fragment() {
    companion object {
        fun newInstance(book: Book): DetailsBookFragment {
            val args = Bundle()
            val bookFragment = DetailsBookFragment()

            args.putParcelable("BOOK", book)
            bookFragment.arguments = args

            return bookFragment
        }
    }
    private val CURRENCY = "€"
    private var title: TextView? = null
    private var cover: ImageView? = null
    private var description: TextView? = null
    private var price: TextView? = null

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        val inflateView = inflater.inflate(R.layout.fragment_details, container, false)
        val selectedBook: Book? = arguments?.getParcelable("BOOK")

        this.title = inflateView.findViewById(R.id.titleBook) as TextView
        cover = inflateView.findViewById(R.id.coverImage) as ImageView
        this.description = inflateView.findViewById(R.id.descriptionBook) as TextView
        this.price = inflateView.findViewById(R.id.priceBook) as TextView

        this.title?.text = selectedBook?.title
        this.cover?.let {
            Glide.with(this)
                .load(selectedBook?.cover)
                .into(it)
        }
        var synopsis = ""
        selectedBook?.synopsis?.forEach {
            synopsis = synopsis + it
        }
        this.description?.text = synopsis
        this.price?.text = selectedBook?.price + CURRENCY

        return inflateView
    }
}