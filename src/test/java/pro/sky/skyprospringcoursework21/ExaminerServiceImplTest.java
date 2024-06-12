package pro.sky.skyprospringcoursework21;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.*;
import java.util.stream.IntStream;

@ExtendWith(MockitoExtension.class)
public class ExaminerServiceImplTest {

    @Mock
    private JavaQuestionService javaQuestionServiceMock;

    @InjectMocks
    private ExaminerServiceImpl examinerServiceTest;

    public final Question questionOne = new Question("Какие два класса не наследуются от Object?",
            "Все классы наследуются от класса Object");
    public final Question questionTwo = new Question("Что такое Instance Variable?",
            "Instance Variable — переменная, которая определена внутри класса, и она " +
                    "существует вплоть до того момента, пока существует объект.");
    public final Question questionThree = new Question("Сколько классов можно наследовать",
            "Только один, множественное наследование в Java запрещено!");
    public Map<String, Question> questionMap = new HashMap<>();
    public  List<Question> questionList = new ArrayList<>(List.of(questionOne, questionTwo, questionThree));
    @Test
    public void getQuestionsTest(){
//        String key1 = "Какие два класса не наследуются от Object?/Все классы наследуются от класса Object";
//        String key2 ="Что такое Instance Variable?/Instance Variable — переменная, которая определена внутри класса, и она " +
//                "существует вплоть до того момента, пока существует объект.";
//        questionMap.put(key1, questionOne);
//        questionMap.put(key2, questionTwo);
        Mockito.when(javaQuestionServiceMock.getAll()).thenReturn(questionList);
//        Assertions.assertNotEquals(examinerServiceTest.getQuestions(2), questionList);
        Assertions.assertNotNull(examinerServiceTest.getQuestions(1));

    }
    @Test
    public void getQuestionsRandomNumberTest(){ //Не падает в бесконечный цикл, токоль когда в метод getQuestions подается число 1, все проверил, в баузере значения меняются, а сдесь падает в бесконечный цикл, что может быть не так?
        Mockito.when(javaQuestionServiceMock.getAll()).thenReturn(questionList);
        Assertions.assertNotEquals(examinerServiceTest.getQuestions(1), javaQuestionServiceMock.getAll());
    }



}
