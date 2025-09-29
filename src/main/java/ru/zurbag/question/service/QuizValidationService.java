package ru.zurbag.question.service;

import ru.zurbag.question.domain.Question;
import java.util.List;

public interface QuizValidationService {
    boolean isAnswerCorrect(String userAnswer, String correctAnswer);
    List<Question> filterQuestionsByCategory(List<Question> allQuestions, String category);
}