package pro.sky.skyprospringcoursework21;

import java.util.Collection;
import java.util.Collections;

public interface ExaminerService {

    public Collection<Question> getQuestions(int amount);
}
