package com.moviesreview.person.dto;

import io.swagger.v3.oas.annotations.media.Schema;

import java.time.LocalDate;

@Schema(name = "PersonResponse",description = "Schema to hold the response of the person")
public record PersonResponseDto(

        @Schema(description = "Id of the person",example = "6aa01e295ddca5905c6b7664")
        String id,
        @Schema(description = "Slug of the person",example = "marlon-brando")
        String slug,
        @Schema(description = "Name of the person",example = "Marlon Brando")
        String name,
        @Schema(description = "Biography of the person",example = "Marlon Brando Jr. was an American actor...")
        String biography,
        @Schema(description = "Birthday date of the person",example = "1924-04-03")
        LocalDate birthDate,
        @Schema(description = "Profile photo of the person",example = "https://cdn.example.com/people/marlon-brando.jpg")
        String photoUrl
) {
}
