package pro.sky.skyprospringcoursework21;

import org.springframework.stereotype.Service;

import java.util.*;

@Service
public class JavaQuestionService implements QuestionService{

//    List<Question> listOfQuestionsService;
//    Question question;

    Map<String, Question> mapOfQuestionsService;
    public JavaQuestionService() {
        mapOfQuestionsService = new HashMap<>(); // Решил собрать в мапу, но может это не правильно. Может лучше в List?
    }

    @Override
    public Question add(String question, String answer){
        String key = keyGeneration(question, answer);
        if (mapOfQuestionsService.containsKey(key)){
            throw new RecurringQuestionException("Этот вопрос уже есть в базе");
        }
        Question newQuestion = new Question(question, answer);
        mapOfQuestionsService.put(key, newQuestion);
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
    public int getRandomQuestion(int amount){
        Random random = new Random();
        return random.nextInt(0, amount);

    }
}
