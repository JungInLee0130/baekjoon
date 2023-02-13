import java.util.Arrays;
import java.util.Collections;
import java.util.Comparator;
import java.util.PriorityQueue;
class Solution {
   public int solution(int[][] routes) {
        // 차량의 수
        int len = routes.length;

        Arrays.sort(routes, new Comparator<int[]>() {
            @Override
            public int compare(int[] o1, int[] o2) {
                /*if (o1[0] == o2[0]) {
                    return o1[1] - o2[1]; // 진출 시점 기준으로 오름차순 정렬
                }*/
                return o1[1] - o2[1]; // 진입시점 기준으로 오름차순 정렬
            }
        });

        // 최대힙 정렬
        PriorityQueue<Integer> priorityQueue = new PriorityQueue<>(Collections.reverseOrder());
        priorityQueue.add(routes[0][1]); // 처음 진출 지점

        for (int i = 0; i < len; i++) {
            // 진출지점보다 진입지점이 크면
            if (routes[i][0] > priorityQueue.peek()) { // priorityQueue.peek보다 진입지점이 크면
                priorityQueue.add(routes[i][1]); // 해당 진출지점을 넣는다. 최대힙 정렬
            }
        }

        return priorityQueue.size();
    }
}