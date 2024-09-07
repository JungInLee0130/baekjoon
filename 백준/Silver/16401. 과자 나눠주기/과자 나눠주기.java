import java.io.*;
import java.util.*;
import java.util.stream.Collectors;

public class Main {
    static int M, N;
    static int[] snacks;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        // 같은 길이로 줄수있는 막대과자의 최대길이
        StringTokenizer st = new StringTokenizer(br.readLine());

        M = Integer.parseInt(st.nextToken()); // 100만 조카
        N = Integer.parseInt(st.nextToken()); // 100만 과자 갯수

        st = new StringTokenizer(br.readLine()); // 10억 이하
        snacks = new int[N];
        int max = 0;
        int min = 1_000_000_001;
        for (int i = 0; i < N; i++) {
            snacks[i] = Integer.parseInt(st.nextToken());
            max = Math.max(max, snacks[i]);
            min = Math.min(min, snacks[i]);
        }

        int low = 1;
        int high = max;

        int answer = binarySearch(low, high);

        System.out.println(answer);

        bw.flush();
        bw.close();
        br.close();
    }

    private static int binarySearch(int low, int high) {
        int answer = 0; // 불가능하면 0 그대로 출력
        while (low <= high) {
            // 1 ~ min까지 모두 나눠줄수있는 길이중에서 가장 큰값
            // mid : 길이
            int mid = (low + high) / 2;

            int count = getCount(mid);

            // M명에게 못나눠줌
            if (count < M) {
                // 더 작게
                high = mid - 1;
            } else {
                // M명 이상(넘침)
                answer = mid;
                low = mid + 1; // 최소길이 늘림
            }
        }
        return answer;
    }

    private static int getCount(int mid) {
        int count = 0;
        for (int i = 0; i < N; i++) {
            count += snacks[i] / mid;
        }
        return count;
    }
}