package de.goodyear.webappwithonion.adapter.repository.raw;

import de.goodyear.webappwithonion.adapter.entity.PersonEntity;
import org.springframework.data.repository.CrudRepository;

import java.util.UUID;

public interface PersonRawRepo extends CrudRepository<PersonEntity, UUID> {

    Iterable<PersonEntity> findByVorname(String vorname);
}
