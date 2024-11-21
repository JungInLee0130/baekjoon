import java.awt.*;
import java.io.*;
import java.util.*;
import java.util.List;
import java.util.stream.Collectors;

public class Main {
    static int R, C;
    static int[][] map;
    static class Point{
        int x;
        int y;
        int dir;

        public Point(int x, int y, int dir) {
            this.x = x;
            this.y = y;
            this.dir = dir;
        }
    }
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        StringTokenizer st = new StringTokenizer(br.readLine());

        C = Integer.parseInt(st.nextToken());
        R = Integer.parseInt(st.nextToken());

        map = new int[R + 1][C + 1];

        int K = Integer.parseInt(br.readLine());
        ArrayList<Point> pointlist = new ArrayList<>();
        for (int i = 0; i < K; i++) {
            st = new StringTokenizer(br.readLine());

            int dir = Integer.parseInt(st.nextToken());
            int move = Integer.parseInt(st.nextToken());

            Point point = getPoint(dir, move);

            pointlist.add(point);
        }

        st = new StringTokenizer(br.readLine());

        Point dongkun = getPoint(Integer.parseInt(st.nextToken()),
                Integer.parseInt(st.nextToken()));

        int answer = 0;
        for (Point cur: pointlist) {
            // 가로가로
            if (horizonhorizon(cur.dir, dongkun.dir)){
                int dis1 = cur.y + dongkun.y;
                int dis2 = C - cur.y + C - dongkun.y;

                answer += (dis1 <= dis2 ? dis1 : dis2) + R;
            }
            // 세로세로
            else if (verticalVertical(cur.dir, dongkun.dir)) {
                int dis1 = cur.x + dongkun.x;
                int dis2 = R - cur.x + R - dongkun.x;

                answer += (dis1 <= dis2 ? dis1 : dis2) + C;
            }
            else{
                answer += Math.abs(cur.x - dongkun.x) + Math.abs(cur.y - dongkun.y);
            }
        }

        System.out.println(answer);


        bw.flush();
        bw.close();
        br.close();
    }


    private static boolean verticalVertical(int dir1, int dir2) {
        return (dir1 == 3 && dir2 == 4) || (dir1 == 4 && dir2 == 3);
    }

    private static boolean horizonhorizon(int dir1, int dir2) {
        return (dir1 == 1 && dir2 == 2) || (dir1 == 2 && dir2 == 1);
    }

    private static Point getPoint(int dir, int move) {
        Point point = new Point(0, 0, dir);
        if (dir == 1) {
            point.x = 0;
            point.y = move;
        }
        else if (dir == 2) {
            point.x = R;
            point.y = move;
        }
        else if (dir == 3) {
            point.x = move;
            point.y = 0;
        }
        else if (dir == 4) {
            point.x = move;
            point.y = C;
        }
        return point;
    }
}