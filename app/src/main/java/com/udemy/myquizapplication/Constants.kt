package com.udemy.myquizapplication

// This object is used to store all constants
object Constants {

    // Constants that will be used for storing data for the result activity
    const val USER_NAME : String = "user_name"
    const val TOTAL_QUESTIONS : String = "total_questions"
    const val CORRECT_ANSWERS : String = "correct_answers"


    // Function that gets questions and is called from the activity
    // This can also be done from an xml or JSON file
    fun getQuestions(): ArrayList<Question>{

        val questionsList = ArrayList<Question>()

        // Creates a question using the parameters in the Question.kt file
        val que1 = Question(
            1, "What country does this flag belong to?",
            R.drawable.ic_flag_of_argentina, "Argentina", "Australia",
            "Armenia", "Austria", 1
        )
        // Adds question to Questions Array
        questionsList.add(que1)

        val que2 = Question(
            1, "What country does this flag belong to?",
            R.drawable.ic_flag_of_australia, "Argentina", "Australia",
            "Armenia", "Austria", 2
        )
        // Adds question to Questions Array
        questionsList.add(que2)

        val que3 = Question(
            1, "What country does this flag belong to?",
            R.drawable.ic_flag_of_belgium, "Argentina", "Australia",
            "Belgium", "New Zealand", 3
        )
        // Adds question to Questions Array
        questionsList.add(que3)

        val que4 = Question(
            1, "What country does this flag belong to?",
            R.drawable.ic_flag_of_brazil, "Argentina", "Brazil",
            "Belgium", "New Zealand", 2
        )
        // Adds question to Questions Array
        questionsList.add(que4)

        val que5 = Question(
            1, "What country does this flag belong to?",
            R.drawable.ic_flag_of_denmark, "Denmark", "Australia",
            "Belgium", "New Zealand", 1
        )
        // Adds question to Questions Array
        questionsList.add(que5)

        val que6 = Question(
            1, "What country does this flag belong to?",
            R.drawable.ic_flag_of_fiji, "Argentina", "Australia",
            "Fiji", "New Zealand", 3
        )
        // Adds question to Questions Array
        questionsList.add(que6)

        val que7 = Question(
            1, "What country does this flag belong to?",
            R.drawable.ic_flag_of_germany, "Sudan", "Australia",
            "Belgium", "Germany", 4
        )
        // Adds question to Questions Array
        questionsList.add(que7)

        val que8 = Question(
            1, "What country does this flag belong to?",
            R.drawable.ic_flag_of_india, "Argentina", "United States",
            "Belgium", "India", 4
        )
        // Adds question to Questions Array
        questionsList.add(que8)

        val que9 = Question(
            1, "What country does this flag belong to?",
            R.drawable.ic_flag_of_kuwait, "Iraq", "Australia",
            "Afghanistan", "Kuwait", 4
        )
        // Adds question to Questions Array
        questionsList.add(que9)

        val que10 = Question(
            1, "What country does this flag belong to?",
            R.drawable.ic_flag_of_new_zealand, "Iraq", "Australia",
            "New Zealand", "Kuwait", 3
        )
        // Adds question to Questions Array
        questionsList.add(que10)



        // returns the question list
        return questionsList
    }
}