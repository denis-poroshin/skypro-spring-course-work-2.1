package pro.sky.skyprospringcoursework21;


import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.util.Assert;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

import static org.assertj.core.api.FactoryBasedNavigableIterableAssert.assertThat;
import static org.junit.jupiter.api.Assertions.*;
@ExtendWith(MockitoExtension.class)
public class JavaQuestionServiceTest {
    @Mock
    JavaQuestionRepository javaQuestionRepositoryMock;
    @InjectMocks
    JavaQuestionService javaQuestionService;
//    @InjectMocks
//    JavaQuestionRepository javaQuestionRepositoryMock;


    public final Question questionOne = new Question("Какие два класса не наследуются от Object?",
            "Все классы наследуются от класса Object");
    public final Question questionTwo = new Question("Что такое Instance Variable?",
            "Instance Variable — переменная, которая определена внутри класса, и она " +
                    "существует вплоть до того момента, пока существует объект.");
    public final Question questionThree = new Question("Сколько классов можно наследовать",
            "Только один, множественное наследование в Java запрещено!");
    public final Question questionFour = new Question("Автоупаковка (autoboxing) и Автораспаковка (unboxing)",
            "Автоупаковка (autoboxing) - процесс автоматического преобразования из примитивного типа в соответствующий класс обертку.");
    Set<Question> questionSet = new HashSet<>(List.of(questionOne, questionTwo, questionThree));

    @Test
    public void addExceptionTest(){
        javaQuestionService.add(questionOne);
        javaQuestionService.add(questionTwo);
        javaQuestionService.add(questionThree);

        Assertions.assertThrows(RecurringQuestionException.class,
                () -> javaQuestionService.add("Какие два класса не наследуются от Object?",
                        "Все классы наследуются от класса Object"));
        Assertions.assertThrows(RecurringQuestionException.class,
                () -> javaQuestionService.add(questionOne));
    }
    @Test
    public void addTest(){
        javaQuestionService.add(questionOne);
        javaQuestionService.add(questionTwo);
        javaQuestionService.add(questionThree);

        int was = javaQuestionService.getAll().size();
        org.assertj.core.api.Assertions.assertThat(javaQuestionService.add(questionFour)).isEqualTo(questionFour);
        org.assertj.core.api.Assertions.assertThat(javaQuestionService.getAll()).hasSize(was + 1);
        org.assertj.core.api.Assertions.assertThat(javaQuestionService.getAll()).contains(questionFour);
        questionSet.add(questionFour);

        Mockito.when(javaQuestionRepositoryMock.getAll()).thenReturn(questionSet);
        assertArrayEquals(javaQuestionService.getAll().toArray(), javaQuestionRepositoryMock.getAll().toArray());
    }
//    @Test
//    public void addObjectTest(){
////        Mockito.when(javaQuestionRepositoryMock.add(questionOne)).thenReturn(questionOne);
//        Mockito.when(javaQuestionRepositoryMock.getAll()).thenReturn(questionSet);
//        int expectedSize = 4; // То что ожидается от метода
//        int actual =
//        org.assertj.core.api.Assertions.assertThat()
//
//        Assertions.assertEquals(javaQuestionServiceMock.add(questionOne), javaQuestionRepositoryMock.add(questionOne));
//    }

    @Test
    public void removeTest(){
        javaQuestionService.add(questionOne);
        javaQuestionService.add(questionTwo);
        javaQuestionService.add(questionThree);
        int was = javaQuestionService.getAll().size();
        String question = "Какие два класса не наследуются от Object?";
        String answer = "Все классы наследуются от класса Object";
        Question questionRemove = javaQuestionService.remove(question, answer);

        Assertions.assertEquals(questionOne, questionRemove);
        Assertions.assertFalse(javaQuestionService.getAll().contains(questionOne));
        questionSet.remove(questionOne);
//        javaQuestionServiceMock.remove(question, answer);
        org.assertj.core.api.Assertions.assertThat(javaQuestionService.getAll()).hasSize(was -1);
        Mockito.when(javaQuestionRepositoryMock.getAll()).thenReturn(questionSet);
        assertArrayEquals(javaQuestionService.getAll().toArray(), javaQuestionRepositoryMock.getAll().toArray());


    }

    @Test
    public void checkingTheMethodRemoveForException(){

        Assertions.assertThrows(QuestionNotFoundException.class,
                () -> javaQuestionService.remove("Какие два класса не наследуются от Object?", "Main, Applicator"));
    }
    @Test
    public void getAllTest(){
        javaQuestionService.add(questionOne);
        javaQuestionService.add(questionTwo);
        javaQuestionService.add(questionThree);
//        org.assertj.core.api.Assertions.assertThat(javaQuestionServiceMock.getAll()).containsExactlyInAnyOrder(questionSet);

        Mockito.when(javaQuestionRepositoryMock.getAll()).thenReturn(questionSet);
        assertArrayEquals(javaQuestionService.getAll().toArray(), javaQuestionRepositoryMock.getAll().toArray());
    }
    @Test
    public void getRandomQuestionTest(){
        javaQuestionService.add("Что такое Instance Variable?",
                "Instance Variable — переменная, которая определена внутри класса, и она " +
                        "существует вплоть до того момента, пока существует объект.");
        javaQuestionService.add("Сколько классов можно наследовать",
                "Только один, множественное наследование в Java запрещено!");
        javaQuestionService.add("Автоупаковка (autoboxing) и Автораспаковка (unboxing)",
                "Автоупаковка (autoboxing) - процесс автоматического преобразования из примитивного типа в соответствующий класс обертку.");
        javaQuestionService.add("Mutable", "Mutable называются объекты, чьи состояния и переменные можно изменить " +
                "после создания. Например такие классы, как StringBuilder, StringBuffer.");

        org.assertj.core.api.Assertions.assertThat(javaQuestionService.getAll().contains(javaQuestionService.getRandomQuestion()));

    }
    @Test
    public void getRandomQuestionExceptionTest(){
        javaQuestionService.add(questionOne);
        javaQuestionService.remove("Какие два класса не наследуются от Object?",
                "Все классы наследуются от класса Object");
        Assertions.assertThrows(QuestionsNullException.class,
                () -> javaQuestionService.getRandomQuestion());
    }




}
