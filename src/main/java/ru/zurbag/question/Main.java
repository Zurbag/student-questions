package ru.zurbag.question;

import org.springframework.context.support.ClassPathXmlApplicationContext;
import ru.zurbag.question.service.QuizService;

public class Main {
    public static void main(String[] args) {
        ClassPathXmlApplicationContext context = new ClassPathXmlApplicationContext("/spring-context.xml");
        QuizService quizService = context.getBean(QuizService.class);
        quizService.startQuiz();
        context.close();
    }
}