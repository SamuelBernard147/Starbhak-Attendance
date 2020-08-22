package com.samuelbernard.starbhakattendance.activity

import android.content.Intent
import android.os.Bundle
import android.os.Handler
import androidx.appcompat.app.AppCompatActivity
import com.samuelbernard.starbhakattendance.R
import com.samuelbernard.starbhakattendance.helper.AnimationHelper
import com.samuelbernard.starbhakattendance.pref.LoginPref
import kotlinx.android.synthetic.main.activity_splash.*

class SplashActivity : AppCompatActivity() {
    private val TAG = this.javaClass.simpleName
    private lateinit var pref: LoginPref

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_splash)
        pref = LoginPref(this)

        AnimationHelper.addLayoutAnimation(parent_splash)
        AnimationHelper.addFadeInAnimation(
            applicationContext,
            img_logo,
            AnimationHelper.DURATION_X_LONG
        )

        checkSession()
    }

    private fun checkSession() {
        val i: Intent
        if (pref.role.equals("")) {
            i = Intent(this, LoginActivity::class.java)
        } else {
            i = Intent(this, MainActivity::class.java)
        }
        Handler().postDelayed({
            startActivity(i)
            finish()
        }, 3000)
    }
}
