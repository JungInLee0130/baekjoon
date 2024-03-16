import java.awt.*;
import java.io.*;
import java.math.BigInteger;
import java.util.*;

public class Main {
    static int N;
    static long[][] count;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        N = Integer.parseInt(br.readLine());

        count = new long[N + 1][3]; // N은 최대 10만.
        // [i][0] : 아무것도 안놓는경우
        // [i][1] : 첫번째 칸에 놓는경우
        // [i][2] : 두번째 칸에 놓는경우

        count[1][0] = 1;
        count[1][1] = 1;
        count[1][2] = 1;

        // count[2][0] = count[1][0] + count[1][1] + count[1][2]
        // count[2][1] = count[1][0] + count[1][2];
        // count[2][2] = count[1][0] + count[1][1];

        for (int i = 2; i <= N; i++) {
            count[i][0] = (count[i - 1][0] + count[i - 1][1] + count[i - 1][2]) % 9901;
            count[i][1] = (count[i - 1][0] + count[i - 1][2]) % 9901;
            count[i][2] = (count[i - 1][0] + count[i - 1][1]) % 9901;
        }

        long answer = (count[N][0] + count[N][1] + count[N][2]) % 9901;

        System.out.println(answer);
    }
}