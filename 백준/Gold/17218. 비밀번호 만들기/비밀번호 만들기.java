import java.io.*;
import java.util.*;

public class Main {
    static String A, B;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        A = br.readLine();
        B = br.readLine();

        lenA = A.length();
        lenB = B.length();

        char[] AChar = new char[lenA + 1]; // 1 ~
        char[] BChar = new char[lenB + 1]; // 1 ~

        for (int i = 1; i <= lenA; i++) {
            AChar[i] = A.charAt(i - 1);
        }

        for (int i = 1; i <= lenB; i++) {
            BChar[i] = B.charAt(i - 1);
        }

        dp = new int[lenA + 1][lenB + 1];
        for (int a = 1; a <= lenA; a++) {
            for (int b = 1; b <= lenB; b++) {
                if (AChar[a] == BChar[b]) {
                    dp[a][b] = dp[a - 1][b - 1] + 1;
                    continue;
                }

                dp[a][b] = Math.max(dp[a - 1][b], dp[a][b - 1]);
            }
        }

        //System.out.println(dp[lenA][lenB]);

        //print();

        // 역추적

        StringBuilder sb = new StringBuilder();
        int idxA = lenA;
        int idxB = lenB;

        while (!(dp[idxA][idxB] == 0)){
            if (AChar[idxA] == BChar[idxB]) {
                sb.append(AChar[idxA]);
                idxA--;
                idxB--;
                continue;
            }

            int preA = dp[idxA - 1][idxB];
            int preB = dp[idxA][idxB - 1];

            if (preA > preB) {
                idxA -= 1;
            }
            else{
                idxB -= 1;
            }
        }

        System.out.println(sb.reverse().toString());
    }

    private static int[][] dp;
    private static int lenA, lenB;
    private static void print() {
        for (int i = 0; i <= lenA; i++) {
            for (int j = 0; j <= lenB; j++) {
                System.out.print(dp[i][j] + " ");
            }
            System.out.println();
        }
    }

}