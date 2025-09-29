package ru.zurbag.question.service.impl;

import org.springframework.stereotype.Service;
import ru.zurbag.question.dao.QuestionDao;
import ru.zurbag.question.domain.Question;
import ru.zurbag.question.service.QuizConsoleService;
import ru.zurbag.question.service.QuizService;
import ru.zurbag.question.service.QuizValidationService;

import java.util.List;
import java.util.Scanner;

@Service
public class QuizServiceImpl implements QuizService {

    private final QuestionDao questionDao;
    private final QuizValidationService validationService;
    private final QuizConsoleService consoleService;

    public QuizServiceImpl(QuestionDao questionDao,
                           QuizValidationService validationService,
                           QuizConsoleService consoleService) {
        this.questionDao = questionDao;
        this.validationService = validationService;
        this.consoleService = consoleService;
    }

    @Override
    public void startQuiz() {
        List<Question> allQuestions = questionDao.findAll();
        Scanner scanner = new Scanner(System.in);

        int categoryChoice = consoleService.chooseCategory(scanner);
        String selectedCategory = (categoryChoice == 1) ? "astronomy" : "sport";

        List<Question> questions = validationService.filterQuestionsByCategory(allQuestions, selectedCategory);
        int correctCount = 0;

        consoleService.showWelcomeMessage();

        for (int i = 0; i < questions.size(); i++) {
            Question question = questions.get(i);
            String userAnswer = consoleService.getUserAnswer(scanner, i + 1, question.getText());


            if (validationService.isAnswerCorrect(userAnswer, question.getAnswer())) {
                consoleService.showCorrectAnswer();
                correctCount++;
            } else {
                consoleService.showWrongAnswer(question.getAnswer());
            }
        }

        consoleService.showResult(correctCount, questions.size());
    }
}