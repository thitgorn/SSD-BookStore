package com.example.thitiwat.booklist

import android.content.Intent
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.provider.AlarmClock.EXTRA_MESSAGE
import android.view.Menu
import android.view.View
import android.widget.*
import com.example.thitiwat.booklist.model.Book
import com.example.thitiwat.booklist.model.DataBookRepository
import com.example.thitiwat.booklist.presenter.BookPresenter
import com.example.thitiwat.booklist.presenter.BookView
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity(), BookView {

    lateinit var BookView: BookPresenter
    lateinit var options: Spinner
    lateinit var result : TextView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        BookView = BookPresenter(this, DataBookRepository())
        BookView.start()

        syncSearchView(search_view)

        options = findViewById(R.id.spinner)
        result = findViewById(R.id.result)

        var option = arrayOf("Sort By Name","Sort by Year")
        options.adapter = ArrayAdapter<String>(this,android.R.layout.simple_list_item_1,option)

        options.onItemSelectedListener = object : AdapterView.OnItemSelectedListener{
            override fun onNothingSelected(p0: AdapterView<*>?) {
                result.text = "Select Sort"
            }

            override fun onItemSelected(p0: AdapterView<*>?, p1: View?, p2: Int, p3: Long) {
                result.text = option.get(p2)
                BookView.repository.sortBook(result.text.substring(8))
            }

        }


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
