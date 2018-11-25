package com.library.bpotiron.libraryapp

import android.os.Parcelable
import android.os.Parcel

data class Book(val title: String?, val cover: String?, val price: String?, val synopsis: Array<String>?, val isbn: String?) :
    Parcelable {
    constructor(parcel: Parcel) : this(
        parcel.readString(),
        parcel.readString(),
        parcel.readString(),
        parcel.createStringArray(),
        parcel.readString()
    ) {
    }

    override fun writeToParcel(parcel: Parcel, flags: Int) {
        parcel.writeString(title)
        parcel.writeString(cover)
        parcel.writeString(price)
        parcel.writeStringArray(synopsis)
        parcel.writeString(isbn)
    }

    override fun describeContents(): Int {
        return 0
    }

    companion object CREATOR : Parcelable.Creator<Book> {
        override fun createFromParcel(parcel: Parcel): Book {
            return Book(parcel)
        }

        override fun newArray(size: Int): Array<Book?> {
            return arrayOfNulls(size)
        }
    }

}