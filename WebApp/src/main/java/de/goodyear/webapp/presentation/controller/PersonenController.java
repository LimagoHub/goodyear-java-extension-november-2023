package de.goodyear.webapp.presentation.controller;

import de.goodyear.webapp.presentation.dto.PersonDto;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.awt.print.Book;
import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/v1/personen")
public class PersonenController {


    @Operation(summary = "Get a person by its id")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Found the person",
                    content = { @Content(mediaType = "application/json",
                            schema = @Schema(implementation = PersonDto.class)) }),
            @ApiResponse(responseCode = "400", description = "Invalid id supplied",
                    content = @Content),
            @ApiResponse(responseCode = "404", description = "Person not found",
                    content = @Content) })
    @GetMapping(path="/{id}", produces = {MediaType.APPLICATION_JSON_VALUE} )
    public ResponseEntity<PersonDto> findById(@Parameter(description = "id of person to be searched" ) @PathVariable String id) {
        if("5".equals(id))
            return ResponseEntity.notFound().build();
        return ResponseEntity.ok(PersonDto.builder().id(UUID.randomUUID().toString()).vorname("John").nachname("Doe").build());
    }

    @GetMapping(path="", produces = MediaType.APPLICATION_JSON_VALUE)
    public Iterable<PersonDto> findAll(
            @RequestParam(required = false, defaultValue = "John") String vorname,
            @RequestParam(required = false, defaultValue = "Doe") String nachname) {

        System.out.printf("Vorname='%s', nachname='%s'\n", vorname, nachname);

        var result = List.of(
                PersonDto.builder().id(UUID.randomUUID().toString()).vorname("John").nachname("Doe").build(),
                PersonDto.builder().id(UUID.randomUUID().toString()).vorname("John").nachname("Rambo").build(),
                PersonDto.builder().id(UUID.randomUUID().toString()).vorname("John").nachname("Wayne").build()
        );

        return result;
    }

    @PostMapping(path = "/ja/doof", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public PersonDto transform(@RequestBody PersonDto p) {
        p.setVorname(p.getVorname().toUpperCase());
        p.setNachname(p.getNachname().toUpperCase());
        return p;
    }

}
