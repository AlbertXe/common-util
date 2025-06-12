package com.hwod;

import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

/**
 * City1=1.1.1.1,1.1.1.2;City1=1.1.1.11,1.1.1.16;City2=3.3.3.3,4.4.4.4;City3=2.2.2.2,6.6.6.6
 * 1.1.1.15,3.3.3.5,2.2.2.3
 * 根据IP查找城市
 * @description:todo
 * @author: AlbertXe
 * @create: 2025-04-08 19:45
 */
public class Main0267 {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        String ipRanges = scanner.nextLine();
        String ips = scanner.nextLine();
        System.out.println(findCities(ipRanges, ips));
        scanner.close();
    }

    private static long ipToInt(String ip) {
        String[] parts = ip.split("\\.");
        return (long) Integer.parseInt(parts[0]) << 24
                | Integer.parseInt(parts[1]) << 16
                | Integer.parseInt(parts[2]) << 8
                | Integer.parseInt(parts[3]);
    }

    private static String findCity(String ip, Map<Long, String> ranges) {
        long ipInt = ipToInt(ip);
        String bestMatch = null;
        long bestRange = Long.MAX_VALUE;
        for (Map.Entry<Long, String> entry : ranges.entrySet()) {
            long range = entry.getKey();
            long startIp = range >> 32;
//            将地位 32位提取出来
            long endIp = range & 0xFFFFFFFFL;
            if (startIp <= ipInt && ipInt <= endIp) {
                long currentRange = endIp - startIp;
                if (bestMatch == null || currentRange < bestRange) {
                    bestMatch = entry.getValue();
                    bestRange = currentRange;
                }
            }
        }
        return bestMatch;
    }

    public static String findCities(String ipRangesStr, String ipsStr) {
        Map<Long, String> ranges = new HashMap<>();
        for (String part : ipRangesStr.split(";")) {
            String[] cityAndRange = part.split("=");
            String city = cityAndRange[0];
            String[] startEnd = cityAndRange[1].split(",");
            // ip
            long startIp = ipToInt(startEnd[0]);
            long endIp = ipToInt(startEnd[1]);
            // 64位 前32开始 后32位结束
            ranges.put((startIp << 32) | endIp, city);
        }
        String[] ips = ipsStr.split(",");
        StringBuilder result = new StringBuilder();
        for (String ip : ips) {
            if (result.length() > 0) {
                result.append(",");
            }
            result.append(findCity(ip, ranges));
        }
        return result.toString();
    }
}
