package com.example.thitiwat.booklist

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import com.example.thitiwat.booklist.presenter.UserPresenter
import com.example.thitiwat.booklist.presenter.UserView
import kotlinx.android.synthetic.main.activity_user.*

class UserActivity : AppCompatActivity(), UserView {

    val userPresenter = UserPresenter(this)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_user)

        userPresenter.user.addObserver(userPresenter)
        balance.text = userPresenter.user.balance.toString()
    }

    fun addBalance(view: View) {
        val value = addBalanceField.text.toString().toInt()
        userPresenter.user.addBalance(value)
    }

    override fun setBalanceView(value: Int) {
        balance.text = value.toString()
    }
}
