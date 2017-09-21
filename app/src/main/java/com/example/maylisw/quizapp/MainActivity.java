package com.example.maylisw.quizapp;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {
    private Button trueButton, falseButton, nextButton;
    private TextView question;
    private ArrayList<Questions> questions;
    private int questionsNumber, score;
    private SharedPreferences sharedPref;
    private SharedPreferences.Editor editor;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        wireWidgets();
        listenersOnClick();
        createQuestions();
        questionsNumber = 0;
        question.setText(questions.get(questionsNumber).getQuestionText());
        sharedPref = this.getPreferences(Context.MODE_PRIVATE);
        editor = sharedPref.edit();


    }

    private void createQuestions() {
        questions = new ArrayList<>();
        questions.add(new Questions(getString(R.string.race), false));
        questions.add(new Questions(getString(R.string.better), true));
        questions.add(new Questions(getString(R.string.capital), false));
        questions.add(new Questions(getString(R.string.marriage), true));
        questions.add(new Questions(getString(R.string.sister), true));
    }

    public void listenersOnClick() {
        trueButton.setOnClickListener(this);
        falseButton.setOnClickListener(this);
        nextButton.setOnClickListener(this);
    }

    public void wireWidgets(){
        trueButton = (Button) findViewById(R.id.button_true);
        falseButton = (Button) findViewById(R.id.button_false);
        nextButton = (Button) findViewById(R.id.button_next);
        question = (TextView) findViewById(R.id.question_text);
    }


    @Override
    public void onClick(View view) {
        switch(view.getId()){
            case R.id.button_false:
                checkAnswer(questionsNumber, false);
                break;
            case R.id.button_true:
                checkAnswer(questionsNumber, true);
                break;
            case R.id.button_next:
                if(questionsNumber < questions.size()){
                    questionsNumber ++;
                }
                else {
                    Intent i = new Intent(this, Main2Activity.class);
                    startActivity(i);
                    editor.putInt("score", score);
                    editor.commit();
                }
                question.setText(questions.get(questionsNumber).getQuestionText());
                break;
        }
    }

    private void checkAnswer(int questionsNumber, boolean answer) {
        boolean s =  questions.get(questionsNumber).checkAnswer(answer);
        if(s) {
            Toast.makeText(this, getString(R.string.correct), Toast.LENGTH_SHORT).show();
            score++;
        } else {
            Toast.makeText(this, getString(R.string.incorrect), Toast.LENGTH_SHORT).show();
        }
    }


}
//yeah but there