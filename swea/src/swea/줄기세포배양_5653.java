package swea;

import java.util.*;
import java.io.*;

public class �ٱ⼼�����_5653 {
	static int[] dx = { 0, 0, -1, 1 };
	static int[] dy = { 1, -1, 0, 0 };
	static int[][] map;
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
			map = new int[350][350];
			for (int i = 0; i < n; i++) {
				st = new StringTokenizer(br.readLine());
				for (int j = 0; j < m; j++) {
					int life = Integer.parseInt(st.nextToken());
					if (life != 0) {
						cell.add(new Cell(j, n-i-1, life, 0, 0, -1));
					}
				}
			}

			start(0);
			
			for(Cell c : cell) {
				if(c.activate == 2) continue;
				if(c.activate == 1 || c.activate == 0) {
					ans++;
				}
			}

			System.out.printf("#%d %d\n", tc, ans);
			System.out.println(cnt);
			cell.clear();
		}
	}

	static void start(int time) {
		if(time == k+1) {
			return;
		}
		
		for (int i = 0; i < cell.size(); i++) {
			cnt++;
			int life = cell.get(i).life; // X�ð�
			// Ȱ��ȭ�� �� ���� �� �ð� �� ������ ���Ľ�����
			if (cell.get(i).activate == 1 && time - cell.get(i).acttime == 1) {
				for (int j = 0; j < 4; j++) {
					int xx = cell.get(i).xy[0] + dx[j];
					int yy = cell.get(i).xy[1] + dy[j];
					boolean check = false;

						
					Cell ctemp = cell.get(i); // �ʱ�ȭ �ؾ��ϴϱ� �ƹ��ų� �־���

					for (Cell c : cell) {
						if (c.xy[0] == xx && c.xy[1] == yy) {
							check = true;
							ctemp = c; // �����Ϸ��� �ڸ��� �ִ� ����
						}
						if(check) { // �����Ϸ��� �ڸ��� ���� �ְ�, �װ� ���ÿ� �¾ �����ε� life�� ������
							if (ctemp.borntime == time && life > ctemp.life) {
								cell.remove(ctemp); //
								cell.add(new Cell(xx, yy, life, 0, time, -1));
							}
							break;
						}
					}
					if (!check) { // �����Ϸ��� �ڸ��� �ƹ��͵� ������
						cell.add(new Cell(xx, yy, life, 0, time, -1));
					} 
				}
				
				
			}

			// Ȱ��ȭ �� ��X�ð� ���� ������ ��Ȱ��ȭ ������
			if (cell.get(i).activate == 1 && time - cell.get(i).acttime == life) {
				cell.get(i).activate = 2;
				
			}
			// �¾�� X�ð� ���� ������ Ȱ��ȭ ������
			if (cell.get(i).activate == 0 && time - cell.get(i).borntime == life) {
				cell.get(i).activate = 1;
				cell.get(i).acttime = time;
			}
		}
		
		start(time+1);
	}

	public static class Cell {
		int[] xy;
		int life, activate, borntime, acttime;

		public Cell(int x, int y, int life, int activate, int borntime, int acttime) {
			this.xy = new int[2];
			this.xy[0] = x;
			this.xy[1] = y;
			this.life = life; // �����
			this.activate = activate; // 0:��Ȱ��ȭ, 1:Ȱ��ȭ, 2:����
			this.borntime = borntime; // �¾ �ð� -> X�ð� ��� �� ���� �̹� �ִ� ��������, ���� �ִ� �������� Ȯ��
			this.acttime = acttime; // Ȱ��ȭ ���۵� �ð�
		}
	}
}
