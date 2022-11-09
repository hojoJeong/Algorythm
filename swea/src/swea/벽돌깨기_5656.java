package swea;

import java.util.*;
import java.io.*;

public class 벽돌깨기_5656 {
	static int[][] map, copy;
	static boolean[][] check;
	static int[] order;
	static int ans, n, w, h;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;

		int t = Integer.parseInt(br.readLine());
		for (int tc = 1; tc <= t; tc++) {
			st = new StringTokenizer(br.readLine());
			n = Integer.parseInt(st.nextToken());
			w = Integer.parseInt(st.nextToken());
			h = Integer.parseInt(st.nextToken());

			map = new int[h][w];
			order = new int[n];
			ans = Integer.MAX_VALUE;

			for (int i = 0; i < h; i++) {
				st = new StringTokenizer(br.readLine());
				for (int j = 0; j < w; j++) {
					map[i][j] = Integer.parseInt(st.nextToken());
				}
			}
			permutation(0, w, n);

			System.out.printf("#%d %d\n", tc, ans);
		}
	}

	// 공 쏠 순서 먼저 정해줌
	static void permutation(int index, int N, int M) {
		if (index == M) {
			copy = new int[h][w];
			for (int i = 0; i < h; i++) {
				for (int j = 0; j < w; j++) {
					copy[i][j] = map[i][j];
				}
			}
			play(0);
			int result = 0;
			for (int i = 0; i < h; i++) {
				for (int j = 0; j < w; j++) {
					if (copy[i][j] != 0) {
						result++;
					}
				}
			}
			ans = Math.min(ans, result);
			return;
		}

		for (int i = 0; i < N; i++) {
			order[index] = i;
			permutation(index + 1, N, M);
		}
	}

	static void play(int index) {
		if (index == n) {
			return;
		}

		// 벽돌이 아래로 당겨질 수 있는지 확인하고 아래로 내려줌
		check();

		// 공을 쏘려는 열에서 가장 위에 있는 벽돌의 행번호를 구함
		int r = 0;
		boolean flag = false;
		for (int i = 0; i < h; i++) {
			if (copy[i][order[index]] != 0) {
				r = i;
				flag = true;
				break;
			}
		}
		check = new boolean[h][w];
		if (flag) { // 해당 열에 벽돌이 남아 있을 경우만 실행
			Queue<Brick> q = new LinkedList<>();
			q.add(new Brick(r, order[index]));
			check[r][order[index]] = true;
			while (!q.isEmpty()) { // 공 한 발 쏘기 start
				Brick point = q.poll();
				// 체크할 범위 정해줌
				int startr = point.r - copy[point.r][point.c] + 1;
				int endr = point.r + copy[point.r][point.c] - 1;
				int startc = point.c - copy[point.r][point.c] + 1;
				int endc = point.c + copy[point.r][point.c] - 1;

				// 범위 밖 벗어나지 않게 맞춰줌
				if (startr < 0)
					startr = 0;
				if (startc < 0)
					startc = 0;
				if (endr >= h)
					endr = h - 1;
				if (endc >= w)
					endc = w - 1;

				// 깨질 벽돌을 골라줌
				for (int i = startr; i <= endr; i++) {
					if (copy[i][point.c] != 0 && check[i][point.c] == false) {
						check[i][point.c] = true;
						q.add(new Brick(i, point.c));
					}
				}
				for (int j = startc; j <= endc; j++) {
					if (copy[point.r][j] != 0 && check[point.r][j] == false) {
						check[point.r][j] = true;
						q.add(new Brick(point.r, j));
					}
				}
			}
			// 골라준 벽돌을 깨트림
			for (int i = 0; i < h; i++) {
				for (int j = 0; j < w; j++) {
					if (check[i][j]) {
						copy[i][j] = 0;
					}
				}
			}
		}

		play(index + 1);
	}

	// 빈칸 체크하고 밑으로 다 내려줌
	static void check() {
		for (int j = 0; j < w; j++) {
			int r = 0;
			boolean flag = false;
			for (int i = h - 1; i >= 0; i--) {
				if (copy[i][j] == 0 && flag == false) {
					flag = true;
					r = i;
				}
				else if (copy[i][j] != 0 && flag == true) {
					for (int f = i + 1; f <= r; f++) {
						copy[f][j] = copy[f - 1][j];
						copy[f - 1][j] = 0;
					}
					flag = false;
					i = r;
				}
			}
		}
	}

	public static class Brick {
		int r, c;

		public Brick(int r, int c) {
			this.r = r;
			this.c = c;
		}
	}
}
