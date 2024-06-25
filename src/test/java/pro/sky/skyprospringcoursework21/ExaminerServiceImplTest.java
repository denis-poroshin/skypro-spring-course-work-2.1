package pro.sky.skyprospringcoursework21;

import org.assertj.core.api.AbstractBooleanAssert;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Qualifier;

import java.util.*;


@ExtendWith(MockitoExtension.class)
public class ExaminerServiceImplTest {
    @Mock
    private MathQuestionService mathQuestionServiceMock;

    @Mock
    private JavaQuestionService javaQuestionServiceMock;

    private ExaminerServiceImpl examinerServiceTest;
    @BeforeEach
    public void injectExaminerServiceTest(){
        this.examinerServiceTest = new ExaminerServiceImpl(mathQuestionServiceMock, javaQuestionServiceMock);
    }


//    private final ExaminerServiceImpl examinerServiceTest = new ExaminerServiceImpl(mathQuestionServiceMock, javaQuestionServiceMock);
//    @BeforeEach
//    public void prprww(){
//        Question questionOne = new Question("Какие два класса не наследуются от Object?",
//                "Все классы наследуются от класса Object");
//        Question questionTwo = new Question("Что такое Instance Variable?",
//                "Instance Variable — переменная, которая определена внутри класса, и она " +
//                        "существует вплоть до того момента, пока существует объект.");
//        Question questionThree = new Question("Сколько классов можно наследовать",
//                "Только один, множественное наследование в Java запрещено!");
//        Question questionFour = new Question("Автоупаковка (autoboxing) и Автораспаковка (unboxing)",
//                "Автоупаковка (autoboxing) - процесс автоматического преобразования из примитивного типа в соответствующий класс обертку.");
//        Question questionFive = new Question("Mutable", "Mutable называются объекты, чьи состояния и переменные можно изменить " +
//                "после создания. Например такие классы, как StringBuilder, StringBuffer.");
////    public final Question questionSix = new Question("Immutable",
////            "Immutable называются объекты, состояния и переменные которых нельзя изменить после создания объекта. " +
////                    "Чем не отличный ключ для HashMap, да?) Например, String, Integer, Double и так далее.");
//        Question questionSix = new Question("Число, у которого нет собственного числа?",
//                "0");
//        Question questionSeven = new Question("Назовите единственное четное простое число",
//                "Две");
//        Question questionEight = new Question("Каков фактический чистый номер после 7",
//                "11");
//        Question questionI = new Question("53 разделить на четыре сколько будет?","13");
//        Set<Question> questionSetMath = new HashSet<>(List.of(questionSix, questionSeven, questionEight, questionI));
//
//        Set<Question> questionSetJava = new HashSet<>(List.of(questionOne, questionTwo, questionThree, questionFour, questionFive));
//    }
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
    private final Question questionSix = new Question("Число, у которого нет собственного числа?",
            "0");
    private final Question questionSeven = new Question("Назовите единственное четное простое число",
            "Две");
    private final Question questionEight = new Question("Каков фактический чистый номер после 7",
            "11");
    private final Question questionI = new Question("53 разделить на четыре сколько будет?","13");
    public Set<Question> questionSetMath = new HashSet<>(List.of(questionSix, questionSeven, questionEight, questionI));

    public  Set<Question> questionSetJava = new HashSet<>(List.of(questionOne, questionTwo, questionThree, questionFour, questionFive));





    @Test
    public void getQuestionsJavaTest(){
//        Question questionFiveDuplicate = new Question("Mutable", "Mutable называются объекты, чьи состояния и переменные можно изменить " +
//                "после создания. Например такие классы, как StringBuilder, StringBuffer.");
//        questionSetJava.add(questionFiveDuplicate);
//
//        Mockito.when(javaQuestionServiceMock.getRandomQuestion()).thenReturn(questionOne, questionFiveDuplicate, questionTwo, questionThree, questionFour, questionFive);
//        Mockito.when(javaQuestionServiceMock.getAll()).thenReturn(questionSetJava);
//
//        int expectedSize = 3; // То что ожидается от метода
//        int actualSize = examinerServiceTest.getQuestions(3).size(); // То что получили на самом деле от метода

//        Assertions.assertEquals(expectedSize, actualSize);
//        org.assertj.core.api.Assertions.assertThat(examinerServiceTest.getQuestions(3).containsAll(questionSetJava));
        Mockito.when(javaQuestionServiceMock.getRandomQuestion()).thenReturn(
                questionOne,
                questionTwo,
                questionThree,
                questionFour,
                questionFive,
                questionThree,
                questionOne

        );
        Mockito.when(javaQuestionServiceMock.getAll()).thenReturn(questionSetJava);
        org.assertj.core.api.Assertions.assertThat(examinerServiceTest.getQuestions(3)).containsExactlyInAnyOrder(
                questionOne,
                questionTwo,
                questionThree
        );

    }
    @Test //падает в ошибку
    public void getQuestionsMathTest(){
        Mockito.when(mathQuestionServiceMock.getRandomQuestion()).thenReturn(
                questionSix,
                questionSeven,
                questionEight,
                questionI,
                questionSeven,
                questionI,
                questionEight);
        Mockito.when(mathQuestionServiceMock.getAll()).thenReturn(questionSetMath);

        org.assertj.core.api.Assertions.assertThat(examinerServiceTest.getQuestions(4)).containsExactlyInAnyOrder(
                questionSix,
                questionSeven,
                questionEight,
                questionI
        );


    }

    @Test
    public void getQuestionsJealousyTest(){
        Mockito.when(javaQuestionServiceMock.getRandomQuestion()).thenReturn(questionOne, questionTwo, questionThree, questionFour, questionFive);
        Mockito.when(javaQuestionServiceMock.getAll()).thenReturn(questionSetJava);


        org.assertj.core.api.Assertions.assertThat(examinerServiceTest.getQuestions(5).containsAll(questionSetJava));

    }
    @Test
    public void getQuestionsExceptionTest(){
        Assertions.assertThrows(IncorrectInputException.class,
                () -> examinerServiceTest.getQuestions(0));
        Assertions.assertThrows(IncorrectInputException.class,
                () -> examinerServiceTest.getQuestions(7));
    }
    @Test // также падает в бесконечный цикл
    public void gettingAQuestionOnJavaAndMathematics(){
        Mockito.when(javaQuestionServiceMock.getRandomQuestion()).thenReturn(
                questionOne,
                questionTwo,
                questionThree,
                questionFour,
                questionFive,
                questionThree,
                questionOne
        );
        Mockito.when(javaQuestionServiceMock.getAll()).thenReturn(questionSetJava);

        Mockito.when(mathQuestionServiceMock.getRandomQuestion()).thenReturn(
                questionSix,
                questionSeven,
                questionEight,
                questionI,
                questionSeven,
                questionI,
                questionEight);
        Mockito.when(mathQuestionServiceMock.getAll()).thenReturn(questionSetMath);

        org.assertj.core.api.Assertions.assertThat(examinerServiceTest.getQuestions(8)).containsExactlyInAnyOrder(
                questionSix,
                questionSeven,
                questionEight,
                questionI,
                questionOne,
                questionTwo,
                questionThree,
                questionFour

        );

    }


}
