package baekjoon;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.IOException;
import java.util.StringTokenizer;

public class 스타트와링크_14889 {

	static int[][] arr;
	static boolean[] vis;
	static int N;
	static int ans  = Integer.MAX_VALUE;;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		N = Integer.parseInt(br.readLine());
		
		arr = new int[N][N];
		vis = new boolean[N];

		for (int i = 0; i < N; i++) {
			StringTokenizer st = new StringTokenizer(br.readLine(), " ");
			for (int j = 0; j < N; j++) {
				arr[i][j] = Integer.parseInt(st.nextToken());
			}
		}

		combination(0, 0);
		System.out.println(ans);
	}

	static void combination(int index, int cnt) {
		if (cnt== N/2) {
			cal();
			return;
		}

		// 조합으로 팀 나눠줌
		for (int i = index; i < N; i++) {
			if (vis[i] == false) {
				vis[i] = true;
				combination(i+ 1, cnt+1);
				vis[i] = false;
			}
			
		}
	}

	static void cal() {
		int startteam=0; // 초기화 시점 신경쓸것!!!!!
		int linkteam=0;

		for (int i = 0; i < N - 1; i++) { // 순열을 따로 만들어주지 않고 팀이 배정되면 그 안에서 for문으로 해결
			for (int j = i + 1; j < N; j++) {
				if (vis[i] == true && vis[j] == true) { // vis == ture면 startteam, false면 linkteam으로 배정
					startteam += arr[i][j];
					startteam += arr[j][i];
				} else if (vis[i] == false && vis[j] == false) {
					linkteam += arr[i][j];
					linkteam += arr[j][i];
				}
			}
		}
		int result = Math.abs(startteam - linkteam);

		if (result == 0) {
			System.out.println(result);
			System.exit(0);
		}
		ans = Math.min(ans, result);
	}
}
