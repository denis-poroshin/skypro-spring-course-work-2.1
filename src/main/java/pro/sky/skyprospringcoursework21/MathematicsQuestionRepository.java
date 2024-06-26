package pro.sky.skyprospringcoursework21;

import jakarta.annotation.PostConstruct;
import org.springframework.stereotype.Repository;

import java.util.Collection;
import java.util.Collections;
import java.util.HashSet;
import java.util.Set;
@Repository
public class MathematicsQuestionRepository implements QuestionRepository{
    private final Set<Question> setMathematicsOfQuestions = new HashSet<>();


//    @PostConstruct
//    public void init(){
//        add(new Question("Число, у которого нет собственного числа?",
//                "0"));
//        add(new Question("Назовите единственное четное простое число",
//                "Две"));
//        add(new Question("Каков фактический чистый номер после 7",
//                "11"));
//        add(new Question("53 разделить на четыре сколько будет?",
//                "13"));
//
//    }

    @Override
    public Question add(Question question) {
        setMathematicsOfQuestions.add(question);
        return question;
    }

    @Override
    public Question remove(Question question) {
        setMathematicsOfQuestions.remove(question);
        return question;

    }

    @Override
    public Collection<Question> getAll() {
        return Collections.unmodifiableCollection(setMathematicsOfQuestions); // создаст неизменяемую копию сета
    }
}
