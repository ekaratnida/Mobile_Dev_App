package pok.test.quiz2;

import android.app.Notification;
import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.net.ConnectivityManager;
import android.os.Build;
import android.support.v4.app.NotificationCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import java.util.Timer;
import java.util.TimerTask;

public class MainActivity extends AppCompatActivity {

	private NotificationManager mNotificationManager;

	int i =0;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);

		Timer timer = new Timer();
		timer.schedule(new TimerTask()
		{
			@Override
			public void run()
			{
				ConnectivityManager manager = (ConnectivityManager) getSystemService(CONNECTIVITY_SERVICE);

				//For 3G check
				boolean is3g = manager.getNetworkInfo(ConnectivityManager.TYPE_MOBILE)
					  .isConnectedOrConnecting();
				//For WiFi Check
				boolean isWifi = manager.getNetworkInfo(ConnectivityManager.TYPE_WIFI)
					  .isConnectedOrConnecting();

				if (!is3g && !isWifi)
				{
					Log.d("wifi","off");
				}
				else
				{
					Log.d("wifi","on");
				}

				NotificationCompat.Builder mBuilder =
					  new NotificationCompat.Builder(getApplicationContext(), "notify_001");
				Intent ii = new Intent(getApplicationContext(), MainActivity.class);
				PendingIntent pendingIntent = PendingIntent.getActivity(getApplicationContext(), 0, ii, 0);

				NotificationCompat.BigTextStyle bigText = new NotificationCompat.BigTextStyle();
				bigText.bigText("TEST"+i);
				++i;
				bigText.setBigContentTitle("Today's Bible Verse");
				bigText.setSummaryText("Text in detail");

				mBuilder.setContentIntent(pendingIntent);
				mBuilder.setSmallIcon(R.mipmap.ic_launcher_round);
				mBuilder.setContentTitle("Your Title");
				mBuilder.setContentText("Your text");
				mBuilder.setPriority(Notification.BADGE_ICON_LARGE);
				mBuilder.setStyle(bigText);

				mNotificationManager =
					  (NotificationManager) getSystemService(Context.NOTIFICATION_SERVICE);

				if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
					String channelId = "YOUR_CHANNEL_ID";
					NotificationChannel channel = new NotificationChannel(channelId,
						  "Channel human readable title",
						  NotificationManager.IMPORTANCE_DEFAULT);
					mNotificationManager.createNotificationChannel(channel);
					mBuilder.setChannelId(channelId);
				}

				mNotificationManager.notify(0, mBuilder.build());
			}
		}, 0, 3000);


	}
}
