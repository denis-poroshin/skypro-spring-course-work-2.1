package pro.sky.skyprospringcoursework21;

import org.springframework.stereotype.Service;

import javax.swing.*;
import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.Stream;

@Service
public class ExaminerServiceImpl implements ExaminerService{
    private final QuestionService questionService;

//    private  final List<Question> mapExaminerService = new ArrayList<>();



    public ExaminerServiceImpl(QuestionService questionService) {
        this.questionService = questionService;
    }

    @Override
    public Collection<Question> getQuestions(int amount) {
        List<Question> mapExaminerService = new ArrayList<>(questionService.getAll());
        List<Question> mapExaminerServiceNew = new ArrayList<>();
        int total = 0;
        while (total != amount){
            int random = questionService.getRandomQuestion(amount);
            if (!mapExaminerServiceNew.contains(mapExaminerService.get(random))){
                mapExaminerServiceNew.add(mapExaminerService.get(random));
                ++total;
            }
        }
        return mapExaminerServiceNew;



//        return questionService.getAll()
//                .stream()
//                .map(e -> mapExaminerServiceNew.add(mapExaminerService.get(questionService.getRandomQuestion(amount))))
//                .distinct()
//                .limit(amount)
//                .collect(Collectors.groupingBy(mapExaminerServiceNew, () -> new List<Question>(), Collectors.toList())));


    }
}
