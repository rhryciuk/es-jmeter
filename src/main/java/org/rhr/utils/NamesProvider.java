package org.rhr.utils;

import java.util.Random;

public class NamesProvider {

    private static final String[] names = new String[] {"Eric Cartman", "Randy Marsh", "Stan Braflovski", "Kile Marsh",
            "Kenny McCormic", "Vincent Vega", "Jules", "Marcellus Wales"};


    public static String getRandomName() {
        Random random = new Random(System.nanoTime());
        final int index = random.nextInt(names.length);
        return names[index];
    }


}
