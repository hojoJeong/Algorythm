package swea;

import java.io.*;
import java.util.*;

public class 수영장_1952_모의SW역량테스트 {

	static int[] cost;
	static int[] plan;
	static int ans;

	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = null;

		int T = Integer.parseInt(br.readLine());

		for (int tc = 1; tc <= T; ++tc) {
			cost = new int[4];
			plan = new int[13];

			st = new StringTokenizer(br.readLine());
			for (int i = 0; i < 4; ++i)
				cost[i] = Integer.parseInt(st.nextToken());

			st = new StringTokenizer(br.readLine());
			for (int i = 1; i <= 12; ++i)
				plan[i] = Integer.parseInt(st.nextToken());

			ans = cost[3];
			dfs(1, 0);

			System.out.printf("#%d %d\n", tc, ans);
		}
		System.out.println("check");
	}

	private static void dfs(int month, int total) {
		if (month == 13) {
			if(ans > total) ans = total;
			return;
		}

		// 이용 안하는 달에는 구매 x
		if (plan[month] == 0)
			dfs(month + 1, total);

		// 1일 사용권으로 구매 -> 1달 이상의 구매권을 구매하면 1일 구매권을 구매할 필요가 없으므로 전부 구매해줌
		if (plan[month] > 0)
			dfs(month + 1, total + plan[month] * cost[0]);

		// 1달 사용권으로 구매
		dfs(month + 1, total + cost[1]);

		// 3달 사용권으로 구매(11, 12월 구매 제한)
		if (month <= 10)
			dfs(month + 3, total + cost[2]);
	}
}
