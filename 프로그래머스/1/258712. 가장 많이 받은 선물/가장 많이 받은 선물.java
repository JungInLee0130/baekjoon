import java.util.*;

class Solution {
    private static int[][] send;
    private static int n, m;
    public int solution(String[] friends, String[] gifts) {
        n = friends.length;
        m = gifts.length; // 주고 받은횟수
        
        Map<String, Integer> map = new HashMap<>();
        
        for (int i = 0; i < n; i++){
            map.put(friends[i], i); // 0번부터 순서 매김.
        }
        
        send = new int[n][n]; // n * n 배열
        
        for (int i = 0; i < m; i++){
            String[] sp = gifts[i].split(" ");
            int a = map.get(sp[0]);
            int b = map.get(sp[1]);
            
            send[a][b]++; // 선물 줬으면 ++
        }
        // 선물지수 계산
        // 준선물
        int[] giftPoints = new int[n];
        for (int i = 0; i < n; i++){
            for (int j = 0; j < n; j++){
                giftPoints[i] += send[i][j];
            }
        }
        
        // 받은선물
        for (int j = 0; j < n; j++){
            for (int i = 0; i < n; i++){
                giftPoints[j] -= send[i][j];
            }
        }
        
        
        // 선물지수 완성
        
        // send에 이차원 배열로 다 표시 완료
        int[] answer = new int[n];
        for (int i = 0; i < n; i++){
            for (int j = i; j < n; j++){
                if (i == j) continue;
                
                if (send[i][j] > send[j][i]){
                    answer[i]++; // 더 크면 받음
                }
                // 같으면 : 선물지수가 큰사람이 받음.
                else if (send[i][j] == send[j][i]){
                    if (giftPoints[i] > giftPoints[j]){
                        answer[i]++;
                    }
                    else if (giftPoints[i] < giftPoints[j]){
                        answer[j]++;
                    }
                }
                // 더 작음
                else{
                    answer[j]++;
                }
            }
        }
        
        
        Arrays.sort(answer);

        
        return answer[n - 1];
    }
}