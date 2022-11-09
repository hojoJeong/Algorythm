package swea;

import java.util.*;
import java.io.*;

public class 홈방범서비스_2117 {
	static int[] dx = { 0, 0, -1, 1 };
	static int[] dy = { -1, 1, 0, 0 };
	static int[][] map;
	static boolean[][] vis;
	static int ans, n, m;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;

		int t = Integer.parseInt(br.readLine());
		for (int tc = 1; tc <= t; tc++) {
			st = new StringTokenizer(br.readLine());
			n = Integer.parseInt(st.nextToken());
			m = Integer.parseInt(st.nextToken());

			map = new int[n][n];
			ans = 0;

			for (int i = 0; i < n; i++) {
				st = new StringTokenizer(br.readLine());
				for (int j = 0; j < n; j++) {
					map[i][j] = Integer.parseInt(st.nextToken());

				}
			}

			for (int i = 0; i < n; i++) {
				for (int j = 0; j < n; j++) {
					bfs(i, j);
				}
			}
			System.out.printf("#%d %d\n", tc, ans);
		}
	}

	static void bfs(int r, int c) {
		Queue<Point> q = new LinkedList<>();
		vis = new boolean[n][n];

		q.add(new Point(r, c));
		vis[r][c] = true;
		
		int k = 1;
		int cnt = 0;
		while (!q.isEmpty()) {
			int size = q.size();
			for (int i = 0; i < size; i++) {
				Point temp = q.poll();
				if (map[temp.r][temp.c] == 1) {
					cnt++;
				}
				for (int j = 0; j < 4; j++) {
					int rr = temp.r + dy[j];
					int cc = temp.c + dx[j];
					if (0 <= rr && rr < n && 0 <= cc && cc < n && !vis[rr][cc]) {
						q.add(new Point(rr, cc));
						vis[rr][cc] = true;
					}
				}
			}
			int cost = (k * k) + (k - 1) * (k - 1);			
			int income = cnt * m;
			if (income - cost >= 0) {
				ans = Math.max(ans, cnt);
			}
			k++;
		}
	}

	public static class Point {
		int r, c;

		public Point(int r, int c) {
			this.r = r;
			this.c = c;
		}
	}
}