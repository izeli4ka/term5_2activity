package com.example.app2activity

import android.content.Context
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.TextView

class SecondActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_second)

        val textView = findViewById<TextView>(R.id.myTextView)
        val receivedValue = intent.getStringExtra(EXTRA_DATA)
        textView.text = receivedValue
    }
    companion object {
        const val EXTRA_DATA = "EXTRA_DATA"

        fun createIntent(context: Context, data: String): Intent {
            return Intent(context, SecondActivity::class.java)
                .putExtra(EXTRA_DATA, data)
        }
    }
}