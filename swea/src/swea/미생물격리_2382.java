package swea;

import java.io.*;
import java.util.*;

public class 미생물격리_2382 {
	static int[] dx = {0,  0, 0, -1, 1 };
	static int[] dy = {0,  -1, 1, 0, 0 };
	static int N, M, K, ans, sum;
	static int[][] map, dir, maptemp;
	static boolean[][] vis;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;

		int t = Integer.parseInt(br.readLine());
		for (int tc = 1; tc <= t; tc++) {
			st = new StringTokenizer(br.readLine());
			N = Integer.parseInt(st.nextToken());
			M = Integer.parseInt(st.nextToken());
			K = Integer.parseInt(st.nextToken());
			map = new int[N][N];
			dir = new int[N][N];
			ans = 0;

			for (int i = 0; i < K; i++) {
				st = new StringTokenizer(br.readLine());
				int r = Integer.parseInt(st.nextToken());
				int c = Integer.parseInt(st.nextToken());
				int m = Integer.parseInt(st.nextToken());
				int d = Integer.parseInt(st.nextToken());
				map[r][c] = m;
				dir[r][c] = d;
			}

			sum = 0;
			dfs(0);
			System.out.printf("#%d %d\n", tc, ans);
		}
	}

	static void dfs(int count) {
		if (count == M) {
			for (int i = 0; i < N; i++) {
				for (int j = 0; j < N; j++) {
					sum += map[i][j];
				}
			}
			ans = sum;
			return;
		}
		maptemp = new int[N][N];
		vis = new boolean[N][N];

		// 2개 이상의 군집이 합쳐지는 곳을 찾음
		for (int r = 0; r < N; r++) {
			for (int c = 0; c < N; c++) {
				if (map[r][c] != 0) {
					int rr = r + dy[dir[r][c]];
					int cc = c + dx[dir[r][c]];
					maptemp[rr][cc]++;
				}
			}
		}
		for (int r = 0; r < N; r++) {
			for (int c = 0; c < N; c++) {
				if (maptemp[r][c] > 1) {
					int temp = 0;
					for (int i = 0; i < 4; i++) {
						int rr = r + dy[i];
						int cc = c + dx[i];
						if (map[rr][cc] != 0) {
							if (temp == 0) { // 비어있으면 초기화해줌
								temp = map[rr][cc];
								map[r][c] += map[rr][cc];
								map[rr][cc] = 0;
								dir[r][c] = dir[rr][cc];
								dir[rr][cc] = 0;
								vis[r][c] = true;
							} else {
								if (temp > map[rr][cc]) { // 이미 이동한 군집 중 이동할 군집보다 큰 것이 있으면 map값만 더해주고 dir은 초기화x
									map[r][c] += map[rr][cc];
									map[rr][cc] = 0;
									dir[rr][cc] = 0;
								} else { // 이미 이동한 군집 보다 이동할 군집이 크면 dir, temp 초기화
									temp = map[rr][cc];
									map[r][c] += map[rr][cc];
									map[rr][cc] = 0;
									dir[r][c] = dir[rr][cc];
									dir[rr][cc] = 0;
								}
							}
						}
					}
				}
			}
		}

		// 2개 이상 합쳐지는 곳을 제외하고 나머지 이동
		for (int r = 0; r < N; r++) {
			for (int c = 0; c < N; c++) {
				if (map[r][c] != 0 && vis[r][c] == false) {
					int rr = r + dy[dir[r][c]];
					int cc = c + dx[dir[r][c]];
					if (0 <= rr && rr < N && 0 <= cc && cc < N) {
						if (map[rr][cc] == 0 && 0 < rr && rr < N - 1 && 0 < cc && cc < N - 1) { // 가장자리에 붙지 않고 다음 칸에 아무것도 없을 때
							map[rr][cc] = map[r][c];
							map[r][c] = 0;
							dir[rr][cc] = dir[r][c];
							dir[r][c] = 0;
						} else if (rr == 0 || rr == N - 1 || cc == 0 || cc == N - 1) { // 가장자리 접촉
							System.out.println(r + " " + c + " " + map[r][c] + " " + dir[r][c]);
							map[rr][cc] = map[r][c] / 2;
							map[r][c] = 0;
							if (dir[r][c] == 1)
								dir[rr][cc] = 2;
							else if (dir[r][c] == 2)
								dir[rr][cc] = 1;
							else if (dir[r][c] == 3)
								dir[rr][cc] = 4;
							else if (dir[r][c] == 4)
								dir[rr][cc] = 3;
							dir[r][c] = 0;
						} else if (map[rr][cc] != 0 && 0 <= rr && rr < N && 0 <= cc && cc < N) { // 서로 다른 2개의 군집이 만났을 때(방향이 서로 다를 때)
							move(r, c);
//							int rrr = rr + dy[dir[rr][cc]];	//뒤에거 먼저 옮겨줌
//							int ccc = cc + dx[dir[rr][cc]];
//							map[rrr][ccc] = map[rr][cc];
//							map[rr][cc] = 0;
//							dir[rrr][ccc] = dir[rr][cc];
//							dir[rr][cc] = 0;
//							
//							map[rr][cc] = map[r][c];
//							map[r][c] = 0;
//							dir[rr][cc] = dir[r][c];
//							dir[r][c] = 0;
						}
					}

				}
			}
		}
		dfs(count + 1);
	}
	
	static void move(int r, int c) {
		
		
		Stack<Point> stack = new Stack<>();
		stack.add(new Point(r, c));
		
		while(!stack.isEmpty()) {
			Point temp = stack.peek();
			int rr = temp.r + dy[dir[temp.r][temp.c]];
			int cc = temp.c + dx[dir[temp.r][temp.c]];
			if(map[rr][cc] != 0) {
				stack.add(new Point(rr, cc));
			} else {
				Point point = stack.pop();
				int rr2 = temp.r + dy[dir[temp.r][temp.c]];
				int cc2 = temp.c + dx[dir[temp.r][temp.c]];
				map[rr2][cc2] = map[point.r][point.c];
				map[point.r][point.c] = 0;
				dir[rr2][cc2] = dir[point.r][point.c];
				dir[point.r][point.c] = 0;
			}
		}
		System.out.println("check");
		
	}
	
	public static class Point{
		int r, c;
		public Point(int r, int c) {
			this.r = r;
			this.c = c;
		}
	}
}