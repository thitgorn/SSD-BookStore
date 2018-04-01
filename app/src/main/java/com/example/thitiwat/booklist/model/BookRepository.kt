package com.example.thitiwat.booklist.model

import java.util.*
import kotlin.collections.ArrayList

/**
 * Created by thitiwat on 3/30/18.
 */
abstract class BookRepository : Observable() {
    abstract fun loadAllBooks()
    abstract fun getBooks(): ArrayList<Book>
}