package com.training_helper.model

import android.os.CountDownTimer
import android.util.Log
import android.view.View
import android.widget.TextView
import com.training_helper.R

class ButtonAnimation() {
    var turning: Boolean = false


    fun buttonEffect(button: View, timeGear: View, setGear: View, restTime: TextView) {
        var timeGearTurning = object : CountDownTimer(60000, 10) {
            override fun onTick(millisUntilFinished: Long) {
                timeGear.rotation += 0.3f
            }

            override fun onFinish() {

            }
        }

        var setGearTurning = object : CountDownTimer(60000, 10) {
            override fun onTick(millisUntilFinished: Long) {
                setGear.rotation -= 0.45f
            }

            override fun onFinish() {

            }
        }

        var setRestTime = object : CountDownTimer(60000, 1000) {
            override fun onTick(p0: Long) {
                restTime.text = "0:${(p0/1000).toString()}"
            }

            override fun onFinish() {
                button.setBackgroundResource(R.drawable.play)
            }
        }


        button.setOnClickListener {
            turning = !turning
            val timer = object : CountDownTimer(200, 10) {
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

            if (turning) {
                it.setBackgroundResource(R.drawable.pause)
                timeGearTurning.start()
                setGearTurning.start()
                setRestTime.start()
            } else {
                it.setBackgroundResource(R.drawable.play)
                timeGearTurning.cancel()
                setGearTurning.cancel()
                setRestTime.cancel()
            }
        }
    }
}