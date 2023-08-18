package com.udemy.myquizapplication

import android.annotation.SuppressLint
import android.content.Intent
import android.graphics.Color
import android.graphics.Typeface
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.ImageView
import android.widget.ProgressBar
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.ContextCompat

class QuizQuestionsActivity : AppCompatActivity(), View.OnClickListener {

    private var mCurrentPosition : Int = 1 // Default and the first question position
    private var mQuestionsList : ArrayList<Question>? = null
    private var mSelectedOptionPosition : Int = 0

    // Global variable that retrieves the user name and correct answers from the main activity
    private var mUserName : String? = null
    private var mCorrectAnswers : Int = 0


    //Create global variables for the views in the layout
    private var progressBar : ProgressBar? = null
    private var tvProgress : TextView? = null
    private var tvQuestion : TextView? = null
    private var ivImage : ImageView? = null
    private var tvOptionOne : TextView? = null
    private var tvOptionTwo : TextView? = null
    private var tvOptionThree : TextView? = null
    private var tvOptionFour : TextView? = null
    private var btnSubmit : Button? = null

    /**
     * This function is auto created by Android when the Activity Class is created.
     */
    override fun onCreate(savedInstanceState: Bundle?) {
        //This call the parent constructor
        super.onCreate(savedInstanceState)
        // This is used to align the xml view to this class
        setContentView(R.layout.activity_quiz_questions)

        // This retrieves the user name from the main activity
        mUserName = intent.getStringExtra(Constants.USER_NAME)

        progressBar = findViewById(R.id.progress_bar)
        tvProgress = findViewById(R.id.tv_progress)
        tvQuestion = findViewById(R.id.tv_question)
        ivImage = findViewById(R.id.iv_image)
        tvOptionOne = findViewById(R.id.tv_option_one)
        tvOptionTwo = findViewById(R.id.tv_option_two)
        tvOptionThree = findViewById(R.id.tv_option_three)
        tvOptionFour = findViewById(R.id.tv_option_four)
        btnSubmit = findViewById(R.id.btn_submit)

        // Sets on click listener to options and accesses the onclick method
        tvOptionOne?.setOnClickListener(this)
        tvOptionTwo?.setOnClickListener(this)
        tvOptionThree?.setOnClickListener(this)
        tvOptionFour?.setOnClickListener(this)
        btnSubmit?.setOnClickListener(this)

        // Loads questions list
        // Makes the Question List a global variable
        mQuestionsList = Constants.getQuestions()

        // Calls the Question function
        setQuestion()
    }

    @SuppressLint("SetTextI18n")
    private fun setQuestion(){

        // Once the user goes to next question, this resets the tv
        defaultOptionsView()

        // TODO (Step 3: Setting the question in the UI from the list.)
        // START
        // Get the questions
        val question : Question = mQuestionsList!![mCurrentPosition - 1] // Getting the question from the list with the help of current position.

        // Set Image above the progress bar
        ivImage?.setImageResource(question.image)

        // Setting the current progress in the progressbar using the position of question
        progressBar?.progress = mCurrentPosition

        // Setting up the progress text
        tvProgress?.text = "$mCurrentPosition/${progressBar?.max}"

        // Setting the current question and the options in the UI
        tvQuestion?.text = question.question
        tvOptionOne?.text = question.optionOne
        tvOptionTwo?.text = question.optionTwo
        tvOptionThree?.text = question.optionThree
        tvOptionFour?.text = question.optionFour

        // Changes the button text if user reaches the last question
        if (mCurrentPosition == mQuestionsList!!.size){
            btnSubmit?.text = "FINISH"
        }else{
            btnSubmit?.text = "SUBMIT"
        }

    }

    private fun defaultOptionsView(){
        val options = ArrayList<TextView>()
        tvOptionOne?.let {
            options.add(0,it)
        }
        tvOptionTwo?.let {
            options.add(1,it)
        }
        tvOptionThree?.let {
            options.add(2,it)
        }
        tvOptionFour?.let {
            options.add(3,it)
        }

        for (option in options){
            option.setTextColor(Color.parseColor("#7A8089"))
            option.typeface = Typeface.DEFAULT
            option.background = ContextCompat.getDrawable(
                this, R.drawable.default_option_border_bg)
        }
    }

    private fun selectedOptionView(tv: TextView, selectedOptionNum: Int){
        defaultOptionsView()

        mSelectedOptionPosition = selectedOptionNum

        tv.setTextColor(Color.parseColor("#363A43"))
        tv.setTypeface(tv.typeface, Typeface.BOLD)
        tv.background = ContextCompat.getDrawable(
            this, R.drawable.selected_option_border_bg
        )
    }

    @SuppressLint("SetTextI18n")
    override fun onClick(view: View?) {
        when(view?.id){
            R.id.tv_option_one -> {
                tvOptionOne?.let {
                    selectedOptionView(it, 1)
                }
            }
            R.id.tv_option_two -> {
                tvOptionTwo?.let {
                    selectedOptionView(it, 2)
                }
            }
            R.id.tv_option_three -> {
                tvOptionThree?.let {
                    selectedOptionView(it, 3)
                }
            }
            R.id.tv_option_four -> {
                tvOptionFour?.let {
                    selectedOptionView(it, 4)
                }
            }
            R.id.btn_submit ->{
                if (mSelectedOptionPosition == 0){
                    mCurrentPosition ++

                    when{
                        mCurrentPosition <= mQuestionsList!!.size ->{
                            setQuestion()
                        }
                        else ->{
                            // Once user reaches finish
                            // This sends the data to the Congratulations Activity once button is pressed
                            val intent = Intent(this, CongratulationsActivity::class.java)
                            // Retrieves and sends User name
                            intent.putExtra(Constants.USER_NAME, mUserName)
                            // Retrieves and sends correct answers
                            intent.putExtra(Constants.CORRECT_ANSWERS, mCorrectAnswers)
                            // Retrieves and sends total questions
                            intent.putExtra(Constants.TOTAL_QUESTIONS, mQuestionsList?.size)
                            // Starts next activity
                            startActivity(intent)
                            // Prevents user from going back once on the final activity
                            finish()
                        }
                    }
                }else{
                    val question = mQuestionsList?.get(mCurrentPosition - 1)

                    /*
                    If statement that changes the tv to either display green or red box for correct
                    or incorrect answers
                     */
                    // If statement that checks for incorrect answer
                    if(question!!.correctAnswer != mSelectedOptionPosition){
                        answerView(mSelectedOptionPosition, R.drawable.incorrect_option_border_bg)
                    }else{
                        // This will increase the correct answer variable for which will be sent to the final activity
                        mCorrectAnswers++
                    }
                    // Putting the correct answer drawable here allows the program to show both incorrect
                    // and incorrect answers.
                    answerView(question.correctAnswer, R.drawable.correct_option_border_bg)

                    // If last question change button to finish else change to "Next Question"
                    if (mCurrentPosition == mQuestionsList!!.size){
                        btnSubmit?.text = "FINISH"
                    }else{
                        btnSubmit?.text = "Next Question"
                    }

                    // Sets selected option back to zero
                    mSelectedOptionPosition = 0
                }
            }
        }
    }

    private fun answerView(answer: Int, drawableView : Int){
        when(answer){
            1 -> {tvOptionOne?.background = ContextCompat.getDrawable(this, drawableView)}
            2 -> {tvOptionTwo?.background = ContextCompat.getDrawable(this, drawableView)}
            3 -> {tvOptionThree?.background = ContextCompat.getDrawable(this, drawableView)}
            4 -> {tvOptionFour?.background = ContextCompat.getDrawable(this, drawableView)}

        }
    }
}
// END