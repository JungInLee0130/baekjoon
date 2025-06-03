import java.io.*;
import java.util.*;

class Main {
    static int[] A;
    static int N;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        N = Integer.parseInt(br.readLine());

        StringTokenizer st = new StringTokenizer(br.readLine());
        A = new int[N];
        for (int i = 0; i < N; i++) {
            A[i] = Integer.parseInt(st.nextToken());
        }

        int[] Lis = new int[N];     // 진짜 정답이 아님. 길이만 맞음.
        int[] idx = new int[N];
        Lis[0] = A[0]; // 초기원소 필요. 비교 기준
        idx[0] = 0;

        int len = 1;

        for (int i = 1; i < N; i++) {
            if (Lis[len - 1] < A[i]) {
                len++;
                Lis[len - 1] = A[i];
                idx[i] = len - 1; // idx[i] = Lis의 인덱스(len)
                continue;
            }

            int left = 0;
            int right = len - 1;

            while (left < right) {
                int mid = (left + right) / 2;

                // 원소들중에 자신보다 크거나같은수
                if (Lis[mid] >= A[i]) {
                    right = mid;
                } else if (Lis[mid] < A[i]) {
                    left = mid + 1;
                }
            }

            Lis[right] = A[i];
            idx[i] = right;
        }

        StringBuilder sb = new StringBuilder();
        sb.append(len + "\n");

        int index = len - 1;
        Stack<Integer> stack = new Stack<>();
        for (int i = N - 1; i >= 0; i--) {
            if (idx[i] == index){
                index--;
                stack.push(A[i]);
            }
            if (index < 0) {
                break;
            }
        }
        while (!stack.isEmpty()) {
            sb.append(stack.pop() + " ");
        }

        System.out.println(sb);;

        br.close();
    }
}