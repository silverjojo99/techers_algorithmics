package com.example.algorithmics



import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.EditText
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity

class LoginActivity : AppCompatActivity() {

    // ── View references (lateinit = assigned in onCreate, not at declaration) ──
    private lateinit var etLogin: EditText
    private lateinit var etPassword: EditText
    private lateinit var etPasswordVisible: EditText   // the "show password" field
    private lateinit var ivTogglePassword: ImageView
    private lateinit var btnLogin: Button
    private lateinit var tvSignTeacher: TextView

    // Tracks whether password is currently visible
    private var isPasswordVisible = false

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)

        bindViews()
        setupPasswordToggle()
        setupLoginButton()
        setupTeacherLink()
    }

    // ── Step 2a: Wire up all view references from the XML ──────────────────────
    private fun bindViews() {
        etLogin           = findViewById(R.id.et_login)
        etPassword        = findViewById(R.id.et_password)
        etPasswordVisible = findViewById(R.id.et_password_visible)
        ivTogglePassword  = findViewById(R.id.iv_toggle_password)
        btnLogin          = findViewById(R.id.btn_login)
        tvSignTeacher     = findViewById(R.id.tv_sign_teacher)
    }

    // ── Step 2b: Eye icon toggles between hidden/visible password ──────────────
    // The XML has TWO separate EditTexts: one with inputType="textPassword" (hidden)
    // and one with inputType="text" (visible). We toggle visibility between them.
    private fun setupPasswordToggle() {
        ivTogglePassword.setOnClickListener {
            isPasswordVisible = !isPasswordVisible

            if (isPasswordVisible) {
                // Show the plain-text field, copy current typed value across
                etPassword.visibility        = View.GONE
                etPasswordVisible.visibility = View.VISIBLE
                etPasswordVisible.setText(etPassword.text)
                ivTogglePassword.setImageResource(R.drawable.ic_eye_off)
            } else {
                // Show the password field, copy value back
                etPassword.visibility        = View.VISIBLE
                etPasswordVisible.visibility = View.GONE
                etPassword.setText(etPasswordVisible.text)
                // Move cursor to end
                etPassword.setSelection(etPassword.text.length)
                ivTogglePassword.setImageResource(R.drawable.ic_eye)
            }
        }
    }

    // ── Step 2c: Login button — validate then navigate ──────────────────────────
    private fun setupLoginButton() {
        btnLogin.setOnClickListener {
            val username = etLogin.text.toString().trim()
            // Read from whichever password field is currently active
            val password = if (isPasswordVisible) {
                etPasswordVisible.text.toString()
            } else {
                etPassword.text.toString()
            }

            when {
                username.isEmpty() -> {
                    etLogin.error = "Please enter your login"
                    etLogin.requestFocus()
                }
                password.isEmpty() -> {
                    val activeField = if (isPasswordVisible) etPasswordVisible else etPassword
                    activeField.error = "Please enter your password"
                    activeField.requestFocus()
                }
                else -> {
                    // TODO: Replace with real auth (API call / SharedPrefs check)
                    navigateToDashboard(username)
                }
            }
        }
    }

    // ── Step 2d: "Sign in as a teacher" link ───────────────────────────────────
    private fun setupTeacherLink() {
        tvSignTeacher.setOnClickListener {
            // Placeholder — in a real app this would open a teacher login flow
            Toast.makeText(this, "Teacher login coming soon", Toast.LENGTH_SHORT).show()
        }
    }

    // ── Step 2e: Navigate to dashboard, passing the username ───────────────────
    private fun navigateToDashboard(username: String) {
        val intent = Intent(this, MainDashboardActivity::class.java).apply {
            putExtra(MainDashboardActivity.EXTRA_USERNAME, username)
        }
        startActivity(intent)
        finish() // Remove LoginActivity from back stack — no "back to login"
    }
}
