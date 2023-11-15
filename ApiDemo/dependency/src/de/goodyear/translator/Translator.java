package de.goodyear.translator;

import de.goodyear.translator.inner.ToLowerTranslatorImpl;
import de.goodyear.translator.inner.ToUpperTranslatorImpl;

import java.util.ServiceLoader;

public interface Translator {
    String translate(String message);

    static Translator create() {
        ServiceLoader<Translator> loader = ServiceLoader.load(Translator.class);
        return loader.findFirst().get();
    }
}
