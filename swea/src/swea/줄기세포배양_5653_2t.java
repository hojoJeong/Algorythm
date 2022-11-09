package swea;

import java.io.*;
import java.util.*;

import swea.�ٱ⼼�����_5653.Cell;

public class �ٱ⼼�����_5653_2t {
	static int[] dx = { 0, 0, -1, 1 };
	static int[] dy = { -1, 1, 0, 0 };
	static int[][] map; // ��� �¾ ���� : life, Ȱ��ȭ : -1, ��Ȱ��ȭ : -2, ���� : -3, ��ĭ : 0
	static int ans, n, m, k, cnt;
	static ArrayList<Cell> cell;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		cell = new ArrayList<>(); // testcase ���� ������ clear ���༭ �ʱ�ȭ �ּ�ȭ

		int t = Integer.parseInt(br.readLine());

		for (int tc = 1; tc <= t; tc++) {
			st = new StringTokenizer(br.readLine());
			n = Integer.parseInt(st.nextToken());
			m = Integer.parseInt(st.nextToken());
			k = Integer.parseInt(st.nextToken());
			ans = 0;
			map = new int[650][650];
			for (int i = 0; i < n; i++) {
				st = new StringTokenizer(br.readLine());
				for (int j = 0; j < m; j++) {
					int life = Integer.parseInt(st.nextToken());
					if (life != 0) {
						cell.add(new Cell(i, j, life, 0, 0, -1));
						map[150 + i][150 + j] = life;
					}
				}
			}

			start(0);

			for(int i=0; i<650; i++) {
				for(int j=0; j<650; j++) {
					if(map[i][j] > 0) ans++;
				}
			}

			System.out.printf("#%d %d\n", tc, ans);
			cell.clear();
		}
	}

	static void start(int time) {
		if (time == k + 1) {
			return;
		}
		for (int i = 0; i < cell.size(); i++) {
			if (cell.get(i).activate == 2)
				continue; // ���� �ֵ��̸� üũ ����
			int life = cell.get(i).life; // X�ð�
			// Ȱ��ȭ�� �� ���� �� �ð� �� ������ ���Ľ�����
			if (map[150+cell.get(i).r][150+cell.get(i).c] == -1) {
				if (cell.get(i).activate == 1 && time - cell.get(i).acttime == 1) {

					for (int j = 0; j < 4; j++) {
						int rr = 150+cell.get(i).r + dy[j];
						int cc = 150+cell.get(i).c + dx[j];
						// �����Ϸ��� �ڸ��� �ƹ��͵� ���� ��
						if (map[rr][cc] == 0) {
							cell.add(new Cell(rr-150, cc-150, life, 0, time, -1));
						}
						// �����Ϸ��� �ڸ��� ���� �ִµ� �װ� �̹� �¾�� ������ �ƴϰ�(���ÿ� �¾) life�� �� ���� ��
						else if (map[rr][cc] > 0 && map[rr][cc] < life) {
							map[rr][cc] = life;
							cell.add(new Cell(rr-150, cc-150, life, 0, time, -1));
						}

						Cell ctemp = cell.get(i); // �ʱ�ȭ �ؾ��ϴϱ� �ƹ��ų� �־���
					}

				}
			}

			// Ȱ��ȭ �� ��X�ð� ���� ������ ��Ȱ��ȭ ������
			if (cell.get(i).activate == 1 && time - cell.get(i).acttime == life) {
				cell.get(i).activate = 2;
				map[cell.get(i).r][cell.get(i).c] = -2;
//				cell.remove(i);

			}
			// �¾�� X�ð� ���� ������ Ȱ��ȭ ������
			if (cell.get(i).activate == 0 && time - cell.get(i).borntime == life) {
				cell.get(i).activate = 1;
				cell.get(i).acttime = time;
				map[cell.get(i).r][cell.get(i).c] = -1;
			}
			
			if(cell.get(i).activate == 0 && time == cell.get(i).borntime + 2*life) {
				cell.get(i).activate = 2;
				map[cell.get(i).r][cell.get(i).c] = -3;
//				cell.remove(i);
			}
		}

		start(time + 1);
	}

//	static void kill() {
//		for(int i=0; i<cell.size(); i++) {
//			if(cell.get(i).activate == 2) {
//				int[] xx = new int[4];
//				int[] yy = new int[4];
//				int cnt = 0;
//				for(int j=0; j<4; j++) {
//					xx[j] = cell.get(i).xy[0] + dx[i];
//					yy[j] = cell.get(i).xy[1] + dy[i];
//					if(cell.get(cell.indexOf(xx[j])).activate ==2 && cell.get(cell.indexOf(yy[j])).activate ==2) {
//						cnt++;
//					}
//				}
//				if(cnt == 4) {
//					cell.remove(i);
//				}
//				
//			}
//		}
//	}
	public static class Cell {
		int r, c, life, activate, borntime, acttime;

		public Cell(int r, int c, int life, int activate, int borntime, int acttime) {
			this.r = r;
			this.c = c;
			this.life = life; // �����
			this.activate = activate; // 0:��Ȱ��ȭ, 1:Ȱ��ȭ, 2:����
			this.borntime = borntime; // �¾ �ð� -> X�ð� ��� �� ���� �̹� �ִ� ��������, ���� �ִ� �������� Ȯ��
			this.acttime = acttime; // Ȱ��ȭ ���۵� �ð�
		}
	}
}