package com.example.myfirstapp

import android.content.Intent
import android.content.SharedPreferences
import android.os.Bundle
import android.view.View
import android.widget.ImageButton
import android.widget.ImageView
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity

class AccountActivity : AppCompatActivity() {

    private lateinit var user_photo: ImageView
    private lateinit var user_name: TextView
    private lateinit var user_email: TextView
    private lateinit var log_out: ImageButton

    lateinit var sharedPreferences: SharedPreferences

    private val SHARED_PREF_NAME = "mypref"
    private val KEY_NAME = "name"
    private val KEY_EMAIL = "email"
    private val KEY_PHOTO = 0

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_account)

        user_photo = findViewById(R.id.user_photo)
        user_name = findViewById(R.id.user_name)
        user_email = findViewById(R.id.user_email)
        log_out = findViewById(R.id.exit_button)

        sharedPreferences = getSharedPreferences(SHARED_PREF_NAME, MODE_PRIVATE)

        var name = sharedPreferences.getString(KEY_NAME, null)
        var email = sharedPreferences.getString(KEY_EMAIL, null)
        var photo = sharedPreferences.getInt("KEY_PHOTO", 0)

        if (name != null && email != null && photo != 0) {
            user_photo.setImageResource(photo)
            user_name.text = name
            user_email.text = email
        }
        else {
            name = intent.getStringExtra("name")
            email = intent.getStringExtra("email")
            photo = intent.getIntExtra("photo", 0)

            user_photo.setImageResource(photo)
            user_name.setText(name)
            user_email.setText(email)
        }
        log_out.setOnClickListener(View.OnClickListener {
            val editor = sharedPreferences.edit()
            editor.clear()
            editor.commit()

            val openMainActivity = Intent(this@AccountActivity, MainActivity::class.java)
            startActivity(openMainActivity)
        })
    }
}