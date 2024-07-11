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

public class MainActivity extends AppCompatActivity {
    int currentindex;
    private static final String EXTRA_CURRENT_ITEM=
            "com.bignerdranch.android.geoquiz.answer_is_true";
    TextView textView;
    Question[] questions;
    Question currentQuestion;
    int trueanswers;
    Button but1;
    Button but2;
    Button cheat;
    public static Intent newIntent1(Context packageContext,int current_item) {
        Intent i = new Intent(packageContext, MainActivity.class);
        i.putExtra(EXTRA_CURRENT_ITEM,current_item);
        return i;
    }


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            currentindex = getIntent().getIntExtra(EXTRA_CURRENT_ITEM,0);
            trueanswers =0;
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            questions = new Question[]{
                    new Question(R.string.question_oceans, true),
                    new Question(R.string.question_mideast, false),
                    new Question(R.string.question_africa, false),
                    new Question(R.string.question_americas, true),
                    new Question(R.string.question_asia, true)
            };
            but1 = findViewById(R.id.button1);
            but2 = findViewById(R.id.button2);
            cheat = findViewById(R.id.cheat);
            textView = findViewById(R.id.text);
            currentindex = getIntent().getIntExtra(EXTRA_CURRENT_ITEM,0);
            cheat.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    boolean answerIsTrue = questions[currentindex].isAnswer();
                    Intent i = CheatActivity.newIntent(MainActivity.this, answerIsTrue,currentindex);
                    startActivity(i);
                }
            });

            textView.setText(getString(questions[currentindex].getQuestion()));
            Toast toast1 = Toast.makeText(this, "Correct!", Toast.LENGTH_SHORT);
            Toast toast2 = Toast.makeText(this,"Incorrect!",Toast.LENGTH_SHORT);
            currentQuestion = questions[currentindex];
            but1.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if(currentQuestion.isAnswer()){
                        toast1.show();
                        trueanswers+=1;
                        updateQuestion();
                    }
                    else {
                        toast2.show();
                        updateQuestion();
                    }

                }
            });
            but2.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if(!currentQuestion.isAnswer()){
                        toast1.show();
                        trueanswers+=1;
                        updateQuestion();
                    }
                    else {
                        toast2.show();
                        updateQuestion();
                    }

                }
            });
            return insets;
        });
    }
    @SuppressLint("SetTextI18n")
    public void updateQuestion(){
        if(currentindex>=questions.length-1){
            but1.setVisibility(View.GONE);
            but2.setVisibility(View.GONE);
            cheat.setVisibility(View.GONE);
            textView.setText(trueanswers+"/5");
        }
        else {
            currentindex+=1;
            textView.setText(getString(questions[currentindex].getQuestion()));
            currentQuestion = questions[currentindex];
        }

    }

}