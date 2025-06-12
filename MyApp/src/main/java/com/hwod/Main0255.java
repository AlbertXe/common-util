package com.hwod;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.List;
import java.util.PriorityQueue;
import java.util.Scanner;

/**
 * 3 3
 * 1 2 11
 * 2 3 13
 * 1 3 50
 * 1 3
 * 最小传输时延
 * @description:todo
 * @author: AlbertXe
 * @create: 2025-04-08 19:45
 */
public class Main0255 {

    public static void main(String[] args) {
        try (Scanner scanner = new Scanner(System.in)) {
            // 节点个数 与延时表长度
            int N = scanner.nextInt();
            int M = scanner.nextInt();
            List<List<int[]>> graph = new ArrayList<>(N + 1);
            for (int i = 0; i <= N; i++) {
                graph.add(new ArrayList<>());
            }

            for (int i = 0; i < M; i++) {
                int u = scanner.nextInt();
                int v = scanner.nextInt();
                int w = scanner.nextInt();
                // 起始节点集合 一个起始节点有多个目标
                graph.get(u).add(new int[]{v, w});
            }

            int source = scanner.nextInt();
            int target = scanner.nextInt();
            int result = dijkstra(graph, source, target, N);
            System.out.println(result == Integer.MAX_VALUE ? -1 : result);
        }

    }

    private static int dijkstra(List<List<int[]>> graph, int source, int target, int N) {
        // 队列以数组的第二位 顺序排列
        PriorityQueue<int[]> heap = new PriorityQueue<>(Comparator.comparingInt(a -> a[1]));
        heap.offer(new int[]{source, 0});
        boolean[] visited = new boolean[N + 1];
        int[] distance = new int[N + 1];
        Arrays.fill(distance, Integer.MAX_VALUE);

        distance[source] = 0;

        while (!heap.isEmpty()) {
            int[] current = heap.poll();
            //节点
            int node = current[0];
            //耗时 0
            int dis = current[1];

            if (node == target) {
                return dis;
            }
            if (visited[node]) continue;

            visited[node] = true;
            for (int[] edge : graph.get(node)) {
                int next = edge[0];
                int nextDis = edge[1] + dis;

                if (nextDis < distance[next]) {
                    distance[next] = nextDis;
                    // 这里放  取的时候取耗时最短的
                    heap.offer(new int[]{next, nextDis});
                }
            }
        }

        return Integer.MAX_VALUE;
    }
}
