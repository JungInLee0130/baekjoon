import org.w3c.dom.Node;

import java.io.*;
import java.util.*;

public class Main {
    static int P, T;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        P = Integer.parseInt(br.readLine());

        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < P; i++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            int T = Integer.parseInt(st.nextToken());

            int[] A = new int[20];

            A[0] = Integer.parseInt(st.nextToken());
            for (int j = 1; j < 20; j++) {
                A[j] = Integer.parseInt(st.nextToken());
            }

            int count = 0;
            for (int j = 0; j < 20; j++) {
                for (int k = 0; k <= j; k++) {
                    if (A[k] > A[j]) count++;
                }
            }

            sb.append(T + " " + count + "\n");
        }

        System.out.println(sb.toString());

        bw.flush();
        br.close();
        bw.close();
    }
}