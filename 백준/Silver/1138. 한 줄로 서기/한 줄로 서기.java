import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Main {
    static int N;
    static int[] nums;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        N = Integer.parseInt(br.readLine());

        StringTokenizer st = new StringTokenizer(br.readLine());

        nums = new int[N];
        for (int i = 0; i < N; i++) {
            nums[i] = Integer.parseInt(st.nextToken());
        }

        int[] answer = new int[N];
        for (int j = 0; j < N; j++) { // 10
            int cnt = 0;
            for (int i = 0; i < N; i++) { // 10
                if (answer[i] > 0) {
                    continue;
                }

                if (nums[j] == cnt) {
                    answer[i] = j + 1;
                    break;
                }

                if (answer[i] == 0) {
                    cnt++;
                }
            }
        }

        for (int i = 0; i < N; i++) {
            System.out.print(answer[i] + " ");
        }


        br.close();
    }
}