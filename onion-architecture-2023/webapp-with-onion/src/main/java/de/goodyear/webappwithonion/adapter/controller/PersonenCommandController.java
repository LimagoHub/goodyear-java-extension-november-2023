package de.goodyear.webappwithonion.adapter.controller;

import de.goodyear.service.PersoneServiceException;
import de.goodyear.webappwithonion.adapter.dto.PersonDTO;
import de.goodyear.webappwithonion.adapter.mapper.PersonDTOMapper;
import de.goodyear.webappwithonion.application.PersonHandler;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponents;
import org.springframework.web.util.UriComponentsBuilder;

import java.util.UUID;

@RestController
@RequestMapping("/v1/personen")
@RequiredArgsConstructor
public class PersonenCommandController {

    private final PersonDTOMapper mapper;
    private final PersonHandler handler;

    @PostMapping(path="", consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Void> createPerson(@RequestBody @Valid PersonDTO dto, UriComponentsBuilder builder) throws PersoneServiceException {
        handler.handleSpeichern(mapper.convert(dto));
        UriComponents uriComponents = builder.path("/v1/personen/{id}").buildAndExpand(dto.getId());
        return ResponseEntity.created(uriComponents.toUri()).build();
    }

    @PutMapping(path="/{id}", consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Void> updatePerson(@RequestBody @Valid PersonDTO dto,  @PathVariable UUID id) throws PersoneServiceException {
        if(! dto.getId().equals(id))
            return ResponseEntity.badRequest().build();
        if(handler.handleAendern(mapper.convert(dto)))
            return ResponseEntity.ok().build();
        return ResponseEntity.notFound().build();
    }

    @DeleteMapping(path="/{id}")
    public ResponseEntity<Void> remove(@PathVariable UUID id) throws PersoneServiceException {

        if(handler.handleLoeschen(id))
            return ResponseEntity.ok().build();
        return ResponseEntity.notFound().build();
    }
}
