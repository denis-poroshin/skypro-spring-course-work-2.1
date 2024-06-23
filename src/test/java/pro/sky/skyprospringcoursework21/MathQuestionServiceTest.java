package pro.sky.skyprospringcoursework21;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

import static org.junit.jupiter.api.Assertions.assertArrayEquals;

@ExtendWith(MockitoExtension.class)
public class MathQuestionServiceTest {
    @Mock
    MathematicsQuestionRepository mathematicsQuestionRepositoryMock;
    @InjectMocks
    MathQuestionService mathQuestionServiceMock;

    Question questionOne = new Question("Число, у которого нет собственного числа?",
                        "0");
    Question questionTwo = new Question("Назовите единственное четное простое число",
            "Две");
    Question questionThree = new Question("Каков фактический чистый номер после 7",
            "11");
    Question questionFour = new Question("53 разделить на четыре сколько будет?","13");
    Set<Question> questionSet = new HashSet<>(List.of(questionOne, questionTwo, questionThree));
    @Test
    public void addExceptionTest(){
        mathQuestionServiceMock.add(questionOne);
        mathQuestionServiceMock.add(questionTwo);
        mathQuestionServiceMock.add(questionThree);

        Assertions.assertThrows(RecurringQuestionException.class,
                () -> mathQuestionServiceMock.add("Число, у которого нет собственного числа?",
                        "0"));
        Assertions.assertThrows(RecurringQuestionException.class,
                () -> mathQuestionServiceMock.add(questionOne));
    }
    @Test
    public void addTest(){
        mathQuestionServiceMock.add(questionOne);
        mathQuestionServiceMock.add(questionTwo);
        mathQuestionServiceMock.add(questionThree);

        int was = mathQuestionServiceMock.getAll().size();
        org.assertj.core.api.Assertions.assertThat(mathQuestionServiceMock.add(questionFour)).isEqualTo(questionFour);
        org.assertj.core.api.Assertions.assertThat(mathQuestionServiceMock.getAll()).hasSize(was + 1);
        org.assertj.core.api.Assertions.assertThat(mathQuestionServiceMock.getAll()).contains(questionFour);
        questionSet.add(questionFour);

        Mockito.when(mathematicsQuestionRepositoryMock.getAll()).thenReturn(questionSet);
        assertArrayEquals(mathQuestionServiceMock.getAll().toArray(), mathematicsQuestionRepositoryMock.getAll().toArray());
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
        mathQuestionServiceMock.add(questionOne);
        mathQuestionServiceMock.add(questionTwo);
        mathQuestionServiceMock.add(questionThree);
        int was = mathQuestionServiceMock.getAll().size();
        String question = "Число, у которого нет собственного числа?";
        String answer = "0";
        Question questionRemove = mathQuestionServiceMock.remove(question, answer);

        Assertions.assertEquals(questionOne, questionRemove);
        Assertions.assertFalse(mathQuestionServiceMock.getAll().contains(questionOne));
        questionSet.remove(questionOne);
//        javaQuestionServiceMock.remove(question, answer);
        org.assertj.core.api.Assertions.assertThat(mathQuestionServiceMock.getAll()).hasSize(was -1);
        Mockito.when(mathematicsQuestionRepositoryMock.getAll()).thenReturn(questionSet);
        assertArrayEquals(mathQuestionServiceMock.getAll().toArray(), mathematicsQuestionRepositoryMock.getAll().toArray());


    }

    @Test
    public void checkingTheMethodRemoveForException(){

        Assertions.assertThrows(QuestionNotFoundException.class,
                () -> mathQuestionServiceMock.remove("Число, у которого нет собственного числа?", "0"));
    }
    @Test
    public void getAllTest(){
        mathQuestionServiceMock.add(questionOne);
        mathQuestionServiceMock.add(questionTwo);
        mathQuestionServiceMock.add(questionThree);
//        org.assertj.core.api.Assertions.assertThat(javaQuestionServiceMock.getAll()).containsExactlyInAnyOrder(questionSet);

        Mockito.when(mathematicsQuestionRepositoryMock.getAll()).thenReturn(questionSet);
        assertArrayEquals(mathQuestionServiceMock.getAll().toArray(), mathematicsQuestionRepositoryMock.getAll().toArray());
    }
    @Test
    public void getRandomQuestionTest(){

        mathQuestionServiceMock.add("Число, у которого нет собственного числа?","0");
        mathQuestionServiceMock.add("Назовите единственное четное простое число",
                "Две");
        mathQuestionServiceMock.add("Каков фактический чистый номер после 7",
                "11");
        mathQuestionServiceMock.add("53 разделить на четыре сколько будет?","13");

        org.assertj.core.api.Assertions.assertThat(mathQuestionServiceMock.getAll().contains(mathQuestionServiceMock.getRandomQuestion()));

    }
    @Test
    public void getRandomQuestionExceptionTest(){
        mathQuestionServiceMock.add(questionOne);
        mathQuestionServiceMock.remove("Число, у которого нет собственного числа?","0");
        Assertions.assertThrows(QuestionsNullException.class,
                () -> mathQuestionServiceMock.getRandomQuestion());
    }


}
