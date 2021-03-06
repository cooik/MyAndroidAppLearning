package com.example.myserviceapp;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.media.AudioManager;
import android.widget.Toast;

public class ToHourAlarmReceiver extends BroadcastReceiver {

	@Override
	public void onReceive(Context context, Intent intent) {
		// TODO Auto-generated method stub
		AudioManager am = (AudioManager) context
				.getSystemService(Context.AUDIO_SERVICE);
		am.setRingerMode(AudioManager.RINGER_MODE_NORMAL);
		Toast.makeText(context, "Phone Mode is Changed to Normal Mode",
				Toast.LENGTH_LONG).show();
	}

}
