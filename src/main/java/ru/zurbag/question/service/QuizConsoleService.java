package ru.zurbag.question.service;

import java.util.Scanner;

public interface QuizConsoleService {
    int chooseCategory(Scanner scanner);
    String getUserAnswer(Scanner scanner, int questionNumber, String questionText);
    void showResult(int correctCount, int totalQuestions);
    void showCorrectAnswer();
    void showWrongAnswer(String correctAnswer);
    void showWelcomeMessage();
}