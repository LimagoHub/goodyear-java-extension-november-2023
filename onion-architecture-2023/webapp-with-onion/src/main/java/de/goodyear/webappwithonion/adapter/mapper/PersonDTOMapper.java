package de.goodyear.webappwithonion.adapter.mapper;

import de.goodyear.model.Person;
import de.goodyear.webappwithonion.adapter.dto.PersonDTO;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface PersonDTOMapper {

    PersonDTO convert(Person person);
    Person convert(PersonDTO personDTO);

    Iterable<PersonDTO> convert(Iterable<Person> personen);

}
