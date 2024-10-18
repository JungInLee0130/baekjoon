import java.util.*;

class Solution {
    static int[][] cost;
    static int[] extra;
    static List<Integer>[] sales_info;
    static int[] g_sales;
    static int INF = Integer.MAX_VALUE;
    
    public int solution(int[] sales, int[][] links) {
        int answer = INF;
        
        int n1 = sales.length;
        int n2 = links.length;
        
        cost = new int[n1 + 1][2];
        extra = new int[n1 + 1];
        sales_info = new ArrayList[n1 + 1];
        g_sales = new int[n1];
        
        for (int i = 0; i < n1; i++){
            g_sales[i] = sales[i];
        }
        
        for (int i = 0; i < n1 + 1; i++){
            sales_info[i] = new ArrayList<>();
        }
        
        for (int i = 0; i < n2; i++){
            sales_info[links[i][0]].add(links[i][1]);
        }
        
        trevarsal(1);
        
        return Math.min(cost[1][0], cost[1][1]);
    }
    
    static void trevarsal(int pos){
        int child_num = sales_info[pos].size();
        
        cost[pos][0] = 0;
        cost[pos][1] = g_sales[pos - 1];
        
        if (child_num == 0) return;
        
        int extra_cost = INF;
        
        for (int child : sales_info[pos]){
            trevarsal(child);
            
            if (cost[child][0] < cost[child][1]){
                cost[pos][0] += cost[child][0];
                cost[pos][1] += cost[child][0];
                
                extra_cost = Math.min(extra_cost, cost[child][1] - cost[child][0]);
            }
            else{
                cost[pos][0] += cost[child][1];
                cost[pos][1] += cost[child][1];
                
                extra_cost = 0;
            }
        }
        
        cost[pos][0] += extra_cost;
    }
}