package com.training_helper.model

import android.os.CountDownTimer
import android.view.View
import android.widget.TextView
import com.training_helper.R
import kotlinx.coroutines.*

class ButtonAnimation() {
    var turning: Boolean = false

    fun buttonEffect(
        button: View,
        resetButton: View,
        timeGear: View,
        setGear: View,
        restTime: TextView,
        counter: Long
    ) {
        var cpCounter: Long = counter
        val timeGearTurning = object : CountDownTimer(counter, 10) {
            override fun onTick(millisUntilFinished: Long) {
                timeGear.rotation += 0.3f
            }

            override fun onFinish() {

            }
        }

        val setGearTurning = object : CountDownTimer(counter, 10) {
            override fun onTick(millisUntilFinished: Long) {
                setGear.rotation -= 0.45f
            }

            override fun onFinish() {

            }
        }

        val setRestTime = object : CountDownTimer(cpCounter, 1000) {
            override fun onTick(p0: Long) {
                cpCounter -= 1000

                restTime.text = buildString {
                    append("0:")
                    append(if (cpCounter / 1000 >= 10) cpCounter / 1000 else "0" + (cpCounter / 1000).toString())
                }

                if (cpCounter == 0L) {
                    this.onFinish()
                }
            }

            override fun onFinish() {
                button.setBackgroundResource(R.drawable.play)
                setGearTurning.onFinish()
                timeGearTurning.onFinish()
                cpCounter = counter
                turning = false

                restTime.text = buildString {
                    append("0:")
                    append(cpCounter / 1000)
                }
            }
        }


        button.setOnClickListener {
            turning = !turning

            val playButtonReact: Job = buttonAnimate(button)
            playButtonReact.start()

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

        resetButton.setOnClickListener {
            val resetButtonReact: Job = buttonAnimate(resetButton)
            resetButtonReact.start()
            cpCounter = counter
            restTime.text = buildString {
                append("0:")
                append(cpCounter / 1000)
            }
        }
    }

    fun buttonAnimate(button: View): Job {
        val job: Job = GlobalScope.launch(Dispatchers.Main) {
            for (i in 0 until 20) {
                button.scaleX += 0.01f
                button.scaleY += 0.01f
                delay(10)
            }
            button.scaleX = 1f
            button.scaleY = 1f
        }
        return job
    }
}