package com.example.test2

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.Toast

class MainActivity : AppCompatActivity() {

    private lateinit var edEmail: EditText
    private lateinit var edPass: EditText
    private lateinit var btnSignin: Button
    private lateinit var btnSignup: Button

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        edEmail = findViewById(R.id.emailBox)
        edPass = findViewById(R.id.passBox)
        btnSignin = findViewById(R.id.btn_singin)
        btnSignup = findViewById(R.id.btn_singup)

        btnSignin.setOnClickListener{
            val email = edEmail.text.toString()
            val pass = edPass.text.toString()

            if (email.isNullOrBlank() && pass.isNullOrBlank()){
                Toast.makeText(this, "Missing Data", Toast.LENGTH_SHORT).show()
            }
            else{
                if(email == "admin" && pass == "123"){
                    Intent(this@MainActivity, home::class.java).apply {

                        val user = User(1, "Hasin")

                        putExtra("User", user)
                        startActivity(this)
                    }
                }
            }
        }

        btnSignup.setOnClickListener{

        }

    }
}