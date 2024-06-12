package pro.sky.skyprospringcoursework21;


import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.Collection;


@RestController
@RequestMapping("/exam/java")
public class JavaQuestionController {
    private final QuestionService questionService;


    public JavaQuestionController(QuestionService questionService) {
        this.questionService = questionService;
    }
    @GetMapping(path = "/add")
    public Question addQuestion(@RequestParam("question") String question,
                                @RequestParam("answer") String answer){
        return questionService.add(question, answer);
    }
    @GetMapping(path = "/remove")
    public Question removeQuestion(@RequestParam("question") String question,
                                   @RequestParam("answer") String answer){
        return questionService.remove(question, answer);
    }
    @GetMapping
    public Collection<Question> getQuestion(){
        return questionService.getAll();
    }


}
