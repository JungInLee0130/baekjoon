import java.awt.*;
import java.io.*;
import java.util.*;

public class Main {
    static int N, M;
    static int[] A, B;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());

        A = new int[N];
        B = new int[M];

        st = new StringTokenizer(br.readLine());

        StringBuilder sb = new StringBuilder();

        for (int i = 0; i < N; i++) {
            A[i] = Integer.parseInt(st.nextToken());
        }
        st = new StringTokenizer(br.readLine());
        for (int i = 0; i < M; i++) {
            B[i] = Integer.parseInt(st.nextToken());
        }

        int indexA = 0;
        int indexB = 0;

        while (indexA < N || indexB < M) {
            if (indexA == N) {
                sb.append(B[indexB++] + " ");
                continue;
            }
            if (indexB == M) {
                sb.append(A[indexA++] + " ");
                continue;
            }

            if (A[indexA] < B[indexB]) {
                sb.append(A[indexA++] + " ");
            }
            else{
                sb.append(B[indexB++] + " ");
            }
        }

        System.out.print(sb.toString());
    }

    static int[] list;

}