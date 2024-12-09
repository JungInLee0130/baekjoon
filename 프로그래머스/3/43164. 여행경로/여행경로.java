import java.util.*;

class Solution {
    static int N; // 지나는 공항의 개수 : tickets + 1
    static class Ticket implements Comparable<Ticket>{
        String start;
        String end;
        
        public Ticket(String start, String end){
            this.start = start;
            this.end = end;
        }
        
        // 오름차순
        @Override
        public int compareTo(Ticket t){
            if (start.equals(t.start)){
                return end.compareTo(t.end);
            }
            return start.compareTo(t.start);
        }
    }
    static Ticket[] Tickets;
    public String[] solution(String[][] tickets) {
        N = tickets.length + 1;
        
        // ICN 에서 시작해서 N개만큼 경로를 만들어야함.
        Tickets = new Ticket[tickets.length];
        
        for (int i = 0; i < tickets.length; i++){
            Tickets[i] = new Ticket(tickets[i][0], tickets[i][1]);
        }
        
        Arrays.sort(Tickets);
        
        visited = new boolean[tickets.length];
        
        route = new String[N];
        route[0] = "ICN";
        
        dfs("ICN", 1);
        
        return route;
    }
    static boolean[] visited;
    static String[] route;
    static boolean isFinished;
    
    static void dfs(String start, int count){
        if (count >= N){
            isFinished = true;
            return;
        }
        for (int i = 0; i < N - 1; i++){ // ticket.length
            if (isFinished || visited[i]) continue;
            if (start.equals(Tickets[i].start)){
                visited[i] = true;
                route[count] = Tickets[i].end;
                dfs(Tickets[i].end, count + 1);
                visited[i] = false;
            }
        }
    }
}