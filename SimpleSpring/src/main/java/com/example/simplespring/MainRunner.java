package com.example.simplespring;

import com.example.simplespring.translator.Translator;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class MainRunner implements CommandLineRunner {

    private final Translator translator;

    @Override
    public void run(final String... args) throws Exception {
        System.out.println(translator.translate("Hallo Welt"));
    }
}
