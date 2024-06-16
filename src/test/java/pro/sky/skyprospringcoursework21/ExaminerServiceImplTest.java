package pro.sky.skyprospringcoursework21;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.*;


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
    public final Question questionFour = new Question("Автоупаковка (autoboxing) и Автораспаковка (unboxing)",
            "Автоупаковка (autoboxing) - процесс автоматического преобразования из примитивного типа в соответствующий класс обертку.");
    public final Question questionFive = new Question("Mutable", "Mutable называются объекты, чьи состояния и переменные можно изменить " +
            "после создания. Например такие классы, как StringBuilder, StringBuffer.");
//    public final Question questionSix = new Question("Immutable",
//            "Immutable называются объекты, состояния и переменные которых нельзя изменить после создания объекта. " +
//                    "Чем не отличный ключ для HashMap, да?) Например, String, Integer, Double и так далее.");

    public  List<Question> questionList = new ArrayList<>(List.of(questionOne, questionTwo, questionThree, questionFour, questionFive));

    @Test
    public void getQuestionsTest(){
        Mockito.when(javaQuestionServiceMock.getRandomQuestion()).thenReturn(questionTwo, questionThree, questionFour, questionFive);
        Assertions.assertNotNull(examinerServiceTest.getQuestions(5));
    }
    @Test
    public void getQuestionsRandomNumberTest(){
        Mockito.when(javaQuestionServiceMock.getRandomQuestion()).thenReturn(questionOne, questionTwo, questionThree, questionFour, questionFive);
        Assertions.assertNotEquals(examinerServiceTest.getQuestions(5), questionList);
    }
    @Test
    public void getQuestionsJealousyTest(){
        Mockito.when(javaQuestionServiceMock.getRandomQuestion()).thenReturn(questionOne, questionTwo, questionThree, questionFour, questionFive);

        org.assertj.core.api.Assertions.assertThat(examinerServiceTest.getQuestions(5).containsAll(questionList));
    }
    @Test
    public void getQuestionsDuplicate(){
        Question questionFiveDuplicate = new Question("Mutable", "Mutable называются объекты, чьи состояния и переменные можно изменить " +
                "после создания. Например такие классы, как StringBuilder, StringBuffer.");
        Mockito.when(javaQuestionServiceMock.getRandomQuestion()).thenReturn(questionOne,questionFiveDuplicate, questionTwo, questionThree, questionFour, questionFive);
        org.assertj.core.api.Assertions.assertThat(examinerServiceTest.getQuestions(5).containsAll(questionList));
        System.out.println(examinerServiceTest.getQuestions(5));
    }




}
