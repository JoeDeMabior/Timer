package com.joe.timer

import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import com.joe.timer.utils.PreferenceUtility

class TimerExpiredReceiver : BroadcastReceiver() {

    override fun onReceive(context: Context, intent: Intent) {
        // TODO: Show notification
        PreferenceUtility.setTimerState(MainActivity.TimerState.Stopped, context)
        PreferenceUtility.setAlarmSetTime(0, context)
    }
}
