import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;

public class Main {

	static class CCTV{
		int r;
		int c;
		int type;
		public CCTV(int r, int c, int type) {
			this.r = r;
			this.c = c;
			this.type = type;
		}
	}
	
	static int N,M;
	static int[][] map;
	static int min;
	static int [] dr= {-1,0,1, 0};
	static int [] dc= { 0,1,0,-1};
	
	static List<CCTV> cctvs;
	static boolean [] visited;
	static int cctvsize;
	
	public static void main(String[] args) {
		
		Scanner scann=new Scanner(System.in);
		N=scann.nextInt();
		M=scann.nextInt();
		cctvs=new ArrayList<>(); //cctv만 저장해서 추적 해보자
		map=new int[N][M];
		for (int i = 0; i < N; i++) {
			for (int j = 0; j < M; j++) {
				map[i][j]=scann.nextInt();
				if(map[i][j]!=0 && map[i][j]!=6) {
					//1~5 CCTV 저장
					cctvs.add(new CCTV(i, j, map[i][j]));
				}
			}
		}// 읽기
		//로직
		min=Integer.MAX_VALUE;
		cctvsize=cctvs.size();  // CCTV 개수
		dfs(0,map);             // cctvsize 깊이 만큼 들어가보자.
		System.out.println(min==Integer.MAX_VALUE?0:min);
	}
	// cnt번째 CCTV
	static void dfs(int cnt, int[][] nmap) {
		
		if(cnt==cctvsize) {
			int tot=0;
			for (int i = 0; i < N; i++) {
				for (int j = 0; j < M; j++) {
					if(nmap[i][j]==0) {
						tot++;
					}
				}
			}
			min=Math.min(min, tot);
			return ;
		}
		CCTV cur=cctvs.get(cnt);  
		int cr=cur.r;
		int cc=cur.c;
		int cctvType=cur.type;
		int [][] vMap=new int[N][M];
		
		if(cctvType==1) {
			for (int d = 0; d <4; d++) {
				// 현재의 배열 복사 
				for (int i = 0; i < N; i++) {
					vMap[i]=Arrays.copyOf(nmap[i], M);
				}
				gosharp(cr, cc, d, vMap);
				dfs(cnt+1, vMap);
			}
		}else if(cctvType==2) {
			for (int d = 0; d <2; d++) {
				// 현재의 배열 복사
				for (int i = 0; i < N; i++) {
					vMap[i]=Arrays.copyOf(nmap[i], M);
				}
				gosharp(cr, cc, d, vMap);      
				gosharp(cr, cc, (d+2)%4, vMap);
				dfs(cnt+1, vMap);
			}
		}else if(cctvType==3) {
			for (int d = 0; d <4; d++) {
				// 현재의 배열 복사
				for (int i = 0; i < N; i++) {
					vMap[i]=Arrays.copyOf(nmap[i], M);
				}
				gosharp(cr, cc, d, vMap);
				gosharp(cr, cc, (d+1)%4, vMap);
				dfs(cnt+1, vMap);
			}
		}else if(cctvType==4) {
			for (int d = 0; d <4; d++) {
				// 현재의 배열 복사
				for (int i = 0; i < N; i++) {
					vMap[i]=Arrays.copyOf(nmap[i], M);
				}
				gosharp(cr, cc, d, vMap);
				gosharp(cr, cc, (d+1)%4, vMap);
				gosharp(cr, cc, (d+2)%4, vMap);
				dfs(cnt+1, vMap);
			}
		}else if(cctvType==5) {
			for (int d = 0; d <1; d++) {
				// 현재의 배열 복사
				for (int i = 0; i < N; i++) {
					vMap[i]=Arrays.copyOf(nmap[i], M);
				}
				gosharp(cr, cc, d, vMap);      // 감시 표시하고
				gosharp(cr, cc, (d+1)%4, vMap);
				gosharp(cr, cc, (d+2)%4, vMap);
				gosharp(cr, cc, (d+3)%4, vMap);
				dfs(cnt+1, vMap);              // 다음 CCTV로
			}
		}
		
	}

	private static void gosharp(int r, int c, int d, int[][] mm) {
		// 한방향으로 진행하면서 0을 #으로 
		while(true) {
			int nr=r+dr[d];
			int nc=c+dc[d];
			if(!check(nr,nc))break;
			if(mm[nr][nc]==6) break;
			if(mm[nr][nc]==0) mm[nr][nc]=9;  // 감시된 영역을 #으로 바꾼다.
			r=nr;
			c=nc;
		}
		
	}

	private static boolean check(int r, int c) {
		return r>=0 && r<N && c>=0 && c<M;
	}
}