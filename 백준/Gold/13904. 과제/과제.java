
import java.io.*;
import java.util.*;

public class Main {
    static int N;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        N = Integer.parseInt(br.readLine());

        int[][] assign = new int[N][2];


        for (int i = 0; i < N; i++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            assign[i][0] = Integer.parseInt(st.nextToken());
            assign[i][1] = Integer.parseInt(st.nextToken());
        }

        Arrays.sort(assign, new Comparator<int[]>() {
            @Override
            public int compare(int[] o1, int[] o2) {
                if (o2[0] == o1[0]) { // 남은일수가 같을때는
                    return o2[1] - o1[1]; // 점수에따라 내림차순 정렬
                }
                return o2[0] - o1[0]; // 남은일수에따라 내림차순 정렬
            }
        });


        // 방문배열로
        boolean[] visited = new boolean[N];
        int maxDay = assign[0][0];
        int max = assign[0][1]; // 점수
        int maxIndex = 0;
        int sum = 0; // 점수의 합
        for (int day = maxDay; day >= 1; --day) { // day 줄여나감
            max = 0;
            maxIndex = 0;
            for (int j = 0; j < N; j++) { // 전체 순회
                if (!visited[j]) {
                    // !visited
                    int curday = assign[j][0];
                    int score = assign[j][1];

                    // 6
                    // 5 6
                    if (day <= curday) { // 주어진날(6)보다 날짜(6)가 크거나 같아야한다. 5 <= 6
                        if (max < score) {
                            max = score;
                            maxIndex = j;
                        }
                    }
                }
            }
            // sum + max, maxIndex 사용완료
            if (max != 0) {
                sum += max;
                visited[maxIndex] = true;
            }
        }


        /*for (int i = 0; i < N; i++) { // 순회
            if (visited[i]) continue;
            int day = assign[i][0]; // 다음날
            for (int j = 0; j < N; j++) {
                if (visited[count]) continue;
                if (day == assign[count][0]) { // 같은 날중에 최대값 비교
                    max = Math.max(max, assign[count][1]);
                    maxIndex = count;
                }
            }
            sum += max;
            visited[maxIndex] = true;
            // visited ->
        }*/

        bw.write(String.valueOf(sum));

        bw.flush();
        br.close();
        bw.close();
    }


}

