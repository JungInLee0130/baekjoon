import java.util.*;
class Solution {
    static int N, M, T;
    static PriorityQueue<Integer> waiters;
    public String solution(int n, int t, int m, String[] timetable) {
        N = n;
        M = m;
        T = t;
        
        waiters = new PriorityQueue<>();
        // 대기자들 시간순으로 정렬해서 넣어줌.(int 오름차순(디폴트))
        setPq(timetable);
        
        // 버스타기
        return getOnBus();
    }
    
    static String getOnBus(){
        int arrivedBusCount = 0;
        int arrivedBusTime = timeToInt("09:00");
        
        // arrivedBusCount < N일때까지
        while (arrivedBusCount <= N - 1){
            // arrivedBusCount > 0일때 시간 +t
            // 0일때는 그시각에 탑승을 수행해서 +t x
            if (arrivedBusCount > 0) arrivedBusTime += T;
            int boardCount = 0;
            
            while (waiters.size() > 0 && boardCount <= M - 1){
                // 도착시간보다 크면 break;
                if (waiters.peek() > arrivedBusTime) break;
                // boardCount == M - 1 이고 arrivedBusCount == N - 1일때
                // 막차에 남은 한자리 일때
                if (boardCount == M - 1 && arrivedBusCount == N - 1){
                    // 일단 콘은 타야하니까 -1분 해줌.
                    // 같은시간이면 맨 뒤에 타서 못탐.
                    return timeToStr(waiters.poll() - 1);
                }
                // poll
                waiters.poll();
                // boardCount++
                boardCount++;
            }
            // arrivedBusCount++
            arrivedBusCount++;
        }
        
        // 빈자리가 남았다는 거니까
        return timeToStr(arrivedBusTime);
    }
    
    static void setPq(String[] timetable){
        for (String time : timetable){
            waiters.add(timeToInt(time));
        }
    }
    
    static int timeToInt(String time){
        String[] times = time.split(":");
        int hour = Integer.parseInt(times[0]);
        int min = Integer.parseInt(times[1]);
        
        return hour * 60 + min;
    }
    
    static String timeToStr(int time){
        String hour = String.format("%02d", time/60);
        String min = String.format("%02d", time % 60);
        return hour + ":" + min;
    }
}