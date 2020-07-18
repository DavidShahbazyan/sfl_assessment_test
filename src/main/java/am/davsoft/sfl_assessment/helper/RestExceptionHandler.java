package am.davsoft.sfl_assessment.helper;

import am.davsoft.sfl_assessment.exception.OrderNotFoundException;
import am.davsoft.sfl_assessment.exception.ProductNotFoundException;
import am.davsoft.sfl_assessment.exception.TableNotFoundException;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.context.request.WebRequest;

import javax.persistence.PersistenceException;

/**
 * @author David Shahbazyan
 * @since Jul 18, 2020
 */
@RestControllerAdvice
public class RestExceptionHandler {
    @ExceptionHandler(value = {ProductNotFoundException.class})
    public ResponseEntity productNotFound(ProductNotFoundException ex, WebRequest request) {
        writeLog("handling ProductNotFoundException...", request, ex);
        return ResponseEntity.notFound().build();
    }

    @ExceptionHandler(value = {OrderNotFoundException.class})
    public ResponseEntity orderNotFound(OrderNotFoundException ex, WebRequest request) {
        writeLog("handling OrderNotFoundException...", request, ex);
        return ResponseEntity.notFound().build();
    }

    @ExceptionHandler(value = {TableNotFoundException.class})
    public ResponseEntity tableNotFound(TableNotFoundException ex, WebRequest request) {
        writeLog("handling TableNotFoundException...", request, ex);
        return ResponseEntity.notFound().build();
    }

    private void writeLog(String message, WebRequest request, PersistenceException exception) {
        System.out.println(message);
        System.out.println(request);
    }
}