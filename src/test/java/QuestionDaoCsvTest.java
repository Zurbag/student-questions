import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.ClassPathResource;
import org.springframework.test.context.junit.jupiter.SpringJUnitConfig;
import ru.zurbag.question.dao.QuestionDaoCsv;
import ru.zurbag.question.domain.Question;

import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

import static org.junit.jupiter.api.Assertions.*;

@SpringJUnitConfig(TestConfig.class)
class QuestionDaoCsvTest {

    @Value("${csv.file.path.test}")
    private String csvFilePathTest;

    @Value("${csv.file.path.main}")
    private String csvFilePathMain;

    @Test
    void testCsvFileExistsInTestResources() {
        ClassPathResource resource = new ClassPathResource(csvFilePathTest);
        assertTrue(resource.exists(), "В тестовых ресурсах ожидается файл: "+csvFilePathTest);
    }

    @Test
    void testCsvFileExistsInMainResources() {
        ClassPathResource resource = new ClassPathResource(csvFilePathMain);
        assertTrue(resource.exists(), "В основных ресурсах ожидается файл: " + csvFilePathMain);
    }


    @Test
    void testCsvFileReading() {
        QuestionDaoCsv dao = new QuestionDaoCsv(csvFilePathMain);

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
        QuestionDaoCsv dao = new QuestionDaoCsv(csvFilePathMain);
        List<Question> questions = dao.findAll();
        Set<String> categories = questions.stream()
                .map(Question::getCategory)
                .collect(Collectors.toSet());
        assertTrue(categories.contains("astronomy"));
        assertTrue(categories.contains("sport"));
    }


}