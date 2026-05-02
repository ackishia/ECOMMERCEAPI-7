package exception;

import com.ws101.calambasgalupo.EcommerceApi.ProductNotFoundException;
import org.springframework.http.*;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.Map;

@RestControllerAdvice
public class GlobalExceptionalHandler {

    @ExceptionHandler(ProductNotFoundException.class)
    public ResponseEntity<Map<String, Object>> handleNotFound(Exception ex) {
        return ResponseEntity.status(404).body(Map.of(
                "timestamp", LocalDateTime.now(),
                "error", "Not Found",
                "message", ex.getMessage()
        ));
    }

    @ExceptionHandler(Exception.class)
    public ResponseEntity<Map<String, Object>> handleGeneral(Exception ex) {
        return ResponseEntity.status(500).body(Map.of(
                "timestamp", LocalDateTime.now(),
                "error", "Server Error",
                "message", ex.getMessage()
        ));
    }
}