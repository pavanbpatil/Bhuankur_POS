package com.alphaone.pos.activity

import android.content.Intent
import android.os.Bundle
import android.text.InputType
import android.view.MotionEvent
import android.view.View
import android.view.View.OnTouchListener
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.alphaone.pos.R
import com.alphaone.pos.commonutilities.CommonUtils
import com.alphaone.pos.modelclass.Data
import com.alphaone.pos.networkmanager.LoginManager
import kotlinx.android.synthetic.main.activity_login.*
import kotlinx.android.synthetic.main.layout_otp_login.*
import kotlinx.android.synthetic.main.layout_password_login.*
import kotlinx.android.synthetic.main.toolbar.*


class Login : AppCompatActivity(), LoginManager.StatusListener {
    private var loginManager: LoginManager? = null
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)
        setSupportActionBar(toolBar)
        loginManager= LoginManager(this@Login, this)
        btLoginWithPassword.setOnClickListener {
            layoutOtpLogin.visibility=View.GONE
            layoutPasswordLogin.visibility=View.VISIBLE

        }
        btLoginWithOtp.setOnClickListener {
            layoutOtpLogin.visibility=View.VISIBLE
            layoutPasswordLogin.visibility=View.GONE
        }
        btLogin.setOnClickListener {
            loginManager!!.doLogin("false",etUserId.text.toString(),etPassword.text.toString())
        }
        btGetOtp.setOnClickListener {
            startActivity(Intent(this,OtpVerification::class.java))
        }
        ivViewPassword.setOnTouchListener(OnTouchListener { v, event ->
            when (event.action) {
                MotionEvent.ACTION_DOWN -> etPassword.setInputType(InputType.TYPE_CLASS_TEXT)
                MotionEvent.ACTION_UP -> etPassword.setInputType(InputType.TYPE_CLASS_TEXT or InputType.TYPE_TEXT_VARIATION_PASSWORD)
            }
            true
        })


    }

    override fun onLoginSuccess(
        status: String,
        message: String,
        data: Data
    ) {
        CommonUtils.showMessageDialog(this, message, status)

    }

    override fun onLoginFailure(status: String, errorMessage: String) {
        CommonUtils.showMessageDialog(this,errorMessage,null)
    }


}
