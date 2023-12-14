package com.stepanew;

import com.stepanew.filters.*;
import com.stepanew.utils.ColorGenerator;
import com.stepanew.utils.FileReader;

import java.awt.image.BufferedImage;

public class Main {
    public static final int CLUSTERS = 3;
    public static final int ITERATIONS = 100;
    public static final String PNG = "png";
    public static final String FIRST_SAMPLE = "src/main/resources/img";

    public static final String SECOND_SAMPLE = "src/main/resources/img_1";
    public static final String THIRD_SAMPLE = "src/main/resources/img_2";
    public static final String FOURTH_SAMPLE = "src/main/resources/img_3";
    public static void main(String[] args) {
        FileReader fileReader = new FileReader();
        ColorGenerator colorGenerator = new ColorGenerator();
        Histogram histogram = new Histogram(fileReader);

        BufferedImage image = fileReader.ReadImage(THIRD_SAMPLE + "." + PNG);
        image = GrayScale.grayScale(image);
        histogram.buildHistogram(image);
        histogram.plotHistogram();

        /*List<Integer> peaks = PeaksDetector.findPeaks(histogram.getHistogram());

        for(int i: peaks){
            System.out.println("Peaks in my method: " + i);
        }

        KMeansOnColor kMeanClustering = new KMeansOnColor(image, peaks.size(), ITERATIONS, peaks, colorGenerator); //For first and second

        kMeanClustering.kMeansColorSegmentation();
        image = kMeanClustering.restoreImage();

        fileReader.SaveImage(image, PNG, SECOND_SAMPLE + peaks.size() + "." + PNG);*/

        /*KMeansOnColorCoord myColorWithCoord = new KMeansOnColorCoord(image, CLUSTERS, ITERATIONS, colorGenerator);

        myColorWithCoord.kMeansColorAndCoordSegmentation();                                         //For third and fourth
        image = myColorWithCoord.restoreImage();


        fileReader.SaveImage(image, PNG, THIRD_SAMPLE + CLUSTERS + ".png");*/
    }
}
