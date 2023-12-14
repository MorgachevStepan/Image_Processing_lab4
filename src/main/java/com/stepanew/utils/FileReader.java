package com.stepanew.utils;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

public class FileReader {
    public BufferedImage ReadImage(String source){
        BufferedImage returnImage = null;
        try {
            returnImage = ImageIO.read(new File(source));
        } catch (IOException e) {
            System.err.println(e.getMessage());
        }
        return returnImage;
    }

    public void SaveImage(BufferedImage image, String formatName, String distant){
        try {
            ImageIO.write(image, formatName, new File(distant));
        } catch (IOException e) {
            System.err.println(e.getMessage());
        }
    }
}
