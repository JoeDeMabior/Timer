package com.joe.timer.receivers

import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import com.joe.timer.ui.MainActivity
import com.joe.timer.utils.NotificationUtil
import com.joe.timer.utils.PreferenceUtil

class TimerExpiredReceiver : BroadcastReceiver() {

    override fun onReceive(context: Context, intent: Intent) {
        NotificationUtil.showTimerExpired(context)

        PreferenceUtil.setTimerState(MainActivity.TimerState.Stopped, context)
        PreferenceUtil.setAlarmSetTime(0, context)
    }
}
