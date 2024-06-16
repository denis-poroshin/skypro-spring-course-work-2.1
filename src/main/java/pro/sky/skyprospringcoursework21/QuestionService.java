package pro.sky.skyprospringcoursework21;

import java.util.Collection;
import java.util.Collections;
import java.util.Random;

public interface QuestionService {
    Question add(String question, String answer);

    Question add(Question question);

    Question remove(String question, String answer);
    Collection<Question> getAll();
    Question getRandomQuestion();


}
