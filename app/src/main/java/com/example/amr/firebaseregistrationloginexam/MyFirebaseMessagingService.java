package com.example.amr.firebaseregistrationloginexam;

import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.support.v4.app.NotificationCompat;

import com.google.firebase.messaging.FirebaseMessagingService;
import com.google.firebase.messaging.RemoteMessage;

/**
 * Created by NgocTri on 8/9/2016.
 */
public class MyFirebaseMessagingService extends FirebaseMessagingService {

    @Override
    public void onMessageReceived(RemoteMessage remoteMessage) {
        String s = remoteMessage.getNotification().getBody();
        Intent intent = new Intent(this, NotificationShow.class);
//        Bundle bundle = new Bundle();
//        bundle.putString("p",s);
//        intent.putExtras(bundle);
        intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
        PendingIntent pendingIntent = PendingIntent.getActivity(this, 0, intent, PendingIntent.FLAG_ONE_SHOT);

        NotificationCompat.Builder notifiBuilder = new NotificationCompat.Builder(this);
        notifiBuilder.setContentTitle("COMPASS_17");
        notifiBuilder.setContentText(s);
        notifiBuilder.setAutoCancel(true);
        notifiBuilder.setSmallIcon(R.mipmap.ic_launcher);
//        notifiBuilder.setSound(notificationSound);
        notifiBuilder.setContentIntent(pendingIntent);

        NotificationManager notificationManager = (NotificationManager) getSystemService(Context.NOTIFICATION_SERVICE);
        notificationManager.notify(0, notifiBuilder.build());

    }

}
