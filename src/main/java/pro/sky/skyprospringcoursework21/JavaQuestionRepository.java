package pro.sky.skyprospringcoursework21;

import jakarta.annotation.PostConstruct;
import org.springframework.web.bind.annotation.RestController;

import java.util.Collection;
import java.util.Collections;
import java.util.HashSet;
import java.util.Set;

public class JavaQuestionRepository implements QuestionRepository{
//    JavaQuestionService questionService;
    private final Set<Question> setJavaOfQuestions = new HashSet<>();


//    @PostConstruct
//    public void init(){
//        add(new Question("Какие два класса не наследуются от Object?",
//                "Все классы наследуются от класса Object"));
//        add(new Question("Что такое Instance Variable?",
//                "Instance Variable — переменная, которая определена внутри класса, и она " +
//                        "существует вплоть до того момента, пока существует объект."));
//        add(new Question("Сколько классов можно наследовать",
//                "Только один, множественное наследование в Java запрещено!"));
//        add(new Question("Автоупаковка (autoboxing) и Автораспаковка (unboxing)",
//                "Автоупаковка (autoboxing) - процесс автоматического преобразования из примитивного типа в соответствующий класс обертку."));
//
//    }

    @Override
    public Question add(Question question) {
        setJavaOfQuestions.add(question);
        return question;
    }

    @Override
    public Question remove(Question question) {
        setJavaOfQuestions.remove(question);
        return question;
    }

    @Override
    public Collection<Question> getAll() {
        return Collections.unmodifiableCollection(setJavaOfQuestions); // создаст неизменяемую копию сета
    }
}
