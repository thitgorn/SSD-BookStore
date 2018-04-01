package com.example.thitiwat.booklist.presenter

import com.example.thitiwat.booklist.model.Book

/**
 * Created by thitiwat on 3/30/18.
 */
interface BookView {
    fun setBookList(books: ArrayList<Book>)
}