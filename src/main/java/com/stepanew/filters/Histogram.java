package com.stepanew.filters;

import com.stepanew.utils.FileReader;

import java.awt.*;
import java.awt.image.BufferedImage;
import java.util.*;
import java.util.List;

public class Histogram {
    private final int[] histogram;
    private final FileReader fileReader;
    private BufferedImage image;
    public static final String PNG = "png";
    public static final String PATH = "src/main/resources/Histogram.png";

    public List<Double> getHistogram(Boolean flag) {
        List<Double> result = new ArrayList<>();

        for(int i: histogram){
            result.add((double) i);
        }
        return result;
    }

    public List<Integer> getHistogram() {
        List<Integer> result = new ArrayList<>();

        for(int i: histogram){
            result.add(i);
        }
        return result;
    }

    public Histogram(FileReader fileReader){
        this.fileReader = fileReader;
        this.histogram = new int[256];
    }

    public void buildHistogram(BufferedImage image){
        this.image = image;
        int width = image.getWidth();
        int height = image.getHeight();

        for(int x = 0; x < width; x++){
            for (int y = 0; y < height; y++){
                Color color = new Color(image.getRGB(x, y));
                int gray = (color.getRed() + color.getBlue() + color.getGreen()) / 3;

                histogram[gray]++;
            }
        }
    }

    public void plotHistogram(){
        int maxHeight = 100;
        int maxFrequency = getMaxFrequency();
        BufferedImage histogramImage = new BufferedImage(256, maxHeight, BufferedImage.TYPE_INT_ARGB);

        for (int x = 0; x < histogram.length; x++){
            int height = (int)(histogram[x] * 1.0/maxFrequency * maxHeight);
            for (int y = 0; y < maxHeight; y++){
                if(y > maxHeight - height){
                    histogramImage.setRGB(x, y, Color.BLACK.getRGB());
                }else{
                    histogramImage.setRGB(x, y, Color.WHITE.getRGB());
                }
            }
        }

        fileReader.SaveImage(histogramImage, PNG, PATH);
    }

    private Map<Integer, Integer> findPeaks(){
        Map<Integer, Integer> peaks = new HashMap<>();

        for (int i = 2; i < histogram.length - 2; i++){
            if(histogram[i] > histogram[i - 1]
                    && histogram[i] > histogram[i - 2]
                    && histogram[i] > histogram[i + 1]
                    && histogram[i] > histogram[i + 2]){
                peaks.put(i, histogram[i]);
            }
        }

        return peaks;
    }

    private int getMaxFrequency() {
        int maxFrequency = 0;
        for(int frequency: histogram){
            if(frequency > maxFrequency){
                maxFrequency = frequency;
            }
        }
        return maxFrequency;
    }
}
