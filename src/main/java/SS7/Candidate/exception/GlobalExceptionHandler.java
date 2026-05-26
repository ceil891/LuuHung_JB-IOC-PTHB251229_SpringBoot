package SS7.Candidate.exception;


import SS7.Candidate.model.ApiResponse;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;


import java.util.HashMap;
import java.util.Map;

@RestControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler({MethodArgumentNotValidException.class, org.springframework.validation.BindException.class})
    public ResponseEntity<ApiResponse<Map<String, String>>> handleValidationExceptions(Exception ex) {
        Map<String, String> errors = new HashMap<>();

        if (ex instanceof MethodArgumentNotValidException) {
            ((MethodArgumentNotValidException) ex).getBindingResult().getAllErrors().forEach((error) -> {
                String fieldName = ((FieldError) error).getField();
                String errorMessage = error.getDefaultMessage();
                errors.put(fieldName, errorMessage);
            });
        } else if (ex instanceof org.springframework.validation.BindException) {
            ((org.springframework.validation.BindException) ex).getBindingResult().getAllErrors().forEach((error) -> {
                String fieldName = ((FieldError) error).getField();
                String errorMessage = error.getDefaultMessage();
                errors.put(fieldName, errorMessage);
            });
        }

        ApiResponse<Map<String, String>> response = new ApiResponse<>(
                "error",
                "Dữ liệu đầu vào không hợp lệ!",
                errors
        );

        return new ResponseEntity<>(response, HttpStatus.BAD_REQUEST);
    }
}