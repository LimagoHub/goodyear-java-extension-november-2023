package de.goodyear.translator.inner;

import de.goodyear.translator.Translator;

public class ToLowerTranslatorImpl implements Translator {
    @Override
    public String translate(final String message) {
        return message.toLowerCase();
    }
}
