import java.awt.*;
import java.io.*;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main {
    private static boolean[][] visited;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        StringTokenizer st = new StringTokenizer(br.readLine());

        int row = Integer.parseInt(st.nextToken());
        int column = Integer.parseInt(st.nextToken());

        int[][] maze = new int[row][column];

        for (int i = 0; i < row; i++) {
            String str = br.readLine();
            for (int j = 0; j < column; j++) {
                maze[i][j] = str.charAt(j) - '0';
            }
        }

        visited = new boolean[row][column];

        for (int i = 0; i < row; i++) {
            for (int j = 0; j < column; j++) {
                visited[i][j] = false;
            }
        }

        mazeRunner(maze, row, column);

        bw.write(String.valueOf(minWay));

        bw.flush();
        br.close();
        bw.close();
    }

    private static void mazeRunner(int[][] maze, int row, int column) {
         BFS(maze, 0, 0, row, column);
    }

    private static Queue<Point> queue = new LinkedList<>();
    private static int minWay = 0;
    private static void BFS(int[][] maze, int i, int j, int row, int column) {
        visited[i][j] = true;
        queue.add(new Point(i, j));
        int queueSize = 1;
        int childNum = 1;
        while (!queue.isEmpty()) {
            Point p = queue.poll();
            queueSize--;
            if (queueSize == 0) {
                queueSize = childNum;
                childNum = 0;
                minWay++;
            }
            int x = p.x;
            int y = p.y;
            if (x >= 1) {
                if (maze[x - 1][y] == 1 && !visited[x - 1][y]) {
                    visited[x - 1][y] = true;
                    childNum++;
                    queue.add(new Point(x - 1, y));
                }
            }
            if (x < row - 1) {
                if (maze[x + 1][y] == 1 && !visited[x + 1][y]) {
                    visited[x + 1][y] = true;
                    childNum++;
                    queue.add(new Point(x + 1, y));
                }
            }

            if (y >= 1) {
                if (maze[x][y - 1] == 1 && !visited[x][y - 1]) {
                    visited[x][y - 1] = true;
                    childNum++;
                    queue.add(new Point(x, y - 1));
                }
            }

            if (y < column - 1) {
                if (maze[x][y + 1] == 1 && !visited[x][y + 1]) {
                    visited[x][y + 1] = true;
                    childNum++;
                    queue.add(new Point(x, y + 1));
                }
            }
            if (p.x == row - 1 && p.y == column - 1) {
                return;
            }
        }
    }
}
