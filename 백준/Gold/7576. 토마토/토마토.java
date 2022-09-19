import java.awt.*;
import java.io.*;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main {
    // depth == day
    private static boolean[][] visited;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        StringTokenizer st = new StringTokenizer(br.readLine());
        int column = Integer.parseInt(st.nextToken());
        int row = Integer.parseInt(st.nextToken());

        // 2 <= 가로, 세로 <= 1000
        // 토마토 상태 입력
        int[][] tomato = new int[row][column];
        for (int i = 0; i < row; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < column; j++) {
                tomato[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        visited = new boolean[row][column];

        // visited 배열 초기화
        for (int i = 0; i < row; i++) {
            for (int j = 0; j < column; j++) {
                visited[i][j] = false;
            }
        }

        int day = 0;
        // 최소일수 출력
        day = spreadTomato(tomato, row, column, day);

        if (allTomatoGood(tomato, row, column)) { // 모두 익은 상황 : day 출력
            bw.write(String.valueOf(day));
        } else { // 모두 익지 못하는 상황 : 0이 남아있는 상황
            bw.write(String.valueOf(-1));
        }



        bw.flush();
        br.close();
        bw.close();
    }

    private static boolean allTomatoGood(int[][] tomato, int row, int column) {
        int count1 = 0;
        int count2 = 0;
        for (int i = 0; i < row; i++) {
            for (int j = 0; j < column; j++) {
                if (tomato[i][j] == 1) {
                    count1++;
                } else if (tomato[i][j] == -1) {
                    count2++;
                } else {
                    return false;
                }
            }
        }
        if (count1 == 0) {
            return false;
        }
        return true;
    }

    private static Queue<Point> queue = new LinkedList<>();
    private static int spreadTomato(int[][] tomato, int row, int column, int day) {
        for (int i = 0; i < row; i++) {
            for (int j = 0; j < column; j++) {
                if (tomato[i][j] == 1) {
                    // 처음 익은 토마토 확인
                    // 익은 토마토 먼저 확인 -> 모두 큐에 담고 -> BFS
                    queue.add(new Point(i, j));
                    // BFS를 통과하고서 모든 토마토가 물들여졌는지 확인해야함.
                }
            }
        }
        for (int i = 0; i < row; i++) {
            for (int j = 0; j < column; j++) {
                if (!visited[i][j] && tomato[i][j] == 1) {
                    day = Math.max(BFS(tomato,row, column), day);    
                }
            }
        }

        return day;
    }

    private static int[] dx = {-1,0,1,0};
    private static int[] dy = {0,1,0,-1};

    private static int BFS(int[][] tomato, int row, int column) {
        int day = -1;
        int queueSize = queue.size();
        int nextChildNum = 0;

        while (!queue.isEmpty()) {
            Point point = queue.poll();
            int x = point.x;
            int y = point.y;
            visited[x][y] = true;
            queueSize--;

            for (int i = 0; i < 4; i++) {
                int newX = x + dx[i];
                int newY = y + dy[i];

                if (0 <= newX && newX <= row - 1 && 0 <= newY && newY <= column - 1
                        && !visited[newX][newY]
                        && tomato[newX][newY] == 0) {
                    // 방문하지않고 배열범위 안에있다면, 그리고 토마토가 있고 안익었다면
                    visited[newX][newY] = true;
                    nextChildNum++;
                    // 토마토가 익으므로
                    tomato[newX][newY] = 1;
                    queue.add(new Point(newX, newY));
                }
            }

            if (queueSize == 0) {
                queueSize = nextChildNum;
                nextChildNum = 0;
                day++; // 처음부터 증가하고 시작해서 day = -1로 시작.
            }
        }
        return day;
    }
}
