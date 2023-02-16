import java.io.*;
import java.util.StringTokenizer;

public class Main {

    public static long binarySearchMax(long[] arr, int N, long max){ // N == key
        long left = 1L;
        long right = max;

        long result = -1;

        while (left <= right){
            long mid = (left + right) / 2; // -> 합할때 자료형 주의..ㅠㅠ

            int count = 0;

            for (int i = 0; i < arr.length; i++) {
                count += arr[i] / mid;
            }

            if (count >= N){ // 11 <= 12 // 근데 count가 더 크면 N보다 필연적으로 작은수임.
                result =  mid; // <- 아마 여기 대체되는거에서 오류날듯싶음...
                left =  mid + 1;
            }
            else{ // 10 < 11 -> 나누는수가 큼. -> right줄이기
                right = mid - 1;
            }
        }

        return result;
    }
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        StringTokenizer st = new StringTokenizer(br.readLine());
        int K = Integer.parseInt(st.nextToken());
        int N = Integer.parseInt(st.nextToken());

        long[] kLan = new long[K];

        for (int i = 0; i < K; i++) {
            kLan[i] = Integer.parseInt(br.readLine());
        }

        long max = kLan[0];

        for (int i = 1; i < K; i++) {
            max = Math.max(max, kLan[i]);
        }

        // 1~min 사이에 이분탐색 : N값중 최대값 색출
        bw.write(String.valueOf(binarySearchMax(kLan, N, max)));

        bw.flush();
        br.close();
        bw.close();
    }
}
