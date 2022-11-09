package swea;

import java.util.*;
import java.io.*;
import java.lang.reflect.Array;

public class 프로세서_1767 {
	static int[] dx = { 0, 0, -1, 1 };
	static int[] dy = { -1, 1, 0, 0 };
	static ArrayList<Core> core;
	static int[][] cell;
	static boolean[][] vis;
	static int n, ans, coreCnt;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;

		int t = Integer.parseInt(br.readLine());

		for (int tc = 1; tc <= t; tc++) {
			n = Integer.parseInt(br.readLine());
			cell = new int[n][n];
			vis = new boolean[n][n];
			core = new ArrayList<>();
			ans = Integer.MAX_VALUE;
			coreCnt = 0;

			for (int i = 0; i < n; i++) {
				st = new StringTokenizer(br.readLine());
				for (int j = 0; j < n; j++) {
					cell[i][j] = Integer.parseInt(st.nextToken());
					if (cell[i][j] == 1) {
						vis[i][j] = true;
						if (i != 0 && i != n - 1 && j != 0 && j != n - 1) {
							core.add(new Core(i, j));
						}
					}
				}
			}

			dfs(0, 0, 0, 0);
//			for (int i = -1; i < 4; i++) {
//				dfs(0, i, 0, 0);
//			}
			System.out.printf("#%d %d\n", tc, ans);
		}

	}

	static void dfs(int index, int dir, int curCoreCnt, int lineLength) {
		if (index == core.size()) {
			if (coreCnt < curCoreCnt) {
				coreCnt = curCoreCnt;
				ans = lineLength;
			} else if (coreCnt == curCoreCnt) {
				ans = Math.min(ans, lineLength);
			}
			return;
		}

		int r = core.get(index).r;
		int c = core.get(index).c;
		int lineLengthTemp = lineLength;
		int curCoreCntTemp = curCoreCnt;

		for (int i = -1; i < 4; i++) {
			if(i == -1) {
				dfs(index + 1, i, curCoreCntTemp, lineLengthTemp);
			}
			if (i != -1 && check(r, c, i)) {
				int temp = drawLine(r, c, i);
				lineLengthTemp += temp;
				curCoreCntTemp++;
				dfs(index + 1, i, curCoreCntTemp, lineLengthTemp);
				eraser(r, c, i);
				lineLengthTemp -= temp;
				curCoreCntTemp--;
			}
		}
	}

	static boolean check(int r, int c, int dir) {
		int cnt = 1;
		boolean result = true;
		while (true) {
			int rr = r + dy[dir] * cnt;
			int cc = c + dx[dir] * cnt;
			if (rr < 0 || rr > n - 1 || cc < 0 || cc > n - 1)
				break;
			if (vis[rr][cc]) {
				result = false;
				break;
			} else {
				cnt++;
			}
		}
		return result;
	}

	static int drawLine(int r, int c, int dir) {
		int cnt = 1;
		while (true) {
			int rr = r + dy[dir] * cnt;
			int cc = c + dx[dir] * cnt;
			if (rr < 0 || rr > n - 1 || cc < 0 || cc > n - 1)
				break;
			else {
				vis[rr][cc] = true;
				cnt++;
			}
		}
		return cnt - 1;
	}

	static void eraser(int r, int c, int dir) {
		int cnt = 1;
		while (true) {
			int rr = r + dy[dir] * cnt;
			int cc = c + dx[dir] * cnt;
			if (rr < 0 || rr > n - 1 || cc < 0 || cc > n - 1)
				break;
			else {
				vis[rr][cc] = false;
				cnt++;
			}
		}
	}

	public static class Core {
		int r, c;

		public Core(int r, int c) {
			this.r = r;
			this.c = c;
		}
	}
}