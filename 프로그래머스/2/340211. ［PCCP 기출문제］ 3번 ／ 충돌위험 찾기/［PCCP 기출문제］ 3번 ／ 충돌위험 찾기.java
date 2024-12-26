import java.io.*;
import java.util.*;

class Solution {
    static int[][] Points, Routes;
    static Map<Integer, Point> pointMap = new HashMap<>();

    static class Point{
        int x;
        int y;
        public Point(int x, int y){
            this.x = x;
            this.y = y;
        }

        @Override
        public boolean equals(Object obj){
            if (this == obj) return true;
            if (obj == null || getClass() != obj.getClass()) return false;
            Point point = (Point)obj;
            return x == point.x && y == point.y;
        }

        @Override
        public int hashCode(){
            return Objects.hash(x,y);
        }
    }

    public int solution(int[][] points, int[][] routes) {
        Points = points;
        Routes = routes;

        int cnt = solve();

        return cnt;
    }

    static void setPointMap(int[][] points){
        for (int i = 0; i < points.length; i++){
            pointMap.put(i + 1, new Point(Points[i][0], Points[i][1]));
        }
    }


    static List<List<Point>> shortestRoutes;

    static int solve(){
        setPointMap(Points);

        int longestRouteDist = 0;
        shortestRoutes = new ArrayList<>();

        for (int[] r : Routes){
            List<Point> route = findShortestRoute(r);
            shortestRoutes.add(route);
            // 이건 그냥 루트 길이 저장하는 용도같은데
            longestRouteDist = (longestRouteDist < route.size()) ? route.size() : longestRouteDist;
        }

        int answer = findStrikeCount(shortestRoutes, longestRouteDist);

        return answer;
    }

    static List<Point> findShortestRoute(int[] routePoints){
        List<Point> shortestRoute = new ArrayList<>();

        for (int i = 1; i < routePoints.length; i++){
            Point start = pointMap.get(routePoints[i - 1]);
            Point end = pointMap.get(routePoints[i]);

            int r = start.x;
            int c = start.y;

            if (shortestRoute.isEmpty()){ // 맨 첫 점
                shortestRoute.add(new Point(r,c));
            }

            while (r != end.x){
                r += (r > end.x) ? -1 : 1;
                shortestRoute.add(new Point(r,c));
            }

            while (c != end.y){
                c += (c > end.y) ? -1 : 1;
                shortestRoute.add(new Point(r,c));
            }
        }

        return shortestRoute;
    }

    static int findStrikeCount(List<List<Point>> shortestRoutes, int longestRouteDist){
        int totalCount = 0;

        for (int i = 0; i < longestRouteDist; i++){
            Map<Point, Integer> countMap = new HashMap<>();

            for (List<Point> shortestRoute : shortestRoutes){
                // 해당 시간에 해당하는 점이 있어야함.
                Point point = (shortestRoute.size() > i) ? shortestRoute.get(i) : null;

                if (point != null){
                    countMap.put(point, countMap.getOrDefault(point, 0) + 1);
                }
            }

            for (int count : countMap.values()){
                if (count > 1){
                    totalCount += 1;
                }
            }
        }
        return totalCount;
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
