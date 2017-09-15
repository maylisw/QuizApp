package com.example.maylisw.quizapp;

/**
 * Created by per6 on 9/15/17.
 */

public class Questions {
    private String questionText;
    private boolean answer;

    public Questions(String questionText, boolean answer) {
        this.questionText = questionText;
        this.answer = answer;
    }

    public String getQuestionText() {
        return questionText;
    }

    public void setQuestionText(String questionText) {
        this.questionText = questionText;
    }

    public boolean isAnswer() {
        return answer;
    }

    public void setAnswer(boolean answer) {
        this.answer = answer;
    }

    public boolean checkAnswer(boolean userAns) {
        return answer == userAns;
    }
}
