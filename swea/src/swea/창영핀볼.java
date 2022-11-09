package swea;

import java.util.*;
import java.io.*;

public class â���ɺ� {

	// https://swexpertacademy.com/main/code/problem/problemDetail.do?contestProbId=AWXRF8s6ezEDFAUo
	// -1 : ��Ȧ, 0 : �����, 1 ~ 5 : ���, 6 ~ 10 : ��Ȧ
	// ���ӿ��� ���� �� �ִ� �ִ��� ���϶�.
	// ���, ��Ȧ, ��Ȧ ��ġ������ ��� �� �� ����.
	// ������ ���� �ε��� Ƚ���� �ȴ�.
	// ������ �ɺ��� �����ġ�� ���ƿ��ų�, ��Ȧ�� ���� �� ������ �ȴ�.

	// ��Ȧ�� ������ �Ǹ�, ������ ���ڸ� ���� �ݴ��� ��Ȧ�� ������ �Ǹ� ��������� �״�� �����ȴ�.
	// 5���� ������ �ڷ� ���ư�

	static int N, result;
	static int[][] map;

	static int[] dirX = { -1, 1, 0, 0 }; // �� �� �� ��
	static int[] dirY = { 0, 0, -1, 1 };

	static class Coordinates {
		int x;
		int y;

		public Coordinates(int x, int y) {
			this.x = x;
			this.y = y;
		}
	} // End of Coordinates

	public static void main(String[] args) throws Exception {
		System.setIn(new FileInputStream("res/5650.txt"));
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		StringBuilder sb = new StringBuilder();
		StringTokenizer st;

		int T = Integer.parseInt(br.readLine());
		for (int t = 1; t <= T; t++) {
			sb.append('#').append(t).append(' ');

			N = Integer.parseInt(br.readLine());
			init();

			for (int i = 0; i < N; i++) {
				st = new StringTokenizer(br.readLine());
				for (int j = 0; j < N; j++) {
					map[i][j] = Integer.parseInt(st.nextToken());
				}
			}

			for (int i = 0; i < N; i++) {
				for (int j = 0; j < N; j++) {

					// ���ƿ´ٴ� ���� �������� ��, isVisited�� ��� �� �� ����
					if (map[i][j] == 0) {
						for (int k = 0; k < 4; k++) {
							DFS(i, j, i, j, k);
						}
					}
				}
			}

			sb.append(result).append('\n');
		}

		bw.write(sb.toString());
		bw.close();
	} // End of main

	private static void DFS(int startX, int startY, int x, int y, int direction) {
		System.out.println("DFS(" + startX + ", " + startY + ", " + direction + ") ");

		int hitCount = 0;
		int nowX = x;
		int nowY = y;
		int newDir = direction;
		int count = 0;

		for (;;) {
			nowX = dirX[newDir] + nowX;
			nowY = dirY[newDir] + nowY;

//	            System.out.println("newDir : " + newDir);
//	            System.out.println("nowX : " + nowX + ", nowY : " + nowY);

			// ������ ����� ���� �ε����� ������ ������ �ݴ�� ��ȯ
			if (!rangeCheck(nowX, nowY)) {
				hitCount++;
				nowX = nowX - dirX[newDir];
				nowY = nowY - dirY[newDir];
				newDir = backDirection(newDir);
			} else {

				if (map[nowX][nowY] == 0 && nowX == startX && nowY == startY) {
					result = Math.max(result, hitCount);
					return;
				}

				if (map[nowX][nowY] == 1) {
					if (newDir == 0) {
						// �� -> ��
						newDir = backDirection(newDir);
					} else if (newDir == 1) {
						// �� -> ��
						newDir = 3;
					} else if (newDir == 2) {
						// �� -> ��
						newDir = 0;
					} else {
						// �� -> ��
						newDir = backDirection(newDir);
					}

					hitCount++;
				} else if (map[nowX][nowY] == 2) {
					if (newDir == 0) {
						// �� -> ��
						newDir = 3;
					} else if (newDir == 1) {
						// �� -> ��
						newDir = backDirection(newDir);
					} else if (newDir == 2) {
						// �� -> ��
						newDir = 1;
					} else {
						// �� -> ��
						newDir = backDirection(newDir);
					}

					hitCount++;
				} else if (map[nowX][nowY] == 3) {
					if (newDir == 0) {
						// �� -> ��
						newDir = 2;
					} else if (newDir == 1) {
						// �� -> ��
						newDir = backDirection(newDir);
					} else if (newDir == 2) {
						// �� -> ��
						newDir = backDirection(newDir);
					} else {
						// �� -> ��
						newDir = 1;
					}

					hitCount++;
				} else if (map[nowX][nowY] == 4) {
					if (newDir == 0) {
						// �� -> ��
						newDir = backDirection(newDir);
					} else if (newDir == 1) {
						// �� -> ��
						newDir = 2;
					} else if (newDir == 2) {
						// �� -> ��
						newDir = backDirection(newDir);
					} else {
						// �� -> ��
						newDir = 0;
					}

					hitCount++;
				} else if (map[nowX][nowY] == 5) {
					newDir = backDirection(newDir);
					hitCount++;
				} else if (map[nowX][nowY] == -1) {
					result = Math.max(result, hitCount);
					return;
				} else if (map[nowX][nowY] <= 6 && map[nowX][nowY] >= 10) {
					Coordinates wormCoor = findWormhole(map[nowX][nowY], nowX, nowY);
					nowX = wormCoor.x;
					nowY = wormCoor.y;
				}
			}

			count++;
			if (count == 10) {
				// break;
			}

		}

	} // End of DFS

	private static int backDirection(int dir) {
		if (dir == 0) {
			return 1;
		} else if (dir == 1) {
			return 0;
		} else if (dir == 2) {
			return 3;
		} else {
			return 2;
		}
	} // End of backDirection

	private static Coordinates findWormhole(int holeNum, int nowX, int nowY) {

		for (int i = 0; i < N; i++) {
			for (int j = 0; j < N; j++) {
				if (map[i][j] == holeNum && nowX != i && nowY != j) {
					return new Coordinates(i, j);
				}
			}
		}

		return null;
	} // End of findWormhole

	private static boolean rangeCheck(int nowX, int nowY) {
		return nowX >= 0 && nowX < N && nowY >= 0 && nowY < N;
	} // End of rangeCheck

	private static void init() {
		map = new int[N][N];
	} // End of init
	// End of Main class
}
