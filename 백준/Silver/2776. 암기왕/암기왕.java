import java.io.*;
import java.util.*;

class Main {
    static int T, N, M;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        T = Integer.parseInt(br.readLine());

        for (int t = 0; t < T; t++) {
            N = Integer.parseInt(br.readLine());

            StringTokenizer st = new StringTokenizer(br.readLine());

            Set<Integer> noteset1 = new HashSet<>();
            for (int i = 0; i < N; i++) {
                noteset1.add(Integer.parseInt(st.nextToken()));
            }

            M = Integer.parseInt(br.readLine());

            st = new StringTokenizer(br.readLine());

            StringBuilder sb = new StringBuilder();

            for (int i = 0; i < M; i++){
                int m = Integer.parseInt(st.nextToken());

                if (noteset1.contains(m)) {
                    sb.append(1 + "\n");
                }
                else{
                    sb.append(0 + "\n");
                }
            }

            System.out.print(sb);
        }


        br.close();
    }
}