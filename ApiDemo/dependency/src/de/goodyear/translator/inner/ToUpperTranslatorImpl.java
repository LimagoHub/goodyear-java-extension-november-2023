package de.goodyear.translator.inner;

import de.goodyear.translator.Translator;

public class ToUpperTranslatorImpl implements Translator {
    @Override
    public String translate(final String message) {
        return message.toUpperCase();
    }
}
