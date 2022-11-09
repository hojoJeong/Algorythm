package baekjoon;

import java.util.*;
import java.io.*;

public class 아기상어_17086 {

	static int[] dx = { 1, 1, 0, -1, -1, -1, 0, 1 };
	static int[] dy = { 0, -1, -1, -1, 0, 1, 1, 1, };

	static int[][] arr;
	static boolean[][] vis;
	static int n, m, ans, cnt;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());

		n = Integer.parseInt(st.nextToken());
		m = Integer.parseInt(st.nextToken());

		arr = new int[n][m];
		ans = 0;

		for (int i = 0; i < n; i++) {
			st = new StringTokenizer(br.readLine());
			for (int j = 0; j < m; j++) {
				arr[i][j] = Integer.parseInt(st.nextToken());
			}
		}

		for (int i = 0; i < n; i++) {
			for (int j = 0; j < m; j++) {
				if (arr[i][j] == 0) {
					bfs(i, j);
					ans = Math.max(ans, cnt);
				}
			}
		}
		
		System.out.println(ans);
	}

	static void bfs(int r, int c) {
		cnt = 0;
		vis = new boolean[n][m];
		Queue<Point> q = new LinkedList<>();
		q.add(new Point(r, c, 1));
		vis[r][c] = true;
		Loop1: while (!q.isEmpty()) {
			Point temp = q.poll();
			for (int i = 0; i < 8; i++) {
				for (int j = 0; j < 8; j++) {
					int rr = temp.r + dy[i];
					int cc = temp.c + dx[i];
					int count = temp.cnt;
					if (0 <= rr && rr < n && 0 <= cc & cc < m) {
						if (arr[rr][cc] == 0 && vis[rr][cc] == false) {
							q.add(new Point(rr, cc, count + 1));
							vis[rr][cc] = true;
						} else if (arr[rr][cc] == 1) {
							cnt = temp.cnt;
							break Loop1;
						}
					}
				}
			}
		}
	}

	public static class Point {
		int r, c, cnt;

		public Point(int r, int c, int cnt) {
			this.r = r;
			this.c = c;
			this.cnt = cnt;
		}
	}
}