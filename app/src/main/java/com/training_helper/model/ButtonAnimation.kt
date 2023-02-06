package com.training_helper.model

import android.os.CountDownTimer
import android.view.View
import com.training_helper.R

class ButtonAnimation() {
    var turning: Boolean = false


    fun buttonEffect(button: View, timeGear: View, setGear: View) {
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


        button.setOnClickListener {
            turning = !turning
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

            if(turning) {
                it.setBackgroundResource(R.drawable.pause)
                timeGearTurning.start()
                setGearTurning.start()
            } else {
                it.setBackgroundResource(R.drawable.play)
                timeGearTurning.cancel()
                setGearTurning.cancel()
            }
        }
    }
}