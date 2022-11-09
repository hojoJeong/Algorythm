package swea;

import java.util.*;
import java.io.*;

public class ����_A�������׽�Ʈ {
	static int[][] map;
	static ArrayList<Integer> arr; // map�Է� �޴ٰ� ����, �� ������ �־���
	static int[] per; // ����, �� ���� ����(��, ���� �� �� ���� ���Ͱ� ���;���)
	static int[][] perinfo; // ���� ���� ����, �� ��ǥ
	static boolean[] pvis; // ���� ���� �� ���
	static int ans, N;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;

		int t = Integer.parseInt(br.readLine());
		for (int tc = 1; tc <= t; tc++) {
			N = Integer.parseInt(br.readLine());
			arr = new ArrayList<>();
			map = new int[N][N];
			ans = Integer.MAX_VALUE;

			for (int i = 0; i < N; i++) {
				st = new StringTokenizer(br.readLine());
				for (int j = 0; j < N; j++) {
					map[i][j] = Integer.parseInt(st.nextToken());
					if (map[i][j] != 0) {
						arr.add(map[i][j]);
					}
				}
			}

			per = new int[arr.size()];
			perinfo = new int[arr.size()][2];
			pvis = new boolean[arr.size()];

			permutation(0, arr.size(), arr.size());
			for (int i = 0; i < per.length; i++)
				System.out.print(per[i] + " ");
			System.out.println();
			System.out.printf("#%d %d\n", tc, ans);
		}
	}

	// arr���� �湮�� ���� �������, ���� ������� �ʰ� �ϴ� �׳� �� ����
	static void permutation(int index, int n, int m) {
		if (index == m) {
			check();
			return;
		}
		for (int i = 0; i < n; i++) {
			if (pvis[i] == false) {
				per[index] = arr.get(i);
				pvis[i] = true;
				permutation(index + 1, n, m);
				pvis[i] = false;
			}
		}
	}

	//���� ������ ���ǿ� �����ϴ��� Ȯ��, �����ϸ� ���� �ܰ�� �Ѿ
	static void check() {
		Loop1: for (int i = 0; i < per.length; i++) {
			int check = 0;
			if(per[0] < 0) break Loop1;	//ó������ �� �湮�̸� �Լ� ����(���� �ܰ� ����)
			if (per[i] < 0) {	//per[i]�� ������ ��(���϶�)
				for (int j = 0; j < i; j++) {
					if (per[j] == Math.abs(per[i])) {	//�տ��� ���� ���� �߿� ���� ���Ͱ� �ִ��� Ȯ��
						check = 1;
					}
				}
				if (check == 0)	//������  �Լ� ����(���� �ܰ�� ����)
					break Loop1;
			}
			if (i == per.length - 1)
				permutationinfo();
		}
	}

	// ���� ���� ����, ���� ��ǥ ����
	static void permutationinfo() {
		for (int k = 0; k < per.length; k++) {
			Loop1: for (int i = 0; i < N; i++) {
				for (int j = 0; j < N; j++) {
					if (per[k] == map[i][j]) {
						perinfo[k][0] = i;
						perinfo[k][1] = j;
						break Loop1; // ��ǥ ã������ �� ã������ �ٷ� ���� per�� ��ǥ ã����
					}
				}
			}
		}
		callength();
	}

	static void callength() {
		int len = 0;
		len = perinfo[0][0] + perinfo[0][1];
		for (int i = 0; i < per.length - 1; i++) {
			len += Math.abs(perinfo[i][0] - perinfo[i + 1][0]) + Math.abs(perinfo[i][1] - perinfo[i + 1][1]);
		}
		ans = Math.min(ans, len);
	}

}
