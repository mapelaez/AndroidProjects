package com.example.paneladmin.contenidoExtras.notificaciones;

import android.app.Activity;
import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import androidx.core.app.NotificationCompat;
import androidx.core.app.NotificationManagerCompat;

import com.example.paneladmin.R;

public class Notificaciones extends Activity {
    private static final String CHANNEL_ID = "Canal 2";


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.notificacion);
        NotificationChannel channel = new NotificationChannel(CHANNEL_ID,"Mi Notificación", NotificationManager.IMPORTANCE_DEFAULT);
        NotificationManager manager = getSystemService(NotificationManager.class);
        manager.createNotificationChannel(channel);

        Button buttonNotification = (Button) findViewById(R.id.button1);
        buttonNotification.setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {

                // Create an explicit intent for an Activity in your app
                Intent intent = new Intent(Notificaciones.this, Notificaciones.class);
                intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
                PendingIntent pendingIntent = PendingIntent.getActivity(Notificaciones.this, 0, intent, 0);

                NotificationCompat.Builder builder = new NotificationCompat.Builder(Notificaciones.this, CHANNEL_ID)
                        .setSmallIcon(android.R.drawable.star_on)
                        .setContentTitle("Mi notificacion")
                        .setContentText("Esta es una prueba")
                        .setPriority(NotificationCompat.PRIORITY_DEFAULT)
                        // Set the intent that will fire when the user taps the notification
                        .setContentIntent(pendingIntent)
                        .setAutoCancel(true);

                NotificationManagerCompat notificationManager = NotificationManagerCompat.from(Notificaciones.this);

                // notificationId is a unique int for each notification that you must define
                int notificationId = 1;
                notificationManager.notify(notificationId, builder.build());
            }
        });
    }

}
