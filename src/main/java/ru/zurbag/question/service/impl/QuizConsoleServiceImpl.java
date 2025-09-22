package ru.zurbag.question.service.impl;

import org.springframework.stereotype.Service;
import ru.zurbag.question.service.QuizConsoleService;

import java.util.Scanner;
@Service
public class QuizConsoleServiceImpl implements QuizConsoleService {

    @Override
    public int chooseCategory(Scanner scanner) {
        System.out.println("Change category:");
        System.out.println("1. Astronomy");
        System.out.println("2. Sport");
        System.out.print("Input number (1 or 2): ");

        int categoryChoice = 0;
        while (categoryChoice != 1 && categoryChoice != 2) {
            try {
                categoryChoice = Integer.parseInt(scanner.nextLine());
                if (categoryChoice != 1 && categoryChoice != 2) {
                    System.out.print("Input number (1 or 2): ");
                }
            } catch (NumberFormatException e) {
                System.out.print("Input correct number (1 or 2): ");
            }
        }
        return categoryChoice;
    }

    @Override
    public String getUserAnswer(Scanner scanner, int questionNumber, String questionText) {
        System.out.println("\nQuestion " + questionNumber + ": " + questionText);
        System.out.print("Your answer: ");
        return scanner.nextLine().trim().toLowerCase();
    }

    @Override
    public void showResult(int correctCount, int totalQuestions) {
        System.out.println("\nResult: " + correctCount + " out of " + totalQuestions + " correct");
    }

    @Override
    public void showCorrectAnswer() {
        System.out.println("Correct!");
    }

    @Override
    public void showWrongAnswer(String correctAnswer) {
        System.out.println("Wrong! Correct answer: " + correctAnswer);
    }

    @Override
    public void showWelcomeMessage() {
        System.out.println("Answer 'yes', 'no' or 'maybe': ");
    }
}