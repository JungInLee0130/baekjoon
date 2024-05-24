import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.sql.SQLOutput;
import java.util.*;
import java.util.stream.IntStream;

public class Main {
    static int N;
    static boolean[] arr;
    static boolean[] res;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        N = Integer.parseInt(br.readLine());

        String s = br.readLine();
        String r = br.readLine();

        arr = new boolean[N];
        boolean[] arr2 = new boolean[N];
        res = new boolean[N];

        // 0 : True 1 : false
        for (int i = 0; i < N; i++) {
            arr[i] = s.charAt(i) - '0' == 0 ? true : false;
            arr2[i] = s.charAt(i) - '0' == 0 ? true : false;
            res[i] = r.charAt(i) - '0' == 0 ? true : false;
        }

        // 그리디
        // 2의 첫번째 스위치를 킨다.
        arr2[0] = !arr2[0];
        arr2[1] = !arr2[1];

        int min = Integer.MAX_VALUE;
        min = Math.min(Math.min(switchOn(0,arr), switchOn(1,arr2)), min);

        if (min == Integer.MAX_VALUE) {
            System.out.println(-1);
        }
        else{
            System.out.println(min);
        }


        bw.flush();
        bw.close();
        br.close();
    }

    private static int switchOn(int cnt, boolean[] arr) {
        for (int i = 0; i <= N - 3; i++) { // 1 ~ N - 2
            if (res[i] == arr[i]) { // 똑같으면 -> 안바꿈

            }
            else{
                arr[i] = !arr[i];
                arr[i + 1] = !arr[i + 1];
                arr[i + 2] = !arr[i + 2];
                cnt++;
            }
        }

        if (res[N - 2] == arr[N - 2]) {

        }
        else{
            arr[N - 2] = !arr[N - 2];
            arr[N - 1] = !arr[N - 1];
            cnt++;
        }

        if (res[N - 1] == arr[N - 1]) {
            return cnt;
        }
        else{
            return Integer.MAX_VALUE;
        }
    }
}