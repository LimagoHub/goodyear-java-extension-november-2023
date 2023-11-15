package de.main;

import de.goodyear.translator.Translator;

import java.util.ServiceLoader;

public class Main {
    public static void main(String[] args) {
        Translator translator = Translator.create();


        System.out.println(translator.translate("Hello world!"));
    }
}