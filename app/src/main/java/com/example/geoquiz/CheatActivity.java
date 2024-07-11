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

public class CheatActivity extends AppCompatActivity {
    private static final String EXTRA_ANSWER_IS_TRUE =
            "com.bignerdranch.android.geoquiz.answer_is_true";
    private static final String EXTRA_CURRENT_ITEM=
            "com.bignerdranch.android.geoquiz.current_item";
    private boolean answerIsTrue;
    private int currentindex;

    public static Intent newIntent(Context packageContext, boolean answerIsTrue,int currentindex) {
        Intent i = new Intent(packageContext, CheatActivity.class);
        i.putExtra(EXTRA_ANSWER_IS_TRUE, answerIsTrue);
        return i;
    }

    Button cheatBtn;
    TextView cheatText;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_cheat);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            cheatBtn = findViewById(R.id.cheatBtn);
            cheatText = findViewById(R.id.cheatText);
            cheatText.setText(R.string.cheat_activity);
            cheatBtn.setText(R.string.show_button);

            answerIsTrue = getIntent().getBooleanExtra(EXTRA_ANSWER_IS_TRUE, false);
            currentindex = getIntent().getIntExtra(EXTRA_CURRENT_ITEM,0);
            cheatBtn.setOnClickListener(new View.OnClickListener() {
                @SuppressLint("SetTextI18n")
                @Override
                public void onClick(View v) {
                    cheatBtn.setVisibility(View.GONE);
                    Toast.makeText(CheatActivity.this, "You are a cheater", Toast.LENGTH_SHORT).show();
                    cheatText.setText("The answer is " + answerIsTrue);
                }
            });

            return insets;
        });
    }
}