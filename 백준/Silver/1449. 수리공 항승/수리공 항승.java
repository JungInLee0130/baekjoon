
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

        // 정렬
        Arrays.sort(leak);

        if (N == 1) bw.write(String.valueOf(1));
        else {
            int count = 0;
            int sub = 0;
            for (int i = 0; i < N; i++) {
                // 결국 끝점에 도달해서 테이프를 한개 밖에 붙일수밖에 없는 상황
                // 붙일 수 있었으면 이전점에서 끝났을꺼임.
                if (i == N - 1){
                    count += 1;
                    break;
                }
                // 차를 구하기 : 양쪽 0.5만큼의 차가 필요하므로 +1
                 sub += leak[i + 1] - leak[i] + 1;

                // 차가 L보다 크면 : 테이프 1개로 커버해야하는 상황
                if (sub > L) {
                    count++;
                    sub = 0;
                    // 테이프 길이가 같으면 : 적어도 2개이상 커버가능
                    // 시작지점부터 끝지점까지 커버가 가능하기때문에 끝지점 다음점부터 시작
                } else if (sub == L) {
                    count++;
                    i = i + 1;
                    sub = 0;
                } else {
                    // 테이프 길이가 남는다는 뜻이므로 다음 점끼리 차를 더해준다. +1했던것은 뺌.
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

