import java.awt.*;
import java.io.*;
import java.math.BigInteger;
import java.util.*;

public class Main {
    static String S;
    static int min = Integer.MAX_VALUE;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        S = br.readLine(); // 100만 보다 작음

        int n = S.length();
        // split 연속된거 나오는 기준으로
        // 0 : false 1 : true

        counting(S, n, false);

        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < n; i++) {
            if (S.charAt(i) == '0') {
                sb.append('1');
            }
            else{
                sb.append('0');
            }
        }

        System.out.println(min);
    }

    private static void counting(String S, int n, boolean isReverse) {
        boolean isOne;
        int[] count = new int[2];

        if (S.charAt(0) == '0') {
            isOne = false;
            count[0]++;
        } else {
            isOne = true;
            count[1]++;
        }

        for (int i = 1; i < n; i++) {
            int num = S.charAt(i) - '0';

            if (num == 0) {
                if (isOne) {
                    count[0]++;
                    isOne = false;
                }
                // 계속 0이면 그냥 continue
            }
            else{
                if (!isOne) {
                    count[1]++;
                    isOne = true;
                }
            }
        }

        if (count[0] < count[1]) {
            min = Math.min(count[0], min);

        }
        else{
            min = Math.min(count[1], min);
        }
        //System.out.println(count[0]+ " " + count[1]);
    }
}