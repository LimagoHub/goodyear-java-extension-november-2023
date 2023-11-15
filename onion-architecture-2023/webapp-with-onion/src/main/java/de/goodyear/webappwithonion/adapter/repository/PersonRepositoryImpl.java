package de.goodyear.webappwithonion.adapter.repository;

import de.goodyear.model.Person;
import de.goodyear.repository.PersonRepository;
import de.goodyear.webappwithonion.adapter.mapper.PersonMapper;
import de.goodyear.webappwithonion.adapter.repository.raw.PersonRawRepo;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import java.util.Optional;
import java.util.UUID;

@RequiredArgsConstructor
@Repository
public class PersonRepositoryImpl implements PersonRepository {

    private final PersonRawRepo raw;
    private final PersonMapper mapper;

    @Override
    public void save(final Person person) {
        if(raw.existsById(person.getId()))
            throw new RuntimeException("Person existiert bereits");
        raw.save(mapper.convert(person));
    }

    @Override
    public boolean update(final Person person) {
        if(! raw.existsById(person.getId()))
            return false;
        raw.save(mapper.convert(person));
        return true;
    }

    @Override
    public boolean remove(final Person person) {
        return remove(person.getId());
    }

    @Override
    public boolean remove(final UUID id) {
        if(! raw.existsById(id))
            return false;
        raw.deleteById(id);
        return true;
    }

    @Override
    public Optional<Person> findByID(final UUID id) {
        return raw.findById(id).map(mapper::convert);
    }

    @Override
    public Iterable<Person> findAll() {
        return mapper.convert(raw.findAll());
    }
}
