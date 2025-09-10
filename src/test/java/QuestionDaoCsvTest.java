import org.junit.jupiter.api.Test;
import ru.zurbag.question.dao.QuestionDaoCsv;
import ru.zurbag.question.domain.Question;

import java.util.List;

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

        assertEquals(5, questions.size(), "Должно быть 5 вопросов в файле");

    }
}