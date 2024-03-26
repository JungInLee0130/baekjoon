import java.awt.*;
import java.io.*;
import java.math.BigInteger;
import java.util.*;

public class Main {

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        // 기차안에 사람이 가장많을때의 사람수
        // 내린사람빼고, 탄사람 더한후 = 결과값중에 가장큰곳
        int count = 0;
        int max = Integer.MIN_VALUE;

        for (int i = 0; i < 10; i++) {
            StringTokenizer st = new StringTokenizer(br.readLine());

            int out = Integer.parseInt(st.nextToken());
            int in = Integer.parseInt(st.nextToken());

            count -= out;
            count += in;

            max = Math.max(max, count);
        }

        System.out.println(max);
    }
}