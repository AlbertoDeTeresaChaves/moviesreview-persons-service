package com.moviesreview.person.controller;

import com.moviesreview.person.constants.PersonConstants;
import com.moviesreview.person.dto.ErrorResponseDto;
import com.moviesreview.person.dto.PersonRequestDto;
import com.moviesreview.person.dto.PersonResponseDto;
import com.moviesreview.person.dto.ResponseDto;
import com.moviesreview.person.mapper.PersonMapper;
import com.moviesreview.person.service.impl.PersonServiceImpl;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(path = "/api/persons")
@RequiredArgsConstructor
@Tag(name = "CRUD Rest API for Person Microservices")
public class PersonController {

    private final PersonServiceImpl personService;

    @Operation(summary = "Create Person", description = "API Restful to create person")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201",description = "HTTP Status CREATED",content =
                @Content(schema = @Schema(implementation = PersonResponseDto.class))
            ),
            @ApiResponse(responseCode = "500", description = "HTTP Status INTERNAL SERVER ERROR",content =
                @Content(schema = @Schema(implementation = ErrorResponseDto.class))
            )
    })
    @PostMapping("/create")
    public ResponseEntity<PersonResponseDto> createPerson(@Valid @RequestBody PersonRequestDto requestDto){
        PersonResponseDto createdPerson = personService.create(requestDto);
        return ResponseEntity.status(HttpStatus.CREATED).body(createdPerson);
    }

    @Operation(summary = "Fetch Persons by Pagination", description = "API Restful to fetch persons based on Pagination")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200",description = "HTTP Status OK",content =
                @Content(schema = @Schema(implementation = PersonResponseDto.class))
            ),
            @ApiResponse(responseCode = "500", description = "HTTP Status INTERNAL SERVER ERROR",content =
            @Content(schema = @Schema(implementation = ErrorResponseDto.class))
            )
    })
    @GetMapping
    public ResponseEntity<Page<PersonResponseDto>> fetchAllPersons(@PageableDefault(page = 0 ,size = 10) Pageable pageable){
        Page<PersonResponseDto> personPage = personService.fetchAll(pageable);
        return ResponseEntity.status(HttpStatus.OK).body(personPage);
    }

    @Operation(summary = "Fetch Person Details", description = "API Restful to fetch person details based on slug")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200",description = "HTTP Status OK",content =
            @Content(schema = @Schema(implementation = PersonResponseDto.class))
            ),
            @ApiResponse(responseCode = "500", description = "HTTP Status INTERNAL SERVER ERROR",content =
            @Content(schema = @Schema(implementation = ErrorResponseDto.class))
            )
    })
    @GetMapping("/{slug}")
    public ResponseEntity<PersonResponseDto> fetchPersonDetails(@PathVariable String slug){
        PersonResponseDto result = PersonMapper.toDto(personService.fetchBySlug(slug));

        return ResponseEntity.status(HttpStatus.OK).body(result);
    }

    @Operation(summary = "Update Persons Details", description = "API Restful to update person details based on slug")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200",description = "HTTP Status OK",content =
            @Content(schema = @Schema(implementation = PersonResponseDto.class))
            ),
            @ApiResponse(responseCode = "500", description = "HTTP Status INTERNAL SERVER ERROR",content =
            @Content(schema = @Schema(implementation = ErrorResponseDto.class))
            )
    })
    @PutMapping("/{slug}")
    public ResponseEntity<PersonResponseDto> updatePersonDetails(@PathVariable String slug, @Valid @RequestBody PersonRequestDto requestDto){
        PersonResponseDto result = personService.update(slug,requestDto);

        return ResponseEntity.status(HttpStatus.OK).body(result);
    }

    @Operation(summary = "Delete Person", description = "API Restful to delete person based on slug")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "204",description = "HTTP Status NO CONTENT",content =
            @Content(schema = @Schema(implementation = ResponseDto.class))
            ),
            @ApiResponse(responseCode = "500", description = "HTTP Status INTERNAL SERVER ERROR",content =
            @Content(schema = @Schema(implementation = ErrorResponseDto.class))
            )
    })
    @DeleteMapping("/{slug}")
    public ResponseEntity<ResponseDto> deletePerson (@PathVariable String slug){
        personService.delete(slug);
        return ResponseEntity.status(HttpStatus.NO_CONTENT).body(new ResponseDto(HttpStatus.NO_CONTENT, PersonConstants.PERSON_DELETED));
    }
}
