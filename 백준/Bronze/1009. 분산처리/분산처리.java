import java.io.*;
import java.util.*;

public class Main {
    static int T;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        T = Integer.parseInt(br.readLine());

        for (int t = 1; t <= T; t++) {
            StringTokenizer st = new StringTokenizer(br.readLine());

            int a = Integer.parseInt(st.nextToken());
            int b = Integer.parseInt(st.nextToken());

            int modA = a % 10;

            int[] rest = {1, 1, 4, 4, 2, 1, 1, 4, 4, 2}; // 0 ~ 9

            int modB = b % rest[modA];

            if (modB == 0) {
                modB = rest[modA];
            }

            int ans = ((int) Math.pow(modA, modB)) % 10;

            if (ans == 0) {
                System.out.println(10);
            } else {
                System.out.println(ans);
            }
        }
    }
}