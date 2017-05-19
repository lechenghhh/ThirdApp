package com.lecheng.hello.thirdapp.ActivityList

import android.support.v7.app.ActionBarActivity
import android.os.Bundle
import android.widget.TextView

import com.lecheng.hello.thirdapp.R

import kotlinx.android.synthetic.main.aty061_kotlin. *              //依赖kotlinx的xml找到控件

class Aty061Kotlin : ActionBarActivity() {
    private var i = 0;

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.aty061_kotlin)
        init("init()");
    }

    private fun init(str: String?) {
        var tv1 = findViewById(R.id.tv1) as TextView
        tv1.setOnClickListener { tv1.text = "fffff" }
        tv2.setOnClickListener { tv2.text = "" }
        btn1.setOnClickListener {
            i++
            tv1.text = "Hello Kotlin !"
            tv2.append(str + et1.text + i)
        }
    }


}
