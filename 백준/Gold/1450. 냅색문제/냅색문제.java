import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.*;

public class Main {
    static int N, C, index;
    static int[] arr;
    static ArrayList<Integer> aSum, bSum;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());
        C = Integer.parseInt(st.nextToken());

        // 2^30 = 10억 7천만
        // 2^15 = 3만 2천

        st = new StringTokenizer(br.readLine());
        arr = new int[N + 1];
        for (int i = 0; i < N; i++) {
            arr[i] = Integer.parseInt(st.nextToken());
        }

        aSum = new ArrayList<>();
        bSum = new ArrayList<>();

        aDfs(0,0);
        bDfs(N / 2, 0);
        Collections.sort(bSum);

        int ans = 0;
        for (int i = 0; i < aSum.size(); i++) { // N / 2
            index = -1;
            binarySearch(0, bSum.size() - 1, aSum.get(i)); // aSum과 bSum 반복문을 돌려서 해당하는 값찾기
            ans += index + 1;
        }
        
        bw.write(String.valueOf(ans));



        bw.flush();
        bw.close();
        br.close();
    }

    // 재귀
    /*private static void binarySearch(int start, int end, int val) { // val : aSum
        if (start > end) return;

        int mid = (start + end) / 2;

        if (bSum.get(mid) + val <= C) {
            index = mid;
            binarySearch(mid + 1, end, val); // 작거나같으면 위에 탐색
        }
        else{
            binarySearch(start, mid - 1, val); // 아니면 아래탐색 // 근데 아래탐색할꺼없지않나
        }
    }*/

    // 반복문
    private static void binarySearch(int start, int end, int val) { // val : aSum
        while (start <= end) {
            int mid = (start + end) / 2;

            if (bSum.get(mid) + val <= C) {
                index = mid;
                start = mid + 1;
            }
            else{
                end = mid - 1;
            }
        }
    }

    private static void bDfs(int idx, int sum) {
        if (sum > C) return;

        if (idx == N) {
            bSum.add(sum);
            return;
        }

        bDfs(idx + 1, sum + arr[idx]);
        bDfs(idx + 1, sum);
    }

    // 선택하거나 선택하지않거나
    private static void aDfs(int idx, int sum) {
        if (sum > C) return;

        if (idx == N / 2) {
            aSum.add(sum);
            return;
        }

        aDfs(idx + 1, sum + arr[idx]); // 선택
        aDfs(idx + 1, sum); // 선택 x
    }
}