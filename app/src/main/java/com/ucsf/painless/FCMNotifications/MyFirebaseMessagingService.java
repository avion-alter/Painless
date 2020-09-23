package com.ucsf.painless.FCMNotifications;

import android.app.Notification;
import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.media.MediaPlayer;
import android.os.Build;
import android.util.Log;
import android.view.View;
import android.widget.RemoteViews;
import android.widget.Toast;

import androidx.core.app.NotificationCompat;

import com.google.firebase.messaging.FirebaseMessagingService;
import com.google.firebase.messaging.RemoteMessage;
import com.ucsf.painless.R;
import com.ucsf.painless.view.DashboardActivity;
import com.ucsf.painless.view.MonthlyRandomActivity;
import com.ucsf.painless.view.PainIntensityRatingActivity;

import org.json.JSONObject;

import java.text.SimpleDateFormat;
import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.TimeZone;
import java.util.Timer;
import java.util.TimerTask;
import java.util.concurrent.atomic.AtomicInteger;


import static java.lang.Integer.valueOf;
import static java.util.concurrent.TimeUnit.MILLISECONDS;
import static java.util.concurrent.TimeUnit.MINUTES;


public class MyFirebaseMessagingService extends FirebaseMessagingService {


    static int NOTIFICATION_ID;
    static NotificationManager notificationManager;
    @Override
    public void onNewToken(String s) {
        super.onNewToken(s);
        String TOKEN = s;
    }
    @Override
    public void onMessageReceived(RemoteMessage remoteMessage) {
        if (remoteMessage.getData().size() > 0) {
            Log.d("notification", "Message data payload: " + remoteMessage.getData());
            notificationManager =(NotificationManager) getSystemService(Context.NOTIFICATION_SERVICE);

            Intent intent = new Intent();
            if (remoteMessage.getData().get("reminder_type").equals("Daily")){
                 intent = new Intent(this, PainIntensityRatingActivity.class);
            }else if (remoteMessage.getData().get("reminder_type").equals("Monthly")){
                 intent = new Intent(this, MonthlyRandomActivity.class);
            }

            intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
            PendingIntent pendingIntent = PendingIntent.getActivity(this, 0, intent, 0);
            final NotificationCompat.Builder notification = new NotificationCompat.Builder(MyFirebaseMessagingService.this, getString(R.string.app_name))
                    .setChannelId(getString(R.string.app_name))
                    .setTicker(getString(R.string.app_name))
                    .setContentTitle("Reminders")
                    .setStyle(new NotificationCompat.BigTextStyle().bigText(remoteMessage.getData().get("Message")))
                    .setSmallIcon(R.mipmap.ic_launcher)
                    .setPriority(Notification.FLAG_ONGOING_EVENT)
                    .setOngoing(true)
                    .setAutoCancel(true)
                    .setContentIntent(pendingIntent)
                    .setVisibility(NotificationCompat.VISIBILITY_PUBLIC)
                    .setVibrate(new long[]{1000, 1000, 1000, 1000, 1000, 1000, 1000, 1000, 1000, 1000, 1000, 1000, 1000, 1000, 1000, 1000, 1000, 1000, 1000, 1000, 1000, 1000, 1000, 1000, 1000, 1000, 1000, 1000, 1000, 1000});
            notificationManager.notify(NOTIFICATION_ID, notification.build());

        }/*{
            Log.d("notification", "Message data payload: " + remoteMessage.getData());
            callAcceptOrReject = false;
            String notificationData = remoteMessage.getData().toString();
            sessionID = remoteMessage.getData().get("sessionId");
            tokenID = remoteMessage.getData().get("tokenId");
            senderName = remoteMessage.getData().get("sender");
            meetingId = remoteMessage.getData().get("meeting_id");

            callType = remoteMessage.getData().get("call_type");
            pat_name = remoteMessage.getData().get("pat_name");
            appt_id = remoteMessage.getData().get("appt_id");


            chatId = remoteMessage.getData().get("id");
            chatName = remoteMessage.getData().get("name");
            chatImage = remoteMessage.getData().get("image");
            chatMessage = remoteMessage.getData().get("message");
            chatMessage = remoteMessage.getData().get("message");

            patientName = remoteMessage.getData().get("patient_name");
            doctorName = remoteMessage.getData().get("doctor_name");
            doctorDegree = remoteMessage.getData().get("doctor_degree");


            try {
                mNoti_id = remoteMessage.getData().get("noti_id");
            }catch (Exception e){
                e.printStackTrace();
            }

            try {
                calling_category = remoteMessage.getData().get("calling_category");
            }catch (Exception e){
                e.printStackTrace();
            }

            try {
                if (chatMessage.equals(null)){
                    chatMessage = remoteMessage.getData().get("Message");
                }
            }catch (Exception e){
                chatMessage = remoteMessage.getData().get("Message");
                e.printStackTrace();
            }

            doctor_chat = remoteMessage.getData().get("chat_type");

            current_address = remoteMessage.getData().get("location");
            home_address = remoteMessage.getData().get("home_address");
            patient_phone = remoteMessage.getData().get("patient_phone");
            patient_image = remoteMessage.getData().get("patient_image");

            profile_image = remoteMessage.getData().get("profile_image");

            try {
                date_time = remoteMessage.getData().get("date_time");
            }catch (Exception e){
                e.printStackTrace();
            }
            try {
                notification_type = remoteMessage.getData().get("notification_type");
                if (notification_type != null) {
                    chatMessage = remoteMessage.getData().get("message");
                }
            } catch (Exception e) {
                notification_type = "";
                e.printStackTrace();
            }

            try {
                if (chatMessage.equals(null)){
                    chatMessage = remoteMessage.getData().get("Message");
                }
            }catch (Exception e){
                chatMessage = remoteMessage.getData().get("Message");
                e.printStackTrace();
            }

            try {
                if (notification_type.equalsIgnoreCase("call_to_doctor")) {
                    senderName = remoteMessage.getData().get("doctor_name");
                    callie_name = remoteMessage.getData().get("doctor_name");
                    pat_name = remoteMessage.getData().get("doctor_name");
                    receiver_email = remoteMessage.getData().get("receiver_email");
                    sender_email = remoteMessage.getData().get("sender");
                    profile_image = remoteMessage.getData().get("profile_image");
                }
            } catch (Exception e) {
                notification_type = "";
                e.printStackTrace();
            }

            *//*THIS IS TIME CALCULATION FOR ON TIME NOTIFICATIONS. We have taken America/New_York current time*//*
            Date server_date = null;
            Date current_date = null;

            //GetTime
            SimpleDateFormat etDf = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");
            TimeZone etTimeZone = TimeZone.getTimeZone("America/New_York");
            etDf.setTimeZone( etTimeZone );

            Date currentDate = new Date();
            Calendar currentTime = Calendar.getInstance();

            //In ET Time
            try{
                System.out.println("TEST" +etDf.format( currentDate.getTime()));
                System.out.println("TEST" +etDf.format( currentTime.getTimeInMillis()));
            }catch (Exception e){
                e.printStackTrace();
            }

            ZoneId denverTimeZone = null;
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
                denverTimeZone = ZoneId.of( "America/New_York" );
                ZonedDateTime zdt = ZonedDateTime.now( denverTimeZone );
                String output2 = zdt.toLocalTime().toString();
                System.out.println( "Current time in " + denverTimeZone + ": " + output2 );
            }

            Calendar calNewYork = Calendar.getInstance();
            calNewYork.setTimeZone(TimeZone.getTimeZone("America/New_York"));
            System.out.println();
            System.out.println("Time in New York: " + calNewYork.get(Calendar.HOUR_OF_DAY) + ":"
                    + calNewYork.get(Calendar.MINUTE)+":"+calNewYork.get(Calendar.SECOND));
            System.out.println();

            try{
                SimpleDateFormat df2 = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");
                server_date = df2.parse(date_time);
                current_date = df2.parse(etDf.format( currentDate.getTime()));
                System.out.println(df2.format(server_date));
            } catch (Exception e) {
                e.printStackTrace();
            }

            long MAX_DURATION = MILLISECONDS.convert(1, MINUTES);

            long duration = current_date.getTime() - server_date.getTime();

            if (duration >= MAX_DURATION) {
                Log.e("TESTNOTIFICATION","OutTime");
                try {
                    NOTIFICATION_ID = NotificationID.getID();
                    notificationManager =
                            (NotificationManager) getSystemService(Context.NOTIFICATION_SERVICE);
                    if (chatMessage.equalsIgnoreCase("")) {
                        Intent intent = new Intent(this, DashboardActivity.class);
                        intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
                        PendingIntent pendingIntent = PendingIntent.getActivity(this, 0, intent, 0);
                        final NotificationCompat.Builder notification = new NotificationCompat.Builder(MyFirebaseMessagingService.this, getString(R.string.app_name))
                                .setChannelId(getString(R.string.app_name))
                                .setTicker(getString(R.string.app_name))
                                .setContentTitle("Missed Call")
                                //.setContentText("You have the  missed call from the " + pat_name)
                                //.setContentText("RxOnDemand Missed Consultation Request From " + pat_name)
                                //.setStyle(new NotificationCompat.BigTextStyle().bigText("RxOnDemand Missed Consultation Request From " + pat_name))
                                .setStyle(new NotificationCompat.BigTextStyle().bigText("You have a missed call from " + pat_name))
                                .setSmallIcon(R.mipmap.ic_launcher)
                                .setPriority(Notification.FLAG_ONGOING_EVENT)
                                .setAutoCancel(true)
                                .setOngoing(true)
                                .setContentIntent(pendingIntent)
                                .setVisibility(NotificationCompat.VISIBILITY_PUBLIC)
                                .setVibrate(new long[]{1000, 1000, 1000, 1000, 1000, 1000, 1000, 1000, 1000, 1000, 1000, 1000, 1000, 1000, 1000, 1000, 1000, 1000, 1000, 1000, 1000, 1000, 1000, 1000, 1000, 1000, 1000, 1000, 1000, 1000});
                        notificationManager.notify(NOTIFICATION_ID, notification.build());
                    }
                }catch (Exception e) {
                    e.printStackTrace();
                    if (!callAcceptOrReject) {
                        Intent intent = new Intent(this, DashboardActivity.class);
                        intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
                        PendingIntent pendingIntent = PendingIntent.getActivity(this, 0, intent, 0);
                        final NotificationCompat.Builder notification = new NotificationCompat.Builder(MyFirebaseMessagingService.this, getString(R.string.app_name))
                                .setChannelId(getString(R.string.app_name))
                                .setTicker(getString(R.string.app_name))
                                .setContentTitle("Missed Call")
                                //.setContentText("You have the  missed call from the " + pat_name)
                                //.setContentText("RxOnDemand Missed Consultation Request From " + pat_name)
                                //.setStyle(new NotificationCompat.BigTextStyle().bigText("RxOnDemand Missed Consultation Request From " + pat_name))
                                .setStyle(new NotificationCompat.BigTextStyle().bigText("You have a missed call from " + pat_name))
                                .setSmallIcon(R.mipmap.ic_launcher)
                                .setPriority(Notification.FLAG_ONGOING_EVENT)
                                .setAutoCancel(true)
                                .setOngoing(true)
                                .setContentIntent(pendingIntent)
                                .setVisibility(NotificationCompat.VISIBILITY_PUBLIC)
                                .setVibrate(new long[]{1000, 1000, 1000, 1000, 1000, 1000, 1000, 1000, 1000, 1000, 1000, 1000, 1000, 1000, 1000, 1000, 1000, 1000, 1000, 1000, 1000, 1000, 1000, 1000, 1000, 1000, 1000, 1000, 1000, 1000});
                        notificationManager.notify(NOTIFICATION_ID, notification.build());
                    }
                }
            }

            // imp >>>---->>>
        }*/

    }
    @Override
    public void onDestroy() {
        super.onDestroy();
    }
}













