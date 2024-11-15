import org.w3c.dom.Node;

import java.awt.*;
import java.io.*;
import java.util.*;
import java.util.List;
import java.util.stream.Collectors;

public class Main {
    static int N;
    static int[] in, post, pre;
    static int idx;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        N = Integer.parseInt(br.readLine());

        in = new int[N];
        post = new int[N];
        pre = new int[N];

        StringTokenizer st = new StringTokenizer(br.readLine());

        for (int i = 0; i < N; i++){
            in[i] = Integer.parseInt(st.nextToken());
        }

        st = new StringTokenizer(br.readLine());

        for (int i = 0; i < N; i++) {
            post[i] = Integer.parseInt(st.nextToken());
        }

        getPreOrder(0, N - 1, 0, N - 1);

        for (int e : pre) {
            System.out.print(e + " ");
        }

        bw.flush();
        bw.close();
        br.close();
    }


    private static void getPreOrder(int is, int ie, int ps, int pe) {
        if (is <= ie && ps <= pe){
            pre[idx++] = post[pe];

            int pos = is;

            // 인오더에서 루트노드 위치 찾기
            for (int i = is; i <= ie; i++) {
                if (in[i] == post[pe]){
                    pos = i;
                    break;
                }
            }

            // 이래서 분할정복인가
            // 왼쪽
            getPreOrder(is, pos - 1, ps,ps + pos - 1 - is);
            // 오른쪽
            getPreOrder(pos + 1, ie, ps + pos - is, pe - 1);
        }
    }
}