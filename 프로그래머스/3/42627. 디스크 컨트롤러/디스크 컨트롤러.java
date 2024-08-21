import java.util.*;

class Solution {
    static int N;
    static int maxTime = 0;
    static int answer;
    public int solution(int[][] jobs) {
        N = jobs.length;
        
        
        Arrays.sort(jobs, new Comparator<>(){
            @Override
            public int compare(int[] ja, int[] jb){
                return Integer.compare(ja[0], jb[0]); // 요청시점 순으로 정렬
            }
        });
        
        
        getTotalTime(jobs);
        
        
        return answer / N;
    }
    
    static void getTotalTime(int[][] jobs){
        answer = 0;
        
        PriorityQueue<Job> pq = new PriorityQueue<>(); // 오름차순
        
        int idx = 0;
        int time = 0;
        while (!pq.isEmpty() || idx < N){
            while (idx < N && jobs[idx][0] <= time){
                pq.add(new Job(idx, jobs[idx][0], jobs[idx][1]));
                idx++;
            }
            
            if (pq.isEmpty()){
                time = jobs[idx][0];
            }
            else{
                Job curJob = pq.poll();
                time += curJob.running;
                answer += time - curJob.start;
            }
        }
    }
    static class Job implements Comparable<Job>{
        int num;
        int start;
        int running;
        
        public Job(int num,int start, int running){
            this.num = num;
            this.start = start;
            this.running = running;
        }
        
        @Override
        public int compareTo(Job j){
            return this.running - j.running; // running 오름차순
        }
    }
}