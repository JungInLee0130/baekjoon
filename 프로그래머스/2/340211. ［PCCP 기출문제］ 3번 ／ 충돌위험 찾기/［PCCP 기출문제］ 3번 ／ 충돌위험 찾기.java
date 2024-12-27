import java.io.*;
import java.util.*;

class Solution {
    static int[][] Points, Routes;

    static class Point{
        int x;
        int y;
        public Point(int x, int y){
            this.x = x;
            this.y = y;
        }

        @Override
        public boolean equals(Object obj) {
            if (this == obj) return true;
            if (obj == null || getClass() != obj.getClass()) return false;
            Point point = (Point) obj;
            return x == point.x && y == point.y;
        }

        @Override
        public int hashCode() {
            return Objects.hash(x, y);
        }
    }

    static int N;

    public int solution(int[][] points, int[][] routes) {
        Points = points;
        Routes = routes;

        int cnt = solve();

        return cnt;
    }

    static void setN(){
        int max = 0;
        for (int i = 0; i < Points.length; i++){
            for (int j = 0; j < 2; j++) {
                max = Math.max(max, Points[i][j]);
            }
        }

        N = max;
    }

    static int solve(){
        setN();

        routeList = new ArrayList<>();

        for (int i = 0; i < Routes.length; i++) {
            routeList.add(new ArrayList<>());
            for (int j = 0; j < Routes[i].length - 1; j++) {
                move(Routes[i][j] - 1, Routes[i][j + 1] - 1, i);
            }
        }

        for (int i = 0; i < routeList.size(); i++){
            maxLen = Math.max(maxLen, routeList.get(i).size());
        }

        int cnt = strikeCount();

        return cnt;
    }

    static int maxLen = 0; // maxTime

    static List<List<Point>> routeList;

    static void move(int start, int end, int idx){
        int startX = Points[start][0] - 1;
        int startY = Points[start][1] - 1;
        int endX = Points[end][0] - 1;
        int endY = Points[end][1] - 1;

        List<Point> route = routeList.get(idx);

        if (routeList.get(idx).size() == 0) {
            route.add(new Point(startX, startY)); // 맨처음 좌표 대입 : t == 0일때임
        }

        if (startX == endX && startY == endY) { // 좌표 자기자신이면 그냥 return
            return;
        }

        while (!(endX == startX)){
            startX += (endX > startX) ? 1 : -1;
            route.add(new Point(startX, startY));
        }

        while (!(endY == startY)){
            startY += (endY > startY) ? 1 : -1;
            route.add(new Point(startX, startY));
        }
    }

    static int strikeCount(){
        int cnt = 0;
        for (int j = 0; j < maxLen; j++) { // 시간
            Map<Point, Integer> map = new HashMap<>(); // 시간마다 map 생성(카운트)

            for (int i = 0; i < routeList.size(); i++) { // 루트
                if (j >= routeList.get(i).size()) continue; // 루트끝에 도달하면 break
                Point point = routeList.get(i).get(j);
                map.put(point, map.getOrDefault(point, 0) + 1); // 위치마다 카운트 1씩 넣어줌
            }

            for (Map.Entry<Point, Integer> entry:map.entrySet()) {
                if (entry.getValue() > 1) {
                    cnt++;
                }
            }
            
        }

        return cnt;
    }


    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        Solution s = new Solution();

        int[][] points = {{3, 2}, {6, 4}, {4, 7}, {1, 4}};
        int[][] routes = {{4, 2}, {1, 3}, {2, 4}};

        System.out.println(s.solution(points, routes));



        bw.flush();
        br.close();
        bw.close();
    }
}
