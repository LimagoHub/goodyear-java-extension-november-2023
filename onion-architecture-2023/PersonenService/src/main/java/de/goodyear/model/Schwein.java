package de.goodyear.model;

import lombok.AccessLevel;
import lombok.Setter;

@Setter(AccessLevel.PRIVATE)
public class Schwein {

    private int gewicht;

    public void fuettern() {
        gewicht ++;
    }
}
