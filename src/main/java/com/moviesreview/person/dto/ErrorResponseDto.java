package com.moviesreview.person.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import org.springframework.http.HttpStatus;

import java.time.LocalDateTime;

@Schema(name = "ErrorResponse",description = "Schema to hold error responses")
public record ErrorResponseDto(
        @Schema(description = "API path of the call")
        String apiPath,
        @Schema(description = "Status code of the error")
        HttpStatus statusCode,
        @Schema(description = "Message of the error")
        String errorMessage,
        @Schema(description = "Time of the error")
        LocalDateTime errorTime
) {
}
