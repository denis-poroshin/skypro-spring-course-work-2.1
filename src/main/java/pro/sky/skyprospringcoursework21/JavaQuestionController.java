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
        return questionService.add(question, answer);
    }
    @GetMapping(path = "/add/{question}/{answer}")//Мне кажется я войду в эти 99%, кто не правильно реализовал этот метод в контроллере).Проверил,  работает, но как-то это не правильно...
    public Question addQuestionTho(@PathVariable("question") String question,
                                @PathVariable("answer") String answer){
        return questionService.add(new Question(question, answer));

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
