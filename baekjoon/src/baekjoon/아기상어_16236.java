package baekjoon;

import java.util.*;
import java.io.*;

public class 아기상어_16236 {
	static int[] dx = { 0, 0, -1, 1 };
	static int[] dy = { -1, 1, 0, 0 };
	static int[][] map;
	static Point fish;
	static boolean[][] vis;
	static int ans, n, startR, startC, size, eatCnt;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;

		n = Integer.parseInt(br.readLine());
		map = new int[n][n];
		vis = new boolean[n][n];
		ans = 0;
		size = 2;
		eatCnt = 0;
		for (int i = 0; i < n; i++) {
			st = new StringTokenizer(br.readLine());
			for (int j = 0; j < n; j++) {
				map[i][j] = Integer.parseInt(st.nextToken());
				if (map[i][j] == 9) {
					startR = i;
					startC = j;
					map[i][j] = 0;
				}
			}
		}

		while (true) {
			fish = new Point(Integer.MAX_VALUE, Integer.MAX_VALUE, Integer.MAX_VALUE);
			bfs(startR, startC);
			if (fish.r == Integer.MAX_VALUE) {
				break;
			} else {
				startR = fish.r;
				startC = fish.c;
				eatCnt++;
				if (size == eatCnt) {
					size++;
					eatCnt = 0;
				}
				ans += fish.cnt;
				vis = new boolean[n][n];
				map[fish.r][fish.c] = 0;
			}
		}
		System.out.println(ans);
	}

	static void bfs(int r, int c) {
		Queue<Point> q = new LinkedList<>();
		q.add(new Point(r, c, 0));
		vis[r][c] = true;
		while (!q.isEmpty()) {
			Point temp = q.poll();
			for (int i = 0; i < 4; i++) {
				int rr = temp.r + dy[i];
				int cc = temp.c + dx[i];
				int cnt = temp.cnt;
				if (0 <= rr && rr < n && 0 <= cc && cc < n && !vis[rr][cc] && map[rr][cc] <= size) {
					q.add(new Point(rr, cc, cnt + 1));
					vis[rr][cc] = true;
					if (map[rr][cc] != 0 && map[rr][cc] < size) {
						if (fish.cnt > cnt + 1) {
							fish.r = rr;
							fish.c = cc;
							fish.cnt = cnt+1;
						} else if(fish.cnt == cnt +1) {
							if(fish.r > rr) {
								fish.r = rr;
								fish.c = cc;
								fish.cnt = cnt+1;
							} else if(fish.r == rr && fish.c > cc) {
								fish.r = rr;
								fish.c = cc;
								fish.cnt = cnt+1;
							}
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