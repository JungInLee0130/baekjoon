import java.io.*;
import java.util.*;

/*
 * - 제약조건 분석 및 알고리즘 라벨링
 *  - 3 <= N, M <= 10 : 최대 10x10 격자
 *  - 10번 초과하면 -1 출력
 *  - 10! -> 너무많음
 *  - BFS
 * 1. 초기화
 * 가로, 세로 변수 - R, C
 * 방향 : 4방 탐색 - dr,dc
 * 2. 자료구조 관리
 * 2차원 격자 - map 
 * 빨간구슬, 파란구슬 클래스 : 위치 - Red(r,c), Blue(r,c)
 * 탈출 위치 - Point(r,c)
 * 3. 메인 로직
 * 1. R, C 받기
 * 2. 격자 받기
 * '.' -> 이동 가능
 * '#' -> 이동 불가
 * 다른 구슬 만나면 -> 정지 (좌표 : 방향 - 1)
 * 'O' -> 탈출
 * isRange(nr, nc) : 새로운 좌표 범위안에 드는지.
 * 목표 : 빨간구슬만 안에 넣기.
 * 4. 잔류 처리 or 엣지 케이스
 * - 빨간 구슬, 파란구슬이 동시에 빠지면 안됨.
 * */
class Main {
	private static int R, C;
	private static int[] dr = {-1,1,0,0};	// 위, 아래, 왼쪽, 오른쪽
	private static int[] dc = {0,0,-1,1};
	private static char[][] map;
	private static Point redMarble, blueMarble, exit;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        
        // 1. 변수 초기화
        StringTokenizer st = new StringTokenizer(br.readLine());
        R = Integer.parseInt(st.nextToken());
        C = Integer.parseInt(st.nextToken());
        map = new char[R][C];
        redMarble = new Point(0,0);
        blueMarble = new Point(0,0);
        exit = new Point(0,0);
        
        // 2. 격자 초기화
        // 2-1. 빨간구슬, 파란구슬, 출구 위치 저장
        for (int r = 0; r < R; r++) {
        	StringBuilder sb = new StringBuilder(br.readLine());
        	for (int c = 0; c < C; c++) {
        		map[r][c] = sb.charAt(c);
        		switch (map[r][c]) {
	        		case 'R' : 
	        			redMarble = new Point(r,c);
	        			break;
	        		case 'B' :
	        			blueMarble = new Point(r,c);
	        			break;
	        		case 'O' : 
	        			exit = new Point(r,c);
	        			break;
        		}
        	}
        }
        
        // 목표 : 빨간구슬만 안에 넣기.
        // 구슬의 이동
        // 1. 벽에 도달 할때까지 이동.
        // 2. 다른 구슬을 만나면 좌표 : (방향 - 1)
        
        // 1. bfs
        bfs();
        
    	System.out.println(minCount);	
        
        br.close();
    }
    private static int minCount = -1;
    private static void bfs() {
    	Queue<int[]> que = new LinkedList<>();
    	que.add(new int[] {redMarble.r, redMarble.c, blueMarble.r, blueMarble.c, 1});
    	// 구슬 위치 저장
    	boolean[][][][] visited = new boolean[R][C][R][C];
    	visited[redMarble.r][redMarble.c][blueMarble.r][blueMarble.c] = true;
    	
    	while (!que.isEmpty()) {
    		int[] location = que.poll();
    		
			int rr = location[0];
			int rc = location[1];
			int br = location[2];
			int bc = location[3];
			int count = location[4];
			
			if (count > 10) {
    			break;
    		}
    		
    		for (int d = 0; d < 4; d++) {
    			// 1. Red 좌표 이동
    			// 2. Blue 좌표 이동
        		
        		// 벽에 닿을때까지 이동
        		int[] newR = moveMarble(rr, rc, d);
        		int[] newB = moveMarble(br, bc, d);
        		
        		int newRr = newR[0];
        		int newRc = newR[1];
        		int newBr = newB[0];
        		int newBc = newB[1];
        		
        		
        		// 3. 좌표 정리
        		// 3-2. 모두 exit에 들어가면 : continue
        		if (isEqual(newRr, newRc, exit.r, exit.c) 
        				&& isEqual(newBr, newBc, exit.r, exit.c)) {
        			continue;
        		}
        		// 3-3. 하나만 exit
        		// 3-3-1. blue: continue;
        		else if (isEqual(newBr, newBc, exit.r, exit.c)) {
        			continue;
        		}
        		// 3-3-2. red : count 저장
        		else if (isEqual(newRr, newRc, exit.r, exit.c)) {
        			minCount = count;
        			return;
        		}
        		// 3-1. red == blue : 겹치는 경우
        		else if (isEqual(newRr, newRc, newBr, newBc)) {
        			// 1. 위, 아래 : r 좌표만 변경. 2. 왼쪽, 오른쪽 : c좌표만 변경
        			int redDist = newR[2];
        			int blueDist = newB[2];
        			
        			if (redDist > blueDist) {
        				// 이동거리가 크면 뒤에 위치.
        				newRr = newRr - dr[d];
        				newRc = newRc - dc[d];
        			} else {
        				newBr = newBr - dr[d];
        				newBc = newBc - dc[d];
        			}
        			// switch - case => 변경 : human error 방지.
        		}
        		
        		// 이미 방문한 snapshot이면
        		if (visited[newRr][newRc][newBr][newBc]) continue;
        		
        			        		
        		// 새로운 좌표로 계속
        		visited[newRr][newRc][newBr][newBc] = true;
        		que.add(new int[]{newRr, newRc, newBr, newBc, count + 1});
    		}
    	}
    }
    
    private static int[] moveMarble(int r, int c, int d) {
    	int cnt = 0;
    	// '#'이거나 'O'이면 탈출 => '#'도 'O'도 아니면 탈출
    	while (map[r + dr[d]][c + dc[d]] != '#' && map[r][c] != 'O') {
    		r = r + dr[d];
			c = c + dc[d];
			cnt++; // 이동거리
		}
    	return new int[]{r,c,cnt};
    }
    
    private static boolean isEqual(int rr, int rc, int br, int bc) {
    	if (rr == br && rc == bc) {
    		return true;
    	}
    	return false;
    }
    // 빨간구슬 : 위치, exit 도착 여부
    // 파란구슬 : 위치, exit 도착 여부
	// 탈출 구멍
	static class Point {
		int r;
		int c;
		public Point(int r, int c) {
			super();
			this.r = r;
			this.c = c;
		}
	}
}