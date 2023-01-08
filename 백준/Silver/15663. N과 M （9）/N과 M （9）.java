import java.io.*;
import java.math.BigInteger;
import java.util.*;

public class Main {
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

        // 사전순이라 sort먼저
        Arrays.sort(array);

        // N개중 M개를 뽑는 경우를 모두 출력
        visited = new boolean[N]; // N개 // 방문기록 체크
        answer = new int[M]; // 순열을 담을 배열
        entireHashSet = new LinkedHashSet<>(); // LinkedHashSet -> 입력순으로 정렬
        Permutation(0);

        Iterator<String> iter = entireHashSet.iterator();
        while (iter.hasNext()) {
            bw.write(iter.next() + "\n");
        }

        bw.flush();
        br.close();
        bw.close();
    }
    private static int N;
    private static int M;
    private static boolean[] visited;
    private static int[] array;
    private static HashSet<String> entireHashSet;
    private static int[] answer;
    private static void Permutation(int depth) {
        if (depth == M) { // N개의 조합이 완성
            StringBuilder sb = new StringBuilder();
            for (int element: answer) {
                sb.append(element).append(' '); // element + ' '
            }
            entireHashSet.add(sb.toString());
            return;
        }
        for (int i = 0; i < N; i++) {
            if (!visited[i]) {
                visited[i] = true;
                answer[depth] = array[i];
                Permutation(depth + 1);
                visited[i] = false;
            }
        }
    }
}
