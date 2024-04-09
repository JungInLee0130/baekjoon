import java.awt.*;
import java.io.*;
import java.math.BigInteger;
import java.util.*;

public class Main {
    static int[][] a;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        a = new int[2][2];

        for (int i = 0; i < 2; i++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            for (int j = 0; j < 2; j++) {
                a[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        int up = a[0][0] * a[1][1] + a[1][0] * a[0][1];
        int down = a[0][1] * a[1][1];

        int gcd = 1;
        for (int i = up; i >= 2; i--) {
            if (down % i == 0 && up % i == 0) {
                gcd = i;
                break;
            }
        }

        up /= gcd;
        down /= gcd;

        System.out.println(up + " " + down);
    }
}