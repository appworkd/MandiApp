package com.appwork.mandiapp

import android.content.Intent
import android.os.Bundle
import android.os.Handler
import android.view.View
import android.view.animation.Animation
import android.view.animation.AnimationUtils
import androidx.appcompat.app.AppCompatActivity
import kotlinx.android.synthetic.main.activity_splash.*

class SplashActivity : AppCompatActivity() {
    private lateinit var animTopBottom: Animation
    private lateinit var animLeftRight: Animation
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_splash)
        animTopBottom = AnimationUtils.loadAnimation(this, R.anim.top_animator)
        animLeftRight = AnimationUtils.loadAnimation(this, R.anim.left_to_right_animator)
        tvSplashHead.animation = animTopBottom

        Handler().postDelayed(Runnable {
            tvSplashSubHead.animation = animLeftRight
            tvSplashSubHead.visibility = View.VISIBLE
            val intent = Intent(this, WeightActivity::class.java)
            startActivity(intent)
        }, 1000)
    }
}