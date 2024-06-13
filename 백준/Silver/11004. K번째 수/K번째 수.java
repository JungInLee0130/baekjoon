import java.io.*;
import java.util.Arrays;
import java.util.Comparator;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

public class Main {
    static int N,K;
    static int[] a;
    static PriorityQueue<Integer> pq = new PriorityQueue<>();
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());
        K = Integer.parseInt(st.nextToken());

        st = new StringTokenizer(br.readLine());

        for (int i = 0; i < N; i++) { // 100만
            pq.add(Integer.parseInt(st.nextToken()));
        }

        // 1,000,000,000
        int t = 0;
        while (t < K) {
            t++;
            Integer poll = pq.poll();
            if (t == K) {
                System.out.println(poll);
                break;
            }
        }

        bw.flush();
        br.close();
        bw.close();
    }
}