import java.util.*;
class Solution {
    static String[] times;
    static double second;
    static List<Traffic> timelist;
    static int lineN;
    static class Traffic{
        double start;
        double end;
        
        public Traffic(double start, double end){
            this.start = start;
            this.end = end;
        }
        
        // 정렬은 할필요가 없을거같음.
    }
    public int solution(String[] lines) {
        // lines : 응답완료 시간을 기준으로 오름차순으로 정렬되어있음.
        // 2016-09-15일만 포함.
        lineN = lines.length;
        
        timelist = new ArrayList<>();
        
        // 1. 시간 -> double로
        for (int i = 0; i < lineN; i++){
            times = lines[i].split(" ");

            String[] hms = times[1].split(":"); // 시,분,초(ms)가 들어있음.
            double originalSecond = timeToDouble(hms);
            double second = timeToDouble(times[2]);
            
            // 시작시간과 종료시간을 계산해서 list에 넣어야할듯
            timelist.add(new Traffic(originalSecond - second + 0.001,
                                      originalSecond));
        }
        
        // 종료시간 + 1 > 시작시간인것들의 개수 찾기
        List<Integer> countlist = new ArrayList<>();
        int answer = 1;
        
        for (int i = 0; i < lineN; i++){
            double curEnd = timelist.get(i).end;
            int count = 1;
            for (int j = i + 1; j < lineN; j++){
                double start = timelist.get(j).start;
                
                // 1초기간재고
                // 5.001 < 5.002
                if (start < curEnd + 1){
                    count++;
                }
            }
            answer = Math.max(count, answer);
        }
            
        return answer;
    }
    
    static double timeToDouble(String[] str){
        double hour = Double.parseDouble(str[0]);
        double min = Double.parseDouble(str[1]);
        double seconds = Double.parseDouble(str[2]);
        
        return hour * 60 * 60 + min * 60 + seconds;
    }
    
    static double timeToDouble(String miliseconds){
        String str = miliseconds.substring(0, miliseconds.length() - 1);
        
        return Double.parseDouble(str);
    }
}