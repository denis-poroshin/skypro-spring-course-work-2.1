package pro.sky.skyprospringcoursework21;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.Collection;
@RestController
@RequestMapping("/exam/mathematics")
public class MathQuestionController {
    private final QuestionService mathematicsQuestionService;

    public MathQuestionController(@Qualifier("mathQuestionService") QuestionService mathematicsQuestionService) {
        this.mathematicsQuestionService = mathematicsQuestionService;
    }

    @GetMapping(path = "/add")
    public Question addQuestion(@RequestParam(value = "question") String question,
                                @RequestParam(value = "answer") String answer){
//        return questionService.add(question, answer);
        return mathematicsQuestionService.add(new Question(question, answer)); //Можно так
    }

    @GetMapping(path = "/remove")
    public Question removeQuestion(@RequestParam("question") String question,
                                   @RequestParam("answer") String answer){
        return mathematicsQuestionService.remove(question, answer);
    }
    @GetMapping
    public Collection<Question> getQuestion(){
        return mathematicsQuestionService.getAll();
    }
}
