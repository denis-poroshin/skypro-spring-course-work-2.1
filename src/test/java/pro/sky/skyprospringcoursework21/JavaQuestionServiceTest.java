package pro.sky.skyprospringcoursework21;


import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.provider.Arguments;
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
    public void removeTest(){
        String question = "Какие два класса не наследуются от Object?";
        String answer = "Все классы наследуются от класса Object";
        Assertions.assertEquals(this.question, javaQuestionService.remove(question, answer));
    }

    @Test
    public void checkingTheMethodRemoveForException(){
        Assertions.assertThrows(QuestionNotFoundException.class,
                () -> javaQuestionService.remove("Какие два класса не наследуются от Object?", "Main, Applicator"));
    }



}
