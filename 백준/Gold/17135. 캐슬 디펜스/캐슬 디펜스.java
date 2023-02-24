import java.io.*;
import java.util.ArrayList;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

public class Main {
    static int N, M, D;
    static int[][] board;
    static class Enemy implements Comparable<Enemy>{
        int x;
        int y;
        int distance;

        public Enemy(int x, int y, int distance) {
            this.x = x;
            this.y = y;
            this.distance = distance;
        }


        @Override
        public int compareTo(Enemy o) {
            if (this.distance == o.distance){
                return this.y - o.y; // 거리가 같을때는 왼쪽 순서대로.
            }
            return this.distance - o.distance;
        }
    }
    

    static ArrayList<Enemy> enemyList = new ArrayList<>();
    static int[][] temp;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        D = Integer.parseInt(st.nextToken());

        // 보드 상태 입력
        board = new int[N][M];
        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < M; j++) {
                board[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        // 임시 배열
        temp = new int[N][M];
        scan(temp, board);
        // N번 행에 성이 있음 -> 궁수 배치 가능.
        //visited = new boolean[M];
        arr = new int[3];
        combi(0, 0);

        bw.write(String.valueOf(total));


        bw.flush();
        br.close();
        bw.close();
    }

    //private static boolean[] visited;
    private static int[] arr;
    private static int total = 0;
    private static void combi(int cnt, int start) {
        if (cnt == 3) {
            total = Math.max(move(), total);
            // 원래대로 되돌리기
            scan(board, temp);

            return;
        }

        for (int i = start; i < M; i++) {
            //visited[i] = true;
            arr[cnt] = i;
            combi(cnt + 1, i + 1);
            arr[cnt] = 0;
            //visited[i] = false;
        }
    }

    private static void scan(int[][] board, int[][] temp) {
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < M; j++) {
                board[i][j] = temp[i][j];
            }
        }
    }


    private static int move() {
        int count = 0;
        while (true) {
            save(); // 적 좌표 저장
            if (enemyList.isEmpty()) return count;
            count += bow(); // 공격 : 적 삭제
            //System.out.println("최종값" + " " + total);
            save(); // 적 저장
            nextTurn(); // 적 이동
        }
    }

    private static void save(){
        enemyList = new ArrayList<>();

        for (int i = 0; i < N; i++) {
            for (int j = 0; j < M; j++) {
                if (board[i][j] == 1) {
                    enemyList.add(new Enemy(i, j, 0));
                }
            }
        }
    }
    // 적 옮기기
    private static void nextTurn() {
        // 중복삭제되니까 뒤에서 부터 하는게 나은듯
        for (int i = enemyList.size() - 1; i >= 0; i--) {
            Enemy enemy = enemyList.get(i);
            board[enemy.x][enemy.y] = 0; // 옮김
            enemy.x += 1; // 아래로 1이동
            if (enemy.x == N){
                continue;
            }
            // 성벽에 도달하지않았으면 좌표 만들어줌
            board[enemy.x][enemy.y] = 1;
        }

        /*for (int i = 0; i < enemyList.size(); i++) { // 모든 적 한칸씩 내려옴
            Enemy enemy = enemyList.get(i);
            // 여기서 중복삭제됨.
            *//*board[enemy.x][enemy.y] = 0; *//*
            //System.out.println("현재점 : " + enemy.x + ", " + enemy.y);
            enemy.x += 1;
            if (enemy.x != N) { // 벽에 닿으면 없어짐.
                continue;
            }
            //System.out.println("다음점 : " + enemy.x + ", " + enemy.y);
            //System.out.println();
            *//*board[enemy.x][enemy.y] = 1; // 옮김*//*
            enemyList.remove(enemyList.get(i));
        }*/

        /*for (int i = 0; i < N; i++) {
            for (int j = 0; j < M; j++) {
                System.out.print(board[i][j] + " ");
            }
            System.out.println();
        }
        System.out.println();*/
    }


    private static int bow() {
        ArrayList<PriorityQueue<Enemy>> bower = new ArrayList<>();
        /*bower = new ArrayList<>();*/
        for (int i = 0; i < 3; i++) {
            bower.add(new PriorityQueue<>());
        }
        // 궁수의 좌표 : (N, arr[i])
        // 적의 좌표 EnemyList
        int count = 0;
        // enemylist -> priorityQueue
        for (int t = 0; t < 3; t++) {
            for (int i = 0; i < enemyList.size(); i++) {
                Enemy enemy = enemyList.get(i);
                int distance = Math.abs(enemy.x - N) + Math.abs(enemy.y - arr[t]);
                bower.get(t).add(new Enemy(enemy.x, enemy.y, distance));
            }
        }

        //int preSize = enemyList.size();
        boolean[][] visitedBoard = new boolean[N][M];
        for (int t = 0; t < 3; t++) {
            if (bower.get(t).peek().distance <= D){ // distance 안에 있으면 공격
                Enemy poll = bower.get(t).poll();
                //enemyList.remove(poll);
                //print();
                board[poll.x][poll.y] = 0;
                //System.out.println("공격 적 좌표 " + t + " : " + poll.x + "," + poll.y);
                // 같은거 공격하면 하나로 해야됨.
                visitedBoard[poll.x][poll.y] = true;
                //enemyList.remove(poll);
            }
        }
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < M; j++) {
                if (visitedBoard[i][j]) {
                    count++;
                }
            }
        }
        // 다음 턴으로 넘어감.
        return count;
    }

    /*private static void print() {
        *//*for (Enemy e: enemyList) {
            System.out.println(e);
        }*//*
    }*/
}