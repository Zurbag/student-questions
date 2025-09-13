package ru.zurbag.question;

import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import ru.zurbag.question.config.AppConfig;
import ru.zurbag.question.service.QuizService;

public class Main {
    public static void main(String[] args) {
        AnnotationConfigApplicationContext context =
                new AnnotationConfigApplicationContext(AppConfig.class);
        QuizService quizService = context.getBean(QuizService.class);
        quizService.startQuiz();
        context.close();
    }
}