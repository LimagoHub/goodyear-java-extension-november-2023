package de.goodyear.webappwithonion.adapter.controller;

import de.goodyear.service.PersoneServiceException;
import de.goodyear.service.PersonenService;
import de.goodyear.webappwithonion.adapter.dto.PersonDTO;
import de.goodyear.webappwithonion.adapter.mapper.PersonDTOMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.UUID;

@RestController
@RequestMapping("/v1/personen")
@RequiredArgsConstructor
public class PersonenQueryController {

    private final PersonenService service;
    private final PersonDTOMapper mapper;

    @GetMapping(path="", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Iterable<PersonDTO>> findAllPersons() throws PersoneServiceException {
        return ResponseEntity.ok(mapper.convert(service.findeAlle()));
    }

    @GetMapping(path="/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<PersonDTO> findById(@PathVariable UUID id) throws PersoneServiceException {
        return ResponseEntity.of(service.findeNachId(id).map(mapper::convert));
    }
}
