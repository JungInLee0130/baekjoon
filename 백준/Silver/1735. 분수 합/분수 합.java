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
        // gcd(a,b) = gcd(b,r)
        // r == 0 -> gcd = b
        int a = down;
        int b = up;
        int r = down % up;
        while (true) { // true는 안쓰는게 좋을꺼같은데
            // execute
            r = a % b; // r
            
            // condition
            if (r == 0) {
                gcd = b;
                break;
            }
            
            // result
            a = b;
            b = r;
        }

        up /= gcd;
        down /= gcd;

        System.out.println(up + " " + down);
    }
}