import java.util.*;

class Solution {

    public int[] solution(int[][] edges) {
        int[] answer = new int[4];
        
        int edgeNums = edges.length;
        
        // [3,4] : 0 : 나가는 간선, 1 : 들어오는 간선
        int vertexNum = 0;
        for (int i = 0; i < edgeNums; i++){
            int max = Math.max(edges[i][0], edges[i][1]);
            vertexNum = Math.max(vertexNum, max);
        }
        
        int[][] inout = new int[vertexNum + 1][2];
        for (int i = 0; i < edgeNums; i++){
            int start = edges[i][0];
            int end = edges[i][1];
            
            inout[start][0]++; // 나가는것 count
            inout[end][1]++; // 들어오는것 count
        }
        
        // 정점 : 나가는것 2개이상 (그래프의 갯수 알수있음.)
        // 도넛 : 정점 - (8자개수 + 도넛개수)
        // 막대 : 나가는 간선수 : 0, 들어오는 간선수 : 1
        // 8자 : 나가는 간선수 : 2, 들어오는 간선수 : 2
        
        answer = new int[4];
        
        for (int i = 1; i <= vertexNum; i++){
            // 정점
            if (inout[i][0] >= 2 && inout[i][1] == 0){
                answer[0] = i;
            }
            else if (inout[i][0] == 0 && inout[i][1] > 0){
                answer[2]++;
            }
            else if (inout[i][0] >= 2 && inout[i][1] >= 2){
                answer[3]++;
            }
        }
        
        answer[1] = inout[answer[0]][0] - answer[2] - answer[3];
        
        
        return answer;
    }
    
}