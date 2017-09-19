package com.example.maylisw.quizapp;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {
    private Button trueButton, falseButton, nextButton;
    private TextView question;
    private ArrayList<Questions> questions;
    private int x;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        wireWidgets();
        listenersOnClick();
        createQuestions();
        x = 0;
    }

    private void createQuestions() {
        questions = new ArrayList<>();
        questions.add(new Questions("Le Tour de France occurs twice per yer.", false));
        questions.add(new Questions("France is better than The United States of America in every way.", true));
        questions.add(new Questions("The Vatican City is the capital the United States.", false));
        questions.add(new Questions("France is the world's capital of posthumous marriage", true));
        questions.add(new Questions("Isabelle is the best little sister", true));
    }

    private void listenersOnClick() {
        trueButton.setOnClickListener(this);
        falseButton.setOnClickListener(this);
        nextButton.setOnClickListener(this);
    }

    public void wireWidgets(){
        trueButton = (Button) findViewById(R.id.button_true);
        falseButton = (Button) findViewById(R.id.button_true);
        nextButton = (Button) findViewById(R.id.button_true);
        question = (TextView) findViewById(R.id.question_text);
    }


    @Override
    public void onClick(View view) {
        switch(view.getId()){
            case R.id.button_false:
                checkAnswer(x, false);
                break;
            case R.id.button_true:
                checkAnswer(x, true);
                break;
            case R.id.button_next:
                question.setText((CharSequence) questions.get(x));
                x++;
                break;
        }
    }

    private void checkAnswer(int questionsNumber, boolean answer) {
        questions.get(questionsNumber).checkAnswer(answer);
    }
}
