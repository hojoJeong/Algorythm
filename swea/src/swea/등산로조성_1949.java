import java.util.*;
import java.io.*;

public class 등산로조성_1949 {
	static int[] dx = { 0, 0, -1, 1 };
	static int[] dy = { -1, 1, 0, 0 };
	static int[][] map;
	static boolean[][] vis;
	static ArrayList<Point> startPoint;
	static int ans, N, K;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;

		int t = Integer.parseInt(br.readLine());
		for (int tc = 1; tc <= t; tc++) {
			st = new StringTokenizer(br.readLine());
			N = Integer.parseInt(st.nextToken());
			K = Integer.parseInt(st.nextToken());

			map = new int[N][N];
			vis = new boolean[N][N];
			ans = Integer.MIN_VALUE;
			startPoint = new ArrayList<>();

			int highest = 0;
			for (int i = 0; i < N; i++) {
				st = new StringTokenizer(br.readLine());
				for (int j = 0; j < N; j++) {
					map[i][j] = Integer.parseInt(st.nextToken());
					highest = Math.max(highest, map[i][j]);
				}
			}

			for (int i = 0; i < N; i++) {
				for (int j = 0; j < N; j++) {
					if (map[i][j] == highest) {
						startPoint.add(new Point(i, j));
					}
				}
			}

			for (int i = 0; i < startPoint.size(); i++) {
				int r = startPoint.get(i).r;
				int c = startPoint.get(i).c;

				vis[r][c] = true;
				checkLength(map, r, c, 1, false);
				vis[r][c] = false;
			}

			System.out.printf("#%d %d\n", tc, ans);
		}

	}

	static void checkLength(int[][] mapTemp, int r, int c, int length, boolean work) {
		int cnt = 0; // 4방향 탐색 후 갈 수 있는 곳이 없어 종료해야하는지 판별하는 변수
		for (int i = 0; i < 4; i++) {
			int nr = r + dy[i];
			int nc = c + dx[i];

			if (0 <= nr && nr < N && 0 <= nc && nc < N && !vis[nr][nc]) {
				if (mapTemp[nr][nc] < mapTemp[r][c]) {
					cnt++;
					vis[nr][nc] = true;
					checkLength(mapTemp, nr, nc, length + 1, work);
					vis[nr][nc] = false;
				} else {
					if (!work) {
						boolean check = false; // 깎고 이동할 수 있었는지 체크하는 변수
						for (int j = 1; j <= K; j++) {
							mapTemp[nr][nc] -= j;
							if (mapTemp[nr][nc] < mapTemp[r][c]) {
								cnt++;
								vis[nr][nc] = true;
								work = true;
								checkLength(mapTemp, nr, nc, length + 1, work);
								work = false;
								vis[nr][nc] = false;
								check = true;
							}
							mapTemp[nr][nc] += j;
							if (check)
								break; // 다음 칸을 깎은 후 이동했으면 더 이상 깎을 필요 없음
						}
					}
				}
			}
		}
		if (cnt == 0) {
			ans = Math.max(ans, length);
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
