package micromakers.com.flightsearch

import android.util.Log
import com.google.firebase.messaging.RemoteMessage
import com.google.firebase.messaging.FirebaseMessagingService


class FirebaseMessagingService : FirebaseMessagingService() {
    override fun onMessageReceived(remoteMessage: RemoteMessage?) {
        Log.d(TAG, "From: " + remoteMessage!!.from)
        Log.d(TAG, "Notification Message Body: " + remoteMessage.notification.body!!)
    }

    companion object {
        private val TAG = "FCM Service"
    }
}