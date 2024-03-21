package com.example.easyshop.service

import android.app.NotificationChannel
import android.app.NotificationManager
import android.app.PendingIntent
import android.app.PendingIntent.FLAG_IMMUTABLE
import android.content.Intent
import android.content.Intent.FLAG_ACTIVITY_CLEAR_TOP
import android.os.Build
import androidx.compose.material.ExperimentalMaterialApi
import androidx.core.app.NotificationCompat
import com.example.easyshop.MainActivity
import com.example.easyshop.R
import com.google.firebase.messaging.FirebaseMessagingService
import com.google.firebase.messaging.RemoteMessage

class FirebaseNotificationsService : FirebaseMessagingService (){


    override fun onMessageReceived(remoteMessage: RemoteMessage) {
        remoteMessage.notification?.let { message ->
            println()
            sendNotification(message)
//            showInAppMessage(message)
        }
    }

    @OptIn(ExperimentalMaterialApi::class)
    private fun sendNotification(message: RemoteMessage.Notification) {
        val intent = Intent(this, MainActivity::class.java).apply {
            addFlags(FLAG_ACTIVITY_CLEAR_TOP)
        }

        val pendingIntent = PendingIntent.getActivity(
            this, 0, intent, FLAG_IMMUTABLE
        )

//        val channelId = this.getString(R.string.default_notification_channel_id)
        val channelId = "EasyShop Demo"

        val notificationBuilder =
            NotificationCompat.Builder(this, channelId).setContentTitle(message.title)
                .setContentText(message.body).setSmallIcon(R.mipmap.ic_launcher).setAutoCancel(true)
                .setContentIntent(pendingIntent)

        val manager = getSystemService(NOTIFICATION_SERVICE) as NotificationManager

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
//            val channel = NotificationChannel(channelId, CHANNEL_NAME, IMPORTANCE_DEFAULT)
            val channel = NotificationChannel(channelId, "CHANNEL_NAME",NotificationManager.IMPORTANCE_HIGH)
            manager.createNotificationChannel(channel)
        }
        val notificationId = System.currentTimeMillis().toInt()
        manager.notify(notificationId,notificationBuilder.build())
//        manager.notify(random.nextInt(), notificationBuilder.build())
    }
    private fun showInAppMessage(message: RemoteMessage.Notification) {
        // Broadcast the received message to be handled by UI components
        val intent = Intent("com.example.easyshop.MESSAGE_RECEIVED")
        intent.putExtra("title", message.title)
        intent.putExtra("body", message.body)
        sendBroadcast(intent)
    }

//    @OptIn(DelicateCoroutinesApi::class)
//    override fun onNewToken(token: String) {
//        Log.d("Push notification", "Token Refreshed ${token}")
//
//        GlobalScope.launch {
//            NotificationManager.registerTokenOnServer(token)
//        }
//
//    }


}