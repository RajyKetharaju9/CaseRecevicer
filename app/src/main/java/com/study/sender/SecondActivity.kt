package com.study.sender

import android.os.Bundle
import android.widget.TextView
import androidx.activity.ComponentActivity
import androidx.activity.enableEdgeToEdge
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat

class SecondActivity : ComponentActivity() {
    private var textView : TextView? = null;
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_second)
        textView  = findViewById<TextView>(R.id.textView);
    }

    override fun onResume() {
        super.onResume()
        val contact = intent.getParcelableExtra<Contact>("contact");
        textView!!.text = "Name: ${contact?.name}, Phone: ${contact?.phone}, Email: ${contact?.email}"
    }
}