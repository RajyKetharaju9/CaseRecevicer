package com.study.sender

import android.content.ComponentName
import android.content.Intent
import android.os.Bundle
import android.os.Parcelable
import android.util.Log
import android.view.View
import android.widget.Button
import android.widget.EditText
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import kotlinx.parcelize.Parcelize

@Parcelize
data class Contact (val name:String, val phone:String, val email:String):Parcelable

class MainActivity : ComponentActivity(), View.OnClickListener {

    private lateinit var nameField : EditText
    private lateinit var emailField : EditText
    private lateinit var phoneField : EditText


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main);
        nameField = findViewById<EditText>(R.id.nameInput);
        emailField = findViewById<EditText>(R.id.emailInput);
        phoneField = findViewById<EditText>(R.id.phoneInput);

        val sendButton = findViewById<Button>(R.id.sendButton);
        val shareTextButton = findViewById<Button>(R.id.shareTextButton);
        val sendBroadcastButton = findViewById<Button>(R.id.sendBroadcastButton);


        sendButton.setOnClickListener(this);
        shareTextButton.setOnClickListener(this);
        sendBroadcastButton.setOnClickListener(this);
    }

    override fun onClick(v: View?) {
        val contact = Contact (
            nameField.text.toString(),
            phoneField.text.toString(),
            emailField.text.toString()
        )
        when (v?.id){
            R.id.sendButton ->{
                val intent = Intent(this, SecondActivity::class.java)
                intent.putExtra("contact", contact)
                startActivity(intent);
            }

            R.id.shareTextButton ->{
                val shareIntent = Intent().apply {
                    action = Intent.ACTION_SEND
                    putExtra(Intent.EXTRA_TEXT, "Hello Humber!")
                    type = "text/plain"
                }
                startActivity(Intent.createChooser(shareIntent, "Share via"), null);
            }
            R.id.sendBroadcastButton ->{
                Log.d("SenderApp", "Broadcast is prepared")
                val intent = Intent("com.study.CONTACT_BROADCAST").apply {
                    component = ComponentName(
                        "com.study.receiver",
                        "com.study.receiver.broadcast.ContactBroadcastReceiver"
                    )

                    putExtra("name", contact.name)
                    putExtra("email", contact.email)
                    putExtra("phone", contact.phone)
                }
                sendBroadcast(intent)
                Log.d("SenderApp", "Broadcast is sent")
            }
        }
    }
}