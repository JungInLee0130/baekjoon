import java.io.*;
import java.util.*;

public class Main {
    static int N, M;
    static int[] Ns;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());

        st = new StringTokenizer(br.readLine());
        Ns = new int[N];
        for (int i = 0; i < N; i++) {
            Ns[i] = Integer.parseInt(st.nextToken());
        }

        // 정렬
        Arrays.sort(Ns);

        sol();

    }

    private static LinkedHashSet<String> set;

    private static void sol() {
        set = new LinkedHashSet<>();
        combi(0, 0, "");
        print();
    }

    private static void print() {
        StringBuilder sb = new StringBuilder();
        for (String str : set) {
            sb.append(str + "\n");
        }

        System.out.print(sb.toString());
    }

    private static void combi(int start, int cnt, String str) {
        if (cnt == M){
            set.add(str);
            return;
        }

        for (int i = start; i < N; i++) {
            combi(i + 1, cnt + 1, str + Ns[i] + " ");
        }
    }
}