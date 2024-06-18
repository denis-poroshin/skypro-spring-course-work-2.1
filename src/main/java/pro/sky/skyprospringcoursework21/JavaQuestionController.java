package pro.sky.skyprospringcoursework21;


import org.springframework.web.bind.annotation.*;

import java.util.Collection;


@RestController
@RequestMapping("/exam/java")
public class JavaQuestionController {
    private final QuestionService questionService;


    public JavaQuestionController(QuestionService questionService) {
        this.questionService = questionService;
    }
    @GetMapping(path = "/add")
    public Question addQuestion(@RequestParam(value = "question") String question,
                                @RequestParam(value = "answer") String answer){
//        return questionService.add(question, answer);
        return questionService.add(new Question(question, answer)); //Можно так
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
