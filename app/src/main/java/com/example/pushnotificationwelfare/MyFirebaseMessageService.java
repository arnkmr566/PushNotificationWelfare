package com.example.pushnotificationwelfare;

import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.media.RingtoneManager;
import android.net.Uri;
import android.os.Build;
import android.util.Log;

import androidx.annotation.NonNull;
import androidx.core.app.NotificationCompat;

import com.google.firebase.messaging.FirebaseMessagingService;
import com.google.firebase.messaging.RemoteMessage;

import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.Random;

public class MyFirebaseMessageService extends FirebaseMessagingService {

    private static final String TAG = "MyFirebaseMsgService";
    Bitmap bitmap;


/*
    @Override
    public void onMessageReceived(RemoteMessage remoteMessage) {
        //Displaying data in log
        //It is optional
        Log.e(TAG, "From: " + remoteMessage.getFrom());
     //   Log.e(TAG, "Notification Message Body: " + remoteMessage.getData().get("title"));
         if (remoteMessage.getData().size() > 0) {
            Log.d(TAG, "Message data payload: " + remoteMessage.getData());

        }

        // Check if message contains a notification payload.
        if (remoteMessage.getNotification() != null) {
            Log.d(TAG, "Message Notification Body: " + remoteMessage.getNotification().getBody());


        }

        //Calling method to generate notification
        sendNotification(remoteMessage.getData());
    }

    //This method is only generating push notification
    //It is same as we did in earlier posts

    private void sendNotification(Map<String, String> notification) {
        Intent intent = new Intent(this, MainActivity.class);
        intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
        PendingIntent pendingIntent = PendingIntent.getActivity(this, 0, intent,
                PendingIntent.FLAG_IMMUTABLE);

        Uri defaultSoundUri= RingtoneManager.getDefaultUri(RingtoneManager.TYPE_NOTIFICATION);
        NotificationCompat.Builder notificationBuilder = new NotificationCompat.Builder(this)
                .setSmallIcon(R.mipmap.ic_launcher)
                .setContentTitle(notification.get("title"))
                .setContentText(notification.get("body"))
                .setAutoCancel(true)
                .setSound(defaultSoundUri)
                .setContentIntent(pendingIntent);

        NotificationManager notificationManager =
                (NotificationManager) getSystemService(Context.NOTIFICATION_SERVICE);

        notificationManager.notify(0, notificationBuilder.build());
    }

 */



/*
        @Override
        public void onMessageReceived(RemoteMessage remoteMessage) {
            // TODO(developer): Handle FCM messages here.
            Map<String, String> params = remoteMessage.getData();
            if (params.size() > 0) {
                sendNotification(params.get("title"), params.get("message"));
                broadcastNewNotification();
            } else {
                sendNotification(remoteMessage.getNotification().getTitle(), remoteMessage.getNotification().getBody());
            }

        }

        @Override
        public void onNewToken(String token) {
            Log.d(TAG, "Refreshed token: " + token);
        }


        private void sendNotification(String title, String messageBody) {
            Intent intent = new Intent(this, MainActivity.class);
            intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
            intent.putExtra("Push Notification", title);
            PendingIntent pendingIntent = PendingIntent.getActivity(this, 0, intent, PendingIntent.FLAG_ONE_SHOT);
            NotificationManager notificationManager = (NotificationManager) this.getSystemService(Context.NOTIFICATION_SERVICE);
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
                NotificationChannel mChannel = new NotificationChannel(getResources().getString(R.string.app_name),
                        getResources().getString(R.string.app_name), NotificationManager.IMPORTANCE_HIGH);
                mChannel.enableLights(true);
                mChannel.setLockscreenVisibility(Notification.VISIBILITY_PUBLIC);
                notificationManager.createNotificationChannel(mChannel);
            }
            NotificationCompat.Builder notificationBuilder;
            notificationBuilder = new NotificationCompat.Builder(this, getResources().getString(R.string.app_name))
                    .setAutoCancel(true)
                    .setSmallIcon(R.drawable.ic_stat_name)
                    .setColor(getResources().getColor(R.color.black))
                    .setLargeIcon(BitmapFactory.decodeResource(this.getResources(),
                            R.drawable.ic_stat_name))
                    .setDefaults(Notification.DEFAULT_ALL)
                    .setPriority(NotificationCompat.PRIORITY_HIGH)
                    .setContentTitle(title)
                    .setContentText(messageBody)
                    .setChannelId(getResources().getString(R.string.app_name))
                    .setFullScreenIntent(pendingIntent, true);
            notificationManager.notify(1, notificationBuilder.build());
        }

        private void broadcastNewNotification() {
            Intent intent = new Intent("new_notification");
            LocalBroadcastManager.getInstance(this).sendBroadcast(intent);
        }

 */


    // [START receive_message]
    @Override
    public void onMessageReceived(RemoteMessage remoteMessage) {
        // TODO(developer): Handle FCM messages here.
        // Not getting messages here? See why this may be: https://goo.gl/39bRNJ
        Log.d(TAG, "From: " + remoteMessage.getFrom());

        // Check if message contains a data payload.
        if (remoteMessage.getData().size() > 0) {
            Log.d(TAG, "Message data payload: " + remoteMessage.getData());
         //   sendNotification(remoteMessage.getData().get("message"));


        }

        // Check if message contains a notification payload.
        if (remoteMessage.getNotification() != null) {
            Log.d(TAG, "Message Notification Body: " + remoteMessage.getNotification().getBody());
         //   remoteMessage.getNotification().getImageUrl();
        }



        //message will contain the Push Message
    //    String message = remoteMessage.getData().get("message");
   //     sendNotification(message);


        // Also if you intend on generating your own notifications as a result of a received FCM
        // message, here is where that should be initiated. See sendNotification method below.


        //Calling method to generate notification
           sendNotification( remoteMessage.getNotification().getBody());

    }

    private void sendNotification(String messageBody) {
        Intent intent = new Intent(this, MainActivity.class);
        intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
        PendingIntent pendingIntent = PendingIntent.getActivity(this, 0 /* Request code */
                , intent, PendingIntent.FLAG_ONE_SHOT);


        String channelId = "fcm_default_channel";
        Uri defaultSoundUri = RingtoneManager.getDefaultUri(RingtoneManager.TYPE_NOTIFICATION);
        NotificationCompat.Builder notificationBuilder =
                new NotificationCompat.Builder(this, channelId)
                        .setSmallIcon(R.drawable.ic_stat_notification)
                        .setContentTitle("FCM Message")
                        .setContentText(messageBody)
                        .setAutoCancel(true)
                        .setSound(defaultSoundUri)
                        .setContentIntent(pendingIntent);

        NotificationManager notificationManager =
                (NotificationManager) getSystemService(Context.NOTIFICATION_SERVICE);

        // Since android Oreo notification channel is needed.
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            NotificationChannel channel = new NotificationChannel(channelId,
                    "Channel human readable title",
                    NotificationManager.IMPORTANCE_DEFAULT);
            notificationManager.createNotificationChannel(channel);
        }

        notificationManager.notify(0 /* ID of notification */
                , notificationBuilder.build());

    }


}