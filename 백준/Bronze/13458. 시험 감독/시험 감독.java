import java.awt.*;
import java.io.*;
import java.math.BigInteger;
import java.util.*;

public class Main {
    static int N;
    static int[] A;
    static int B, C;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        N = Integer.parseInt(br.readLine());
        StringTokenizer st = new StringTokenizer(br.readLine());
        A = new int[N];
        for (int i = 0; i < N; i++) {
            A[i] = Integer.parseInt(st.nextToken());
        }
        st = new StringTokenizer(br.readLine());

        B = Integer.parseInt(st.nextToken());
        C = Integer.parseInt(st.nextToken());

        // B : 1 C : 여러명
        // B한명 필수
        // A[i] = B * 1 + C * ?
        // A[i] = C * ?? (X)
        // 그리디
        long res = 0;

        for (int i = 0; i < N; i++) {
            int num = 1; // B
            int rest = A[i] - B;
            if (rest > 0) {
                if (rest % C > 0) {
                    num += 1;
                }
                num += rest / C;
            }

            res += num;
        }

        System.out.println(res);


        bw.flush();
        bw.close();
        br.close();
    }
}