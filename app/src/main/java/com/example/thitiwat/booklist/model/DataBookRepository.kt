package com.example.thitiwat.booklist.model

import android.os.AsyncTask
import android.util.Log
import org.json.JSONArray
import org.json.JSONObject
import java.net.URL

/**
 * Created by thitiwat on 3/30/18.
 */
class DataBookRepository: BookRepository() {
    override fun loadAllBooks() {
        bookList.clear()
        val task = DataBookLoader()
        task.execute()
    }

    inner class DataBookLoader: AsyncTask<String, String, String>(){
        override fun doInBackground(vararg p0: String?): String {
            return URL("https://theory.cpe.ku.ac.th/~jittat/courses/sw-spec/ebooks/books.json").readText()
        }

        override fun onPostExecute(result: String?) {
            if(result!=null) {
                var jsonData = JSONArray(result)

                for( i in 0..(jsonData.length()-1)){
                    var tmp = jsonData.getJSONObject(i)
                    var book = Book( tmp.getString("id").toInt() , tmp.get("title").toString() , tmp.getString("price").toDouble()  , tmp.getString("pub_year").toInt() , tmp.getString("img_url").toString())
                    bookList.add( book )
                }

                setChanged()
                notifyObservers()
            }
        }

    }
}