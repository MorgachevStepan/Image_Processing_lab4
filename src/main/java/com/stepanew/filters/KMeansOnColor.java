package com.stepanew.filters;

import com.stepanew.entities.MyColor;
import com.stepanew.utils.ColorGenerator;

import java.awt.*;
import java.awt.image.BufferedImage;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class KMeansOnColor {
    private final MyColor[][] pixels;
    private final int clusters;
    private final int maxIterations;
    private final int width;
    private final int height;
    private final List<Integer> peaks;
    private final ColorGenerator colorGenerator;

    public KMeansOnColor(BufferedImage image, int clusters, int maxIterations, List<Integer> peaks, ColorGenerator colorGenerator) {
        this.width = image.getWidth();
        this.height = image.getHeight();
        pixels = new MyColor[width][height];

        for(int x = 0; x < width; x++){
            for(int y = 0; y < height; y++){
                Color pixel = new Color(image.getRGB(x, y));
                MyColor myColor = new MyColor(pixel.getRed(), pixel.getBlue(), pixel.getGreen());
                pixels[x][y] = myColor;
            }
        }

        this.clusters = clusters;
        this.maxIterations = maxIterations;
        this.peaks = peaks;
        this.colorGenerator = colorGenerator;
    }

    public void kMeansColorSegmentation() {
        MyColor[] centroids = new MyColor[clusters];

        for(int i = 0; i < clusters; i++){
            centroids[i] = new MyColor(peaks.get(i), peaks.get(i), peaks.get(i));
        }

        int[][] labels = new int[width][height];

        for (int iteration = 0; iteration < maxIterations; iteration++) {
            assignLabelsColor(centroids, labels);
            updateCentroidsColor(centroids, labels);
        }

        for (MyColor myColor: centroids){
            System.out.println(myColor);
        }
        visualizeClusters(labels, centroids);
    }

    private void visualizeClusters(int[][] labels, MyColor[] centroids) {
        Color[] results = colorGenerator.generateUniqueColors(clusters);
        Set<Integer> set = new HashSet<>();

        for (int i = 0; i < width; i++) {
            for (int j = 0; j < height; j++) {
                int label = labels[i][j];
                if (!set.contains(label)) {
                    System.out.println("Label " + label);
                    set.add(label);
                }
                Color setColor = results[label];
                pixels[i][j] = centroids[label];
            }
        }
    }

    private void updateCentroidsColor(MyColor[] centroids, int[][] labels) {
        int[] sumsR = new int[centroids.length];
        int[] sumsG = new int[centroids.length];
        int[] sumsB = new int[centroids.length];
        int[] counts = new int[centroids.length];

        for (int i = 0; i < width; i++) {
            for(int j = 0; j < height; j++) {
                sumsR[labels[i][j]] += pixels[i][j].getRed();
                sumsG[labels[i][j]] += pixels[i][j].getGreen();
                sumsB[labels[i][j]] += pixels[i][j].getBlue();
                counts[labels[i][j]]++;
            }
        }

        for (int i = 0; i < centroids.length; i++) {
            if(counts[i] == 0){
                continue;
            }else {
                centroids[i] = new MyColor((int) (sumsR[i] / counts[i]),
                        (int) (sumsG[i] / counts[i]),
                        (int) (sumsB[i] / counts[i]));
            }
        }
    }

    private void assignLabelsColor(MyColor[] centroids, int[][] labels) {
        for (int i = 0; i < width; i++) {
            for (int j = 0; j < height; j++) {
                MyColor pixel = pixels[i][j];
                int minDistance = Integer.MAX_VALUE;
                int label = 0;

                for (int k = 0; k < centroids.length; k++) {
                    int distance = calculateColorDistance(pixel, centroids[k]);

                    if (distance < minDistance) {
                        minDistance = distance;
                        label = k;
                    }
                }

                labels[i][j] = label;
            }
        }
    }

    public BufferedImage restoreImage() {
        BufferedImage bufferedImage = new BufferedImage(width, height, BufferedImage.TYPE_INT_RGB);

        for(int i = 0; i < width; i++){
            for(int j = 0; j < height; j++){
                MyColor myColor = pixels[i][j];
                Color color = new Color(myColor.getRed(), myColor.getGreen(), myColor.getBlue());
                bufferedImage.setRGB(i, j, color.getRGB());
            }
        }

        return bufferedImage;
    }

    private int calculateColorDistance(MyColor pixel, MyColor centroid) {
        int red1 = pixel.getRed();
        int green1 = pixel.getGreen();
        int blue1 = pixel.getBlue();

        int red2 = centroid.getRed();
        int green2 = centroid.getGreen();
        int blue2 = centroid.getBlue();

        int deltaRed = red1 - red2;
        int deltaGreen = green1 - green2;
        int deltaBlue = blue1 - blue2;

        return (int)Math.sqrt(deltaRed * deltaRed + deltaGreen * deltaGreen + deltaBlue * deltaBlue);
    }
}
