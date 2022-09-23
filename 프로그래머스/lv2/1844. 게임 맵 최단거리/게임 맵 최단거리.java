import java.util.Queue;
import java.util.LinkedList;
import java.awt.Point;

class Solution {
    private static Queue<Point> queue = new LinkedList<>();
    private static int[] dx = {-1,0,1,0};
    private static int[] dy = {0,1,0,-1};
    private static boolean[][] visited;
    public int solution(int[][] maps) {
        int row = maps.length;
        int column = maps[0].length;
        visited = new boolean[row][column];

        for (int i = 0; i < row; i++) {
            for (int j = 0; j < column; j++) {
                visited[i][j] = false;
            }
        }

        int min = 1;
        visited[0][0] = true;
        queue.add(new Point(0, 0));
        int queueSize = queue.size();
        int nextChildNum = 0;

        while (!queue.isEmpty()) {
            Point point = queue.poll();
            queueSize--;
            int x = point.x;
            int y = point.y;
            
            if (row - 1 == x && column - 1 == y) {
                return min;
            }

            for (int i = 0; i < 4; i++) {
                int newX = x + dx[i];
                int newY = y + dy[i];

                if (0 <= newX && newX <= row - 1 && 0 <= newY && newY <= column - 1
                    && maps[newX][newY] == 1
                    && !visited[newX][newY]) {
                    visited[newX][newY] = true;
                    nextChildNum++;
                    queue.add(new Point(newX, newY));
                }
            }

            


            if (queueSize == 0) {
                queueSize = nextChildNum;
                nextChildNum = 0;
                min++;
            }
        }

        return -1;
    }
}