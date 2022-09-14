package com.example.newsapp.helpers

import android.util.Log
import com.example.newsapp.BuildConfig

object DebugLogs {
    private const val isDebugLoggingOn = true

    fun e(TAG: String, msg: String) {
        if (isDebugLoggingOn || BuildConfig.DEBUG) {
            largeLog(TAG, msg)
        }
    }

    private fun largeLog(tag: String, content: String) {
        if (content.length > 4000) {
            Log.e(tag, content.substring(0, 4000))
            largeLog(tag, content.substring(4000))
        } else {
            Log.e(tag, content)
        }
    }
}