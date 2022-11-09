package baekjoon;

import java.util.*;
import java.io.*;

public class 감시_15683 {

	static int N, M;
	static int[][] map, copy; // 방향에 따른 -1 입력 시 복사해줄 배열
	static boolean[] cvis;
	static boolean[][] copyvis;
	static int[] com;
	static ArrayList<CCTV> list; // cctv번호와 좌표 입력
	static int ans, min;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());

		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());

		map = new int[N][M];
		copy = new int[N][M];

		list = new ArrayList<>();
		ans = 0;
		min = Integer.MAX_VALUE;

		for (int i = 0; i < N; i++) {
			st = new StringTokenizer(br.readLine());
			for (int j = 0; j < M; j++) {
				map[i][j] = Integer.parseInt(st.nextToken());
				if (map[i][j] != 0 && map[i][j] != 6)
					list.add(new CCTV(map[i][j], i, j));
			}
		}

		com = new int[list.size()];
		cvis = new boolean[4];

		combination(0, 4, list.size());

		if (min == Integer.MAX_VALUE)
			min = 0;
		System.out.println(min);
	}

	// 중복조합을 통해 cctv별 방향의 경우의 수를 구해줌
	static void combination(int index, int n, int m) {
		if (index == m) {

			// 중복조합의 결과가 나오면 direction으로 보내줌
			// 깊은복사로 배열 복사해줌, 초기화 위치 주의할 것
			copy = new int[N][M];
			for (int i = 0; i < N; i++) {
				for (int j = 0; j < M; j++) {
					copy[i][j] = map[i][j];
				}
			}
			for (int i = 0; i < list.size(); i++) {
				direction(list.get(i), com[i]);
			}
			return;
		}
		for (int i = 0; i < n; i++) {
			if (cvis[i] == false) {
				com[index] = i;
				combination(index + 1, n, m);
			}
		}
	}

	// combination으로 구해진 중복조합을 통해 경우의 수 별로 방향을 전환시켜 감시구역을 정함
	static void direction(CCTV cctv, int d) {

		if (cctv.cctvnum == 1) {
			if (d == 0) {
				int[] dx = { 1, 0, 0, 0 };
				int[] dy = { 0, 0, 0, 0 };
				addsection(cctv, dx, dy);
			} else if (d == 1) {
				int[] dx = { 0, 0, 0, 0 };
				int[] dy = { 0, 0, 0, -1 };
				addsection(cctv, dx, dy);
			} else if (d == 2) {
				int[] dx = { 0, -1, 0, 0 };
				int[] dy = { 0, 0, 0, 0 };
				addsection(cctv, dx, dy);
			} else if (d == 3) {
				int[] dx = { 0, 0, 0, 0 };
				int[] dy = { 0, 0, 1, 0 };
				addsection(cctv, dx, dy);
			}
		} else if (cctv.cctvnum == 2) {
			if (d == 0 || d == 2) {
				int[] dx = { 1, -1, 0, 0 };
				int[] dy = { 0, 0, 0, 0 };
				addsection(cctv, dx, dy);
			} else if (d == 1 || d == 3) {
				int[] dx = { 0, 0, 0, 0 };
				int[] dy = { 0, 0, 1, -1 };
				addsection(cctv, dx, dy);
			}
		} else if (cctv.cctvnum == 3) {
			if (d == 0) {
				int[] dx = { 1, 0, 0, 0 };
				int[] dy = { 0, 0, 0, 0 };
				addsection(cctv, dx, dy);
				int[] dx2 = { 0, 0, 0, 0 };
				int[] dy2 = { 0, 0, 1, 0 };
				addsection(cctv, dx2, dy2);
			} else if (d == 1) {
				int[] dx = { 1, 0, 0, 0 };
				int[] dy = { 0, 0, 0, 0 };
				addsection(cctv, dx, dy);
				int[] dx2 = { 0, 0, 0, 0 };
				int[] dy2 = { 0, 0, 0, -1 };
				addsection(cctv, dx2, dy2);
			} else if (d == 2) {
				int[] dx = { 0, -1, 0, 0 };
				int[] dy = { 0, 0, 0, 0 };
				addsection(cctv, dx, dy);
				int[] dx2 = { 0, 0, 0, 0 };
				int[] dy2 = { 0, 0, 0, -1 };
				addsection(cctv, dx2, dy2);
			} else if (d == 3) {
				int[] dx = { 0, -1, 0, 0 };
				int[] dy = { 0, 0, 0, 0 };
				addsection(cctv, dx, dy);
				int[] dx2 = { 0, 0, 0, 0 };
				int[] dy2 = { 0, 0, 1, 0 };
				addsection(cctv, dx2, dy2);
			}
		} else if (cctv.cctvnum == 4) {
			if (d == 0) {
				int[] dx = { 1, -1, 0, 0 };
				int[] dy = { 0, 0, 0, 0 };
				addsection(cctv, dx, dy);
				int[] dx2 = { 0, 0, 0, 0 };
				int[] dy2 = { 0, 0, 1, 0 };
				addsection(cctv, dx2, dy2);
			} else if (d == 1) {
				int[] dx = { 0, 0, 0, 0 };
				int[] dy = { 0, 0, 1, -1 };
				addsection(cctv, dx, dy);
				int[] dx2 = { 1, 0, 0, 0 };
				int[] dy2 = { 0, 0, 0, 0 };
				addsection(cctv, dx2, dy2);
			} else if (d == 2) {
				int[] dx = { 1, -1, 0, 0 };
				int[] dy = { 0, 0, 0, 0 };
				addsection(cctv, dx, dy);
				int[] dx2 = { 0, 0, 0, 0 };
				int[] dy2 = { 0, 0, 0, -1 };
				addsection(cctv, dx2, dy2);
			} else if (d == 3) {
				int[] dx = { 0, -1, 0, 0 };
				int[] dy = { 0, 0, 0, 0 };
				addsection(cctv, dx, dy);
				int[] dx2 = { 0, 0, 0, 0 };
				int[] dy2 = { 0, 0, 1, -1 };
				addsection(cctv, dx2, dy2);
			}
		} else if (cctv.cctvnum == 5) {
			int[] dx = { 1, -1, 0, 0 };
			int[] dy = { 0, 0, 0, 0 };
			addsection(cctv, dx, dy);
			int[] dx2 = { 0, 0, 0, 0 };
			int[] dy2 = { 0, 0, 1, -1 };
			addsection(cctv, dx2, dy2);
		}
	}

	// 정해진 방향 대로 감시구역을 -1로 지정
	static void addsection(CCTV cctv, int[] dx, int[] dy) {
		int i=cctv.r;
		int j= cctv.c;
		while(true) {
			if(copy[i][j] == 6 || i>=N || j >=M || i <0 || j < 0) break;
			for(int k=0; k<4; k++) {
				int ni = i + dy[k];
				int nj = j + dx[k];
			}
	}

//		copyvis = new boolean[N][M];
//		Queue<CCTV> q = new LinkedList<>();
//		q.add(new CCTV(cctv.cctvnum, cctv.r, cctv.c));
//		copyvis[cctv.r][cctv.c] = true;
//		while (!q.isEmpty()) {
//			CCTV temp = q.poll();
//			for (int i = 0; i < 4; i++) {
//				int rr = temp.r + dy[i];
//				int cc = temp.c + dx[i];
//				int cctvnum = temp.cctvnum;
//				 if(0<=rr && rr<N && 0<=cc && cc<M) {
//					 if((copyvis[rr][cc] == false && copy[rr][cc] != 6) ) {
//						 if(copy[rr][cc] == 0) copy[rr][cc] = -1;
//						 copyvis[rr][cc] = true;
//						 q.add(new CCTV(cctvnum, rr, cc));
//					 }
//				 }
//			}
//		}
//		countsection();
	}

	static void countsection() {
		System.out.println();

		int cnt = 0;
		for (int i = 0; i < N; i++) {
			for (int j = 0; j < M; j++) {
				if (copy[i][j] == 0)
					cnt++;
				System.out.printf(copy[i][j] + " ");
			}
			System.out.println();
		}
		min = Math.min(min, cnt);
	}

	public static class CCTV {
		int cctvnum, r, c;

		public CCTV(int cctvnum, int r, int c) {
			this.cctvnum = cctvnum;
			this.r = r;
			this.c = c;
		}
	}
}
//
//8 8 
//0 0 0 0 0 0 0 1
//0 0 0 0 0 0 1 0
//0 0 0 1 0 0 0 0
//0 0 0 0 0 1 0 0
//1 0 0 0 0 0 0 0
//0 0 1 0 0 0 0 0
//0 0 0 0 1 0 0 0
//0 1 0 0 0 0 0 0
