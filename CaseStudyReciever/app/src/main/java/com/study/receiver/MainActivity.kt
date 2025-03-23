package com.study.receiver

import android.os.Bundle
import android.widget.TextView
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import com.study.receiver.ui.theme.CaseStudyReceiverTheme
import kotlinx.parcelize.Parcelize
import android.os.Build
import android.content.Intent
import android.os.Parcelable

@Parcelize
data class Contact(val name: String, val phone: String, val email: String) : Parcelable

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        // Retrieve the contact from the intent, ensuring compatibility with older API levels
        val contact = if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.TIRAMISU) {
            intent.getParcelableExtra("contact", Contact::class.java)
        } else {
            intent.getParcelableExtra<Contact>("contact")
        }

        // If contact is not null, update the TextView with its details
        contact?.let {
            findViewById<TextView>(R.id.contactTextView).text = "Name: ${it.name}\nPhone: ${it.phone}\nEmail: ${it.email}"
        }
    }
}