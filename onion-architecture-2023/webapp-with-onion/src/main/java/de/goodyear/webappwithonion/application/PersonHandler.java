package de.goodyear.webappwithonion.application;

import de.goodyear.model.Person;
import de.goodyear.service.PersoneServiceException;

import java.util.UUID;

public interface PersonHandler {
    void handleSpeichern(Person p) throws PersoneServiceException;
    boolean handleAendern(Person p) throws PersoneServiceException;

    boolean handleLoeschen(UUID id) throws PersoneServiceException;
}
