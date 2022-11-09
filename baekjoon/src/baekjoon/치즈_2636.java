package baekjoon;

import java.util.*;
import java.io.*;

public class ġ��_2636 {

	static int[] dx = { 1, -1, 0, 0 };
	static int[] dy = { 0, 0, 1, -1 };
	static int n, m, cnt, hour;
	static int[][] arr;
	static boolean[][] vis;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		n = Integer.parseInt(st.nextToken());
		m = Integer.parseInt(st.nextToken());

		arr = new int[n][m];

		// �迭 �Է�
		for (int i = 0; i < n; i++) {
			st = new StringTokenizer(br.readLine());
			for (int j = 0; j < m; j++) {
				arr[i][j] = Integer.parseInt(st.nextToken());
			}
		}

		while (check()) {
			vis = new boolean[n][m];
			cnt = 0;
			hour++;
			findedge(0, 0); // ���� �߿� �´��� �κ��� 0�� ����
		}
		System.out.printf(hour +"\n"+ cnt);
		

	}

	
	static boolean check() {
		for (int i = 0; i < n; i++) {
			for (int j = 0; j < m; j++) {
				if (arr[i][j] == 1) {
					return true;
				}
			}
		}
		return false;
	}

	//dfs�� ���������� �� if������ �����൵ ��. for�� �ȿ��� ���ǹ��� �ش��� �� dfs�� ��ͷ� ȣ���ϰ� �ƴϸ� �׳� ���������� ������ �����ص� �����
	static void findedge(int r, int c) {
		for (int i = 0; i < 4; i++) {
			int rr = r + dy[i];
			int cc = c + dx[i];
			if (0 <= rr && rr < n && 0 <= cc && cc < m) {
				if (vis[rr][cc] == false) {
					vis[rr][cc] = true;
					if (arr[rr][cc] == 1) {
						cnt++;
						arr[rr][cc] = 0; // ���� �߿� ���� ĭ�� 2�� �������ְ� �� �̻� ������ ���� �ʵ��� dfs�� �����Ŵ
					} else {
						findedge(rr, cc);
					}
				}
			}
		}
	}
}
