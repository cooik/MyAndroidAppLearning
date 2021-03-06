package com.example.myserviceapp;

import java.util.Calendar;

import android.app.Activity;
import android.app.AlarmManager;
import android.app.PendingIntent;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class MainActivity extends Activity {
	private EditText editText1;
	private EditText editText2;
	private Button btn1;
	private int hourFrom;
	private int hourTo;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);

		editText1 = (EditText) findViewById(R.id.editText1);
		editText2 = (EditText) findViewById(R.id.editText2);

		btn1 = (Button) findViewById(R.id.btn1);

		Intent intent1 = new Intent(getBaseContext(),
				FromHourAlarmReceiver.class);
		final PendingIntent sender1 = PendingIntent.getBroadcast(this, 192837,
				intent1, PendingIntent.FLAG_UPDATE_CURRENT);

		Intent intent2 = new Intent(getBaseContext(), ToHourAlarmReceiver.class);
		final PendingIntent sender2 = PendingIntent.getBroadcast(this, 192837,
				intent2, PendingIntent.FLAG_UPDATE_CURRENT);

		btn1.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				try {
					hourFrom = Integer.parseInt(editText1.getText().toString());
					hourTo = Integer.parseInt(editText2.getText().toString());
				} catch (Exception e) {
				}
				if ((0 < hourFrom && hourFrom < 24)
						&& (0 < hourTo && hourFrom < 24)) {
					Calendar call1 = Calendar.getInstance();
					call1.set(Calendar.HOUR, hourFrom);

					Calendar call2 = Calendar.getInstance();
					call2.set(Calendar.HOUR, hourTo);

					AlarmManager am = (AlarmManager) getSystemService(ALARM_SERVICE);
					am.set(AlarmManager.RTC_WAKEUP, call1.getTimeInMillis(),
							sender1);
					am.set(AlarmManager.RTC_WAKEUP, call2.getTimeInMillis(),
							sender2);
					Intent myServiceIntent = new Intent(getBaseContext(),
							MyService.class);
					getBaseContext().startService(myServiceIntent);
					Toast.makeText(getBaseContext(),
							"Phone Mode Will Be changed Autimatically!",
							Toast.LENGTH_LONG).show();

				} else {
					Toast.makeText(getBaseContext(),
							"Please enter hour in between 1-23",
							Toast.LENGTH_LONG).show();
				}
			}

		});
	}
}
