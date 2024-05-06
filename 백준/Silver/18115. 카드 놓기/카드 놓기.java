import java.awt.*;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.*;
import java.util.stream.IntStream;

public class Main {
    static int N;
    static int[] A;
    static LinkedList<Integer> answer;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        N = Integer.parseInt(br.readLine());

        StringTokenizer st = new StringTokenizer(br.readLine());

        A = new int[N];
        answer = new LinkedList<>();
        for (int i = 0; i < N; i++) {
            A[i] = Integer.parseInt(st.nextToken());
        }

        for (int i = N - 1; i >= 0; i--) {
            if (A[i] == 1) {
                answer.add(0, N - i); // 1
            } else if (A[i] == 2) { // 2번째
                answer.add(1, N - i);
            } else if (A[i] == 3) {
                answer.add(N - i);
            }
        }

        for (Integer e:answer) {
            bw.write(e + " ");
        }

        bw.flush();
        bw.close();
        br.close();
    }
}