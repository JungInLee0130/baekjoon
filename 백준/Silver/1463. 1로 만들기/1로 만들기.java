import java.io.*;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        int N = Integer.parseInt(br.readLine());
        int count = 0;
        int[] min = new int[N + 1];
        min[1] = N;
        minCount(N, count, min);
        bw.write(String.valueOf(min[1]));


        bw.flush();
        br.close();
        bw.close();
    }

    public static int[] minCount(int n, int count, int[] min) {
        if (n == 1) {
            min[1] = Math.min(min[1], count);
            return min;
        }
        if (min[n] < count && min[n] != 0) { // count보다 작으면 그냥 리턴
            return min;
        }
        if (n % 3 == 0) {
            minCount(n / 3, count + 1, min);
            min[n] = count;
        }
        if (n % 2 == 0) {
            minCount(n / 2, count + 1, min);
            min[n] = count;
        }
        minCount(n - 1, count + 1, min);
        min[n] = count;
        return min;
    }
}
