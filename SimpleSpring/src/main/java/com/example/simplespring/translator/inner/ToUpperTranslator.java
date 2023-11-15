package com.example.simplespring.translator.inner;

import com.example.simplespring.translator.Translator;
import org.springframework.stereotype.Component;

@Component
public class ToUpperTranslator implements Translator {
    @Override
    public String translate(final String message) {
        return message.toUpperCase();
    }
}
