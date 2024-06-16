package pro.sky.skyprospringcoursework21;

import org.springframework.stereotype.Service;

import javax.swing.*;
import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.Stream;

@Service
public class ExaminerServiceImpl implements ExaminerService{
    private final QuestionService questionService;

    private  final HashSet<Question> mapExaminerService = new HashSet<>();



    public ExaminerServiceImpl(QuestionService questionService) {
        this.questionService = questionService;
    }

    @Override
    public Collection<Question> getQuestions(int amount) {
//        int total = 0;
//        while (mapExaminerService.size() != amount){
//            Question questionRandom = questionService.getRandomQuestion();
//            if (!mapExaminerService.contains(questionRandom)){
//                mapExaminerService.add(questionRandom);
//            }
//
//            mapExaminerService.add(questionRandom);
//            ++total;
//        }
//        return mapExaminerService;

        while (mapExaminerService.size() != amount){
            mapExaminerService.add(questionService.getRandomQuestion());

        }
        return mapExaminerService;



//        return questionService.getAll()
//                .stream()
//                .map(e -> mapExaminerServiceNew.add(mapExaminerService.get(questionService.getRandomQuestion(amount))))
//                .distinct()
//                .limit(amount)
//                .collect(Collectors.groupingBy(mapExaminerServiceNew, () -> new List<Question>(), Collectors.toList())));








//        List<Question> mapExaminerService = new ArrayList<>(questionService.getAll());
//        List<Question> mapExaminerServiceNew = new ArrayList<>();
//        int total = 0;
//        while (total != amount){
//            int random = questionService.getRandomQuestion(amount);
//            if (!mapExaminerServiceNew.contains(mapExaminerService.get(random))){
//                mapExaminerServiceNew.add(mapExaminerService.get(random));
//                ++total;
//            }
//        }
//        return mapExaminerServiceNew;
    }
}
