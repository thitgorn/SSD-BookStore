package com.example.thitiwat.booklist.model

import java.util.*
import kotlin.collections.ArrayList

/**
 * Created by thitiwat on 3/30/18.
 */
abstract class BookRepository : Observable() {

    val bookList = ArrayList<Book>()

    abstract fun loadAllBooks()

    fun getBooks(): ArrayList<Book> {
        return this.bookList
    }

    fun sortBook(query: String) {
        when(query.toLowerCase()) {
            "title" -> bookList.sortBy { book -> book.title }
        }
    }

    fun filterBook(query : String): ArrayList<Book> {
        return this.bookList.filter{
            it.title.contains(query , true) ||
                    it.publicationYear.toString().contains(query , true) }
                as ArrayList<Book>
    }
}