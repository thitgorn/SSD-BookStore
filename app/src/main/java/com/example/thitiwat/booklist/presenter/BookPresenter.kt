package com.example.thitiwat.booklist.presenter

import com.example.thitiwat.booklist.model.BookRepository
import java.util.*

/**
 * Created by thitiwat on 3/30/18.
 */
class BookPresenter(val view: BookView , val repository: BookRepository): Observer {

    fun start() {
        repository.addObserver(this)
        repository.loadAllBooks()
    }

    override fun update(obj: Observable?, arg: Any?) {
        if(obj == repository) {
            view.setBookList(repository.getBooks())
        }
    }

}