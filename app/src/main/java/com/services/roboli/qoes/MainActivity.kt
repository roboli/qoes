package com.services.roboli.qoes

import android.animation.Animator
import android.animation.AnimatorSet
import android.animation.ObjectAnimator
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.support.constraint.ConstraintLayout
import android.support.v4.view.animation.FastOutSlowInInterpolator
import android.view.View
import android.widget.ImageView
import android.widget.TextView
import kotlinx.coroutines.*
import kotlin.coroutines.CoroutineContext

class MainActivity : AppCompatActivity(), CoroutineScope {
    val DURATION_SLOW: Long = 700
    val DURATION_FAST: Long = 300
    val IN_START_POS = 580f
    val OUT_END_POS = -650f
    val MIDDLE_POS = 20f

    var opLogo: ImageView? = null
    var logoSet: AnimatorSet? = null
    var animatorStart: ObjectAnimator? = null
    var animatorEnd: ObjectAnimator? = null
    var textView: TextView? = null

    var currentOp = UNKNOWN
    var phoneNumber = ""

    lateinit var mJob: Job
    override val coroutineContext: CoroutineContext
        get() = mJob + Dispatchers.Main

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        mJob = Job()

        opLogo = findViewById<ImageView>(R.id.op_logo)
        textView = findViewById<TextView>(R.id.phone)

        animatorStart = ObjectAnimator.ofFloat(opLogo, "x", IN_START_POS, MIDDLE_POS)
        animatorStart?.interpolator = FastOutSlowInInterpolator()
        animatorStart?.setDuration(DURATION_SLOW)

        animatorEnd = ObjectAnimator.ofFloat(opLogo, "x", MIDDLE_POS, OUT_END_POS)
        animatorEnd?.interpolator = FastOutSlowInInterpolator()
        animatorEnd?.setDuration(DURATION_FAST)
        animatorEnd?.addListener(object : Animator.AnimatorListener {
            override fun onAnimationStart(animation: Animator?) {}
            override fun onAnimationCancel(animation: Animator?) {}
            override fun onAnimationRepeat(animation: Animator?) {}
            override fun onAnimationEnd(animation: Animator?) {
                when (currentOp) {
                    CLARO    -> opLogo?.setImageResource(R.mipmap.ic_claro)
                    MOVISTAR -> opLogo?.setImageResource(R.mipmap.ic_movistar)
                    TIGO     -> opLogo?.setImageResource(R.mipmap.ic_tigo)
                    else     -> opLogo?.setImageResource(R.mipmap.ic_question)
                }
            }
        })

        logoSet = AnimatorSet()
        logoSet?.play(animatorStart)?.after(animatorEnd)

        connectButton(findViewById(R.id.btn_1))
        connectButton(findViewById(R.id.btn_2))
        connectButton(findViewById(R.id.btn_3))
        connectButton(findViewById(R.id.btn_4))
        connectButton(findViewById(R.id.btn_5))
        connectButton(findViewById(R.id.btn_6))
        connectButton(findViewById(R.id.btn_7))
        connectButton(findViewById(R.id.btn_8))
        connectButton(findViewById(R.id.btn_9))
        connectButton(findViewById(R.id.btn_0))
        connectButton(findViewById(R.id.btn_bksp))
    }

    fun connectButton(btn: View) {
        btn.setOnClickListener { v -> btnClicked(v) }
    }

    fun btnClicked(view: View) {
        when (view.id) {
            R.id.btn_1 ->  phoneNumber += "1"
            R.id.btn_2 ->  phoneNumber += "2"
            R.id.btn_3 ->  phoneNumber += "3"
            R.id.btn_4 ->  phoneNumber += "4"
            R.id.btn_5 ->  phoneNumber += "5"
            R.id.btn_6 ->  phoneNumber += "6"
            R.id.btn_7 ->  phoneNumber += "7"
            R.id.btn_8 ->  phoneNumber += "8"
            R.id.btn_9 ->  phoneNumber += "9"
            R.id.btn_bksp -> phoneNumber = phoneNumber.dropLast(1)
            else -> phoneNumber += "0"
        }

        textView?.setText(phoneNumber)
        launch {
            startAnimation()
        }
    }

    var waiting = false

    suspend fun startAnimation() {
        if (waiting) return

        if (logoSet?.isRunning == true) {
            waiting = true
            delay(DURATION_FAST + DURATION_SLOW)
        }

        waiting = false
        var newPhoneTxt: String = textView?.text.toString()

        if (newPhoneTxt.length >= 4 && newPhoneTxt.length <= 8) {
            val op = identifyOp(newPhoneTxt.take(4).toInt())

            if (currentOp != op) {
                logoSet?.start()
            }

            currentOp = op
        } else if (newPhoneTxt.length < 4 || newPhoneTxt.length > 8) {
            if (currentOp != UNKNOWN) {
                logoSet?.start()
            }

            currentOp = UNKNOWN
        }
    }
}
