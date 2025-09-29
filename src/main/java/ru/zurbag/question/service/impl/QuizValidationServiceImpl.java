package ru.zurbag.question.service.impl;

import org.springframework.stereotype.Service;
import ru.zurbag.question.domain.Question;
import ru.zurbag.question.service.QuizValidationService;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class QuizValidationServiceImpl implements QuizValidationService {

    @Override
    public boolean isAnswerCorrect(String userAnswer, String correctAnswer) {
        return userAnswer.equalsIgnoreCase(correctAnswer);
    }

    @Override
    public List<Question> filterQuestionsByCategory(List<Question> allQuestions, String category) {
        return allQuestions.stream()
                .filter(q -> q.getCategory().equals(category))
                .collect(Collectors.toList());
    }
}