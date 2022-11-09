package baekjoon;

import java.util.*;
import java.io.*;

public class º¸¹°¼¶_2589 {
	static int[] dx = { 1, -1, 0, 0 };
	static int[] dy = { 0, 0, 1, -1 };

	static char[][] map;
	static boolean[][] vis;
	static int n, m, max, ans;

	public static void main(String[] args) throws IOException {

		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());

		n = Integer.parseInt(st.nextToken());
		m = Integer.parseInt(st.nextToken());

		map = new char[n][m];

		for (int i = 0; i < n; i++) {
			String str = br.readLine();
			for (int j = 0; j < m; j++) {
				map[i][j] = str.charAt(j);
			}
		}

		ans = 0;

		for (int i = 0; i < n; i++) {
			for (int j = 0; j < m; j++) {
				if (map[i][j] == 'L') {
					vis = new boolean[n][m];
					bfs(i, j);
					ans = Math.max(max, ans);
				}
			}
		}
		System.out.println(ans);
	}

	static void bfs(int r, int c) {
		Queue<Point> q = new LinkedList<>();
		max = 0;
		q.add(new Point(r, c, 0));
		vis[r][c] = true;
		while (!q.isEmpty()) {
			Point temp = q.poll();
			for (int i = 0; i < 4; i++) {
				int rr = temp.r + dy[i];
				int cc = temp.c + dx[i];
				if (0 <= rr && rr < n && 0 <= cc && cc < m) {
					if (map[rr][cc] == 'L' && vis[rr][cc] == false) {
						q.add(new Point(rr, cc, temp.count+1));
						vis[rr][cc] = true;
						max = Math.max(max, temp.count+1);
					}
				}
			}
		}
	}

	public static class Point {
		int r;
		int c;
		int count;
		public Point(int r, int c, int count) {
			this.r = r;
			this.c = c;
			this.count = count;
		}
	}
}
