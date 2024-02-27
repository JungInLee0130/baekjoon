import java.awt.*;
import java.io.*;
import java.util.*;

public class Main {
    static int N;
    static String[] strs;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        N = Integer.parseInt(br.readLine());

        strs = new String[N];

        for (int i = 0; i < N; i++) {
            strs[i] = br.readLine();
        }

        int len = strs[0].length();

        // 기준 strs[0]
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < len; i++) { // 행
            char ch = strs[0].charAt(i);

            boolean isSame = true;
            for (int j = 1; j < N; j++) {
                if (ch != strs[j].charAt(i)) {
                    isSame = false;
                    sb.append("?");
                    break;
                }
            }
            if (isSame) {
                sb.append(strs[0].charAt(i));
            }
        }

        System.out.println(sb.toString());
    }
}