import org.junit.jupiter.api.Test;
import ru.zurbag.question.dao.QuestionDaoCsv;
import ru.zurbag.question.domain.Question;

import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

import static org.junit.jupiter.api.Assertions.*;

class QuestionDaoCsvTest {

    @Test
    void testCsvFileReading() {
        QuestionDaoCsv dao = new QuestionDaoCsv("questions.csv");

        List<Question> questions = dao.findAll();

        assertNotNull(questions, "Список вопросов не должен быть null");
        assertFalse(questions.isEmpty(), "Список вопросов не должен быть пустым");

        Question firstQuestion = questions.get(0);
        assertNotNull(firstQuestion.getText(), "Текст вопроса не должен быть null");
        assertNotNull(firstQuestion.getAnswer(), "Ответ не должен быть null");

        assertNotNull(questions, "Список вопросов не должен быть null");
        assertFalse(questions.isEmpty(), "Список вопросов не должен быть пустым");
        assertEquals(10, questions.size(), "Должно быть 10 вопросов в файле");

    }

    @Test
    void testCsvCategoryFileReading() {
        QuestionDaoCsv dao = new QuestionDaoCsv("questions.csv");
        List<Question> questions = dao.findAll();
        Set<String> categories = questions.stream()
                .map(Question::getCategory)
                .collect(Collectors.toSet());
        assertTrue(categories.contains("astronomy"));
        assertTrue(categories.contains("sport"));
    }


}