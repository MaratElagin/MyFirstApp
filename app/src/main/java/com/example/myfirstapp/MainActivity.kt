package com.example.myfirstapp

import android.content.Intent
import android.content.SharedPreferences
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.example.entrance_app.models.User
import java.util.*

class MainActivity : AppCompatActivity() {

    private val users: MutableList<User> = ArrayList()
    private val vysotsky =
        User("Vladimir Vysotsky", "vysotskyvlad@mail.ru", "Vlad38", R.drawable.vysotsky)
    private val elagin = User("Marat Elagin", "elaginmarat@mail.ru", "Marat02", R.drawable.marat)

    private lateinit var email: EditText
    private lateinit var password: EditText
    private lateinit var signIn: Button

    lateinit var sharedPreferences: SharedPreferences

    private val SHARED_PREF_NAME = "mypref"
    private val KEY_NAME = "name"
    private val KEY_EMAIL = "email"
    private val KEY_PHOTO = 0

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        sharedPreferences = getSharedPreferences(SHARED_PREF_NAME, MODE_PRIVATE)

        val mail = sharedPreferences.getString(KEY_EMAIL, null)
        if (mail != null) {
            val openProfileAccount = Intent(this@MainActivity, AccountActivity::class.java)
            startActivity(openProfileAccount)
        }

        email = findViewById(R.id.et_email)
        password = findViewById(R.id.et_password)
        signIn = findViewById(R.id.entrance_button)

        users.add(vysotsky)
        users.add(elagin)


        signIn.setOnClickListener(View.OnClickListener {
            if(!isEmailExists(email.getText().toString()))
                Toast.makeText(
                    this@MainActivity,
                    "This user isn't registered",
                    Toast.LENGTH_SHORT
                ).show()
            else if (!isPasswordMatch(password.getText().toString()))
                    Toast.makeText(
                        this@MainActivity,
                        "Password must contain more than 6 symbols: 0-9, A-Z, a-z",
                        Toast.LENGTH_LONG
                    ).show()
                else {
                    val currUser =
                        getUser(email.getText().toString(), password.getText().toString())
                    if (currUser != null) {
                        val editor = sharedPreferences.edit()
                        editor.putString(KEY_NAME, currUser.name)
                        editor.putString(KEY_EMAIL, email.text.toString())
                        editor.putInt("KEY_PHOTO", currUser.photo)
                        editor.apply()

                        val openProfileAccount =
                            Intent(this@MainActivity, AccountActivity::class.java)
                        openProfileAccount.putExtra("name", currUser.name)
                        openProfileAccount.putExtra("email", currUser.email)
                        openProfileAccount.putExtra("photo", currUser.photo)
                        startActivity(openProfileAccount)
                    }
                }
        })
    }

    private fun getUser(email: String, password: String): User? {
        for (user in users) {
            if (email == user.email && password == user.password) return user
        }
        return null
    }

    private fun isPasswordMatch(password: String): Boolean {
        if (password != password.lowercase() &&
            password != password.uppercase() && password.length > 5
        ) {
            for (c in password.toCharArray()) if (java.lang.Character.isDigit(c)) return true
        }
        return false
    }

    private fun isEmailExists(email: String): Boolean {
        for (user in users) {
            if (user.email.equals(email)) return true
        }
        return false
    }

}