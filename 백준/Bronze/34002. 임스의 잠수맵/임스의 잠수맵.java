import java.io.*;
import java.util.*;

class Main {
    static int A, B, C;
    static int S, V;
    static int L;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        StringTokenizer st = new StringTokenizer(br.readLine());
        A = Integer.parseInt(st.nextToken());
        B = Integer.parseInt(st.nextToken());
        C = Integer.parseInt(st.nextToken());

        st = new StringTokenizer(br.readLine());
        S = Integer.parseInt(st.nextToken()) * 30;
        V = Integer.parseInt(st.nextToken()) * 30;

        L = Integer.parseInt(br.readLine());

        int exp = (250 - L) * 100;

        int min = 0;

        if (exp >= C * V) { // 1
            exp -= C * V;

            min += V;

            if (exp >= S * B) { // 3
                exp -= S * B;

                min += S;

                int q = exp / A;
                int r = exp % A;

                if (r > 0) {
                    min += q + 1;
                }
                else if (r == 0){
                    min += q;
                }
            }
            else { // 4
                int minute = exp / B;
                int r = exp % B;

                if (r > 0) {
                    minute += 1;
                } else if (r == 0) {

                }

                min += minute;
            }
        }
        else { // 2
            int minute = (exp / C);
            if (exp % C > 0) {
                minute += 1;
            } else if (exp % C == 0) {

            }

            min += minute;
        }

        System.out.println(min);

        br.close();
    }
}