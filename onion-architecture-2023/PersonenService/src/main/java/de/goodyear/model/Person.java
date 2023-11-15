package de.goodyear.model;

import lombok.*;

import java.util.UUID;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Setter(AccessLevel.PRIVATE)
public class Person {
    private UUID id;
    private String vorname;

    private String nachname;

    public void taufen(String name) {
        setNachname(name);
    }
}
