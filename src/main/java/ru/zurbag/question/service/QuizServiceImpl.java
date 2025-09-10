package ru.zurbag.question.service;

import ru.zurbag.question.dao.QuestionDao;
import ru.zurbag.question.domain.Question;
import java.util.List;
import java.util.Scanner;

public class QuizServiceImpl implements QuizService {

    private final QuestionDao questionDao;

    public QuizServiceImpl(QuestionDao questionDao) {
        this.questionDao = questionDao;
    }

    @Override
    public void startQuiz() {
        List<Question> questions = questionDao.findAll();
        Scanner scanner = new Scanner(System.in);
        int correctCount = 0;

        System.out.println("Answer 'yes' or 'no':");

        for (int i = 0; i < questions.size(); i++) {
            Question question = questions.get(i);
            System.out.println("\nQuestion " + (i + 1) + ": " + question.getText());
            String userAnswer = scanner.nextLine().trim().toLowerCase();

            if (userAnswer.equals(question.getAnswer().toLowerCase())) {
                System.out.println("Correct!");
                correctCount++;
            } else {
                System.out.println("Wrong! Correct answer: " + question.getAnswer());
            }
        }

        System.out.println("\nResult: " + correctCount + " out of " + questions.size() + " correct");
    }
}