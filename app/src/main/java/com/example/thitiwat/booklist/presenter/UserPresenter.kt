package com.example.thitiwat.booklist.presenter

import android.view.View
import com.example.thitiwat.booklist.model.User
import java.util.*

/**
 * Created by thitiwat on 4/19/18.
 */
class UserPresenter(val view : UserView): Observer {

    var user = User("Guy" , 55555)

    override fun update(p0: Observable?, p1: Any?) {
        view.setBalanceView(user.balance)
    }
}