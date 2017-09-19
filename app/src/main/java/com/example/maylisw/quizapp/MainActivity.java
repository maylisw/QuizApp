package com.example.maylisw.quizapp;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.Button;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {
    private Button trueButton, falseButton, nextButton;
    private TextView question;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        wireWidgets();
    }
    public void wireWidgets(){
        trueButton = (Button) findViewById(R.id.button_true);
        falseButton = (Button) findViewById(R.id.button_true);
        nextButton = (Button) findViewById(R.id.button_true);
        question = (TextView) findViewById(R.id.question_text);
    }

}
