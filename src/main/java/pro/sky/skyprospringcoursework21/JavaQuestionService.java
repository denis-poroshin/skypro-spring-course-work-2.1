package pro.sky.skyprospringcoursework21;

import org.springframework.stereotype.Service;

import java.util.*;

@Service
public class JavaQuestionService implements QuestionService{

//    List<Question> listOfQuestionsService;
//    Question question;
    private final Random random = new Random();

    private final Map<String, Question> mapOfQuestionsService;
    public JavaQuestionService() {
        mapOfQuestionsService = new HashMap<>(); // Решил собрать в мапу, но может это не правильно. Может лучше в List?
    }

    @Override
    public Question add(String question, String answer){
        return add(new Question(question, answer));
    }

    @Override
    public Question add(Question question) {
        String key = keyGeneration(question.getQuestion(), question.getAnswer());
        if (mapOfQuestionsService.containsKey(key)){
            throw new RecurringQuestionException("Этот вопрос уже есть в базе");
        }
        mapOfQuestionsService.put(key, question);
        return mapOfQuestionsService.get(key);
    }

    @Override
    public Question remove(String question, String answer){
        String key = keyGeneration(question, answer);
        if (mapOfQuestionsService.containsKey(key)){
           return mapOfQuestionsService.remove(key);
        }
        throw new QuestionNotFoundException("Этот вопрос не найден в базе");


    }
    private String keyGeneration(String question, String answer){
        return question + "/" + answer;
    }

    @Override
    public Collection<Question> getAll() {
        return Collections.unmodifiableCollection(mapOfQuestionsService.values()); // создаст неизменяемую копию мапы
    }
    @Override
    public Question getRandomQuestion(){
        List<Question> questionList = new ArrayList<>(getAll());
        int index =  random.nextInt(questionList.size());
        return questionList.get(index);


    }
}
