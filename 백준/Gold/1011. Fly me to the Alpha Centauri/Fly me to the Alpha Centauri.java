import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Main {
    static int T;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        T = Integer.parseInt(br.readLine());

        StringBuilder sb = new StringBuilder();
        for (int t = 0; t < T; t++) {
            StringTokenizer st = new StringTokenizer(br.readLine());

            int x = Integer.parseInt(st.nextToken());
            int y = Integer.parseInt(st.nextToken());

            int sub = y - x;
            int subSqrt = (int) Math.sqrt(sub);

            if (sub == subSqrt * subSqrt){ // 제곱수임
                sb.append(2 * subSqrt - 1 + "\n");
            }
            else if (subSqrt * subSqrt < sub && sub <= subSqrt * subSqrt + subSqrt){
                sb.append(2 * subSqrt + "\n");
            }
            else{
                sb.append(2 * subSqrt + 1 + "\n");
            }
        }

        System.out.println(sb.toString());

        br.close();
    }
    // 하..bfs인줄알았는데 dp엿구나..
}