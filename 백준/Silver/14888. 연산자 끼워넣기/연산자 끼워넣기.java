import java.io.*;
import java.util.StringTokenizer;

import static java.lang.Math.*;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        int N = Integer.parseInt(br.readLine());

        int[] num = new int[N];
        StringTokenizer st = new StringTokenizer(br.readLine());

        for (int i = 0; i < N; i++) {
            num[i] = Integer.parseInt(st.nextToken());
        }

        st = new StringTokenizer(br.readLine());

        int[] operatorNum = new int[4]; // arr
        for (int i = 0; i < 4; i++) {
            operatorNum[i] = Integer.parseInt(st.nextToken());
        }

        // 만들수있는 최댓값, 최솟값
        // 나눗셈의 경우 : 몫만 취한다. 음수일 경우: 양수로 바꾼후 -> 몫을 취하고 -> 음수로 다시 바꾼다.
        // 연산자는 개수가 N - 1개
        // 경우의 수를 모두 구한다.
        // N = 6 -> 5개. 1 2 3 4 5 -> 경우의 수 5! / (같은거)!

        int depth = 1;
        max = -1000000000;
        min = 1000000000;
        int sum = num[0];

        DFS(num, operatorNum, sum, N, depth);

        bw.write(String.valueOf(max) + "\n" + String.valueOf(min));

        bw.flush();
        br.close();
        bw.close();
    }

    private static int max;
    private static int min;

    private static void DFS(int[] num, int[] operatorNum, int sum, int N, int depth) {
        if (depth == N) {
            max = max(max, sum);
            min = min(min, sum);
            return;
        }
        for (int i = 0; i < 4; i++) {
            if (operatorNum[i] > 0) {
                operatorNum[i]--; // 먼저 카운트 뺌.

                if (i == 0) {
                    // 함수 인자에 sum + num[depth] 형태로 넣는다. 그래야 빠져나올때 이전 결과로 돌아옴.
                    // 먼저 더하고 넣어버리면 결과가 그대로 남는다.
                    DFS(num, operatorNum, sum + num[depth], N,depth + 1);
                }
                else if (i == 1) {
                    DFS(num, operatorNum, sum - num[depth], N,depth + 1);
                }
                else if (i == 2) {
                    DFS(num, operatorNum, sum * num[depth], N,depth + 1);
                }
                else {
                    DFS(num, operatorNum, sum / num[depth], N, depth + 1);
                }
                operatorNum[i]++; // 카운트 나중에 더함
            }

        }
    }
}
