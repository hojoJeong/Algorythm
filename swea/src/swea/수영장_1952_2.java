package swea;

import java.util.*;
import java.io.*;

public class 수영장_1952_2 {
	static int[] ticket, plan;
	static int ans;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;

		int t = Integer.parseInt(br.readLine());
		for (int tc = 1; tc <= t; tc++) {
			ticket = new int[4];
			plan = new int[12];
			ans = Integer.MAX_VALUE;

			st = new StringTokenizer(br.readLine());
			for (int i = 0; i < 4; i++) {
				ticket[i] = Integer.parseInt(st.nextToken());
			}

			st = new StringTokenizer(br.readLine());
			for (int i = 0; i < 12; i++) {
				plan[i] = Integer.parseInt(st.nextToken());
			}

			dfs(0, 0);
			System.out.printf("#%d %d\n", tc, ans);
		}
	}

	static void dfs(int month, int pay) {
		if (month >= 12) {

			ans = Math.min(ans, pay);
			return;
		}

		// 이용 안할 떄
		if (plan[month] == 0) {
			dfs(month + 1, pay);
		} else {
			// 1일 이용권
			dfs(month + 1, pay + ticket[0] * plan[month]);

			// 1개월 이용권
			dfs(month + 1, pay + ticket[1]);

			// 3개월 이용권
			dfs(month + 3, pay + ticket[2]);

			// 1년 이용권
			if (month == 0) {
				dfs(month + 12, pay + ticket[3]);
			}
		}
	}
}
