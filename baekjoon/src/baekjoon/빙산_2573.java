package baekjoon;

import java.util.*;
import java.io.*;

public class ºù»ê_2573 {
	static int[] dx = { 1, -1, 0, 0 };
	static int[] dy = { 0, 0, 1, -1 };
	static int[][] arr;
	static boolean[][] vis;
	static int n, m, cnt, years;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());

		n = Integer.parseInt(st.nextToken());
		m = Integer.parseInt(st.nextToken());

		arr = new int[n][m];
		years = 0;

		for (int i = 0; i < n; i++) {
			st = new StringTokenizer(br.readLine());
			for (int j = 0; j < m; j++) {
				arr[i][j] = Integer.parseInt(st.nextToken());
			}
		}

		while (true) {
			cnt = 0;
			vis = new boolean[n][m];
			for (int i = 0; i < n; i++) {
				for (int j = 0; j < m; j++) {
					if (arr[i][j] != 0 && vis[i][j] == false) {
						vis[i][j] = true;
						check(i, j); // µ¢¾î¸® Ã£¾Æ¼­ vis=true ¸¸µé¾îÁÜ
						cnt++;
					}
				}
			}
			if(cnt == 0) years = 0;
			if (cnt >= 2 || cnt == 0)
				break;
			vis = new boolean[n][m];
			for (int i = 0; i < n; i++) {
				for (int j = 0; j < m; j++) {
					if (arr[i][j] != 0 && vis[i][j] == false) {
						vis[i][j] = true;
						dfs(i, j); // ¹Ù´Ù¶û ¸Â´êÀº ºÎºÐ¸¸Å­ °¨¼Ò
					}
				}
			}
			years++;
		}

		System.out.println(years);
	}

	static void check(int r, int c) {
		for (int i = 0; i < 4; i++) {
			int rr = r + dy[i];
			int cc = c + dx[i];
			if(0<=rr && rr<n && 0<= cc && cc<m) {
				if (arr[rr][cc] != 0 && vis[rr][cc] == false) {
					vis[rr][cc] = true;
					check(rr, cc);
				}
			}
			
		}
	}

	static void dfs(int r, int c) {
		int sea = 0;
		for (int i = 0; i < 4; i++) {
			int rr = r + dy[i];
			int cc = c + dx[i];
			if(0<=rr && rr<n && 0<= cc && cc<m) {
				if (arr[rr][cc] != 0 && vis[rr][cc] == false) {
					vis[rr][cc] = true;
					dfs(rr, cc);
				} else if (arr[rr][cc] == 0 && vis[rr][cc] == false)
					sea++;
			}
		}
		if(arr[r][c] < sea) arr[r][c] = 0;
		else arr[r][c] -= sea;
	}
}
