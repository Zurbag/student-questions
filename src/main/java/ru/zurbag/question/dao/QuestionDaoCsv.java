package ru.zurbag.question.dao;

import org.springframework.core.io.ClassPathResource;
import ru.zurbag.question.domain.Question;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

public class QuestionDaoCsv implements QuestionDao {

    private final String csvResourcePath;

    public QuestionDaoCsv(String csvResourcePath) {
        this.csvResourcePath = csvResourcePath;
    }

    @Override
    public List<Question> findAll() {
        List<Question> questions = new ArrayList<>();
        try (BufferedReader br = new BufferedReader(
                new InputStreamReader(new ClassPathResource(csvResourcePath).getInputStream()))) {
            String line;
            while ((line = br.readLine()) != null) {
                String[] parts = line.split(";");
                if (parts.length >= 2) {
                    questions.add(new Question(parts[0].trim(), parts[1].trim()));
                }
            }
        } catch (Exception e) {
            throw new RuntimeException("Error loading CSV", e);
        }
        return questions;
    }
}
