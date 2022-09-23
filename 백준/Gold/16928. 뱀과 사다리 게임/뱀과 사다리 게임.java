import java.io.*;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main {
    //private static boolean[][] ladder = new boolean[10][10];
    //private static boolean[][] snake = new boolean[10][10];
    private static boolean[] visited = new boolean[101];
    private static HashMap<Integer, Integer> ladder = new HashMap<>();
    private static HashMap<Integer, Integer> snake = new HashMap<>();

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        StringTokenizer st = new StringTokenizer(br.readLine());
        int N = Integer.parseInt(st.nextToken());
        int M = Integer.parseInt(st.nextToken());


        int[] board = new int[101];
        //int k = 1;
        for (int i = 1; i <= 100 ; i++) {
            board[i] = i;
        }
        /*for (int i = 0; i < 10; i++) {
            for (int j = 0; j < 10; j++) {
                board[i][j] = k;
                k++;
            }
        }*/
        /*for (int i = 0; i < 10; i++) {
            for (int j = 0; j < 10; j++) {
                ladder[i][j] = false;
                snake[i][j] = false;
            }
        }*/

        // 사다리의 정보
        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            int x = Integer.parseInt(st.nextToken());
            int y = Integer.parseInt(st.nextToken());
            ladder.put(x, y);
        }

        // 뱀의 정보
        for (int i = 0; i < M; i++) {
            st = new StringTokenizer(br.readLine());
            int u = Integer.parseInt(st.nextToken());
            int v = Integer.parseInt(st.nextToken());
            snake.put(u, v);
        }

        BFS(board);

        bw.write(String.valueOf(minDice) + "\n");

        bw.flush();
        br.close();
        bw.close();
    }

    private static int[] dice = {1, 2, 3, 4, 5, 6};
    private static Queue<Integer> queue = new LinkedList<>();
    private static int minDice = 0;
    private static void BFS(int[] board) {
        visited[1] = true;
        queue.add(board[1]);
        int queueSize = 1;
        int nextChild = 0;

        while (!queue.isEmpty()) {
            Integer boardNum = queue.poll();
            queueSize--;


            for (int i = 0; i < 6; i++) {
                int nextBoardNum = boardNum + dice[i];

                if (nextBoardNum >= 100) {
                    minDice++;
                    return;
                }

                // ladder
                for (Integer key : ladder.keySet()) {
                    if (key == nextBoardNum) { // key가 nextBoardNum이랑 같으면 -> 올라감
                        nextBoardNum = ladder.get(key);
                    }
                }

                // snake
                for (Integer key: snake.keySet()) {
                    if (key == nextBoardNum) { // key가 nextBoardNum이랑 같으면 -> 내려감
                        nextBoardNum = snake.get(key);
                    }
                }

                if (!visited[nextBoardNum]
                && 1 < board[nextBoardNum] && board[nextBoardNum] < 100) {
                    visited[nextBoardNum] = true;
                    queue.add(nextBoardNum);
                    nextChild++;
                }
            }

            if (queueSize == 0) {
                queueSize = nextChild;
                nextChild = 0;
                minDice++;
            }
        }
    }
}
