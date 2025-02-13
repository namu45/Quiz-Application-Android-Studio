package com.example.quizapplication;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    private TextView questionTextView, resultTextView, totalQuestionsTextView;
    private Button ansAButton, ansBButton, ansCButton, ansDButton, submitButton, restartButton;
    private int currentQuestionIndex = 0;
    private int score = 0;  // To track score
    private boolean isAnswered = false;  // To check if the question is answered
    private String selectedAnswer = "";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // Initialize views
        questionTextView = findViewById(R.id.question);
        totalQuestionsTextView = findViewById(R.id.total_question);
        resultTextView = findViewById(R.id.result_text);
        ansAButton = findViewById(R.id.ans_A);
        ansBButton = findViewById(R.id.ans_B);
        ansCButton = findViewById(R.id.ans_C);
        ansDButton = findViewById(R.id.ans_D);
        submitButton = findViewById(R.id.submit_btn);
        restartButton = findViewById(R.id.restart_btn); // Restart button

        // Set onClick listeners
        ansAButton.setOnClickListener(this);
        ansBButton.setOnClickListener(this);
        ansCButton.setOnClickListener(this);
        ansDButton.setOnClickListener(this);
        submitButton.setOnClickListener(this);
        restartButton.setOnClickListener(this); // Set restart button listener

        // Set the total questions count in the TextView
        totalQuestionsTextView.setText("Total Questions: " + QuestionAnswer.question.length);

        // Load the first question
        loadQuestion();
    }

    private void loadQuestion() {
        // Reset button colors before loading new question
        resetAnswerButtons();

        // Set question text
        questionTextView.setText(QuestionAnswer.question[currentQuestionIndex]);

        // Set answer choices
        ansAButton.setText(QuestionAnswer.choices[currentQuestionIndex][0]);
        ansBButton.setText(QuestionAnswer.choices[currentQuestionIndex][1]);
        ansCButton.setText(QuestionAnswer.choices[currentQuestionIndex][2]);
        ansDButton.setText(QuestionAnswer.choices[currentQuestionIndex][3]);

        // Reset selected answer flag
        isAnswered = false;
        selectedAnswer = "";

        // Enable buttons again after restart
        enableAnswerButtons(true);
    }

    @Override
    public void onClick(View v) {
        // Handle answer button clicks
        if (v.getId() == R.id.ans_A || v.getId() == R.id.ans_B || v.getId() == R.id.ans_C || v.getId() == R.id.ans_D) {
            // Record selected answer
            if (v.getId() == R.id.ans_A) {
                selectedAnswer = QuestionAnswer.choices[currentQuestionIndex][0];
            } else if (v.getId() == R.id.ans_B) {
                selectedAnswer = QuestionAnswer.choices[currentQuestionIndex][1];
            } else if (v.getId() == R.id.ans_C) {
                selectedAnswer = QuestionAnswer.choices[currentQuestionIndex][2];
            } else if (v.getId() == R.id.ans_D) {
                selectedAnswer = QuestionAnswer.choices[currentQuestionIndex][3];
            }
            isAnswered = true;  // Mark the question as answered
            resetAnswerButtons();  // Reset previous colors
            highlightSelectedAnswer();  // Highlight the selected answer

            // Disable all answer buttons after one is clicked
            enableAnswerButtons(false);
        }

        // Handle submit button click
        if (v.getId() == R.id.submit_btn) {
            if (isAnswered) {
                checkAnswer();
                currentQuestionIndex++;
                if (currentQuestionIndex < QuestionAnswer.question.length) {
                    loadQuestion();
                } else {
                    showResults();
                }
            } else {
                Toast.makeText(this, "Please select an answer before submitting!", Toast.LENGTH_SHORT).show();
            }
        }

        // Handle restart button click
        if (v.getId() == R.id.restart_btn) {
            restartQuiz();
        }
    }

    private void checkAnswer() {
        // Check if the selected answer is correct
        if (selectedAnswer.equals(QuestionAnswer.correctAnswers[currentQuestionIndex])) {
            score++;
            Toast.makeText(this, "Correct!", Toast.LENGTH_SHORT).show();
        } else {
            Toast.makeText(this, "Incorrect!", Toast.LENGTH_SHORT).show();
        }
    }

    private void showResults() {
        // Display the user's score or final results in the result TextView
        String resultMessage = "You answered " + score + " out of " + QuestionAnswer.question.length + " questions correctly.";
        resultTextView.setText(resultMessage);  // Set result on the screen
        resultTextView.setVisibility(View.VISIBLE);  // Make sure result TextView is visible
        restartButton.setVisibility(View.VISIBLE);  // Show the restart button
        submitButton.setVisibility(View.GONE);  // Hide the submit button
    }

    private void resetAnswerButtons() {
        // Reset the colors of all buttons
        ansAButton.setBackgroundColor(getResources().getColor(android.R.color.white));
        ansBButton.setBackgroundColor(getResources().getColor(android.R.color.white));
        ansCButton.setBackgroundColor(getResources().getColor(android.R.color.white));
        ansDButton.setBackgroundColor(getResources().getColor(android.R.color.white));
    }

    private void highlightSelectedAnswer() {
        // Highlight the selected answer and set color based on correctness
        if (selectedAnswer.equals(QuestionAnswer.choices[currentQuestionIndex][0])) {
            ansAButton.setBackgroundColor(getResources().getColor(selectedAnswer.equals(QuestionAnswer.correctAnswers[currentQuestionIndex]) ?
                    android.R.color.holo_green_light : android.R.color.holo_red_light));
        } else if (selectedAnswer.equals(QuestionAnswer.choices[currentQuestionIndex][1])) {
            ansBButton.setBackgroundColor(getResources().getColor(selectedAnswer.equals(QuestionAnswer.correctAnswers[currentQuestionIndex]) ?
                    android.R.color.holo_green_light : android.R.color.holo_red_light));
        } else if (selectedAnswer.equals(QuestionAnswer.choices[currentQuestionIndex][2])) {
            ansCButton.setBackgroundColor(getResources().getColor(selectedAnswer.equals(QuestionAnswer.correctAnswers[currentQuestionIndex]) ?
                    android.R.color.holo_green_light : android.R.color.holo_red_light));
        } else if (selectedAnswer.equals(QuestionAnswer.choices[currentQuestionIndex][3])) {
            ansDButton.setBackgroundColor(getResources().getColor(selectedAnswer.equals(QuestionAnswer.correctAnswers[currentQuestionIndex]) ?
                    android.R.color.holo_green_light : android.R.color.holo_red_light));
        }
    }

    private void enableAnswerButtons(boolean enable) {
        // Enable or disable answer buttons based on the parameter
        ansAButton.setEnabled(enable);
        ansBButton.setEnabled(enable);
        ansCButton.setEnabled(enable);
        ansDButton.setEnabled(enable);
    }

    private void restartQuiz() {
        // Reset the quiz variables
        currentQuestionIndex = 0;
        score = 0;
        resultTextView.setVisibility(View.GONE);
        restartButton.setVisibility(View.GONE);
        submitButton.setVisibility(View.VISIBLE);
        enableAnswerButtons(true);  // Re-enable the buttons
        loadQuestion();
    }
}
