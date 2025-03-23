package com.study.receiver.broadcast

import android.Manifest
import android.app.NotificationChannel
import android.app.NotificationManager
import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import android.os.Build
import android.util.Log
import com.study.receiver.Contact
import com.study.receiver.MainActivity

class ContactBroadcastReceiver : BroadcastReceiver() {
    override fun onReceive(context: Context?, intent: Intent?) {
        val name = intent?.getStringExtra("name")!!
        val email = intent.getStringExtra("email")!!
        val phone = intent.getStringExtra("phone")!!
        val contact = Contact(name, email, phone)
        Log.d("Broadcast Receiver", "Name: ${contact?.name}\nPhone: ${contact?.phone}\nEmail: ${contact?.email}")
    }

}