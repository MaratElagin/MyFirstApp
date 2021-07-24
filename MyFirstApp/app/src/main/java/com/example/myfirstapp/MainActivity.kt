package com.example.entrance_app

import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.example.entrance_app.models.User
import com.example.myfirstapp.R
import java.util.*

class MainActivity : AppCompatActivity() {
    private val users: MutableList<User> = ArrayList()
    private val vysotsky = User("Vladimir Vysotsky", "vysotskyvlad@mail.ru", "Vlad38", R.drawable.vysotsky)
    private val elagin = User("Marat Elagin", "elaginmarat@mail.ru", "Marat02", R.drawable.marat)
    private lateinit var email: EditText
    private lateinit var password: EditText
    private lateinit var signIn: Button
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        email = findViewById(R.id.et_email)
        password = findViewById(R.id.et_password)
        signIn = findViewById(R.id.entrance_button)
        users.add(vysotsky)
        users.add(elagin)
        signIn.setOnClickListener(View.OnClickListener {
            val currUser = getUser(email.getText().toString(), password.getText().toString())
            if (currUser != null) {
                val openProfileAccount = Intent(this@MainActivity, AccountActivity::class.java)
                openProfileAccount.putExtra("name", currUser.name)
                openProfileAccount.putExtra("email", currUser.email)
                openProfileAccount.putExtra("photo", currUser.photo)
                startActivity(openProfileAccount)
            } else Toast.makeText(
                this@MainActivity,
                "This user isn't registered",
                Toast.LENGTH_SHORT
            ).show()
        })
    }

    private fun getUser(email: String, password: String): User? {
        for (user in users) {
            if (email == user.email && password == user.password) return user
        }
        return null
    }
}