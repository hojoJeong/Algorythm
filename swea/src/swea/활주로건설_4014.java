import java.util.*;
import java.io.*;

public class 활주로건설_4104 {
	static int[][] map;
	static int N, X, ans;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;

		int t = Integer.parseInt(br.readLine());
		for (int tc = 1; tc <= t; tc++) {
			st = new StringTokenizer(br.readLine());
			N = Integer.parseInt(st.nextToken());
			X = Integer.parseInt(st.nextToken());

			map = new int[N][N];
			ans = 0;

			for (int i = 0; i < N; i++) {
				st = new StringTokenizer(br.readLine());
				for (int j = 0; j < N; j++) {
					map[i][j] = Integer.parseInt(st.nextToken());
				}
			}

			for (int i = 0; i < N; i++) {
				if (checkRow(i)) {
					ans++;
				}
				if (checkCol(i)) {
					ans++;
				}
			}

			System.out.printf("#%d %d\n", tc, ans);
		}
	}

	// 배열의 원소들이 X만큼 연속되는지 검사
	static boolean checkRow(int r) {

		int cnt = 1;
		boolean down = false;
		for (int i = 1; i < N; i++) {

			if (map[r][i - 1] == map[r][i]) { // 다음 칸과 높이가 같을 때
				cnt++;
			} else {
				if (Math.abs(map[r][i - 1] - map[r][i]) > 1) {
					return false;
				}

				if (map[r][i - 1] < map[r][i]) {// 오르막일 때
					if (down && cnt < 2 * X) { // 이전이 내리막이었을 때
						return false;
					} else if (!down && cnt < X) { // 이전이 오르막이었을 때
						return false;
					}
					cnt = 1;
					down = false;
				} else if (map[r][i - 1] > map[r][i]) {
					if (down && cnt < X) { // 이전이 내리막이었을 때
						return false;
					}
					cnt = 1;
					down = true;
				}
			}
			if (i == N - 1 && down) {
				if(cnt < X) {
					return false;
				}
			}
		}
		return true;
	}

	static boolean checkCol(int c) {

		int cnt = 1;
		boolean down = false;
		for (int i = 1; i < N; i++) {
			if (map[i - 1][c] == map[i][c]) { // 다음 칸과 높이가 같을 때
				cnt++;
			} else {
				if (Math.abs(map[i - 1][c] - map[i][c]) > 1) {
					return false;
				}

				if (map[i - 1][c] < map[i][c]) {// 오르막일 때
					if (down && cnt < 2 * X) { // 이전이 내리막이었을 때
						return false;
					} else if (!down && cnt < X) { // 이전이 오르막이었을 때
						return false;
					}
					cnt = 1;
					down = false;

				} else if (map[i - 1][c] > map[i][c]) {
					if (down && cnt < X) { // 이전이 내리막이었을 때
						return false;
					}
					cnt = 1;
					down = true;
				}
			}
			if (i == N - 1 && down) {
				if(cnt < X) {
					return false;
				}
			}
		}
		return true;
	}
}
