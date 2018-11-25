package com.library.bpotiron.libraryapp

import android.content.Context
import android.os.Bundle
import android.support.v4.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ListView
import kotlin.collections.ArrayList
import retrofit2.*
import retrofit2.converter.gson.GsonConverterFactory

class ListBooksFragment : Fragment() {
    private val BOOK_CODE = "BK"
    private var listView: ListView? = null
    private var booksList = ArrayList<Book>()
    private var onClickListBookListener: OnClickListBookListener? = null

    override fun onCreateView(layoutInflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        val view = layoutInflater.inflate(R.layout.fragment_list, container, false)
        this.listView = view.findViewById(R.id.bookListView) as ListView
        if (savedInstanceState?.containsKey(BOOK_CODE) == true) {
            this.booksList = savedInstanceState.getParcelableArrayList(BOOK_CODE)
        } else {
            this.booksList = this.getBooks()
        }

        this.listView?.setOnItemClickListener {
                parent,
                view,
                position,
                id -> this.onClickListBookListener?.onClick(
                    this.booksList[position]
                )
            }
        return view
    }

    override fun onAttach(currentContext: Context?) {
        super.onAttach(currentContext)
        when (currentContext) {
            is OnClickListBookListener -> {
                this.onClickListBookListener = currentContext
            }
            else -> {
                throw Exception("Error during the activation of the BookListener")
            }
        }
    }

    private fun getBooks(): ArrayList<Book> {
        this.booksList = ArrayList()
        val bookAdapter = BookAdapter(this.context!!, this.booksList)
        this.listView!!.adapter = bookAdapter
        val retrofit = Retrofit.Builder()
            .baseUrl("http://henri-potier.xebia.fr/")
            .addConverterFactory(GsonConverterFactory.create())
            .build()

        val retrofitAPI = retrofit.create(HenriPotierService::class.java)

        retrofitAPI.listBooks().enqueue(object : Callback<Array<Book>> {
            override fun onFailure(call: Call<Array<Book>>, t: Throwable) {
                throw Exception("Failure during queuing into the list of booksList")
            }

            override fun onResponse(call: Call<Array<Book>>, response: Response<Array<Book>>) {
                response.body()?.forEach {
                    booksList.add(it)
                }
                bookAdapter.notifyDataSetChanged()
            }
        })
        return booksList
    }

    override fun onSaveInstanceState(outState: Bundle) {
        super.onSaveInstanceState(outState)
        outState.putParcelableArrayList(BOOK_CODE, this.booksList)
    }

    interface OnClickListBookListener {
        fun onClick(book:Book)
    }
}