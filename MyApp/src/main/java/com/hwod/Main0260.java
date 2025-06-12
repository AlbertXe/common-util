package com.hwod;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Scanner;
import java.util.Set;

/**
 * 最多几个直角三角形
 * @description:todo
 * @author: AlbertXe
 * @create: 2025-04-08 19:45
 */
public class Main0260 {

    public static int triangles(int N, int[] segments) {
        Arrays.sort(segments);
        int count = 0;
        Set<String> used = new HashSet<>();

        for (int i = 0; i < N; i++) {
            for (int j = i + 1; j < N; j++) {
                for (int k = j + 1; k < N; k++) {
                    int a = segments[i], b = segments[j], c = segments[k];

                    String combo = a + "," + b + "," + c;
                    if (used.contains(combo)) continue;

                    if (a * a + b * b == c * c) {
                        // a b c够用才能够组成三角形
                        count++;
                        used.add(combo);
                    }
                }
            }
        }
        return count;
    }

    public static void main(String[] args) {
        try (Scanner scanner = new Scanner(System.in)) {
            int T = scanner.nextInt();
            for (int t = 0; t < T; t++) {
                int N = scanner.nextInt();
                int[] segments = new int[N];
                for (int i = 0; i < N; i++) {
                    segments[i] = scanner.nextInt();
                }
                System.out.println(triangles(N, segments));
            }
        }
    }
}
