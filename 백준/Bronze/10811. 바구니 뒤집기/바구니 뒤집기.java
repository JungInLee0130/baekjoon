import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.sql.SQLOutput;
import java.util.*;
import java.util.stream.IntStream;

public class Main {
    static int N, M;
    static int[] array;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());

        array = new int[N + 1];

        for (int i = 0; i <= N; i++) {
            array[i] = i;
        }

        for (int i = 0; i < M; i++) {
            st = new StringTokenizer(br.readLine());

            int start = Integer.parseInt(st.nextToken());
            int end = Integer.parseInt(st.nextToken());

            int t = 0;
            while (t < (end - start + 1) / 2) {
                int temp = array[start + t];
                array[start + t] = array[end - t];
                array[end - t] = temp;
                t++;
            }
        }

        Arrays.stream(array)
                .filter(e -> 1 <= e && e < array.length)
                .forEach(e -> System.out.print(e + " "));


        bw.flush();
        bw.close();
        br.close();
    }
}