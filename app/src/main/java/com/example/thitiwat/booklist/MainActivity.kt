package com.example.thitiwat.booklist

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.widget.ArrayAdapter
import com.example.thitiwat.booklist.model.Book
import com.example.thitiwat.booklist.model.DataBookRepository
import com.example.thitiwat.booklist.model.MockBookRepository
import com.example.thitiwat.booklist.presenter.BookPresenter
import com.example.thitiwat.booklist.presenter.BookView
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity(), BookView {

    lateinit var BookView: BookPresenter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        BookView = BookPresenter(this, DataBookRepository())
        BookView.start()

    }

    override fun setBookList(books: ArrayList<Book>) {
        val array = arrayOfNulls<String>(books.size)

        for (i in 0..(books.size-1)) {
            array[i] = books.get(i).toString()
        }
//
        var adapter = ArrayAdapter<String>(this,android.R.layout.simple_list_item_1,array)
        list_view.adapter = adapter
    }
}
