import java.io.*;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.StringTokenizer;

public class Main {
    private static int N;
    private static boolean[][] visited;
    private static int houseNumber = 0;
    private static ArrayList<Integer> houseSpecificNumber = new ArrayList<>();
    private static int specificNumber = 0;
    private static void DFS(int[][] house, int x, int y) {
        visited[x][y] = true;
        specificNumber++;

        if (x >= 1) {
            if (house[x - 1][y] == 1 && !visited[x - 1][y]) {
                DFS(house, x - 1, y);
            }
        }

        if (y >= 1) {
            if (house[x][y - 1] == 1 && !visited[x][y - 1]) {
                DFS(house, x, y - 1);
            }
        }

        if (x <= N - 2) {
            if (house[x + 1][y] == 1 && !visited[x + 1][y]) {
                DFS(house, x + 1, y);
            }
        }

        if (y <= N - 2) {
            if (house[x][y + 1] == 1 && !visited[x][y + 1]) {
                DFS(house, x, y + 1);
            }
        }
    }

    private static void naming(int[][] house) {
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < N; j++) {
                if (house[i][j] == 0 || visited[i][j]) { // 0인곳을 따라 DFS
                    continue;
                } else {
                    DFS(house, i, j);
                    houseNumber++;
                    houseSpecificNumber.add(specificNumber);
                    specificNumber = 0;
                }
            }
        }

    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        N = Integer.parseInt(br.readLine());

        int[][] house = new int[N][N];

        for (int i = 0; i < N; i++) {
            String str = br.readLine();
            for (int j = 0; j < N; j++) {
                house[i][j] = str.charAt(j) - '0';
            }
        }

        visited = new boolean[N][N];

        for (int i = 0; i < N; i++) {
            for (int j = 0; j < N; j++) {
                visited[i][j] = false;
            }
        }

        naming(house);

        bw.write(String.valueOf(houseNumber) + "\n");

        Collections.sort(houseSpecificNumber);

        for (Integer num: houseSpecificNumber) {
            bw.write(String.valueOf(num) + "\n");
        }
        bw.flush();
        br.close();
        bw.close();
    }
}
