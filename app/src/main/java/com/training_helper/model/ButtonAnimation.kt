package com.training_helper.model

import android.annotation.SuppressLint
import android.os.CountDownTimer
import android.util.Log
import android.view.View
import android.widget.TextView
import com.training_helper.R

class ButtonAnimation() {
    var turning: Boolean = false

    fun buttonEffect(button: View, timeGear: View, setGear: View, restTime: TextView, counter: Long) {
        var cpCounter: Long = counter
        var timeGearTurning = object : CountDownTimer(counter, 10) {
            override fun onTick(millisUntilFinished: Long) {
                timeGear.rotation += 0.3f
                if (cpCounter == 0L) {
                    this.cancel()
                }
            }

            override fun onFinish() {

            }
        }

        var setGearTurning = object : CountDownTimer(counter, 10) {
            override fun onTick(millisUntilFinished: Long) {
                setGear.rotation -= 0.45f
                if (cpCounter == 0L) {
                    this.cancel()
                }
            }

            override fun onFinish() {

            }
        }

        var setRestTime = object : CountDownTimer(cpCounter, 1000) {
            @SuppressLint("SetTextI18n")
            override fun onTick(p0: Long) {
                cpCounter -= 1000
                if (cpCounter == 0L) {
                    button.setBackgroundResource(R.drawable.play)
                    this.cancel()
                }
                restTime.text =
                    "0:${if (cpCounter / 1000 >= 10) cpCounter / 1000 else "0" + (cpCounter / 1000).toString()}"
            }

            override fun onFinish() {
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