package com.udemy.myquizapplication

import android.annotation.SuppressLint
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
//import android.widget.ImageView
import android.widget.TextView

class CongratulationsActivity : AppCompatActivity() {
    @SuppressLint("SetTextI18n")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        // Sets the layout
        setContentView(R.layout.activity_congratulations)

        // Activity Views
        //val tvResult : TextView = findViewById(R.id.tv_result)
        //val icTrophy : ImageView = findViewById(R.id.iv_trophy)
        //val tvCongratulation : TextView = findViewById(R.id.tv_congratulation)

        // TODO tvResult, icTrophy, and tvCongratulation does not appear in emulator
        val tvName : TextView = findViewById(R.id.tv_name)
        val tvScore : TextView = findViewById(R.id.tv_score)
        val btnFinish : Button = findViewById(R.id.btn_finish)

        // Retrieves user name and sets it to tvName
        tvName.text = intent.getStringExtra(Constants.USER_NAME)

        val totalQuestions = intent.getIntExtra(Constants.TOTAL_QUESTIONS, 0)
        val correctAnswers = intent.getIntExtra(Constants.CORRECT_ANSWERS, 0)

        // Changes tv text to display the correct information
        tvScore.text = "Your Score is $correctAnswers out of $totalQuestions"

        btnFinish.setOnClickListener{
            startActivity(Intent(this, MainActivity::class.java))
        }
    }
}