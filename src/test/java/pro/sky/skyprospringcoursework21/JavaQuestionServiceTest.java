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
    JavaQuestionService javaQuestionServiceMock;
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
        javaQuestionServiceMock.add(questionOne);
        javaQuestionServiceMock.add(questionTwo);
        javaQuestionServiceMock.add(questionThree);

        Assertions.assertThrows(RecurringQuestionException.class,
                () -> javaQuestionServiceMock.add("Какие два класса не наследуются от Object?",
                        "Все классы наследуются от класса Object"));
        Assertions.assertThrows(RecurringQuestionException.class,
                () -> javaQuestionServiceMock.add(questionOne));
    }
    @Test
    public void addTest(){
        javaQuestionServiceMock.add(questionOne);
        javaQuestionServiceMock.add(questionTwo);
        javaQuestionServiceMock.add(questionThree);

        int was = javaQuestionServiceMock.getAll().size();
        org.assertj.core.api.Assertions.assertThat(javaQuestionServiceMock.add(questionFour)).isEqualTo(questionFour);
        org.assertj.core.api.Assertions.assertThat(javaQuestionServiceMock.getAll()).hasSize(was + 1);
        org.assertj.core.api.Assertions.assertThat(javaQuestionServiceMock.getAll()).contains(questionFour);
        questionSet.add(questionFour);

        Mockito.when(javaQuestionRepositoryMock.getAll()).thenReturn(questionSet);
        assertArrayEquals(javaQuestionServiceMock.getAll().toArray(), javaQuestionRepositoryMock.getAll().toArray());
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
        javaQuestionServiceMock.add(questionOne);
        javaQuestionServiceMock.add(questionTwo);
        javaQuestionServiceMock.add(questionThree);
        int was = javaQuestionServiceMock.getAll().size();
        String question = "Какие два класса не наследуются от Object?";
        String answer = "Все классы наследуются от класса Object";
        Question questionRemove = javaQuestionServiceMock.remove(question, answer);

        Assertions.assertEquals(questionOne, questionRemove);
        Assertions.assertFalse(javaQuestionServiceMock.getAll().contains(questionOne));
        questionSet.remove(questionOne);
//        javaQuestionServiceMock.remove(question, answer);
        org.assertj.core.api.Assertions.assertThat(javaQuestionServiceMock.getAll()).hasSize(was -1);
        Mockito.when(javaQuestionRepositoryMock.getAll()).thenReturn(questionSet);
        assertArrayEquals(javaQuestionServiceMock.getAll().toArray(), javaQuestionRepositoryMock.getAll().toArray());


    }

    @Test
    public void checkingTheMethodRemoveForException(){

        Assertions.assertThrows(QuestionNotFoundException.class,
                () -> javaQuestionServiceMock.remove("Какие два класса не наследуются от Object?", "Main, Applicator"));
    }
    @Test
    public void getAllTest(){
        javaQuestionServiceMock.add(questionOne);
        javaQuestionServiceMock.add(questionTwo);
        javaQuestionServiceMock.add(questionThree);
//        org.assertj.core.api.Assertions.assertThat(javaQuestionServiceMock.getAll()).containsExactlyInAnyOrder(questionSet);

        Mockito.when(javaQuestionRepositoryMock.getAll()).thenReturn(questionSet);
        assertArrayEquals(javaQuestionServiceMock.getAll().toArray(), javaQuestionRepositoryMock.getAll().toArray());
    }
    @Test
    public void getRandomQuestionTest(){
        javaQuestionServiceMock.add("Что такое Instance Variable?",
                "Instance Variable — переменная, которая определена внутри класса, и она " +
                        "существует вплоть до того момента, пока существует объект.");
        javaQuestionServiceMock.add("Сколько классов можно наследовать",
                "Только один, множественное наследование в Java запрещено!");
        javaQuestionServiceMock.add("Автоупаковка (autoboxing) и Автораспаковка (unboxing)",
                "Автоупаковка (autoboxing) - процесс автоматического преобразования из примитивного типа в соответствующий класс обертку.");
        javaQuestionServiceMock.add("Mutable", "Mutable называются объекты, чьи состояния и переменные можно изменить " +
                "после создания. Например такие классы, как StringBuilder, StringBuffer.");

        org.assertj.core.api.Assertions.assertThat(javaQuestionServiceMock.getAll().contains(javaQuestionServiceMock.getRandomQuestion()));

    }
    @Test
    public void getRandomQuestionExceptionTest(){
        javaQuestionServiceMock.add(questionOne);
        javaQuestionServiceMock.remove("Какие два класса не наследуются от Object?",
                "Все классы наследуются от класса Object");
        Assertions.assertThrows(QuestionsNullException.class,
                () -> javaQuestionServiceMock.getRandomQuestion());
    }




}
