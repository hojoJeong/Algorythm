package baekjoon;

import java.util.*;
import java.io.*;

public class ��_3190 {

	static int[] dx = { 0, 1, 0, -1 };
	static int[] dy = { -1, 0, 1, 0 };
	static ArrayList<Point> snake, copy;
	static ArrayList<Dir> direction;
	static int[][] map;
	static int n, k, l, ans;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;

		n = Integer.parseInt(br.readLine());
		k = Integer.parseInt(br.readLine());
		map = new int[n][n];
		snake = new ArrayList<>();
		copy = new ArrayList<>();
		direction = new ArrayList<>();
		snake.add(new Point(0, 0));

		// ��� �Է�
		for (int i = 0; i < k; i++) {
			st = new StringTokenizer(br.readLine());
			int r = Integer.parseInt(st.nextToken());
			int c = Integer.parseInt(st.nextToken());
			map[r - 1][c - 1] = 1;
		}

		// ���� ��ȯ list ����
		l = Integer.parseInt(br.readLine());
		for (int i = 0; i < l; i++) {
			st = new StringTokenizer(br.readLine());
			int time = Integer.parseInt(st.nextToken());
			char dir = st.nextToken().charAt(0);
			direction.add(new Dir(time, dir));
		}

		int time = 0;
		int dir = 1;
		// ���ѽð� 1���̹Ƿ� DFS ���x
		while (true) {
			time++;
			Point nextHead = new Point(snake.get(0).r + dy[dir], snake.get(0).c + dx[dir]);
			boolean check = checkDone(nextHead.r, nextHead.c);
			if (check == false) {
				// ���� ĭ�� ����� ������ nextHead�� 0�� �ε����� �ְ�, ���� �迭�� �� �ڿ� �̾���(���� +1)
				copy.clear();
				copy.addAll(snake);
				snake.clear();
				snake.add(nextHead);
				snake.addAll(copy);
				if (map[nextHead.r][nextHead.c] == 1) {
					map[nextHead.r][nextHead.c] = 0;
				} else if (map[nextHead.r][nextHead.c] != 1) { // ��� ������ �� �� remove(�̵� �� ���� �״��)
					snake.remove(snake.size() - 1);
				}

				// ���� ��ȯ�� ������ üũ�ϰ� ������ ���� ��ȯ
				if (direction.size() > 0 && direction.get(0).time == time) { // �ð� ������� �־����Ƿ� 0�� �ε��� �����ϰ� remove
					if (direction.get(0).dir == 'D') {
						dir++;
						if (dir >= 4)
							dir = 0;
					} else if (direction.get(0).dir == 'L') {
						dir--;
						if (dir < 0)
							dir = 3;
					}
					direction.remove(0);
				}
			} else if (check == true) {
				break;
			}
		}

		System.out.println(time);
	}

	// ���� �������� �˻�
	static boolean checkDone(int r, int c) {
		if (r < 0 || r >= n || c < 0 || c >= n || checkTouchMyBody(r, c)) {
			return true;
		} else
			return false;
	}

	// �ڱ� �� �ε������� �˻�
	static boolean checkTouchMyBody(int r, int c) {
		for (int i = 0; i < snake.size(); i++) {
			if (snake.get(i).r == r && snake.get(i).c == c) {
				return true;
			}
		}
		//contains()�� ��ü ���� ���θ� ���ϱ� ���ؼ��� ��ü�� equals()�� �����ؾ���
//		Point temp = new Point(r, c);
//		if (snake.contains(temp))
//			return true;
//		else
		return false;
	}

	public static class Point {
		int r, c;

		public Point(int r, int c) {
			this.r = r;
			this.c = c;
		}
		
	}

	public static class Dir {
		int time;
		char dir;

		public Dir(int time, char dir) {
			this.time = time;
			this.dir = dir;
		}
	}
}
