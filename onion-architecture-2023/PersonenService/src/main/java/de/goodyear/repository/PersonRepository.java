package de.goodyear.repository;

import de.goodyear.model.Person;

import java.util.Optional;
import java.util.UUID;

public interface PersonRepository {

    void save(Person person);
    boolean update(Person person);
    boolean remove(Person person);
    boolean remove(UUID id);

    Optional<Person> findByID(UUID id);

    Iterable<Person> findAll();
}
