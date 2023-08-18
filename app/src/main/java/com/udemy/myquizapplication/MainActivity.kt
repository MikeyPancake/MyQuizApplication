package com.udemy.myquizapplication

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.Toast

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val btnStart : Button = findViewById(R.id.btnStart)
        val etName : EditText = findViewById(R.id.etName)

        // Event that listens for users input into text box on main screen
        btnStart.setOnClickListener {

            // If user does not enter name, a toast is given to enter name
            if (etName.text.isEmpty()) {
                Toast.makeText(this, "Please Enter Name", Toast.LENGTH_LONG).show()
            } else{
                // If user inputs name then the else statement will send user to next screen
                // This references where you are going (from current activity to..)
                val intent = Intent(this, QuizQuestionsActivity::class.java)

                // Intent is used to send data from one activity to another
                // In this case the data is sent to the QuizQuestion Activity
                // This information must be retrieved in the QuizQuestion Activity
                intent.putExtra(Constants.USER_NAME, etName.text.toString())

                // Starts the next activity
                startActivity(intent)
                // When user clicks back, finish will close the application
                finish()
            }
        }
    }
}