package com.stepanew.filters;

import java.awt.*;
import java.awt.image.BufferedImage;

public class GrayScale {
    public BufferedImage grayScale(BufferedImage image) {
        int width = image.getWidth();
        int height = image.getHeight();

        BufferedImage grayscaleImage = new BufferedImage(width, height, BufferedImage.TYPE_INT_RGB);

        for (int y = 0; y < height; y++) {
            for (int x = 0; x < width; x++) {
                java.awt.Color color = new java.awt.Color(image.getRGB(x, y));

                int brightness = (int) (color.getRed() + color.getGreen() + color.getBlue()) / 3;

                java.awt.Color grayColor = new Color(brightness, brightness, brightness);

                grayscaleImage.setRGB(x, y, grayColor.getRGB());
            }
        }

        return grayscaleImage;
    }
}
