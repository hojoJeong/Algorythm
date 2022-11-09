package swea;

import java.util.Scanner;

public class swea_2589_º¸¹°¼¶ {
	static int n, m, cnt, max, check;
	static char[][] map;
	static int[] dx = {1, -1, 0, 0};
	static int[] dy = {0, 0, 1, -1};
	
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		n = sc.nextInt();
		m = sc.nextInt();
		map = new char[n][m];
		max = 0;
		check = 0;
		
		for(int i=0; i<n; i++) {
			String str = sc.next();
			for(int j=0; j<m; j++) {
				map[i] = str.toCharArray();
			}
		}
		
		bfs(0, 0);
	}
	static void bfs(int r, int c) {
		
		check++;
	}
}