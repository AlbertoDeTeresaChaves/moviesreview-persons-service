package com.moviesreview.person.service;

import com.moviesreview.person.dto.PersonRequestDto;
import com.moviesreview.person.dto.PersonResponseDto;
import com.moviesreview.person.model.Person;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;


public interface IPersonService {
    PersonResponseDto create (PersonRequestDto request);
    Page<PersonResponseDto> fetchAll(Pageable pageable);
    Person fetchBySlug(String slug);
    PersonResponseDto update(String slug, PersonRequestDto request);
    void delete(String slug);
}
