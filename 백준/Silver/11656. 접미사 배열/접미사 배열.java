import java.io.*;
import java.util.*;

class Main {
    static String S;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        S = br.readLine(); // 길이 1000

        PriorityQueue<String> pq = new PriorityQueue<>();

        int N = S.length();
        for (int i = 0; i < N; i++) {
            pq.add(S.substring(i));
        }

        StringBuilder sb = new StringBuilder();
        while (!pq.isEmpty()) {
            sb.append(pq.poll() + "\n");
        }

        System.out.print(sb);





        br.close();
    }
}