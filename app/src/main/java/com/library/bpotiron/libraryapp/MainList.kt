package com.library.bpotiron.libraryapp

import android.content.res.Configuration
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import com.library.bpotiron.libraryapp.ListBooksFragment.OnClickListBookListener

class MainList: AppCompatActivity(), OnClickListBookListener {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_library)
        supportFragmentManager.beginTransaction()
            .replace(R.id.containerFrame, ListBooksFragment(), ListBooksFragment::class.java.name)
            .commit()
    }

    override fun onClick(bookObject: Book) {
        val fragmentBook = DetailsBookFragment.newInstance(bookObject)
        if (Configuration.ORIENTATION_LANDSCAPE == resources.configuration.orientation) {
            supportFragmentManager
                .beginTransaction()
                .replace(R.id.detailsFrame, fragmentBook, DetailsBookFragment::class.java.name)
                .commit()
        } else {
            supportFragmentManager
                .beginTransaction()
                .replace(R.id.containerFrame, fragmentBook, DetailsBookFragment::class.java.name)
                .addToBackStack(DetailsBookFragment::class.java.name)
                .commit()
        }
    }

    override fun onBackPressed() {
        super.onBackPressed()
        supportFragmentManager.popBackStack()
    }
}
