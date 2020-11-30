package com.example.musicstore.bean;

public class ReportRequestBean extends RequestBean {

    private int score;
    private String completeTime;
    private String exerciseAnswer;

    public int getScore() {
        return score;
    }

    public void setScore(int score) {
        this.score = score;
    }

    public String getCompleteTime() {
        return completeTime;
    }

    public void setCompleteTime(String completeTime) {
        this.completeTime = completeTime;
    }

    public String getExerciseAnswer() {
        return exerciseAnswer;
    }

    public void setExerciseAnswer(String exerciseAnswer) {
        this.exerciseAnswer = exerciseAnswer;
    }
}
