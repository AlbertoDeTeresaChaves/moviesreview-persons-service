package com.moviesreview.person.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import org.springframework.http.HttpStatus;

@Schema(name = "Response",description = "Schema to hold the response of the calls")
public record ResponseDto(
        @Schema(description = "Status code of the response",example = "200")
        HttpStatus statusCode,
        @Schema(description = "Message of the response",example = "Person successful created")
        String message
) {
}
