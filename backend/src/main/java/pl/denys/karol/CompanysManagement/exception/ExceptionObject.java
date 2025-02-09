package pl.denys.karol.CompanysManagement.exception;

import lombok.Data;
import java.time.LocalDateTime;

@Data
public class ExceptionObject {
    private Integer statusCode;
    private String message;
    private LocalDateTime timestamp;
}
