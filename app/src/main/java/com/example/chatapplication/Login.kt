package com.example.chatapplication

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import com.google.firebase.auth.FirebaseAuth
import java.net.PasswordAuthentication

class Login : AppCompatActivity() {

    private lateinit var editEmail: EditText
    private lateinit var editPassword: EditText
    private lateinit var buttonLogin: Button
    private lateinit var buttonSignUp: Button
    private lateinit var mAuth: FirebaseAuth //authentication




    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)

        supportActionBar?.hide()

        mAuth = FirebaseAuth.getInstance()

        editEmail = findViewById(R.id.edit_email)
        editPassword = findViewById(R.id.edit_password)
        buttonLogin = findViewById(R.id.buttonLogin)
        buttonSignUp = findViewById(R.id.buttonSignUp)

        buttonSignUp.setOnClickListener { //when clicked, it navigates the user to signup activity
            val intent = Intent(this, SignUp::class.java)
            startActivity(intent)
        }

        buttonLogin.setOnClickListener {
            val email = editEmail.text.toString()
            val password = editPassword.text.toString()

            login(email, password);
        }
    }

    private fun login(email: String, password: String){
        //login user login users

        mAuth.signInWithEmailAndPassword(email, password) //authentication
            .addOnCompleteListener(this) { task -> //handle the result of authentication attempt
                if (task.isSuccessful) {
                    //for login user

                    val intent = Intent(this@Login, MainActivity::class.java) //if successful, redirected to main activity
                    finish()
                    startActivity(intent)


                } else {
                    Toast.makeText(this@Login, "user does not exist", Toast.LENGTH_SHORT).show()
                }
            }
    }


}