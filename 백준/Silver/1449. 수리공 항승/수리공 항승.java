
import java.io.*;
import java.util.*;

public class Main {
    static int N, L;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        L = Integer.parseInt(st.nextToken());

        st = new StringTokenizer(br.readLine());
        int[] leak = new int[N];
        for (int i = 0; i < N; i++) {
            leak[i] = Integer.parseInt(st.nextToken());
        }

        Arrays.sort(leak);

        if (N == 1) bw.write(String.valueOf(1));
        else {
            int count = 0;
            int sub = 0;
            for (int i = 0; i < N; i++) {
                if (i == N - 1){
                    count += 1;
                    break;
                }
                 sub += leak[i + 1] - leak[i] + 1;

                if (sub > L) { // L보다 크면
                    count++;
                    sub = 0;
                } else if (sub == L) {
                    count++;
                    i = i + 1;
                    sub = 0;
                } else { // L보다 작으면
                    // do nothing
                    sub -= 1;
                }
            }

            bw.write(String.valueOf(count));
        }



        bw.flush();
        br.close();
        bw.close();
    }


}

