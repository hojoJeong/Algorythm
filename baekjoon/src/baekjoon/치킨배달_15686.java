package baekjoon;

import java.util.*;
import java.io.*;

public class 치킨배달_15686 {

	static int[] dx = { 1, -1, 0, 0 };
	static int[] dy = { 0, 0, 1, -1 };
	static int[][] map, chickeninfo; // 치킨집 좌표
	static int[] chicken; // 조합 뽑은 치킨집
	static boolean[][] vis, check; // bfs에서 사용할 vis, 조합 뽑은 치킨집은 true로 해줄 배열
	static boolean[] cvis; // 조합에서 사용
	static int ans, res, length, N, m, n;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());

		N = Integer.parseInt(st.nextToken());
		m = Integer.parseInt(st.nextToken());

		map = new int[N][N];
		ans = Integer.MAX_VALUE;
		n = 0;
		for (int i = 0; i < N; i++) {
			st = new StringTokenizer(br.readLine());
			for (int j = 0; j < N; j++) {
				map[i][j] = Integer.parseInt(st.nextToken());
				if (map[i][j] == 2) {
					n++;
				}
			}
		}

		chicken = new int[m];
		chickeninfo = new int[n][2];
		// 치킨집 좌표 구해줌
		int index = 0;
		for (int i = 0; i < N; i++) {
			for (int j = 0; j < N; j++) {
				if (map[i][j] == 2) {
					chickeninfo[index][0] = i;
					chickeninfo[index][1] = j;
					index++;
				}
			}
		}

		cvis = new boolean[n];
		combination(0, 0, n, m);

		System.out.println(ans);
	}

	// 치킨집 m개 조합으로 뽑음
	static void combination(int index, int start, int n, int m) {

		if (index == m) {
			res = 0;
			int len = 0;
			for (int i = 0; i < N; i++) {
				for (int j = 0; j < N; j++) {
					if (map[i][j] == 1) {
						length = Integer.MAX_VALUE;	//초기화 위치 잘보자
						for (int k = 0; k < m; k++) {
							len = Math.abs(chickeninfo[chicken[k]][0] - i) + Math.abs(chickeninfo[chicken[k]][1] - j);
							length = Math.min(len, length);
						}
						res += length;
					}
				}
			}
			ans = Math.min(ans, res);
			return;
		}
		for (int i = start; i < n; i++) {
			if (cvis[i] == false) {
				chicken[index] = i;
				cvis[i] = true;
				combination(index + 1, i + 1, n, m);
				cvis[i] = false;
			}
		}
	}
}
