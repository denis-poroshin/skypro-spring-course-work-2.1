package pro.sky.skyprospringcoursework21;


import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class JavaQuestionServiceTest {
    JavaQuestionService javaQuestionService = new JavaQuestionService();
    Question question;
    @BeforeEach
    public void setUp(){
        question = new Question("Какие два класса не наследуются от Object?", "Все классы наследуются от класса Object");
        javaQuestionService.add("Какие два класса не наследуются от Object?", "Все классы наследуются от класса Object");


    }
    @Test
    public void addTest(){
        Assertions.assertThrows(RecurringQuestionException.class,
                () -> javaQuestionService.add("Какие два класса не наследуются от Object?",
                        "Все классы наследуются от класса Object"));
    }
    @Test
    public void addObjectTest(){
        Assertions.assertThrows(RecurringQuestionException.class,
                () -> javaQuestionService.add(question));
    }
    @Test
    public void removeTest(){
        String question = "Какие два класса не наследуются от Object?";
        String answer = "Все классы наследуются от класса Object";
        Assertions.assertEquals(this.question, javaQuestionService.remove(question, answer));
        Assertions.assertFalse(javaQuestionService.getAll().contains(this.question));

    }

    @Test
    public void checkingTheMethodRemoveForException(){
        Assertions.assertThrows(QuestionNotFoundException.class,
                () -> javaQuestionService.remove("Какие два класса не наследуются от Object?", "Main, Applicator"));
    }
    @Test
    public void getAllTest(){
        org.assertj.core.api.Assertions.assertThat(javaQuestionService.getAll()).containsExactlyInAnyOrder(question);
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



//        assertNotEquals(javaQuestionService.g, javaQuestionService.getRandomQuestion());

    }



}
