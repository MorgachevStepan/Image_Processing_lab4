package com.stepanew.utils;

import java.awt.*;
import java.util.HashSet;
import java.util.Random;
import java.util.Set;

public class ColorGenerator {
    public Color[] generateUniqueColors(int arraySize) {
        Set<Color> colorSet = new HashSet<>();
        Random random = new Random();

        while (colorSet.size() < arraySize) {
            int red = random.nextInt(256);
            int green = random.nextInt(256);
            int blue = random.nextInt(256);

            Color color = new Color(red, green, blue);
            colorSet.add(color);
        }

        return colorSet.toArray(new Color[0]);
    }
}
