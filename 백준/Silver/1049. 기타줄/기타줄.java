import org.w3c.dom.ls.LSOutput;

import java.io.*;
import java.util.*;

public class Main {
    static int N, M;
    static int[][] band;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());

        band = new int[M][2];

        for (int i = 0; i < M; i++) {
            st = new StringTokenizer(br.readLine());

            band[i][0] = Integer.parseInt(st.nextToken());
            band[i][1] = Integer.parseInt(st.nextToken());
        }

        // 완탐이 아니라 그냥 그리디 기초.
        // 결국엔 가장 낮은것만 놓고 계산하면 됨.

        int minA = Integer.MAX_VALUE;
        int minB = Integer.MAX_VALUE;
        for (int i = 0; i < M; i++) {
            minA = Math.min(minA, band[i][0]);
            minB = Math.min(minB, band[i][1]);
        }

        int minBPrice = minB * N;
        int minAPrice = 0;

        if (N <= 6) {
            minAPrice = minA;
        }
        else{
            int q = N / 6;
            int r = N % 6;

            minAPrice = q * minA;
            int restPrice = minA > r * minB ? r * minB : minA;

            minAPrice += restPrice;
        }

        if (minAPrice > minBPrice) {
            System.out.println(minBPrice);
        }
        else{
            System.out.println(minAPrice);
        }
    }
}