import java.io.*;
import java.util.*;

public class Main {
    static String str;
    static int[][] preSum;
    static int N;
    public static void main(String[] args) throws IOException{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        str = br.readLine();

        N = str.length();

        int Q = Integer.parseInt(br.readLine());

        preSum = new int[26][N + 1];

        for (int i = 0; i < 26; i++) {
            for (int j = 1; j <= N; j++) {
                if (str.charAt(j - 1) == (char)('a' + i)){
                    preSum[i][j] = preSum[i][j - 1] + 1;
                }
                else{
                    preSum[i][j] = preSum[i][j - 1];
                }
            }
        }

        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < Q; i++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            char alpha = st.nextToken().charAt(0);
            int l = Integer.parseInt(st.nextToken());
            int r = Integer.parseInt(st.nextToken());

            sb.append(preSum[alpha - 'a'][r + 1] - preSum[alpha - 'a'][l - 1 + 1] + "\n");
        }

        System.out.print(sb);

        br.close();
    }
}