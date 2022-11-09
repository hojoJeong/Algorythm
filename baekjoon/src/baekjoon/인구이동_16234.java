package baekjoon;

import java.util.*;
import java.io.*;

public class 인구이동_16234 {

	static int[] dx = { 1, -1, 0, 0 };
	static int[] dy = { 0, 0, 1, -1 };
	static ArrayList<Point> list;
	static boolean[][] vis;
	static int n, L, R;
	static int[][] map;
	static int cnt, sum;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());

		n = Integer.parseInt(st.nextToken());
		L = Integer.parseInt(st.nextToken());
		R = Integer.parseInt(st.nextToken());

		map = new int[n][n];
		cnt = 0;

		for (int i = 0; i < n; i++) {
			st = new StringTokenizer(br.readLine());
			for (int j = 0; j < n; j++) {
				map[i][j] = Integer.parseInt(st.nextToken());
			}
		}

		int check = 1;
		while (check != 0) {
			vis = new boolean[n][n];
			check = 0;
			for (int i = 0; i < n; i++) {
				for (int j = 0; j < n; j++) {
					if (vis[i][j] == false) {
						bfs(i, j);
						if (list.size() > 1) {
							check = 1;
							cal();
						}
					}
				}
			}
			if (check == 1)
				cnt++;
		}
		System.out.println(cnt);
	}

	static void bfs(int r, int c) {

		list = new ArrayList<>();
		list.add(new Point(r, c));
		sum = map[r][c];
		Queue<Point> q = new LinkedList<>();
		q.add(new Point(r, c));
		vis[r][c] = true;
		
		while(!q.isEmpty()) {
			Point temp = q.poll();
			for(int i=0; i<4; i++) {
				int rr = temp.r + dy[i];
				int cc = temp.c + dx[i];

				if(0<=rr && rr<n && 0<=cc && cc<n) {
					if(vis[rr][cc] == false) {

						int value = Math.abs(map[temp.r][temp.c] - map[rr][cc]);

						if(L <= value && value <= R) {

							vis[rr][cc] = true;
							sum += map[rr][cc];
							list.add(new Point(rr, cc));
							q.add(new Point(rr, cc));
						}
					}
				}
			}
		}
		
	}

	static void cal() {

		int value = sum/list.size();
		for(int i=0; i<list.size(); i++) {
			map[list.get(i).r][list.get(i).c] = value;
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
