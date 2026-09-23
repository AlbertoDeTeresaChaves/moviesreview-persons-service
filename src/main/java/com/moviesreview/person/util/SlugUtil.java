package com.moviesreview.person.util;

import org.springframework.stereotype.Component;

import java.text.Normalizer;
import java.util.Locale;
import java.util.regex.Pattern;

@Component
public class SlugUtil {

    private static final Pattern DIACRITICS = Pattern.compile("\\p{InCombiningDiacriticalMarks}+");
    private static final Pattern LETTERS = Pattern.compile("[^a-z]+");

    public static String slugify(String input){

        String normalized = Normalizer.normalize(input, Normalizer.Form.NFD);
        String noAccents = DIACRITICS.matcher(normalized).replaceAll("");

        return LETTERS.matcher(noAccents.toLowerCase(Locale.ROOT)).replaceAll("-");
    }


}
