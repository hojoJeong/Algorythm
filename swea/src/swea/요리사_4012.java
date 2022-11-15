import java.util.*;
import java.io.*;

public class 요리사_4012 {
	static int[][] ingre;
	static int N, ans;
	static boolean[] vis;
	static int[] ingreA, ingreB;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;

		int t = Integer.parseInt(br.readLine());
		for (int tc = 1; tc <= t; tc++) {
			N = Integer.parseInt(br.readLine());

			ingre = new int[N][N];
			ingreA = new int[N / 2];
			ingreB = new int[N / 2];
			vis = new boolean[N];
			ans = Integer.MAX_VALUE;

			for (int i = 0; i < N; i++) {
				st = new StringTokenizer(br.readLine());
				for (int j = 0; j < N; j++) {
					ingre[i][j] = Integer.parseInt(st.nextToken());
				}
			}

			combination(0, 0, N, N / 2);
			System.out.printf("#%d %d\n", tc, ans);

		}
	}

	static void combination(int index, int start, int n, int m) {
		if (index == m) {
			int idxA = 0;
			int idxB = 0;
			for (int i = 0; i < vis.length; i++) {
				if (vis[i]) {
					ingreA[idxA] = i;
					idxA++;
				} else {
					ingreB[idxB] = i;
					idxB++;
				}
			}

			check();
			return;
		}

		for (int i = start; i < n; i++) {
			if (!vis[i]) {
				vis[i] = true;
				combination(index + 1, i + 1, n, m);
				vis[i] = false;
			}

		}

	}

	static void check() {
		int sumA = 0;
		int sumB = 0;
		for (int i = 0; i < ingreA.length; i++) {
			for (int j = 0; j < ingreA.length; j++) {
				if (i != j) {
					sumA += ingre[ingreA[i]][ingreA[j]];
					sumB += ingre[ingreB[i]][ingreB[j]];

				}
			}
		}

		ans = Math.min(ans, Math.abs(sumA - sumB));
	}
}
