import java.util.*;

class Solution {
    static class Server implements Comparable<Server> {
        int start;
        int end;
        
        public Server(int start, int end) {
            this.start = start;
            this.end = end;
        }
        
        @Override
        public int compareTo(Server s) {
            return Integer.compare(this.end, s.end);    // 종료시간 기준으로 오름차순 정렬
        }
    }
    static PriorityQueue<Server> pq;
    static int result = 0;
    public int solution(int[] players, int m, int k) {
        pq = new PriorityQueue<>();                     // 우선순위큐 선언
        
        for (int i = 0; i < 24; i++) {
            // 시간별 반복
            if (players[i] >= m) {
                int server = players[i] / m;            // 증설된 서버 수 
                
                int count = server - pq.size();
                if (count > 0){
                    result += count;      // 증설횟수 : server - pq 사이즈
                    
                    for (int j = 0; j < count; j++) {
                        pq.add(new Server(i, i + k - 1));    
                    }
                }
            }
            
            while (!pq.isEmpty() && pq.peek().end == i) {
                // 서버 제거 로직 : pq에 원소가 있고 && 종료시간이면
                pq.poll();                                      // poll    
            }
        }
        
        return result;
    }
}