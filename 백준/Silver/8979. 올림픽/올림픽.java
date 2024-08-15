import org.w3c.dom.Node;

import java.io.*;
import java.util.*;

public class Main {
    static int N, K;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        K = Integer.parseInt(st.nextToken());

        int[][] medals = new int[N][5];
        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());

            for (int j = 0; j < 4; j++) {
                medals[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        int idx = K - 1;

        Arrays.sort(medals, new Comparator<int[]>(){
            @Override
            public int compare(int[] o1, int[] o2){
                if (o1[1] == o2[1]){
                    if (o1[2] == o2[2]) {
                        return o2[3] - o1[3];
                    }
                    return o2[2] - o1[2];
                }
                return o2[1] - o1[1];
            }
        });

        // 1. 금이 같으면 은을 비교
        // 2. 은이 같으면 동을 비교
        int rank = 1;
        medals[0][4] = rank;
        Stack<Integer> stack = new Stack<>();
        stack.push(rank);

        for (int i = 1; i < N; i++) {
            if (medals[i - 1][1] > medals[i][1]){
                rank = rank + stack.size();
                stack = new Stack<>();
                medals[i][4] = rank;
                stack.push(rank);
            }
            else{
                if (medals[i - 1][2] > medals[i][2]) {
                    rank = rank + stack.size();
                    stack = new Stack<>();
                    medals[i][4] = rank;
                    stack.push(rank);
                }
                else{
                    if (medals[i - 1][3] > medals[i][3]) {
                        rank = rank + stack.size();
                        stack = new Stack<>();
                        medals[i][4] = rank;
                        stack.push(rank);
                    }
                    else{ // 같은 경우
                        medals[i][4] = rank;
                        stack.push(rank);
                    }
                }
            }
        }

        for (int i = 0; i < N; i++) {
            //System.out.println(medals[i][4]);
            if (medals[i][0] == K) {
                System.out.println(medals[i][4]);
                break;
            }
        }



        bw.flush();
        br.close();
        bw.close();
    }
}