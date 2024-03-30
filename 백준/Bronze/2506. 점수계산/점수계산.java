import java.awt.*;
import java.io.*;
import java.math.BigInteger;
import java.util.*;

public class Main {
    static int N;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        N = Integer.parseInt(br.readLine());

        StringTokenizer st = new StringTokenizer(br.readLine());
        int[] score = new int[N];
        for (int i = 0; i < N; i++) {
            score[i] = Integer.parseInt(st.nextToken());
        }

        int sum = 0;
        int[] sumScore = new int[N];

        for (int i = 0; i < N; i++) {
            if (score[i] == 1) {
                sum += 1;
                sumScore[i] = sum;
            }
            if (score[i] == 0) {
                sum = 0;
                sumScore[i] = 0;
            }
        }

        sum = 0;
        for (int i = 0; i < N; i++) {
            sum += sumScore[i];
        }

        System.out.println(sum);
    }
}