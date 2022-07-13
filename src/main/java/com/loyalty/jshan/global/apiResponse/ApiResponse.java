package com.loyalty.jshan.global.apiResponse;
 
import lombok.Getter; 
// import org.springframework.http.HttpStatus; 
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
 
@Getter 
@NoArgsConstructor
public abstract class ApiResponse {
    private int status;
    private LocalDateTime timeStamp;  

    public ApiResponse(int status, LocalDateTime timeStamp) {
        this.status = status;
        this.timeStamp = timeStamp; 
    }
}
