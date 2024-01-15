package com.example.app2activity

import android.content.Intent
import android.net.Uri
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText

class MainActivity : AppCompatActivity() {
    private var textValue: String = ""
    private var phoneValue: String = ""

    companion object {
        private const val SAVED_TEXT_KEY = "SAVED_TEXT_KEY"
        private const val SAVED_PHONE_KEY = "SAVED_PHONE_KEY"
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val activityButton = findViewById<Button>(R.id.button_activity)
        val phoneButton = findViewById<Button>(R.id.button_phone)
        val textEditText = findViewById<EditText>(R.id.Text)
        val phoneEditText = findViewById<EditText>(R.id.TextPhone)

        activityButton.setOnClickListener {
            textValue = textEditText.text.toString()
            startSecondActivityWithData(textValue)
        }
        phoneButton.setOnClickListener {
            phoneValue = phoneEditText.text.toString()
            sendImplicitIntent(phoneValue)
        }

        if (savedInstanceState != null) {
            textValue = savedInstanceState.getString(SAVED_TEXT_KEY, "")
            phoneValue = savedInstanceState.getString(SAVED_PHONE_KEY, "")
            textEditText.setText(textValue)
            phoneEditText.setText(phoneValue)
        }
    }

    override fun onSaveInstanceState(outState: Bundle) {
        outState.putString(SAVED_TEXT_KEY, textValue)
        outState.putString(SAVED_PHONE_KEY, phoneValue)
        super.onSaveInstanceState(outState)
    }

    override fun onRestoreInstanceState(savedInstanceState: Bundle) {
        super.onRestoreInstanceState(savedInstanceState)
        textValue = savedInstanceState.getString(SAVED_TEXT_KEY, "")
        phoneValue = savedInstanceState.getString(SAVED_PHONE_KEY, "")
    }

    private fun startSecondActivityWithData(text: String) {
        val intent = SecondActivity.createIntent(this, "$text")
        startActivity(intent)
    }

    private fun sendImplicitIntent(phoneNumber: String) {
        val intent = Intent(Intent.ACTION_DIAL).apply {
            data = Uri.parse("tel:$phoneNumber")
        }
        startActivity(intent)
    }
}