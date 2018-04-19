package com.example.thitiwat.booklist.model

import java.util.*

/**
 * Created by thitiwat on 4/19/18.
 */
class User(name:String = "Unkowned name",balance : Int):Observable() {
    var balance = balance
    var myBook = null
    var cartBook = null

    fun addBalance( value : Int ) {
        if(value!=null) {
            this.balance+=value
            setChanged()
            notifyObservers()
        }
    }
}