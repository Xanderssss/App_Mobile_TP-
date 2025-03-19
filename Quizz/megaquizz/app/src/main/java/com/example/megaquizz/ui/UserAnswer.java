package com.example.megaquizz.ui; // Doit être bien présent en haut du fichier !

import java.io.Serializable;

public class UserAnswer implements Serializable {
    private final String question;
    private final String userAnswer;
    private final boolean isCorrect;

    public UserAnswer(String question, String userAnswer, boolean isCorrect) {
        this.question = question;
        this.userAnswer = userAnswer;
        this.isCorrect = isCorrect;
    }

    public String getQuestion() {
        return question;
    }

    public String getUserAnswer() {
        return userAnswer;
    }

    public boolean isCorrect() {
        return isCorrect;
    }
}



