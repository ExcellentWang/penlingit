package com.ontheroad.utils;

public class ArrayUtil {

    public static Float compareMax(Float[] arr) {
        float max = arr[0];
        //求數組最大值
        for (int i = 0; i < arr.length; i++) {
            if (arr[i] > max)
                max = arr[i];
        }
        return max;
    }

    public static Float compareMin(Float[] arr) {
        float min = arr[0];
        //求數組最小值
        for (int j = 0; j < arr.length; j++) {
            if (arr[j] < min) min = arr[j];
        }
        return min;
    }

    public static Float compareSvg(Float[] arr) {
        Float sum = (float) 0.0;
        for (int k = 0; k < arr.length; k++) {
            sum += arr[k];
        }
        float avg = sum / arr.length;
        return avg;
    }

    public static boolean isContain(String str, String[] strs) {
        boolean flag = true;
        if (str != null) {
            for (int i = 0; i < strs.length; i++) {
                if (str.equals(strs[i])) {
                    flag = true;
                    break;
                }
            }
        }
        return flag;
    }
}
