import java.awt.*;
import java.io.*;
import java.math.BigInteger;
import java.util.*;

public class Main {
    static int M;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        M = Integer.parseInt(br.readLine());

        int ballIndex = 1;
        for (int i = 0; i < M; i++) {
            StringTokenizer st = new StringTokenizer(br.readLine());

            int x = Integer.parseInt(st.nextToken());
            int y = Integer.parseInt(st.nextToken());

            // 1~3

            // 공은 처음 1에
            if (x == ballIndex) { // x가 ball이 들어있는 위치이면
                ballIndex = y;
            }
            else if (y == ballIndex) {
                ballIndex = x;
            }
        }

        System.out.println(ballIndex);
    }
}