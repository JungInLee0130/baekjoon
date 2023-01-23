
import java.io.*;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Main {
    static int N;
    static int[] array;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        N = Integer.parseInt(br.readLine());
        array = new int[N];

        StringTokenizer st = new StringTokenizer(br.readLine());
        for (int i = 0; i < N; i++) {
            array[i] = Integer.parseInt(st.nextToken());
        }

        int x = Integer.parseInt(br.readLine());

        int left = 0;
        int right = N - 1;
        int count = 0;

        Arrays.sort(array);
        // 모두 끝원소에 도달하기 전까지
        while (left < right) {
            int temp = array[left] + array[right];
            if (temp <= x) {
                left++;
            } else if (temp > x) {
                right--;
            }
            if (temp == x) {
                count++;
            }
        }

        bw.write(String.valueOf(count));

        bw.flush();
        br.close();
        bw.close();
    }
}
