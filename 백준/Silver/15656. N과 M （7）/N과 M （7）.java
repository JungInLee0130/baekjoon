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

        Arrays.sort(Ns);

        sol();

        System.out.println(sb.toString());
    }

    private static StringBuilder sb;
    private static void sol() {
        selected = new int[M];
        sb = new StringBuilder();
        duplPermu(0);
    }

    private static int[] selected;
    private static void duplPermu(int cnt) {
        if (cnt == M) {
            sbappend();
            return;
        }

        for (int i = 0; i < N; i++) {
            // 7,1 같은것도 포함하니, 0부터 시작, 중복 순열
            // 이건 배열 만들어야할듯.
            selected[cnt] = Ns[i]; // cnt <- i
            duplPermu(cnt + 1); // 그냥 자신 포함해주면됨.
        }
    }

    private static void sbappend() {
        for (int i = 0; i < M; i++) {
            sb.append(selected[i] + " ");
        }
        sb.append("\n");
    }
}