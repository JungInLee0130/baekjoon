import java.util.*;
class Solution {
    static String[] times;
    static double second;
    static List<Traffic> timelist;
    static int lineN;
    static class Traffic implements Comparable<Traffic>{
        double start;
        double end;
        
        public Traffic(double start, double end){
            this.start = start;
            this.end = end;
        }
        
        @Override
        public int compareTo(Traffic t){
            double sub = this.start - t.start;
            
            if (sub > 0){
                return 1;
            }
            else if (sub < 0){
                return -1;
            }
            else{
                double subEnd = this.end - t.end;
                if (subEnd > 0){
                    return 1;
                }
                else if (subEnd < 0){
                    return -1;
                }
                else{
                    return 0;
                }
            }
        }
    }
    public int solution(String[] lines) {
        lineN = lines.length;
        
        timelist = new ArrayList<>();
        
        for (int i = 0; i < lineN; i++){
            times = lines[i].split(" ");

            String[] hms = times[1].split(":"); // 시,분,초(ms)가 들어있음.
            double originalSecond = timeToDouble(hms);
            double second = timeToDouble(times[2]);
            
            // 시작시간과 종료시간을 계산해서 list에 넣어야할듯
            timelist.add(new Traffic(originalSecond - second + 0.001,
                                      originalSecond));
        }
        
        //Collections.sort(timelist);
        
        List<Integer> countlist = new ArrayList<>();
        int answer = 1;
        
        for (int i = 0; i < lineN; i++){
            double curEnd = timelist.get(i).end;
            int count = 1;
            for (int j = i + 1; j < lineN; j++){
                double start = timelist.get(j).start;
                
                // 1초기간재고
                if (curEnd + 1 > start){
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