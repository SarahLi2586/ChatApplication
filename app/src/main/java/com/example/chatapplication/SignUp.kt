package com.example.chatapplication

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.FirebaseDatabase

class SignUp : AppCompatActivity() {

    private lateinit var editName: EditText
    private lateinit var editEmail: EditText
    private lateinit var editPassword: EditText
    private lateinit var buttonSignUp: Button
    private lateinit var mAuth: FirebaseAuth
    private lateinit var mDbRef: DatabaseReference


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_sign_up)

        supportActionBar?.hide()

        mAuth = FirebaseAuth.getInstance()

        editName = findViewById(R.id.edit_name)
        editEmail = findViewById(R.id.edit_email)
        editPassword = findViewById(R.id.edit_password)
        buttonSignUp = findViewById(R.id.buttonSignUp)

        buttonSignUp.setOnClickListener {
            val name = editName.text.toString()
            val email = editEmail.text.toString()
            val password = editPassword.text.toString()

            signUp(name, email, password)
        }
    }

    private fun signUp(name: String, email: String, password: String){
        //logic of creating users
        mAuth.createUserWithEmailAndPassword(email, password) //create a new user account with the provided email and password
            .addOnCompleteListener(this) { task ->
                if (task.isSuccessful) {
                    //code for jumping to home
                    addUserToDatabase(name, email, mAuth.currentUser?.uid!!) //user info is added to database,navigated to main activity
                    val intent = Intent(this@SignUp, MainActivity::class.java)
                    finish()
                    startActivity(intent)

                } else {
                    Toast.makeText(this@SignUp, "some error occurred", Toast.LENGTH_SHORT).show()

                }
            }
    }

    private fun addUserToDatabase(name: String, email: String, uid: String){
        mDbRef = FirebaseDatabase.getInstance().getReference()
        mDbRef.child("user").child(uid).setValue(User(name, email, uid))
    }
}