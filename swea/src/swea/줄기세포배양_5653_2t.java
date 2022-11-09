package swea;

import java.io.*;
import java.util.*;

import swea.줄기세포배양_5653.Cell;

public class 줄기세포배양_5653_2t {
	static int[] dx = { 0, 0, -1, 1 };
	static int[] dy = { -1, 1, 0, 0 };
	static int[][] map; // 방금 태어난 세포 : life, 활성화 : -1, 비활성화 : -2, 죽음 : -3, 빈칸 : 0
	static int ans, n, m, k, cnt;
	static ArrayList<Cell> cell;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		cell = new ArrayList<>(); // testcase 끝날 때마다 clear 해줘서 초기화 최소화

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
				continue; // 죽은 애들이면 체크 안함
			int life = cell.get(i).life; // X시간
			// 활성화된 지 최초 한 시간 된 세포들 번식시켜줌
			if (map[150+cell.get(i).r][150+cell.get(i).c] == -1) {
				if (cell.get(i).activate == 1 && time - cell.get(i).acttime == 1) {

					for (int j = 0; j < 4; j++) {
						int rr = 150+cell.get(i).r + dy[j];
						int cc = 150+cell.get(i).c + dx[j];
						// 번식하려는 자리에 아무것도 없을 때
						if (map[rr][cc] == 0) {
							cell.add(new Cell(rr-150, cc-150, life, 0, time, -1));
						}
						// 번식하려는 자리에 뭔가 있는데 그게 이미 태어났던 세포가 아니고(동시에 태어남) life가 더 작을 때
						else if (map[rr][cc] > 0 && map[rr][cc] < life) {
							map[rr][cc] = life;
							cell.add(new Cell(rr-150, cc-150, life, 0, time, -1));
						}

						Cell ctemp = cell.get(i); // 초기화 해야하니까 아무거나 넣어줌
					}

				}
			}

			// 활성화 된 지X시간 지난 세포들 비활성화 시켜줌
			if (cell.get(i).activate == 1 && time - cell.get(i).acttime == life) {
				cell.get(i).activate = 2;
				map[cell.get(i).r][cell.get(i).c] = -2;
//				cell.remove(i);

			}
			// 태어난지 X시간 지난 세포들 활성화 시켜줌
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
			this.life = life; // 생명력
			this.activate = activate; // 0:비활성화, 1:활성화, 2:죽음
			this.borntime = borntime; // 태어난 시간 -> X시간 계산 할 때와 이미 있던 세포인지, 원래 있던 세포인지 확인
			this.acttime = acttime; // 활성화 시작된 시간
		}
	}
}