package baekjoon;

import java.util.*;
import java.io.*;

public class 캐슬디펜스_17135 {
	static int[] dx = {-1, 0, 1};
	static int[] dy = {0, -1, 0};
	static Map[][] map, copy;
	static boolean[] vis;
	static boolean[][] mvis;
	static int[] archer;
	static int N, M, D, ans, kill;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());

		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		D = Integer.parseInt(st.nextToken());
		ans = 0;
	
		map = new Map[N][M];
		vis = new boolean[M];
		archer = new int[3];
		for (int i = 0; i < N; i++) {
			st = new StringTokenizer(br.readLine());
			for (int j = 0; j < M; j++) {
				map[i][j] = new Map(Integer.parseInt(st.nextToken()), 0);
			}
		}

		combination(0, 0, M, 3);
		System.out.println(ans);
		
	}

	static void combination(int index, int start, int n, int m) {
		if (index == m) {
			copy = new Map[N+1][M];
			for (int i = 0; i < N; i++) {
				for (int j = 0; j < M; j++) {
					copy[i][j] = new Map(map[i][j].value, map[i][j].status);
				}
			}	
			
			kill = 0;
			play();
			return;
		}

		for (int i = start; i < n; i++) {
			if (!vis[i]) {
				vis[i] = true;
				archer[index] = i;
				combination(index + 1, start + 1, n, m);
				vis[i] = false;
			}
		}
	}

	static void play() {
		if(check() == 0) {
			ans = Math.max(ans, kill);
			return;
		}

		for (int index = 0; index < 3; index++) {
			mvis = new boolean[N+1][M];
			Queue<Map> q = new LinkedList<>();
			q.add(new Map(N, archer[index]));
			mvis[N][archer[index]] = true;
			
			Loop1 : while(!q.isEmpty()) {
				Map temp = q.poll();
				for(int k=0; k<3; k++) {
					int rr = temp.value + dy[k];
					int cc = temp.status + dx[k];
					if(0<= rr && rr< N && 0<= cc && cc< M && !mvis[rr][cc]) {
						int dis = Math.abs(rr - (N)) + Math.abs(cc - archer[index]);
						if (copy[rr][cc].value == 1 && dis <= D) {
							copy[rr][cc].status = 1;
							break Loop1;
						} else {
							q.add(new Map(rr, cc));
							mvis[rr][cc] = true;
						}
					}
				}
			}
		}
		
		// status == 1인 적들 없애줌
		for (int i = 0; i < N; i++) {
			for (int j = 0; j < M; j++) {
				if (copy[i][j].status == 1) {
					copy[i][j].value = 0;
					copy[i][j].status = 0;
					kill++;
				}
			}
		}
		
		move();
			
		play();
	}

	static void move() {
		for (int i = N - 1; i > 0; i--) {
			for (int j = 0; j < M; j++) {
				copy[i][j].value = copy[i-1][j].value;
				copy[i][j].status = copy[i-1][j].status;

				copy[i-1][j].value = 0;
				copy[i-1][j].status = 0;
			}
		}
	}
	
	//남은 적의 수 계산
	static int check() {
		int cnt = 0;
		for (int i = 0; i < N; i++) {
			for (int j = 0; j < M; j++) {
				if (copy[i][j].value == 1) {
					cnt++;
				}
			}
		}
		
		return cnt;
	}

	public static class Map {
		int value, status;

		public Map(int value, int status) {
			this.value = value;
			this.status = status;
		}
	}
}