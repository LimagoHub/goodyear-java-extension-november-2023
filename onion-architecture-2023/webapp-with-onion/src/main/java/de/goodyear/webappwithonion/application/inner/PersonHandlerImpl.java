package de.goodyear.webappwithonion.application.inner;

import de.goodyear.model.Person;
import de.goodyear.service.PersoneServiceException;
import de.goodyear.service.PersonenService;
import de.goodyear.webappwithonion.application.PersonHandler;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.util.UUID;

@Component
@RequiredArgsConstructor
@Transactional(propagation = Propagation.REQUIRED, rollbackFor = PersoneServiceException.class, isolation = Isolation.READ_COMMITTED)
public class PersonHandlerImpl implements PersonHandler {

    private final PersonenService service;
    @Override
    public void handleSpeichern(final Person p) throws PersoneServiceException {
        try {
            service.speichern(p);
            // FireSuccessEvent ....
        } catch (PersoneServiceException e) {
            // FireFailureEvent....
            throw e;
        }
    }

    @Override
    public boolean handleAendern(final Person p) throws PersoneServiceException {
        return service.aendern(p);
    }

    @Override
    @Transactional( isolation = Isolation.READ_UNCOMMITTED)
    public boolean handleLoeschen(final UUID id) throws PersoneServiceException {
        return service.loesche(id);
    }
}
