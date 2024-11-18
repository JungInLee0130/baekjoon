import org.w3c.dom.Node;

import java.awt.*;
import java.io.*;
import java.util.*;
import java.util.List;
import java.util.stream.Collectors;

public class Main {
    static int N;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        N = Integer.parseInt(br.readLine());

        // 투포인터
        // end포인터와 start포인터를 이동시키면서 합이 N일때를 카운트한다.
        int start = 0;
        int end = 0;

        int sum = 0;
        int count = 0;
        while (start <= N) {
            while (++end <= N){
                sum += end;
                if (sum > N) {
                    break; // N보다 크면 start를 늘림
                } else if (sum == N) {
                    count++;
                }
            }
            // end를 증가시켜서 count에 해당하는걸 찾으면
            while (++start <= N) {
                sum -= start;
                if (sum == N) {
                    count++;
                } else if (sum < N) {
                    break; // N보다작으면 end를 늘림
                }
            }
        }

        System.out.println(count);


        bw.flush();
        bw.close();
        br.close();
    }
}