package com.example.techers_algorithmics

import android.content.Intent
import android.os.Bundle
import android.text.InputType
import android.widget.Button
import android.widget.EditText
import android.widget.ImageView
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import com.example.algorithmics.R

/**
 * SCREEN 1 & 2 — Teacher Login
 * Handles email/password input + show/hide password toggle
 */
class TeacherLoginActivity : AppCompatActivity() {

    private lateinit var etEmail: EditText
    private lateinit var etPassword: EditText
    private lateinit var etPasswordVisible: EditText
    private lateinit var ivTogglePwd: ImageView
    private lateinit var btnLogin: Button
    private lateinit var tvForgotPassword: TextView
    private lateinit var tvLoginAsStudent: TextView

    private var isPasswordVisible = false

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_teacher_login)

        etEmail           = findViewById(R.id.et_teacher_email)
        etPassword        = findViewById(R.id.et_teacher_password)
        etPasswordVisible = findViewById(R.id.et_teacher_password_visible)
        ivTogglePwd       = findViewById(R.id.iv_toggle_pwd)
        btnLogin          = findViewById(R.id.btn_teacher_login)
        tvForgotPassword  = findViewById(R.id.tv_forgot_password)
        tvLoginAsStudent  = findViewById(R.id.tv_login_as_student)

        // Toggle show/hide password
        ivTogglePwd.setOnClickListener {
            isPasswordVisible = !isPasswordVisible
            if (isPasswordVisible) {
                etPassword.inputType = InputType.TYPE_CLASS_TEXT or InputType.TYPE_TEXT_VARIATION_VISIBLE_PASSWORD
                ivTogglePwd.setImageResource(R.drawable.ic_eye_off)
            } else {
                etPassword.inputType = InputType.TYPE_CLASS_TEXT or InputType.TYPE_TEXT_VARIATION_PASSWORD
                ivTogglePwd.setImageResource(R.drawable.ic_eye)
            }
            etPassword.setSelection(etPassword.text.length)
        }

        btnLogin.setOnClickListener {
            val email    = etEmail.text.toString().trim()
            val password = etPassword.text.toString().trim()
            if (email.isNotEmpty() && password.isNotEmpty()) {
                // TODO: call auth API
                startActivity(Intent(this, TeacherDashboardTabsActivity::class.java))
                finish()
            }
        }

        tvForgotPassword.setOnClickListener {
            // TODO: navigate to forgot-password screen
        }

        tvLoginAsStudent.setOnClickListener {
            // TODO: navigate to student login
        }
    }
}
