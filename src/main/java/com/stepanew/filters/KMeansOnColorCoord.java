package com.stepanew.filters;

import com.stepanew.entities.ColorWithCoord;

import java.awt.*;
import java.awt.image.BufferedImage;
import java.util.HashSet;
import java.util.Random;
import java.util.Set;

public class KMeansOnColorCoord {
    private final ColorWithCoord[][] pixels;
    private final int clusters;
    private final int maxIterations;
    private final int width;
    private final int height;


    public KMeansOnColorCoord(BufferedImage image, int clusters, int maxIterations) {
        this.width = image.getWidth();
        this.height = image.getHeight();
        pixels = new ColorWithCoord[width][height];

        for(int x = 0; x < width; x++){
            for(int y = 0; y < height; y++){
                Color pixel = new Color(image.getRGB(x, y));
                ColorWithCoord myColor = new ColorWithCoord(pixel.getRed(), pixel.getGreen(), pixel.getBlue(),
                        x, y);
                pixels[x][y] = myColor;
            }
        }

        this.clusters = clusters;
        this.maxIterations = maxIterations;
    }

    public void kMeansColorAndCoordSegmentation() {
        ColorWithCoord[] centroids = new ColorWithCoord[clusters];
        Random random = new Random();

        for(int i = 0; i < clusters - 1; i++){
            centroids[i] = new ColorWithCoord(0,
                    0,
                    0,
                    random.nextInt(width),
                    random.nextInt(height));
        }

        centroids[clusters - 1] = new ColorWithCoord(255, 255, 255, random.nextInt(width), random.nextInt(height));

        int[][] labels = new int[width][height];

        for (int iteration = 0; iteration < maxIterations; iteration++) {
            assignLabelsColor(centroids, labels);
            updateCentroidsColor(centroids, labels);
        }
        visualizeClusters(labels, centroids);

        for (ColorWithCoord myColor: centroids){
            System.out.println(myColor);
        }
    }

    private void visualizeClusters(int[][] labels, ColorWithCoord[] centroids) {
        Color[] results = generateUniqueColors(clusters);
        Set<Integer> set = new HashSet<>();

        for (int i = 0; i < width; i++) {
            for (int j = 0; j < height; j++) {
                int label = labels[i][j];
                if (!set.contains(label)) {
                    System.out.println("Label " + label);
                    set.add(label);
                }
                Color setColor = results[label];
                pixels[i][j] = new ColorWithCoord(setColor.getRed(),
                        setColor.getGreen(),
                        setColor.getBlue(),
                        centroids[label].getX(),
                        centroids[label].getY());
            }
        }
    }

    private void updateCentroidsColor(ColorWithCoord[] centroids, int[][] labels) {
        int[] sumsR = new int[centroids.length];
        int[] sumsG = new int[centroids.length];
        int[] sumsB = new int[centroids.length];
        int[] sumsX = new int[centroids.length];
        int[] sumsY = new int[centroids.length];
        int[] counts = new int[centroids.length];

        for (int i = 0; i < width; i++) {
            for (int j = 0; j < height; j++) {
                int label = labels[i][j];
                sumsR[label] += pixels[i][j].getRed();
                sumsG[label] += pixels[i][j].getGreen();
                sumsB[label] += pixels[i][j].getBlue();
                sumsX[label] += pixels[i][j].getX();
                sumsY[label] += pixels[i][j].getY();
                counts[label]++;
            }
        }

        for (int i = 0; i < centroids.length; i++) {
            if (counts[i] != 0) {
                centroids[i].setRed(sumsR[i] / counts[i]);
                centroids[i].setGreen(sumsG[i] / counts[i]);
                centroids[i].setBlue(sumsB[i] / counts[i]);
                centroids[i].setX(sumsX[i] / counts[i]);
                centroids[i].setY(sumsY[i] / counts[i]);
            }
        }
    }

    private void assignLabelsColor(ColorWithCoord[] centroids, int[][] labels) {
        for (int i = 0; i < width; i++) {
            for (int j = 0; j < height; j++) {
                ColorWithCoord pixel = pixels[i][j];
                int minDistance = Integer.MAX_VALUE;
                int label = 0;

                for (int k = 0; k < centroids.length; k++) {
                    int distance = calculateColorAndCoordDistance(pixel, centroids[k]);

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
                ColorWithCoord myColor = pixels[i][j];
                Color color = new Color(myColor.getRed(), myColor.getGreen(), myColor.getBlue());
                bufferedImage.setRGB(i, j, color.getRGB());
            }
        }

        return bufferedImage;
    }

    private int calculateColorAndCoordDistance(ColorWithCoord pixel, ColorWithCoord centroid) {
        int red1 = pixel.getRed();
        int green1 = pixel.getGreen();
        int blue1 = pixel.getBlue();
        int x1 = pixel.getX();
        int y1 = pixel.getY();

        int red2 = centroid.getRed();
        int green2 = centroid.getGreen();
        int blue2 = centroid.getBlue();
        int x2 = centroid.getX();
        int y2 = centroid.getY();

        int deltaRed = red1 - red2;
        int deltaGreen = green1 - green2;
        int deltaBlue = blue1 - blue2;
        int deltaX = x1 - x2;
        int deltaY = y1 - y2;

        return ((deltaRed * deltaRed + deltaGreen * deltaGreen + deltaBlue * deltaBlue) * 10 + deltaX * deltaX + deltaY * deltaY);
    }

    private Color[] generateUniqueColors(int arraySize) {
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
