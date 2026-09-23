package com.moviesreview.person.service.impl;

import com.mongodb.DuplicateKeyException;
import com.moviesreview.person.dto.PersonRequestDto;
import com.moviesreview.person.dto.PersonResponseDto;
import com.moviesreview.person.exceptions.ResourceNotFoundException;
import com.moviesreview.person.mapper.PersonMapper;
import com.moviesreview.person.model.Person;
import com.moviesreview.person.repository.PersonRepository;
import com.moviesreview.person.service.IPersonService;
import com.moviesreview.person.util.SlugUtil;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Slf4j
@RequiredArgsConstructor
@Transactional
@Service
public class PersonServiceImpl implements IPersonService {

    private final PersonRepository personRepository;

    @Override
    public PersonResponseDto create(PersonRequestDto request) {
        log.info("Trying to create Person with the name: {}",request.name());
        String baseSlug = SlugUtil.slugify(request.name());

        if(baseSlug.isEmpty()){
            baseSlug = "person";
        }

        String slug = baseSlug;
        int suffix = 2;
        while(true){
            if(!personRepository.existsBySlug(slug)){
                try{
                    Person saved = personRepository.save(PersonMapper.toEntity(request,slug));
                    log.info("Person saved with the id: {}",saved.getId());
                    return PersonMapper.toDto(saved);
                }catch(DuplicateKeyException e){

                }
            }
            slug = baseSlug + "-" + suffix++;
        }
    }

    @Override
    @Transactional(readOnly = true)
    public Page<PersonResponseDto> fetchAll(Pageable pageable) {
        log.info("Trying to fetch Persons in the page: {}" ,pageable.getPageNumber());
        Page<Person> personPage = personRepository.findAll(pageable);

        return personPage.map(PersonMapper::toDto);
    }

    @Override
    @Transactional(readOnly = true)
    public Person fetchBySlug(String slug) {
        log.info("Trying to find Person by ID: {}", slug);
        return personRepository.findBySlug(slug).orElseThrow(
                () -> new ResourceNotFoundException("Person","slug",slug)
        );
    }

    @Override
    public PersonResponseDto update(String slug, PersonRequestDto request) {
        log.info("Trying to update Person with the slug: {}", slug);
        Person person = fetchBySlug(slug);

        person.setName(request.name());
        person.setBiography(request.biography());
        person.setBirthDate(request.birthDate());
        person.setPhotoUrl(request.photoUrl());

        log.info("Person updated with the slug: {}",person.getSlug());
        return PersonMapper.toDto(person);
    }

    @Override
    public void delete(String slug) {
        log.info("Trying to delete Person with the slug: {}", slug);
        Person person = fetchBySlug(slug);
        personRepository.delete(person);
    }
}
