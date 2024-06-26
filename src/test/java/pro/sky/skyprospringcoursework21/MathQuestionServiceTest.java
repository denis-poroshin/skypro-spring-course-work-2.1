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
    MathQuestionService mathQuestionService;

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
        mathQuestionService.add(questionOne);
        mathQuestionService.add(questionTwo);
        mathQuestionService.add(questionThree);

        Assertions.assertThrows(RecurringQuestionException.class,
                () -> mathQuestionService.add("Число, у которого нет собственного числа?",
                        "0"));
        Assertions.assertThrows(RecurringQuestionException.class,
                () -> mathQuestionService.add(questionOne));
    }
    @Test
    public void addTest(){
        mathQuestionService.add(questionOne);
        mathQuestionService.add(questionTwo);
        mathQuestionService.add(questionThree);

        int was = mathQuestionService.getAll().size();
        org.assertj.core.api.Assertions.assertThat(mathQuestionService.add(questionFour)).isEqualTo(questionFour);
        org.assertj.core.api.Assertions.assertThat(mathQuestionService.getAll()).hasSize(was + 1);
        org.assertj.core.api.Assertions.assertThat(mathQuestionService.getAll()).contains(questionFour);
        questionSet.add(questionFour);

        Mockito.when(mathematicsQuestionRepositoryMock.getAll()).thenReturn(questionSet);
        assertArrayEquals(mathQuestionService.getAll().toArray(), mathematicsQuestionRepositoryMock.getAll().toArray());
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
        mathQuestionService.add(questionOne);
        mathQuestionService.add(questionTwo);
        mathQuestionService.add(questionThree);
        int was = mathQuestionService.getAll().size();
        String question = "Число, у которого нет собственного числа?";
        String answer = "0";
        Question questionRemove = mathQuestionService.remove(question, answer);

        Assertions.assertEquals(questionOne, questionRemove);
        Assertions.assertFalse(mathQuestionService.getAll().contains(questionOne));
        questionSet.remove(questionOne);
//        javaQuestionServiceMock.remove(question, answer);
        org.assertj.core.api.Assertions.assertThat(mathQuestionService.getAll()).hasSize(was -1);
        Mockito.when(mathematicsQuestionRepositoryMock.getAll()).thenReturn(questionSet);
        assertArrayEquals(mathQuestionService.getAll().toArray(), mathematicsQuestionRepositoryMock.getAll().toArray());


    }

    @Test
    public void checkingTheMethodRemoveForException(){

        Assertions.assertThrows(QuestionNotFoundException.class,
                () -> mathQuestionService.remove("Число, у которого нет собственного числа?", "0"));
    }
    @Test
    public void getAllTest(){
        mathQuestionService.add(questionOne);
        mathQuestionService.add(questionTwo);
        mathQuestionService.add(questionThree);
//        org.assertj.core.api.Assertions.assertThat(javaQuestionServiceMock.getAll()).containsExactlyInAnyOrder(questionSet);

        Mockito.when(mathematicsQuestionRepositoryMock.getAll()).thenReturn(questionSet);
        assertArrayEquals(mathQuestionService.getAll().toArray(), mathematicsQuestionRepositoryMock.getAll().toArray());
    }
    @Test
    public void getRandomQuestionTest(){

        mathQuestionService.add("Число, у которого нет собственного числа?","0");
        mathQuestionService.add("Назовите единственное четное простое число",
                "Две");
        mathQuestionService.add("Каков фактический чистый номер после 7",
                "11");
        mathQuestionService.add("53 разделить на четыре сколько будет?","13");

        org.assertj.core.api.Assertions.assertThat(mathQuestionService.getAll().contains(mathQuestionService.getRandomQuestion()));

    }
    @Test
    public void getRandomQuestionExceptionTest(){
        mathQuestionService.add(questionOne);
        mathQuestionService.remove("Число, у которого нет собственного числа?","0");
        Assertions.assertThrows(QuestionsNullException.class,
                () -> mathQuestionService.getRandomQuestion());
    }


}
