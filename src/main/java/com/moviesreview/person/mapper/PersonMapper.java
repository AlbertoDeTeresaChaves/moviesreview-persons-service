package com.moviesreview.person.mapper;

import com.moviesreview.person.dto.PersonRequestDto;
import com.moviesreview.person.dto.PersonResponseDto;
import com.moviesreview.person.model.Person;
import org.springframework.stereotype.Component;

@Component
public class PersonMapper {

    public static PersonResponseDto toDto(Person person){
        return new PersonResponseDto(
                person.getId().toString(),
                person.getSlug(),
                person.getName(),
                person.getBiography(),
                person.getBirthDate(),
                person.getPhotoUrl()
        );
    }

    public static Person toEntity(PersonRequestDto requestDto,String slug){
        new Person();
        return Person.builder()
                .slug(slug)
                .name(requestDto.name())
                .biography(requestDto.biography())
                .birthDate(requestDto.birthDate())
                .photoUrl(requestDto.photoUrl()).build();
    }
}
