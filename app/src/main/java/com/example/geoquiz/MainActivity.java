package com.example.geoquiz;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class MainActivity extends AppCompatActivity {
    int currentindex;

    public TextView textView;
    public List<Question> questions;
    public Question currentQuestion;
    public int trueanswers;
    public Button but1;
    public Button but2;
    public Button cheat;

    public static Intent newIntent1(Context packageContext, int current_item) {
        Intent i = new Intent(packageContext, MainActivity.class);
        return i;
    }


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        trueanswers = 0;
        questions = initQuestions();
        but1 = findViewById(R.id.button1);
        but2 = findViewById(R.id.button2);
        cheat = findViewById(R.id.cheat);
        textView = findViewById(R.id.text);
        cheat.setOnClickListener(v -> {
            boolean answerIsTrue = questions.get(currentindex).isAnswer();
            Intent i = CheatActivity.newIntent(MainActivity.this, answerIsTrue);
            startActivity(i);
        });

        textView.setText(getString(questions.get(currentindex).getQuestion()));
        Toast toast1 = Toast.makeText(this, "Correct!", Toast.LENGTH_SHORT);
        Toast toast2 = Toast.makeText(this, "Incorrect!", Toast.LENGTH_SHORT);
        currentQuestion = questions.get(currentindex);
        but1.setOnClickListener(v -> {
            if (currentQuestion.isAnswer()) {
                toast1.show();
                trueanswers += 1;
                updateQuestion();
            } else {
                toast2.show();
                updateQuestion();
            }

        });
        but2.setOnClickListener(v -> {
            if (!currentQuestion.isAnswer()) {
                toast1.show();
                trueanswers += 1;
                updateQuestion();
            } else {
                toast2.show();
                updateQuestion();
            }

        });
    }

    @SuppressLint("SetTextI18n")
    public void updateQuestion() {
        if (currentindex >= questions.size() - 1) {
            but1.setVisibility(View.GONE);
            but2.setVisibility(View.GONE);
            cheat.setVisibility(View.GONE);
            textView.setText(trueanswers + "/5");
        } else {
            currentindex += 1;
            textView.setText(getString(questions.get(currentindex).getQuestion()));
            currentQuestion = questions.get(currentindex);
        }

    }

    private List<Question> initQuestions() {
        return new ArrayList<>(
                Arrays.asList(
                        new Question(R.string.question_oceans, true),
                        new Question(R.string.question_mideast, false),
                        new Question(R.string.question_africa, false),
                        new Question(R.string.question_americas, true),
                        new Question(R.string.question_asia, true))
        );
    }
}