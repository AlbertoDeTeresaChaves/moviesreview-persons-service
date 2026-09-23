package com.moviesreview.person.model;

import jakarta.validation.constraints.NotBlank;
import lombok.*;
import org.bson.types.ObjectId;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;
import org.springframework.data.mongodb.core.mapping.FieldType;

import java.time.LocalDate;

@Document(collection = "persons")
@NoArgsConstructor
@AllArgsConstructor
@Setter
@Getter
@ToString
@Builder
public class Person {
    @Id
    private ObjectId id;

    @NotBlank
    private String slug;

    @NotBlank
    private String name;
    private String biography;

    @Field(targetType = FieldType.STRING)
    private LocalDate birthDate;
    private String photoUrl;

}