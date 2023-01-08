import java.io.*;
import java.math.BigInteger;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        StringTokenizer st = new StringTokenizer(br.readLine());

        int N = Integer.parseInt(st.nextToken());
        int M = Integer.parseInt(st.nextToken());

        st = new StringTokenizer(br.readLine());
        int[] array = new int[N];
        for (int i = 0; i < N; i++) {
            array[i] = Integer.parseInt(st.nextToken());
        }

        // 사전순이라 sort먼저
        Arrays.sort(array);

        // N개중 M개를 뽑는 경우를 모두 출력
        boolean[] visited = new boolean[N]; // N개 // 방문기록 체크
        int[] answer = new int[M]; // 순열을 담을 배열
        Permutation(array, visited, answer, 0, N, M);

        bw.flush();
        br.close();
        bw.close();
    }
    private static void Permutation(int[] array, boolean[] visited, int[] answer, int depth, int N, int M) {
        if (depth == M) { // N개의 조합이 완성
            print(answer, M);
            return;
        }
        for (int i = 0; i < N; i++) {
            if (!visited[i]) {
                visited[i] = true;
                answer[depth] = array[i];
                Permutation(array, visited, answer, depth + 1, N, M);
                visited[i] = false;
            }
        }
    }

    private static void print(int[] answer, int M) {
        for (int i = 0; i < M; i++) {
            System.out.print(answer[i] + " ");
        }
        System.out.println();
    }
}
