package com.brightskiesinc.mangm

import android.content.Context
import android.content.Intent
import com.brightskiesinc.mangm.presentation.main.MainActivity

object Mangm {
        fun startSession(context: Context) {
            val intent = Intent(context, MainActivity::class.java)
            context.startActivity(intent)
        }
}