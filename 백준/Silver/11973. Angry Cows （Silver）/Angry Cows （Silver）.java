import java.io.*;
import java.util.*;

class Main {
    static int N, K;
    static int[] X;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());
        K = Integer.parseInt(st.nextToken());

        X = new int[N];
        for (int i = 0; i < N; i++) {
            X[i] = Integer.parseInt(br.readLine());
        }

        // cow : K

        Arrays.sort(X);

        // 반지름 R * 2 : 전체길이로
        int low = 0;
        int high = (X[N - 1] - X[0]) / 2;

        int answer = high;

        while (low <= high) {
            int mid = (low + high) / 2;

            int start = 0;
            int end = 0;
            int count = 1;

            while (end <= N - 1) {
                int distance = X[end] - X[start];

                if (mid * 2 > distance) { // mid가 더 크면 계속
                    // 계속
                }
                else if (mid * 2 < distance){ // 더 작으면 여기까지
                    start = end;
                    count++;
                }
                else{ // 같으면
                    // 계속
                }
                end++;
            }

            // 총 카운트가 더 많으면 K보다 많으면
            if (count > K) { // 길이 늘리기
                low = mid + 1;
            }
            else if (count < K){ // K보다 작으면 // 길이 좁히기
                high = mid - 1;
            }
            else{ // 같으면 길이라도 좁히기
                answer = mid;
                high = mid - 1;
            }
        }

        System.out.println(answer);


        br.close();
    }
}