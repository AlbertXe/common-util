package com.hwod;

import java.util.Scanner;

/**
 * 补种未成活胡杨
 * @description:todo
 * @author: AlbertXe
 * @create: 2025-04-08 19:45
 */
public class Main0256 {

    public static void main(String[] args) {
        try (Scanner scanner = new Scanner(System.in)) {
            // 总树数目
            int N = scanner.nextInt();
            int M = scanner.nextInt();

            int[] indies = new int[M];
            for (int i = 0; i < M; i++) {
                indies[i] = scanner.nextInt();
            }

            int K = scanner.nextInt();
            System.out.println(solution(N, M, indies, K));
        }

    }

    public static int solution(int n, int m, int[] indies, int k) {
        int maxLen = 0;

        // m 未成活 2   k 补种1
        for (int i = 0; i <= m - k; i++) {
            if (i == 0) {
                // 补左边
                // indies[1]-1=4-1=3
                maxLen = Math.max(maxLen, indies[k] - 1);
            } else if (i == m - k) { // 将最后一颗补活
                // 5-index[0]=5-2=3
                maxLen = Math.max(maxLen, n - indies[i - 1]);
            } else {
                // 补中间
                maxLen = Math.max(maxLen, indies[i + k] - indies[i - 1] - 1);
            }
        }
        return maxLen;
    }

    public static int solution2(int n, int m, int[] indies, int k) {
        int maxLen = 0;

        // m 未成活 2   k 补种1
        // 补种一颗
        for (int i = 0; i <= m - 1; i++) {
            if (i == 0) {
                // 补左边
                // indies[1]-1=4-1=3
                maxLen = Math.max(maxLen, indies[1] - 1);
            } else if (i == m - 1) { // 将最后一颗补活
                // 5-index[0]=5-2=3
                maxLen = Math.max(maxLen, n - indies[i - 1]);
            } else {
                // 补中间
                maxLen = Math.max(maxLen, indies[i + 1] - indies[i - 1] - 1);
            }
        }
        return maxLen;
    }
}
