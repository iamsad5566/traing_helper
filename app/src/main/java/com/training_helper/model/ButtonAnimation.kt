package com.training_helper.model

import android.annotation.SuppressLint
import android.content.Context
import android.os.CountDownTimer
import android.util.Log
import android.view.MotionEvent
import android.view.View

class ButtonAnimation() {

    @SuppressLint("ClickableViewAccessibility")
    fun buttonEffect(button: View, timeGear: View, setGear: View) {
        button.setOnTouchListener { v, event ->
            when (event.action) {
                MotionEvent.ACTION_DOWN -> {

                    val timer = object : CountDownTimer(300, 10) {
                        override fun onTick(millisUntilFinished: Long) {
                            v.scaleX += 0.01f
                            v.scaleY += 0.01f
                        }

                        override fun onFinish() {
                        }
                    }
                    timer.start()

                    v.invalidate()
                }
                MotionEvent.ACTION_UP -> {
                    v.scaleY = 1f
                    v.scaleX = 1f
                    v.invalidate()
                }
            }
            false
        }
    }
}