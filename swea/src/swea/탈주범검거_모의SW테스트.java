package swea;

import java.util.*;
import java.io.*;

public class 탈주범검거_모의SW테스트 {
	static int[] dx = { 0, 0, 0, -1, 1 };
	static int[] dy = { 0, -1, 1, 0, 0 };
	static boolean[][] vis;
	static Map[][] map;
	static int N, M, R, C, L, ans;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;

		int t = Integer.parseInt(br.readLine());
		for (int tc = 1; tc <= t; tc++) {
			st = new StringTokenizer(br.readLine());
			N = Integer.parseInt(st.nextToken());
			M = Integer.parseInt(st.nextToken());
			R = Integer.parseInt(st.nextToken());
			C = Integer.parseInt(st.nextToken());
			L = Integer.parseInt(st.nextToken());

			ans = 0;
			map = new Map[N][M];

			for (int i = 0; i < N; i++) {
				st = new StringTokenizer(br.readLine());
				for (int j = 0; j < M; j++) {
					int temp = Integer.parseInt(st.nextToken());
					map[i][j] = new Map(temp);
					switch (temp) {
					case 1: {
						map[i][j].dir.add(1);
						map[i][j].dir.add(2);
						map[i][j].dir.add(3);
						map[i][j].dir.add(4);
						break;
					}
					case 2: {
						map[i][j].dir.add(1);
						map[i][j].dir.add(2);
						break;

					}
					case 3: {
						map[i][j].dir.add(3);
						map[i][j].dir.add(4);
						break;

					}
					case 4: {
						map[i][j].dir.add(1);
						map[i][j].dir.add(4);
						break;

					}
					case 5: {
						map[i][j].dir.add(2);
						map[i][j].dir.add(4);
						break;

					}
					case 6: {
						map[i][j].dir.add(2);
						map[i][j].dir.add(3);
						break;

					}
					case 7: {
						map[i][j].dir.add(1);
						map[i][j].dir.add(3);
						break;

					}
					}

				}
			}

			bfs(R, C);
			System.out.printf("#%d %d\n", tc, ans);
		}
	}

	static void bfs(int r, int c) {
		vis = new boolean[N][M];
		Queue<Point> q = new LinkedList<>();
		q.add(new Point(r, c, 1));
		vis[r][c] = true;
		while (!q.isEmpty()) {
			Point temp = q.poll();
			if(temp.time >= L) break;
			for (int i = 0; i < map[temp.r][temp.c].dir.size(); i++) {
				int rr = temp.r + dy[map[temp.r][temp.c].dir.get(i)];
				int cc = temp.c + dx[map[temp.r][temp.c].dir.get(i)];
				int time = temp.time;

				if (0 <= rr && rr < N && 0 <= cc && cc < M && !vis[rr][cc] && map[rr][cc].value != 0) {
					if(map[temp.r][temp.c].dir.get(i) == 1) {
						if(map[rr][cc].dir.contains(2)) {
							q.add(new Point(rr, cc, time+1));
							vis[rr][cc] = true;
						}
					} else if(map[temp.r][temp.c].dir.get(i) == 2) {
						if(map[rr][cc].dir.contains(1)) {
							q.add(new Point(rr, cc, time+1));
							vis[rr][cc] = true;
						}
					} else if(map[temp.r][temp.c].dir.get(i) == 3) {
						if(map[rr][cc].dir.contains(4)) {
							q.add(new Point(rr, cc, time+1));
							vis[rr][cc] = true;
						}
					} else if(map[temp.r][temp.c].dir.get(i) == 4) {
						if(map[rr][cc].dir.contains(3)) {
							q.add(new Point(rr, cc, time+1));
							vis[rr][cc] = true;
						}
					}
				}
			}
		}
		
		for(int i=0; i<N; i++) {
			for(int j=0; j<M; j++) {
				if(vis[i][j]) {
					ans++;
				}
			}
		}
	}

	public static class Map {
		int value;
		ArrayList<Integer> dir;
		
		public Map(int value) {
			this.value = value;
			this.dir = new ArrayList<>();
		}
	}

	public static class Point {
		int r, c, time;

		public Point(int r, int c, int time) {
			this.r = r;
			this.c = c;
			this.time = time;
		}
	}
}
