package com.joe.timer.receivers

import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import com.joe.timer.constants.AppConstants
import com.joe.timer.ui.MainActivity
import com.joe.timer.utils.NotificationUtil
import com.joe.timer.utils.PreferenceUtil

class TimerNotificationActionReceiver : BroadcastReceiver() {

    override fun onReceive(context: Context, intent: Intent) {
        when (intent.action) {
            AppConstants.ACTION_STOP -> {
                MainActivity.removeAlarm(context)
                PreferenceUtil.setTimerState(MainActivity.TimerState.Stopped, context)
                NotificationUtil.hideTimerNotification(context)
            }
            AppConstants.ACTION_PAUSE -> {
                var secondsRemaining = PreferenceUtil.getSecondsRemaining(context)
                val alarmSetTime = PreferenceUtil.getAlarmSetTime(context)
                val nowSeconds = MainActivity.nowSeconds

                secondsRemaining -= nowSeconds - alarmSetTime
                PreferenceUtil.setSecondsRemaining(secondsRemaining, context)

                MainActivity.removeAlarm(context)
                PreferenceUtil.setTimerState(MainActivity.TimerState.Paused, context)
                NotificationUtil.showTimerPaused(context)
            }
            AppConstants.ACTION_RESUME -> {
                val secondsRemaining = PreferenceUtil.getSecondsRemaining(context)
                val wakeUpTime = MainActivity.setAlarm(context, MainActivity.nowSeconds, secondsRemaining)
                PreferenceUtil.setTimerState(MainActivity.TimerState.Running, context)
                NotificationUtil.showTimerRunning(context, wakeUpTime)
            }
            AppConstants.ACTION_START -> {
                val minutesRemaining = PreferenceUtil.getTimerLength(context)
                val secondsRemaining = minutesRemaining * 60L
                val wakeUpTime = MainActivity.setAlarm(context, MainActivity.nowSeconds, secondsRemaining)
                PreferenceUtil.setTimerState(MainActivity.TimerState.Running, context)
                PreferenceUtil.setSecondsRemaining(secondsRemaining, context)
                NotificationUtil.showTimerRunning(context, wakeUpTime)
            }
        }
    }
}
