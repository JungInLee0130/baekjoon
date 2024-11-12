import java.util.*;
class Solution {
    static int N, M;
    static int[][] gameBoard;
    static int[][] Table;
    static ArrayList<ArrayList<int[]>> gameBoardCord;
    static ArrayList<ArrayList<int[]>> TableCord;
    public int solution(int[][] game_board, int[][] table) {
        
        gameBoard = game_board;
        Table = table;
        
        N = game_board.length;
        
        // M : 블록의 개수
        // 1 과 0 바꾸기(game_board)
        for (int i = 0; i < N; i++){
            for (int j = 0; j < N; j++){
                if (gameBoard[i][j] == 1){
                    gameBoard[i][j] = 0;
                }
                else{
                    gameBoard[i][j] = 1;
                }
            }
        }
        
        
        // 칠하기
        gameBoardCord = new ArrayList<>();
        TableCord = new ArrayList<>();
        // 좌표들 저장 : 좌표 정규화는 안에서
        setList(gameBoard, gameBoardCord);
        setList(table, TableCord);
        
        // 이제 블록들을 선택하면서 맞는지 정해줘야한다.
        M = TableCord.size();
        
        return match(gameBoardCord, TableCord);
    }
    
    static int match(ArrayList<ArrayList<int[]>> gameBoardCord,
                     ArrayList<ArrayList<int[]>> TableCord){
        int result = 0;
        boolean[] isUsed = new boolean[TableCord.size()];
        
        int N = gameBoardCord.size();
        int M = TableCord.size();
        // 그냥 이중반복으로 확인
        for (int i = 0; i < N; i++){
        
            for (int j = 0; j < M; j++){
                if (isUsed[j]) continue;
                
                if (rotateAndCompare(gameBoardCord.get(i), TableCord.get(j))){
                    isUsed[j] = true;
                    result += TableCord.get(j).size();
                    break;
                }
            }
        }
        
        return result;
    }
    
    static ArrayList<int[]> rotate(ArrayList<int[]> block){
        ArrayList<int[]> result = new ArrayList<>();
        
        int minX = Integer.MAX_VALUE;
        int minY = Integer.MAX_VALUE;
        
        for (int[] b : block){
            int x = b[1];
            int y = -b[0];
            
            result.add(new int[]{x,y});
            
            minX = Math.min(x, minX);
            minY = Math.min(y, minY);
        }
        
        for (int[] r:result){
            r[0] -= minX;
            r[1] -= minY;
        }
        
        // 좌표 90도 회전 : (x,y) -> (y, -x)
        // 정규화도 같이해줌.
        return result;
    }
    
    static boolean rotateAndCompare(ArrayList<int[]> space, ArrayList<int[]> block){
        if (space.size() != block.size()) return false;
        
        ArrayList<int[]> rotated = block;
        
        for (int i = 0; i < 4; i++){
            if (compare(space, rotated)){
                return true;
            }
            
            if (i < 3){
                rotated = rotate(rotated);
            }
        }
        
        return false;
    }
    
    static boolean compare(ArrayList<int[]> space, ArrayList<int[]> block){

        // 정렬 : 같은공간하고 비교해야함.
        Collections.sort(space, (a,b) -> a[0] == b[0] ? a[1] - b[1] : a[0] - b[0]);
        Collections.sort(block, (a,b) -> a[0] == b[0] ? a[1] - b[1] : a[0] - b[0]);
        
        for (int i = 0; i < space.size(); i++){
            int[] curSpace = space.get(i);
            int[] curBlock = block.get(i);
            if (curSpace[0] != curBlock[0] 
                || curSpace[1] != curBlock[1]){
                return false;
            }
        }
        
        return true;
    }
    
    static void cordinate(ArrayList<int[]> list){
        // 최소치를 구해서 빼준다
        int minX = Integer.MAX_VALUE;
        int minY = Integer.MAX_VALUE;
        
        for (int[] e : list){
            minX = Math.min(e[0], minX);
            minY = Math.min(e[1], minY);
        }
        
        for (int[] e: list){
            e[0] -= minX;
            e[1] -= minY;
        }
    }
    
    
    
    static void setList(int[][] arr, ArrayList<ArrayList<int[]>> list){
        int count = 2;
        
        for (int i = 0; i < N; i++){
            for (int j = 0; j < N; j++){
                // 1은 그대로라 제외하고
                if (arr[i][j] == 1){
                    // floodfill다
                    list.add(floodfill(i,j,count,arr));
                    count++;
                }
            }
        }
        
        //print(arr);
        //System.out.println();
    }
    
    
    static boolean[][] visited;
    static int[] dx = {-1,1,0,0};
    static int[] dy = {0,0,-1,1};
    
    static ArrayList<int[]> floodfill(int x, int y, int count, int[][] arr){
        ArrayList<int[]> result = new ArrayList<>();
        
        Queue<int[]> que = new LinkedList<>();
        visited =new boolean[N][N];
        
        que.add(new int[]{x,y});
        visited[x][y] = true;
        arr[x][y] = count;
        
        result.add(new int[]{x,y});
        
        while (!que.isEmpty()){
            int[] poll= que.poll();
            
            for (int d = 0; d < 4; d++){
                int nx = poll[0] + dx[d];
                int ny = poll[1] + dy[d];
                
                if (!isRange(nx,ny)) continue;
                if (visited[nx][ny]) continue;
                
                if (arr[nx][ny] == 1){
                    arr[nx][ny] = count; // 색칠
                    que.add(new int[]{nx,ny});
                    result.add(new int[]{nx,ny});
                }
            }
        }
        
        cordinate(result);
        
        //print(result);
        //System.out.println();
        
        return result;
    }
    
    static boolean isRange(int nx, int ny){
        return 0 <= nx && nx <= N - 1 && 0 <= ny && ny <= N - 1;
    }
    
    static void print(ArrayList<int[]> list){
        
        for(int[] e:list){
            System.out.print(e[0] + " " + e[1] + "\n");
        }
    }
    
    static void print(int[][] arr){
        for (int i = 0; i < N; i++){
            for (int j = 0; j < N; j++){
                System.out.print(arr[i][j] + " ");
            } 
            System.out.println();
        }
        
    }
}