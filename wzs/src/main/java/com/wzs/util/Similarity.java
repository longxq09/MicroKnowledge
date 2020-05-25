package com.wzs.util;

public class Similarity {

    private static int dim = 10;

    public static double calCosineSimilarity(int[] arr1,int[] arr2) {
        double molecular = 0;
        for (int i = 0;i < dim;++i) {
            molecular += arr1[i] * arr2[i];
        }
        double comp1 = 0, comp2 = 0;

        for (int i = 0;i < dim;++i ) {
            comp1 += arr1[i] * arr1[i];
        }
        comp1 = Math.sqrt(comp1);

        for (int i = 0;i < dim;++i ) {
            comp2 += arr2[i] * arr2[i];
        }
        comp2 = Math.sqrt(comp2);

        return molecular / (comp1 * comp2);
    }

}
