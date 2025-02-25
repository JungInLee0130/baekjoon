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

            int[] note = new int[N];
            for (int i = 0; i < N; i++) {
                note[i] = Integer.parseInt(st.nextToken());
            }

            Arrays.sort(note);

            M = Integer.parseInt(br.readLine());

            st = new StringTokenizer(br.readLine());

            StringBuilder sb = new StringBuilder();

            for (int i = 0; i < M; i++){
                int m = Integer.parseInt(st.nextToken());

                int left = 0;
                int right = N - 1;

                boolean hasNumber = false;
                while (left <= right){
                    int mid = (left + right) / 2;

                    if (note[mid] < m){
                        left = mid + 1;
                    }
                    else if (m < note[mid]){
                        right = mid - 1;
                    }
                    else{
                        hasNumber = true;
                        break;
                    }
                }

                if (hasNumber){
                    sb.append(1 + "\n");
                }
                else{
                    sb.append(0 + "\n");
                }
            }

            System.out.print(sb); // \n넣으면 안됨.
        }

        br.close();
    }
}