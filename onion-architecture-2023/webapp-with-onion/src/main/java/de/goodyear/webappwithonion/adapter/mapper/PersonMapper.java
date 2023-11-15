package de.goodyear.webappwithonion.adapter.mapper;

import de.goodyear.model.Person;
import de.goodyear.webappwithonion.adapter.entity.PersonEntity;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface PersonMapper {

    Person convert(PersonEntity entity);
    PersonEntity convert(Person person);

    Iterable<Person> convert(Iterable<PersonEntity> entities);
}
