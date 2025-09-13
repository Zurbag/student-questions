package ru.zurbag.question.domain;

public class Question {
    private final String category;
    private final String text;
    private final String answer;

    public Question(String category, String text, String answer) {
        this.category = category;
        this.text = text;
        this.answer = answer;
    }

    public String getCategory() { return category; }
    public String getText() { return text; }
    public String getAnswer() { return answer; }
}