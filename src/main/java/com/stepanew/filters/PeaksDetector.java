package com.stepanew.filters;

import java.util.ArrayList;
import java.util.List;

public class PeaksDetector {
    public static List<Integer> findPeaks(List<Integer> histogram){
        int max = otsuThreshold(histogram);
        double average = calculateAverage(histogram);
        List<Integer> expandHist = initTemp(histogram);
        List<Integer> result = new ArrayList<>();

        for(int i = 1; i < expandHist.size() - 1; i++){
            if(expandHist.get(i) > expandHist.get(i - 1)
                    && expandHist.get(i) > expandHist.get(i + 1)
                    && expandHist.get(i) > 2.5 * average) {
                result.add(i - 1);
            }
        }

        return result;
    }

    public static int otsuThreshold(List<Integer> histogram) {
        int total = histogram.size();
        int sum = 0;
        for (int i = 0; i < total; i++) {
            sum += i * histogram.get(i);
        }

        double sumB = 0;
        int wB = 0;
        int wF = 0;

        double maxVariance = 0;
        int threshold = 0;

        for (int i = 0; i < total; i++) {
            wB += histogram.get(i);
            if (wB == 0) {
                continue;
            }

            wF = total - wB;
            if (wF == 0) {
                break;
            }

            sumB += i * histogram.get(i);

            double mB = sumB / wB;
            double mF = (sum - sumB) / wF;

            double varianceBetween = wB * wF * Math.pow((mB - mF), 2);

            if (varianceBetween > maxVariance) {
                maxVariance = varianceBetween;
                threshold = i;
            }
        }

        return threshold;
    }

    private static List<Integer> initTemp(List<Integer> histogram){
        List<Integer> temp = new ArrayList<>();
        temp.add(0);
        temp.addAll(histogram);
        temp.add(0);

        return temp;
    }

    private static double calculateAverage(List<Integer> list) {
        int sum = 0;
        for (Integer value : list) {
            sum += value;
        }
        return (double) sum / list.size();
    }
}
