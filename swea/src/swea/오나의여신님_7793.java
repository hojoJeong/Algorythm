package swea;

import java.util.*;
import java.io.*;

public class 오나의여신님_7793 {

	static int[] dx = { 0, 0, -1, 1 };
	static int[] dy = { -1, 1, 0, 0 };
	static ArrayList<Point> Devil;
	static Map[][] map;
	static int ans, n, m, sooR, sooC;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;

		int t = Integer.parseInt(br.readLine());
		for (int tc = 1; tc <= t; tc++) {
			st = new StringTokenizer(br.readLine());

			n = Integer.parseInt(st.nextToken());
			m = Integer.parseInt(st.nextToken());

			map = new Map[n][m];
			ans = 0;
			Devil = new ArrayList<>();
			for (int i = 0; i < n; i++) {
				String str = br.readLine();
				for (int j = 0; j < m; j++) {
					map[i][j] = new Map(str.charAt(j), false, 0);
					if (str.charAt(j) == '*') {
						Devil.add(new Point(i, j, 0, -1));
						map[i][j].skill = true;
					} else if (str.charAt(j) == 'S') {
						sooR = i;
						sooC = j;
						map[i][j].soo = 1;
					}
				}
			}
			
			bfs();
			System.out.printf("#%d ", tc);
			if(ans == 0) {
				System.out.println("GAME OVER");
			} else {
				System.out.println(ans);
			}
		}

	}

	static void bfs() {
		Queue<Point> q = new LinkedList<>();
		for(int i=0; i<Devil.size(); i++) {
			q.add(new Point(Devil.get(i).r, Devil.get(i).c, 0, -1));
		}
		q.add(new Point(sooR, sooC, 0, 1));
		int result = 0;
		while (!q.isEmpty()) {
			Point temp = q.poll();
			for (int i = 0; i < 4; i++) {
				int rr = temp.r + dy[i];
				int cc = temp.c + dx[i];
				int cnt = temp.cnt;

				if (0 <= rr && rr < n && 0 <= cc && cc < m) {
					if(temp.who == -1) {	// 악마일 경우
						if(!map[rr][cc].skill && map[rr][cc].value != 'X' && map[rr][cc].value != 'D') {
							q.add(new Point(rr, cc, cnt+1, -1));
							map[rr][cc].skill = true;
						}
					} else if(temp.who == 1) {	//수연이일 경우
						if(!map[rr][cc].skill && map[rr][cc].value != 'X' && map[rr][cc].soo == 0 && map[rr][cc].value != '*') {
							q.add(new Point(rr, cc, cnt+1, 1));
							map[rr][cc].soo = cnt+1;
							if(map[rr][cc].value == 'D') {
								ans = map[rr][cc].soo;
								return;
							}
						}
					}
				}
			}
		}
	}

	public static class Map {
		char value;
		boolean skill;
		int soo;

		public Map(char value, boolean skill, int soo) {
			this.value = value;
			this.skill = skill;
			this.soo = soo;
		}
	}

	public static class Point {
		int r, c, cnt, who;

		public Point(int r, int c, int cnt, int who) {
			this.r = r;
			this.c = c;
			this.cnt = cnt;
			this.who = who;
		}
	}
}
