package com.training_helper.model

import android.os.CountDownTimer
import android.view.View

class ButtonAnimation() {
    fun buttonEffect(button: View, timeGear: View, setGear: View) {
        button.setOnClickListener {
            val timer = object : CountDownTimer(300, 10) {
                override fun onTick(millisUntilFinished: Long) {
                    it.scaleX += 0.01f
                    it.scaleY += 0.01f
                }

                override fun onFinish() {
                    it.scaleY = 1f
                    it.scaleX = 1f
                }
            }
            timer.start()
        }
    }
}