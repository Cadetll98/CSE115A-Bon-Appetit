package com.cookingapp

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Button
import android.widget.Toast
import android.widget.EditText
import com.google.firebase.auth.ktx.auth
import com.google.firebase.ktx.Firebase
import com.google.firebase.auth.FirebaseAuth


class LoginUI : AppCompatActivity() {
    private lateinit var auth: FirebaseAuth // declare instance of FirebaseAuth

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        auth = Firebase.auth
        //auth.signOut()  // debug
        val currentUser = auth.currentUser

        // Go to main menu if user is signed in, else go to LoginUI
        if(currentUser != null){
            Log.d("Login", "User is signed in")
            finish()
        } else {
            // if no user, prompt to login UI page
            setContentView(R.layout.activity_login_ui)

            val button = findViewById<Button>(R.id.loginButton)
            val editTextTextEmailAddress = findViewById<EditText>(R.id.editTextTextEmailAddress)
            val editTextTextPassword = findViewById<EditText>(R.id.editTextTextPassword)

            // Sign in using email/password given when button is pressed
            // only go to main menu when credential matches
            button.setOnClickListener{
                if(editTextTextEmailAddress.text.isNullOrBlank()||editTextTextPassword.text.isNullOrBlank()){
                    Toast.makeText(this,"Please fill the required fields", Toast.LENGTH_SHORT).show()
                }
                else{
                    Toast.makeText(this,"Logging in", Toast.LENGTH_SHORT).show()
                    signIn(editTextTextEmailAddress.text.toString(), editTextTextPassword.text.toString())
                }
            }
        }

    }

    private fun createAccount(email: String, password: String) {
        auth.createUserWithEmailAndPassword(email, password)
            .addOnCompleteListener(this) { task ->
                if (task.isSuccessful) {
                    // Sign in success, update UI with the signed-in user's information
                    Log.d("Login", "createUserWithEmail:success")
                    //updateUI(auth.currentUser)
                } else {
                    // If sign in fails, display a message to the user.
                    Log.w("Login", "createUserWithEmail:failure", task.exception)
                    Toast.makeText(baseContext, "Authentication failed.", Toast.LENGTH_SHORT).show()
                    //updateUI(null)
                }
            }
    }
    // sign in with given email/password, return true if succeed, false otherwise
    private fun signIn(email: String, password: String) {
        auth.signInWithEmailAndPassword(email, password)
            .addOnCompleteListener(this) { task ->
                if (task.isSuccessful) {
                    Log.d("Login", "signInWithEmail:success")
                    finish()    // go to main menu
                } else {
                    Log.w("Login", "signInWithEmail:failure", task.exception)
                    Toast.makeText(baseContext, "Authentication failed.", Toast.LENGTH_SHORT).show()
                }
            }
    }
}
