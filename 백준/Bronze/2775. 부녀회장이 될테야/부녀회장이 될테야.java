import java.io.*;
import java.math.BigInteger;
import java.util.Arrays;
import java.util.Collections;
import java.util.Comparator;
import java.util.StringTokenizer;

public class Main {
    // 0층, 1호부터 있음.
    public static int people(int[][] apartment, int k, int n) {
        if (n == 1) {
            return apartment[k - 1][1];
        }
        //apartment[k][n] = apartment[k - 1][1] + ... + apartment[k - 1][n];
        for (int i = 1; i <= n; i++) {
            apartment[k][n] += apartment[k - 1][i];
        }
        return apartment[k][n];
    }
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        int T = Integer.parseInt(br.readLine());

        for (int i = 0; i < T; i++) {
            int k = Integer.parseInt(br.readLine());
            int n = Integer.parseInt(br.readLine());

            // k층 n호
            int[][] apartment = new int[k + 1][n + 1];

            for (int j = 0; j <= k; j++) {
                for (int l = 0; l <= n; l++) {
                    apartment[j][l] = 0;
                }
            }

            for (int j = 1; j <= n; j++) {
                apartment[0][j] = j;
            }

            if (k >= 1) {
                for (int j = 1; j <= k; j++) {
                    for (int l = 1; l <= n; l++) {
                        apartment[j][l] = people(apartment, j, l);
                    }
                }
            }

            bw.write(String.valueOf(apartment[k][n]) + "\n");
        }

        bw.flush();
        br.close();
        bw.close();
    }
}
