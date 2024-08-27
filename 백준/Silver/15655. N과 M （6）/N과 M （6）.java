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

        // 길이가 M인 수열 모두 구하기
        // 모두 오름차순 조합?

        sol();

    }

    private static void sol() {
        visited = new boolean[N];

        combi(0,0);
    }

    private static boolean[] visited;
    private static void combi(int start, int cnt) {
        if (cnt == M) {
            // 다른 배열에 넣어서 sort한후 출력
            ArrayList<Integer> list = select();
            print(list);
        }

        for (int i = start; i < N; i++) {
            visited[i] = true;
            combi(i + 1, cnt + 1);
            visited[i] = false;
        }
    }

    private static void print(ArrayList<Integer> list) {
        // 그냥 처음부터 정렬하셈.
        for (Integer e: list) {
            System.out.print(e + " ");
        }
        System.out.println();
    }

    private static ArrayList<Integer> select() {
        ArrayList<Integer> list = new ArrayList<>();
        for (int i = 0; i < N; i++) {
            if (visited[i]) {
                list.add(Ns[i]);
            }
        }
        return list;
    }
}