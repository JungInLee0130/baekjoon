import java.io.*;
import java.util.*;

class Main {
    // 백준 야구
	static int N;
	static int[][] score;
	static boolean[] visited;
	static int[] order;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        N = Integer.parseInt(br.readLine());
        
        score = new int[N + 1][10]; // 1 ~ N, 1 ~ 9
        for (int i = 1; i <= N; i++) {
        	StringTokenizer st = new StringTokenizer(br.readLine());
        	
        	for (int j = 1; j <= 9; j++) {
        		score[i][j] = Integer.parseInt(st.nextToken());
        	}
        }
        
        order = new int[10]; // 1 ~ 9
        visited = new boolean[10]; // 1 ~ 9
        order[4] = 1;
        visited[4] = true;

        permutation(2);
        
        
        System.out.println(max);
   

        br.close();
    }
    private static void permutation(int num) {
    	if (num == 10) {
			// 타순에 따라 스코어링
    		int result = getScore();
			max = Math.max(result, max);
			//System.out.println(result);
			return;
		}
		
		for (int i = 1; i <= 9; i++) {
			if (order[i] > 0) continue;
			//visited[i] = true;
			order[i] = num;
			permutation(num + 1);
			order[i] = 0;
			//visited[i] = false;
		}
	}
    private static int getScore2() {
    	int sum = 0;
    	
    	int idx = 1; // 1번 타자부터 시작
    	for(int r=1; r<= N; r++) {
    		int inningScore = 0; // 현재 이닝에서 얻는 점수
    		int out = 0; // 현재 이닝에서 아웃의 수
    		boolean[] base = new boolean[4]; // true : 해당 베이스에 선수가 있음, false : 해당 베이스에 선수가 없음
    		
    		// 3 아웃이 되기 전까지 반복
    		while(out < 3) {
    			switch(score[r][order[idx]]) {
    				// 아웃
		    		case 0:
		    			out++;
		    			break;
		    		// 1루타
		    		case 1:
		    			if(base[3]) {
		    				inningScore++;
		    				base[3] = false;
		    			}
		    			if(base[2]) {
		    				base[3] = true;
		    				base[2] = false;
		    			}
		    			if(base[1]) {
		    				base[2] = true;
		    			}
		    			base[1] = true;
		    			break;
		    		// 2루타
		    		case 2:
		    			if(base[3]) {
		    				inningScore++;
		    				base[3] = false;
		    			}
		    			if(base[2]) {
		    				inningScore++;
		    			}
		    			if(base[1]) {
		    				base[3] = true;
		    				base[1] = false;
		    			}
		    			base[2] = true;
		    			break;
		    		// 3루타	
		    		case 3:
		    			if(base[3]) {
		    				inningScore++;
		    			}
		    			if(base[2]) {
		    				inningScore++;
		    				base[2] = false;
		    			}
		    			if(base[1]) {
		    				inningScore++;
		    				base[1] = false;
		    			}
		    			base[3] = true;
		    			break;
		    		// 홈런	
		    		case 4:
		    			if(base[3]) {
		    				inningScore++;
		    				base[3] = false;
		    			}
		    			if(base[2]) {
		    				inningScore++;
		    				base[2] = false;
		    			}
		    			if(base[1]) {
		    				inningScore++;
		    				base[1] = false;
		    			}
		    			inningScore++;
		    			break;
    			}
    			
    			// 다음 타자로
    			idx++;
    			// 만약 10번 타자가 되면 다시 1번 타자로 되돌림
    			if(idx >= 10) {
    				idx = 1;
    			}
    		}
    		
    		// 해당 이닝에서 얻은 점수를 총 게임 점수에 더해줌
    		sum += inningScore;
    		
    	}
    	
    	// 이번 게임에서 얻은 점수를 반환함
    	return sum;
    }
	private static int getScore() {
		// order에 따라 정해진 이닝까지 scoring
		int j = 1;
		
		int sum = 0;
		
		for (int i = 1; i <= N; i++) {
			int[] roo = new int[4]; // 이닝 끝나면 루 초기화 해야지 등신아...
			int out = 0;
			while (out < 3) {
			
				int num = order[j];
				
				int hit = score[i][num];
				
				if (hit == 1) {
					// 안타
					for (int idx = 3; idx >= 1; idx--) {
						if (roo[idx] == 1) { // 사람이 있으면
							if (isOut(idx + 1)) {
								sum += 1;
								roo[idx] = 0;
							}
							else {
								roo[idx + 1] = 1;
								roo[idx] = 0;	
							}
						}
					}
					roo[1] = 1;
				}
				else if (hit == 2) {
					// 2루타
					for (int idx = 3; idx >= 1; idx--) {
						if (roo[idx] == 1) {
							if (isOut(idx + 2)) {
								sum += 1;
								roo[idx] = 0;
								continue;
							}
							roo[idx + 2] = 1;
							roo[idx] = 0;
						}
					}
					roo[2] = 1;
				}
				else if (hit == 3) {
					// 3루타
					for (int idx = 3; idx >= 1; idx--) {
						if (roo[idx] == 1) {
							if (isOut(idx + 3)) {
								sum += 1;
								roo[idx] = 0;
								continue;
							}
							roo[idx + 3] = 1;
							roo[idx] = 0;
						}
					}
					roo[3] = 1;
				}
				else if (hit == 4) {
					// 홈런
					for (int idx = 3; idx >= 1; idx--) {
						if (roo[idx] == 1) {
							sum += 1;
							roo[idx] = 0;
							continue;
						}
					}
					sum += 1;
				}
				else {
					out += 1;
				}
				
				j = j % 9 + 1; // 1 ~ 9
			}
		}
		
		
		return sum;
	}
	private static boolean isOut(int idx) {
		return 1 > idx || idx > 3;
	}
	static int max = 0;
}