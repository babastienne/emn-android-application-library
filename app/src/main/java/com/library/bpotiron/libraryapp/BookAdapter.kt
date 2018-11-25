package com.library.bpotiron.libraryapp

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.BaseAdapter

class BookAdapter(context: Context, private val booksList: List<Book>) : BaseAdapter() {
    private val inflater: LayoutInflater = LayoutInflater.from(context)
    override fun getView(position: Int, convertView: View?, parent: ViewGroup?): View? {
        var convertView = convertView
        val selectedBook = getItem(position)
        if (convertView == null)
            convertView = inflater.inflate(R.layout.activity_main_list, parent, false)
        val bookItemView = convertView as BookItemView
        selectedBook?.let{
            bookItemView.bindView(it as Book)
        }
        return bookItemView
    }

    override fun getItem(index: Int): Book {
        return booksList[index]
    }

    override fun getItemId(index: Int): Long {
        return booksList[index].hashCode().toLong()
    }

    override fun getCount(): Int {
        return booksList.size
    }

}