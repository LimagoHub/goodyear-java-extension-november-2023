package de.goodyear.service.inner;

import de.goodyear.model.Person;
import de.goodyear.repository.PersonRepository;
import de.goodyear.service.PersoneServiceException;
import de.goodyear.service.PersonenService;
import lombok.RequiredArgsConstructor;

import java.util.Optional;
import java.util.UUID;

@RequiredArgsConstructor
public class PersonenServiceImpl implements PersonenService {

    private final PersonRepository repo;
    @Override
    public void speichern(final Person person) throws PersoneServiceException {
        try {
            validatePerson(person);
            repo.save(person);
        } catch (RuntimeException e) {
            throw new PersoneServiceException("Unbekannter Fehler", e);
        }
    }



    @Override
    public boolean aendern(final Person person) throws PersoneServiceException {
        try {
            validatePerson(person);
            return repo.update(person);
        } catch (RuntimeException e) {
            throw new PersoneServiceException("Unbekannter Fehler", e);
        }
    }

    @Override
    public boolean loesche(final Person person) throws PersoneServiceException {
        return loesche(person.getId());
    }

    @Override
    public boolean loesche(final UUID id) throws PersoneServiceException {
        try {

            return repo.remove(id);
        } catch (RuntimeException e) {
            throw new PersoneServiceException("Unbekannter Fehler", e);
        }
    }

    @Override
    public Optional<Person> findeNachId(final UUID id) throws PersoneServiceException {
        try {

            return repo.findByID(id);
        } catch (RuntimeException e) {
            throw new PersoneServiceException("Unbekannter Fehler", e);
        }
    }

    @Override
    public Iterable<Person> findeAlle() throws PersoneServiceException {
        try {

            return repo.findAll();
        } catch (RuntimeException e) {
            throw new PersoneServiceException("Unbekannter Fehler", e);
        }
    }

    private static void validatePerson(final Person person) throws PersoneServiceException {
        if(person == null) throw new PersoneServiceException("Person darf nicht null sein");
        if(person.getVorname() == null || person.getVorname().length() < 2) throw new PersoneServiceException("Vorname zu kurz");
        if(person.getNachname() == null || person.getNachname().length() < 2) throw new PersoneServiceException("Nachname zu kurz");
        if(person.getVorname().equals("Attila")) throw new PersoneServiceException("Unerwuenschte Person");
    }
}
