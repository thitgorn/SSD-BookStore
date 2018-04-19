package com.example.thitiwat.booklist

import android.content.Intent
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.provider.AlarmClock.EXTRA_MESSAGE
import android.view.Menu
import android.view.View
import android.widget.ArrayAdapter
import android.widget.SearchView
import com.example.thitiwat.booklist.model.Book
import com.example.thitiwat.booklist.model.DataBookRepository
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

        syncSearchView(search_view)

    }

    fun startUserActivity(view : View) {
        val intent = Intent(this, UserActivity::class.java).apply {
            putExtra(EXTRA_MESSAGE, "SEND MESSAGE")
        }
        startActivity(intent)
    }

    override fun setBookList(books: ArrayList<Book>) {
        val array = arrayOfNulls<String>(books.size)

        for (i in 0..(books.size-1)) {
            array[i] = books.get(i).toString()
        }
        var adapter = ArrayAdapter<String>(this,android.R.layout.simple_list_item_1,array)
        list_view.adapter = adapter
    }

    private fun syncSearchView(searchView: SearchView) {
        searchView.setOnQueryTextListener(object : SearchView.OnQueryTextListener {

            override fun onQueryTextChange(newText: String): Boolean {
//                BookView.repository.sortBook("title")
                setBookList(BookView.repository.filterBook(newText))
                return false
            }

            override fun onQueryTextSubmit(query: String): Boolean {
                setBookList(BookView.repository.filterBook(query))
                return false
            }

        })
    }
}
