package baekjoon;

import java.util.*;
import java.io.*;

public class 단지번호붙이기_2667 {
	static int[] dx = { 1, -1, 0, 0 };
	static int[] dy = { 0, 0, 1, -1 };
	static int[][] arr;
	static boolean[][] vis;
	static int[] home;
	static int n, cnt;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

		n = Integer.parseInt(br.readLine());
		cnt = 0;
		vis = new boolean[n][n];
		arr = new int[n][n];
		home = new int[n*n];
		
		//배열 입력
		for (int i = 0; i < n; i++) {
			String str = br.readLine();
			for (int j = 0; j < n; j++) {
				arr[i][j] = str.charAt(j) - '0';
			}
		}

		//탐색하면서 arr[i][j]==1이면 dfs 호출, 아니면 vis[i][j] == false일 떄 다시 호출
		for(int i=0; i<n; i++) {
			for(int j=0; j<n; j++) {
				if(arr[i][j] == 1 && vis[i][j] == false) {
					dfs(i, j);
					cnt++;
				}
			}
		}
		
	
		Arrays.sort(home);
		System.out.println(cnt);
		for(int i=0; i<home.length; i++) {
			if(home[i] == 0) continue;
			System.out.println(home[i]);
		}
		
	}
	
	//dfs는 only 인접 칸이 1인지 확인하고 1이면 재귀 통해서 home[cnt]++ 해주고 vis[][] = true 만들어주는 역할
	//메소드마다 역할을 명확하고 간단하게 정해주는 것이 중요함
	static void dfs(int r, int c) {
		vis[r][c] = true;
		home[cnt]++;
		for(int i=0; i<4; i++) {
			int rr = r+dy[i];
			int cc = c+dx[i];
			if(rr>=0 && cc>=0 && rr<n && cc<n) {
				if(arr[rr][cc] == 1 && vis[rr][cc] != true) {
					arr[rr][cc] = cnt;
					dfs(rr, cc);
				}
			}
		}
	}
}
