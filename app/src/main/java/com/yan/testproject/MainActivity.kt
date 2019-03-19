package com.yan.testproject

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.view.View
import android.widget.Toast
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        btn_click.visibility = View.VISIBLE
        btn_click.setOnClickListener {
            Toast.makeText(this, "click the button!", Toast.LENGTH_SHORT).show()
        }

    }
}
