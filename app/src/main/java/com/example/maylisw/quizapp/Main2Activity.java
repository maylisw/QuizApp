package com.example.maylisw.quizapp;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class Main2Activity extends AppCompatActivity implements View.OnClickListener{
    private Button playAgain;
    private TextView scoreboard;
    private int score;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);
        SharedPreferences sharedPref = this.getPreferences(Context.MODE_PRIVATE);
        score = sharedPref.getInt("score", 0);
        wireMeUp();
        playAgain.setOnClickListener(this);
    }

    public void wireMeUp() {
        playAgain = (Button) findViewById(R.id.play_again);
        scoreboard = (TextView) findViewById(R.id.score);
        scoreboard.setText(getString(R.string.score) +": " + score);
        playAgain.setText(getString(R.string.playAgain));
    }

    @Override
    public void onClick(View view) {
        Intent i = new Intent(Main2Activity.this, MainActivity.class);
        startActivity(i);
    }
}
