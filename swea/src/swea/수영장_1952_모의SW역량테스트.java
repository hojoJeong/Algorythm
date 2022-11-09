package swea;

import java.io.*;
import java.util.*;

public class ������_1952_����SW�����׽�Ʈ {

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

		// �̿� ���ϴ� �޿��� ���� x
		if (plan[month] == 0)
			dfs(month + 1, total);

		// 1�� �������� ���� -> 1�� �̻��� ���ű��� �����ϸ� 1�� ���ű��� ������ �ʿ䰡 �����Ƿ� ���� ��������
		if (plan[month] > 0)
			dfs(month + 1, total + plan[month] * cost[0]);

		// 1�� �������� ����
		dfs(month + 1, total + cost[1]);

		// 3�� �������� ����(11, 12�� ���� ����)
		if (month <= 10)
			dfs(month + 3, total + cost[2]);
	}
}
