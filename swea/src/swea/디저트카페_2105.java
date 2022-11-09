package swea;

import java.util.*;
import java.io.*;

public class 디저트카페_2105 {
	static int[] dx = { -1, 1, 1, -1 };
	static int[] dy = { 1, 1, -1, -1 };
	static int[][] map;
	static boolean[][] vis;
	static Set<Integer> set;
	static int N, ans;
	static int[] line;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;

		int t = Integer.parseInt(br.readLine());

		for (int tc = 1; tc <= t; tc++) {
			N = Integer.parseInt(br.readLine());
			map = new int[N][N];
			line = new int[2];
			ans = -1;

			for (int i = 0; i < N; i++) {
				st = new StringTokenizer(br.readLine());
				for (int j = 0; j < N; j++) {
					map[i][j] = Integer.parseInt(st.nextToken());
				}
			}

			permutation(0, N - 1, 2);

			System.out.printf("#%d %d\n", tc, ans);
		}
	}

	static void permutation(int index, int n, int m) {
		if (index == m) {
			if (line[0] + line[1] - 1 <= N) {
				if (2 <= line[0] && line[0] <= N - 1 && 2 <= line[1] && line[1] <= N - 1) {
					for (int i = 0; i < N; i++) {
						for (int j = 0; j < N; j++) {
							move(i, j);
						}
					}
				}
			}
			return;
		}

		for (int i = 2; i <= n; i++) {
				line[index] = i;
				permutation(index + 1, n, m);			
		}
	}

	static void move(int r, int c) {
		Queue<Point> q = new LinkedList<>();
		vis = new boolean[N][N];
		set = new HashSet<>();
		q.add(new Point(r, c));
		set.add(map[r][c]);
		vis[r][c] = true;
		int index = 0;
		int cnt = 1;
		while (!q.isEmpty()) {
			if (cnt == line[index % 2]) {
				index++;
				cnt = 1;
			}

			Point temp = q.poll();
			int rr = temp.r + dy[index];
			int cc = temp.c + dx[index];
			if (0 <= rr && rr < N && 0 <= cc && cc < N && !vis[rr][cc]) {
				q.add(new Point(rr, cc));
				vis[rr][cc] = true;
				set.add(map[rr][cc]);
				cnt++;
			}
		}

		if (set.size() >= 3 && set.size() == (2 * line[0] + (line[1] - 2) * 2)) {

			ans = Math.max(set.size(), ans);
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
