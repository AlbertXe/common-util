package com.hwod;

import java.util.Scanner;

/**
 * 字符串划分 分割三个子串 权重一致
 * @description:todo
 * @author: AlbertXe
 * @create: 2025-04-08 19:45
 */
public class Main0259 {

    public static void main(String[] args) {
        try (Scanner scanner = new Scanner(System.in)) {
            String str = scanner.nextLine();
            solution(str);
        }
    }

    private static void solution(String str) {
        int n = str.length();

        int[] prefixSum = new int[n + 1];
        // 保存从左到右的总值
        for (int i = 0; i < n; i++) {
            prefixSum[i + 1] = prefixSum[i] + str.charAt(i);
        }

        // 双指针
        int i = 1, j = 2;
        while (j < n) {
            // left
            int asciiSumI = prefixSum[i] - prefixSum[0];
            // mid
            int asciiSumJ = prefixSum[j] - prefixSum[i + 1];
            // right
            int asciiSumK = prefixSum[n] - prefixSum[j + 1];

            if (asciiSumI == asciiSumJ && asciiSumJ == asciiSumK) {
                System.out.println(i + "," + j);
                return;
            }

            if (asciiSumI <= asciiSumJ) {
                i++;
            } else {
                j++;
            }
        }

        System.out.println("0,0");
    }
}
