package pro.sky.skyprospringcoursework21;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Random;
@Service("mathQuestionService")
//@Qualifier("mathematics")
public class MathQuestionService implements QuestionService{

    private final MathematicsQuestionRepository mathematicsQuestionRepository;


    private final Random random = new Random();

    public MathQuestionService(MathematicsQuestionRepository mathematicsQuestionRepository) {
        this.mathematicsQuestionRepository = mathematicsQuestionRepository;
    }

    @Override
    public Question add(String question, String answer){
        return add(new Question(question, answer));
    }

    @Override
    public Question add(Question question) {
        if (mathematicsQuestionRepository.getAll().contains(question)){
            throw new RecurringQuestionException("Этот вопрос уже есть в базе");
        }
        return mathematicsQuestionRepository.add(question);
    }

    @Override
    public Question remove(String question, String answer){
        Question questionToBeDeleted = new Question(question, answer);
        if (mathematicsQuestionRepository.getAll().contains(questionToBeDeleted)){
            return mathematicsQuestionRepository.remove(questionToBeDeleted);
        }
        throw new QuestionNotFoundException("Этот вопрос не найден в базе");


    }
    @Override
    public Collection<Question> getAll() {

        return mathematicsQuestionRepository.getAll();
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
