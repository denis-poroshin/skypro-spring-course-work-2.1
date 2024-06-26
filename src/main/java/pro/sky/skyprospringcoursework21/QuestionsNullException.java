package pro.sky.skyprospringcoursework21;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.BAD_REQUEST)
public class QuestionsNullException extends RuntimeException{
    public QuestionsNullException(String message) {
        super(message);
    }
}
