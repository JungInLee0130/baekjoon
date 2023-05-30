class Solution {
    static int N, M;
    public int solution(int[] picks, String[] minerals) {
        N = picks.length;
        M = minerals.length;
        
        dfs(picks, minerals,0,0);
         
        return min;
    }
   
    
    private static int min = Integer.MAX_VALUE;
    
    private static void dfs(int[] picks, String[] minerals, int idx, int power){
        if (power > min){
            return;
        }
        if (idx == M || noPicks(picks)){ // 다캐거나, 곡괭이가 먼저 닳거나
            min = Math.min(min,power); 
            return;
        }
        
        // 1,2,3 : 다이아, 철, 돌
        for(int i = 0; i < 3; i++){ // 최대 15개
            if (picks[i] > 0){
                picks[i]--;
                int[] result = rocking(i, minerals,idx,power);
                dfs(picks,minerals,result[0],result[1]);
                picks[i]++;
            }
        }
    }
    
    private static int[] rocking(int pick, String[] minerals, int idx,int power){
        int i = idx;
        int count = 5;
        
        if (pick == 0){
            for(i = idx; i < M; i++){
                if (count == 0){
                    break;
                }
                power += 1;        
                count--;
            }
        }
        else if (pick == 1){
            for(i = idx; i < M; i++){
                if (count == 0){
                    break;
                }
                if (minerals[i].equals("diamond")){
                    power += 5;
                }
                else{
                    power += 1;
                }
                count--;
            }
        }
        else{
            for(i = idx; i < M; i++){
                if (count == 0){
                    break;
                }
                if (minerals[i].equals("diamond")){
                    power += 25;
                }
                else if (minerals[i].equals("iron")){
                    power += 5;
                }
                else{
                    power += 1;
                }
                count--;
            }
        }
        
        //System.out.println(i + ":" + power);
        return new int[] {i, power};
    }
     private static boolean noPicks(int[] picks){
        for(int i = 0; i < 3; i++){
            if (picks[i] != 0){
                return false;
            }
        }
        return true;
    }
}