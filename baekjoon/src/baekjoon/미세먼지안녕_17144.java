package baekjoon;

import java.util.*;
import java.io.*;

public class �̼������ȳ�_17144 {
	static int[] dx = { 0, 0, -1, 1 };
	static int[] dy = { -1, 1, 0, 0 };
	static Map[][] map;
	static boolean[][] vis;
	static int[] machine;
	static int R, C, T, ans;

	public static void main(String[] args) throws IOException {

		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());

		R = Integer.parseInt(st.nextToken());
		C = Integer.parseInt(st.nextToken());
		T = Integer.parseInt(st.nextToken());

		map = new Map[R][C];
		vis = new boolean[R][C];
		machine = new int[2];
		ans = 0;

		int idx = 0;
		for (int i = 0; i < R; i++) {
			st = new StringTokenizer(br.readLine());
			for (int j = 0; j < C; j++) {
				int temp = Integer.parseInt(st.nextToken());
				map[i][j] = new Map(temp, 0);
				if (temp == -1)
					machine[idx++] = i;
			}
		}
		diffusion(0);
		
		System.out.println(ans);
	}

	// Ȯ�� ������
	static void diffusion(int time) {
		if (time == T) {
			ans = sum();
			return;
		}

		for (int i = 0; i < R; i++) {
			for (int j = 0; j < C; j++) {
				int cnt = 0;
				if (map[i][j].value != -1 && map[i][j].value != 0) {
					for (int k = 0; k < 4; k++) {
						int rr = i + dy[k];
						int cc = j + dx[k];
						if (0 <= rr && rr < R && 0 <= cc && cc < C && map[rr][cc].value != -1) {
							map[rr][cc].valueTemp += map[i][j].value / 5;
							cnt++; // Ȯ������� ������ cnt++�ؼ� ���� �� ���� �� �����
						}
					}
					map[i][j].value = map[i][j].value - (map[i][j].value / 5) * cnt;
				}
			}
		}
		
		// Ȯ�� �� �ӽ÷� ��Ƴ��� ���� value�� �� ������
		for (int i = 0; i < R; i++) {
			for (int j = 0; j < C; j++) {
				map[i][j].value += map[i][j].valueTemp;
				map[i][j].valueTemp = 0;
			}
		}
		move(time);
	}

	// ����û���� �ٶ� ���� �̵�
	static void move(int time) {
		// �� ĭ�� ����û����� ������ �ʺ��� ����
		int r = machine[0] - 1;
		int c = 0;
		for (int i = r; i > 0; i--) {
			map[i][c].value = map[i - 1][c].value;
		}
		r = 0;
		for (int i = 0; i < C - 1; i++) {
			map[r][i].value = map[r][i + 1].value;
		}
		c = C - 1;
		for (int i = 0; i < machine[0]; i++) {
			map[i][c].value = map[i + 1][c].value;
		}
		r = machine[0];
		for (int i = C - 1; i > 1; i--) { // 0�� ���� ����û���� �����Ƿ� ���ǽ� ����
			map[r][i].value = map[r][i - 1].value;
		}

		// �Ʒ�ĭ ����
		r = machine[1] + 1;
		c = 0;
		for (int i = r; i < R - 1; i++) {
			map[i][c].value = map[i + 1][c].value;
		}
		r = R - 1;
		for (int i = 0; i < C - 1; i++) {
			map[r][i].value = map[r][i + 1].value;
		}
		c = C - 1;
		for (int i = R - 1; i > machine[1]; i--) {
			map[i][c].value = map[i - 1][c].value;
		}
		r = machine[1];
		for (int i = C - 1; i > 1; i--) {
			map[r][i].value = map[r][i - 1].value;
		}
		map[machine[0]][1].value = 0;
		map[machine[1]][1].value = 0;
		
		diffusion(time+1);
	}

	static int sum() {
		int result = 0;
		for (int i = 0; i < R; i++) {
			for (int j = 0; j < C; j++) {
				if (map[i][j].value != 0 && map[i][j].value != -1) {
					result += map[i][j].value;
				}
			}
		}

		return result;
	}

	public static class Map {
		int value, valueTemp;

		public Map(int value, int valueTemp) {
			this.value = value;
			this.valueTemp = valueTemp;	//Ȯ�� �� ������ �ӽ÷� ����� ����(���ÿ� Ȯ��ǹǷ� ��ȸ�� �� ���� ���� ����Ǹ� �ȵ�)
		}
	}

}
