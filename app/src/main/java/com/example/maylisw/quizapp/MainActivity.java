package com.example.maylisw.quizapp;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.PersistableBundle;
import android.preference.PreferenceManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;

import static android.app.PendingIntent.getActivity;
import static android.util.Log.*;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {
    private Button trueButton, falseButton, nextButton;
    private TextView question;
    private ArrayList<Questions> questions;
    private int questionsNumber, score;
    public static final String TAG = "MainActivity";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        wireWidgets();
        listenersOnClick();
        createQuestions();
        //check if we are resuming
        if(savedInstanceState != null){
            questionsNumber = savedInstanceState.getInt(getString(R.string.QNo), 0);
        } else {
            questionsNumber = 0;
        }
        score = 0;
        question.setText(questions.get(questionsNumber).getQuestionText());
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
                falseButton.setEnabled(false);
                trueButton.setEnabled(false);
                checkAnswer(questionsNumber, false);
                break;
            case R.id.button_true:
                falseButton.setEnabled(false);
                trueButton.setEnabled(false);
                checkAnswer(questionsNumber, true);
                break;
            case R.id.button_next:
                if(questionsNumber < questions.size() - 1){
                    questionsNumber ++;
                    question.setText(questions.get(questionsNumber).getQuestionText());
                    falseButton.setEnabled(true);
                    trueButton.setEnabled(true);
                }
                else {
                    Log.d("message 1", "Score: "+score);
                    SharedPreferences sharedPref = PreferenceManager.getDefaultSharedPreferences(this);;
                    SharedPreferences.Editor editor = sharedPref.edit();
                    editor.putInt(getString(R.string.score), score);
                    editor.commit();
                    editor.apply();
                    Intent i = new Intent(this, Main2Activity.class);
                    startActivity(i);
                }
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
    //prevents a bug if device is rotated
    @Override
    protected void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        Log.d(TAG, "onSaveInstanceState: method fired");
        outState.putInt(getString(R.string.QNo), questionsNumber);
    }

    //Android Lifecycles
    @Override
    protected void onDestroy() {
        super.onDestroy();
        Log.d(TAG, "onDestroy: method fired");
    }

    @Override
    protected void onPause() {
        super.onPause();
        Log.d(TAG, "onPause: method fired");
    }
    @Override
    protected void onResume(){
        super.onResume();
        Log.d(TAG, "onResume: method fired");
    }

    @Override
    protected void onStart() {
        super.onStart();
        Log.d(TAG, "onStart: method fired");
    }

    @Override
    protected void onStop() {
        super.onStop();
        Log.d(TAG, "onStop: method fired");
    }
}
//yeah but there