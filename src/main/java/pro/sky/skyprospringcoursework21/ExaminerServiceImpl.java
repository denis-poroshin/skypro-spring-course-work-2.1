package pro.sky.skyprospringcoursework21;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import javax.swing.*;
import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.Stream;

@Service
public class ExaminerServiceImpl implements ExaminerService{
    private final QuestionService javaQuestionService;
    private final QuestionService mathematicsQuestionService;

    private final Random random = new Random();
    public ExaminerServiceImpl(@Qualifier("mathQuestionService") QuestionService mathematicsQuestionService,
                               @Qualifier("javaQuestionService") QuestionService javaQuestionService) {
        this.mathematicsQuestionService = mathematicsQuestionService;
        this.javaQuestionService = javaQuestionService;
    }


    @Override
    public Collection<Question> getQuestions(int amount) {
        if (amount <= 0 ||
                (amount > (javaQuestionService.getAll().size() + mathematicsQuestionService.getAll().size()))){
            throw new IncorrectInputException("Некоретный ввод числа для получения случайных вопросв");
        }
        if (mathematicsQuestionService.getAll().isEmpty()){
            return gettingAJavaQuestion(amount);

        }else if (javaQuestionService.getAll().isEmpty()) {
            return gettingAMathQuestion(amount);

        }else {
            return gettingAQuestionOnJavaAndMathematics(amount);
        }

    }
    private Collection<Question> gettingAJavaQuestion(int amount){
        Set<Question> mapExaminerService = new HashSet<>();
        while (mapExaminerService.size() < amount){
            mapExaminerService.add(javaQuestionService.getRandomQuestion());
        }
        return mapExaminerService;

    }
    private Collection<Question> gettingAMathQuestion(int amount){
        Set<Question> mapExaminerService = new HashSet<>();
        while (mapExaminerService.size() < amount){
            mapExaminerService.add(mathematicsQuestionService.getRandomQuestion());
        }
        return mapExaminerService;
    }
    private Collection<Question> gettingAQuestionOnJavaAndMathematics(int amount){
        Set<Question> mapExaminerService = new HashSet<>();
        while (mapExaminerService.size() < amount){
            boolean numRandom = random.nextBoolean(); // true или false
            if (numRandom){
                mapExaminerService.add(javaQuestionService.getRandomQuestion());
            }else {
                mapExaminerService.add(mathematicsQuestionService.getRandomQuestion());
            }
        }
        return mapExaminerService;

    }
}
