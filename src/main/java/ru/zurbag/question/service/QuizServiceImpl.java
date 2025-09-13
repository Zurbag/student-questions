package ru.zurbag.question.service;

import org.springframework.stereotype.Service;
import ru.zurbag.question.dao.QuestionDao;
import ru.zurbag.question.domain.Question;
import java.util.List;
import java.util.Scanner;
import java.util.stream.Collectors;

@Service
public class QuizServiceImpl implements QuizService {

    private final QuestionDao questionDao;

    public QuizServiceImpl(QuestionDao questionDao) {
        this.questionDao = questionDao;
    }

    @Override
    public void startQuiz() {
        List<Question> allQuestions = questionDao.findAll();
        Scanner scanner = new Scanner(System.in);

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

        String selectedCategory = (categoryChoice == 1) ? "astronomy" : "sport";

        List<Question> questions = allQuestions.stream()
                .filter(q -> q.getCategory().equals(selectedCategory))
                .collect(Collectors.toList());

        int correctCount = 0;
        System.out.println("Answer 'yes' or 'no':");

        for (int i = 0; i < questions.size(); i++) {
            Question question = questions.get(i);
            System.out.println("\nQuestion " + (i + 1) + ": " + question.getText());
            System.out.print("Your answer: ");
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