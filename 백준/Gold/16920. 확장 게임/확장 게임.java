import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.List;
import java.util.Queue;

public class Main {
	static int N, M, P;
	static int[] maximumMove, area;
	static char[][] map;
	static List<Point>[] start;
	static Queue<Point> q;

	static final char BLANK = '.';
	static final char WALL = '#';

	static int[] dx = {-1, 1, 0, 0};
	static int[] dy = {0, 0, -1, 1};

	static class Point {
		int x;
		int y;

		Point(int a, int b) {
			x = a;
			y = b;
		}
	}

	public static void main(String[] args) throws Exception {
		BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
		String[] splitedLine = in.readLine().split(" ");

		N = stoi(splitedLine[0]);
		M = stoi(splitedLine[1]);
		P = stoi(splitedLine[2]);
		maximumMove = new int[P + 1];
		area = new int[P + 1];
		map = new char[N][M];
		start = new List[P + 1];

		for (int i = 0; i < P + 1; ++i)
			start[i] = new ArrayList<>();

		splitedLine = in.readLine().split(" ");
		for (int i = 1; i <= P; ++i)
			maximumMove[i] = stoi(splitedLine[i - 1]);

		for (int i = 0; i < N; ++i) {
			String s = in.readLine();
			for (int j = 0; j < M; ++j) {
				map[i][j] = s.charAt(j);
				if ('1' <= map[i][j] && map[i][j] <= '9')
					start[map[i][j] - '0'].add(new Point(i, j));
			}
		}

		q = new ArrayDeque<>();
		// 1부터 모든 시작점을 넣어준다.
		for (int i = 1; i <= P; ++i)
			for (Point p : start[i])
				q.add(p);

		simulation();

		StringBuilder sb = new StringBuilder();

		// 각 플레이어의 성의 갯수를 찾는다.
		for (int i = 0; i < N; ++i) {
			for (int j = 0; j < M; ++j) {
				if ('1' <= map[i][j] && map[i][j] <= '9')
					area[map[i][j] - '0']++;
			}
		}

		for (int i = 1; i <= P; ++i)
			sb.append(area[i]).append(" ");

		System.out.println(sb);
	}

	private static void simulation() {
		while (!q.isEmpty()) {
			Point p = q.poll();
			int playerNum = map[p.x][p.y] - '0'; // 현재 진행중인 플레이어
			int maxMove = maximumMove[playerNum]; // 현재 플레이어의 최대 이동가능 범위

			Queue<Point> nextQ = new ArrayDeque<>();

			int[][] visit = new int[N][M];
			// 같은 플레이어의 성을 모두 넣는다.
			while (!q.isEmpty() && map[q.peek().x][q.peek().y] == map[p.x][p.y]) {
				visit[q.peek().x][q.peek().y] = 1;
				nextQ.add(q.poll());
			}

			nextQ.add(p);
			visit[p.x][p.y] = 1;
			while (!nextQ.isEmpty()) { // 특정 플레이어의 성을 최대 범위까지 확장한다.
				Point cur = nextQ.poll();
				for (int d = 0; d < 4; ++d) {
					int nextX = cur.x + dx[d];
					int nextY = cur.y + dy[d];
					if (isInRange(nextX, nextY) && visit[nextX][nextY] == 0 && map[nextX][nextY] == BLANK) {
						visit[nextX][nextY] = visit[cur.x][cur.y] + 1;
						map[nextX][nextY] = map[cur.x][cur.y];
						if (visit[nextX][nextY] == maxMove + 1) // 최대 범위까지 왔으면, 다른 플레이어가 진행 후 진행할 수 있다.
							q.add(new Point(nextX, nextY));
						else
							nextQ.add(new Point(nextX, nextY)); // 이동 가능횟수가 남아있다면, 현재 턴에 계속 확장한다.
					}
				}
			}
		}
	}

	private static boolean isInRange(int nextX, int nextY) {
		if (0 <= nextX && nextX < N && 0 <= nextY && nextY < M)
			return true;
		return false;
	}

	private static int stoi(String s) {
		return Integer.parseInt(s);
	}
}