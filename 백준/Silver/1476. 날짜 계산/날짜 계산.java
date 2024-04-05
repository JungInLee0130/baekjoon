import java.awt.*;
import java.io.*;
import java.math.BigInteger;
import java.util.*;

public class Main {
    static int E, S, M;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        StringTokenizer st = new StringTokenizer(br.readLine());

        E = Integer.parseInt(st.nextToken()); // 15이하
        S = Integer.parseInt(st.nextToken()); // 28이하
        M = Integer.parseInt(st.nextToken()); // 19이하

        int num = 1;
        int e = 1;
        int s = 1;
        int m = 1;
        while (!check(e,s,m)) {
            num++;
            e++;
            s++;
            m++;
            if (e >= 16) {
                e = 1;
            }
            if (s >= 29) {
                s = 1;
            }
            if (m >= 20) {
                m = 1;
            }
        }
        System.out.println(num);
    }

    private static boolean check(int e, int s, int m) {
        if (e == E && s == S && m == M) {
            return true;
        }
        return false;
    }
}