package com.example.entrance_app

import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.ImageButton
import android.widget.ImageView
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import com.example.myfirstapp.R

class AccountActivity : AppCompatActivity() {
    private lateinit var user_photo: ImageView
    private lateinit var user_name: TextView
    private lateinit var user_email: TextView
    private lateinit var log_out: ImageButton
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_account)
        user_photo = findViewById(R.id.user_photo)
        user_name = findViewById(R.id.user_name)
        user_email = findViewById(R.id.user_email)
        log_out = findViewById(R.id.exit_button)
        val name = intent.getStringExtra("name")
        val email = intent.getStringExtra("email")
        val photo = intent.getIntExtra("photo", 0)
        user_photo.setImageResource(photo)
        user_name.setText(name)
        user_email.setText(email)
        log_out.setOnClickListener(View.OnClickListener {
            val openMainActivity = Intent(this@AccountActivity, MainActivity::class.java)
            startActivity(openMainActivity)
        })
    }
}