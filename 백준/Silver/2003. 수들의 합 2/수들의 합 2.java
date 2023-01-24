
import java.io.*;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Main {
    static int N;
    static int M;
    static int[] array;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());

        st = new StringTokenizer(br.readLine());
        array = new int[N];
        for (int i = 0; i < N; i++) {
            array[i] = Integer.parseInt(st.nextToken());
        }

        int sum = 0;

        int start = 0;
        int end = 0;
        int count = 0;
        /*Arrays.sort(array);*/

        while (true) {
            if (sum >= M) { // 맞음
                sum -= array[start++];
            } else if (end == N) {
                break;
            } else {
                sum += array[end++];
            }
            if (sum == M) { // count 맞음
                count++;
            }
        }

        bw.write(String.valueOf(count));

        bw.flush();
        br.close();
        bw.close();
    }
}
