
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

        int sum = 0;

        int start = 0;
        int end = N - 1;
        int closet = 2_000_000_000;
        int[] answer = new int[2];

        Arrays.sort(array);

        while (start < end) {
            sum = array[start] + array[end];
            int num = Math.abs(sum);
            if (num <= closet) { // closet이 더 크면 갱신 필요
                // 차이를 줄여야하니까 둘다 start++ end--
                closet = num;
                answer[0] = array[start];
                answer[1] = array[end];

            }
            if (sum > 0){
                end--;
            }
            else{
                start++;
            }
        }

        bw.write(String.valueOf(answer[0]) + " " + String.valueOf(answer[1]));


        bw.flush();
        br.close();
        bw.close();
    }
}
