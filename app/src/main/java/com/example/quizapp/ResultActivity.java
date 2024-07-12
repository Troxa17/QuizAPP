package com.example.quizapp;

import android.content.Intent;
import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;

import com.example.quizapp.databinding.QuizResultBinding;

public class ResultActivity extends AppCompatActivity {

    private QuizResultBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = QuizResultBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        int score = getIntent().getIntExtra("SCORE", 0);
        int total = getIntent().getIntExtra("TOTAL", 0);

        binding.resultTextView.setText("Your Score: " + score + " / " + total);

        binding.restartQuizButton.setOnClickListener(view -> {
            Intent intent = new Intent(ResultActivity.this, MainActivity.class);
            startActivity(intent);
            finish();
        });
    }
}
