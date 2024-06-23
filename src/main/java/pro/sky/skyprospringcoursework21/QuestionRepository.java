package pro.sky.skyprospringcoursework21;

import java.util.Collection;

public interface QuestionRepository {
//    Question add(String question, String answer);

    Question add(Question question);

    Question remove(Question question);
    Collection<Question> getAll();
//    Question getRandomQuestion();



}
