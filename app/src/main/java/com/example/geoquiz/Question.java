package com.example.geoquiz;
public class Question {
    private int question;
    boolean answer;

    public Question(int question, boolean answer) {
        this.question = question;
        this.answer = answer;
    }

    public int getQuestion() {
        return question;
    }

    public void setQuestion(int question) {
        this.question = question;
    }

    public boolean isAnswer() {
        return answer;
    }

    public void setAnswer(boolean answer) {
        this.answer = answer;
    }
}
