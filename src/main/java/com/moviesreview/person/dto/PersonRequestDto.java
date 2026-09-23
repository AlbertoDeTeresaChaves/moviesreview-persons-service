package com.moviesreview.person.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.*;
import org.hibernate.validator.constraints.URL;

import java.time.LocalDate;

@Schema(name = "PersonRequest",description = "Schema to hold request of persons")
public record PersonRequestDto(
        @NotBlank
        @Pattern(regexp = "^[a-zA-ZáéíóúÁÉÍÓÚñÑ ]+$")
        @Size(min = 2, max = 30,message = "Name must be between 2 and 30 characters")
        @Schema(description = "name of the person",example = "Ryan Gosling")
        String name,

        @Size(max = 200,message = "The biography must be maximum 200 characters")
        @Schema(description = "biography of the person", example = "Ryan Gosling is an American actor...")
        String biography,

        @NotNull
        @Past(message = "The date cannot be in the future")
        @JsonFormat(pattern = "yyyy-MM-dd")
        @Schema(description = "birthday date of the person",example = "1980-11-12",type = "string",format = "date")
        LocalDate birthDate,

        @URL(protocol = "https",message = "Photo must be a valid and secure HTTP link")
        @Schema(description = "profile photo HTTP URL",example = "https://cdn.example.com/people/ryan-gosling.jpg")
        String photoUrl
) {
}
