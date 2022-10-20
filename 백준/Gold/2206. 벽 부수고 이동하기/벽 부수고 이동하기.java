import java.awt.*;
import java.io.*;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

import static java.lang.Math.*;

class Point3 {
    int x;
    int y;
    int cnt;
    boolean destroyed;

    public Point3(int x, int y, int cnt, boolean destroyed) {
        this.x = x;
        this.y = y;
        this.cnt = cnt;
        this.destroyed = destroyed;
    }

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }

    public int getCnt() {
        return cnt;
    }

    public boolean isDestroyed() {
        return destroyed;
    }
}

public class Main {
    //3중 visited 배열
    private static boolean[][][] visited;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        StringTokenizer st = new StringTokenizer(br.readLine());
        int row = Integer.parseInt(st.nextToken());
        int column = Integer.parseInt(st.nextToken());

        int[][] map = new int[row][column];
        for (int i = 0; i < row; i++) {
            String str = br.readLine();
            for (int j = 0; j < column; j++) {
                map[i][j] = str.charAt(j) - '0';
            }
        }

        visited = new boolean[row][column][2];

        int minWay = 0;

        minWay = BFS(map);

        bw.write(String.valueOf(minWay) + "\n");

        bw.flush();
        br.close();
        bw.close();
    }

    private static Queue<Point3> queue = new LinkedList<>();
    private static int[] dx = {-1, 1, 0, 0};
    private static int[] dy = {0, 0, 1, -1};

    private static int BFS(int[][] map) {
        int row = map.length;
        int column = map[0].length;
        queue.add(new Point3(0, 0, 1, false));

        while (!queue.isEmpty()) {
            Point3 point3 = queue.poll();
            int x = point3.x;
            int y = point3.y;

            if (x == row - 1 && y == column - 1) {
                return point3.cnt;
            }

            for (int i = 0; i < 4; i++) {
                int newX = x + dx[i];
                int newY = y + dy[i];

                if (newX < 0 || newX >= row || newY < 0 || newY >= column) continue;
                int nextCnt = point3.cnt + 1;

                if (map[newX][newY] == 0) {
                    if (!point3.destroyed && !visited[newX][newY][0]) {
                        queue.add(new Point3(newX, newY, nextCnt, false));
                        visited[newX][newY][0] = true;
                    } else if (point3.destroyed && !visited[newX][newY][1]) {
                        queue.add(new Point3(newX, newY, nextCnt,true));
                        visited[newX][newY][1] = true;
                    }
                } else if (map[newX][newY] == 1) {
                    if (!point3.destroyed) {
                        queue.add(new Point3(newX, newY, nextCnt,true));
                        visited[newX][newY][1] = true;
                    }
                }
            }
        }
        return -1;
    }
}