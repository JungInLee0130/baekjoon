import java.awt.*;
import java.io.*;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main {
    private static int[][] chess;
    private static boolean[][] visited;
    private static int depth = -1;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        int T = Integer.parseInt(br.readLine());
        /* 1. 체스판 한변의 길이: l (4 <= l <= 300)
           각 칸은 두수의 쌍 (a,b)로 나타낼수있음. (0 <= a, b <= l-1)
           2. 나이트가 현재있는 칸 (x,y)
           3. 나이트가 이동하려는 칸. (x2,y2)
        * */

        for (int i = 0; i < T; i++) {
            depth = -1;
            // 체스판
            int l = Integer.parseInt(br.readLine());
            chess = new int[l][l];
            visited = new boolean[l][l];
            visitedInit(visited, l);

            // 현재 나이트 좌표
            StringTokenizer st = new StringTokenizer(br.readLine());
            int x = Integer.parseInt(st.nextToken());
            int y = Integer.parseInt(st.nextToken());
            Point currentKnight = new Point(x, y);

            // 이동할 나이트 좌표
            st = new StringTokenizer(br.readLine());
            x = Integer.parseInt(st.nextToken());
            y = Integer.parseInt(st.nextToken());
            Point afterKnight = new Point(x, y);

            Queue<Point> queue = new LinkedList<>();

            GoKnight(currentKnight, afterKnight, l, queue);

            bw.write(String.valueOf(depth) + "\n");
        }

        bw.flush();
        br.close();
        bw.close();
    }

    private static void visitedInit(boolean[][] visited, int l) {
        for (int i = 0; i < l; i++) {
            for (int j = 0; j < l; j++) {
                visited[i][j] = false;
            }
        }
    }


    private static void GoKnight(Point currentKnight, Point afterKnight, int l, Queue<Point> queue) {
        BFS(currentKnight, afterKnight, l, queue);
        //DFS(currentKnight, afterKnight, l);
    }


    private static void BFS(Point currentKnight, Point afterKnight, int l, Queue<Point> queue) {
        visited[currentKnight.x][currentKnight.y] = true;
        queue.add(currentKnight);
        int queueSize = 1;
        int childNum = 1;
        while (!queue.isEmpty()) {
            Point point = queue.poll();
            queueSize = queueSize - 1;
            int x = point.x;
            int y = point.y;

            if (queueSize == 0) {
                queueSize = childNum;
                childNum = 0;
                depth++;
            }

            if (x == afterKnight.x && y == afterKnight.y) {
                return;
            }

            /* 나이트가 이동하는 방법 : 무조건 앞으로 한칸, 대각선 좌우로 한칸 -> 한점에서 이동한다면 8가지
               단, 좌표범위 안에서 움직여야한다.
               이동하기전:
               0 <= point.x, point.y <= l - 1
               이동한후:
               0 <= afterPoint.x, afterPoint.y <= l - 1
               8가지 경우:
               0 <= x - 2, y - 1 <= l - 1
               : 2 <= x <= l - 1, 1 <= y <= l - 1
               0 <= x - 2, y + 1 <= l - 1
               : 2 <= x <= l - 1, 0 <= y <= l - 2
               0 <= x - 1, y - 2 <= l - 1
               : 1 <= x <= l - 1, 2 <= y <= l - 1
               0 <= x - 1, y + 2 <= l - 1
               : 1 <= x <= l - 1, 0 <= y <= l - 3
               0 <= x + 1, y - 2 <= l - 1
               : 0 <= x <= l - 2, 2 <= y <= l - 1
               0 <= x + 1, y + 2 <= l - 1
               : 0 <= x <= l - 2, 0 <= y <= l - 3

               0 <= x + 2, y - 1 <= l - 1
               : 0 <= x <= l - 3, 1 <= y <= l - 1
               0 <= x + 2, y + 1 <= l - 1
               : 0 <= x <= l - 3, 0 <= y <= l - 2
            * */


            if (2 <= x && x <= l - 1 && 1 <= y && y <= l - 1) {
                if (!visited[x - 2][y - 1]) {
                    visited[x - 2][y - 1] = true;
                    childNum++;
                    queue.add(new Point(x - 2, y - 1));
                }
            }
            if (2 <= x && x <= l - 1 && 0 <= y && y <= l - 2) {
                if (!visited[x - 2][y + 1]) {
                    visited[x - 2][y + 1] = true;
                    childNum++;
                    queue.add(new Point(x - 2, y + 1));
                }
            }
            if (1 <= x && x <= l - 1 && 2 <= y && y <= l - 1) {
                if (!visited[x - 1][y - 2]) {
                    visited[x - 1][y - 2] = true;
                    childNum++;
                    queue.add(new Point(x - 1, y - 2));
                }
            }
            if (1 <= x && x <= l - 1 && 0 <= y && y <= l - 3) {
                if (!visited[x - 1][y + 2]) {
                    visited[x - 1][y + 2] = true;
                    childNum++;
                    queue.add(new Point(x - 1, y + 2));
                }
            }

            if (0 <= x && x <= l - 2 && 2 <= y && y <= l - 1) {
                if (!visited[x + 1][y - 2]) {
                    visited[x + 1][y - 2] = true;
                    childNum++;
                    queue.add(new Point(x + 1, y - 2));
                }
            }
            if (0 <= x && x <= l - 2 && 0 <= y && y <= l - 3) {
                if (!visited[x + 1][y + 2]) {
                    visited[x + 1][y + 2] = true;
                    childNum++;
                    queue.add(new Point(x + 1, y + 2));
                }
            }
            if (0 <= x && x <= l - 3 && 1 <= y && y <= l - 1) {
                if (!visited[x + 2][y - 1]) {
                    visited[x + 2][y - 1] = true;
                    childNum++;
                    queue.add(new Point(x + 2, y - 1));
                }
            }
            if (0 <= x && x <= l - 3 && 0 <= y && y <= l - 2) {
                if (!visited[x + 2][y + 1]) {
                    visited[x + 2][y + 1] = true;
                    childNum++;
                    queue.add(new Point(x + 2, y + 1));
                }
            }
        }
    }
    private static void DFS(Point currentKnight, Point afterKnight, int l) {
        int x = currentKnight.x;
        int y = currentKnight.y;
        visited[x][y] = true;

        if (x == afterKnight.x && y == afterKnight.y) {
            return;
        }

        if (2 <= x && x <= l - 1 && 1 <= y && y <= l - 1) {
            visited[x - 2][y - 1] = true;
            depth++;
            currentKnight.x = x - 2;
            currentKnight.y = y - 1;
            DFS(currentKnight, afterKnight, l);
        }
        if (2 <= x && x <= l - 1 && 0 <= y && y <= l - 2) {
            visited[x - 2][y + 1] = true;
            depth++;
            currentKnight.x = x - 2;
            currentKnight.y = y = 1;
            DFS(currentKnight, afterKnight, l);
        }
        if (1 <= x && x <= l - 1 && 2 <= y && y <= l - 1) {
            visited[x - 1][y - 2] = true;
            currentKnight.x = x - 1;
            currentKnight.y = y - 2;
            DFS(currentKnight, afterKnight, l);
        }
        if (1 <= x && x <= l - 1 && 0 <= y && y <= l - 3) {
            visited[x - 1][y + 2] = true;
            currentKnight.x = x - 1;
            currentKnight.y = y + 2;
            DFS(currentKnight, afterKnight, l);
        }

        if (0 <= x && x <= l - 2 && 2 <= y && y <= l - 1) {
            visited[x + 1][y - 2] = true;
            currentKnight.x = x + 1;
            currentKnight.y = y - 2;
            DFS(currentKnight, afterKnight, l);
        }
        if (0 <= x && x <= l - 2 && 0 <= y && y <= l - 3) {
            visited[x + 1][y + 2] = true;
            currentKnight.x = x + 1;
            currentKnight.y = y - 2;
            DFS(currentKnight, afterKnight, l);
        }
        if (0 <= x && x <= l - 3 && 1 <= y && y <= l - 1) {
            visited[x + 2][y - 1] = true;
            currentKnight.x = x + 2;
            currentKnight.y = y - 1;
            DFS(currentKnight, afterKnight, l);
        }
        if (0 <= x && x <= l - 3 && 0 <= y && y <= l - 2) {
            visited[x + 2][y + 1] = true;
            currentKnight.x = x + 2;
            currentKnight.y = y + 1;
            DFS(currentKnight, afterKnight, l);
        }
    }
}
