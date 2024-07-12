package com.example.quizapp;

import android.content.Intent;
import android.os.Bundle;
import android.widget.RadioButton;
import androidx.appcompat.app.AppCompatActivity;
import com.example.quizapp.databinding.QuizGameBinding;

public class QuizActivity extends AppCompatActivity {

    private QuizGameBinding binding;
    private int currentQuestionIndex = 0;
    private int score = 0;
    private String[] questions = {"Largest animal in the world",
            " Name the name of the US ships created for the purpose of flying to the Moon?",
            "What is a two-seater bicycle called?"};
    private String[][] options = {
            {"Elephant", "Сrocodile", "Whale", "Giraffe"},
            {"Gector", "Apolon", "Thor", "Moon's spector"},
            {"Strangers", "Roco", "Tandem", "Duo"}
    };
    private int[] correctAnswers = {0, 1, 2};

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = QuizGameBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        loadQuestion();

        binding.submitAnswerButton.setOnClickListener(view -> {
            int selectedId = binding.optionsRadioGroup.getCheckedRadioButtonId();
            RadioButton selectedRadioButton = findViewById(selectedId);
            int selectedIndex = binding.optionsRadioGroup.indexOfChild(selectedRadioButton);

            if (selectedIndex == correctAnswers[currentQuestionIndex]) {
                score++;
            }

            currentQuestionIndex++;

            if (currentQuestionIndex < questions.length) {
                loadQuestion();
            } else {
                Intent intent = new Intent(QuizActivity.this, ResultActivity.class);
                intent.putExtra("SCORE", score);
                intent.putExtra("TOTAL", questions.length);
                startActivity(intent);
                finish();
            }
        });
    }

    private void loadQuestion() {

        binding.questionTextView.setText(questions[currentQuestionIndex]);

        binding.optionsRadioGroup.clearCheck();

        int count = binding.optionsRadioGroup.getChildCount();

        for (int i = 0; i < count; i++) {
            RadioButton radioButton = (RadioButton) binding.optionsRadioGroup.getChildAt(i);
            radioButton.setText(options[currentQuestionIndex][i]);
        }
    }
}
