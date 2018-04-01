package com.example.thitiwat.booklist.model

/**
 * Created by thitiwat on 3/30/18.
 */
class MockBookRepository : BookRepository() {

    override fun loadAllBooks() {
        bookList.clear()
        bookList.add(Book(1,"How to win BNK election",500.0))
        bookList.add(Book(2,"How to write Android App",199.0))
        bookList.add(Book(3,"Sleep today",39.9))
        setChanged()
        notifyObservers()
    }

}