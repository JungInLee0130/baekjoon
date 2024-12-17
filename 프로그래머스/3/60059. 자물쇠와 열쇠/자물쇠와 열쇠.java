import java.awt.*;
import java.io.*;
import java.util.*;

public class Solution {
    static int N, M;
    static int[][] Key;
    static int[][] Lock;
    public boolean solution(int[][] key, int[][] lock) {
        N = key.length;
        M = lock.length;

        Key = key;
        Lock = lock;

        countLock = getCountLock();

        return solve();
    }

    static int countLock;

    static int getCountLock(){
        int countLock = 0;

        for (int i = 0; i < M; i++){
            for (int j = 0; j < M; j++){
                if (Lock[i][j] == 0) countLock++;
            }
        }
        return countLock;
    }

    static boolean solve(){
        int point = Key.length - 1;
        for (int x = 0; x < point + Lock.length; x++) {
            for (int y = 0; y < point + Lock.length; y++) {
                for (int r = 0; r < 4; r++) {
                    int[][] newLock = new int[Lock.length + Key.length * 2][Lock.length + Key.length * 2];
                    for (int i = 0; i < Lock.length; i++) {
                        for (int j = 0; j < Lock.length; j++) {
                            newLock[i + point][j + point] = Lock[i][j];
                        }
                    }
                    match(newLock, Key, r, x, y);
                    if (check(newLock, point, Lock.length)) return true;
                }
            }
        }
        return false;
    }

    static void match(int[][] newLock, int[][] Key, int rot, int x, int y) {
        int len = Key.length;

        for (int i = 0; i < len; i++) {
            for (int j = 0; j < len; j++) {
                if (rot == 0) {
                    newLock[x + i][y + j] += Key[i][j];
                }
                else if (rot == 1){
                    newLock[x + i][y + j] += Key[(len - 1) - j][i];
                }
                else if (rot == 2){
                    newLock[x + i][y + j] += Key[(len - 1) - i][(len - 1) - j];
                }
                else{
                    newLock[x + i][y + j] += Key[j][(len - 1) - i];
                }
            }
        }
    }

    static boolean check(int[][] newLock, int point, int len) {
        for (int i = 0; i < len; i++) {
            for (int j = 0; j < len; j++) {
                if (newLock[point + i][point + j] != 1) return false;
            }
        }
        return true;
    }

    static int getCountKey(){
        int countKey = 0;

        for (int i = 0; i < N; i++){
            for (int j = 0; j < N; j++){
                if (Key[i][j] == 1) countKey++;
            }
        }

        return countKey;
    }
    static boolean invalidCountKey(int countKey){
        return countKey == 0;
    }

    static boolean notSameCountLockAndCountKey(int countLock, int countKey){
        return countLock > countKey || countLock < countKey;
    }


    static boolean hasSameKeyAndLock(int countKey){

        for (int i = 0; i < M - N; i++){
            for (int j = 0; j < M - N; j++){
                int sameCount = 0;

                for (int k = 0; k < N; k++){
                    for (int s = 0; s < N; s++){
                        if (Key[k][s] == 1 && Lock[k + i][s + j] == 0){
                            sameCount++;
                            if (sameCount == countKey){
                                isFinish = true;
                                return true;
                            }
                        }
                    }
                }

            }
        }
        return false;
    }

    static boolean isFinish = false;

    static boolean dfs(){
        int countKey = getCountKey();

        if (invalidCountKey(countKey)) return false;

        for (int d = 0; d < 4; d++){
            if (isFinish) return true;

            int[][] prevKey = save();

            if (d == 0){
                moveUp();
            }
            else if (d == 1){
                moveBottom();
            }
            else if (d == 2){
                moveLeft();
            }
            else if (d == 3){
                moveRight();
            }

            countKey = getCountKey();

            if (notSameCountLockAndCountKey(countLock, countKey)) {
                recover(prevKey);
                continue;
            }

            if (hasSameKeyAndLock(countKey)) return true; // 0도

            Key = rotateClockwise(); // 90도
            if (hasSameKeyAndLock(countKey)) return true;

            dfs();

            Key = rotateClockwise(); // 180도
            if (hasSameKeyAndLock(countKey)) return true;

            dfs();

            Key = rotateClockwise(); // 270도
            if (hasSameKeyAndLock(countKey)) return true;

            dfs();

            Key = rotateClockwise(); // 360도
            // 돌렸는데 다 안됨
            recover(prevKey);
        }
        return false;
    }

    static int[][] save(){
        int[][] copyKey = new int[N][N];

        for (int i = 0; i < N; i++){
            copyKey[i] = Arrays.copyOf(Key[i], N);
        }

        return copyKey;
    }

    static void recover(int[][] prevKey){
        for (int i = 0; i < N; i++){
            Key[i] = Arrays.copyOf(prevKey[i], N);
        }
    }

    static void moveLeft(){
        for (int j = 0; j <= N - 1; j++){
            for (int i = 0; i <= N - 1; i++){
                if (j == N - 1){
                    Key[i][j] = 0;
                    continue;
                }
                Key[i][j] = Key[i][j + 1];
            }
        }
    }

    static void moveRight(){
        for (int j = N - 1; j >= 0; j--){
            for (int i = 0; i <= N - 1; i++){
                if (j == 0){
                    Key[i][j] = 0;
                    continue;
                }
                Key[i][j] = Key[i][j - 1];
            }
        }

    }

    static void moveUp(){
        for (int i = 0; i <= N - 1; i++){
            for (int j = 0; j <= N - 1; j++){
                if (i == N - 1){
                    Key[i][j] = 0;
                    continue;
                }
                Key[i][j] = Key[i + 1][j];
            }
        }

    }

    static void moveBottom(){
        for (int i = N - 1; i >= 0; i--){
            for (int j = 0; j <= N - 1; j++){
                if (i == 0){
                    Key[i][j] = 0;
                    continue;
                }
                Key[i][j] = Key[i - 1][j];
            }
        }

    }

    static int[][] rotateClockwise(){
        int[][] copyKey = new int[N][N];

        for (int j = N - 1; j >= 0; j--){
            for (int i = 0; i <= N - 1; i++){
                copyKey[i][j] = Key[(N - 1) - j][i];
            }
        }

        return copyKey;
    }
}
