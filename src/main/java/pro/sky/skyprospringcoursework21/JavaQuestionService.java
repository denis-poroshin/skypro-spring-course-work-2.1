package pro.sky.skyprospringcoursework21;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import java.util.*;

@Service("javaQuestionService")
//@Qualifier("java")
public class JavaQuestionService implements QuestionService{
    private final QuestionRepository javaQuestionRepository;
    private final Random random = new Random();

    public JavaQuestionService(@Qualifier("javaQuestionRepository") QuestionRepository javaQuestionRepository) {
        this.javaQuestionRepository = javaQuestionRepository;
    }


    @Override
    public Question add(String question, String answer){
        return add(new Question(question, answer));
    }

    @Override
    public Question add(Question question) {
        if (javaQuestionRepository.getAll().contains(question)){
            throw new RecurringQuestionException("Этот вопрос уже есть в базе");
        }
        return javaQuestionRepository.add(question);
    }

    @Override
    public Question remove(String question, String answer){
        Question questionToBeDeleted = new Question(question, answer);
        if (javaQuestionRepository.getAll().contains(questionToBeDeleted)){
           return javaQuestionRepository.remove(questionToBeDeleted);
        }
        throw new QuestionNotFoundException("Этот вопрос не найден в базе");

    }
//    private String keyGeneration(String question, String answer){
//        return question + "/" + answer;
//    }

    @Override
    public Collection<Question> getAll() {

        return javaQuestionRepository.getAll();
    }
    @Override
    public Question getRandomQuestion(){
        List<Question> questionList = new ArrayList<>(getAll());
        if (questionList.isEmpty()){
            throw new QuestionsNullException("Список вопрос равен null");
        }
        int index =  random.nextInt(questionList.size());
        return questionList.get(index);


    }
}
