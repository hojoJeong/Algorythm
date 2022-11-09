package baekjoon;

import java.util.*;
import java.io.*;

public class 치즈_2636 {

	static int[] dx = { 1, -1, 0, 0 };
	static int[] dy = { 0, 0, 1, -1 };
	static int n, m, cnt, hour;
	static int[][] arr;
	static boolean[][] vis;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		n = Integer.parseInt(st.nextToken());
		m = Integer.parseInt(st.nextToken());

		arr = new int[n][m];

		// 배열 입력
		for (int i = 0; i < n; i++) {
			st = new StringTokenizer(br.readLine());
			for (int j = 0; j < m; j++) {
				arr[i][j] = Integer.parseInt(st.nextToken());
			}
		}

		while (check()) {
			vis = new boolean[n][m];
			cnt = 0;
			hour++;
			findedge(0, 0); // 공기 중에 맞닿은 부분은 0로 변경
		}
		System.out.printf(hour +"\n"+ cnt);
		

	}

	
	static boolean check() {
		for (int i = 0; i < n; i++) {
			for (int j = 0; j < m; j++) {
				if (arr[i][j] == 1) {
					return true;
				}
			}
		}
		return false;
	}

	//dfs의 종료조건을 꼭 if문으로 안해줘도 됨. for문 안에서 조건문에 해당할 시 dfs를 재귀로 호출하고 아니면 그냥 빠져나가는 식으로 구현해도 종료됨
	static void findedge(int r, int c) {
		for (int i = 0; i < 4; i++) {
			int rr = r + dy[i];
			int cc = c + dx[i];
			if (0 <= rr && rr < n && 0 <= cc && cc < m) {
				if (vis[rr][cc] == false) {
					vis[rr][cc] = true;
					if (arr[rr][cc] == 1) {
						cnt++;
						arr[rr][cc] = 0; // 공기 중에 닿은 칸은 2로 변경해주고 더 이상 안으로 들어가지 않도록 dfs를 종료시킴
					} else {
						findedge(rr, cc);
					}
				}
			}
		}
	}
}
