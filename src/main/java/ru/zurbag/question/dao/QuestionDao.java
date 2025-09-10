package ru.zurbag.question.dao;

import ru.zurbag.question.domain.Question;
import java.util.List;

public interface QuestionDao {
    List<Question> findAll();
}