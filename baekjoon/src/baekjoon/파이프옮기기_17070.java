package baekjoon;

import java.util.Scanner;

public class 파이프옮기기_17070 {
	static int cnt, n;
	static int[][] arr;
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		n = sc.nextInt();
		arr = new int[n][n];
		for(int i=0; i<n; i++) {
			for(int j=0; j<n; j++) {
				arr[i][j] = sc.nextInt();
			}
		}
		cnt = 0;
		dfs(0, 1,1);
		System.out.println(cnt);
	}
	static void dfs(int i, int j, int status){
		if(i==n-1 && j==n-1) {
			cnt++;
			return;
		}
		if(status == 1) {  // 가로 상태
			if(j+1<n && arr[i][j+1] != 1) {
				dfs(i, j+1, 1); //오른쪽 이동
			}
			if((j+1<n && i+1<n) && (arr[i][j+1] != 1 && arr[i+1][j] != 1 && arr[i+1][j+1] != 1)) {
				dfs(i+1, j+1, 3); //대각선 이동
			}
		}
		else if(status == 2) {  //세로 상태
			if(i+1<n && arr[i+1][j] != 1) {
				dfs(i+1, j, 2); //아래 이동
			}
			if((j+1<n && i+1<n) && (arr[i][j+1] != 1 && arr[i+1][j] != 1 && arr[i+1][j+1] != 1)) {
				dfs(i+1, j+1, 3); //대각선 이동
			}
		}
		else if(status == 3) {  //대각선 상태
			if(j+1<n && arr[i][j+1] != 1) {
				dfs(i, j+1, 1); //오른쪽 이동
			}
			if(i+1<n && arr[i+1][j] != 1) {
				dfs(i+1, j, 2); //아래 이동
			}
			if((j+1<n && i+1<n) && (arr[i][j+1] != 1 && arr[i+1][j] != 1 && arr[i+1][j+1] != 1)) {
				dfs(i+1, j+1, 3); //대각선 이동
			}
		}
	}
}
