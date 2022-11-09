package swea;

import java.util.*;
import java.io.*;

public class swea_모의역량테스트_등산로 {
	static int[] dx = { 0, 0, -1, 1 };
	static int[] dy = { -1, 1, 0, 0 };
	static boolean[][] vis;
	static ArrayList<Map> start;
	static Map[][] map;
	static int N, K, ans;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;

		int t = Integer.parseInt(br.readLine());

		for (int tc = 1; tc <= t; tc++) {
			st = new StringTokenizer(br.readLine());

			N = Integer.parseInt(st.nextToken());
			K = Integer.parseInt(st.nextToken());
			ans = 0;

			map = new Map[N][N];
			start = new ArrayList<>();

			int temp = 0;
			for (int i = 0; i < N; i++) {
				st = new StringTokenizer(br.readLine());
				for (int j = 0; j < N; j++) {
					map[i][j] = new Map(i, j, Integer.parseInt(st.nextToken()), 1);
					if (temp < map[i][j].value)
						temp = map[i][j].value;
				}
			}

			for (int i = 0; i < N; i++) {
				for (int j = 0; j < N; j++) {
					if (map[i][j].value == temp) {
						start.add(new Map(i, j, temp, 1));
					}
				}
			}

			for (int i = 0; i < start.size(); i++) {
				vis = new boolean[N][N];
				vis[start.get(i).r][start.get(i).c] = true;
				dfs(start.get(i).r, start.get(i).c, false, 1);
			}

			System.out.printf("#%d %d\n", tc, ans);
		}
	}

	static void dfs(int r, int c, boolean flag, int count) {
		for (int i = 0; i < 4; i++) {
			int rr = r + dy[i];
			int cc = c + dx[i];
			if (0 <= rr && rr < N && 0 <= cc && cc < N && !vis[rr][cc]) {
				if (map[rr][cc].value < map[r][c].value) {
					vis[rr][cc] = true;
					dfs(rr, cc, flag, count + 1);
					vis[rr][cc] = false;
				} else if (!flag && map[rr][cc].value >= map[r][c].value && map[rr][cc].value - K < map[r][c].value) {
					for (int k = 1; k <= K; k++) {
						if(map[rr][cc].value - k < map[r][c].value) {
							map[rr][cc].value = map[rr][cc].value-k;
							vis[rr][cc] = true;
							dfs(rr, cc, true, count+1);
							map[rr][cc].value = map[rr][cc].value+k;
							vis[rr][cc] = false;
							break;
						}
					}
				}
			}
		}
		ans = Math.max(count, ans);
	}

	public static class Map {
		int r, c, value, cnt;

		public Map(int r, int c, int value, int cnt) {
			this.r = r;
			this.c = c;
			this.value = value;
			this.cnt = cnt;
		}
	}
}
