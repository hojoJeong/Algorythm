package baekjoon;

import java.util.Scanner;

public class 색종이접기_17136 {
	static int[][] map;
	static int[] count;
	static int ans;

	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);

		ans = 0;
		map = new int[10][10];
		count = new int[5];
		for (int i = 0; i < 10; i++) {
			for (int j = 0; j < 10; j++) {
				map[i][j] = sc.nextInt();
			}
		}

		for (int i = 0; i < 10; i++) {
			for (int j = 0; j < 10; j++) {
				if (map[i][j] == 1) {
					if (check5(map, i, j) && count[4] <= 5) {
						changevalue(i, j, 5);
						count[4]++;
					}
				}
			}
			for (int j = 0; j < 10; j++) {
				if (map[i][j] == 1) {
					if (check4(map, i, j) && count[3] <= 5) {
						changevalue(i, j, 4);
						count[3]++;
					}
				}
			}
			for (int j = 0; j < 10; j++) {
				if (map[i][j] == 1) {
					if (check3(map, i, j) && count[2] <= 5) {
						changevalue(i, j, 3);
						count[2]++;
					}
				}
			}
			for (int j = 0; j < 10; j++) {
				if (map[i][j] == 1) {
					if (check2(map, i, j) && count[1] <= 5) {
						changevalue(i, j, 2);
						count[1]++;
					}
				}
			}
			for (int j = 0; j < 10; j++) {
				if (map[i][j] == 1) {
					map[i][j] = 0;
					count[0]++;
				}
			}
		}
		for (int i = 0; i < count.length; i++) {
			ans += count[i];
		}
		Loop1: for (int i = 0; i < 10; i++) {
			for (int j = 0; j < 10; j++) {
				if (map[i][j] == 1 || count[0] > 5) {
					ans = -1;
					break Loop1;
				}

			}
		}
//		result(map, count, 0, 0, 0);
		System.out.println(ans);

	}
//
//	static void result(int[][] arr, int[] cnt, int i, int j, int answer) {
//		if (i == 9 && j == 9) {
//			for (int k = 0; k < cnt.length; k++) {
//				answer += cnt[i];
//			}
//			if (answer < ans)
//				ans = answer;
//			Loop1: for (int r = 0; r < 10; r++) {
//				for (int c = 0; c < 10; c++) {
//					if (arr[r][c] == 1 || cnt[0] > 5) {
//						ans = -1;
//						break Loop1;
//					}
//				}
//			}
//			return;
//		}
//
//		for (; i < 10; i++) {
//			for (; j < 10; j++) {
//				if (arr[i][j] == 1) {
//					if (cnt[4] < 5 && check5(arr, i, j)) {
//						changevalue(i, j, 5);
//						cnt[4]++;
//						if (i < 5 && j < 5) {
//							i = i + 5;
//							j = j + 5;
//						}
//						result(arr, cnt, i, j, answer);
//					}
//					if (cnt[3] < 5 && check4(arr, i, j)) {
//						changevalue(i, j, 4);
//						cnt[3]++;
//						if (i < 4 && j < 4) {
//							i = i + 4;
//							j = j + 4;
//						}
//						result(arr, cnt, i, j, answer);
//					}
//					if (cnt[2] < 5 && check3(arr, i, j)) { 
//						changevalue(i, j, 3);
//						cnt[2]++;
//						if (i < 3 && j < 3) {
//							i = i + 3;
//							j = j + 3;
//						}
//						result(arr, cnt, i, j, answer);
//					}
//					if (cnt[1] < 5 && check5(arr, i, j)) {
//						changevalue(i, j, 2);
//						cnt[1]++;
//						if (i < 2 && j < 2) {
//							i = i + 2;
//							j = j + 2;
//						}
//						result(arr, cnt, i, j, answer);
//					}
//					if (map[i][j] == 1) {
//						map[i][j] = 0;
//						cnt[0]++;
//					}
//
//				}
//			}
//		}
//	}

	static boolean check5(int[][] arr, int r, int c) {
		int cnt = 0;
		if (r <= 5 && c <= 5) {
			for (int i = r; i < r + 5; i++) {
				for (int j = c; j < c + 5; j++) {
					if (arr[i][j] == 1)
						cnt++;
				}
			}
		}
		if (cnt == 25)
			return true;
		else
			return false;
	}

	static boolean check4(int[][] arr, int r, int c) {
		int cnt = 0;
		if (r <= 6 && c <= 6) {
			for (int i = r; i < r + 4; i++) {
				for (int j = c; j < c + 4; j++) {
					if (arr[i][j] == 1)
						cnt++;
				}
			}
		}
		if (cnt == 16)
			return true;
		else
			return false;
	}

	static boolean check3(int[][] arr, int r, int c) {
		int cnt = 0;
		if (r <= 7 && c <= 7) {
			for (int i = r; i < r + 3; i++) {
				for (int j = c; j < c + 3; j++) {
					if (arr[i][j] == 1)
						cnt++;
				}
			}
		}
		if (cnt == 9)
			return true;
		else
			return false;
	}

	static boolean check2(int[][] arr, int r, int c) {
		int cnt = 0;
		if (r <= 8 && c <= 8) {
			for (int i = r; i < r + 2; i++) {
				for (int j = c; j < c + 2; j++) {
					if (arr[i][j] == 1)
						cnt++;
				}
			}
		}
		if (cnt == 4)
			return true;
		else
			return false;
	}

	static void changevalue(int i, int j, int n) {
		for (int k = i; k < i + n; k++) {
			for (int h = j; h < j + n; h++) {
				map[k][h] = 0;
			}
		}
	}
}
